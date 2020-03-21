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
		T__24=25, T__25=26, T__26=27, T__27=28, TYPE=29, NULL=30, INT=31, WS=32, 
		STRING=33;
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
			"T__25", "T__26", "T__27", "TYPE", "NULL", "INT", "WS", "STRING", "ESC", 
			"UNICODE", "HEX", "SAFECODEPOINT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "','", "'type('", "')'", "'bet<'", "'>'", "'xbet<'", "'length<'", 
			"'betitems<'", "'pro<'", "'mof<'", "'_NOT('", "'_AND('", "'_XOR('", "'_OR('", 
			"'req(['", "'])'", "'enum('", "'('", "'=>'", "'|'", "'uniqueItems'", 
			"'pattern('", "'items(;'", "'items('", "'*'", "';'", "'contains<'", "'const('", 
			null, "'null'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, "TYPE", "NULL", "INT", "WS", "STRING"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2#\u0129\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3"+
		"\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31"+
		"\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34"+
		"\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36"+
		"\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36"+
		"\3\36\3\36\3\36\3\36\5\36\u00fe\n\36\3\37\3\37\3\37\3\37\3\37\3 \6 \u0106"+
		"\n \r \16 \u0107\3!\6!\u010b\n!\r!\16!\u010c\3!\3!\3\"\3\"\3\"\7\"\u0114"+
		"\n\"\f\"\16\"\u0117\13\"\3\"\3\"\3#\3#\3#\5#\u011e\n#\3$\3$\3$\3$\3$\3"+
		"$\3%\3%\3&\3&\2\2\'\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r"+
		"\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33"+
		"\65\34\67\359\36;\37= ?!A\"C#E\2G\2I\2K\2\3\2\b\3\2\62;\5\2\13\f\17\17"+
		"\"\"\4\2$$^^\n\2$$\61\61^^ddhhppttvv\5\2\62;CHch\5\2\2!$$^^\2\u012f\2"+
		"\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2"+
		"\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2"+
		"\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2"+
		"\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2"+
		"\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2"+
		"\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\3M\3\2\2\2\5O\3\2\2\2"+
		"\7U\3\2\2\2\tW\3\2\2\2\13\\\3\2\2\2\r^\3\2\2\2\17d\3\2\2\2\21l\3\2\2\2"+
		"\23v\3\2\2\2\25{\3\2\2\2\27\u0080\3\2\2\2\31\u0086\3\2\2\2\33\u008c\3"+
		"\2\2\2\35\u0092\3\2\2\2\37\u0097\3\2\2\2!\u009d\3\2\2\2#\u00a0\3\2\2\2"+
		"%\u00a6\3\2\2\2\'\u00a8\3\2\2\2)\u00ab\3\2\2\2+\u00ad\3\2\2\2-\u00b9\3"+
		"\2\2\2/\u00c2\3\2\2\2\61\u00ca\3\2\2\2\63\u00d1\3\2\2\2\65\u00d3\3\2\2"+
		"\2\67\u00d5\3\2\2\29\u00df\3\2\2\2;\u00fd\3\2\2\2=\u00ff\3\2\2\2?\u0105"+
		"\3\2\2\2A\u010a\3\2\2\2C\u0110\3\2\2\2E\u011a\3\2\2\2G\u011f\3\2\2\2I"+
		"\u0125\3\2\2\2K\u0127\3\2\2\2MN\7.\2\2N\4\3\2\2\2OP\7v\2\2PQ\7{\2\2QR"+
		"\7r\2\2RS\7g\2\2ST\7*\2\2T\6\3\2\2\2UV\7+\2\2V\b\3\2\2\2WX\7d\2\2XY\7"+
		"g\2\2YZ\7v\2\2Z[\7>\2\2[\n\3\2\2\2\\]\7@\2\2]\f\3\2\2\2^_\7z\2\2_`\7d"+
		"\2\2`a\7g\2\2ab\7v\2\2bc\7>\2\2c\16\3\2\2\2de\7n\2\2ef\7g\2\2fg\7p\2\2"+
		"gh\7i\2\2hi\7v\2\2ij\7j\2\2jk\7>\2\2k\20\3\2\2\2lm\7d\2\2mn\7g\2\2no\7"+
		"v\2\2op\7k\2\2pq\7v\2\2qr\7g\2\2rs\7o\2\2st\7u\2\2tu\7>\2\2u\22\3\2\2"+
		"\2vw\7r\2\2wx\7t\2\2xy\7q\2\2yz\7>\2\2z\24\3\2\2\2{|\7o\2\2|}\7q\2\2}"+
		"~\7h\2\2~\177\7>\2\2\177\26\3\2\2\2\u0080\u0081\7a\2\2\u0081\u0082\7P"+
		"\2\2\u0082\u0083\7Q\2\2\u0083\u0084\7V\2\2\u0084\u0085\7*\2\2\u0085\30"+
		"\3\2\2\2\u0086\u0087\7a\2\2\u0087\u0088\7C\2\2\u0088\u0089\7P\2\2\u0089"+
		"\u008a\7F\2\2\u008a\u008b\7*\2\2\u008b\32\3\2\2\2\u008c\u008d\7a\2\2\u008d"+
		"\u008e\7Z\2\2\u008e\u008f\7Q\2\2\u008f\u0090\7T\2\2\u0090\u0091\7*\2\2"+
		"\u0091\34\3\2\2\2\u0092\u0093\7a\2\2\u0093\u0094\7Q\2\2\u0094\u0095\7"+
		"T\2\2\u0095\u0096\7*\2\2\u0096\36\3\2\2\2\u0097\u0098\7t\2\2\u0098\u0099"+
		"\7g\2\2\u0099\u009a\7s\2\2\u009a\u009b\7*\2\2\u009b\u009c\7]\2\2\u009c"+
		" \3\2\2\2\u009d\u009e\7_\2\2\u009e\u009f\7+\2\2\u009f\"\3\2\2\2\u00a0"+
		"\u00a1\7g\2\2\u00a1\u00a2\7p\2\2\u00a2\u00a3\7w\2\2\u00a3\u00a4\7o\2\2"+
		"\u00a4\u00a5\7*\2\2\u00a5$\3\2\2\2\u00a6\u00a7\7*\2\2\u00a7&\3\2\2\2\u00a8"+
		"\u00a9\7?\2\2\u00a9\u00aa\7@\2\2\u00aa(\3\2\2\2\u00ab\u00ac\7~\2\2\u00ac"+
		"*\3\2\2\2\u00ad\u00ae\7w\2\2\u00ae\u00af\7p\2\2\u00af\u00b0\7k\2\2\u00b0"+
		"\u00b1\7s\2\2\u00b1\u00b2\7w\2\2\u00b2\u00b3\7g\2\2\u00b3\u00b4\7K\2\2"+
		"\u00b4\u00b5\7v\2\2\u00b5\u00b6\7g\2\2\u00b6\u00b7\7o\2\2\u00b7\u00b8"+
		"\7u\2\2\u00b8,\3\2\2\2\u00b9\u00ba\7r\2\2\u00ba\u00bb\7c\2\2\u00bb\u00bc"+
		"\7v\2\2\u00bc\u00bd\7v\2\2\u00bd\u00be\7g\2\2\u00be\u00bf\7t\2\2\u00bf"+
		"\u00c0\7p\2\2\u00c0\u00c1\7*\2\2\u00c1.\3\2\2\2\u00c2\u00c3\7k\2\2\u00c3"+
		"\u00c4\7v\2\2\u00c4\u00c5\7g\2\2\u00c5\u00c6\7o\2\2\u00c6\u00c7\7u\2\2"+
		"\u00c7\u00c8\7*\2\2\u00c8\u00c9\7=\2\2\u00c9\60\3\2\2\2\u00ca\u00cb\7"+
		"k\2\2\u00cb\u00cc\7v\2\2\u00cc\u00cd\7g\2\2\u00cd\u00ce\7o\2\2\u00ce\u00cf"+
		"\7u\2\2\u00cf\u00d0\7*\2\2\u00d0\62\3\2\2\2\u00d1\u00d2\7,\2\2\u00d2\64"+
		"\3\2\2\2\u00d3\u00d4\7=\2\2\u00d4\66\3\2\2\2\u00d5\u00d6\7e\2\2\u00d6"+
		"\u00d7\7q\2\2\u00d7\u00d8\7p\2\2\u00d8\u00d9\7v\2\2\u00d9\u00da\7c\2\2"+
		"\u00da\u00db\7k\2\2\u00db\u00dc\7p\2\2\u00dc\u00dd\7u\2\2\u00dd\u00de"+
		"\7>\2\2\u00de8\3\2\2\2\u00df\u00e0\7e\2\2\u00e0\u00e1\7q\2\2\u00e1\u00e2"+
		"\7p\2\2\u00e2\u00e3\7u\2\2\u00e3\u00e4\7v\2\2\u00e4\u00e5\7*\2\2\u00e5"+
		":\3\2\2\2\u00e6\u00e7\7Q\2\2\u00e7\u00e8\7d\2\2\u00e8\u00fe\7l\2\2\u00e9"+
		"\u00ea\7P\2\2\u00ea\u00eb\7w\2\2\u00eb\u00ec\7n\2\2\u00ec\u00fe\7n\2\2"+
		"\u00ed\u00ee\7U\2\2\u00ee\u00ef\7v\2\2\u00ef\u00fe\7t\2\2\u00f0\u00f1"+
		"\7P\2\2\u00f1\u00f2\7w\2\2\u00f2\u00fe\7o\2\2\u00f3\u00f4\7K\2\2\u00f4"+
		"\u00f5\7p\2\2\u00f5\u00fe\7v\2\2\u00f6\u00f7\7C\2\2\u00f7\u00f8\7t\2\2"+
		"\u00f8\u00fe\7t\2\2\u00f9\u00fa\7D\2\2\u00fa\u00fb\7q\2\2\u00fb\u00fc"+
		"\7q\2\2\u00fc\u00fe\7n\2\2\u00fd\u00e6\3\2\2\2\u00fd\u00e9\3\2\2\2\u00fd"+
		"\u00ed\3\2\2\2\u00fd\u00f0\3\2\2\2\u00fd\u00f3\3\2\2\2\u00fd\u00f6\3\2"+
		"\2\2\u00fd\u00f9\3\2\2\2\u00fe<\3\2\2\2\u00ff\u0100\7p\2\2\u0100\u0101"+
		"\7w\2\2\u0101\u0102\7n\2\2\u0102\u0103\7n\2\2\u0103>\3\2\2\2\u0104\u0106"+
		"\t\2\2\2\u0105\u0104\3\2\2\2\u0106\u0107\3\2\2\2\u0107\u0105\3\2\2\2\u0107"+
		"\u0108\3\2\2\2\u0108@\3\2\2\2\u0109\u010b\t\3\2\2\u010a\u0109\3\2\2\2"+
		"\u010b\u010c\3\2\2\2\u010c\u010a\3\2\2\2\u010c\u010d\3\2\2\2\u010d\u010e"+
		"\3\2\2\2\u010e\u010f\b!\2\2\u010fB\3\2\2\2\u0110\u0115\7$\2\2\u0111\u0114"+
		"\5E#\2\u0112\u0114\n\4\2\2\u0113\u0111\3\2\2\2\u0113\u0112\3\2\2\2\u0114"+
		"\u0117\3\2\2\2\u0115\u0113\3\2\2\2\u0115\u0116\3\2\2\2\u0116\u0118\3\2"+
		"\2\2\u0117\u0115\3\2\2\2\u0118\u0119\7$\2\2\u0119D\3\2\2\2\u011a\u011d"+
		"\7^\2\2\u011b\u011e\t\5\2\2\u011c\u011e\5G$\2\u011d\u011b\3\2\2\2\u011d"+
		"\u011c\3\2\2\2\u011eF\3\2\2\2\u011f\u0120\7w\2\2\u0120\u0121\5I%\2\u0121"+
		"\u0122\5I%\2\u0122\u0123\5I%\2\u0123\u0124\5I%\2\u0124H\3\2\2\2\u0125"+
		"\u0126\t\6\2\2\u0126J\3\2\2\2\u0127\u0128\n\7\2\2\u0128L\3\2\2\2\t\2\u00fd"+
		"\u0107\u010c\u0113\u0115\u011d\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}