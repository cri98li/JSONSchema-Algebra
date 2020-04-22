// Generated from Grammatica.g4 by ANTLR 4.8
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
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, T__40=41, T__41=42, NULL=43, TYPE=44, INT=45, DOUBLE=46, 
		WS=47, STRING=48, BOOLEAN=49;
	public static final int
		RULE_assertion_list = 0, RULE_assertion = 1, RULE_type_assertion = 2, 
		RULE_between_assertion = 3, RULE_xbetween_assertion = 4, RULE_length_assertion = 5, 
		RULE_bet_items_assertion = 6, RULE_between_properties_assertion = 7, RULE_multiple_of_assertion = 8, 
		RULE_not_multiple_of_assertion = 9, RULE_not_assertion = 10, RULE_all_of_assertion = 11, 
		RULE_one_of_assertion = 12, RULE_any_of_assertion = 13, RULE_required_assertion = 14, 
		RULE_enum_assertion_assertion = 15, RULE_if_then_else_assertion = 16, 
		RULE_unique_items_assertion = 17, RULE_repeated_items_assertion = 18, 
		RULE_pattern_assertion = 19, RULE_not_pattern_assertion = 20, RULE_items_assertion = 21, 
		RULE_contains_assertion = 22, RULE_properties = 23, RULE_const_assertion = 24, 
		RULE_def_assertion = 25, RULE_ref_assertion = 26, RULE_propertyNames_assertion = 27, 
		RULE_propertyExNames_assertion = 28, RULE_annotations = 29, RULE_pattern_required = 30, 
		RULE_additional_pattern_required = 31, RULE_json_value = 32;
	private static String[] makeRuleNames() {
		return new String[] {
			"assertion_list", "assertion", "type_assertion", "between_assertion", 
			"xbetween_assertion", "length_assertion", "bet_items_assertion", "between_properties_assertion", 
			"multiple_of_assertion", "not_multiple_of_assertion", "not_assertion", 
			"all_of_assertion", "one_of_assertion", "any_of_assertion", "required_assertion", 
			"enum_assertion_assertion", "if_then_else_assertion", "unique_items_assertion", 
			"repeated_items_assertion", "pattern_assertion", "not_pattern_assertion", 
			"items_assertion", "contains_assertion", "properties", "const_assertion", 
			"def_assertion", "ref_assertion", "propertyNames_assertion", "propertyExNames_assertion", 
			"annotations", "pattern_required", "additional_pattern_required", "json_value"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'{'", "','", "'}'", "'type'", "'('", "')'", "'bet'", "'xbet'", 
			"'length'", "'betItems'", "'pro'", "'mof'", "'notMof'", "'not'", "'allOf'", 
			"'['", "']'", "'oneOf'", "'anyOf'", "'req'", "'enum['", "'ifThenElse'", 
			"';'", "'ifThen'", "'uniqueItems'", "'repeatedItems'", "'pattern'", "'notPattern'", 
			"'items'", "'contains'", "'props'", "':'", "'const'", "'def'", "'='", 
			"'rootdef'", "'ref'", "'names'", "'exNames'", "'annotations'", "'pattReq'", 
			"'addPattReq'", "'null'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, "NULL", "TYPE", "INT", "DOUBLE", 
			"WS", "STRING", "BOOLEAN"
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
	public static class ParseAssertionListContext extends Assertion_listContext {
		public List<AssertionContext> assertion() {
			return getRuleContexts(AssertionContext.class);
		}
		public AssertionContext assertion(int i) {
			return getRuleContext(AssertionContext.class,i);
		}
		public ParseAssertionListContext(Assertion_listContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitParseAssertionList(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParseBooleanSchemaContext extends Assertion_listContext {
		public TerminalNode BOOLEAN() { return getToken(GrammaticaParser.BOOLEAN, 0); }
		public ParseBooleanSchemaContext(Assertion_listContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitParseBooleanSchema(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Assertion_listContext assertion_list() throws RecognitionException {
		Assertion_listContext _localctx = new Assertion_listContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_assertion_list);
		int _la;
		try {
			setState(78);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				_localctx = new ParseAssertionListContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(66);
				match(T__0);
				setState(67);
				assertion();
				setState(72);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(68);
					match(T__1);
					setState(69);
					assertion();
					}
					}
					setState(74);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(75);
				match(T__2);
				}
				break;
			case BOOLEAN:
				_localctx = new ParseBooleanSchemaContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(77);
				match(BOOLEAN);
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
	public static class NewBetweenItemsContext extends AssertionContext {
		public Bet_items_assertionContext bet_items_assertion() {
			return getRuleContext(Bet_items_assertionContext.class,0);
		}
		public NewBetweenItemsContext(AssertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitNewBetweenItems(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NewDefContext extends AssertionContext {
		public Def_assertionContext def_assertion() {
			return getRuleContext(Def_assertionContext.class,0);
		}
		public NewDefContext(AssertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitNewDef(this);
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
	public static class NewAssertionListContext extends AssertionContext {
		public Assertion_listContext assertion_list() {
			return getRuleContext(Assertion_listContext.class,0);
		}
		public NewAssertionListContext(AssertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitNewAssertionList(this);
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
	public static class NewRefContext extends AssertionContext {
		public Ref_assertionContext ref_assertion() {
			return getRuleContext(Ref_assertionContext.class,0);
		}
		public NewRefContext(AssertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitNewRef(this);
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
	public static class NewRepeatedItemsContext extends AssertionContext {
		public Repeated_items_assertionContext repeated_items_assertion() {
			return getRuleContext(Repeated_items_assertionContext.class,0);
		}
		public NewRepeatedItemsContext(AssertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitNewRepeatedItems(this);
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
	public static class NewPropertiesContext extends AssertionContext {
		public PropertiesContext properties() {
			return getRuleContext(PropertiesContext.class,0);
		}
		public NewPropertiesContext(AssertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitNewProperties(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NewAdditionalPatternRequiredContext extends AssertionContext {
		public Additional_pattern_requiredContext additional_pattern_required() {
			return getRuleContext(Additional_pattern_requiredContext.class,0);
		}
		public NewAdditionalPatternRequiredContext(AssertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitNewAdditionalPatternRequired(this);
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
	public static class NewNotPatternContext extends AssertionContext {
		public Not_pattern_assertionContext not_pattern_assertion() {
			return getRuleContext(Not_pattern_assertionContext.class,0);
		}
		public NewNotPatternContext(AssertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitNewNotPattern(this);
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
	public static class NewPropertyNamesContext extends AssertionContext {
		public PropertyNames_assertionContext propertyNames_assertion() {
			return getRuleContext(PropertyNames_assertionContext.class,0);
		}
		public NewPropertyNamesContext(AssertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitNewPropertyNames(this);
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
	public static class NewPropertyExNamesContext extends AssertionContext {
		public PropertyExNames_assertionContext propertyExNames_assertion() {
			return getRuleContext(PropertyExNames_assertionContext.class,0);
		}
		public NewPropertyExNamesContext(AssertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitNewPropertyExNames(this);
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
	public static class NewPatternRequiredContext extends AssertionContext {
		public Pattern_requiredContext pattern_required() {
			return getRuleContext(Pattern_requiredContext.class,0);
		}
		public NewPatternRequiredContext(AssertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitNewPatternRequired(this);
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
	public static class NewNotMultipleOfContext extends AssertionContext {
		public Not_multiple_of_assertionContext not_multiple_of_assertion() {
			return getRuleContext(Not_multiple_of_assertionContext.class,0);
		}
		public NewNotMultipleOfContext(AssertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitNewNotMultipleOf(this);
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
	public static class NewAnnotationsContext extends AssertionContext {
		public AnnotationsContext annotations() {
			return getRuleContext(AnnotationsContext.class,0);
		}
		public NewAnnotationsContext(AssertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitNewAnnotations(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssertionContext assertion() throws RecognitionException {
		AssertionContext _localctx = new AssertionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_assertion);
		try {
			setState(111);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__3:
				_localctx = new NewTypeAssertionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(80);
				type_assertion();
				}
				break;
			case T__6:
				_localctx = new NewBetweenAssertionContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(81);
				between_assertion();
				}
				break;
			case T__13:
				_localctx = new NewNotContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(82);
				not_assertion();
				}
				break;
			case T__7:
				_localctx = new NewXBetweenAssertionContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(83);
				xbetween_assertion();
				}
				break;
			case T__9:
				_localctx = new NewBetweenItemsContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(84);
				bet_items_assertion();
				}
				break;
			case T__8:
				_localctx = new NewLengthContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(85);
				length_assertion();
				}
				break;
			case T__10:
				_localctx = new NewBetweenPropertiesContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(86);
				between_properties_assertion();
				}
				break;
			case T__14:
				_localctx = new NewAllOfContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(87);
				all_of_assertion();
				}
				break;
			case T__18:
				_localctx = new NewAnyOfContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(88);
				any_of_assertion();
				}
				break;
			case T__17:
				_localctx = new NewOneOfContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(89);
				one_of_assertion();
				}
				break;
			case T__19:
				_localctx = new NewRequiredContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(90);
				required_assertion();
				}
				break;
			case T__21:
			case T__23:
				_localctx = new NewIfThenElseContext(_localctx);
				enterOuterAlt(_localctx, 12);
				{
				setState(91);
				if_then_else_assertion();
				}
				break;
			case T__11:
				_localctx = new NewMultipleOfContext(_localctx);
				enterOuterAlt(_localctx, 13);
				{
				setState(92);
				multiple_of_assertion();
				}
				break;
			case T__20:
				_localctx = new NewEnumContext(_localctx);
				enterOuterAlt(_localctx, 14);
				{
				setState(93);
				enum_assertion_assertion();
				}
				break;
			case T__24:
				_localctx = new NewUniqueItemsContext(_localctx);
				enterOuterAlt(_localctx, 15);
				{
				setState(94);
				unique_items_assertion();
				}
				break;
			case T__26:
				_localctx = new NewPatternContext(_localctx);
				enterOuterAlt(_localctx, 16);
				{
				setState(95);
				pattern_assertion();
				}
				break;
			case T__29:
				_localctx = new NewContainsContext(_localctx);
				enterOuterAlt(_localctx, 17);
				{
				setState(96);
				contains_assertion();
				}
				break;
			case T__32:
				_localctx = new NewConstContext(_localctx);
				enterOuterAlt(_localctx, 18);
				{
				setState(97);
				const_assertion();
				}
				break;
			case T__28:
				_localctx = new NewItemsContext(_localctx);
				enterOuterAlt(_localctx, 19);
				{
				setState(98);
				items_assertion();
				}
				break;
			case T__0:
			case BOOLEAN:
				_localctx = new NewAssertionListContext(_localctx);
				enterOuterAlt(_localctx, 20);
				{
				setState(99);
				assertion_list();
				}
				break;
			case T__30:
				_localctx = new NewPropertiesContext(_localctx);
				enterOuterAlt(_localctx, 21);
				{
				setState(100);
				properties();
				}
				break;
			case T__33:
			case T__35:
				_localctx = new NewDefContext(_localctx);
				enterOuterAlt(_localctx, 22);
				{
				setState(101);
				def_assertion();
				}
				break;
			case T__36:
				_localctx = new NewRefContext(_localctx);
				enterOuterAlt(_localctx, 23);
				{
				setState(102);
				ref_assertion();
				}
				break;
			case T__37:
				_localctx = new NewPropertyNamesContext(_localctx);
				enterOuterAlt(_localctx, 24);
				{
				setState(103);
				propertyNames_assertion();
				}
				break;
			case T__38:
				_localctx = new NewPropertyExNamesContext(_localctx);
				enterOuterAlt(_localctx, 25);
				{
				setState(104);
				propertyExNames_assertion();
				}
				break;
			case T__39:
				_localctx = new NewAnnotationsContext(_localctx);
				enterOuterAlt(_localctx, 26);
				{
				setState(105);
				annotations();
				}
				break;
			case T__12:
				_localctx = new NewNotMultipleOfContext(_localctx);
				enterOuterAlt(_localctx, 27);
				{
				setState(106);
				not_multiple_of_assertion();
				}
				break;
			case T__27:
				_localctx = new NewNotPatternContext(_localctx);
				enterOuterAlt(_localctx, 28);
				{
				setState(107);
				not_pattern_assertion();
				}
				break;
			case T__25:
				_localctx = new NewRepeatedItemsContext(_localctx);
				enterOuterAlt(_localctx, 29);
				{
				setState(108);
				repeated_items_assertion();
				}
				break;
			case T__41:
				_localctx = new NewAdditionalPatternRequiredContext(_localctx);
				enterOuterAlt(_localctx, 30);
				{
				setState(109);
				additional_pattern_required();
				}
				break;
			case T__40:
				_localctx = new NewPatternRequiredContext(_localctx);
				enterOuterAlt(_localctx, 31);
				{
				setState(110);
				pattern_required();
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
		public TerminalNode NULL() { return getToken(GrammaticaParser.NULL, 0); }
		public ParseTypeAssertionContext(Type_assertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitParseTypeAssertion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Type_assertionContext type_assertion() throws RecognitionException {
		Type_assertionContext _localctx = new Type_assertionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_type_assertion);
		int _la;
		try {
			_localctx = new ParseTypeAssertionContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			match(T__3);
			setState(114);
			match(T__4);
			setState(115);
			_la = _input.LA(1);
			if ( !(_la==NULL || _la==TYPE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(116);
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
		public List<Json_valueContext> json_value() {
			return getRuleContexts(Json_valueContext.class);
		}
		public Json_valueContext json_value(int i) {
			return getRuleContext(Json_valueContext.class,i);
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
		enterRule(_localctx, 6, RULE_between_assertion);
		try {
			_localctx = new ParseBetweenAssertionContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			match(T__6);
			setState(119);
			match(T__4);
			setState(120);
			json_value();
			setState(121);
			match(T__1);
			setState(122);
			json_value();
			setState(123);
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
		public List<Json_valueContext> json_value() {
			return getRuleContexts(Json_valueContext.class);
		}
		public Json_valueContext json_value(int i) {
			return getRuleContext(Json_valueContext.class,i);
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
		enterRule(_localctx, 8, RULE_xbetween_assertion);
		try {
			_localctx = new ParseXBetweenAssertionContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(125);
			match(T__7);
			setState(126);
			match(T__4);
			setState(127);
			json_value();
			setState(128);
			match(T__1);
			setState(129);
			json_value();
			setState(130);
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
		public List<Json_valueContext> json_value() {
			return getRuleContexts(Json_valueContext.class);
		}
		public Json_valueContext json_value(int i) {
			return getRuleContext(Json_valueContext.class,i);
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
		enterRule(_localctx, 10, RULE_length_assertion);
		try {
			_localctx = new ParseLengthAssertionContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(132);
			match(T__8);
			setState(133);
			match(T__4);
			setState(134);
			json_value();
			setState(135);
			match(T__1);
			setState(136);
			json_value();
			setState(137);
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
		public List<Json_valueContext> json_value() {
			return getRuleContexts(Json_valueContext.class);
		}
		public Json_valueContext json_value(int i) {
			return getRuleContext(Json_valueContext.class,i);
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
		enterRule(_localctx, 12, RULE_bet_items_assertion);
		try {
			_localctx = new ParseBetItemsAssertionContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(139);
			match(T__9);
			setState(140);
			match(T__4);
			setState(141);
			json_value();
			setState(142);
			match(T__1);
			setState(143);
			json_value();
			setState(144);
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
		public List<Json_valueContext> json_value() {
			return getRuleContexts(Json_valueContext.class);
		}
		public Json_valueContext json_value(int i) {
			return getRuleContext(Json_valueContext.class,i);
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
		enterRule(_localctx, 14, RULE_between_properties_assertion);
		try {
			_localctx = new ParseBetProAssertionContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
			match(T__10);
			setState(147);
			match(T__4);
			setState(148);
			json_value();
			setState(149);
			match(T__1);
			setState(150);
			json_value();
			setState(151);
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
		public Json_valueContext json_value() {
			return getRuleContext(Json_valueContext.class,0);
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
		enterRule(_localctx, 16, RULE_multiple_of_assertion);
		try {
			_localctx = new ParseMultipleOfContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(153);
			match(T__11);
			setState(154);
			match(T__4);
			setState(155);
			json_value();
			setState(156);
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

	public static class Not_multiple_of_assertionContext extends ParserRuleContext {
		public Not_multiple_of_assertionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_not_multiple_of_assertion; }
	 
		public Not_multiple_of_assertionContext() { }
		public void copyFrom(Not_multiple_of_assertionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ParseNotMultipleOfContext extends Not_multiple_of_assertionContext {
		public Json_valueContext json_value() {
			return getRuleContext(Json_valueContext.class,0);
		}
		public ParseNotMultipleOfContext(Not_multiple_of_assertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitParseNotMultipleOf(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Not_multiple_of_assertionContext not_multiple_of_assertion() throws RecognitionException {
		Not_multiple_of_assertionContext _localctx = new Not_multiple_of_assertionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_not_multiple_of_assertion);
		try {
			_localctx = new ParseNotMultipleOfContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(158);
			match(T__12);
			setState(159);
			match(T__4);
			setState(160);
			json_value();
			setState(161);
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
		enterRule(_localctx, 20, RULE_not_assertion);
		try {
			_localctx = new ParseNotContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(163);
			match(T__13);
			setState(164);
			match(T__4);
			setState(165);
			assertion();
			setState(166);
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
		public List<AssertionContext> assertion() {
			return getRuleContexts(AssertionContext.class);
		}
		public AssertionContext assertion(int i) {
			return getRuleContext(AssertionContext.class,i);
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
		enterRule(_localctx, 22, RULE_all_of_assertion);
		int _la;
		try {
			_localctx = new ParseAllOfContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
			match(T__14);
			setState(169);
			match(T__15);
			setState(170);
			assertion();
			setState(175);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(171);
				match(T__1);
				setState(172);
				assertion();
				}
				}
				setState(177);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(178);
			match(T__16);
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
		public List<AssertionContext> assertion() {
			return getRuleContexts(AssertionContext.class);
		}
		public AssertionContext assertion(int i) {
			return getRuleContext(AssertionContext.class,i);
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
		enterRule(_localctx, 24, RULE_one_of_assertion);
		int _la;
		try {
			_localctx = new ParseOneOfContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(180);
			match(T__17);
			setState(181);
			match(T__15);
			setState(182);
			assertion();
			setState(187);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(183);
				match(T__1);
				setState(184);
				assertion();
				}
				}
				setState(189);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(190);
			match(T__16);
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
		public List<AssertionContext> assertion() {
			return getRuleContexts(AssertionContext.class);
		}
		public AssertionContext assertion(int i) {
			return getRuleContext(AssertionContext.class,i);
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
		enterRule(_localctx, 26, RULE_any_of_assertion);
		int _la;
		try {
			_localctx = new ParseAnyOfContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(192);
			match(T__18);
			setState(193);
			match(T__15);
			setState(194);
			assertion();
			setState(199);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(195);
				match(T__1);
				setState(196);
				assertion();
				}
				}
				setState(201);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(202);
			match(T__16);
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
		enterRule(_localctx, 28, RULE_required_assertion);
		int _la;
		try {
			_localctx = new ParseRequiredContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(204);
			match(T__19);
			setState(205);
			match(T__15);
			setState(206);
			match(STRING);
			setState(211);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(207);
				match(T__1);
				setState(208);
				match(STRING);
				}
				}
				setState(213);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(214);
			match(T__16);
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
		public List<Json_valueContext> json_value() {
			return getRuleContexts(Json_valueContext.class);
		}
		public Json_valueContext json_value(int i) {
			return getRuleContext(Json_valueContext.class,i);
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
		enterRule(_localctx, 30, RULE_enum_assertion_assertion);
		int _la;
		try {
			_localctx = new ParseEnumContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(216);
			match(T__20);
			setState(217);
			json_value();
			setState(222);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(218);
				match(T__1);
				setState(219);
				json_value();
				}
				}
				setState(224);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(225);
			match(T__16);
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
		enterRule(_localctx, 32, RULE_if_then_else_assertion);
		try {
			setState(243);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__21:
				_localctx = new ParseIfThenElseContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(227);
				match(T__21);
				setState(228);
				match(T__4);
				setState(229);
				assertion();
				setState(230);
				match(T__22);
				setState(231);
				assertion();
				setState(232);
				match(T__22);
				setState(233);
				assertion();
				setState(234);
				match(T__5);
				}
				break;
			case T__23:
				_localctx = new ParseIfThenContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(236);
				match(T__23);
				setState(237);
				match(T__4);
				setState(238);
				assertion();
				setState(239);
				match(T__22);
				setState(240);
				assertion();
				setState(241);
				match(T__5);
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
		enterRule(_localctx, 34, RULE_unique_items_assertion);
		try {
			_localctx = new ParseUniqueItemsContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(245);
			match(T__24);
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

	public static class Repeated_items_assertionContext extends ParserRuleContext {
		public Repeated_items_assertionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_repeated_items_assertion; }
	 
		public Repeated_items_assertionContext() { }
		public void copyFrom(Repeated_items_assertionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ParseRepeatedItemsContext extends Repeated_items_assertionContext {
		public ParseRepeatedItemsContext(Repeated_items_assertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitParseRepeatedItems(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Repeated_items_assertionContext repeated_items_assertion() throws RecognitionException {
		Repeated_items_assertionContext _localctx = new Repeated_items_assertionContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_repeated_items_assertion);
		try {
			_localctx = new ParseRepeatedItemsContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(247);
			match(T__25);
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
		enterRule(_localctx, 38, RULE_pattern_assertion);
		try {
			_localctx = new ParsePatternContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(249);
			match(T__26);
			setState(250);
			match(T__4);
			setState(251);
			match(STRING);
			setState(252);
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

	public static class Not_pattern_assertionContext extends ParserRuleContext {
		public Not_pattern_assertionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_not_pattern_assertion; }
	 
		public Not_pattern_assertionContext() { }
		public void copyFrom(Not_pattern_assertionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ParseNotPatternContext extends Not_pattern_assertionContext {
		public TerminalNode STRING() { return getToken(GrammaticaParser.STRING, 0); }
		public ParseNotPatternContext(Not_pattern_assertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitParseNotPattern(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Not_pattern_assertionContext not_pattern_assertion() throws RecognitionException {
		Not_pattern_assertionContext _localctx = new Not_pattern_assertionContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_not_pattern_assertion);
		try {
			_localctx = new ParseNotPatternContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(254);
			match(T__27);
			setState(255);
			match(T__4);
			setState(256);
			match(STRING);
			setState(257);
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
	public static class ParseOnlyItemsContext extends Items_assertionContext {
		public List<AssertionContext> assertion() {
			return getRuleContexts(AssertionContext.class);
		}
		public AssertionContext assertion(int i) {
			return getRuleContext(AssertionContext.class,i);
		}
		public ParseOnlyItemsContext(Items_assertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitParseOnlyItems(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParseAdditionalItemsContext extends Items_assertionContext {
		public List<AssertionContext> assertion() {
			return getRuleContexts(AssertionContext.class);
		}
		public AssertionContext assertion(int i) {
			return getRuleContext(AssertionContext.class,i);
		}
		public ParseAdditionalItemsContext(Items_assertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitParseAdditionalItems(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Items_assertionContext items_assertion() throws RecognitionException {
		Items_assertionContext _localctx = new Items_assertionContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_items_assertion);
		int _la;
		try {
			setState(288);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				_localctx = new ParseOnlyItemsContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(259);
				match(T__28);
				setState(260);
				match(T__4);
				setState(261);
				assertion();
				setState(266);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(262);
					match(T__1);
					setState(263);
					assertion();
					}
					}
					setState(268);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(269);
				match(T__22);
				setState(270);
				match(T__5);
				}
				break;
			case 2:
				_localctx = new ParseAdditionalItemsContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(272);
				match(T__28);
				setState(273);
				match(T__4);
				setState(282);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__3) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << T__30) | (1L << T__32) | (1L << T__33) | (1L << T__35) | (1L << T__36) | (1L << T__37) | (1L << T__38) | (1L << T__39) | (1L << T__40) | (1L << T__41) | (1L << BOOLEAN))) != 0)) {
					{
					setState(274);
					assertion();
					setState(279);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__1) {
						{
						{
						setState(275);
						match(T__1);
						setState(276);
						assertion();
						}
						}
						setState(281);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(284);
				match(T__22);
				setState(285);
				assertion();
				setState(286);
				match(T__5);
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
		public List<Json_valueContext> json_value() {
			return getRuleContexts(Json_valueContext.class);
		}
		public Json_valueContext json_value(int i) {
			return getRuleContext(Json_valueContext.class,i);
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
		enterRule(_localctx, 44, RULE_contains_assertion);
		try {
			_localctx = new ParseContainsContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(290);
			match(T__29);
			setState(291);
			match(T__4);
			setState(292);
			json_value();
			setState(293);
			match(T__1);
			setState(294);
			json_value();
			setState(295);
			match(T__22);
			setState(296);
			assertion();
			setState(297);
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

	public static class PropertiesContext extends ParserRuleContext {
		public PropertiesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_properties; }
	 
		public PropertiesContext() { }
		public void copyFrom(PropertiesContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ParseAdditionalPropertiesContext extends PropertiesContext {
		public List<AssertionContext> assertion() {
			return getRuleContexts(AssertionContext.class);
		}
		public AssertionContext assertion(int i) {
			return getRuleContext(AssertionContext.class,i);
		}
		public List<TerminalNode> STRING() { return getTokens(GrammaticaParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(GrammaticaParser.STRING, i);
		}
		public ParseAdditionalPropertiesContext(PropertiesContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitParseAdditionalProperties(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParsePropertiesContext extends PropertiesContext {
		public List<TerminalNode> STRING() { return getTokens(GrammaticaParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(GrammaticaParser.STRING, i);
		}
		public List<AssertionContext> assertion() {
			return getRuleContexts(AssertionContext.class);
		}
		public AssertionContext assertion(int i) {
			return getRuleContext(AssertionContext.class,i);
		}
		public ParsePropertiesContext(PropertiesContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitParseProperties(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PropertiesContext properties() throws RecognitionException {
		PropertiesContext _localctx = new PropertiesContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_properties);
		int _la;
		try {
			setState(337);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				_localctx = new ParseAdditionalPropertiesContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(299);
				match(T__30);
				setState(300);
				match(T__15);
				setState(313);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==STRING) {
					{
					setState(301);
					match(STRING);
					setState(302);
					match(T__31);
					setState(303);
					assertion();
					setState(310);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__1) {
						{
						{
						setState(304);
						match(T__1);
						setState(305);
						match(STRING);
						setState(306);
						match(T__31);
						setState(307);
						assertion();
						}
						}
						setState(312);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(315);
				match(T__22);
				setState(316);
				assertion();
				setState(317);
				match(T__16);
				}
				break;
			case 2:
				_localctx = new ParsePropertiesContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(319);
				match(T__30);
				setState(320);
				match(T__15);
				setState(333);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==STRING) {
					{
					setState(321);
					match(STRING);
					setState(322);
					match(T__31);
					setState(323);
					assertion();
					setState(330);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__1) {
						{
						{
						setState(324);
						match(T__1);
						setState(325);
						match(STRING);
						setState(326);
						match(T__31);
						setState(327);
						assertion();
						}
						}
						setState(332);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(335);
				match(T__22);
				setState(336);
				match(T__16);
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
		public Json_valueContext json_value() {
			return getRuleContext(Json_valueContext.class,0);
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
		enterRule(_localctx, 48, RULE_const_assertion);
		try {
			_localctx = new ParseConstContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(339);
			match(T__32);
			setState(340);
			match(T__4);
			setState(341);
			json_value();
			setState(342);
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

	public static class Def_assertionContext extends ParserRuleContext {
		public Def_assertionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_def_assertion; }
	 
		public Def_assertionContext() { }
		public void copyFrom(Def_assertionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ParseDefContext extends Def_assertionContext {
		public List<TerminalNode> STRING() { return getTokens(GrammaticaParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(GrammaticaParser.STRING, i);
		}
		public List<AssertionContext> assertion() {
			return getRuleContexts(AssertionContext.class);
		}
		public AssertionContext assertion(int i) {
			return getRuleContext(AssertionContext.class,i);
		}
		public ParseDefContext(Def_assertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitParseDef(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParseDefRootContext extends Def_assertionContext {
		public List<AssertionContext> assertion() {
			return getRuleContexts(AssertionContext.class);
		}
		public AssertionContext assertion(int i) {
			return getRuleContext(AssertionContext.class,i);
		}
		public List<TerminalNode> STRING() { return getTokens(GrammaticaParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(GrammaticaParser.STRING, i);
		}
		public ParseDefRootContext(Def_assertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitParseDefRoot(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Def_assertionContext def_assertion() throws RecognitionException {
		Def_assertionContext _localctx = new Def_assertionContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_def_assertion);
		try {
			int _alt;
			setState(371);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__33:
				_localctx = new ParseDefContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(344);
				match(T__33);
				setState(345);
				match(STRING);
				setState(346);
				match(T__34);
				setState(347);
				assertion();
				setState(355);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(348);
						match(T__1);
						setState(349);
						match(T__33);
						setState(350);
						match(STRING);
						setState(351);
						match(T__34);
						setState(352);
						assertion();
						}
						} 
					}
					setState(357);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
				}
				}
				break;
			case T__35:
				_localctx = new ParseDefRootContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(358);
				match(T__35);
				setState(359);
				match(T__34);
				setState(360);
				assertion();
				setState(368);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(361);
						match(T__1);
						setState(362);
						match(T__33);
						setState(363);
						match(STRING);
						setState(364);
						match(T__34);
						setState(365);
						assertion();
						}
						} 
					}
					setState(370);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
				}
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

	public static class Ref_assertionContext extends ParserRuleContext {
		public Ref_assertionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ref_assertion; }
	 
		public Ref_assertionContext() { }
		public void copyFrom(Ref_assertionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ParseRefContext extends Ref_assertionContext {
		public TerminalNode STRING() { return getToken(GrammaticaParser.STRING, 0); }
		public ParseRefContext(Ref_assertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitParseRef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Ref_assertionContext ref_assertion() throws RecognitionException {
		Ref_assertionContext _localctx = new Ref_assertionContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_ref_assertion);
		try {
			_localctx = new ParseRefContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(373);
			match(T__36);
			setState(374);
			match(T__4);
			setState(375);
			match(STRING);
			setState(376);
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

	public static class PropertyNames_assertionContext extends ParserRuleContext {
		public PropertyNames_assertionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_propertyNames_assertion; }
	 
		public PropertyNames_assertionContext() { }
		public void copyFrom(PropertyNames_assertionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ParsePropertyNamesContext extends PropertyNames_assertionContext {
		public AssertionContext assertion() {
			return getRuleContext(AssertionContext.class,0);
		}
		public ParsePropertyNamesContext(PropertyNames_assertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitParsePropertyNames(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PropertyNames_assertionContext propertyNames_assertion() throws RecognitionException {
		PropertyNames_assertionContext _localctx = new PropertyNames_assertionContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_propertyNames_assertion);
		try {
			_localctx = new ParsePropertyNamesContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(378);
			match(T__37);
			setState(379);
			match(T__4);
			setState(380);
			assertion();
			setState(381);
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

	public static class PropertyExNames_assertionContext extends ParserRuleContext {
		public PropertyExNames_assertionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_propertyExNames_assertion; }
	 
		public PropertyExNames_assertionContext() { }
		public void copyFrom(PropertyExNames_assertionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ParsePropertyExNamesContext extends PropertyExNames_assertionContext {
		public AssertionContext assertion() {
			return getRuleContext(AssertionContext.class,0);
		}
		public ParsePropertyExNamesContext(PropertyExNames_assertionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitParsePropertyExNames(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PropertyExNames_assertionContext propertyExNames_assertion() throws RecognitionException {
		PropertyExNames_assertionContext _localctx = new PropertyExNames_assertionContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_propertyExNames_assertion);
		try {
			_localctx = new ParsePropertyExNamesContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(383);
			match(T__38);
			setState(384);
			match(T__4);
			setState(385);
			assertion();
			setState(386);
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

	public static class AnnotationsContext extends ParserRuleContext {
		public AnnotationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotations; }
	 
		public AnnotationsContext() { }
		public void copyFrom(AnnotationsContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ParseAnnotationsContext extends AnnotationsContext {
		public List<TerminalNode> STRING() { return getTokens(GrammaticaParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(GrammaticaParser.STRING, i);
		}
		public ParseAnnotationsContext(AnnotationsContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitParseAnnotations(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnnotationsContext annotations() throws RecognitionException {
		AnnotationsContext _localctx = new AnnotationsContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_annotations);
		int _la;
		try {
			_localctx = new ParseAnnotationsContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(388);
			match(T__39);
			setState(389);
			match(T__15);
			setState(390);
			match(STRING);
			setState(391);
			match(T__31);
			setState(392);
			match(STRING);
			setState(399);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(393);
				match(T__1);
				setState(394);
				match(STRING);
				setState(395);
				match(T__31);
				setState(396);
				match(STRING);
				}
				}
				setState(401);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(402);
			match(T__16);
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

	public static class Pattern_requiredContext extends ParserRuleContext {
		public Pattern_requiredContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pattern_required; }
	 
		public Pattern_requiredContext() { }
		public void copyFrom(Pattern_requiredContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ParsePatternRequiredContext extends Pattern_requiredContext {
		public List<TerminalNode> STRING() { return getTokens(GrammaticaParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(GrammaticaParser.STRING, i);
		}
		public List<AssertionContext> assertion() {
			return getRuleContexts(AssertionContext.class);
		}
		public AssertionContext assertion(int i) {
			return getRuleContext(AssertionContext.class,i);
		}
		public ParsePatternRequiredContext(Pattern_requiredContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitParsePatternRequired(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Pattern_requiredContext pattern_required() throws RecognitionException {
		Pattern_requiredContext _localctx = new Pattern_requiredContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_pattern_required);
		int _la;
		try {
			_localctx = new ParsePatternRequiredContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(404);
			match(T__40);
			setState(405);
			match(T__15);
			setState(406);
			match(STRING);
			setState(407);
			match(T__31);
			setState(408);
			assertion();
			setState(415);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(409);
				match(T__1);
				setState(410);
				match(STRING);
				setState(411);
				match(T__31);
				setState(412);
				assertion();
				}
				}
				setState(417);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(418);
			match(T__16);
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

	public static class Additional_pattern_requiredContext extends ParserRuleContext {
		public Additional_pattern_requiredContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additional_pattern_required; }
	 
		public Additional_pattern_requiredContext() { }
		public void copyFrom(Additional_pattern_requiredContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ParseAdditionalPatternRequiredContext extends Additional_pattern_requiredContext {
		public AssertionContext assertion() {
			return getRuleContext(AssertionContext.class,0);
		}
		public List<TerminalNode> STRING() { return getTokens(GrammaticaParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(GrammaticaParser.STRING, i);
		}
		public ParseAdditionalPatternRequiredContext(Additional_pattern_requiredContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitParseAdditionalPatternRequired(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Additional_pattern_requiredContext additional_pattern_required() throws RecognitionException {
		Additional_pattern_requiredContext _localctx = new Additional_pattern_requiredContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_additional_pattern_required);
		int _la;
		try {
			_localctx = new ParseAdditionalPatternRequiredContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(420);
			match(T__41);
			setState(421);
			match(T__4);
			setState(422);
			match(T__15);
			setState(428);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==STRING) {
				{
				{
				setState(423);
				match(STRING);
				{
				setState(424);
				match(T__1);
				setState(425);
				match(STRING);
				}
				}
				}
				setState(430);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(431);
			match(T__16);
			setState(432);
			match(T__31);
			setState(433);
			assertion();
			setState(434);
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

	public static class Json_valueContext extends ParserRuleContext {
		public Json_valueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_json_value; }
	 
		public Json_valueContext() { }
		public void copyFrom(Json_valueContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class NullValueContext extends Json_valueContext {
		public TerminalNode NULL() { return getToken(GrammaticaParser.NULL, 0); }
		public NullValueContext(Json_valueContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitNullValue(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DoubleValueContext extends Json_valueContext {
		public TerminalNode DOUBLE() { return getToken(GrammaticaParser.DOUBLE, 0); }
		public DoubleValueContext(Json_valueContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitDoubleValue(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BooleanValueContext extends Json_valueContext {
		public TerminalNode BOOLEAN() { return getToken(GrammaticaParser.BOOLEAN, 0); }
		public BooleanValueContext(Json_valueContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitBooleanValue(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StringValueContext extends Json_valueContext {
		public TerminalNode STRING() { return getToken(GrammaticaParser.STRING, 0); }
		public StringValueContext(Json_valueContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitStringValue(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntValueContext extends Json_valueContext {
		public TerminalNode INT() { return getToken(GrammaticaParser.INT, 0); }
		public IntValueContext(Json_valueContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitIntValue(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArrayValueContext extends Json_valueContext {
		public List<Json_valueContext> json_value() {
			return getRuleContexts(Json_valueContext.class);
		}
		public Json_valueContext json_value(int i) {
			return getRuleContext(Json_valueContext.class,i);
		}
		public ArrayValueContext(Json_valueContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitArrayValue(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class JsonObjectValueContext extends Json_valueContext {
		public List<TerminalNode> STRING() { return getTokens(GrammaticaParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(GrammaticaParser.STRING, i);
		}
		public List<Json_valueContext> json_value() {
			return getRuleContexts(Json_valueContext.class);
		}
		public Json_valueContext json_value(int i) {
			return getRuleContext(Json_valueContext.class,i);
		}
		public JsonObjectValueContext(Json_valueContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammaticaVisitor ) return ((GrammaticaVisitor<? extends T>)visitor).visitJsonObjectValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Json_valueContext json_value() throws RecognitionException {
		Json_valueContext _localctx = new Json_valueContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_json_value);
		int _la;
		try {
			setState(469);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NULL:
				_localctx = new NullValueContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(436);
				match(NULL);
				}
				break;
			case INT:
				_localctx = new IntValueContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(437);
				match(INT);
				}
				break;
			case STRING:
				_localctx = new StringValueContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(438);
				match(STRING);
				}
				break;
			case DOUBLE:
				_localctx = new DoubleValueContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(439);
				match(DOUBLE);
				}
				break;
			case T__15:
				_localctx = new ArrayValueContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(440);
				match(T__15);
				setState(449);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__15) | (1L << NULL) | (1L << INT) | (1L << DOUBLE) | (1L << STRING) | (1L << BOOLEAN))) != 0)) {
					{
					setState(441);
					json_value();
					setState(446);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__1) {
						{
						{
						setState(442);
						match(T__1);
						setState(443);
						json_value();
						}
						}
						setState(448);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(451);
				match(T__16);
				}
				break;
			case BOOLEAN:
				_localctx = new BooleanValueContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(452);
				match(BOOLEAN);
				}
				break;
			case T__0:
				_localctx = new JsonObjectValueContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(453);
				match(T__0);
				setState(466);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==STRING) {
					{
					setState(454);
					match(STRING);
					setState(455);
					match(T__31);
					setState(456);
					json_value();
					setState(463);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__1) {
						{
						{
						setState(457);
						match(T__1);
						setState(458);
						match(STRING);
						setState(459);
						match(T__31);
						setState(460);
						json_value();
						}
						}
						setState(465);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(468);
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\63\u01da\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\3\2\3\2\3\2\3\2\7\2I\n\2\f\2\16\2L\13\2\3\2\3\2\3\2\5\2Q\n"+
		"\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3r\n\3\3\4"+
		"\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f"+
		"\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\7\r\u00b0\n\r\f\r\16\r\u00b3\13\r\3\r"+
		"\3\r\3\16\3\16\3\16\3\16\3\16\7\16\u00bc\n\16\f\16\16\16\u00bf\13\16\3"+
		"\16\3\16\3\17\3\17\3\17\3\17\3\17\7\17\u00c8\n\17\f\17\16\17\u00cb\13"+
		"\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\7\20\u00d4\n\20\f\20\16\20\u00d7"+
		"\13\20\3\20\3\20\3\21\3\21\3\21\3\21\7\21\u00df\n\21\f\21\16\21\u00e2"+
		"\13\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\5\22\u00f6\n\22\3\23\3\23\3\24\3\24\3\25\3\25"+
		"\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\7\27"+
		"\u010b\n\27\f\27\16\27\u010e\13\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\7\27\u0118\n\27\f\27\16\27\u011b\13\27\5\27\u011d\n\27\3\27\3\27"+
		"\3\27\3\27\5\27\u0123\n\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\7\31\u0137\n\31\f\31\16"+
		"\31\u013a\13\31\5\31\u013c\n\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31"+
		"\3\31\3\31\3\31\3\31\3\31\7\31\u014b\n\31\f\31\16\31\u014e\13\31\5\31"+
		"\u0150\n\31\3\31\3\31\5\31\u0154\n\31\3\32\3\32\3\32\3\32\3\32\3\33\3"+
		"\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\7\33\u0164\n\33\f\33\16\33\u0167"+
		"\13\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\7\33\u0171\n\33\f\33\16"+
		"\33\u0174\13\33\5\33\u0176\n\33\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35"+
		"\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3\37\3\37"+
		"\3\37\3\37\7\37\u0190\n\37\f\37\16\37\u0193\13\37\3\37\3\37\3 \3 \3 \3"+
		" \3 \3 \3 \3 \3 \7 \u01a0\n \f \16 \u01a3\13 \3 \3 \3!\3!\3!\3!\3!\3!"+
		"\7!\u01ad\n!\f!\16!\u01b0\13!\3!\3!\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3\"\3"+
		"\"\3\"\7\"\u01bf\n\"\f\"\16\"\u01c2\13\"\5\"\u01c4\n\"\3\"\3\"\3\"\3\""+
		"\3\"\3\"\3\"\3\"\3\"\3\"\7\"\u01d0\n\"\f\"\16\"\u01d3\13\"\5\"\u01d5\n"+
		"\"\3\"\5\"\u01d8\n\"\3\"\2\2#\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 "+
		"\"$&(*,.\60\62\64\668:<>@B\2\3\3\2-.\2\u01f7\2P\3\2\2\2\4q\3\2\2\2\6s"+
		"\3\2\2\2\bx\3\2\2\2\n\177\3\2\2\2\f\u0086\3\2\2\2\16\u008d\3\2\2\2\20"+
		"\u0094\3\2\2\2\22\u009b\3\2\2\2\24\u00a0\3\2\2\2\26\u00a5\3\2\2\2\30\u00aa"+
		"\3\2\2\2\32\u00b6\3\2\2\2\34\u00c2\3\2\2\2\36\u00ce\3\2\2\2 \u00da\3\2"+
		"\2\2\"\u00f5\3\2\2\2$\u00f7\3\2\2\2&\u00f9\3\2\2\2(\u00fb\3\2\2\2*\u0100"+
		"\3\2\2\2,\u0122\3\2\2\2.\u0124\3\2\2\2\60\u0153\3\2\2\2\62\u0155\3\2\2"+
		"\2\64\u0175\3\2\2\2\66\u0177\3\2\2\28\u017c\3\2\2\2:\u0181\3\2\2\2<\u0186"+
		"\3\2\2\2>\u0196\3\2\2\2@\u01a6\3\2\2\2B\u01d7\3\2\2\2DE\7\3\2\2EJ\5\4"+
		"\3\2FG\7\4\2\2GI\5\4\3\2HF\3\2\2\2IL\3\2\2\2JH\3\2\2\2JK\3\2\2\2KM\3\2"+
		"\2\2LJ\3\2\2\2MN\7\5\2\2NQ\3\2\2\2OQ\7\63\2\2PD\3\2\2\2PO\3\2\2\2Q\3\3"+
		"\2\2\2Rr\5\6\4\2Sr\5\b\5\2Tr\5\26\f\2Ur\5\n\6\2Vr\5\16\b\2Wr\5\f\7\2X"+
		"r\5\20\t\2Yr\5\30\r\2Zr\5\34\17\2[r\5\32\16\2\\r\5\36\20\2]r\5\"\22\2"+
		"^r\5\22\n\2_r\5 \21\2`r\5$\23\2ar\5(\25\2br\5.\30\2cr\5\62\32\2dr\5,\27"+
		"\2er\5\2\2\2fr\5\60\31\2gr\5\64\33\2hr\5\66\34\2ir\58\35\2jr\5:\36\2k"+
		"r\5<\37\2lr\5\24\13\2mr\5*\26\2nr\5&\24\2or\5@!\2pr\5> \2qR\3\2\2\2qS"+
		"\3\2\2\2qT\3\2\2\2qU\3\2\2\2qV\3\2\2\2qW\3\2\2\2qX\3\2\2\2qY\3\2\2\2q"+
		"Z\3\2\2\2q[\3\2\2\2q\\\3\2\2\2q]\3\2\2\2q^\3\2\2\2q_\3\2\2\2q`\3\2\2\2"+
		"qa\3\2\2\2qb\3\2\2\2qc\3\2\2\2qd\3\2\2\2qe\3\2\2\2qf\3\2\2\2qg\3\2\2\2"+
		"qh\3\2\2\2qi\3\2\2\2qj\3\2\2\2qk\3\2\2\2ql\3\2\2\2qm\3\2\2\2qn\3\2\2\2"+
		"qo\3\2\2\2qp\3\2\2\2r\5\3\2\2\2st\7\6\2\2tu\7\7\2\2uv\t\2\2\2vw\7\b\2"+
		"\2w\7\3\2\2\2xy\7\t\2\2yz\7\7\2\2z{\5B\"\2{|\7\4\2\2|}\5B\"\2}~\7\b\2"+
		"\2~\t\3\2\2\2\177\u0080\7\n\2\2\u0080\u0081\7\7\2\2\u0081\u0082\5B\"\2"+
		"\u0082\u0083\7\4\2\2\u0083\u0084\5B\"\2\u0084\u0085\7\b\2\2\u0085\13\3"+
		"\2\2\2\u0086\u0087\7\13\2\2\u0087\u0088\7\7\2\2\u0088\u0089\5B\"\2\u0089"+
		"\u008a\7\4\2\2\u008a\u008b\5B\"\2\u008b\u008c\7\b\2\2\u008c\r\3\2\2\2"+
		"\u008d\u008e\7\f\2\2\u008e\u008f\7\7\2\2\u008f\u0090\5B\"\2\u0090\u0091"+
		"\7\4\2\2\u0091\u0092\5B\"\2\u0092\u0093\7\b\2\2\u0093\17\3\2\2\2\u0094"+
		"\u0095\7\r\2\2\u0095\u0096\7\7\2\2\u0096\u0097\5B\"\2\u0097\u0098\7\4"+
		"\2\2\u0098\u0099\5B\"\2\u0099\u009a\7\b\2\2\u009a\21\3\2\2\2\u009b\u009c"+
		"\7\16\2\2\u009c\u009d\7\7\2\2\u009d\u009e\5B\"\2\u009e\u009f\7\b\2\2\u009f"+
		"\23\3\2\2\2\u00a0\u00a1\7\17\2\2\u00a1\u00a2\7\7\2\2\u00a2\u00a3\5B\""+
		"\2\u00a3\u00a4\7\b\2\2\u00a4\25\3\2\2\2\u00a5\u00a6\7\20\2\2\u00a6\u00a7"+
		"\7\7\2\2\u00a7\u00a8\5\4\3\2\u00a8\u00a9\7\b\2\2\u00a9\27\3\2\2\2\u00aa"+
		"\u00ab\7\21\2\2\u00ab\u00ac\7\22\2\2\u00ac\u00b1\5\4\3\2\u00ad\u00ae\7"+
		"\4\2\2\u00ae\u00b0\5\4\3\2\u00af\u00ad\3\2\2\2\u00b0\u00b3\3\2\2\2\u00b1"+
		"\u00af\3\2\2\2\u00b1\u00b2\3\2\2\2\u00b2\u00b4\3\2\2\2\u00b3\u00b1\3\2"+
		"\2\2\u00b4\u00b5\7\23\2\2\u00b5\31\3\2\2\2\u00b6\u00b7\7\24\2\2\u00b7"+
		"\u00b8\7\22\2\2\u00b8\u00bd\5\4\3\2\u00b9\u00ba\7\4\2\2\u00ba\u00bc\5"+
		"\4\3\2\u00bb\u00b9\3\2\2\2\u00bc\u00bf\3\2\2\2\u00bd\u00bb\3\2\2\2\u00bd"+
		"\u00be\3\2\2\2\u00be\u00c0\3\2\2\2\u00bf\u00bd\3\2\2\2\u00c0\u00c1\7\23"+
		"\2\2\u00c1\33\3\2\2\2\u00c2\u00c3\7\25\2\2\u00c3\u00c4\7\22\2\2\u00c4"+
		"\u00c9\5\4\3\2\u00c5\u00c6\7\4\2\2\u00c6\u00c8\5\4\3\2\u00c7\u00c5\3\2"+
		"\2\2\u00c8\u00cb\3\2\2\2\u00c9\u00c7\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca"+
		"\u00cc\3\2\2\2\u00cb\u00c9\3\2\2\2\u00cc\u00cd\7\23\2\2\u00cd\35\3\2\2"+
		"\2\u00ce\u00cf\7\26\2\2\u00cf\u00d0\7\22\2\2\u00d0\u00d5\7\62\2\2\u00d1"+
		"\u00d2\7\4\2\2\u00d2\u00d4\7\62\2\2\u00d3\u00d1\3\2\2\2\u00d4\u00d7\3"+
		"\2\2\2\u00d5\u00d3\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6\u00d8\3\2\2\2\u00d7"+
		"\u00d5\3\2\2\2\u00d8\u00d9\7\23\2\2\u00d9\37\3\2\2\2\u00da\u00db\7\27"+
		"\2\2\u00db\u00e0\5B\"\2\u00dc\u00dd\7\4\2\2\u00dd\u00df\5B\"\2\u00de\u00dc"+
		"\3\2\2\2\u00df\u00e2\3\2\2\2\u00e0\u00de\3\2\2\2\u00e0\u00e1\3\2\2\2\u00e1"+
		"\u00e3\3\2\2\2\u00e2\u00e0\3\2\2\2\u00e3\u00e4\7\23\2\2\u00e4!\3\2\2\2"+
		"\u00e5\u00e6\7\30\2\2\u00e6\u00e7\7\7\2\2\u00e7\u00e8\5\4\3\2\u00e8\u00e9"+
		"\7\31\2\2\u00e9\u00ea\5\4\3\2\u00ea\u00eb\7\31\2\2\u00eb\u00ec\5\4\3\2"+
		"\u00ec\u00ed\7\b\2\2\u00ed\u00f6\3\2\2\2\u00ee\u00ef\7\32\2\2\u00ef\u00f0"+
		"\7\7\2\2\u00f0\u00f1\5\4\3\2\u00f1\u00f2\7\31\2\2\u00f2\u00f3\5\4\3\2"+
		"\u00f3\u00f4\7\b\2\2\u00f4\u00f6\3\2\2\2\u00f5\u00e5\3\2\2\2\u00f5\u00ee"+
		"\3\2\2\2\u00f6#\3\2\2\2\u00f7\u00f8\7\33\2\2\u00f8%\3\2\2\2\u00f9\u00fa"+
		"\7\34\2\2\u00fa\'\3\2\2\2\u00fb\u00fc\7\35\2\2\u00fc\u00fd\7\7\2\2\u00fd"+
		"\u00fe\7\62\2\2\u00fe\u00ff\7\b\2\2\u00ff)\3\2\2\2\u0100\u0101\7\36\2"+
		"\2\u0101\u0102\7\7\2\2\u0102\u0103\7\62\2\2\u0103\u0104\7\b\2\2\u0104"+
		"+\3\2\2\2\u0105\u0106\7\37\2\2\u0106\u0107\7\7\2\2\u0107\u010c\5\4\3\2"+
		"\u0108\u0109\7\4\2\2\u0109\u010b\5\4\3\2\u010a\u0108\3\2\2\2\u010b\u010e"+
		"\3\2\2\2\u010c\u010a\3\2\2\2\u010c\u010d\3\2\2\2\u010d\u010f\3\2\2\2\u010e"+
		"\u010c\3\2\2\2\u010f\u0110\7\31\2\2\u0110\u0111\7\b\2\2\u0111\u0123\3"+
		"\2\2\2\u0112\u0113\7\37\2\2\u0113\u011c\7\7\2\2\u0114\u0119\5\4\3\2\u0115"+
		"\u0116\7\4\2\2\u0116\u0118\5\4\3\2\u0117\u0115\3\2\2\2\u0118\u011b\3\2"+
		"\2\2\u0119\u0117\3\2\2\2\u0119\u011a\3\2\2\2\u011a\u011d\3\2\2\2\u011b"+
		"\u0119\3\2\2\2\u011c\u0114\3\2\2\2\u011c\u011d\3\2\2\2\u011d\u011e\3\2"+
		"\2\2\u011e\u011f\7\31\2\2\u011f\u0120\5\4\3\2\u0120\u0121\7\b\2\2\u0121"+
		"\u0123\3\2\2\2\u0122\u0105\3\2\2\2\u0122\u0112\3\2\2\2\u0123-\3\2\2\2"+
		"\u0124\u0125\7 \2\2\u0125\u0126\7\7\2\2\u0126\u0127\5B\"\2\u0127\u0128"+
		"\7\4\2\2\u0128\u0129\5B\"\2\u0129\u012a\7\31\2\2\u012a\u012b\5\4\3\2\u012b"+
		"\u012c\7\b\2\2\u012c/\3\2\2\2\u012d\u012e\7!\2\2\u012e\u013b\7\22\2\2"+
		"\u012f\u0130\7\62\2\2\u0130\u0131\7\"\2\2\u0131\u0138\5\4\3\2\u0132\u0133"+
		"\7\4\2\2\u0133\u0134\7\62\2\2\u0134\u0135\7\"\2\2\u0135\u0137\5\4\3\2"+
		"\u0136\u0132\3\2\2\2\u0137\u013a\3\2\2\2\u0138\u0136\3\2\2\2\u0138\u0139"+
		"\3\2\2\2\u0139\u013c\3\2\2\2\u013a\u0138\3\2\2\2\u013b\u012f\3\2\2\2\u013b"+
		"\u013c\3\2\2\2\u013c\u013d\3\2\2\2\u013d\u013e\7\31\2\2\u013e\u013f\5"+
		"\4\3\2\u013f\u0140\7\23\2\2\u0140\u0154\3\2\2\2\u0141\u0142\7!\2\2\u0142"+
		"\u014f\7\22\2\2\u0143\u0144\7\62\2\2\u0144\u0145\7\"\2\2\u0145\u014c\5"+
		"\4\3\2\u0146\u0147\7\4\2\2\u0147\u0148\7\62\2\2\u0148\u0149\7\"\2\2\u0149"+
		"\u014b\5\4\3\2\u014a\u0146\3\2\2\2\u014b\u014e\3\2\2\2\u014c\u014a\3\2"+
		"\2\2\u014c\u014d\3\2\2\2\u014d\u0150\3\2\2\2\u014e\u014c\3\2\2\2\u014f"+
		"\u0143\3\2\2\2\u014f\u0150\3\2\2\2\u0150\u0151\3\2\2\2\u0151\u0152\7\31"+
		"\2\2\u0152\u0154\7\23\2\2\u0153\u012d\3\2\2\2\u0153\u0141\3\2\2\2\u0154"+
		"\61\3\2\2\2\u0155\u0156\7#\2\2\u0156\u0157\7\7\2\2\u0157\u0158\5B\"\2"+
		"\u0158\u0159\7\b\2\2\u0159\63\3\2\2\2\u015a\u015b\7$\2\2\u015b\u015c\7"+
		"\62\2\2\u015c\u015d\7%\2\2\u015d\u0165\5\4\3\2\u015e\u015f\7\4\2\2\u015f"+
		"\u0160\7$\2\2\u0160\u0161\7\62\2\2\u0161\u0162\7%\2\2\u0162\u0164\5\4"+
		"\3\2\u0163\u015e\3\2\2\2\u0164\u0167\3\2\2\2\u0165\u0163\3\2\2\2\u0165"+
		"\u0166\3\2\2\2\u0166\u0176\3\2\2\2\u0167\u0165\3\2\2\2\u0168\u0169\7&"+
		"\2\2\u0169\u016a\7%\2\2\u016a\u0172\5\4\3\2\u016b\u016c\7\4\2\2\u016c"+
		"\u016d\7$\2\2\u016d\u016e\7\62\2\2\u016e\u016f\7%\2\2\u016f\u0171\5\4"+
		"\3\2\u0170\u016b\3\2\2\2\u0171\u0174\3\2\2\2\u0172\u0170\3\2\2\2\u0172"+
		"\u0173\3\2\2\2\u0173\u0176\3\2\2\2\u0174\u0172\3\2\2\2\u0175\u015a\3\2"+
		"\2\2\u0175\u0168\3\2\2\2\u0176\65\3\2\2\2\u0177\u0178\7\'\2\2\u0178\u0179"+
		"\7\7\2\2\u0179\u017a\7\62\2\2\u017a\u017b\7\b\2\2\u017b\67\3\2\2\2\u017c"+
		"\u017d\7(\2\2\u017d\u017e\7\7\2\2\u017e\u017f\5\4\3\2\u017f\u0180\7\b"+
		"\2\2\u01809\3\2\2\2\u0181\u0182\7)\2\2\u0182\u0183\7\7\2\2\u0183\u0184"+
		"\5\4\3\2\u0184\u0185\7\b\2\2\u0185;\3\2\2\2\u0186\u0187\7*\2\2\u0187\u0188"+
		"\7\22\2\2\u0188\u0189\7\62\2\2\u0189\u018a\7\"\2\2\u018a\u0191\7\62\2"+
		"\2\u018b\u018c\7\4\2\2\u018c\u018d\7\62\2\2\u018d\u018e\7\"\2\2\u018e"+
		"\u0190\7\62\2\2\u018f\u018b\3\2\2\2\u0190\u0193\3\2\2\2\u0191\u018f\3"+
		"\2\2\2\u0191\u0192\3\2\2\2\u0192\u0194\3\2\2\2\u0193\u0191\3\2\2\2\u0194"+
		"\u0195\7\23\2\2\u0195=\3\2\2\2\u0196\u0197\7+\2\2\u0197\u0198\7\22\2\2"+
		"\u0198\u0199\7\62\2\2\u0199\u019a\7\"\2\2\u019a\u01a1\5\4\3\2\u019b\u019c"+
		"\7\4\2\2\u019c\u019d\7\62\2\2\u019d\u019e\7\"\2\2\u019e\u01a0\5\4\3\2"+
		"\u019f\u019b\3\2\2\2\u01a0\u01a3\3\2\2\2\u01a1\u019f\3\2\2\2\u01a1\u01a2"+
		"\3\2\2\2\u01a2\u01a4\3\2\2\2\u01a3\u01a1\3\2\2\2\u01a4\u01a5\7\23\2\2"+
		"\u01a5?\3\2\2\2\u01a6\u01a7\7,\2\2\u01a7\u01a8\7\7\2\2\u01a8\u01ae\7\22"+
		"\2\2\u01a9\u01aa\7\62\2\2\u01aa\u01ab\7\4\2\2\u01ab\u01ad\7\62\2\2\u01ac"+
		"\u01a9\3\2\2\2\u01ad\u01b0\3\2\2\2\u01ae\u01ac\3\2\2\2\u01ae\u01af\3\2"+
		"\2\2\u01af\u01b1\3\2\2\2\u01b0\u01ae\3\2\2\2\u01b1\u01b2\7\23\2\2\u01b2"+
		"\u01b3\7\"\2\2\u01b3\u01b4\5\4\3\2\u01b4\u01b5\7\b\2\2\u01b5A\3\2\2\2"+
		"\u01b6\u01d8\7-\2\2\u01b7\u01d8\7/\2\2\u01b8\u01d8\7\62\2\2\u01b9\u01d8"+
		"\7\60\2\2\u01ba\u01c3\7\22\2\2\u01bb\u01c0\5B\"\2\u01bc\u01bd\7\4\2\2"+
		"\u01bd\u01bf\5B\"\2\u01be\u01bc\3\2\2\2\u01bf\u01c2\3\2\2\2\u01c0\u01be"+
		"\3\2\2\2\u01c0\u01c1\3\2\2\2\u01c1\u01c4\3\2\2\2\u01c2\u01c0\3\2\2\2\u01c3"+
		"\u01bb\3\2\2\2\u01c3\u01c4\3\2\2\2\u01c4\u01c5\3\2\2\2\u01c5\u01d8\7\23"+
		"\2\2\u01c6\u01d8\7\63\2\2\u01c7\u01d4\7\3\2\2\u01c8\u01c9\7\62\2\2\u01c9"+
		"\u01ca\7\"\2\2\u01ca\u01d1\5B\"\2\u01cb\u01cc\7\4\2\2\u01cc\u01cd\7\62"+
		"\2\2\u01cd\u01ce\7\"\2\2\u01ce\u01d0\5B\"\2\u01cf\u01cb\3\2\2\2\u01d0"+
		"\u01d3\3\2\2\2\u01d1\u01cf\3\2\2\2\u01d1\u01d2\3\2\2\2\u01d2\u01d5\3\2"+
		"\2\2\u01d3\u01d1\3\2\2\2\u01d4\u01c8\3\2\2\2\u01d4\u01d5\3\2\2\2\u01d5"+
		"\u01d6\3\2\2\2\u01d6\u01d8\7\5\2\2\u01d7\u01b6\3\2\2\2\u01d7\u01b7\3\2"+
		"\2\2\u01d7\u01b8\3\2\2\2\u01d7\u01b9\3\2\2\2\u01d7\u01ba\3\2\2\2\u01d7"+
		"\u01c6\3\2\2\2\u01d7\u01c7\3\2\2\2\u01d8C\3\2\2\2\37JPq\u00b1\u00bd\u00c9"+
		"\u00d5\u00e0\u00f5\u010c\u0119\u011c\u0122\u0138\u013b\u014c\u014f\u0153"+
		"\u0165\u0172\u0175\u0191\u01a1\u01ae\u01c0\u01c3\u01d1\u01d4\u01d7";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}