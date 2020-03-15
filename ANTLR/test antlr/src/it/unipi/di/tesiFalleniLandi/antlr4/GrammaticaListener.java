// Generated from Grammatica.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GrammaticaParser}.
 */
public interface GrammaticaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code list}
	 * labeled alternative in {@link GrammaticaParser#assertion_list}.
	 * @param ctx the parse tree
	 */
	void enterList(GrammaticaParser.ListContext ctx);
	/**
	 * Exit a parse tree produced by the {@code list}
	 * labeled alternative in {@link GrammaticaParser#assertion_list}.
	 * @param ctx the parse tree
	 */
	void exitList(GrammaticaParser.ListContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TypeAssertion}
	 * labeled alternative in {@link GrammaticaParser#s}.
	 * @param ctx the parse tree
	 */
	void enterTypeAssertion(GrammaticaParser.TypeAssertionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TypeAssertion}
	 * labeled alternative in {@link GrammaticaParser#s}.
	 * @param ctx the parse tree
	 */
	void exitTypeAssertion(GrammaticaParser.TypeAssertionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code newList}
	 * labeled alternative in {@link GrammaticaParser#s}.
	 * @param ctx the parse tree
	 */
	void enterNewList(GrammaticaParser.NewListContext ctx);
	/**
	 * Exit a parse tree produced by the {@code newList}
	 * labeled alternative in {@link GrammaticaParser#s}.
	 * @param ctx the parse tree
	 */
	void exitNewList(GrammaticaParser.NewListContext ctx);
	/**
	 * Enter a parse tree produced by the {@code betweenAssertion}
	 * labeled alternative in {@link GrammaticaParser#s}.
	 * @param ctx the parse tree
	 */
	void enterBetweenAssertion(GrammaticaParser.BetweenAssertionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code betweenAssertion}
	 * labeled alternative in {@link GrammaticaParser#s}.
	 * @param ctx the parse tree
	 */
	void exitBetweenAssertion(GrammaticaParser.BetweenAssertionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammaticaParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(GrammaticaParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammaticaParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(GrammaticaParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammaticaParser#between}.
	 * @param ctx the parse tree
	 */
	void enterBetween(GrammaticaParser.BetweenContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammaticaParser#between}.
	 * @param ctx the parse tree
	 */
	void exitBetween(GrammaticaParser.BetweenContext ctx);
}