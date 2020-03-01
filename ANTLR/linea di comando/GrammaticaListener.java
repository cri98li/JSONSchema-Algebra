// Generated from Grammatica.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GrammaticaParser}.
 */
public interface GrammaticaListener extends ParseTreeListener {
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
	 * Enter a parse tree produced by {@link GrammaticaParser#coppia}.
	 * @param ctx the parse tree
	 */
	void enterCoppia(GrammaticaParser.CoppiaContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammaticaParser#coppia}.
	 * @param ctx the parse tree
	 */
	void exitCoppia(GrammaticaParser.CoppiaContext ctx);
}