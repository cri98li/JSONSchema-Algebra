package it.unipi.di.tesiFalleniLandi.antlr4;

// Generated from Grammatica.g4 by ANTLR 4.7.2
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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, INT=6, ID=7, WS=8, STRING=9;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "INT", "ID", "WS", "STRING", 
			"ESC", "UNICODE", "HEX", "SAFECODEPOINT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'{'", "','", "'}'", "'bet<'", "'>'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, "INT", "ID", "WS", "STRING"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\13T\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5"+
		"\3\5\3\6\3\6\3\7\6\7,\n\7\r\7\16\7-\3\b\6\b\61\n\b\r\b\16\b\62\3\t\6\t"+
		"\66\n\t\r\t\16\t\67\3\t\3\t\3\n\3\n\3\n\7\n?\n\n\f\n\16\nB\13\n\3\n\3"+
		"\n\3\13\3\13\3\13\5\13I\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\16\3\16"+
		"\2\2\17\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\2\27\2\31\2\33\2\3"+
		"\2\b\3\2\62;\4\2C\\c|\5\2\13\f\17\17\"\"\n\2$$\61\61^^ddhhppttvv\5\2\62"+
		";CHch\5\2\2!$$^^\2U\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2"+
		"\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\3\35\3"+
		"\2\2\2\5\37\3\2\2\2\7!\3\2\2\2\t#\3\2\2\2\13(\3\2\2\2\r+\3\2\2\2\17\60"+
		"\3\2\2\2\21\65\3\2\2\2\23;\3\2\2\2\25E\3\2\2\2\27J\3\2\2\2\31P\3\2\2\2"+
		"\33R\3\2\2\2\35\36\7}\2\2\36\4\3\2\2\2\37 \7.\2\2 \6\3\2\2\2!\"\7\177"+
		"\2\2\"\b\3\2\2\2#$\7d\2\2$%\7g\2\2%&\7v\2\2&\'\7>\2\2\'\n\3\2\2\2()\7"+
		"@\2\2)\f\3\2\2\2*,\t\2\2\2+*\3\2\2\2,-\3\2\2\2-+\3\2\2\2-.\3\2\2\2.\16"+
		"\3\2\2\2/\61\t\3\2\2\60/\3\2\2\2\61\62\3\2\2\2\62\60\3\2\2\2\62\63\3\2"+
		"\2\2\63\20\3\2\2\2\64\66\t\4\2\2\65\64\3\2\2\2\66\67\3\2\2\2\67\65\3\2"+
		"\2\2\678\3\2\2\289\3\2\2\29:\b\t\2\2:\22\3\2\2\2;@\7$\2\2<?\5\25\13\2"+
		"=?\5\33\16\2><\3\2\2\2>=\3\2\2\2?B\3\2\2\2@>\3\2\2\2@A\3\2\2\2AC\3\2\2"+
		"\2B@\3\2\2\2CD\7$\2\2D\24\3\2\2\2EH\7^\2\2FI\t\5\2\2GI\5\27\f\2HF\3\2"+
		"\2\2HG\3\2\2\2I\26\3\2\2\2JK\7w\2\2KL\5\31\r\2LM\5\31\r\2MN\5\31\r\2N"+
		"O\5\31\r\2O\30\3\2\2\2PQ\t\6\2\2Q\32\3\2\2\2RS\n\7\2\2S\34\3\2\2\2\t\2"+
		"-\62\67>@H\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}