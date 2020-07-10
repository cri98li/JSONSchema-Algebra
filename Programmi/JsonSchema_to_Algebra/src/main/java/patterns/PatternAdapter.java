package patterns;

import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Translates JSON Schema pattern syntax (a subset of ECMAScript) such that the
 * Bricks automaton package can handle them.
 */

public class PatternAdapter implements REVisitor {

	private static final Logger logger = Logger.getLogger("PatternAdapter");

	/**
	 * A stack tracking path to root when traversing AST of a regular expression.
	 */
	private Stack<REToken> stack = new Stack<REToken>();

	/**
	 * @param ecmaRegex String in ECMAScript syntax
	 * @return regex String in Bricks syntax
	 * @throws REException if ecmaRegex has syntax errors
	 */
	public static String rewrite(String ecmaRegex) throws REException {

		logger.setLevel(Level.OFF); // Switch OFF/ALL
		// logger.setLevel(Level.ALL); // Switch OFF/ALL

		// Parse ecmaRegex into parse tree.
		RE ast = new RE(ecmaRegex);
		logger.info("AST " + ast.toString() + " has class " + ast.getClass());

		PatternAdapter pa = new PatternAdapter();
		ast.accept(pa);

		// If EcmaScript regex does not start with "^",
		// we must translate this to prefix "@" in Bricks syntax.

		// TODO - make sure we also bind end of string.

		logger.info(ast.toString() + "\t" + ast.getClass());
		logger.info("Result of rewriting: '" + pa.bricksRegex + "'");

		// Ensure that the pattern can be parsed
		// RegexParser parser = new RegexParser(ecmaRegex);
		// RegexNode node = parser.parse();
		// assert(node != null);

		// return translate(ecmaRegex);
		return pa.bricksRegex.toString();
	}

	// For assembling the rewritten regex in Bricks syntax.
	private StringBuffer bricksRegex = new StringBuffer();

	// In descending from RETokenOneOf to children, remember the parent.
	private REToken visitedParent = null;

	public void visit(RE re) {
		// TODO - this is currently just a 70% hack.
		if (stack.empty()) {
			if (!(re.firstToken instanceof RETokenStart) && !(re.firstToken instanceof RETokenOneOf)) {
				logger.info("start not bounded");
				bricksRegex.append('@');
			}
		}

		stack.push(re);

		if (stack.size() == 3 && (stack.elementAt(0) instanceof RE) && (stack.elementAt(1) instanceof RETokenOneOf)) {
			if (!(re.firstToken instanceof RETokenStart)) {
				logger.info("start not bounded (1)");
				bricksRegex.append('@');
			}
		}

		logger.info(re.getClass() + ": " + re.toString());
		REToken next = re.firstToken;
		logger.info(next.getClass() + ": " + next.toString());
		next.accept(this);

		while (next.hasNext()) {
			next = next.getNext();
			logger.info(next.getClass() + ": " + next.toString());
			next.accept(this);
		}

		stack.pop();

		// TODO - this is currently just a 70% hack.
		if (stack.empty() && re.firstToken == re.lastToken && (re.firstToken instanceof RETokenOneOf))
			return;

		if ((stack.empty() || (stack.size() == 2 && (stack.elementAt(0) instanceof RE)
				&& (stack.elementAt(1) instanceof RETokenOneOf)))) {
			int posEnd = -1;
			int posEndSub = -1;
			int pos = 0;

			// The regexp is bounded if the last two tokens are "End" and "EndSub".
			next = re.firstToken;

			if (next instanceof RETokenEnd)
				posEnd = pos;

			while (next.hasNext()) {
				pos++;
				next = next.getNext();

				if (next instanceof RETokenEnd)
					posEnd = pos;
				if (next instanceof RETokenEndSub)
					posEndSub = pos;
			}

			logger.info("posEnd: " + posEnd + ", posEndSub: " + posEndSub);

			if ((posEnd == -1 || ((posEnd != -1) && posEnd + 1 != posEndSub))) {
				logger.info("end not bounded");
				bricksRegex.append("@");
			}
		}
	}

	public void visit(RETokenAny re) {
		logger.info(re.getClass() + ": " + re.toString());
		bricksRegex.append(".");
	}

	public void visit(RETokenBackRef re) {
	}

	public void visit(RETokenChar re) {
		logger.info(re.getClass() + ": " + re);

		String token = re.toString();

		// Bricks syntax requires that @ symbols are escaped.
		token = token.replaceAll("@", "\\\\@");

		// Need to escape backslashes.
		token = token.replaceAll("\\\\", "\\\\");

		// Escape parentheses, if not done so yet.
		token = token.replaceAll("(?<!\\\\)\\(", "\\\\(");
		token = token.replaceAll("(?<!\\\\)\\)", "\\\\)");

		token = token.replaceAll("(?<!\\\\)\\[", "\\\\[");
		token = token.replaceAll("(?<!\\\\)\\]", "\\\\]");

		// We have to escape "." if it's not contained in [ ].
		if (this.visitedParent instanceof RETokenOneOf) {
			RETokenOneOf parent = (RETokenOneOf) this.visitedParent;
			if (!parent.negative)
				token = token.replaceAll("(?<!\\\\)\\.", "\\\\.");
			token = token.replaceAll("(?<!\\\\)\\+", "\\\\+");
		}

		bricksRegex.append(token);
	}

	public void visit(RETokenEnd re) {
		logger.info(re.getClass().toString() + ": " + re.toString());
	}

	public void visit(RETokenEndSub re) {
		logger.info(re.getClass().toString() + ": " + re.toString());
	}

	public void visit(RETokenLookAhead re) {
		logger.info(re.getClass().toString() + ": " + re.toString());
	}

	public void visit(RETokenOneOf re) {
		stack.push(re);
		logger.info(re.getClass() + ": " + re.toString() + (re.negative ? "(negative)" : "") + " with "
				+ re.options.size() + " options");

		// TODO - can be replaced by use of stack
		REToken oldParent = this.visitedParent;
		this.visitedParent = re;

		bricksRegex.append(re.negative ? "[^" : '(');

		for (int i = 0; i < re.options.size(); i++) {
			if (!re.negative && i > 0)
				bricksRegex.append('|');

			logger.info(re.options.elementAt(i).getClass().toString());
			((REToken) re.options.elementAt(i)).accept(this);
		}

		bricksRegex.append(re.negative ? ']' : ')');

		this.visitedParent = oldParent;
		stack.pop();
	}

	public void visit(RETokenPOSIX re) {
		logger.info(re.getClass() + ":" + re.toString() + ", type:" + RETokenPOSIX.s_nameTable[re.type] + ", negated "
				+ (re.negated));

		boolean openedPar = false;

		// Do not open a second "[".
		if (this.visitedParent instanceof RETokenOneOf) {
			RETokenOneOf parent = (RETokenOneOf) this.visitedParent;

			if (parent.negative && re.negated)
				throw new UnsupportedOperationException("Double negation not supported yet.");

			if (!parent.negative) {
				bricksRegex.append('[');
				openedPar = true;
			}

			if (parent.negative || re.negated)
				bricksRegex.append('^');

			// Do not add double negation.
		} else if (re.negated) {
			bricksRegex.append("[^");
			openedPar = true;
		} else {
			bricksRegex.append('[');
			openedPar = true;
		}

		if (re.type == RETokenPOSIX.SPACE) {
			// \S := [^\r\n\t\f\v ]

			bricksRegex.append("\r\n\t\f");
			bricksRegex.append((char) 11); // \v
			bricksRegex.append(' '); // a single space

		} else if (re.type == RETokenPOSIX.DIGIT) {
			// \d := [0-8]
			bricksRegex.append("0-9");

		} else if (re.type == RETokenPOSIX.PUNCT) { // TODO - do we really need this?
			// \.
			bricksRegex.append("\\.");

		} else if (re.type == RETokenPOSIX.ALNUM) {
			// \w
			bricksRegex.append("a-zA-Z0-9_");
		} else {
			assert false : "Not implemented yet"; // TODO
		}

		if (openedPar)
			bricksRegex.append(']');
	}

	public void visit(RETokenRange re) {
		logger.info(re.getClass().toString() + ": " + re.toString());

		boolean openedPar = false;

		// Do not open a second "[".
		if (this.visitedParent instanceof RETokenOneOf) {
			RETokenOneOf parent = (RETokenOneOf) this.visitedParent;

			if (!parent.negative) {
				bricksRegex.append("[");
				openedPar = true;
			}
		}

		re.dump(bricksRegex);

		if (openedPar)
			bricksRegex.append("]");
	}

	/**
	 * Highly similar to RETokenRepeated.dump() method.
	 */
	public void visit(RETokenRepeated re) {
		stack.push(re);
		logger.info(re.getClass().toString() + ": " + re.toString());

		bricksRegex.append("(");
		re.token.accept(this);
		bricksRegex.append(")");

		if ((re.max == Integer.MAX_VALUE) && (re.min <= 1))
			bricksRegex.append((re.min == 0) ? '*' : '+');
		else if ((re.min == 0) && (re.max == 1))
			bricksRegex.append('?');
		else {
			bricksRegex.append('{').append(re.min);
			if (re.max > re.min) {
				bricksRegex.append(',');
				if (re.max != Integer.MAX_VALUE)
					bricksRegex.append(re.max);
			}
			bricksRegex.append('}');
		}
		if (re.stingy)
			bricksRegex.append('?'); // TODO: check whether we need this.

		stack.pop();
	}

	public void visit(RETokenStart re) {
		logger.info(re.getClass().toString() + ": " + re.toString());
	}

	public void visit(RETokenWordBoundary re) {
		logger.info(re.getClass().toString() + ": " + re.toString());
	}

}
