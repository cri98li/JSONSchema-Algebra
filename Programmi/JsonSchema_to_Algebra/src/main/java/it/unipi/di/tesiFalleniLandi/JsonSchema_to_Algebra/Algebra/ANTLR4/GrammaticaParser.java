// Generated from Grammatica.g4 by ANTLR 4.7.2
package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4;

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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, TYPE=29, NULL=30, INT=31, WS=32, 
		STRING=33;
	public static final int
		RULE_assertion = 0, RULE_type_assertion = 1, RULE_between_assertion = 2, 
		RULE_xbetween_assertion = 3, RULE_length_assertion = 4, RULE_bet_items_assertion = 5, 
		RULE_between_properties_assertion = 6, RULE_multiple_of_assertion = 7, 
		RULE_not_assertion = 8, RULE_all_of_assertion = 9, RULE_one_of_assertion = 10, 
		RULE_any_of_assertion = 11, RULE_required_assertion = 12, RULE_enum_assertion_assertion = 13, 
		RULE_if_then_else_assertion = 14, RULE_unique_items_assertion = 15, RULE_pattern_assertion = 16, 
		RULE_items_assertion = 17, RULE_contains_assertion = 18, RULE_const_assertion = 19, 
		RULE_numeric_value = 20;
	private static String[] makeRuleNames() {
		return new String[] {
			"assertion", "type_assertion", "between_assertion", "xbetween_assertion", 
			"length_assertion", "bet_items_assertion", "between_properties_assertion", 
			"multiple_of_assertion", "not_assertion", "all_of_assertion", "one_of_assertion", 
			"any_of_assertion", "required_assertion", "enum_assertion_assertion", 
			"if_then_else_assertion", "unique_items_assertion", "pattern_assertion", 
			"items_assertion", "contains_assertion", "const_assertion", "numeric_value"
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
	public static class AssertionContext extends ParserRuleContext {
		public AssertionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assertion; }
	 
		public AssertionContext() { }
		public void copyFrom(AssertionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class NewContainsContext extends AssertionContext {
		public Contains_assertionContext contains_assertion() {
			return getRuleContext(Contains_assertionContext.class,0);
		}
		public NewContainsContext(AssertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitNewContains(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NewConstContext extends AssertionContext {
		public Const_assertionContext const_assertion() {
			return getRuleContext(Const_assertionContext.class,0);
		}
		public NewConstContext(AssertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitNewConst(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NewIfThenElseContext extends AssertionContext {
		public If_then_else_assertionContext if_then_else_assertion() {
			return getRuleContext(If_then_else_assertionContext.class,0);
		}
		public NewIfThenElseContext(AssertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitNewIfThenElse(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NewTypeAssertionContext extends AssertionContext {
		public Type_assertionContext type_assertion() {
			return getRuleContext(Type_assertionContext.class,0);
		}
		public NewTypeAssertionContext(AssertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitNewTypeAssertion(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NweBetweenItemsContext extends AssertionContext {
		public Bet_items_assertionContext bet_items_assertion() {
			return getRuleContext(Bet_items_assertionContext.class,0);
		}
		public NweBetweenItemsContext(AssertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitNweBetweenItems(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NewAnyOfContext extends AssertionContext {
		public Any_of_assertionContext any_of_assertion() {
			return getRuleContext(Any_of_assertionContext.class,0);
		}
		public NewAnyOfContext(AssertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitNewAnyOf(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NewXBetweenAssertionContext extends AssertionContext {
		public Xbetween_assertionContext xbetween_assertion() {
			return getRuleContext(Xbetween_assertionContext.class,0);
		}
		public NewXBetweenAssertionContext(AssertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitNewXBetweenAssertion(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NewEnumContext extends AssertionContext {
		public Enum_assertion_assertionContext enum_assertion_assertion() {
			return getRuleContext(Enum_assertion_assertionContext.class,0);
		}
		public NewEnumContext(AssertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitNewEnum(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NewMultipleOfContext extends AssertionContext {
		public Multiple_of_assertionContext multiple_of_assertion() {
			return getRuleContext(Multiple_of_assertionContext.class,0);
		}
		public NewMultipleOfContext(AssertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitNewMultipleOf(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NewPatternContext extends AssertionContext {
		public Pattern_assertionContext pattern_assertion() {
			return getRuleContext(Pattern_assertionContext.class,0);
		}
		public NewPatternContext(AssertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitNewPattern(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NewListContext extends AssertionContext {
		public List<AssertionContext> assertion() {
			return getRuleContexts(AssertionContext.class);
		}
		public AssertionContext assertion(int i) {
			return getRuleContext(AssertionContext.class,i);
		}
		public NewListContext(AssertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitNewList(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NewLengthContext extends AssertionContext {
		public Length_assertionContext length_assertion() {
			return getRuleContext(Length_assertionContext.class,0);
		}
		public NewLengthContext(AssertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitNewLength(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NewAllOfContext extends AssertionContext {
		public All_of_assertionContext all_of_assertion() {
			return getRuleContext(All_of_assertionContext.class,0);
		}
		public NewAllOfContext(AssertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitNewAllOf(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NewBetweenAssertionContext extends AssertionContext {
		public Between_assertionContext between_assertion() {
			return getRuleContext(Between_assertionContext.class,0);
		}
		public NewBetweenAssertionContext(AssertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitNewBetweenAssertion(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NewNotContext extends AssertionContext {
		public Not_assertionContext not_assertion() {
			return getRuleContext(Not_assertionContext.class,0);
		}
		public NewNotContext(AssertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitNewNot(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NewOneOfContext extends AssertionContext {
		public One_of_assertionContext one_of_assertion() {
			return getRuleContext(One_of_assertionContext.class,0);
		}
		public NewOneOfContext(AssertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitNewOneOf(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NewUniqueItemsContext extends AssertionContext {
		public Unique_items_assertionContext unique_items_assertion() {
			return getRuleContext(Unique_items_assertionContext.class,0);
		}
		public NewUniqueItemsContext(AssertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitNewUniqueItems(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NewBetweenPropertiesContext extends AssertionContext {
		public Between_properties_assertionContext between_properties_assertion() {
			return getRuleContext(Between_properties_assertionContext.class,0);
		}
		public NewBetweenPropertiesContext(AssertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitNewBetweenProperties(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NewRequiredContext extends AssertionContext {
		public Required_assertionContext required_assertion() {
			return getRuleContext(Required_assertionContext.class,0);
		}
		public NewRequiredContext(AssertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitNewRequired(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NewItemsContext extends AssertionContext {
		public Items_assertionContext items_assertion() {
			return getRuleContext(Items_assertionContext.class,0);
		}
		public NewItemsContext(AssertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitNewItems(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssertionContext assertion() throws RecognitionException {
		return assertion(0);
	}

	private AssertionContext assertion(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		AssertionContext _localctx = new AssertionContext(_ctx, _parentState);
		AssertionContext _prevctx = _localctx;
		int _startState = 0;
		enterRecursionRule(_localctx, 0, RULE_assertion, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
				{
				_localctx = new NewTypeAssertionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(43);
				type_assertion();
				}
				break;
			case T__3:
				{
				_localctx = new NewBetweenAssertionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(44);
				between_assertion();
				}
				break;
			case T__10:
				{
				_localctx = new NewNotContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(45);
				not_assertion();
				}
				break;
			case T__5:
				{
				_localctx = new NewXBetweenAssertionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(46);
				xbetween_assertion();
				}
				break;
			case T__7:
				{
				_localctx = new NweBetweenItemsContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(47);
				bet_items_assertion();
				}
				break;
			case T__6:
				{
				_localctx = new NewLengthContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(48);
				length_assertion();
				}
				break;
			case T__8:
				{
				_localctx = new NewBetweenPropertiesContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(49);
				between_properties_assertion();
				}
				break;
			case T__11:
				{
				_localctx = new NewAllOfContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(50);
				all_of_assertion();
				}
				break;
			case T__13:
				{
				_localctx = new NewAnyOfContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(51);
				any_of_assertion();
				}
				break;
			case T__12:
				{
				_localctx = new NewOneOfContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(52);
				one_of_assertion();
				}
				break;
			case T__14:
				{
				_localctx = new NewRequiredContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(53);
				required_assertion();
				}
				break;
			case T__17:
				{
				_localctx = new NewIfThenElseContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(54);
				if_then_else_assertion();
				}
				break;
			case T__9:
				{
				_localctx = new NewMultipleOfContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(55);
				multiple_of_assertion();
				}
				break;
			case T__16:
				{
				_localctx = new NewEnumContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(56);
				enum_assertion_assertion();
				}
				break;
			case T__20:
				{
				_localctx = new NewUniqueItemsContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(57);
				unique_items_assertion();
				}
				break;
			case T__21:
				{
				_localctx = new NewPatternContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(58);
				pattern_assertion();
				}
				break;
			case T__26:
				{
				_localctx = new NewContainsContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(59);
				contains_assertion();
				}
				break;
			case T__27:
				{
				_localctx = new NewConstContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(60);
				const_assertion();
				}
				break;
			case T__22:
			case T__23:
				{
				_localctx = new NewItemsContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(61);
				items_assertion();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(69);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new NewListContext(new AssertionContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_assertion);
					setState(64);
					if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
					setState(65);
					match(T__0);
					setState(66);
					assertion(20);
					}
					} 
				}
				setState(71);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Type_assertionContext extends ParserRuleContext {
		public Type_assertionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_assertion; }
	 
		public Type_assertionContext() { }
		public void copyFrom(Type_assertionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ParseTypeAssertionContext extends Type_assertionContext {
		public TerminalNode TYPE() { return getToken(GrammaticaParser.TYPE, 0); }
		public ParseTypeAssertionContext(Type_assertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitParseTypeAssertion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Type_assertionContext type_assertion() throws RecognitionException {
		Type_assertionContext _localctx = new Type_assertionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_type_assertion);
		try {
			_localctx = new ParseTypeAssertionContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			match(T__1);
			setState(73);
			match(TYPE);
			setState(74);
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

	public static class Between_assertionContext extends ParserRuleContext {
		public Between_assertionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_between_assertion; }
	 
		public Between_assertionContext() { }
		public void copyFrom(Between_assertionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ParseBetweenAssertionContext extends Between_assertionContext {
		public List<Numeric_valueContext> numeric_value() {
			return getRuleContexts(Numeric_valueContext.class);
		}
		public Numeric_valueContext numeric_value(int i) {
			return getRuleContext(Numeric_valueContext.class,i);
		}
		public ParseBetweenAssertionContext(Between_assertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitParseBetweenAssertion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Between_assertionContext between_assertion() throws RecognitionException {
		Between_assertionContext _localctx = new Between_assertionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_between_assertion);
		try {
			_localctx = new ParseBetweenAssertionContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
			match(T__3);
			setState(77);
			numeric_value();
			setState(78);
			match(T__0);
			setState(79);
			numeric_value();
			setState(80);
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

	public static class Xbetween_assertionContext extends ParserRuleContext {
		public Xbetween_assertionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_xbetween_assertion; }
	 
		public Xbetween_assertionContext() { }
		public void copyFrom(Xbetween_assertionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ParseXBetweenAssertionContext extends Xbetween_assertionContext {
		public List<Numeric_valueContext> numeric_value() {
			return getRuleContexts(Numeric_valueContext.class);
		}
		public Numeric_valueContext numeric_value(int i) {
			return getRuleContext(Numeric_valueContext.class,i);
		}
		public ParseXBetweenAssertionContext(Xbetween_assertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitParseXBetweenAssertion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Xbetween_assertionContext xbetween_assertion() throws RecognitionException {
		Xbetween_assertionContext _localctx = new Xbetween_assertionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_xbetween_assertion);
		try {
			_localctx = new ParseXBetweenAssertionContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			match(T__5);
			setState(83);
			numeric_value();
			setState(84);
			match(T__0);
			setState(85);
			numeric_value();
			setState(86);
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

	public static class Length_assertionContext extends ParserRuleContext {
		public Length_assertionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_length_assertion; }
	 
		public Length_assertionContext() { }
		public void copyFrom(Length_assertionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ParseLengthAssertionContext extends Length_assertionContext {
		public List<Numeric_valueContext> numeric_value() {
			return getRuleContexts(Numeric_valueContext.class);
		}
		public Numeric_valueContext numeric_value(int i) {
			return getRuleContext(Numeric_valueContext.class,i);
		}
		public ParseLengthAssertionContext(Length_assertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitParseLengthAssertion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Length_assertionContext length_assertion() throws RecognitionException {
		Length_assertionContext _localctx = new Length_assertionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_length_assertion);
		try {
			_localctx = new ParseLengthAssertionContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			match(T__6);
			setState(89);
			numeric_value();
			setState(90);
			match(T__0);
			setState(91);
			numeric_value();
			setState(92);
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

	public static class Bet_items_assertionContext extends ParserRuleContext {
		public Bet_items_assertionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bet_items_assertion; }
	 
		public Bet_items_assertionContext() { }
		public void copyFrom(Bet_items_assertionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ParseBetItemsAssertionContext extends Bet_items_assertionContext {
		public List<Numeric_valueContext> numeric_value() {
			return getRuleContexts(Numeric_valueContext.class);
		}
		public Numeric_valueContext numeric_value(int i) {
			return getRuleContext(Numeric_valueContext.class,i);
		}
		public ParseBetItemsAssertionContext(Bet_items_assertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitParseBetItemsAssertion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Bet_items_assertionContext bet_items_assertion() throws RecognitionException {
		Bet_items_assertionContext _localctx = new Bet_items_assertionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_bet_items_assertion);
		try {
			_localctx = new ParseBetItemsAssertionContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			match(T__7);
			setState(95);
			numeric_value();
			setState(96);
			match(T__0);
			setState(97);
			numeric_value();
			setState(98);
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

	public static class Between_properties_assertionContext extends ParserRuleContext {
		public Between_properties_assertionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_between_properties_assertion; }
	 
		public Between_properties_assertionContext() { }
		public void copyFrom(Between_properties_assertionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ParseBetProAssertionContext extends Between_properties_assertionContext {
		public List<Numeric_valueContext> numeric_value() {
			return getRuleContexts(Numeric_valueContext.class);
		}
		public Numeric_valueContext numeric_value(int i) {
			return getRuleContext(Numeric_valueContext.class,i);
		}
		public ParseBetProAssertionContext(Between_properties_assertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitParseBetProAssertion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Between_properties_assertionContext between_properties_assertion() throws RecognitionException {
		Between_properties_assertionContext _localctx = new Between_properties_assertionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_between_properties_assertion);
		try {
			_localctx = new ParseBetProAssertionContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			match(T__8);
			setState(101);
			numeric_value();
			setState(102);
			match(T__0);
			setState(103);
			numeric_value();
			setState(104);
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

	public static class Multiple_of_assertionContext extends ParserRuleContext {
		public Multiple_of_assertionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiple_of_assertion; }
	 
		public Multiple_of_assertionContext() { }
		public void copyFrom(Multiple_of_assertionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ParseMultipleOfContext extends Multiple_of_assertionContext {
		public Numeric_valueContext numeric_value() {
			return getRuleContext(Numeric_valueContext.class,0);
		}
		public ParseMultipleOfContext(Multiple_of_assertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitParseMultipleOf(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Multiple_of_assertionContext multiple_of_assertion() throws RecognitionException {
		Multiple_of_assertionContext _localctx = new Multiple_of_assertionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_multiple_of_assertion);
		try {
			_localctx = new ParseMultipleOfContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(106);
			match(T__9);
			setState(107);
			numeric_value();
			setState(108);
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

	public static class Not_assertionContext extends ParserRuleContext {
		public Not_assertionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_not_assertion; }
	 
		public Not_assertionContext() { }
		public void copyFrom(Not_assertionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ParseNotContext extends Not_assertionContext {
		public AssertionContext assertion() {
			return getRuleContext(AssertionContext.class,0);
		}
		public ParseNotContext(Not_assertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitParseNot(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Not_assertionContext not_assertion() throws RecognitionException {
		Not_assertionContext _localctx = new Not_assertionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_not_assertion);
		try {
			_localctx = new ParseNotContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			match(T__10);
			setState(111);
			assertion(0);
			setState(112);
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

	public static class All_of_assertionContext extends ParserRuleContext {
		public All_of_assertionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_all_of_assertion; }
	 
		public All_of_assertionContext() { }
		public void copyFrom(All_of_assertionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ParseAllOfContext extends All_of_assertionContext {
		public AssertionContext assertion() {
			return getRuleContext(AssertionContext.class,0);
		}
		public ParseAllOfContext(All_of_assertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitParseAllOf(this);
			else return visitor.visitChildren(this);
		}
	}

	public final All_of_assertionContext all_of_assertion() throws RecognitionException {
		All_of_assertionContext _localctx = new All_of_assertionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_all_of_assertion);
		try {
			_localctx = new ParseAllOfContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(114);
			match(T__11);
			setState(115);
			assertion(0);
			setState(116);
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

	public static class One_of_assertionContext extends ParserRuleContext {
		public One_of_assertionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_one_of_assertion; }
	 
		public One_of_assertionContext() { }
		public void copyFrom(One_of_assertionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ParseOneOfContext extends One_of_assertionContext {
		public AssertionContext assertion() {
			return getRuleContext(AssertionContext.class,0);
		}
		public ParseOneOfContext(One_of_assertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitParseOneOf(this);
			else return visitor.visitChildren(this);
		}
	}

	public final One_of_assertionContext one_of_assertion() throws RecognitionException {
		One_of_assertionContext _localctx = new One_of_assertionContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_one_of_assertion);
		try {
			_localctx = new ParseOneOfContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			match(T__12);
			setState(119);
			assertion(0);
			setState(120);
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

	public static class Any_of_assertionContext extends ParserRuleContext {
		public Any_of_assertionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_any_of_assertion; }
	 
		public Any_of_assertionContext() { }
		public void copyFrom(Any_of_assertionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ParseAnyOfContext extends Any_of_assertionContext {
		public AssertionContext assertion() {
			return getRuleContext(AssertionContext.class,0);
		}
		public ParseAnyOfContext(Any_of_assertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitParseAnyOf(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Any_of_assertionContext any_of_assertion() throws RecognitionException {
		Any_of_assertionContext _localctx = new Any_of_assertionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_any_of_assertion);
		try {
			_localctx = new ParseAnyOfContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			match(T__13);
			setState(123);
			assertion(0);
			setState(124);
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

	public static class Required_assertionContext extends ParserRuleContext {
		public Required_assertionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_required_assertion; }
	 
		public Required_assertionContext() { }
		public void copyFrom(Required_assertionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ParseRequiredContext extends Required_assertionContext {
		public List<TerminalNode> STRING() { return getTokens(GrammaticaParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(GrammaticaParser.STRING, i);
		}
		public ParseRequiredContext(Required_assertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitParseRequired(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Required_assertionContext required_assertion() throws RecognitionException {
		Required_assertionContext _localctx = new Required_assertionContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_required_assertion);
		int _la;
		try {
			_localctx = new ParseRequiredContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			match(T__14);
			setState(127);
			match(STRING);
			setState(132);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(128);
				match(T__0);
				setState(129);
				match(STRING);
				}
				}
				setState(134);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(135);
			match(T__15);
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

	public static class Enum_assertion_assertionContext extends ParserRuleContext {
		public Enum_assertion_assertionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enum_assertion_assertion; }
	 
		public Enum_assertion_assertionContext() { }
		public void copyFrom(Enum_assertion_assertionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ParseEnumContext extends Enum_assertion_assertionContext {
		public List<Numeric_valueContext> numeric_value() {
			return getRuleContexts(Numeric_valueContext.class);
		}
		public Numeric_valueContext numeric_value(int i) {
			return getRuleContext(Numeric_valueContext.class,i);
		}
		public ParseEnumContext(Enum_assertion_assertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitParseEnum(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Enum_assertion_assertionContext enum_assertion_assertion() throws RecognitionException {
		Enum_assertion_assertionContext _localctx = new Enum_assertion_assertionContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_enum_assertion_assertion);
		int _la;
		try {
			_localctx = new ParseEnumContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(137);
			match(T__16);
			setState(138);
			numeric_value();
			setState(143);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(139);
				match(T__0);
				setState(140);
				numeric_value();
				}
				}
				setState(145);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(146);
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

	public static class If_then_else_assertionContext extends ParserRuleContext {
		public If_then_else_assertionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_then_else_assertion; }
	 
		public If_then_else_assertionContext() { }
		public void copyFrom(If_then_else_assertionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ParseIfThenElseContext extends If_then_else_assertionContext {
		public List<AssertionContext> assertion() {
			return getRuleContexts(AssertionContext.class);
		}
		public AssertionContext assertion(int i) {
			return getRuleContext(AssertionContext.class,i);
		}
		public ParseIfThenElseContext(If_then_else_assertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitParseIfThenElse(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParseIfThenContext extends If_then_else_assertionContext {
		public List<AssertionContext> assertion() {
			return getRuleContexts(AssertionContext.class);
		}
		public AssertionContext assertion(int i) {
			return getRuleContext(AssertionContext.class,i);
		}
		public ParseIfThenContext(If_then_else_assertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitParseIfThen(this);
			else return visitor.visitChildren(this);
		}
	}

	public final If_then_else_assertionContext if_then_else_assertion() throws RecognitionException {
		If_then_else_assertionContext _localctx = new If_then_else_assertionContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_if_then_else_assertion);
		try {
			setState(162);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				_localctx = new ParseIfThenElseContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(148);
				match(T__17);
				setState(149);
				assertion(0);
				setState(150);
				match(T__18);
				setState(151);
				assertion(0);
				setState(152);
				match(T__19);
				setState(153);
				assertion(0);
				setState(154);
				match(T__2);
				}
				break;
			case 2:
				_localctx = new ParseIfThenContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(156);
				match(T__17);
				setState(157);
				assertion(0);
				setState(158);
				match(T__18);
				setState(159);
				assertion(0);
				setState(160);
				match(T__2);
				}
				break;
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

	public static class Unique_items_assertionContext extends ParserRuleContext {
		public Unique_items_assertionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unique_items_assertion; }
	 
		public Unique_items_assertionContext() { }
		public void copyFrom(Unique_items_assertionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ParseUniqueItemsContext extends Unique_items_assertionContext {
		public ParseUniqueItemsContext(Unique_items_assertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitParseUniqueItems(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Unique_items_assertionContext unique_items_assertion() throws RecognitionException {
		Unique_items_assertionContext _localctx = new Unique_items_assertionContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_unique_items_assertion);
		try {
			_localctx = new ParseUniqueItemsContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(164);
			match(T__20);
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

	public static class Pattern_assertionContext extends ParserRuleContext {
		public Pattern_assertionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pattern_assertion; }
	 
		public Pattern_assertionContext() { }
		public void copyFrom(Pattern_assertionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ParsePatternContext extends Pattern_assertionContext {
		public TerminalNode STRING() { return getToken(GrammaticaParser.STRING, 0); }
		public ParsePatternContext(Pattern_assertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitParsePattern(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Pattern_assertionContext pattern_assertion() throws RecognitionException {
		Pattern_assertionContext _localctx = new Pattern_assertionContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_pattern_assertion);
		try {
			_localctx = new ParsePatternContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
			match(T__21);
			setState(167);
			match(STRING);
			setState(168);
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

	public static class Items_assertionContext extends ParserRuleContext {
		public Items_assertionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_items_assertion; }
	 
		public Items_assertionContext() { }
		public void copyFrom(Items_assertionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ParseItemsContext extends Items_assertionContext {
		public AssertionContext assertion() {
			return getRuleContext(AssertionContext.class,0);
		}
		public ParseItemsContext(Items_assertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitParseItems(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParseItemsArrayContext extends Items_assertionContext {
		public List<AssertionContext> assertion() {
			return getRuleContexts(AssertionContext.class);
		}
		public AssertionContext assertion(int i) {
			return getRuleContext(AssertionContext.class,i);
		}
		public ParseItemsArrayContext(Items_assertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitParseItemsArray(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Items_assertionContext items_assertion() throws RecognitionException {
		Items_assertionContext _localctx = new Items_assertionContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_items_assertion);
		int _la;
		try {
			setState(187);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__22:
				_localctx = new ParseItemsContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(170);
				match(T__22);
				setState(171);
				assertion(0);
				setState(172);
				match(T__2);
				}
				break;
			case T__23:
				_localctx = new ParseItemsArrayContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(174);
				match(T__23);
				setState(175);
				assertion(0);
				setState(180);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__24) {
					{
					{
					setState(176);
					match(T__24);
					setState(177);
					assertion(0);
					}
					}
					setState(182);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(183);
				match(T__25);
				setState(184);
				assertion(0);
				setState(185);
				match(T__2);
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

	public static class Contains_assertionContext extends ParserRuleContext {
		public Contains_assertionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_contains_assertion; }
	 
		public Contains_assertionContext() { }
		public void copyFrom(Contains_assertionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ParseContainsContext extends Contains_assertionContext {
		public List<Numeric_valueContext> numeric_value() {
			return getRuleContexts(Numeric_valueContext.class);
		}
		public Numeric_valueContext numeric_value(int i) {
			return getRuleContext(Numeric_valueContext.class,i);
		}
		public AssertionContext assertion() {
			return getRuleContext(AssertionContext.class,0);
		}
		public ParseContainsContext(Contains_assertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitParseContains(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Contains_assertionContext contains_assertion() throws RecognitionException {
		Contains_assertionContext _localctx = new Contains_assertionContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_contains_assertion);
		try {
			_localctx = new ParseContainsContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(189);
			match(T__26);
			setState(190);
			numeric_value();
			setState(191);
			match(T__0);
			setState(192);
			numeric_value();
			setState(193);
			match(T__4);
			setState(194);
			assertion(0);
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

	public static class Const_assertionContext extends ParserRuleContext {
		public Const_assertionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_const_assertion; }
	 
		public Const_assertionContext() { }
		public void copyFrom(Const_assertionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ParseConstContext extends Const_assertionContext {
		public Numeric_valueContext numeric_value() {
			return getRuleContext(Numeric_valueContext.class,0);
		}
		public ParseConstContext(Const_assertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitParseConst(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Const_assertionContext const_assertion() throws RecognitionException {
		Const_assertionContext _localctx = new Const_assertionContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_const_assertion);
		try {
			_localctx = new ParseConstContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(196);
			match(T__27);
			setState(197);
			numeric_value();
			setState(198);
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

	public static class Numeric_valueContext extends ParserRuleContext {
		public Numeric_valueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numeric_value; }
	 
		public Numeric_valueContext() { }
		public void copyFrom(Numeric_valueContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class NullValueContext extends Numeric_valueContext {
		public TerminalNode NULL() { return getToken(GrammaticaParser.NULL, 0); }
		public NullValueContext(Numeric_valueContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitNullValue(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NumericValueContext extends Numeric_valueContext {
		public TerminalNode INT() { return getToken(GrammaticaParser.INT, 0); }
		public NumericValueContext(Numeric_valueContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitNumericValue(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StringValueContext extends Numeric_valueContext {
		public TerminalNode STRING() { return getToken(GrammaticaParser.STRING, 0); }
		public StringValueContext(Numeric_valueContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitStringValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Numeric_valueContext numeric_value() throws RecognitionException {
		Numeric_valueContext _localctx = new Numeric_valueContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_numeric_value);
		try {
			setState(203);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NULL:
				_localctx = new NullValueContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(200);
				match(NULL);
				}
				break;
			case INT:
				_localctx = new NumericValueContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(201);
				match(INT);
				}
				break;
			case STRING:
				_localctx = new StringValueContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(202);
				match(STRING);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 0:
			return assertion_sempred((AssertionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean assertion_sempred(AssertionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 19);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3#\u00d0\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2A\n\2\3\2\3\2\3"+
		"\2\7\2F\n\2\f\2\16\2I\13\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\13\3\13\3\13"+
		"\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\7\16\u0085\n"+
		"\16\f\16\16\16\u0088\13\16\3\16\3\16\3\17\3\17\3\17\3\17\7\17\u0090\n"+
		"\17\f\17\16\17\u0093\13\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u00a5\n\20\3\21\3\21\3\22\3\22"+
		"\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\7\23\u00b5\n\23\f\23"+
		"\16\23\u00b8\13\23\3\23\3\23\3\23\3\23\5\23\u00be\n\23\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\26\3\26\3\26\5\26\u00ce\n\26"+
		"\3\26\2\3\2\27\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*\2\2\2\u00d4"+
		"\2@\3\2\2\2\4J\3\2\2\2\6N\3\2\2\2\bT\3\2\2\2\nZ\3\2\2\2\f`\3\2\2\2\16"+
		"f\3\2\2\2\20l\3\2\2\2\22p\3\2\2\2\24t\3\2\2\2\26x\3\2\2\2\30|\3\2\2\2"+
		"\32\u0080\3\2\2\2\34\u008b\3\2\2\2\36\u00a4\3\2\2\2 \u00a6\3\2\2\2\"\u00a8"+
		"\3\2\2\2$\u00bd\3\2\2\2&\u00bf\3\2\2\2(\u00c6\3\2\2\2*\u00cd\3\2\2\2,"+
		"-\b\2\1\2-A\5\4\3\2.A\5\6\4\2/A\5\22\n\2\60A\5\b\5\2\61A\5\f\7\2\62A\5"+
		"\n\6\2\63A\5\16\b\2\64A\5\24\13\2\65A\5\30\r\2\66A\5\26\f\2\67A\5\32\16"+
		"\28A\5\36\20\29A\5\20\t\2:A\5\34\17\2;A\5 \21\2<A\5\"\22\2=A\5&\24\2>"+
		"A\5(\25\2?A\5$\23\2@,\3\2\2\2@.\3\2\2\2@/\3\2\2\2@\60\3\2\2\2@\61\3\2"+
		"\2\2@\62\3\2\2\2@\63\3\2\2\2@\64\3\2\2\2@\65\3\2\2\2@\66\3\2\2\2@\67\3"+
		"\2\2\2@8\3\2\2\2@9\3\2\2\2@:\3\2\2\2@;\3\2\2\2@<\3\2\2\2@=\3\2\2\2@>\3"+
		"\2\2\2@?\3\2\2\2AG\3\2\2\2BC\f\25\2\2CD\7\3\2\2DF\5\2\2\26EB\3\2\2\2F"+
		"I\3\2\2\2GE\3\2\2\2GH\3\2\2\2H\3\3\2\2\2IG\3\2\2\2JK\7\4\2\2KL\7\37\2"+
		"\2LM\7\5\2\2M\5\3\2\2\2NO\7\6\2\2OP\5*\26\2PQ\7\3\2\2QR\5*\26\2RS\7\7"+
		"\2\2S\7\3\2\2\2TU\7\b\2\2UV\5*\26\2VW\7\3\2\2WX\5*\26\2XY\7\7\2\2Y\t\3"+
		"\2\2\2Z[\7\t\2\2[\\\5*\26\2\\]\7\3\2\2]^\5*\26\2^_\7\7\2\2_\13\3\2\2\2"+
		"`a\7\n\2\2ab\5*\26\2bc\7\3\2\2cd\5*\26\2de\7\7\2\2e\r\3\2\2\2fg\7\13\2"+
		"\2gh\5*\26\2hi\7\3\2\2ij\5*\26\2jk\7\7\2\2k\17\3\2\2\2lm\7\f\2\2mn\5*"+
		"\26\2no\7\7\2\2o\21\3\2\2\2pq\7\r\2\2qr\5\2\2\2rs\7\5\2\2s\23\3\2\2\2"+
		"tu\7\16\2\2uv\5\2\2\2vw\7\5\2\2w\25\3\2\2\2xy\7\17\2\2yz\5\2\2\2z{\7\5"+
		"\2\2{\27\3\2\2\2|}\7\20\2\2}~\5\2\2\2~\177\7\5\2\2\177\31\3\2\2\2\u0080"+
		"\u0081\7\21\2\2\u0081\u0086\7#\2\2\u0082\u0083\7\3\2\2\u0083\u0085\7#"+
		"\2\2\u0084\u0082\3\2\2\2\u0085\u0088\3\2\2\2\u0086\u0084\3\2\2\2\u0086"+
		"\u0087\3\2\2\2\u0087\u0089\3\2\2\2\u0088\u0086\3\2\2\2\u0089\u008a\7\22"+
		"\2\2\u008a\33\3\2\2\2\u008b\u008c\7\23\2\2\u008c\u0091\5*\26\2\u008d\u008e"+
		"\7\3\2\2\u008e\u0090\5*\26\2\u008f\u008d\3\2\2\2\u0090\u0093\3\2\2\2\u0091"+
		"\u008f\3\2\2\2\u0091\u0092\3\2\2\2\u0092\u0094\3\2\2\2\u0093\u0091\3\2"+
		"\2\2\u0094\u0095\7\5\2\2\u0095\35\3\2\2\2\u0096\u0097\7\24\2\2\u0097\u0098"+
		"\5\2\2\2\u0098\u0099\7\25\2\2\u0099\u009a\5\2\2\2\u009a\u009b\7\26\2\2"+
		"\u009b\u009c\5\2\2\2\u009c\u009d\7\5\2\2\u009d\u00a5\3\2\2\2\u009e\u009f"+
		"\7\24\2\2\u009f\u00a0\5\2\2\2\u00a0\u00a1\7\25\2\2\u00a1\u00a2\5\2\2\2"+
		"\u00a2\u00a3\7\5\2\2\u00a3\u00a5\3\2\2\2\u00a4\u0096\3\2\2\2\u00a4\u009e"+
		"\3\2\2\2\u00a5\37\3\2\2\2\u00a6\u00a7\7\27\2\2\u00a7!\3\2\2\2\u00a8\u00a9"+
		"\7\30\2\2\u00a9\u00aa\7#\2\2\u00aa\u00ab\7\5\2\2\u00ab#\3\2\2\2\u00ac"+
		"\u00ad\7\31\2\2\u00ad\u00ae\5\2\2\2\u00ae\u00af\7\5\2\2\u00af\u00be\3"+
		"\2\2\2\u00b0\u00b1\7\32\2\2\u00b1\u00b6\5\2\2\2\u00b2\u00b3\7\33\2\2\u00b3"+
		"\u00b5\5\2\2\2\u00b4\u00b2\3\2\2\2\u00b5\u00b8\3\2\2\2\u00b6\u00b4\3\2"+
		"\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00b9\3\2\2\2\u00b8\u00b6\3\2\2\2\u00b9"+
		"\u00ba\7\34\2\2\u00ba\u00bb\5\2\2\2\u00bb\u00bc\7\5\2\2\u00bc\u00be\3"+
		"\2\2\2\u00bd\u00ac\3\2\2\2\u00bd\u00b0\3\2\2\2\u00be%\3\2\2\2\u00bf\u00c0"+
		"\7\35\2\2\u00c0\u00c1\5*\26\2\u00c1\u00c2\7\3\2\2\u00c2\u00c3\5*\26\2"+
		"\u00c3\u00c4\7\7\2\2\u00c4\u00c5\5\2\2\2\u00c5\'\3\2\2\2\u00c6\u00c7\7"+
		"\36\2\2\u00c7\u00c8\5*\26\2\u00c8\u00c9\7\5\2\2\u00c9)\3\2\2\2\u00ca\u00ce"+
		"\7 \2\2\u00cb\u00ce\7!\2\2\u00cc\u00ce\7#\2\2\u00cd\u00ca\3\2\2\2\u00cd"+
		"\u00cb\3\2\2\2\u00cd\u00cc\3\2\2\2\u00ce+\3\2\2\2\n@G\u0086\u0091\u00a4"+
		"\u00b6\u00bd\u00cd";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}