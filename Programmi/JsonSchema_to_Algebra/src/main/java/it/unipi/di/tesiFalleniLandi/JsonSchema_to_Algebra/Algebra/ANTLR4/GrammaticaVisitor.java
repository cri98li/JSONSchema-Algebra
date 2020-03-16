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
	 * Visit a parse tree produced by the {@code list}
	 * labeled alternative in {@link GrammaticaParser#assertion_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitList(GrammaticaParser.ListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeAssertion}
	 * labeled alternative in {@link GrammaticaParser#assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeAssertion(GrammaticaParser.TypeAssertionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code newList}
	 * labeled alternative in {@link GrammaticaParser#assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewList(GrammaticaParser.NewListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code betweenAssertion}
	 * labeled alternative in {@link GrammaticaParser#assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBetweenAssertion(GrammaticaParser.BetweenAssertionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code xBetweenAssertion}
	 * labeled alternative in {@link GrammaticaParser#assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXBetweenAssertion(GrammaticaParser.XBetweenAssertionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code betweenItems}
	 * labeled alternative in {@link GrammaticaParser#assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBetweenItems(GrammaticaParser.BetweenItemsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Length}
	 * labeled alternative in {@link GrammaticaParser#assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLength(GrammaticaParser.LengthContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BetweenProperties}
	 * labeled alternative in {@link GrammaticaParser#assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBetweenProperties(GrammaticaParser.BetweenPropertiesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code multipleOf}
	 * labeled alternative in {@link GrammaticaParser#assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultipleOf(GrammaticaParser.MultipleOfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code allOf}
	 * labeled alternative in {@link GrammaticaParser#assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAllOf(GrammaticaParser.AllOfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code anyOf}
	 * labeled alternative in {@link GrammaticaParser#assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnyOf(GrammaticaParser.AnyOfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code oneOf}
	 * labeled alternative in {@link GrammaticaParser#assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOneOf(GrammaticaParser.OneOfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Not}
	 * labeled alternative in {@link GrammaticaParser#assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNot(GrammaticaParser.NotContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Required}
	 * labeled alternative in {@link GrammaticaParser#assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRequired(GrammaticaParser.RequiredContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UniqueItems}
	 * labeled alternative in {@link GrammaticaParser#assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUniqueItems(GrammaticaParser.UniqueItemsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Pattern}
	 * labeled alternative in {@link GrammaticaParser#assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPattern(GrammaticaParser.PatternContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Items}
	 * labeled alternative in {@link GrammaticaParser#assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitItems(GrammaticaParser.ItemsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Contains}
	 * labeled alternative in {@link GrammaticaParser#assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContains(GrammaticaParser.ContainsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Enum}
	 * labeled alternative in {@link GrammaticaParser#assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnum(GrammaticaParser.EnumContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifThenElse}
	 * labeled alternative in {@link GrammaticaParser#assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfThenElse(GrammaticaParser.IfThenElseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Const}
	 * labeled alternative in {@link GrammaticaParser#assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConst(GrammaticaParser.ConstContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammaticaParser#numeric_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumeric_value(GrammaticaParser.Numeric_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammaticaParser#type_assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType_assertion(GrammaticaParser.Type_assertionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammaticaParser#between_assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBetween_assertion(GrammaticaParser.Between_assertionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammaticaParser#xbetween_assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXbetween_assertion(GrammaticaParser.Xbetween_assertionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammaticaParser#length_assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLength_assertion(GrammaticaParser.Length_assertionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammaticaParser#bet_items_assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBet_items_assertion(GrammaticaParser.Bet_items_assertionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammaticaParser#between_properties_assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBetween_properties_assertion(GrammaticaParser.Between_properties_assertionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammaticaParser#multiple_of_assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiple_of_assertion(GrammaticaParser.Multiple_of_assertionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammaticaParser#all_of_assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAll_of_assertion(GrammaticaParser.All_of_assertionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammaticaParser#one_of_assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOne_of_assertion(GrammaticaParser.One_of_assertionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammaticaParser#any_of_assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAny_of_assertion(GrammaticaParser.Any_of_assertionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammaticaParser#not_assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNot_assertion(GrammaticaParser.Not_assertionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammaticaParser#required_assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRequired_assertion(GrammaticaParser.Required_assertionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammaticaParser#unique_items_assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnique_items_assertion(GrammaticaParser.Unique_items_assertionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammaticaParser#pattern_assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPattern_assertion(GrammaticaParser.Pattern_assertionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammaticaParser#items_assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitItems_assertion(GrammaticaParser.Items_assertionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammaticaParser#contains_assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContains_assertion(GrammaticaParser.Contains_assertionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammaticaParser#enum_assertion_assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnum_assertion_assertion(GrammaticaParser.Enum_assertion_assertionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammaticaParser#if_then_else_assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_then_else_assertion(GrammaticaParser.If_then_else_assertionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammaticaParser#const_assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConst_assertion(GrammaticaParser.Const_assertionContext ctx);
}