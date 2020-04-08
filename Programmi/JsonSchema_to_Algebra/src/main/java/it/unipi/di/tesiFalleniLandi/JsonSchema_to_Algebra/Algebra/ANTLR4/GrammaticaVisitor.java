// Generated from Grammatica.g4 by ANTLR 4.7.2
package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link GrammaticaParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface GrammaticaVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code ParseAssertionList}
	 * labeled alternative in {@link GrammaticaParser#assertion_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParseAssertionList(GrammaticaParser.ParseAssertionListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParseBooleanSchema}
	 * labeled alternative in {@link GrammaticaParser#assertion_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParseBooleanSchema(GrammaticaParser.ParseBooleanSchemaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NewTypeAssertion}
	 * labeled alternative in {@link GrammaticaParser#assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewTypeAssertion(GrammaticaParser.NewTypeAssertionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NewBetweenAssertion}
	 * labeled alternative in {@link GrammaticaParser#assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewBetweenAssertion(GrammaticaParser.NewBetweenAssertionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NewNot}
	 * labeled alternative in {@link GrammaticaParser#assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewNot(GrammaticaParser.NewNotContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NewXBetweenAssertion}
	 * labeled alternative in {@link GrammaticaParser#assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewXBetweenAssertion(GrammaticaParser.NewXBetweenAssertionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NewBetweenItems}
	 * labeled alternative in {@link GrammaticaParser#assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewBetweenItems(GrammaticaParser.NewBetweenItemsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NewLength}
	 * labeled alternative in {@link GrammaticaParser#assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewLength(GrammaticaParser.NewLengthContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NewBetweenProperties}
	 * labeled alternative in {@link GrammaticaParser#assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewBetweenProperties(GrammaticaParser.NewBetweenPropertiesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NewAllOf}
	 * labeled alternative in {@link GrammaticaParser#assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewAllOf(GrammaticaParser.NewAllOfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NewAnyOf}
	 * labeled alternative in {@link GrammaticaParser#assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewAnyOf(GrammaticaParser.NewAnyOfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NewOneOf}
	 * labeled alternative in {@link GrammaticaParser#assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewOneOf(GrammaticaParser.NewOneOfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NewRequired}
	 * labeled alternative in {@link GrammaticaParser#assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewRequired(GrammaticaParser.NewRequiredContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NewIfThenElse}
	 * labeled alternative in {@link GrammaticaParser#assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewIfThenElse(GrammaticaParser.NewIfThenElseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NewMultipleOf}
	 * labeled alternative in {@link GrammaticaParser#assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewMultipleOf(GrammaticaParser.NewMultipleOfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NewEnum}
	 * labeled alternative in {@link GrammaticaParser#assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewEnum(GrammaticaParser.NewEnumContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NewUniqueItems}
	 * labeled alternative in {@link GrammaticaParser#assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewUniqueItems(GrammaticaParser.NewUniqueItemsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NewPattern}
	 * labeled alternative in {@link GrammaticaParser#assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewPattern(GrammaticaParser.NewPatternContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NewContains}
	 * labeled alternative in {@link GrammaticaParser#assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewContains(GrammaticaParser.NewContainsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NewConst}
	 * labeled alternative in {@link GrammaticaParser#assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewConst(GrammaticaParser.NewConstContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NewItems}
	 * labeled alternative in {@link GrammaticaParser#assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewItems(GrammaticaParser.NewItemsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NewAssertionList}
	 * labeled alternative in {@link GrammaticaParser#assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewAssertionList(GrammaticaParser.NewAssertionListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NewProperties}
	 * labeled alternative in {@link GrammaticaParser#assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewProperties(GrammaticaParser.NewPropertiesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NewDef}
	 * labeled alternative in {@link GrammaticaParser#assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewDef(GrammaticaParser.NewDefContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NewRef}
	 * labeled alternative in {@link GrammaticaParser#assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewRef(GrammaticaParser.NewRefContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NewPropertyNames}
	 * labeled alternative in {@link GrammaticaParser#assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewPropertyNames(GrammaticaParser.NewPropertyNamesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NewAnnotations}
	 * labeled alternative in {@link GrammaticaParser#assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewAnnotations(GrammaticaParser.NewAnnotationsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParseTypeAssertion}
	 * labeled alternative in {@link GrammaticaParser#type_assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParseTypeAssertion(GrammaticaParser.ParseTypeAssertionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParseBetweenAssertion}
	 * labeled alternative in {@link GrammaticaParser#between_assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParseBetweenAssertion(GrammaticaParser.ParseBetweenAssertionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParseXBetweenAssertion}
	 * labeled alternative in {@link GrammaticaParser#xbetween_assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParseXBetweenAssertion(GrammaticaParser.ParseXBetweenAssertionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParseLengthAssertion}
	 * labeled alternative in {@link GrammaticaParser#length_assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParseLengthAssertion(GrammaticaParser.ParseLengthAssertionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParseBetItemsAssertion}
	 * labeled alternative in {@link GrammaticaParser#bet_items_assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParseBetItemsAssertion(GrammaticaParser.ParseBetItemsAssertionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParseBetProAssertion}
	 * labeled alternative in {@link GrammaticaParser#between_properties_assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParseBetProAssertion(GrammaticaParser.ParseBetProAssertionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParseMultipleOf}
	 * labeled alternative in {@link GrammaticaParser#multiple_of_assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParseMultipleOf(GrammaticaParser.ParseMultipleOfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParseNot}
	 * labeled alternative in {@link GrammaticaParser#not_assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParseNot(GrammaticaParser.ParseNotContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParseAllOf}
	 * labeled alternative in {@link GrammaticaParser#all_of_assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParseAllOf(GrammaticaParser.ParseAllOfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParseOneOf}
	 * labeled alternative in {@link GrammaticaParser#one_of_assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParseOneOf(GrammaticaParser.ParseOneOfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParseAnyOf}
	 * labeled alternative in {@link GrammaticaParser#any_of_assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParseAnyOf(GrammaticaParser.ParseAnyOfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParseRequired}
	 * labeled alternative in {@link GrammaticaParser#required_assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParseRequired(GrammaticaParser.ParseRequiredContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParseEnum}
	 * labeled alternative in {@link GrammaticaParser#enum_assertion_assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParseEnum(GrammaticaParser.ParseEnumContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParseIfThenElse}
	 * labeled alternative in {@link GrammaticaParser#if_then_else_assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParseIfThenElse(GrammaticaParser.ParseIfThenElseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParseIfThen}
	 * labeled alternative in {@link GrammaticaParser#if_then_else_assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParseIfThen(GrammaticaParser.ParseIfThenContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParseUniqueItems}
	 * labeled alternative in {@link GrammaticaParser#unique_items_assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParseUniqueItems(GrammaticaParser.ParseUniqueItemsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParsePattern}
	 * labeled alternative in {@link GrammaticaParser#pattern_assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParsePattern(GrammaticaParser.ParsePatternContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParseOnlyItems}
	 * labeled alternative in {@link GrammaticaParser#items_assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParseOnlyItems(GrammaticaParser.ParseOnlyItemsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParseAdditionalItems}
	 * labeled alternative in {@link GrammaticaParser#items_assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParseAdditionalItems(GrammaticaParser.ParseAdditionalItemsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParseContains}
	 * labeled alternative in {@link GrammaticaParser#contains_assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParseContains(GrammaticaParser.ParseContainsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParseAdditionalProperties}
	 * labeled alternative in {@link GrammaticaParser#properties}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParseAdditionalProperties(GrammaticaParser.ParseAdditionalPropertiesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParseProperties}
	 * labeled alternative in {@link GrammaticaParser#properties}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParseProperties(GrammaticaParser.ParsePropertiesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParseConst}
	 * labeled alternative in {@link GrammaticaParser#const_assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParseConst(GrammaticaParser.ParseConstContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParseDef}
	 * labeled alternative in {@link GrammaticaParser#def_assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParseDef(GrammaticaParser.ParseDefContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParseDefRoot}
	 * labeled alternative in {@link GrammaticaParser#def_assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParseDefRoot(GrammaticaParser.ParseDefRootContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParseRef}
	 * labeled alternative in {@link GrammaticaParser#ref_assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParseRef(GrammaticaParser.ParseRefContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParsePropertyNames}
	 * labeled alternative in {@link GrammaticaParser#propertyNames}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParsePropertyNames(GrammaticaParser.ParsePropertyNamesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParseAnnotations}
	 * labeled alternative in {@link GrammaticaParser#annotations}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParseAnnotations(GrammaticaParser.ParseAnnotationsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NullValue}
	 * labeled alternative in {@link GrammaticaParser#json_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNullValue(GrammaticaParser.NullValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IntValue}
	 * labeled alternative in {@link GrammaticaParser#json_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntValue(GrammaticaParser.IntValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StringValue}
	 * labeled alternative in {@link GrammaticaParser#json_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringValue(GrammaticaParser.StringValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DoubleValue}
	 * labeled alternative in {@link GrammaticaParser#json_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoubleValue(GrammaticaParser.DoubleValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ArrayValue}
	 * labeled alternative in {@link GrammaticaParser#json_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayValue(GrammaticaParser.ArrayValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BooleanValue}
	 * labeled alternative in {@link GrammaticaParser#json_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanValue(GrammaticaParser.BooleanValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code JsonObjectValue}
	 * labeled alternative in {@link GrammaticaParser#json_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJsonObjectValue(GrammaticaParser.JsonObjectValueContext ctx);
}