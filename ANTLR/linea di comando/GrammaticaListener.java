// Generated from Grammatica.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GrammaticaParser}.
 */
public interface GrammaticaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GrammaticaParser#init}.
	 * @param ctx the parse tree
	 */
	void enterInit(GrammaticaParser.InitContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammaticaParser#init}.
	 * @param ctx the parse tree
	 */
	void exitInit(GrammaticaParser.InitContext ctx);
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
}