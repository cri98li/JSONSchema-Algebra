// Generated from Grammatica.g4 by ANTLR 4.7.2
package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GrammaticaLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, NULL=41, TYPE=42, INT=43, DOUBLE=44, WS=45, STRING=46, 
		BOOLEAN=47;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
			"T__17", "T__18", "T__19", "T__20", "T__21", "T__22", "T__23", "T__24", 
			"T__25", "T__26", "T__27", "T__28", "T__29", "T__30", "T__31", "T__32", 
			"T__33", "T__34", "T__35", "T__36", "T__37", "T__38", "T__39", "NULL", 
			"TYPE", "INT", "DOUBLE", "WS", "STRING", "BOOLEAN", "ESC", "UNICODE", 
			"HEX", "SAFECODEPOINT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'{'", "','", "'}'", "'type'", "'('", "')'", "'bet'", "'xbet'", 
			"'length'", "'betitems'", "'pro'", "'mof'", "'not:'", "'allOf'", "'['", 
			"']'", "'oneOf'", "'anyOf'", "'req'", "'enum['", "'if'", "':'", "'then'", 
			"'else'", "'uniqueItems'", "'pattern'", "'items'", "'(;'", "';'", "'contains'", 
			"'properties'", "'::'", "'addp'", "'|'", "'const'", "'def'", "'='", "'ref'", 
			"'names'", "'annotations'", "'null'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, "NULL", "TYPE", "INT", "DOUBLE", "WS", 
			"STRING", "BOOLEAN"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public GrammaticaLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Grammatica.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\61\u0190\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\b"+
		"\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3"+
		"\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\21\3"+
		"\21\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3"+
		"\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\27\3\27\3"+
		"\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3"+
		"\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3"+
		"\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\36\3\36\3\37\3"+
		"\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3"+
		" \3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3#\3#\3$\3$\3$\3$\3$\3$\3%\3%\3%\3%\3&"+
		"\3&\3\'\3\'\3\'\3\'\3(\3(\3(\3(\3(\3(\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3"+
		")\3)\3*\3*\3*\3*\3*\3+\3+\3+\3+\3+\3+\3+\3+\3+\3+\3+\3+\3+\3+\3+\3+\3"+
		"+\3+\3+\3+\5+\u0146\n+\3,\6,\u0149\n,\r,\16,\u014a\3,\3,\6,\u014f\n,\r"+
		",\16,\u0150\5,\u0153\n,\3-\6-\u0156\n-\r-\16-\u0157\3-\3-\6-\u015c\n-"+
		"\r-\16-\u015d\3.\6.\u0161\n.\r.\16.\u0162\3.\3.\3/\3/\3/\7/\u016a\n/\f"+
		"/\16/\u016d\13/\3/\3/\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\5\60"+
		"\u017a\n\60\3\61\3\61\3\61\5\61\u017f\n\61\3\62\3\62\3\62\3\62\3\62\3"+
		"\62\3\62\3\62\3\62\3\62\5\62\u018b\n\62\3\63\3\63\3\64\3\64\2\2\65\3\3"+
		"\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21"+
		"!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!"+
		"A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\2c\2e\2g\2\3\2\t\3\2\62;\4\2\62"+
		";GG\5\2\13\f\17\17\"\"\4\2$$^^\n\2$$\61\61^^ddhhppttvv\5\2\62;CHch\5\2"+
		"\2!$$^^\2\u019c\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3"+
		"\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2"+
		"\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3"+
		"\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2"+
		"\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\2"+
		"9\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3"+
		"\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2"+
		"\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2"+
		"_\3\2\2\2\3i\3\2\2\2\5k\3\2\2\2\7m\3\2\2\2\to\3\2\2\2\13t\3\2\2\2\rv\3"+
		"\2\2\2\17x\3\2\2\2\21|\3\2\2\2\23\u0081\3\2\2\2\25\u0088\3\2\2\2\27\u0091"+
		"\3\2\2\2\31\u0095\3\2\2\2\33\u0099\3\2\2\2\35\u009e\3\2\2\2\37\u00a4\3"+
		"\2\2\2!\u00a6\3\2\2\2#\u00a8\3\2\2\2%\u00ae\3\2\2\2\'\u00b4\3\2\2\2)\u00b8"+
		"\3\2\2\2+\u00be\3\2\2\2-\u00c1\3\2\2\2/\u00c3\3\2\2\2\61\u00c8\3\2\2\2"+
		"\63\u00cd\3\2\2\2\65\u00d9\3\2\2\2\67\u00e1\3\2\2\29\u00e7\3\2\2\2;\u00ea"+
		"\3\2\2\2=\u00ec\3\2\2\2?\u00f5\3\2\2\2A\u0100\3\2\2\2C\u0103\3\2\2\2E"+
		"\u0108\3\2\2\2G\u010a\3\2\2\2I\u0110\3\2\2\2K\u0114\3\2\2\2M\u0116\3\2"+
		"\2\2O\u011a\3\2\2\2Q\u0120\3\2\2\2S\u012c\3\2\2\2U\u0145\3\2\2\2W\u0152"+
		"\3\2\2\2Y\u0155\3\2\2\2[\u0160\3\2\2\2]\u0166\3\2\2\2_\u0179\3\2\2\2a"+
		"\u017b\3\2\2\2c\u018a\3\2\2\2e\u018c\3\2\2\2g\u018e\3\2\2\2ij\7}\2\2j"+
		"\4\3\2\2\2kl\7.\2\2l\6\3\2\2\2mn\7\177\2\2n\b\3\2\2\2op\7v\2\2pq\7{\2"+
		"\2qr\7r\2\2rs\7g\2\2s\n\3\2\2\2tu\7*\2\2u\f\3\2\2\2vw\7+\2\2w\16\3\2\2"+
		"\2xy\7d\2\2yz\7g\2\2z{\7v\2\2{\20\3\2\2\2|}\7z\2\2}~\7d\2\2~\177\7g\2"+
		"\2\177\u0080\7v\2\2\u0080\22\3\2\2\2\u0081\u0082\7n\2\2\u0082\u0083\7"+
		"g\2\2\u0083\u0084\7p\2\2\u0084\u0085\7i\2\2\u0085\u0086\7v\2\2\u0086\u0087"+
		"\7j\2\2\u0087\24\3\2\2\2\u0088\u0089\7d\2\2\u0089\u008a\7g\2\2\u008a\u008b"+
		"\7v\2\2\u008b\u008c\7k\2\2\u008c\u008d\7v\2\2\u008d\u008e\7g\2\2\u008e"+
		"\u008f\7o\2\2\u008f\u0090\7u\2\2\u0090\26\3\2\2\2\u0091\u0092\7r\2\2\u0092"+
		"\u0093\7t\2\2\u0093\u0094\7q\2\2\u0094\30\3\2\2\2\u0095\u0096\7o\2\2\u0096"+
		"\u0097\7q\2\2\u0097\u0098\7h\2\2\u0098\32\3\2\2\2\u0099\u009a\7p\2\2\u009a"+
		"\u009b\7q\2\2\u009b\u009c\7v\2\2\u009c\u009d\7<\2\2\u009d\34\3\2\2\2\u009e"+
		"\u009f\7c\2\2\u009f\u00a0\7n\2\2\u00a0\u00a1\7n\2\2\u00a1\u00a2\7Q\2\2"+
		"\u00a2\u00a3\7h\2\2\u00a3\36\3\2\2\2\u00a4\u00a5\7]\2\2\u00a5 \3\2\2\2"+
		"\u00a6\u00a7\7_\2\2\u00a7\"\3\2\2\2\u00a8\u00a9\7q\2\2\u00a9\u00aa\7p"+
		"\2\2\u00aa\u00ab\7g\2\2\u00ab\u00ac\7Q\2\2\u00ac\u00ad\7h\2\2\u00ad$\3"+
		"\2\2\2\u00ae\u00af\7c\2\2\u00af\u00b0\7p\2\2\u00b0\u00b1\7{\2\2\u00b1"+
		"\u00b2\7Q\2\2\u00b2\u00b3\7h\2\2\u00b3&\3\2\2\2\u00b4\u00b5\7t\2\2\u00b5"+
		"\u00b6\7g\2\2\u00b6\u00b7\7s\2\2\u00b7(\3\2\2\2\u00b8\u00b9\7g\2\2\u00b9"+
		"\u00ba\7p\2\2\u00ba\u00bb\7w\2\2\u00bb\u00bc\7o\2\2\u00bc\u00bd\7]\2\2"+
		"\u00bd*\3\2\2\2\u00be\u00bf\7k\2\2\u00bf\u00c0\7h\2\2\u00c0,\3\2\2\2\u00c1"+
		"\u00c2\7<\2\2\u00c2.\3\2\2\2\u00c3\u00c4\7v\2\2\u00c4\u00c5\7j\2\2\u00c5"+
		"\u00c6\7g\2\2\u00c6\u00c7\7p\2\2\u00c7\60\3\2\2\2\u00c8\u00c9\7g\2\2\u00c9"+
		"\u00ca\7n\2\2\u00ca\u00cb\7u\2\2\u00cb\u00cc\7g\2\2\u00cc\62\3\2\2\2\u00cd"+
		"\u00ce\7w\2\2\u00ce\u00cf\7p\2\2\u00cf\u00d0\7k\2\2\u00d0\u00d1\7s\2\2"+
		"\u00d1\u00d2\7w\2\2\u00d2\u00d3\7g\2\2\u00d3\u00d4\7K\2\2\u00d4\u00d5"+
		"\7v\2\2\u00d5\u00d6\7g\2\2\u00d6\u00d7\7o\2\2\u00d7\u00d8\7u\2\2\u00d8"+
		"\64\3\2\2\2\u00d9\u00da\7r\2\2\u00da\u00db\7c\2\2\u00db\u00dc\7v\2\2\u00dc"+
		"\u00dd\7v\2\2\u00dd\u00de\7g\2\2\u00de\u00df\7t\2\2\u00df\u00e0\7p\2\2"+
		"\u00e0\66\3\2\2\2\u00e1\u00e2\7k\2\2\u00e2\u00e3\7v\2\2\u00e3\u00e4\7"+
		"g\2\2\u00e4\u00e5\7o\2\2\u00e5\u00e6\7u\2\2\u00e68\3\2\2\2\u00e7\u00e8"+
		"\7*\2\2\u00e8\u00e9\7=\2\2\u00e9:\3\2\2\2\u00ea\u00eb\7=\2\2\u00eb<\3"+
		"\2\2\2\u00ec\u00ed\7e\2\2\u00ed\u00ee\7q\2\2\u00ee\u00ef\7p\2\2\u00ef"+
		"\u00f0\7v\2\2\u00f0\u00f1\7c\2\2\u00f1\u00f2\7k\2\2\u00f2\u00f3\7p\2\2"+
		"\u00f3\u00f4\7u\2\2\u00f4>\3\2\2\2\u00f5\u00f6\7r\2\2\u00f6\u00f7\7t\2"+
		"\2\u00f7\u00f8\7q\2\2\u00f8\u00f9\7r\2\2\u00f9\u00fa\7g\2\2\u00fa\u00fb"+
		"\7t\2\2\u00fb\u00fc\7v\2\2\u00fc\u00fd\7k\2\2\u00fd\u00fe\7g\2\2\u00fe"+
		"\u00ff\7u\2\2\u00ff@\3\2\2\2\u0100\u0101\7<\2\2\u0101\u0102\7<\2\2\u0102"+
		"B\3\2\2\2\u0103\u0104\7c\2\2\u0104\u0105\7f\2\2\u0105\u0106\7f\2\2\u0106"+
		"\u0107\7r\2\2\u0107D\3\2\2\2\u0108\u0109\7~\2\2\u0109F\3\2\2\2\u010a\u010b"+
		"\7e\2\2\u010b\u010c\7q\2\2\u010c\u010d\7p\2\2\u010d\u010e\7u\2\2\u010e"+
		"\u010f\7v\2\2\u010fH\3\2\2\2\u0110\u0111\7f\2\2\u0111\u0112\7g\2\2\u0112"+
		"\u0113\7h\2\2\u0113J\3\2\2\2\u0114\u0115\7?\2\2\u0115L\3\2\2\2\u0116\u0117"+
		"\7t\2\2\u0117\u0118\7g\2\2\u0118\u0119\7h\2\2\u0119N\3\2\2\2\u011a\u011b"+
		"\7p\2\2\u011b\u011c\7c\2\2\u011c\u011d\7o\2\2\u011d\u011e\7g\2\2\u011e"+
		"\u011f\7u\2\2\u011fP\3\2\2\2\u0120\u0121\7c\2\2\u0121\u0122\7p\2\2\u0122"+
		"\u0123\7p\2\2\u0123\u0124\7q\2\2\u0124\u0125\7v\2\2\u0125\u0126\7c\2\2"+
		"\u0126\u0127\7v\2\2\u0127\u0128\7k\2\2\u0128\u0129\7q\2\2\u0129\u012a"+
		"\7p\2\2\u012a\u012b\7u\2\2\u012bR\3\2\2\2\u012c\u012d\7p\2\2\u012d\u012e"+
		"\7w\2\2\u012e\u012f\7n\2\2\u012f\u0130\7n\2\2\u0130T\3\2\2\2\u0131\u0132"+
		"\7q\2\2\u0132\u0133\7d\2\2\u0133\u0146\7l\2\2\u0134\u0146\5S*\2\u0135"+
		"\u0136\7u\2\2\u0136\u0137\7v\2\2\u0137\u0146\7t\2\2\u0138\u0139\7p\2\2"+
		"\u0139\u013a\7w\2\2\u013a\u0146\7o\2\2\u013b\u013c\7k\2\2\u013c\u013d"+
		"\7p\2\2\u013d\u0146\7v\2\2\u013e\u013f\7c\2\2\u013f\u0140\7t\2\2\u0140"+
		"\u0146\7t\2\2\u0141\u0142\7d\2\2\u0142\u0143\7q\2\2\u0143\u0144\7q\2\2"+
		"\u0144\u0146\7n\2\2\u0145\u0131\3\2\2\2\u0145\u0134\3\2\2\2\u0145\u0135"+
		"\3\2\2\2\u0145\u0138\3\2\2\2\u0145\u013b\3\2\2\2\u0145\u013e\3\2\2\2\u0145"+
		"\u0141\3\2\2\2\u0146V\3\2\2\2\u0147\u0149\t\2\2\2\u0148\u0147\3\2\2\2"+
		"\u0149\u014a\3\2\2\2\u014a\u0148\3\2\2\2\u014a\u014b\3\2\2\2\u014b\u0153"+
		"\3\2\2\2\u014c\u014e\7/\2\2\u014d\u014f\t\2\2\2\u014e\u014d\3\2\2\2\u014f"+
		"\u0150\3\2\2\2\u0150\u014e\3\2\2\2\u0150\u0151\3\2\2\2\u0151\u0153\3\2"+
		"\2\2\u0152\u0148\3\2\2\2\u0152\u014c\3\2\2\2\u0153X\3\2\2\2\u0154\u0156"+
		"\t\2\2\2\u0155\u0154\3\2\2\2\u0156\u0157\3\2\2\2\u0157\u0155\3\2\2\2\u0157"+
		"\u0158\3\2\2\2\u0158\u0159\3\2\2\2\u0159\u015b\7\60\2\2\u015a\u015c\t"+
		"\3\2\2\u015b\u015a\3\2\2\2\u015c\u015d\3\2\2\2\u015d\u015b\3\2\2\2\u015d"+
		"\u015e\3\2\2\2\u015eZ\3\2\2\2\u015f\u0161\t\4\2\2\u0160\u015f\3\2\2\2"+
		"\u0161\u0162\3\2\2\2\u0162\u0160\3\2\2\2\u0162\u0163\3\2\2\2\u0163\u0164"+
		"\3\2\2\2\u0164\u0165\b.\2\2\u0165\\\3\2\2\2\u0166\u016b\7$\2\2\u0167\u016a"+
		"\5a\61\2\u0168\u016a\n\5\2\2\u0169\u0167\3\2\2\2\u0169\u0168\3\2\2\2\u016a"+
		"\u016d\3\2\2\2\u016b\u0169\3\2\2\2\u016b\u016c\3\2\2\2\u016c\u016e\3\2"+
		"\2\2\u016d\u016b\3\2\2\2\u016e\u016f\7$\2\2\u016f^\3\2\2\2\u0170\u0171"+
		"\7v\2\2\u0171\u0172\7t\2\2\u0172\u0173\7w\2\2\u0173\u017a\7g\2\2\u0174"+
		"\u0175\7h\2\2\u0175\u0176\7c\2\2\u0176\u0177\7n\2\2\u0177\u0178\7u\2\2"+
		"\u0178\u017a\7g\2\2\u0179\u0170\3\2\2\2\u0179\u0174\3\2\2\2\u017a`\3\2"+
		"\2\2\u017b\u017e\7^\2\2\u017c\u017f\t\6\2\2\u017d\u017f\5c\62\2\u017e"+
		"\u017c\3\2\2\2\u017e\u017d\3\2\2\2\u017fb\3\2\2\2\u0180\u0181\7w\2\2\u0181"+
		"\u0182\5e\63\2\u0182\u0183\5e\63\2\u0183\u0184\5e\63\2\u0184\u0185\5e"+
		"\63\2\u0185\u018b\3\2\2\2\u0186\u0187\7z\2\2\u0187\u0188\5e\63\2\u0188"+
		"\u0189\5e\63\2\u0189\u018b\3\2\2\2\u018a\u0180\3\2\2\2\u018a\u0186\3\2"+
		"\2\2\u018bd\3\2\2\2\u018c\u018d\t\7\2\2\u018df\3\2\2\2\u018e\u018f\n\b"+
		"\2\2\u018fh\3\2\2\2\17\2\u0145\u014a\u0150\u0152\u0157\u015d\u0162\u0169"+
		"\u016b\u0179\u017e\u018a\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}