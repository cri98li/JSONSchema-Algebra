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
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, JSON_VALUE=31, 
		INT=32, ID=33, WS=34, STRING=35;
	public static final int
		RULE_assertion_list = 0, RULE_assertion = 1, RULE_numeric_value = 2, RULE_type_assertion = 3, 
		RULE_between_assertion = 4, RULE_xbetween_assertion = 5, RULE_length_assertion = 6, 
		RULE_bet_items_assertion = 7, RULE_between_properties_assertion = 8, RULE_multiple_of_assertion = 9, 
		RULE_all_of_assertion = 10, RULE_one_of_assertion = 11, RULE_any_of_assertion = 12, 
		RULE_not_assertion = 13, RULE_required_assertion = 14, RULE_unique_items_assertion = 15, 
		RULE_pattern_assertion = 16, RULE_items_assertion = 17, RULE_contains_assertion = 18, 
		RULE_enum_assertion_assertion = 19, RULE_if_then_else_assertion = 20, 
		RULE_const_assertion = 21;
	private static String[] makeRuleNames() {
		return new String[] {
			"assertion_list", "assertion", "numeric_value", "type_assertion", "between_assertion", 
			"xbetween_assertion", "length_assertion", "bet_items_assertion", "between_properties_assertion", 
			"multiple_of_assertion", "all_of_assertion", "one_of_assertion", "any_of_assertion", 
			"not_assertion", "required_assertion", "unique_items_assertion", "pattern_assertion", 
			"items_assertion", "contains_assertion", "enum_assertion_assertion", 
			"if_then_else_assertion", "const_assertion"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'{'", "','", "'}'", "'null'", "'bet<'", "'>'", "'xbet<'", "'length<'", 
			"'betitems<'", "'pro<'", "'mof<'", "'_AND('", "')'", "'_XOR('", "'_OR('", 
			"'_NOT('", "'req(['", "'])'", "'uniqueItems'", "'pattern('", "'items(;'", 
			"'items('", "'*'", "';'", "'contains<'", "'> '", "'enum('", "'=>'", "'|'", 
			"'const('"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, "JSON_VALUE", "INT", "ID", 
			"WS", "STRING"
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
		public List<AssertionContext> assertion() {
			return getRuleContexts(AssertionContext.class);
		}
		public AssertionContext assertion(int i) {
			return getRuleContext(AssertionContext.class,i);
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
			setState(44);
			match(T__0);
			setState(45);
			assertion();
			setState(50);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(46);
				match(T__1);
				setState(47);
				assertion();
				}
				}
				setState(52);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(53);
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
	public static class MultipleOfContext extends AssertionContext {
		public Multiple_of_assertionContext multiple_of_assertion() {
			return getRuleContext(Multiple_of_assertionContext.class,0);
		}
		public MultipleOfContext(AssertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitMultipleOf(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EnumContext extends AssertionContext {
		public Enum_assertion_assertionContext enum_assertion_assertion() {
			return getRuleContext(Enum_assertion_assertionContext.class,0);
		}
		public EnumContext(AssertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitEnum(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PatternContext extends AssertionContext {
		public Pattern_assertionContext pattern_assertion() {
			return getRuleContext(Pattern_assertionContext.class,0);
		}
		public PatternContext(AssertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitPattern(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BetweenAssertionContext extends AssertionContext {
		public Between_assertionContext between_assertion() {
			return getRuleContext(Between_assertionContext.class,0);
		}
		public BetweenAssertionContext(AssertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitBetweenAssertion(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IfThenElseContext extends AssertionContext {
		public If_then_else_assertionContext if_then_else_assertion() {
			return getRuleContext(If_then_else_assertionContext.class,0);
		}
		public IfThenElseContext(AssertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitIfThenElse(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ConstContext extends AssertionContext {
		public Const_assertionContext const_assertion() {
			return getRuleContext(Const_assertionContext.class,0);
		}
		public ConstContext(AssertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitConst(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NewListContext extends AssertionContext {
		public Assertion_listContext assertion_list() {
			return getRuleContext(Assertion_listContext.class,0);
		}
		public NewListContext(AssertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitNewList(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AnyOfContext extends AssertionContext {
		public Any_of_assertionContext any_of_assertion() {
			return getRuleContext(Any_of_assertionContext.class,0);
		}
		public AnyOfContext(AssertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitAnyOf(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UniqueItemsContext extends AssertionContext {
		public Unique_items_assertionContext unique_items_assertion() {
			return getRuleContext(Unique_items_assertionContext.class,0);
		}
		public UniqueItemsContext(AssertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitUniqueItems(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TypeAssertionContext extends AssertionContext {
		public Type_assertionContext type_assertion() {
			return getRuleContext(Type_assertionContext.class,0);
		}
		public TypeAssertionContext(AssertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitTypeAssertion(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class XBetweenAssertionContext extends AssertionContext {
		public Xbetween_assertionContext xbetween_assertion() {
			return getRuleContext(Xbetween_assertionContext.class,0);
		}
		public XBetweenAssertionContext(AssertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitXBetweenAssertion(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AllOfContext extends AssertionContext {
		public All_of_assertionContext all_of_assertion() {
			return getRuleContext(All_of_assertionContext.class,0);
		}
		public AllOfContext(AssertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitAllOf(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OneOfContext extends AssertionContext {
		public One_of_assertionContext one_of_assertion() {
			return getRuleContext(One_of_assertionContext.class,0);
		}
		public OneOfContext(AssertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitOneOf(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NotContext extends AssertionContext {
		public Not_assertionContext not_assertion() {
			return getRuleContext(Not_assertionContext.class,0);
		}
		public NotContext(AssertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitNot(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BetweenPropertiesContext extends AssertionContext {
		public Between_properties_assertionContext between_properties_assertion() {
			return getRuleContext(Between_properties_assertionContext.class,0);
		}
		public BetweenPropertiesContext(AssertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitBetweenProperties(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RequiredContext extends AssertionContext {
		public Required_assertionContext required_assertion() {
			return getRuleContext(Required_assertionContext.class,0);
		}
		public RequiredContext(AssertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitRequired(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LengthContext extends AssertionContext {
		public Length_assertionContext length_assertion() {
			return getRuleContext(Length_assertionContext.class,0);
		}
		public LengthContext(AssertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitLength(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BetweenItemsContext extends AssertionContext {
		public Bet_items_assertionContext bet_items_assertion() {
			return getRuleContext(Bet_items_assertionContext.class,0);
		}
		public BetweenItemsContext(AssertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitBetweenItems(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ContainsContext extends AssertionContext {
		public Contains_assertionContext contains_assertion() {
			return getRuleContext(Contains_assertionContext.class,0);
		}
		public ContainsContext(AssertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitContains(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ItemsContext extends AssertionContext {
		public Items_assertionContext items_assertion() {
			return getRuleContext(Items_assertionContext.class,0);
		}
		public ItemsContext(AssertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitItems(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssertionContext assertion() throws RecognitionException {
		AssertionContext _localctx = new AssertionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_assertion);
		try {
			setState(75);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				_localctx = new TypeAssertionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(55);
				type_assertion();
				}
				break;
			case 2:
				_localctx = new NewListContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(56);
				assertion_list();
				}
				break;
			case 3:
				_localctx = new BetweenAssertionContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(57);
				between_assertion();
				}
				break;
			case 4:
				_localctx = new XBetweenAssertionContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(58);
				xbetween_assertion();
				}
				break;
			case 5:
				_localctx = new BetweenItemsContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(59);
				bet_items_assertion();
				}
				break;
			case 6:
				_localctx = new LengthContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(60);
				length_assertion();
				}
				break;
			case 7:
				_localctx = new BetweenPropertiesContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(61);
				between_properties_assertion();
				}
				break;
			case 8:
				_localctx = new MultipleOfContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(62);
				multiple_of_assertion();
				}
				break;
			case 9:
				_localctx = new AllOfContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(63);
				all_of_assertion();
				}
				break;
			case 10:
				_localctx = new AnyOfContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(64);
				any_of_assertion();
				}
				break;
			case 11:
				_localctx = new OneOfContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(65);
				one_of_assertion();
				}
				break;
			case 12:
				_localctx = new NotContext(_localctx);
				enterOuterAlt(_localctx, 12);
				{
				setState(66);
				not_assertion();
				}
				break;
			case 13:
				_localctx = new RequiredContext(_localctx);
				enterOuterAlt(_localctx, 13);
				{
				setState(67);
				required_assertion();
				}
				break;
			case 14:
				_localctx = new UniqueItemsContext(_localctx);
				enterOuterAlt(_localctx, 14);
				{
				setState(68);
				unique_items_assertion();
				}
				break;
			case 15:
				_localctx = new PatternContext(_localctx);
				enterOuterAlt(_localctx, 15);
				{
				setState(69);
				pattern_assertion();
				}
				break;
			case 16:
				_localctx = new ItemsContext(_localctx);
				enterOuterAlt(_localctx, 16);
				{
				setState(70);
				items_assertion();
				}
				break;
			case 17:
				_localctx = new ContainsContext(_localctx);
				enterOuterAlt(_localctx, 17);
				{
				setState(71);
				contains_assertion();
				}
				break;
			case 18:
				_localctx = new EnumContext(_localctx);
				enterOuterAlt(_localctx, 18);
				{
				setState(72);
				enum_assertion_assertion();
				}
				break;
			case 19:
				_localctx = new IfThenElseContext(_localctx);
				enterOuterAlt(_localctx, 19);
				{
				setState(73);
				if_then_else_assertion();
				}
				break;
			case 20:
				_localctx = new ConstContext(_localctx);
				enterOuterAlt(_localctx, 20);
				{
				setState(74);
				const_assertion();
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

	public static class Numeric_valueContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(GrammaticaParser.INT, 0); }
		public Numeric_valueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numeric_value; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitNumeric_value(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Numeric_valueContext numeric_value() throws RecognitionException {
		Numeric_valueContext _localctx = new Numeric_valueContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_numeric_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			_la = _input.LA(1);
			if ( !(_la==T__3 || _la==INT) ) {
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

	public static class Type_assertionContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(GrammaticaParser.ID, 0); }
		public Type_assertionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_assertion; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitType_assertion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Type_assertionContext type_assertion() throws RecognitionException {
		Type_assertionContext _localctx = new Type_assertionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_type_assertion);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			match(ID);
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
		public List<Numeric_valueContext> numeric_value() {
			return getRuleContexts(Numeric_valueContext.class);
		}
		public Numeric_valueContext numeric_value(int i) {
			return getRuleContext(Numeric_valueContext.class,i);
		}
		public Between_assertionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_between_assertion; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitBetween_assertion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Between_assertionContext between_assertion() throws RecognitionException {
		Between_assertionContext _localctx = new Between_assertionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_between_assertion);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(81);
			match(T__4);
			setState(82);
			numeric_value();
			setState(83);
			match(T__1);
			setState(84);
			numeric_value();
			setState(85);
			match(T__5);
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
		public List<Numeric_valueContext> numeric_value() {
			return getRuleContexts(Numeric_valueContext.class);
		}
		public Numeric_valueContext numeric_value(int i) {
			return getRuleContext(Numeric_valueContext.class,i);
		}
		public Xbetween_assertionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_xbetween_assertion; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitXbetween_assertion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Xbetween_assertionContext xbetween_assertion() throws RecognitionException {
		Xbetween_assertionContext _localctx = new Xbetween_assertionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_xbetween_assertion);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			match(T__6);
			setState(88);
			numeric_value();
			setState(89);
			match(T__1);
			setState(90);
			numeric_value();
			setState(91);
			match(T__5);
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
		public List<Numeric_valueContext> numeric_value() {
			return getRuleContexts(Numeric_valueContext.class);
		}
		public Numeric_valueContext numeric_value(int i) {
			return getRuleContext(Numeric_valueContext.class,i);
		}
		public Length_assertionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_length_assertion; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitLength_assertion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Length_assertionContext length_assertion() throws RecognitionException {
		Length_assertionContext _localctx = new Length_assertionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_length_assertion);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			match(T__7);
			setState(94);
			numeric_value();
			setState(95);
			match(T__1);
			setState(96);
			numeric_value();
			setState(97);
			match(T__5);
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
		public List<Numeric_valueContext> numeric_value() {
			return getRuleContexts(Numeric_valueContext.class);
		}
		public Numeric_valueContext numeric_value(int i) {
			return getRuleContext(Numeric_valueContext.class,i);
		}
		public Bet_items_assertionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bet_items_assertion; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitBet_items_assertion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Bet_items_assertionContext bet_items_assertion() throws RecognitionException {
		Bet_items_assertionContext _localctx = new Bet_items_assertionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_bet_items_assertion);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			match(T__8);
			setState(100);
			numeric_value();
			setState(101);
			match(T__1);
			setState(102);
			numeric_value();
			setState(103);
			match(T__5);
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
		public List<Numeric_valueContext> numeric_value() {
			return getRuleContexts(Numeric_valueContext.class);
		}
		public Numeric_valueContext numeric_value(int i) {
			return getRuleContext(Numeric_valueContext.class,i);
		}
		public Between_properties_assertionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_between_properties_assertion; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitBetween_properties_assertion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Between_properties_assertionContext between_properties_assertion() throws RecognitionException {
		Between_properties_assertionContext _localctx = new Between_properties_assertionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_between_properties_assertion);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			match(T__9);
			setState(106);
			numeric_value();
			setState(107);
			match(T__1);
			setState(108);
			numeric_value();
			setState(109);
			match(T__5);
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
		public Numeric_valueContext numeric_value() {
			return getRuleContext(Numeric_valueContext.class,0);
		}
		public Multiple_of_assertionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiple_of_assertion; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitMultiple_of_assertion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Multiple_of_assertionContext multiple_of_assertion() throws RecognitionException {
		Multiple_of_assertionContext _localctx = new Multiple_of_assertionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_multiple_of_assertion);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(111);
			match(T__10);
			setState(112);
			numeric_value();
			setState(113);
			match(T__5);
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
		public Assertion_listContext assertion_list() {
			return getRuleContext(Assertion_listContext.class,0);
		}
		public All_of_assertionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_all_of_assertion; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitAll_of_assertion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final All_of_assertionContext all_of_assertion() throws RecognitionException {
		All_of_assertionContext _localctx = new All_of_assertionContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_all_of_assertion);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115);
			match(T__11);
			setState(116);
			assertion_list();
			setState(117);
			match(T__12);
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
		public Assertion_listContext assertion_list() {
			return getRuleContext(Assertion_listContext.class,0);
		}
		public One_of_assertionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_one_of_assertion; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitOne_of_assertion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final One_of_assertionContext one_of_assertion() throws RecognitionException {
		One_of_assertionContext _localctx = new One_of_assertionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_one_of_assertion);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			match(T__13);
			setState(120);
			assertion_list();
			setState(121);
			match(T__12);
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
		public Assertion_listContext assertion_list() {
			return getRuleContext(Assertion_listContext.class,0);
		}
		public Any_of_assertionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_any_of_assertion; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitAny_of_assertion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Any_of_assertionContext any_of_assertion() throws RecognitionException {
		Any_of_assertionContext _localctx = new Any_of_assertionContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_any_of_assertion);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123);
			match(T__14);
			setState(124);
			assertion_list();
			setState(125);
			match(T__12);
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
		public Assertion_listContext assertion_list() {
			return getRuleContext(Assertion_listContext.class,0);
		}
		public Not_assertionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_not_assertion; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitNot_assertion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Not_assertionContext not_assertion() throws RecognitionException {
		Not_assertionContext _localctx = new Not_assertionContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_not_assertion);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			match(T__15);
			setState(128);
			assertion_list();
			setState(129);
			match(T__12);
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
		public List<TerminalNode> ID() { return getTokens(GrammaticaParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(GrammaticaParser.ID, i);
		}
		public Required_assertionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_required_assertion; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitRequired_assertion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Required_assertionContext required_assertion() throws RecognitionException {
		Required_assertionContext _localctx = new Required_assertionContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_required_assertion);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131);
			match(T__16);
			setState(132);
			match(ID);
			setState(137);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(133);
				match(T__1);
				setState(134);
				match(ID);
				}
				}
				setState(139);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(140);
			match(T__17);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitUnique_items_assertion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Unique_items_assertionContext unique_items_assertion() throws RecognitionException {
		Unique_items_assertionContext _localctx = new Unique_items_assertionContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_unique_items_assertion);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(142);
			match(T__18);
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
		public TerminalNode ID() { return getToken(GrammaticaParser.ID, 0); }
		public Pattern_assertionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pattern_assertion; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitPattern_assertion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Pattern_assertionContext pattern_assertion() throws RecognitionException {
		Pattern_assertionContext _localctx = new Pattern_assertionContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_pattern_assertion);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(144);
			match(T__19);
			setState(145);
			match(ID);
			setState(146);
			match(T__12);
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
		public List<Assertion_listContext> assertion_list() {
			return getRuleContexts(Assertion_listContext.class);
		}
		public Assertion_listContext assertion_list(int i) {
			return getRuleContext(Assertion_listContext.class,i);
		}
		public Items_assertionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_items_assertion; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitItems_assertion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Items_assertionContext items_assertion() throws RecognitionException {
		Items_assertionContext _localctx = new Items_assertionContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_items_assertion);
		int _la;
		try {
			setState(165);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__20:
				enterOuterAlt(_localctx, 1);
				{
				setState(148);
				match(T__20);
				setState(149);
				assertion_list();
				setState(150);
				match(T__12);
				}
				break;
			case T__21:
				enterOuterAlt(_localctx, 2);
				{
				setState(152);
				match(T__21);
				setState(153);
				assertion_list();
				setState(158);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__22) {
					{
					{
					setState(154);
					match(T__22);
					setState(155);
					assertion_list();
					}
					}
					setState(160);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(161);
				match(T__23);
				setState(162);
				assertion_list();
				setState(163);
				match(T__12);
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
		public List<Numeric_valueContext> numeric_value() {
			return getRuleContexts(Numeric_valueContext.class);
		}
		public Numeric_valueContext numeric_value(int i) {
			return getRuleContext(Numeric_valueContext.class,i);
		}
		public Assertion_listContext assertion_list() {
			return getRuleContext(Assertion_listContext.class,0);
		}
		public Contains_assertionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_contains_assertion; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitContains_assertion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Contains_assertionContext contains_assertion() throws RecognitionException {
		Contains_assertionContext _localctx = new Contains_assertionContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_contains_assertion);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(167);
			match(T__24);
			setState(168);
			numeric_value();
			setState(169);
			match(T__1);
			setState(170);
			numeric_value();
			setState(171);
			match(T__25);
			setState(172);
			assertion_list();
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
		public List<TerminalNode> ID() { return getTokens(GrammaticaParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(GrammaticaParser.ID, i);
		}
		public Enum_assertion_assertionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enum_assertion_assertion; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitEnum_assertion_assertion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Enum_assertion_assertionContext enum_assertion_assertion() throws RecognitionException {
		Enum_assertion_assertionContext _localctx = new Enum_assertion_assertionContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_enum_assertion_assertion);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(174);
			match(T__26);
			setState(175);
			match(ID);
			setState(180);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(176);
				match(T__1);
				setState(177);
				match(ID);
				}
				}
				setState(182);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(183);
			match(T__12);
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
		public List<Assertion_listContext> assertion_list() {
			return getRuleContexts(Assertion_listContext.class);
		}
		public Assertion_listContext assertion_list(int i) {
			return getRuleContext(Assertion_listContext.class,i);
		}
		public If_then_else_assertionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_then_else_assertion; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitIf_then_else_assertion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final If_then_else_assertionContext if_then_else_assertion() throws RecognitionException {
		If_then_else_assertionContext _localctx = new If_then_else_assertionContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_if_then_else_assertion);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(185);
			assertion_list();
			setState(186);
			match(T__27);
			setState(187);
			assertion_list();
			setState(188);
			match(T__28);
			setState(189);
			assertion_list();
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
		public TerminalNode JSON_VALUE() { return getToken(GrammaticaParser.JSON_VALUE, 0); }
		public Const_assertionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_const_assertion; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitConst_assertion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Const_assertionContext const_assertion() throws RecognitionException {
		Const_assertionContext _localctx = new Const_assertionContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_const_assertion);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(191);
			match(T__29);
			setState(192);
			match(JSON_VALUE);
			setState(193);
			match(T__12);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3%\u00c6\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\3\2\3\2\3\2\3\2\7\2"+
		"\63\n\2\f\2\16\2\66\13\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3N\n\3\3\4\3\4\3\5\3\5\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3"+
		"\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17"+
		"\3\20\3\20\3\20\3\20\7\20\u008a\n\20\f\20\16\20\u008d\13\20\3\20\3\20"+
		"\3\21\3\21\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\7\23\u009f\n\23\f\23\16\23\u00a2\13\23\3\23\3\23\3\23\3\23\5\23\u00a8"+
		"\n\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\7\25\u00b5"+
		"\n\25\f\25\16\25\u00b8\13\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3"+
		"\27\3\27\3\27\3\27\3\27\2\2\30\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36"+
		" \"$&(*,\2\3\4\2\6\6\"\"\2\u00c7\2.\3\2\2\2\4M\3\2\2\2\6O\3\2\2\2\bQ\3"+
		"\2\2\2\nS\3\2\2\2\fY\3\2\2\2\16_\3\2\2\2\20e\3\2\2\2\22k\3\2\2\2\24q\3"+
		"\2\2\2\26u\3\2\2\2\30y\3\2\2\2\32}\3\2\2\2\34\u0081\3\2\2\2\36\u0085\3"+
		"\2\2\2 \u0090\3\2\2\2\"\u0092\3\2\2\2$\u00a7\3\2\2\2&\u00a9\3\2\2\2(\u00b0"+
		"\3\2\2\2*\u00bb\3\2\2\2,\u00c1\3\2\2\2./\7\3\2\2/\64\5\4\3\2\60\61\7\4"+
		"\2\2\61\63\5\4\3\2\62\60\3\2\2\2\63\66\3\2\2\2\64\62\3\2\2\2\64\65\3\2"+
		"\2\2\65\67\3\2\2\2\66\64\3\2\2\2\678\7\5\2\28\3\3\2\2\29N\5\b\5\2:N\5"+
		"\2\2\2;N\5\n\6\2<N\5\f\7\2=N\5\20\t\2>N\5\16\b\2?N\5\22\n\2@N\5\24\13"+
		"\2AN\5\26\f\2BN\5\32\16\2CN\5\30\r\2DN\5\34\17\2EN\5\36\20\2FN\5 \21\2"+
		"GN\5\"\22\2HN\5$\23\2IN\5&\24\2JN\5(\25\2KN\5*\26\2LN\5,\27\2M9\3\2\2"+
		"\2M:\3\2\2\2M;\3\2\2\2M<\3\2\2\2M=\3\2\2\2M>\3\2\2\2M?\3\2\2\2M@\3\2\2"+
		"\2MA\3\2\2\2MB\3\2\2\2MC\3\2\2\2MD\3\2\2\2ME\3\2\2\2MF\3\2\2\2MG\3\2\2"+
		"\2MH\3\2\2\2MI\3\2\2\2MJ\3\2\2\2MK\3\2\2\2ML\3\2\2\2N\5\3\2\2\2OP\t\2"+
		"\2\2P\7\3\2\2\2QR\7#\2\2R\t\3\2\2\2ST\7\7\2\2TU\5\6\4\2UV\7\4\2\2VW\5"+
		"\6\4\2WX\7\b\2\2X\13\3\2\2\2YZ\7\t\2\2Z[\5\6\4\2[\\\7\4\2\2\\]\5\6\4\2"+
		"]^\7\b\2\2^\r\3\2\2\2_`\7\n\2\2`a\5\6\4\2ab\7\4\2\2bc\5\6\4\2cd\7\b\2"+
		"\2d\17\3\2\2\2ef\7\13\2\2fg\5\6\4\2gh\7\4\2\2hi\5\6\4\2ij\7\b\2\2j\21"+
		"\3\2\2\2kl\7\f\2\2lm\5\6\4\2mn\7\4\2\2no\5\6\4\2op\7\b\2\2p\23\3\2\2\2"+
		"qr\7\r\2\2rs\5\6\4\2st\7\b\2\2t\25\3\2\2\2uv\7\16\2\2vw\5\2\2\2wx\7\17"+
		"\2\2x\27\3\2\2\2yz\7\20\2\2z{\5\2\2\2{|\7\17\2\2|\31\3\2\2\2}~\7\21\2"+
		"\2~\177\5\2\2\2\177\u0080\7\17\2\2\u0080\33\3\2\2\2\u0081\u0082\7\22\2"+
		"\2\u0082\u0083\5\2\2\2\u0083\u0084\7\17\2\2\u0084\35\3\2\2\2\u0085\u0086"+
		"\7\23\2\2\u0086\u008b\7#\2\2\u0087\u0088\7\4\2\2\u0088\u008a\7#\2\2\u0089"+
		"\u0087\3\2\2\2\u008a\u008d\3\2\2\2\u008b\u0089\3\2\2\2\u008b\u008c\3\2"+
		"\2\2\u008c\u008e\3\2\2\2\u008d\u008b\3\2\2\2\u008e\u008f\7\24\2\2\u008f"+
		"\37\3\2\2\2\u0090\u0091\7\25\2\2\u0091!\3\2\2\2\u0092\u0093\7\26\2\2\u0093"+
		"\u0094\7#\2\2\u0094\u0095\7\17\2\2\u0095#\3\2\2\2\u0096\u0097\7\27\2\2"+
		"\u0097\u0098\5\2\2\2\u0098\u0099\7\17\2\2\u0099\u00a8\3\2\2\2\u009a\u009b"+
		"\7\30\2\2\u009b\u00a0\5\2\2\2\u009c\u009d\7\31\2\2\u009d\u009f\5\2\2\2"+
		"\u009e\u009c\3\2\2\2\u009f\u00a2\3\2\2\2\u00a0\u009e\3\2\2\2\u00a0\u00a1"+
		"\3\2\2\2\u00a1\u00a3\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a3\u00a4\7\32\2\2"+
		"\u00a4\u00a5\5\2\2\2\u00a5\u00a6\7\17\2\2\u00a6\u00a8\3\2\2\2\u00a7\u0096"+
		"\3\2\2\2\u00a7\u009a\3\2\2\2\u00a8%\3\2\2\2\u00a9\u00aa\7\33\2\2\u00aa"+
		"\u00ab\5\6\4\2\u00ab\u00ac\7\4\2\2\u00ac\u00ad\5\6\4\2\u00ad\u00ae\7\34"+
		"\2\2\u00ae\u00af\5\2\2\2\u00af\'\3\2\2\2\u00b0\u00b1\7\35\2\2\u00b1\u00b6"+
		"\7#\2\2\u00b2\u00b3\7\4\2\2\u00b3\u00b5\7#\2\2\u00b4\u00b2\3\2\2\2\u00b5"+
		"\u00b8\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00b9\3\2"+
		"\2\2\u00b8\u00b6\3\2\2\2\u00b9\u00ba\7\17\2\2\u00ba)\3\2\2\2\u00bb\u00bc"+
		"\5\2\2\2\u00bc\u00bd\7\36\2\2\u00bd\u00be\5\2\2\2\u00be\u00bf\7\37\2\2"+
		"\u00bf\u00c0\5\2\2\2\u00c0+\3\2\2\2\u00c1\u00c2\7 \2\2\u00c2\u00c3\7!"+
		"\2\2\u00c3\u00c4\7\17\2\2\u00c4-\3\2\2\2\b\64M\u008b\u00a0\u00a7\u00b6";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}