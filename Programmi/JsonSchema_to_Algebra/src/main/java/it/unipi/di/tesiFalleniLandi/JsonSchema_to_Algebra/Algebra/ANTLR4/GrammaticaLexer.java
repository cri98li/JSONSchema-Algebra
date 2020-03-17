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
		T__9=10, T__10=11, T__11=12, T__12=13, NULL=14, INT=15, ID=16, WS=17, 
		STRING=18;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "NULL", "INT", "ID", "WS", "STRING", 
			"ESC", "UNICODE", "HEX", "SAFECODEPOINT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'{'", "','", "'}'", "'Obj'", "'Null'", "'Str'", "'Num'", "'Int'", 
			"'Arr'", "'bet<'", "'>'", "'_NOT('", "')'", "'null'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, "NULL", "INT", "ID", "WS", "STRING"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\24\u008c\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\3\2\3\2\3\3\3"+
		"\3\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b"+
		"\3\b\3\b\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f"+
		"\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\20\6"+
		"\20d\n\20\r\20\16\20e\3\21\6\21i\n\21\r\21\16\21j\3\22\6\22n\n\22\r\22"+
		"\16\22o\3\22\3\22\3\23\3\23\3\23\7\23w\n\23\f\23\16\23z\13\23\3\23\3\23"+
		"\3\24\3\24\3\24\5\24\u0081\n\24\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26"+
		"\3\27\3\27\2\2\30\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31"+
		"\16\33\17\35\20\37\21!\22#\23%\24\'\2)\2+\2-\2\3\2\b\3\2\62;\6\2\62;C"+
		"\\aac|\5\2\13\f\17\17\"\"\n\2$$\61\61^^ddhhppttvv\5\2\62;CHch\5\2\2!$"+
		"$^^\2\u008d\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2"+
		"\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27"+
		"\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2"+
		"\2\2#\3\2\2\2\2%\3\2\2\2\3/\3\2\2\2\5\61\3\2\2\2\7\63\3\2\2\2\t\65\3\2"+
		"\2\2\139\3\2\2\2\r>\3\2\2\2\17B\3\2\2\2\21F\3\2\2\2\23J\3\2\2\2\25N\3"+
		"\2\2\2\27S\3\2\2\2\31U\3\2\2\2\33[\3\2\2\2\35]\3\2\2\2\37c\3\2\2\2!h\3"+
		"\2\2\2#m\3\2\2\2%s\3\2\2\2\'}\3\2\2\2)\u0082\3\2\2\2+\u0088\3\2\2\2-\u008a"+
		"\3\2\2\2/\60\7}\2\2\60\4\3\2\2\2\61\62\7.\2\2\62\6\3\2\2\2\63\64\7\177"+
		"\2\2\64\b\3\2\2\2\65\66\7Q\2\2\66\67\7d\2\2\678\7l\2\28\n\3\2\2\29:\7"+
		"P\2\2:;\7w\2\2;<\7n\2\2<=\7n\2\2=\f\3\2\2\2>?\7U\2\2?@\7v\2\2@A\7t\2\2"+
		"A\16\3\2\2\2BC\7P\2\2CD\7w\2\2DE\7o\2\2E\20\3\2\2\2FG\7K\2\2GH\7p\2\2"+
		"HI\7v\2\2I\22\3\2\2\2JK\7C\2\2KL\7t\2\2LM\7t\2\2M\24\3\2\2\2NO\7d\2\2"+
		"OP\7g\2\2PQ\7v\2\2QR\7>\2\2R\26\3\2\2\2ST\7@\2\2T\30\3\2\2\2UV\7a\2\2"+
		"VW\7P\2\2WX\7Q\2\2XY\7V\2\2YZ\7*\2\2Z\32\3\2\2\2[\\\7+\2\2\\\34\3\2\2"+
		"\2]^\7p\2\2^_\7w\2\2_`\7n\2\2`a\7n\2\2a\36\3\2\2\2bd\t\2\2\2cb\3\2\2\2"+
		"de\3\2\2\2ec\3\2\2\2ef\3\2\2\2f \3\2\2\2gi\t\3\2\2hg\3\2\2\2ij\3\2\2\2"+
		"jh\3\2\2\2jk\3\2\2\2k\"\3\2\2\2ln\t\4\2\2ml\3\2\2\2no\3\2\2\2om\3\2\2"+
		"\2op\3\2\2\2pq\3\2\2\2qr\b\22\2\2r$\3\2\2\2sx\7$\2\2tw\5\'\24\2uw\5-\27"+
		"\2vt\3\2\2\2vu\3\2\2\2wz\3\2\2\2xv\3\2\2\2xy\3\2\2\2y{\3\2\2\2zx\3\2\2"+
		"\2{|\7$\2\2|&\3\2\2\2}\u0080\7^\2\2~\u0081\t\5\2\2\177\u0081\5)\25\2\u0080"+
		"~\3\2\2\2\u0080\177\3\2\2\2\u0081(\3\2\2\2\u0082\u0083\7w\2\2\u0083\u0084"+
		"\5+\26\2\u0084\u0085\5+\26\2\u0085\u0086\5+\26\2\u0086\u0087\5+\26\2\u0087"+
		"*\3\2\2\2\u0088\u0089\t\6\2\2\u0089,\3\2\2\2\u008a\u008b\n\7\2\2\u008b"+
		".\3\2\2\2\t\2ejovx\u0080\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}