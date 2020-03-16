package it.unipi.di.tesiFalleniLandi.antlr4;

// Generated from Grammatica.g4 by ANTLR 4.7.2
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
	 * labeled alternative in {@link GrammaticaParser#s}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeAssertion(GrammaticaParser.TypeAssertionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code newList}
	 * labeled alternative in {@link GrammaticaParser#s}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewList(GrammaticaParser.NewListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code betweenAssertion}
	 * labeled alternative in {@link GrammaticaParser#s}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBetweenAssertion(GrammaticaParser.BetweenAssertionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammaticaParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(GrammaticaParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammaticaParser#between}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBetween(GrammaticaParser.BetweenContext ctx);
}