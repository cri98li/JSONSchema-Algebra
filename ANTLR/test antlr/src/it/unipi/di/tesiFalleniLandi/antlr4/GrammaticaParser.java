package it.unipi.di.tesiFalleniLandi.antlr4;

// Generated from Grammatica.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GrammaticaParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, INT=6, ID=7, WS=8, STRING=9;
	public static final int
		RULE_assertion_list = 0, RULE_s = 1, RULE_value = 2, RULE_between = 3;
	private static String[] makeRuleNames() {
		return new String[] {
			"assertion_list", "s", "value", "between"
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

	@Override
	public String getGrammarFileName() { return "Grammatica.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public GrammaticaParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class Assertion_listContext extends ParserRuleContext {
		public Assertion_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assertion_list; }
	 
		public Assertion_listContext() { }
		public void copyFrom(Assertion_listContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ListContext extends Assertion_listContext {
		public List<SContext> s() {
			return getRuleContexts(SContext.class);
		}
		public SContext s(int i) {
			return getRuleContext(SContext.class,i);
		}
		public ListContext(Assertion_listContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Assertion_listContext assertion_list() throws RecognitionException {
		Assertion_listContext _localctx = new Assertion_listContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_assertion_list);
		int _la;
		try {
			_localctx = new ListContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(8);
			match(T__0);
			setState(9);
			s();
			setState(14);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(10);
				match(T__1);
				setState(11);
				s();
				}
				}
				setState(16);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(17);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SContext extends ParserRuleContext {
		public SContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_s; }
	 
		public SContext() { }
		public void copyFrom(SContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BetweenAssertionContext extends SContext {
		public BetweenContext between() {
			return getRuleContext(BetweenContext.class,0);
		}
		public BetweenAssertionContext(SContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitBetweenAssertion(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NewListContext extends SContext {
		public Assertion_listContext assertion_list() {
			return getRuleContext(Assertion_listContext.class,0);
		}
		public NewListContext(SContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitNewList(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TypeAssertionContext extends SContext {
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public TypeAssertionContext(SContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitTypeAssertion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SContext s() throws RecognitionException {
		SContext _localctx = new SContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_s);
		try {
			setState(22);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
			case ID:
				_localctx = new TypeAssertionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(19);
				value();
				}
				break;
			case T__0:
				_localctx = new NewListContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(20);
				assertion_list();
				}
				break;
			case T__3:
				_localctx = new BetweenAssertionContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(21);
				between();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ValueContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(GrammaticaParser.INT, 0); }
		public TerminalNode ID() { return getToken(GrammaticaParser.ID, 0); }
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(24);
			_la = _input.LA(1);
			if ( !(_la==INT || _la==ID) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BetweenContext extends ParserRuleContext {
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public BetweenContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_between; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitBetween(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BetweenContext between() throws RecognitionException {
		BetweenContext _localctx = new BetweenContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_between);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(26);
			match(T__3);
			setState(27);
			value();
			setState(28);
			match(T__1);
			setState(29);
			value();
			setState(30);
			match(T__4);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\13#\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\3\2\3\2\3\2\3\2\7\2\17\n\2\f\2\16\2\22\13\2\3\2\3\2"+
		"\3\3\3\3\3\3\5\3\31\n\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\2\2\6\2\4"+
		"\6\b\2\3\3\2\b\t\2!\2\n\3\2\2\2\4\30\3\2\2\2\6\32\3\2\2\2\b\34\3\2\2\2"+
		"\n\13\7\3\2\2\13\20\5\4\3\2\f\r\7\4\2\2\r\17\5\4\3\2\16\f\3\2\2\2\17\22"+
		"\3\2\2\2\20\16\3\2\2\2\20\21\3\2\2\2\21\23\3\2\2\2\22\20\3\2\2\2\23\24"+
		"\7\5\2\2\24\3\3\2\2\2\25\31\5\6\4\2\26\31\5\2\2\2\27\31\5\b\5\2\30\25"+
		"\3\2\2\2\30\26\3\2\2\2\30\27\3\2\2\2\31\5\3\2\2\2\32\33\t\2\2\2\33\7\3"+
		"\2\2\2\34\35\7\6\2\2\35\36\5\6\4\2\36\37\7\4\2\2\37 \5\6\4\2 !\7\7\2\2"+
		"!\t\3\2\2\2\4\20\30";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}