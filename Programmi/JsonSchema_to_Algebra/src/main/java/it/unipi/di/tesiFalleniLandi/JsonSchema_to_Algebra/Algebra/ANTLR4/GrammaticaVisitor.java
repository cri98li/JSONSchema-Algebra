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
	 * Visit a parse tree produced by the {@code NewTypeAssertion}
	 * labeled alternative in {@link GrammaticaParser#assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewTypeAssertion(GrammaticaParser.NewTypeAssertionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NewList}
	 * labeled alternative in {@link GrammaticaParser#assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewList(GrammaticaParser.NewListContext ctx);
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
	 * Visit a parse tree produced by the {@code ParseNot}
	 * labeled alternative in {@link GrammaticaParser#not_assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParseNot(GrammaticaParser.ParseNotContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NullValue}
	 * labeled alternative in {@link GrammaticaParser#numeric_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNullValue(GrammaticaParser.NullValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NumericValue}
	 * labeled alternative in {@link GrammaticaParser#numeric_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumericValue(GrammaticaParser.NumericValueContext ctx);
}