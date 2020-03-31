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
		T__38=39, NULL=40, TYPE=41, INT=42, DOUBLE=43, WS=44, STRING=45, BOOLEAN=46;
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
			"T__33", "T__34", "T__35", "T__36", "T__37", "T__38", "NULL", "TYPE", 
			"INT", "DOUBLE", "WS", "STRING", "BOOLEAN", "ESC", "UNICODE", "HEX", 
			"SAFECODEPOINT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'{'", "','", "'}'", "'type'", "'('", "')'", "'bet'", "'xbet'", 
			"'length'", "'betitems'", "'pro'", "'mof'", "'not:'", "'allOf'", "'['", 
			"']'", "'oneOf'", "'anyOf'", "'req'", "'enum['", "'if'", "':'", "'then'", 
			"'else'", "'uniqueItems'", "'pattern'", "'items'", "';'", "'contains'", 
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
			null, null, null, null, "NULL", "TYPE", "INT", "DOUBLE", "WS", "STRING", 
			"BOOLEAN"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\60\u0187\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\3\2"+
		"\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3"+
		"\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\16\3\16\3"+
		"\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3"+
		"\24\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\27\3\27\3\30\3\30\3"+
		"\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3"+
		"\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3"+
		"\34\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\36\3"+
		"\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3"+
		" \3 \3 \3!\3!\3!\3!\3!\3\"\3\"\3#\3#\3#\3#\3#\3#\3$\3$\3$\3$\3%\3%\3&"+
		"\3&\3&\3&\3\'\3\'\3\'\3\'\3\'\3\'\3(\3(\3(\3(\3(\3(\3(\3(\3(\3(\3(\3("+
		"\3)\3)\3)\3)\3)\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*"+
		"\3*\5*\u0140\n*\3+\5+\u0143\n+\3+\6+\u0146\n+\r+\16+\u0147\3,\5,\u014b"+
		"\n,\3,\6,\u014e\n,\r,\16,\u014f\3,\3,\6,\u0154\n,\r,\16,\u0155\3-\6-\u0159"+
		"\n-\r-\16-\u015a\3-\3-\3.\3.\7.\u0161\n.\f.\16.\u0164\13.\3.\3.\3/\3/"+
		"\3/\3/\3/\3/\3/\3/\3/\5/\u0171\n/\3\60\3\60\3\60\5\60\u0176\n\60\3\61"+
		"\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\5\61\u0182\n\61\3\62\3\62"+
		"\3\63\3\63\3\u0162\2\64\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f"+
		"\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63"+
		"\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\2a\2c\2"+
		"e\2\3\2\b\3\2\62;\4\2\62;GG\5\2\13\f\17\17\"\"\n\2$$\61\61^^ddhhppttv"+
		"v\5\2\62;CHch\5\2\2!$$^^\2\u0191\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2"+
		"\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2"+
		"\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2"+
		"\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2"+
		"\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2"+
		"\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2"+
		"\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O"+
		"\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2"+
		"\2\2\2]\3\2\2\2\3g\3\2\2\2\5i\3\2\2\2\7k\3\2\2\2\tm\3\2\2\2\13r\3\2\2"+
		"\2\rt\3\2\2\2\17v\3\2\2\2\21z\3\2\2\2\23\177\3\2\2\2\25\u0086\3\2\2\2"+
		"\27\u008f\3\2\2\2\31\u0093\3\2\2\2\33\u0097\3\2\2\2\35\u009c\3\2\2\2\37"+
		"\u00a2\3\2\2\2!\u00a4\3\2\2\2#\u00a6\3\2\2\2%\u00ac\3\2\2\2\'\u00b2\3"+
		"\2\2\2)\u00b6\3\2\2\2+\u00bc\3\2\2\2-\u00bf\3\2\2\2/\u00c1\3\2\2\2\61"+
		"\u00c6\3\2\2\2\63\u00cb\3\2\2\2\65\u00d7\3\2\2\2\67\u00df\3\2\2\29\u00e5"+
		"\3\2\2\2;\u00e7\3\2\2\2=\u00f0\3\2\2\2?\u00fb\3\2\2\2A\u00fe\3\2\2\2C"+
		"\u0103\3\2\2\2E\u0105\3\2\2\2G\u010b\3\2\2\2I\u010f\3\2\2\2K\u0111\3\2"+
		"\2\2M\u0115\3\2\2\2O\u011b\3\2\2\2Q\u0127\3\2\2\2S\u013f\3\2\2\2U\u0142"+
		"\3\2\2\2W\u014a\3\2\2\2Y\u0158\3\2\2\2[\u015e\3\2\2\2]\u0170\3\2\2\2_"+
		"\u0172\3\2\2\2a\u0181\3\2\2\2c\u0183\3\2\2\2e\u0185\3\2\2\2gh\7}\2\2h"+
		"\4\3\2\2\2ij\7.\2\2j\6\3\2\2\2kl\7\177\2\2l\b\3\2\2\2mn\7v\2\2no\7{\2"+
		"\2op\7r\2\2pq\7g\2\2q\n\3\2\2\2rs\7*\2\2s\f\3\2\2\2tu\7+\2\2u\16\3\2\2"+
		"\2vw\7d\2\2wx\7g\2\2xy\7v\2\2y\20\3\2\2\2z{\7z\2\2{|\7d\2\2|}\7g\2\2}"+
		"~\7v\2\2~\22\3\2\2\2\177\u0080\7n\2\2\u0080\u0081\7g\2\2\u0081\u0082\7"+
		"p\2\2\u0082\u0083\7i\2\2\u0083\u0084\7v\2\2\u0084\u0085\7j\2\2\u0085\24"+
		"\3\2\2\2\u0086\u0087\7d\2\2\u0087\u0088\7g\2\2\u0088\u0089\7v\2\2\u0089"+
		"\u008a\7k\2\2\u008a\u008b\7v\2\2\u008b\u008c\7g\2\2\u008c\u008d\7o\2\2"+
		"\u008d\u008e\7u\2\2\u008e\26\3\2\2\2\u008f\u0090\7r\2\2\u0090\u0091\7"+
		"t\2\2\u0091\u0092\7q\2\2\u0092\30\3\2\2\2\u0093\u0094\7o\2\2\u0094\u0095"+
		"\7q\2\2\u0095\u0096\7h\2\2\u0096\32\3\2\2\2\u0097\u0098\7p\2\2\u0098\u0099"+
		"\7q\2\2\u0099\u009a\7v\2\2\u009a\u009b\7<\2\2\u009b\34\3\2\2\2\u009c\u009d"+
		"\7c\2\2\u009d\u009e\7n\2\2\u009e\u009f\7n\2\2\u009f\u00a0\7Q\2\2\u00a0"+
		"\u00a1\7h\2\2\u00a1\36\3\2\2\2\u00a2\u00a3\7]\2\2\u00a3 \3\2\2\2\u00a4"+
		"\u00a5\7_\2\2\u00a5\"\3\2\2\2\u00a6\u00a7\7q\2\2\u00a7\u00a8\7p\2\2\u00a8"+
		"\u00a9\7g\2\2\u00a9\u00aa\7Q\2\2\u00aa\u00ab\7h\2\2\u00ab$\3\2\2\2\u00ac"+
		"\u00ad\7c\2\2\u00ad\u00ae\7p\2\2\u00ae\u00af\7{\2\2\u00af\u00b0\7Q\2\2"+
		"\u00b0\u00b1\7h\2\2\u00b1&\3\2\2\2\u00b2\u00b3\7t\2\2\u00b3\u00b4\7g\2"+
		"\2\u00b4\u00b5\7s\2\2\u00b5(\3\2\2\2\u00b6\u00b7\7g\2\2\u00b7\u00b8\7"+
		"p\2\2\u00b8\u00b9\7w\2\2\u00b9\u00ba\7o\2\2\u00ba\u00bb\7]\2\2\u00bb*"+
		"\3\2\2\2\u00bc\u00bd\7k\2\2\u00bd\u00be\7h\2\2\u00be,\3\2\2\2\u00bf\u00c0"+
		"\7<\2\2\u00c0.\3\2\2\2\u00c1\u00c2\7v\2\2\u00c2\u00c3\7j\2\2\u00c3\u00c4"+
		"\7g\2\2\u00c4\u00c5\7p\2\2\u00c5\60\3\2\2\2\u00c6\u00c7\7g\2\2\u00c7\u00c8"+
		"\7n\2\2\u00c8\u00c9\7u\2\2\u00c9\u00ca\7g\2\2\u00ca\62\3\2\2\2\u00cb\u00cc"+
		"\7w\2\2\u00cc\u00cd\7p\2\2\u00cd\u00ce\7k\2\2\u00ce\u00cf\7s\2\2\u00cf"+
		"\u00d0\7w\2\2\u00d0\u00d1\7g\2\2\u00d1\u00d2\7K\2\2\u00d2\u00d3\7v\2\2"+
		"\u00d3\u00d4\7g\2\2\u00d4\u00d5\7o\2\2\u00d5\u00d6\7u\2\2\u00d6\64\3\2"+
		"\2\2\u00d7\u00d8\7r\2\2\u00d8\u00d9\7c\2\2\u00d9\u00da\7v\2\2\u00da\u00db"+
		"\7v\2\2\u00db\u00dc\7g\2\2\u00dc\u00dd\7t\2\2\u00dd\u00de\7p\2\2\u00de"+
		"\66\3\2\2\2\u00df\u00e0\7k\2\2\u00e0\u00e1\7v\2\2\u00e1\u00e2\7g\2\2\u00e2"+
		"\u00e3\7o\2\2\u00e3\u00e4\7u\2\2\u00e48\3\2\2\2\u00e5\u00e6\7=\2\2\u00e6"+
		":\3\2\2\2\u00e7\u00e8\7e\2\2\u00e8\u00e9\7q\2\2\u00e9\u00ea\7p\2\2\u00ea"+
		"\u00eb\7v\2\2\u00eb\u00ec\7c\2\2\u00ec\u00ed\7k\2\2\u00ed\u00ee\7p\2\2"+
		"\u00ee\u00ef\7u\2\2\u00ef<\3\2\2\2\u00f0\u00f1\7r\2\2\u00f1\u00f2\7t\2"+
		"\2\u00f2\u00f3\7q\2\2\u00f3\u00f4\7r\2\2\u00f4\u00f5\7g\2\2\u00f5\u00f6"+
		"\7t\2\2\u00f6\u00f7\7v\2\2\u00f7\u00f8\7k\2\2\u00f8\u00f9\7g\2\2\u00f9"+
		"\u00fa\7u\2\2\u00fa>\3\2\2\2\u00fb\u00fc\7<\2\2\u00fc\u00fd\7<\2\2\u00fd"+
		"@\3\2\2\2\u00fe\u00ff\7c\2\2\u00ff\u0100\7f\2\2\u0100\u0101\7f\2\2\u0101"+
		"\u0102\7r\2\2\u0102B\3\2\2\2\u0103\u0104\7~\2\2\u0104D\3\2\2\2\u0105\u0106"+
		"\7e\2\2\u0106\u0107\7q\2\2\u0107\u0108\7p\2\2\u0108\u0109\7u\2\2\u0109"+
		"\u010a\7v\2\2\u010aF\3\2\2\2\u010b\u010c\7f\2\2\u010c\u010d\7g\2\2\u010d"+
		"\u010e\7h\2\2\u010eH\3\2\2\2\u010f\u0110\7?\2\2\u0110J\3\2\2\2\u0111\u0112"+
		"\7t\2\2\u0112\u0113\7g\2\2\u0113\u0114\7h\2\2\u0114L\3\2\2\2\u0115\u0116"+
		"\7p\2\2\u0116\u0117\7c\2\2\u0117\u0118\7o\2\2\u0118\u0119\7g\2\2\u0119"+
		"\u011a\7u\2\2\u011aN\3\2\2\2\u011b\u011c\7c\2\2\u011c\u011d\7p\2\2\u011d"+
		"\u011e\7p\2\2\u011e\u011f\7q\2\2\u011f\u0120\7v\2\2\u0120\u0121\7c\2\2"+
		"\u0121\u0122\7v\2\2\u0122\u0123\7k\2\2\u0123\u0124\7q\2\2\u0124\u0125"+
		"\7p\2\2\u0125\u0126\7u\2\2\u0126P\3\2\2\2\u0127\u0128\7p\2\2\u0128\u0129"+
		"\7w\2\2\u0129\u012a\7n\2\2\u012a\u012b\7n\2\2\u012bR\3\2\2\2\u012c\u012d"+
		"\7q\2\2\u012d\u012e\7d\2\2\u012e\u0140\7l\2\2\u012f\u0130\7u\2\2\u0130"+
		"\u0131\7v\2\2\u0131\u0140\7t\2\2\u0132\u0133\7p\2\2\u0133\u0134\7w\2\2"+
		"\u0134\u0140\7o\2\2\u0135\u0136\7k\2\2\u0136\u0137\7p\2\2\u0137\u0140"+
		"\7v\2\2\u0138\u0139\7c\2\2\u0139\u013a\7t\2\2\u013a\u0140\7t\2\2\u013b"+
		"\u013c\7d\2\2\u013c\u013d\7q\2\2\u013d\u013e\7q\2\2\u013e\u0140\7n\2\2"+
		"\u013f\u012c\3\2\2\2\u013f\u012f\3\2\2\2\u013f\u0132\3\2\2\2\u013f\u0135"+
		"\3\2\2\2\u013f\u0138\3\2\2\2\u013f\u013b\3\2\2\2\u0140T\3\2\2\2\u0141"+
		"\u0143\7/\2\2\u0142\u0141\3\2\2\2\u0142\u0143\3\2\2\2\u0143\u0145\3\2"+
		"\2\2\u0144\u0146\t\2\2\2\u0145\u0144\3\2\2\2\u0146\u0147\3\2\2\2\u0147"+
		"\u0145\3\2\2\2\u0147\u0148\3\2\2\2\u0148V\3\2\2\2\u0149\u014b\7/\2\2\u014a"+
		"\u0149\3\2\2\2\u014a\u014b\3\2\2\2\u014b\u014d\3\2\2\2\u014c\u014e\t\2"+
		"\2\2\u014d\u014c\3\2\2\2\u014e\u014f\3\2\2\2\u014f\u014d\3\2\2\2\u014f"+
		"\u0150\3\2\2\2\u0150\u0151\3\2\2\2\u0151\u0153\7\60\2\2\u0152\u0154\t"+
		"\3\2\2\u0153\u0152\3\2\2\2\u0154\u0155\3\2\2\2\u0155\u0153\3\2\2\2\u0155"+
		"\u0156\3\2\2\2\u0156X\3\2\2\2\u0157\u0159\t\4\2\2\u0158\u0157\3\2\2\2"+
		"\u0159\u015a\3\2\2\2\u015a\u0158\3\2\2\2\u015a\u015b\3\2\2\2\u015b\u015c"+
		"\3\2\2\2\u015c\u015d\b-\2\2\u015dZ\3\2\2\2\u015e\u0162\7$\2\2\u015f\u0161"+
		"\13\2\2\2\u0160\u015f\3\2\2\2\u0161\u0164\3\2\2\2\u0162\u0163\3\2\2\2"+
		"\u0162\u0160\3\2\2\2\u0163\u0165\3\2\2\2\u0164\u0162\3\2\2\2\u0165\u0166"+
		"\7$\2\2\u0166\\\3\2\2\2\u0167\u0168\7v\2\2\u0168\u0169\7t\2\2\u0169\u016a"+
		"\7w\2\2\u016a\u0171\7g\2\2\u016b\u016c\7h\2\2\u016c\u016d\7c\2\2\u016d"+
		"\u016e\7n\2\2\u016e\u016f\7u\2\2\u016f\u0171\7g\2\2\u0170\u0167\3\2\2"+
		"\2\u0170\u016b\3\2\2\2\u0171^\3\2\2\2\u0172\u0175\7^\2\2\u0173\u0176\t"+
		"\5\2\2\u0174\u0176\5a\61\2\u0175\u0173\3\2\2\2\u0175\u0174\3\2\2\2\u0176"+
		"`\3\2\2\2\u0177\u0178\7w\2\2\u0178\u0179\5c\62\2\u0179\u017a\5c\62\2\u017a"+
		"\u017b\5c\62\2\u017b\u017c\5c\62\2\u017c\u0182\3\2\2\2\u017d\u017e\7z"+
		"\2\2\u017e\u017f\5c\62\2\u017f\u0180\5c\62\2\u0180\u0182\3\2\2\2\u0181"+
		"\u0177\3\2\2\2\u0181\u017d\3\2\2\2\u0182b\3\2\2\2\u0183\u0184\t\6\2\2"+
		"\u0184d\3\2\2\2\u0185\u0186\n\7\2\2\u0186f\3\2\2\2\16\2\u013f\u0142\u0147"+
		"\u014a\u014f\u0155\u015a\u0162\u0170\u0175\u0181\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}