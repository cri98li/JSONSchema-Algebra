// Generated from Grammatica.g4 by ANTLR 4.7.2
package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.ANTLR4;

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
		T__38=39, T__39=40, T__40=41, T__41=42, T__42=43, T__43=44, T__44=45, 
		T__45=46, T__46=47, T__47=48, T__48=49, DEF_FIRMA=50, NULL=51, TYPE=52, 
		INT=53, DOUBLE=54, WS=55, STRING=56, BOOLEAN=57, COMMENT=58, LINE_COMMENT=59;
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
			"T__33", "T__34", "T__35", "T__36", "T__37", "T__38", "T__39", "T__40", 
			"T__41", "T__42", "T__43", "T__44", "T__45", "T__46", "T__47", "T__48", 
			"DEF_FIRMA", "NULL", "TYPE", "INT", "DOUBLE", "WS", "STRING", "BOOLEAN", 
			"ESC", "UNICODE", "HEX", "SAFECODEPOINT", "COMMENT", "LINE_COMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'{'", "','", "'}'", "'type'", "'['", "']'", "'bet'", "'('", "')'", 
			"'xbet'", "'length'", "'betItems'", "'pro'", "'mof'", "'notMof'", "'not'", 
			"'allOf'", "'oneOf'", "'anyOf'", "'req'", "'enum['", "'ifThenElse'", 
			"';'", "'ifThen'", "'uniqueItems'", "'repeatedItems'", "'pAllOf'", "'pAnyOf'", 
			"'pNot'", "'pattern'", "'notPattern'", "'items'", "'contains'", "'props'", 
			"':'", "'const'", "'def'", "'rootdef'", "'='", "'ref'", "'names'", "'exNames'", 
			"'annotations'", "'pattReq'", "'addPattReq'", "'ifBoolThen'", "'+'", 
			"'-'", "'inf'", null, "'null'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, "DEF_FIRMA", "NULL", "TYPE", "INT", "DOUBLE", "WS", "STRING", 
			"BOOLEAN", "COMMENT", "LINE_COMMENT"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2=\u022f\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6"+
		"\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16"+
		"\3\16\3\16\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21"+
		"\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32"+
		"\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33"+
		"\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34"+
		"\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\37\3\37"+
		"\3\37\3\37\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3!\3!"+
		"\3!\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3#\3$"+
		"\3$\3%\3%\3%\3%\3%\3%\3&\3&\3&\3&\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3(\3"+
		"(\3)\3)\3)\3)\3*\3*\3*\3*\3*\3*\3+\3+\3+\3+\3+\3+\3+\3+\3,\3,\3,\3,\3"+
		",\3,\3,\3,\3,\3,\3,\3,\3-\3-\3-\3-\3-\3-\3-\3-\3.\3.\3.\3.\3.\3.\3.\3"+
		".\3.\3.\3.\3/\3/\3/\3/\3/\3/\3/\3/\3/\3/\3/\3\60\3\60\3\61\3\61\3\62\3"+
		"\62\3\62\3\62\3\63\6\63\u01a1\n\63\r\63\16\63\u01a2\3\63\3\63\3\64\3\64"+
		"\3\64\3\64\3\64\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\65"+
		"\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\65"+
		"\3\65\3\65\3\65\5\65\u01c8\n\65\3\66\5\66\u01cb\n\66\3\66\6\66\u01ce\n"+
		"\66\r\66\16\66\u01cf\3\67\5\67\u01d3\n\67\3\67\6\67\u01d6\n\67\r\67\16"+
		"\67\u01d7\3\67\3\67\6\67\u01dc\n\67\r\67\16\67\u01dd\38\68\u01e1\n8\r"+
		"8\168\u01e2\38\38\39\39\39\79\u01ea\n9\f9\169\u01ed\139\39\39\3:\3:\3"+
		":\3:\3:\3:\3:\3:\3:\3:\3:\3:\3:\3:\3:\5:\u0200\n:\3;\3;\3;\5;\u0205\n"+
		";\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\5<\u0211\n<\3=\3=\3>\3>\3?\3?\3?\3?\7"+
		"?\u021b\n?\f?\16?\u021e\13?\3?\3?\3?\3?\3?\3@\3@\3@\3@\7@\u0229\n@\f@"+
		"\16@\u022c\13@\3@\3@\3\u021c\2A\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23"+
		"\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31"+
		"\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60"+
		"_\61a\62c\63e\64g\65i\66k\67m8o9q:s;u\2w\2y\2{\2}<\177=\3\2\n\3\2\62;"+
		"\4\2\62;GG\5\2\13\f\17\17\"\"\6\2\f\f\17\17$$^^\n\2$$\61\61^^ddhhpptt"+
		"vv\5\2\62;CHch\5\2\2!$$^^\4\2\f\f\17\17\2\u0242\2\3\3\2\2\2\2\5\3\2\2"+
		"\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3"+
		"\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3"+
		"\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3"+
		"\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2"+
		"\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2"+
		"Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3"+
		"\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2"+
		"\2\2s\3\2\2\2\2}\3\2\2\2\2\177\3\2\2\2\3\u0081\3\2\2\2\5\u0083\3\2\2\2"+
		"\7\u0085\3\2\2\2\t\u0087\3\2\2\2\13\u008c\3\2\2\2\r\u008e\3\2\2\2\17\u0090"+
		"\3\2\2\2\21\u0094\3\2\2\2\23\u0096\3\2\2\2\25\u0098\3\2\2\2\27\u009d\3"+
		"\2\2\2\31\u00a4\3\2\2\2\33\u00ad\3\2\2\2\35\u00b1\3\2\2\2\37\u00b5\3\2"+
		"\2\2!\u00bc\3\2\2\2#\u00c0\3\2\2\2%\u00c6\3\2\2\2\'\u00cc\3\2\2\2)\u00d2"+
		"\3\2\2\2+\u00d6\3\2\2\2-\u00dc\3\2\2\2/\u00e7\3\2\2\2\61\u00e9\3\2\2\2"+
		"\63\u00f0\3\2\2\2\65\u00fc\3\2\2\2\67\u010a\3\2\2\29\u0111\3\2\2\2;\u0118"+
		"\3\2\2\2=\u011d\3\2\2\2?\u0125\3\2\2\2A\u0130\3\2\2\2C\u0136\3\2\2\2E"+
		"\u013f\3\2\2\2G\u0145\3\2\2\2I\u0147\3\2\2\2K\u014d\3\2\2\2M\u0151\3\2"+
		"\2\2O\u0159\3\2\2\2Q\u015b\3\2\2\2S\u015f\3\2\2\2U\u0165\3\2\2\2W\u016d"+
		"\3\2\2\2Y\u0179\3\2\2\2[\u0181\3\2\2\2]\u018c\3\2\2\2_\u0197\3\2\2\2a"+
		"\u0199\3\2\2\2c\u019b\3\2\2\2e\u01a0\3\2\2\2g\u01a6\3\2\2\2i\u01c7\3\2"+
		"\2\2k\u01ca\3\2\2\2m\u01d2\3\2\2\2o\u01e0\3\2\2\2q\u01e6\3\2\2\2s\u01ff"+
		"\3\2\2\2u\u0201\3\2\2\2w\u0210\3\2\2\2y\u0212\3\2\2\2{\u0214\3\2\2\2}"+
		"\u0216\3\2\2\2\177\u0224\3\2\2\2\u0081\u0082\7}\2\2\u0082\4\3\2\2\2\u0083"+
		"\u0084\7.\2\2\u0084\6\3\2\2\2\u0085\u0086\7\177\2\2\u0086\b\3\2\2\2\u0087"+
		"\u0088\7v\2\2\u0088\u0089\7{\2\2\u0089\u008a\7r\2\2\u008a\u008b\7g\2\2"+
		"\u008b\n\3\2\2\2\u008c\u008d\7]\2\2\u008d\f\3\2\2\2\u008e\u008f\7_\2\2"+
		"\u008f\16\3\2\2\2\u0090\u0091\7d\2\2\u0091\u0092\7g\2\2\u0092\u0093\7"+
		"v\2\2\u0093\20\3\2\2\2\u0094\u0095\7*\2\2\u0095\22\3\2\2\2\u0096\u0097"+
		"\7+\2\2\u0097\24\3\2\2\2\u0098\u0099\7z\2\2\u0099\u009a\7d\2\2\u009a\u009b"+
		"\7g\2\2\u009b\u009c\7v\2\2\u009c\26\3\2\2\2\u009d\u009e\7n\2\2\u009e\u009f"+
		"\7g\2\2\u009f\u00a0\7p\2\2\u00a0\u00a1\7i\2\2\u00a1\u00a2\7v\2\2\u00a2"+
		"\u00a3\7j\2\2\u00a3\30\3\2\2\2\u00a4\u00a5\7d\2\2\u00a5\u00a6\7g\2\2\u00a6"+
		"\u00a7\7v\2\2\u00a7\u00a8\7K\2\2\u00a8\u00a9\7v\2\2\u00a9\u00aa\7g\2\2"+
		"\u00aa\u00ab\7o\2\2\u00ab\u00ac\7u\2\2\u00ac\32\3\2\2\2\u00ad\u00ae\7"+
		"r\2\2\u00ae\u00af\7t\2\2\u00af\u00b0\7q\2\2\u00b0\34\3\2\2\2\u00b1\u00b2"+
		"\7o\2\2\u00b2\u00b3\7q\2\2\u00b3\u00b4\7h\2\2\u00b4\36\3\2\2\2\u00b5\u00b6"+
		"\7p\2\2\u00b6\u00b7\7q\2\2\u00b7\u00b8\7v\2\2\u00b8\u00b9\7O\2\2\u00b9"+
		"\u00ba\7q\2\2\u00ba\u00bb\7h\2\2\u00bb \3\2\2\2\u00bc\u00bd\7p\2\2\u00bd"+
		"\u00be\7q\2\2\u00be\u00bf\7v\2\2\u00bf\"\3\2\2\2\u00c0\u00c1\7c\2\2\u00c1"+
		"\u00c2\7n\2\2\u00c2\u00c3\7n\2\2\u00c3\u00c4\7Q\2\2\u00c4\u00c5\7h\2\2"+
		"\u00c5$\3\2\2\2\u00c6\u00c7\7q\2\2\u00c7\u00c8\7p\2\2\u00c8\u00c9\7g\2"+
		"\2\u00c9\u00ca\7Q\2\2\u00ca\u00cb\7h\2\2\u00cb&\3\2\2\2\u00cc\u00cd\7"+
		"c\2\2\u00cd\u00ce\7p\2\2\u00ce\u00cf\7{\2\2\u00cf\u00d0\7Q\2\2\u00d0\u00d1"+
		"\7h\2\2\u00d1(\3\2\2\2\u00d2\u00d3\7t\2\2\u00d3\u00d4\7g\2\2\u00d4\u00d5"+
		"\7s\2\2\u00d5*\3\2\2\2\u00d6\u00d7\7g\2\2\u00d7\u00d8\7p\2\2\u00d8\u00d9"+
		"\7w\2\2\u00d9\u00da\7o\2\2\u00da\u00db\7]\2\2\u00db,\3\2\2\2\u00dc\u00dd"+
		"\7k\2\2\u00dd\u00de\7h\2\2\u00de\u00df\7V\2\2\u00df\u00e0\7j\2\2\u00e0"+
		"\u00e1\7g\2\2\u00e1\u00e2\7p\2\2\u00e2\u00e3\7G\2\2\u00e3\u00e4\7n\2\2"+
		"\u00e4\u00e5\7u\2\2\u00e5\u00e6\7g\2\2\u00e6.\3\2\2\2\u00e7\u00e8\7=\2"+
		"\2\u00e8\60\3\2\2\2\u00e9\u00ea\7k\2\2\u00ea\u00eb\7h\2\2\u00eb\u00ec"+
		"\7V\2\2\u00ec\u00ed\7j\2\2\u00ed\u00ee\7g\2\2\u00ee\u00ef\7p\2\2\u00ef"+
		"\62\3\2\2\2\u00f0\u00f1\7w\2\2\u00f1\u00f2\7p\2\2\u00f2\u00f3\7k\2\2\u00f3"+
		"\u00f4\7s\2\2\u00f4\u00f5\7w\2\2\u00f5\u00f6\7g\2\2\u00f6\u00f7\7K\2\2"+
		"\u00f7\u00f8\7v\2\2\u00f8\u00f9\7g\2\2\u00f9\u00fa\7o\2\2\u00fa\u00fb"+
		"\7u\2\2\u00fb\64\3\2\2\2\u00fc\u00fd\7t\2\2\u00fd\u00fe\7g\2\2\u00fe\u00ff"+
		"\7r\2\2\u00ff\u0100\7g\2\2\u0100\u0101\7c\2\2\u0101\u0102\7v\2\2\u0102"+
		"\u0103\7g\2\2\u0103\u0104\7f\2\2\u0104\u0105\7K\2\2\u0105\u0106\7v\2\2"+
		"\u0106\u0107\7g\2\2\u0107\u0108\7o\2\2\u0108\u0109\7u\2\2\u0109\66\3\2"+
		"\2\2\u010a\u010b\7r\2\2\u010b\u010c\7C\2\2\u010c\u010d\7n\2\2\u010d\u010e"+
		"\7n\2\2\u010e\u010f\7Q\2\2\u010f\u0110\7h\2\2\u01108\3\2\2\2\u0111\u0112"+
		"\7r\2\2\u0112\u0113\7C\2\2\u0113\u0114\7p\2\2\u0114\u0115\7{\2\2\u0115"+
		"\u0116\7Q\2\2\u0116\u0117\7h\2\2\u0117:\3\2\2\2\u0118\u0119\7r\2\2\u0119"+
		"\u011a\7P\2\2\u011a\u011b\7q\2\2\u011b\u011c\7v\2\2\u011c<\3\2\2\2\u011d"+
		"\u011e\7r\2\2\u011e\u011f\7c\2\2\u011f\u0120\7v\2\2\u0120\u0121\7v\2\2"+
		"\u0121\u0122\7g\2\2\u0122\u0123\7t\2\2\u0123\u0124\7p\2\2\u0124>\3\2\2"+
		"\2\u0125\u0126\7p\2\2\u0126\u0127\7q\2\2\u0127\u0128\7v\2\2\u0128\u0129"+
		"\7R\2\2\u0129\u012a\7c\2\2\u012a\u012b\7v\2\2\u012b\u012c\7v\2\2\u012c"+
		"\u012d\7g\2\2\u012d\u012e\7t\2\2\u012e\u012f\7p\2\2\u012f@\3\2\2\2\u0130"+
		"\u0131\7k\2\2\u0131\u0132\7v\2\2\u0132\u0133\7g\2\2\u0133\u0134\7o\2\2"+
		"\u0134\u0135\7u\2\2\u0135B\3\2\2\2\u0136\u0137\7e\2\2\u0137\u0138\7q\2"+
		"\2\u0138\u0139\7p\2\2\u0139\u013a\7v\2\2\u013a\u013b\7c\2\2\u013b\u013c"+
		"\7k\2\2\u013c\u013d\7p\2\2\u013d\u013e\7u\2\2\u013eD\3\2\2\2\u013f\u0140"+
		"\7r\2\2\u0140\u0141\7t\2\2\u0141\u0142\7q\2\2\u0142\u0143\7r\2\2\u0143"+
		"\u0144\7u\2\2\u0144F\3\2\2\2\u0145\u0146\7<\2\2\u0146H\3\2\2\2\u0147\u0148"+
		"\7e\2\2\u0148\u0149\7q\2\2\u0149\u014a\7p\2\2\u014a\u014b\7u\2\2\u014b"+
		"\u014c\7v\2\2\u014cJ\3\2\2\2\u014d\u014e\7f\2\2\u014e\u014f\7g\2\2\u014f"+
		"\u0150\7h\2\2\u0150L\3\2\2\2\u0151\u0152\7t\2\2\u0152\u0153\7q\2\2\u0153"+
		"\u0154\7q\2\2\u0154\u0155\7v\2\2\u0155\u0156\7f\2\2\u0156\u0157\7g\2\2"+
		"\u0157\u0158\7h\2\2\u0158N\3\2\2\2\u0159\u015a\7?\2\2\u015aP\3\2\2\2\u015b"+
		"\u015c\7t\2\2\u015c\u015d\7g\2\2\u015d\u015e\7h\2\2\u015eR\3\2\2\2\u015f"+
		"\u0160\7p\2\2\u0160\u0161\7c\2\2\u0161\u0162\7o\2\2\u0162\u0163\7g\2\2"+
		"\u0163\u0164\7u\2\2\u0164T\3\2\2\2\u0165\u0166\7g\2\2\u0166\u0167\7z\2"+
		"\2\u0167\u0168\7P\2\2\u0168\u0169\7c\2\2\u0169\u016a\7o\2\2\u016a\u016b"+
		"\7g\2\2\u016b\u016c\7u\2\2\u016cV\3\2\2\2\u016d\u016e\7c\2\2\u016e\u016f"+
		"\7p\2\2\u016f\u0170\7p\2\2\u0170\u0171\7q\2\2\u0171\u0172\7v\2\2\u0172"+
		"\u0173\7c\2\2\u0173\u0174\7v\2\2\u0174\u0175\7k\2\2\u0175\u0176\7q\2\2"+
		"\u0176\u0177\7p\2\2\u0177\u0178\7u\2\2\u0178X\3\2\2\2\u0179\u017a\7r\2"+
		"\2\u017a\u017b\7c\2\2\u017b\u017c\7v\2\2\u017c\u017d\7v\2\2\u017d\u017e"+
		"\7T\2\2\u017e\u017f\7g\2\2\u017f\u0180\7s\2\2\u0180Z\3\2\2\2\u0181\u0182"+
		"\7c\2\2\u0182\u0183\7f\2\2\u0183\u0184\7f\2\2\u0184\u0185\7R\2\2\u0185"+
		"\u0186\7c\2\2\u0186\u0187\7v\2\2\u0187\u0188\7v\2\2\u0188\u0189\7T\2\2"+
		"\u0189\u018a\7g\2\2\u018a\u018b\7s\2\2\u018b\\\3\2\2\2\u018c\u018d\7k"+
		"\2\2\u018d\u018e\7h\2\2\u018e\u018f\7D\2\2\u018f\u0190\7q\2\2\u0190\u0191"+
		"\7q\2\2\u0191\u0192\7n\2\2\u0192\u0193\7V\2\2\u0193\u0194\7j\2\2\u0194"+
		"\u0195\7g\2\2\u0195\u0196\7p\2\2\u0196^\3\2\2\2\u0197\u0198\7-\2\2\u0198"+
		"`\3\2\2\2\u0199\u019a\7/\2\2\u019ab\3\2\2\2\u019b\u019c\7k\2\2\u019c\u019d"+
		"\7p\2\2\u019d\u019e\7h\2\2\u019ed\3\2\2\2\u019f\u01a1\5u;\2\u01a0\u019f"+
		"\3\2\2\2\u01a1\u01a2\3\2\2\2\u01a2\u01a0\3\2\2\2\u01a2\u01a3\3\2\2\2\u01a3"+
		"\u01a4\3\2\2\2\u01a4\u01a5\5q9\2\u01a5f\3\2\2\2\u01a6\u01a7\7p\2\2\u01a7"+
		"\u01a8\7w\2\2\u01a8\u01a9\7n\2\2\u01a9\u01aa\7n\2\2\u01aah\3\2\2\2\u01ab"+
		"\u01ac\7q\2\2\u01ac\u01ad\7d\2\2\u01ad\u01c8\7l\2\2\u01ae\u01af\7u\2\2"+
		"\u01af\u01b0\7v\2\2\u01b0\u01c8\7t\2\2\u01b1\u01b2\7p\2\2\u01b2\u01b3"+
		"\7w\2\2\u01b3\u01c8\7o\2\2\u01b4\u01b5\7k\2\2\u01b5\u01b6\7p\2\2\u01b6"+
		"\u01c8\7v\2\2\u01b7\u01b8\7c\2\2\u01b8\u01b9\7t\2\2\u01b9\u01c8\7t\2\2"+
		"\u01ba\u01bb\7d\2\2\u01bb\u01bc\7q\2\2\u01bc\u01bd\7q\2\2\u01bd\u01c8"+
		"\7n\2\2\u01be\u01bf\7p\2\2\u01bf\u01c0\7w\2\2\u01c0\u01c1\7o\2\2\u01c1"+
		"\u01c2\7P\2\2\u01c2\u01c3\7q\2\2\u01c3\u01c4\7v\2\2\u01c4\u01c5\7K\2\2"+
		"\u01c5\u01c6\7p\2\2\u01c6\u01c8\7v\2\2\u01c7\u01ab\3\2\2\2\u01c7\u01ae"+
		"\3\2\2\2\u01c7\u01b1\3\2\2\2\u01c7\u01b4\3\2\2\2\u01c7\u01b7\3\2\2\2\u01c7"+
		"\u01ba\3\2\2\2\u01c7\u01be\3\2\2\2\u01c8j\3\2\2\2\u01c9\u01cb\7/\2\2\u01ca"+
		"\u01c9\3\2\2\2\u01ca\u01cb\3\2\2\2\u01cb\u01cd\3\2\2\2\u01cc\u01ce\t\2"+
		"\2\2\u01cd\u01cc\3\2\2\2\u01ce\u01cf\3\2\2\2\u01cf\u01cd\3\2\2\2\u01cf"+
		"\u01d0\3\2\2\2\u01d0l\3\2\2\2\u01d1\u01d3\7/\2\2\u01d2\u01d1\3\2\2\2\u01d2"+
		"\u01d3\3\2\2\2\u01d3\u01d5\3\2\2\2\u01d4\u01d6\t\2\2\2\u01d5\u01d4\3\2"+
		"\2\2\u01d6\u01d7\3\2\2\2\u01d7\u01d5\3\2\2\2\u01d7\u01d8\3\2\2\2\u01d8"+
		"\u01d9\3\2\2\2\u01d9\u01db\7\60\2\2\u01da\u01dc\t\3\2\2\u01db\u01da\3"+
		"\2\2\2\u01dc\u01dd\3\2\2\2\u01dd\u01db\3\2\2\2\u01dd\u01de\3\2\2\2\u01de"+
		"n\3\2\2\2\u01df\u01e1\t\4\2\2\u01e0\u01df\3\2\2\2\u01e1\u01e2\3\2\2\2"+
		"\u01e2\u01e0\3\2\2\2\u01e2\u01e3\3\2\2\2\u01e3\u01e4\3\2\2\2\u01e4\u01e5"+
		"\b8\2\2\u01e5p\3\2\2\2\u01e6\u01eb\7$\2\2\u01e7\u01ea\5u;\2\u01e8\u01ea"+
		"\n\5\2\2\u01e9\u01e7\3\2\2\2\u01e9\u01e8\3\2\2\2\u01ea\u01ed\3\2\2\2\u01eb"+
		"\u01e9\3\2\2\2\u01eb\u01ec\3\2\2\2\u01ec\u01ee\3\2\2\2\u01ed\u01eb\3\2"+
		"\2\2\u01ee\u01ef\7$\2\2\u01efr\3\2\2\2\u01f0\u0200\7v\2\2\u01f1\u01f2"+
		"\7v\2\2\u01f2\u0200\7v\2\2\u01f3\u0200\7h\2\2\u01f4\u01f5\7h\2\2\u01f5"+
		"\u0200\7h\2\2\u01f6\u01f7\7v\2\2\u01f7\u01f8\7t\2\2\u01f8\u01f9\7w\2\2"+
		"\u01f9\u0200\7g\2\2\u01fa\u01fb\7h\2\2\u01fb\u01fc\7c\2\2\u01fc\u01fd"+
		"\7n\2\2\u01fd\u01fe\7u\2\2\u01fe\u0200\7g\2\2\u01ff\u01f0\3\2\2\2\u01ff"+
		"\u01f1\3\2\2\2\u01ff\u01f3\3\2\2\2\u01ff\u01f4\3\2\2\2\u01ff\u01f6\3\2"+
		"\2\2\u01ff\u01fa\3\2\2\2\u0200t\3\2\2\2\u0201\u0204\7^\2\2\u0202\u0205"+
		"\t\6\2\2\u0203\u0205\5w<\2\u0204\u0202\3\2\2\2\u0204\u0203\3\2\2\2\u0205"+
		"v\3\2\2\2\u0206\u0207\7w\2\2\u0207\u0208\5y=\2\u0208\u0209\5y=\2\u0209"+
		"\u020a\5y=\2\u020a\u020b\5y=\2\u020b\u0211\3\2\2\2\u020c\u020d\7z\2\2"+
		"\u020d\u020e\5y=\2\u020e\u020f\5y=\2\u020f\u0211\3\2\2\2\u0210\u0206\3"+
		"\2\2\2\u0210\u020c\3\2\2\2\u0211x\3\2\2\2\u0212\u0213\t\7\2\2\u0213z\3"+
		"\2\2\2\u0214\u0215\n\b\2\2\u0215|\3\2\2\2\u0216\u0217\7\61\2\2\u0217\u0218"+
		"\7,\2\2\u0218\u021c\3\2\2\2\u0219\u021b\13\2\2\2\u021a\u0219\3\2\2\2\u021b"+
		"\u021e\3\2\2\2\u021c\u021d\3\2\2\2\u021c\u021a\3\2\2\2\u021d\u021f\3\2"+
		"\2\2\u021e\u021c\3\2\2\2\u021f\u0220\7,\2\2\u0220\u0221\7\61\2\2\u0221"+
		"\u0222\3\2\2\2\u0222\u0223\b?\2\2\u0223~\3\2\2\2\u0224\u0225\7\61\2\2"+
		"\u0225\u0226\7\61\2\2\u0226\u022a\3\2\2\2\u0227\u0229\n\t\2\2\u0228\u0227"+
		"\3\2\2\2\u0229\u022c\3\2\2\2\u022a\u0228\3\2\2\2\u022a\u022b\3\2\2\2\u022b"+
		"\u022d\3\2\2\2\u022c\u022a\3\2\2\2\u022d\u022e\b@\2\2\u022e\u0080\3\2"+
		"\2\2\22\2\u01a2\u01c7\u01ca\u01cf\u01d2\u01d7\u01dd\u01e2\u01e9\u01eb"+
		"\u01ff\u0204\u0210\u021c\u022a\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}