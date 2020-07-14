package patterns;

import java.io.Serializable;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.Vector;

// import gnu.regexp.*;

class IntPair implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int first, second;
}

class CharUnit implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public char ch;
	public boolean bk;
}

/**
 * RE provides the user interface for compiling and matching regular
 * expressions.
 *
 * <p>
 * A regular expression object (class RE) is compiled by constructing it from a
 * String, StringBuffer or character array, with optional compilation flags
 * (below) and an optional syntax specification (see RESyntax; if not specified,
 * <code>RESyntax.RE_SYNTAX_PERL5</code> is used).
 *
 * <p>
 * Various methods attempt to match input text against a compiled regular
 * expression. These methods are:
 * <LI><code>isMatch</code>: returns true if the input text in its entirety
 * matches the regular expression pattern.
 * <LI><code>getMatch</code>: returns the first match found in the input text,
 * or null if no match is found.
 * <LI><code>getAllMatches</code>: returns an array of all non-overlapping
 * matches found in the input text. If no matches are found, the array is
 * zero-length.
 * <LI><code>substitute</code>: substitute the first occurence of the pattern in
 * the input text with a replacement string (which may include metacharacters
 * $0-$9, see REMatch.substituteInto).
 * <LI><code>substituteAll</code>: same as above, but repeat for each match
 * before returning.
 * <LI><code>getMatchEnumeration</code>: returns an REMatchEnumeration object
 * that allows iteration over the matches (see REMatchEnumeration for some
 * reasons why you may want to do this instead of using
 * <code>getAllMatches</code>.
 *
 * <p>
 * These methods all have similar argument lists. The input can be a String, a
 * character array, a StringBuffer, a Reader or an InputStream of some sort.
 * Note that when using a Reader or InputStream, the stream read position cannot
 * be guaranteed after attempting a match (this is not a bug, but a consequence
 * of the way regular expressions work). Using an REMatchEnumeration can
 * eliminate most positioning problems.
 *
 * <p>
 * The optional index argument specifies the offset from the beginning of the
 * text at which the search should start (see the descriptions of some of the
 * execution flags for how this can affect positional pattern operators). For a
 * Reader or InputStream, this means an offset from the current read position,
 * so subsequent calls with the same index argument on a Reader or an
 * InputStream will not necessarily access the same position on the stream,
 * whereas repeated searches at a given index in a fixed string will return
 * consistent results.
 *
 * <p>
 * You can optionally affect the execution environment by using a combination of
 * execution flags (constants listed below).
 *
 * <p>
 * All operations on a regular expression are performed in a thread-safe manner.
 *
 * @author <A HREF="mailto:wes@cacas.org">Wes Biggs</A>
 * @version 1.1.4-dev, to be released
 */
public class RE extends REToken {

	private static final long serialVersionUID = 1L;

	// This String will be returned by getVersion()
	private static final String VERSION = "1.1.4-dev";

	// The localized strings are kept in a separate file
	private static ResourceBundle messages = PropertyResourceBundle.getBundle("patterns/MessagesBundle",
			Locale.getDefault());

	// These are, respectively, the first and last tokens in our linked list
	// If there is only one token, firstToken == lastToken
	protected REToken firstToken, lastToken;

	// This is the number of subexpressions in this regular expression,
	// with a minimum value of zero. Returned by getNumSubs()
	private int numSubs;

	/** Minimum length, in characters, of any possible match. */
	private int minimumLength;

	/**
	 * Compilation flag. Do not differentiate case. Subsequent searches using this
	 * RE will be case insensitive.
	 */
	public static final int REG_ICASE = 2;

	/**
	 * Compilation flag. The match-any-character operator (dot) will match a newline
	 * character. When set this overrides the syntax bit RE_DOT_NEWLINE (see
	 * RESyntax for details). This is equivalent to the "/s" operator in Perl.
	 */
	public static final int REG_DOT_NEWLINE = 4;

	/**
	 * Compilation flag. Use multiline mode. In this mode, the ^ and $ anchors will
	 * match based on newlines within the input. This is equivalent to the "/m"
	 * operator in Perl.
	 */
	public static final int REG_MULTILINE = 8;

	/**
	 * Execution flag. The match-beginning operator (^) will not match at the
	 * beginning of the input string. Useful for matching on a substring when you
	 * know the context of the input is such that position zero of the input to the
	 * match test is not actually position zero of the text.
	 *
	 * <p>
	 * This example demonstrates the results of various ways of matching on a
	 * substring.
	 *
	 * <p>
	 * <CODE>
	 * String s = "food bar fool";<BR>
	 * RE exp = new RE("^foo.");<BR>
	 * REMatch m0 = exp.getMatch(s);<BR>
	 * REMatch m1 = exp.getMatch(s.substring(8));<BR>
	 * REMatch m2 = exp.getMatch(s.substring(8),0,RE.REG_NOTBOL); <BR>
	 * REMatch m3 = exp.getMatch(s,8);                            <BR>
	 * REMatch m4 = exp.getMatch(s,8,RE.REG_ANCHORINDEX);         <BR>
	 * <P>
	 * // Results:<BR>
	 * //  m0 = "food"<BR>
	 * //  m1 = "fool"<BR>
	 * //  m2 = null<BR>
	 * //  m3 = null<BR>
	 * //  m4 = "fool"<BR>
	 * </CODE>
	 */
	public static final int REG_NOTBOL = 16;

	/**
	 * Execution flag. The match-end operator ($) does not match at the end of the
	 * input string. Useful for matching on substrings.
	 */
	public static final int REG_NOTEOL = 32;

	/**
	 * Execution flag. When a match method is invoked that starts matching at a
	 * non-zero index into the input, treat the input as if it begins at the index
	 * given. The effect of this flag is that the engine does not "see" any text in
	 * the input before the given index. This is useful so that the match-beginning
	 * operator (^) matches not at position 0 in the input string, but at the
	 * position the search started at (based on the index input given to the
	 * getMatch function). See the example under REG_NOTBOL. It also affects the use
	 * of the \&lt; and \b operators.
	 */
	public static final int REG_ANCHORINDEX = 64;

	/**
	 * Execution flag. The substitute and substituteAll methods will not attempt to
	 * interpolate occurrences of $1-$9 in the replacement text with the
	 * corresponding subexpressions. For example, you may want to replace all
	 * matches of "one dollar" with "$1".
	 */
	public static final int REG_NO_INTERPOLATE = 128;

	/** Returns a string representing the version of the gnu.regexp package. */
	public static final String version() {
		return VERSION;
	}

	// Retrieves a message from the ResourceBundle
	static final String getLocalizedMessage(String key) {
		return messages.getString(key);
	}

	/**
	 * Constructs a regular expression pattern buffer without any compilation flags
	 * set, and using the default syntax (RESyntax.RE_SYNTAX_PERL5).
	 *
	 * @param pattern A regular expression pattern, in the form of a String,
	 *                StringBuffer or char[]. Other input types will be converted to
	 *                strings using the toString() method.
	 * @exception REException          The input pattern could not be parsed.
	 * @exception NullPointerException The pattern was null.
	 */
	public RE(Object pattern) throws REException {
		this(pattern, 0, RESyntax.RE_SYNTAX_PERL5, 0, 0);
	}

	/**
	 * Constructs a regular expression pattern buffer using the specified
	 * compilation flags and the default syntax (RESyntax.RE_SYNTAX_PERL5).
	 *
	 * @param pattern A regular expression pattern, in the form of a String,
	 *                StringBuffer, or char[]. Other input types will be converted
	 *                to strings using the toString() method.
	 * @param cflags  The logical OR of any combination of the compilation flags
	 *                listed above.
	 * @exception REException          The input pattern could not be parsed.
	 * @exception NullPointerException The pattern was null.
	 */
	public RE(Object pattern, int cflags) throws REException {
		this(pattern, cflags, RESyntax.RE_SYNTAX_PERL5, 0, 0);
	}

	/**
	 * Constructs a regular expression pattern buffer using the specified
	 * compilation flags and regular expression syntax.
	 *
	 * @param pattern A regular expression pattern, in the form of a String,
	 *                StringBuffer, or char[]. Other input types will be converted
	 *                to strings using the toString() method.
	 * @param cflags  The logical OR of any combination of the compilation flags
	 *                listed above.
	 * @param syntax  The type of regular expression syntax to use.
	 * @exception REException          The input pattern could not be parsed.
	 * @exception NullPointerException The pattern was null.
	 */
	public RE(Object pattern, int cflags, RESyntax syntax) throws REException {
		this(pattern, cflags, syntax, 0, 0);
	}

	// internal constructor used for alternation
	private RE(REToken first, REToken last, int subs, int subIndex, int minLength) {
		super(subIndex);
		firstToken = first;
		lastToken = last;
		numSubs = subs;
		minimumLength = minLength;
		addToken(new RETokenEndSub(subIndex));
	}

	private RE(Object patternObj, int cflags, RESyntax syntax, int myIndex, int nextSub) throws REException {
		super(myIndex); // Subexpression index of this token.
		initialize(patternObj, cflags, syntax, myIndex, nextSub);
	}

	// For use by subclasses
	protected RE() {
		super(0);
	}

	// The meat of construction
	protected void initialize(Object patternObj, int cflags, RESyntax syntax, int myIndex, int nextSub)
			throws REException {
		char[] pattern;
		if (patternObj instanceof String) {
			pattern = ((String) patternObj).toCharArray();
		} else if (patternObj instanceof char[]) {
			pattern = (char[]) patternObj;
		} else if (patternObj instanceof StringBuffer) {
			pattern = new char[((StringBuffer) patternObj).length()];
			((StringBuffer) patternObj).getChars(0, pattern.length, pattern, 0);
		} else {
			pattern = patternObj.toString().toCharArray();
		}

		int pLength = pattern.length;

		numSubs = 0; // Number of subexpressions in this token.
		Vector<REToken> branches = null;

		// linked list of tokens (sort of -- some closed loops can exist)
		firstToken = lastToken = null;

		// Precalculate these so we don't pay for the math every time we
		// need to access them.
		boolean insens = ((cflags & REG_ICASE) > 0);

		// Parse pattern into tokens. Does anyone know if it's more efficient
		// to use char[] than a String.charAt()? I'm assuming so.

		// index tracks the position in the char array
		int index = 0;

		// this will be the current parse character (pattern[index])
		CharUnit unit = new CharUnit();

		// This is used for {x,y} calculations
		IntPair minMax = new IntPair();

		// Buffer a token so we can create a TokenRepeated, etc.
		REToken currentToken = null;
		char ch;

		while (index < pLength) {
			// read the next character unit (including backslash escapes)
			index = getCharUnit(pattern, index, unit);

			// ALTERNATION OPERATOR
			// \| or | (if RE_NO_BK_VBAR) or newline (if RE_NEWLINE_ALT)
			// not available if RE_LIMITED_OPS is set

			// TODO: the '\n' literal here should be a test against REToken.newline,
			// which unfortunately may be more than a single character.
			if (((unit.ch == '|' && (syntax.get(RESyntax.RE_NO_BK_VBAR) ^ unit.bk))
					|| (syntax.get(RESyntax.RE_NEWLINE_ALT) && (unit.ch == '\n') && !unit.bk))
					&& !syntax.get(RESyntax.RE_LIMITED_OPS)) {
				// make everything up to here be a branch. create vector if nec.
				addToken(currentToken);
				RE theBranch = new RE(firstToken, lastToken, numSubs, subIndex, minimumLength);
				minimumLength = 0;
				if (branches == null) {
					branches = new Vector<REToken>();
				}
				branches.addElement(theBranch);
				firstToken = lastToken = currentToken = null;
			}

			// INTERVAL OPERATOR:
			// {x} | {x,} | {x,y} (RE_INTERVALS && RE_NO_BK_BRACES)
			// \{x\} | \{x,\} | \{x,y\} (RE_INTERVALS && !RE_NO_BK_BRACES)
			//
			// OPEN QUESTION:
			// what is proper interpretation of '{' at start of string?

			else if ((unit.ch == '{') && syntax.get(RESyntax.RE_INTERVALS)
					&& (syntax.get(RESyntax.RE_NO_BK_BRACES) ^ unit.bk)) {
				int newIndex = getMinMax(pattern, index, minMax, syntax);
				if (newIndex > index) {
					if (minMax.first > minMax.second)
						throw new REException(getLocalizedMessage("interval.order"), REException.REG_BADRPT, newIndex);
					if (currentToken == null)
						throw new REException(getLocalizedMessage("repeat.no.token"), REException.REG_BADRPT, newIndex);
					if (currentToken instanceof RETokenRepeated)
						throw new REException(getLocalizedMessage("repeat.chained"), REException.REG_BADRPT, newIndex);
					if (currentToken instanceof RETokenWordBoundary || currentToken instanceof RETokenWordBoundary)
						throw new REException(getLocalizedMessage("repeat.assertion"), REException.REG_BADRPT,
								newIndex);
					if ((currentToken.getMinimumLength() == 0) && (minMax.second == Integer.MAX_VALUE))
						throw new REException(getLocalizedMessage("repeat.empty.token"), REException.REG_BADRPT,
								newIndex);
					index = newIndex;
					currentToken = setRepeated(currentToken, minMax.first, minMax.second, index);
				} else {
					addToken(currentToken);
					currentToken = new RETokenChar(subIndex, unit.ch, insens);
				}
			}

			// LIST OPERATOR:
			// [...] | [^...]

			else if ((unit.ch == '[') && !unit.bk) {
				Vector<REToken> options = new Vector<REToken>();
				boolean negative = false;
				char lastChar = 0;
				if (index == pLength)
					throw new REException(getLocalizedMessage("unmatched.bracket"), REException.REG_EBRACK, index);

				// Check for initial caret, negation
				if ((ch = pattern[index]) == '^') {
					negative = true;
					if (++index == pLength)
						throw new REException(getLocalizedMessage("class.no.end"), REException.REG_EBRACK, index);
					ch = pattern[index];
				}

				// Check for leading right bracket literal
				if (ch == ']') {
					lastChar = ch;
					if (++index == pLength)
						throw new REException(getLocalizedMessage("class.no.end"), REException.REG_EBRACK, index);
				}

				while ((ch = pattern[index++]) != ']') {
					if ((ch == '-') && (lastChar != 0)) {
						if (index == pLength)
							throw new REException(getLocalizedMessage("class.no.end"), REException.REG_EBRACK, index);
						if ((ch = pattern[index]) == ']') {
							options.addElement(new RETokenChar(subIndex, lastChar, insens));
							lastChar = '-';
						} else {
							options.addElement(new RETokenRange(subIndex, lastChar, ch, insens));
							lastChar = 0;
							index++;
						}
					} else if ((ch == '\\') && syntax.get(RESyntax.RE_BACKSLASH_ESCAPE_IN_LISTS)) {
						if (index == pLength)
							throw new REException(getLocalizedMessage("class.no.end"), REException.REG_EBRACK, index);
						int posixID = -1;
						boolean negate = false;
						char asciiEsc = 0;

						// Recognize \d, \S, \..
						if (("dswDSW0123456789.x".indexOf(pattern[index]) != -1)
								&& syntax.get(RESyntax.RE_CHAR_CLASS_ESC_IN_LISTS)) {
							switch (pattern[index]) {
							case 'D':
								negate = true;
							case 'd':
								posixID = RETokenPOSIX.DIGIT;
								break;
							case 'S':
								negate = true;
							case 's':
								posixID = RETokenPOSIX.SPACE; // edited by Steffi
								break;
							case 'W':
								negate = true;
							case 'w':
								posixID = RETokenPOSIX.ALNUM;
								break;
							case '0':
								posixID = RETokenPOSIX.INDEX0;
								negate = false;
								break;

							case '.': // Added by Steffi S.
								negate = false; // guesswork
								posixID = RETokenPOSIX.PUNCT; // "\."
								break;

							case 'x': // Added by Steffi S.
								throw new REException("Hex constants not yet supported.", REException.REG_EEND,
										subIndex);

							default:
								assert (Character.isDigit(pattern[index])) : "Expected octal constant here.";
								throw new REException("Octal constants not yet supported.", REException.REG_EEND,
										subIndex);

							}

							// Recognize \n, \t, ...
						} else if ("nrtfv\\".indexOf(pattern[index]) != -1) {
							switch (pattern[index]) {
							case 'n':
								asciiEsc = '\n';
								break;
							case 't':
								asciiEsc = '\t';
								break;
							case 'r':
								asciiEsc = '\r';
								break;
							case 'f': // Added by Steffi S.
								asciiEsc = '\f';
								break;
							case 'v': // Added by Steffi S.
								asciiEsc = 11;
								break;
							case '\\': // Added by Steffi S.
								asciiEsc = '\\';
								break;
							}
						}

						if (lastChar != 0) {
							options.addElement(new RETokenChar(subIndex, lastChar, insens));
						}

						if (posixID != -1) {
							options.addElement(new RETokenPOSIX(subIndex, posixID, insens, negate));
						} else if (asciiEsc != 0) {
							lastChar = asciiEsc;
						} else {
							lastChar = pattern[index];
						}
						++index;
					} else if ((ch == '[') && (syntax.get(RESyntax.RE_CHAR_CLASSES)) && (index < pLength)
							&& (pattern[index] == ':')) {
						StringBuffer posixSet = new StringBuffer();
						index = getPosixSet(pattern, index + 1, posixSet);
						int posixId = RETokenPOSIX.intValue(posixSet.toString());
						if (posixId != -1)
							options.addElement(new RETokenPOSIX(subIndex, posixId, insens, false));
					} else {
						if (lastChar != 0) {
							if (lastChar != '\\')
								options.addElement(new RETokenChar(subIndex, lastChar, insens));
							else {
								RETokenChar bs = new RETokenChar(subIndex, lastChar, insens);
								bs.chain(new RETokenChar(subIndex, lastChar, insens));
								options.addElement(bs);
							}
						}
						lastChar = ch;
					}
					if (index == pLength)
						throw new REException(getLocalizedMessage("class.no.end"), REException.REG_EBRACK, index);
				} // while in list
					// Out of list, index is one past ']'

				// Process last char from list.
				if (lastChar != 0) {
					if (lastChar != '\\')
						options.addElement(new RETokenChar(subIndex, lastChar, insens));
					else {
						RETokenChar bs = new RETokenChar(subIndex, lastChar, insens);
						bs.chain(new RETokenChar(subIndex, lastChar, insens));
						options.addElement(bs);
					}
				}

				// Create a new RETokenOneOf
				addToken(currentToken);
				options.trimToSize();
				currentToken = new RETokenOneOf(subIndex, options, negative);
			}

			// SUBEXPRESSIONS
			// (...) | \(...\) depending on RE_NO_BK_PARENS

			else if ((unit.ch == '(') && (syntax.get(RESyntax.RE_NO_BK_PARENS) ^ unit.bk)) {
				boolean pure = false;
				boolean comment = false;
				boolean lookAhead = false;
				boolean negativelh = false;
				if ((index + 1 < pLength) && (pattern[index] == '?')) {
					switch (pattern[index + 1]) {
					case '!':
						if (syntax.get(RESyntax.RE_LOOKAHEAD)) {
							pure = true;
							negativelh = true;
							lookAhead = true;
							index += 2;
						}
						break;
					case '=':
						if (syntax.get(RESyntax.RE_LOOKAHEAD)) {
							pure = true;
							lookAhead = true;
							index += 2;
						}
						break;
					case ':':
						if (syntax.get(RESyntax.RE_PURE_GROUPING)) {
							pure = true;
							index += 2;
						}
						break;
					case '#':
						if (syntax.get(RESyntax.RE_COMMENTS)) {
							comment = true;
						}
						break;
					default:
						throw new REException(getLocalizedMessage("repeat.no.token"), REException.REG_BADRPT, index);
					}
				}

				if (index >= pLength) {
					throw new REException(getLocalizedMessage("unmatched.paren"), REException.REG_ESUBREG, index);
				}

				// find end of subexpression
				int endIndex = index;
				int nextIndex = index;
				int nested = 0;

				while (((nextIndex = getCharUnit(pattern, endIndex, unit)) > 0)
						&& !(nested == 0 && (unit.ch == ')') && (syntax.get(RESyntax.RE_NO_BK_PARENS) ^ unit.bk)))
					if ((endIndex = nextIndex) >= pLength)
						throw new REException(getLocalizedMessage("subexpr.no.end"), REException.REG_ESUBREG,
								nextIndex);
					else if (unit.ch == '(' && (syntax.get(RESyntax.RE_NO_BK_PARENS) ^ unit.bk))
						nested++;
					else if (unit.ch == ')' && (syntax.get(RESyntax.RE_NO_BK_PARENS) ^ unit.bk))
						nested--;

				// endIndex is now position at a ')','\)'
				// nextIndex is end of string or position after ')' or '\)'

				if (comment)
					index = nextIndex;
				else { // not a comment
						// create RE subexpression as token.
					addToken(currentToken);
					if (!pure) {
						numSubs++;
					}

					int useIndex = (pure || lookAhead) ? 0 : nextSub + numSubs;
					currentToken = new RE(String.valueOf(pattern, index, endIndex - index).toCharArray(), cflags,
							syntax, useIndex, nextSub + numSubs);
					numSubs += ((RE) currentToken).getNumSubs();

					if (lookAhead) {
						currentToken = new RETokenLookAhead(currentToken, negativelh);
					}

					index = nextIndex;
				} // not a comment
			} // subexpression

			// UNMATCHED RIGHT PAREN
			// ) or \) throw exception if
			// !syntax.get(RESyntax.RE_UNMATCHED_RIGHT_PAREN_ORD)
			else if (!syntax.get(RESyntax.RE_UNMATCHED_RIGHT_PAREN_ORD)
					&& ((unit.ch == ')') && (syntax.get(RESyntax.RE_NO_BK_PARENS) ^ unit.bk))) {
				throw new REException(getLocalizedMessage("unmatched.paren"), REException.REG_EPAREN, index);
			}

			// START OF LINE OPERATOR
			// ^

			else if ((unit.ch == '^') && !unit.bk) {
				addToken(currentToken);
				currentToken = null;
				addToken(new RETokenStart(subIndex, ((cflags & REG_MULTILINE) > 0) ? syntax.getLineSeparator() : null));
			}

			// END OF LINE OPERATOR
			// $

			else if ((unit.ch == '$') && !unit.bk) {
				addToken(currentToken);
				currentToken = null;
				addToken(new RETokenEnd(subIndex, ((cflags & REG_MULTILINE) > 0) ? syntax.getLineSeparator() : null));
			}

			// MATCH-ANY-CHARACTER OPERATOR (except possibly newline and null)
			// .

			else if ((unit.ch == '.') && !unit.bk) {
				addToken(currentToken);
				currentToken = new RETokenAny(subIndex,
						syntax.get(RESyntax.RE_DOT_NEWLINE) || ((cflags & REG_DOT_NEWLINE) > 0),
						syntax.get(RESyntax.RE_DOT_NOT_NULL));
			}

			// ZERO-OR-MORE REPEAT OPERATOR
			// *

			else if ((unit.ch == '*') && !unit.bk) {
				if (currentToken == null)
					throw new REException(getLocalizedMessage("repeat.no.token"), REException.REG_BADRPT, index);
				if (currentToken instanceof RETokenRepeated)
					throw new REException(getLocalizedMessage("repeat.chained"), REException.REG_BADRPT, index);
				if (currentToken instanceof RETokenWordBoundary || currentToken instanceof RETokenWordBoundary)
					throw new REException(getLocalizedMessage("repeat.assertion"), REException.REG_BADRPT, index);
				if (currentToken.getMinimumLength() == 0)
					throw new REException(getLocalizedMessage("repeat.empty.token"), REException.REG_BADRPT, index);
				currentToken = setRepeated(currentToken, 0, Integer.MAX_VALUE, index);
			}

			// ONE-OR-MORE REPEAT OPERATOR
			// + | \+ depending on RE_BK_PLUS_QM
			// not available if RE_LIMITED_OPS is set

			else if ((unit.ch == '+') && !syntax.get(RESyntax.RE_LIMITED_OPS)
					&& (!syntax.get(RESyntax.RE_BK_PLUS_QM) ^ unit.bk)) {
				if (currentToken == null)
					throw new REException(getLocalizedMessage("repeat.no.token"), REException.REG_BADRPT, index);
				if (currentToken instanceof RETokenRepeated)
					throw new REException(getLocalizedMessage("repeat.chained"), REException.REG_BADRPT, index);
				if (currentToken instanceof RETokenWordBoundary || currentToken instanceof RETokenWordBoundary)
					throw new REException(getLocalizedMessage("repeat.assertion"), REException.REG_BADRPT, index);
				if (currentToken.getMinimumLength() == 0)
					throw new REException(getLocalizedMessage("repeat.empty.token"), REException.REG_BADRPT, index);
				currentToken = setRepeated(currentToken, 1, Integer.MAX_VALUE, index);
			}

			// ZERO-OR-ONE REPEAT OPERATOR / STINGY MATCHING OPERATOR
			// ? | \? depending on RE_BK_PLUS_QM
			// not available if RE_LIMITED_OPS is set
			// stingy matching if RE_STINGY_OPS is set and it follows a quantifier

			else if ((unit.ch == '?') && !syntax.get(RESyntax.RE_LIMITED_OPS)
					&& (!syntax.get(RESyntax.RE_BK_PLUS_QM) ^ unit.bk)) {
				if (currentToken == null)
					throw new REException(getLocalizedMessage("repeat.no.token"), REException.REG_BADRPT, index);

				// Check for stingy matching on RETokenRepeated
				if (currentToken instanceof RETokenRepeated) {
					if (syntax.get(RESyntax.RE_STINGY_OPS) && !((RETokenRepeated) currentToken).isStingy())
						((RETokenRepeated) currentToken).makeStingy();
					else
						throw new REException(getLocalizedMessage("repeat.chained"), REException.REG_BADRPT, index);
				} else if (currentToken instanceof RETokenWordBoundary || currentToken instanceof RETokenWordBoundary)
					throw new REException(getLocalizedMessage("repeat.assertion"), REException.REG_BADRPT, index);
				else
					currentToken = setRepeated(currentToken, 0, 1, index);
			}

			// BACKREFERENCE OPERATOR
			// \1 \2 ... \9
			// not available if RE_NO_BK_REFS is set

			else if (unit.bk && Character.isDigit(unit.ch) && !syntax.get(RESyntax.RE_NO_BK_REFS)) {
				addToken(currentToken);
				currentToken = new RETokenBackRef(subIndex, Character.digit(unit.ch, 10), insens);
				throw new REException("Octal constants not yet supported.", REException.REG_EEND, subIndex); // TODO
			}

			// TODO - implement support for Hex constants.
			// Added by Steffi S.
			// \x<hexnum>
			else if (unit.bk && (unit.ch == 'x')) {
				throw new REException("Hex constants not yet supported.", REException.REG_EEND, subIndex); // TODO
			}

			// START OF STRING OPERATOR
			// \A if RE_STRING_ANCHORS is set

			else if (unit.bk && (unit.ch == 'A') && syntax.get(RESyntax.RE_STRING_ANCHORS)) {
				addToken(currentToken);
				currentToken = new RETokenStart(subIndex, null);
			}

			// WORD BREAK OPERATOR
			// \b if ????

			else if (unit.bk && (unit.ch == 'b') && syntax.get(RESyntax.RE_STRING_ANCHORS)) {
				addToken(currentToken);
				currentToken = new RETokenWordBoundary(subIndex, RETokenWordBoundary.BEGIN | RETokenWordBoundary.END,
						false);
				throw new REException("\\b not yet supported.", REException.REG_EEND, subIndex);
			}

			// WORD BEGIN OPERATOR
			// \< if ????
			else if (unit.bk && (unit.ch == '<')) {
				addToken(currentToken);
				currentToken = new RETokenWordBoundary(subIndex, RETokenWordBoundary.BEGIN, false);
			}

			// WORD END OPERATOR
			// \> if ????
			else if (unit.bk && (unit.ch == '>')) {
				addToken(currentToken);
				currentToken = new RETokenWordBoundary(subIndex, RETokenWordBoundary.END, false);
			}

			// NON-WORD BREAK OPERATOR
			// \B if ????

			else if (unit.bk && (unit.ch == 'B') && syntax.get(RESyntax.RE_STRING_ANCHORS)) {
				addToken(currentToken);
				currentToken = new RETokenWordBoundary(subIndex, RETokenWordBoundary.BEGIN | RETokenWordBoundary.END,
						true);
				throw new REException("\\B not yet supported.", REException.REG_EEND, subIndex);
			}

			// CONTROL CHARACTERS
			// \c
			else if (unit.bk && (unit.ch == 'c') && syntax.get(RESyntax.RE_STRING_ANCHORS)) {
				throw new REException("\\cY not yet supported.", REException.REG_EEND, subIndex);
			}

			// DIGIT OPERATOR
			// \d if RE_CHAR_CLASS_ESCAPES is set

			else if (unit.bk && (unit.ch == 'd') && syntax.get(RESyntax.RE_CHAR_CLASS_ESCAPES)) {
				addToken(currentToken);
				currentToken = new RETokenPOSIX(subIndex, RETokenPOSIX.DIGIT, insens, false);
			}

			// NON-DIGIT OPERATOR
			// \D

			else if (unit.bk && (unit.ch == 'D') && syntax.get(RESyntax.RE_CHAR_CLASS_ESCAPES)) {
				addToken(currentToken);
				currentToken = new RETokenPOSIX(subIndex, RETokenPOSIX.DIGIT, insens, true);
			}

			// NEWLINE ESCAPE
			// \n

			else if (unit.bk && (unit.ch == 'n')) {
				addToken(currentToken);
				currentToken = new RETokenChar(subIndex, '\n', false);
			}

			// RETURN ESCAPE
			// \r

			else if (unit.bk && (unit.ch == 'r')) {
				addToken(currentToken);
				currentToken = new RETokenChar(subIndex, '\r', false);
			}

			// Added by Steffi S.
			// \f

			else if (unit.bk && (unit.ch == 'f')) {
				addToken(currentToken);
				currentToken = new RETokenChar(subIndex, '\f', false);
			}

			// Added by Steffi S.
			// \v

			else if (unit.bk && (unit.ch == 'v')) {
				addToken(currentToken);
				currentToken = new RETokenChar(subIndex, (char) 11, false);
			}

			// WHITESPACE OPERATOR
			// \s if RE_CHAR_CLASS_ESCAPES is set

			else if (unit.bk && (unit.ch == 's') && syntax.get(RESyntax.RE_CHAR_CLASS_ESCAPES)) {
				addToken(currentToken);
				currentToken = new RETokenPOSIX(subIndex, RETokenPOSIX.SPACE, insens, false);
			}

			// NON-WHITESPACE OPERATOR
			// \S

			else if (unit.bk && (unit.ch == 'S') && syntax.get(RESyntax.RE_CHAR_CLASS_ESCAPES)) {
				addToken(currentToken);
				currentToken = new RETokenPOSIX(subIndex, RETokenPOSIX.SPACE, insens, true);
			}

			// TAB ESCAPE
			// \t

			else if (unit.bk && (unit.ch == 't')) {
				addToken(currentToken);
				currentToken = new RETokenChar(subIndex, '\t', false);
			}

			// ALPHANUMERIC OPERATOR
			// \w

			else if (unit.bk && (unit.ch == 'w') && syntax.get(RESyntax.RE_CHAR_CLASS_ESCAPES)) {
				addToken(currentToken);
				currentToken = new RETokenPOSIX(subIndex, RETokenPOSIX.ALNUM, insens, false);
			}

			// NON-ALPHANUMERIC OPERATOR
			// \W

			else if (unit.bk && (unit.ch == 'W') && syntax.get(RESyntax.RE_CHAR_CLASS_ESCAPES)) {
				addToken(currentToken);
				currentToken = new RETokenPOSIX(subIndex, RETokenPOSIX.ALNUM, insens, true);
			}

			// END OF STRING OPERATOR
			// \Z

			else if (unit.bk && (unit.ch == 'Z') && syntax.get(RESyntax.RE_STRING_ANCHORS)) {
				addToken(currentToken);
				currentToken = new RETokenEnd(subIndex, null);
			}

			// Escaped symbols -- added by Steffi S.
			// \\ | \* | \+ | \[ | \] for example
			else if (unit.bk) {
				addToken(currentToken);
				currentToken = new RETokenChar(subIndex, '\\', insens);
				currentToken.chain(new RETokenChar(subIndex, unit.ch, insens));
			}

			// NON-SPECIAL CHARACTER (or escape to make literal)
			// c | 1 for example

			else { // not a special character

				addToken(currentToken);
				currentToken = new RETokenChar(subIndex, unit.ch, insens);
			}
		} // end while

		// Add final buffered token and an EndSub marker
		addToken(currentToken);

		if (branches != null) {
			branches.addElement(new RE(firstToken, lastToken, numSubs, subIndex, minimumLength));
			branches.trimToSize(); // compact the Vector
			minimumLength = 0;
			firstToken = lastToken = null;
			addToken(new RETokenOneOf(subIndex, branches, false));
		} else
			addToken(new RETokenEndSub(subIndex));
	}

	private static int getCharUnit(char[] input, int index, CharUnit unit) throws REException {
		unit.ch = input[index++];
		if (unit.bk = (unit.ch == '\\'))
			if (index < input.length)
				unit.ch = input[index++];
			else
				throw new REException(getLocalizedMessage("ends.with.backslash"), REException.REG_ESCAPE, index);
		return index;
	}

	/**
	 * Returns the maximum number of subexpressions in this regular expression. If
	 * the expression contains branches, the value returned will be the maximum
	 * subexpressions in any of the branches.
	 */
	public int getNumSubs() {
		return numSubs;
	}

	// Overrides REToken.setUncle
	void setUncle(REToken uncle) {
		if (lastToken != null) {
			lastToken.setUncle(uncle);
		} else
			super.setUncle(uncle); // to deal with empty subexpressions
	}

	// Overrides REToken.chain

	boolean chain(REToken next) {
		super.chain(next);
		setUncle(next);
		return true;
	}

	/**
	 * Returns the minimum number of characters that could possibly constitute a
	 * match of this regular expression.
	 */
	public int getMinimumLength() {
		return minimumLength;
	}

	/* Helper function for constructor */
	private void addToken(REToken next) {
		if (next == null)
			return;
		minimumLength += next.getMinimumLength();
		if (firstToken == null) {
			lastToken = firstToken = next;
		} else {
			// if chain returns false, it "rejected" the token due to
			// an optimization, and next was combined with lastToken
			if (lastToken.chain(next)) {
				lastToken = next;
			}
		}
	}

	private static REToken setRepeated(REToken current, int min, int max, int index) throws REException {
		if (current == null)
			throw new REException(getLocalizedMessage("repeat.no.token"), REException.REG_BADRPT, index);
		return new RETokenRepeated(current.subIndex, current, min, max);
	}

	private static int getPosixSet(char[] pattern, int index, StringBuffer buf) {
		// Precondition: pattern[index-1] == ':'
		// we will return pos of closing ']'.
		int i;
		for (i = index; i < (pattern.length - 1); i++) {
			if ((pattern[i] == ':') && (pattern[i + 1] == ']'))
				return i + 2;
			buf.append(pattern[i]);
		}
		return index; // didn't match up
	}

	private int getMinMax(char[] input, int index, IntPair minMax, RESyntax syntax) throws REException {
		// Precondition: input[index-1] == '{', minMax != null

		boolean mustMatch = !syntax.get(RESyntax.RE_NO_BK_BRACES);
		int startIndex = index;
		if (index == input.length) {
			if (mustMatch)
				throw new REException(getLocalizedMessage("unmatched.brace"), REException.REG_EBRACE, index);
			else
				return startIndex;
		}

		int min, max = 0;
		CharUnit unit = new CharUnit();
		StringBuffer buf = new StringBuffer();

		// Read string of digits
		do {
			index = getCharUnit(input, index, unit);
			if (Character.isDigit(unit.ch))
				buf.append(unit.ch);
		} while ((index != input.length) && Character.isDigit(unit.ch));

		// Check for {} tomfoolery
		if (buf.length() == 0) {
			if (mustMatch)
				throw new REException(getLocalizedMessage("interval.error"), REException.REG_EBRACE, index);
			else
				return startIndex;
		}

		min = Integer.parseInt(buf.toString());

		if ((unit.ch == '}') && (syntax.get(RESyntax.RE_NO_BK_BRACES) ^ unit.bk))
			max = min;
		else if (index == input.length)
			if (mustMatch)
				throw new REException(getLocalizedMessage("interval.no.end"), REException.REG_EBRACE, index);
			else
				return startIndex;
		else if ((unit.ch == ',') && !unit.bk) {
			buf = new StringBuffer();
			// Read string of digits
			while (((index = getCharUnit(input, index, unit)) != input.length) && Character.isDigit(unit.ch))
				buf.append(unit.ch);

			if (!((unit.ch == '}') && (syntax.get(RESyntax.RE_NO_BK_BRACES) ^ unit.bk)))
				if (mustMatch)
					throw new REException(getLocalizedMessage("interval.error"), REException.REG_EBRACE, index);
				else
					return startIndex;

			// This is the case of {x,}
			if (buf.length() == 0)
				max = Integer.MAX_VALUE;
			else
				max = Integer.parseInt(buf.toString());
		} else if (mustMatch)
			throw new REException(getLocalizedMessage("interval.error"), REException.REG_EBRACE, index);
		else
			return startIndex;

		// We know min and max now, and they are valid.

		minMax.first = min;
		minMax.second = max;

		// return the index following the '}'
		return index;
	}

	/**
	 * Return a human readable form of the compiled regular expression, useful for
	 * debugging.
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		dump(sb);
		return sb.toString();
	}

	void dump(StringBuffer os) {
		os.append('(');
		if (subIndex == 0)
			os.append("?:");
		if (firstToken != null)
			firstToken.dumpAll(os);
		os.append(')');
	}

	public void accept(REVisitor v) {
		v.visit(this);
	}

}
