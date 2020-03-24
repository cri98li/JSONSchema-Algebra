package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra;

import java.util.List;

import org.antlr.v4.runtime.tree.TerminalNode;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4.GrammaticaBaseVisitor;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4.GrammaticaParser;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4.GrammaticaParser.AdditionalPropertiesContext;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4.GrammaticaParser.AssertionContext;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4.GrammaticaParser.Json_valueContext;

public class Parser extends GrammaticaBaseVisitor<Assertion>{


	/*@Override 
	public AntrlList visitNewList(GrammaticaParser.NewListContext ctx) { 
		AntrlList list = new AntrlList();
		list.add(visit(ctx.assertion(0)));
		list.add(visit(ctx.assertion(1)));
		return list;
	}*/
	
	public AntrlList visitParseAssertionList(GrammaticaParser.ParseAssertionListContext ctx) {
		AntrlList list = new AntrlList();
		
		List<AssertionContext> keywords = ctx.assertion();
		
		for(AssertionContext key : keywords) {
			Assertion schema = visit(key);
			list.add(schema);
		}
		
		return list; 
	}
	
	@Override
	public Bet_Assertion visitNewBetweenAssertion(GrammaticaParser.NewBetweenAssertionContext ctx) {
		
		Bet_Assertion bet = (Bet_Assertion) visit(ctx.between_assertion());

		return bet;
	}
	
	@Override
	public Bet_Assertion visitParseBetweenAssertion(GrammaticaParser.ParseBetweenAssertionContext ctx) {
		
		IntegerAntlr min = (IntegerAntlr) visit(ctx.json_value(0));
		IntegerAntlr max = (IntegerAntlr) visit(ctx.json_value(1));
		
		return new Bet_Assertion(min.getValue(), max.getValue());
	}
	
	@Override
	public Bet_Assertion visitNewXBetweenAssertion(GrammaticaParser.NewXBetweenAssertionContext ctx) {
		
		Bet_Assertion bet = (Bet_Assertion) visit(ctx.xbetween_assertion());

		return bet;
	}
	
	@Override
	public Bet_Assertion visitParseXBetweenAssertion(GrammaticaParser.ParseXBetweenAssertionContext ctx) {
		
		IntegerAntlr min = (IntegerAntlr) visit(ctx.json_value(0));
		IntegerAntlr max = (IntegerAntlr) visit(ctx.json_value(1));
		
		return new Bet_Assertion(min.getValue(), max.getValue());
	}
	

	@Override 
	public IntegerAntlr visitNumericValue(GrammaticaParser.NumericValueContext ctx) { 
		
		return new IntegerAntlr(Integer.valueOf(ctx.INT().getText()));
	}
	
	
	@Override
	public IntegerAntlr visitNullValue(GrammaticaParser.NullValueContext ctx) {
		IntegerAntlr value = new IntegerAntlr(null);

		return value;
	}

	@Override
	public Type_Assertion visitNewTypeAssertion(GrammaticaParser.NewTypeAssertionContext ctx) {  
		AntlrString type = (AntlrString) visit(ctx.type_assertion());
		
		return new Type_Assertion(type.getValue()); 
	}
	
	@Override
	public AntlrString visitParseTypeAssertion(GrammaticaParser.ParseTypeAssertionContext ctx) { 
		String type = ctx.TYPE().getText();
		
		return new AntlrString(type); 
	}
	
	@Override
	public Not_Assertion visitNewNot(GrammaticaParser.NewNotContext ctx) {
		
		return (Not_Assertion) visit(ctx.not_assertion());
	}
	
	@Override
	public Not_Assertion visitParseNot(GrammaticaParser.ParseNotContext ctx) {
		
		return new Not_Assertion(visit(ctx.assertion()));
	}
	
	@Override 
	public And_Assertion visitNewAllOf(GrammaticaParser.NewAllOfContext ctx) { 

		return (And_Assertion) visit(ctx.all_of_assertion()); 
	}
	
	@Override 
	public And_Assertion visitParseAllOf(GrammaticaParser.ParseAllOfContext ctx) {
		List<AssertionContext> list = ctx.assertion();
		And_Assertion and = new And_Assertion();
		
		for(AssertionContext el : list)
			and.add(visit(el));
		
		return and; 
	}
	
	@Override 
	public Or_Assertion visitNewAnyOf(GrammaticaParser.NewAnyOfContext ctx) { 

		return (Or_Assertion) visit(ctx.any_of_assertion()); 
	}
	
	@Override 
	public Or_Assertion visitParseAnyOf(GrammaticaParser.ParseAnyOfContext ctx) {
		List<AssertionContext> list = ctx.assertion();
		Or_Assertion or = new Or_Assertion();
		
		for(AssertionContext el : list)
			or.add(visit(el));
		
		return or; 
	}
	
	@Override 
	public Xor_Assertion visitNewOneOf(GrammaticaParser.NewOneOfContext ctx) { 

		return (Xor_Assertion) visit(ctx.one_of_assertion()); 
	}
	
	@Override 
	public Xor_Assertion visitParseOneOf(GrammaticaParser.ParseOneOfContext ctx) {
		List<AssertionContext> list = ctx.assertion();
		Xor_Assertion xor = new Xor_Assertion();
		
		for(AssertionContext el : list)
			xor.add(visit(el));
		
		return xor; 
	}
	
	@Override 
	public Required_Assertion visitNewRequired(GrammaticaParser.NewRequiredContext ctx) { 
		
		return (Required_Assertion) visit(ctx.required_assertion());
	}
	
	@Override 
	public Required_Assertion visitParseRequired(GrammaticaParser.ParseRequiredContext ctx) {
		Required_Assertion req = new Required_Assertion();
				
		List<TerminalNode> strList = ctx.STRING();
		
		for(TerminalNode str : strList) {
			req.add(str.getText());
		}
		
		return req;
	}
	
	@Override 
	public IfThenElse_Assertion visitNewIfThenElse(GrammaticaParser.NewIfThenElseContext ctx) { 
		
		return (IfThenElse_Assertion) visit(ctx.if_then_else_assertion());
	}
	
	@Override 
	public IfThenElse_Assertion visitParseIfThenElse(GrammaticaParser.ParseIfThenElseContext ctx) { 
		Assertion ifStat = visit(ctx.assertion(0));
		Assertion thenStat = visit(ctx.assertion(1));
		Assertion elseStat = visit(ctx.assertion(2));
		
		return  new IfThenElse_Assertion(ifStat, thenStat, elseStat);
	}
	
	@Override 
	public IfThenElse_Assertion visitParseIfThen(GrammaticaParser.ParseIfThenContext ctx) { 
		Assertion ifStat = visit(ctx.assertion(0));
		Assertion thenStat = visit(ctx.assertion(1));
		
		return new IfThenElse_Assertion(ifStat, thenStat, null); 
	}
	
	@Override
	public Enum_Assertion visitNewEnum(GrammaticaParser.NewEnumContext ctx) { 
		
		return (Enum_Assertion) visit(ctx.enum_assertion_assertion()); 
	}
	
	@Override
	public Enum_Assertion visitParseEnum(GrammaticaParser.ParseEnumContext ctx) { 
		Enum_Assertion _enum = new Enum_Assertion();
		
		List<Json_valueContext> valueList = ctx.json_value();
		
		//trattare null con classe NullAntlr ?
		for(Json_valueContext value : valueList) {
			AntlrValue tmp = (AntlrValue) visit(value);
			_enum.add(tmp.getValue().toString());
		}
		
		return _enum;
	}
	
	@Override 
	public Mof_Assertion visitNewMultipleOf(GrammaticaParser.NewMultipleOfContext ctx) { 
		
		return (Mof_Assertion) visit(ctx.multiple_of_assertion()); 
	}
	
	@Override 
	public Mof_Assertion visitParseMultipleOf(GrammaticaParser.ParseMultipleOfContext ctx) { 
		
		IntegerAntlr value = (IntegerAntlr) visit(ctx.json_value());
		
		return new Mof_Assertion(value.getValue()); 
	}
	
	@Override 
	public AntlrString visitStringValue(GrammaticaParser.StringValueContext ctx) { 
		
		return new AntlrString(ctx.STRING().getText()); 
	}
	
	@Override 
	public UniqueItems_Assertion visitNewUniqueItems(GrammaticaParser.NewUniqueItemsContext ctx) {
		
		return (UniqueItems_Assertion) visit(ctx.unique_items_assertion()); 
	}
		
	@Override 
	public UniqueItems_Assertion visitParseUniqueItems(GrammaticaParser.ParseUniqueItemsContext ctx) {
		
		return new UniqueItems_Assertion(); 
	}
	
	@Override 
	public Pattern_Assertion visitNewPattern(GrammaticaParser.NewPatternContext ctx) {
		
		return (Pattern_Assertion) visit(ctx.pattern_assertion()); 
	}

	@Override 
	public Pattern_Assertion visitParsePattern(GrammaticaParser.ParsePatternContext ctx) {
		
		return new Pattern_Assertion(ctx.STRING().getText()); 
	}
	
	@Override 
	public Exist_Assertion visitNewContains(GrammaticaParser.NewContainsContext ctx) { 
		return (Exist_Assertion) visit(ctx.contains_assertion()); 
	}
	
	@Override 
	public Exist_Assertion visitParseContains(GrammaticaParser.ParseContainsContext ctx) { 
		
		IntegerAntlr min = (IntegerAntlr) visit(ctx.json_value(0));
		IntegerAntlr max = (IntegerAntlr) visit(ctx.json_value(1));
		Assertion schema = visit(ctx.assertion());
		
		return new Exist_Assertion(min.getValue(), max.getValue(), schema); 
	}
	
	@Override 
	public Const_Assertion visitNewConst(GrammaticaParser.NewConstContext ctx) { 
		return (Const_Assertion) visit(ctx.const_assertion()); 
	}
	
	@Override 
	public Const_Assertion visitParseConst(GrammaticaParser.ParseConstContext ctx) { 
		AntlrValue value = (AntlrValue) visit(ctx.json_value());
		
		return new Const_Assertion(value.getValue().toString()); 
	}
	
	@Override 
	public Items_Assertion visitNewItems(GrammaticaParser.NewItemsContext ctx) {
		
		return (Items_Assertion) visit(ctx.items_assertion()); 
	}
	
	@Override 
	public Items_Assertion visitParseItems(GrammaticaParser.ParseItemsContext ctx) { 
		Assertion schema = visit(ctx.assertion());
		Items_Assertion items= new Items_Assertion();
		items.setAdditionalItems(schema);
		
		return items; 
	}

	@Override 
	public Items_Assertion visitParseItemsArray(GrammaticaParser.ParseItemsArrayContext ctx) {
		Items_Assertion items = new Items_Assertion();
		List<AssertionContext> list = ctx.assertion();
		int count = 0;

		for(AssertionContext item : list) {
			Assertion schema = visit(item);
			if(count == list.size() - 1)
				items.setAdditionalItems(schema);
			else
				items.add(schema);
			
			count++;
		}
		
		return items; 
	}

	
	@Override
	public Properties_Assertion visitParseProperties(GrammaticaParser.ParsePropertiesContext ctx) {
		Properties_Assertion p = new Properties_Assertion();
		
		List<AssertionContext> list = ctx.assertion();
		List<TerminalNode> idList = ctx.STRING();
		List<AdditionalPropertiesContext> additionalPro = ctx.additionalProperties();
		
		for(int i = 0; i < list.size(); i++) {
			p.add(idList.get(i).getText(),  visit(list.get(i)));
		}
		
		if(!additionalPro.isEmpty())
			p.setAdditionalProperties(visit(additionalPro.get(0)));
		
		return p;
	}
	
	@Override
	public Assertion visitParseAdditionalProperties(GrammaticaParser.ParseAdditionalPropertiesContext ctx) {
		return visit(ctx.assertion());
	}
	
	@Override
	public Properties_Assertion visitNewProperties(GrammaticaParser.NewPropertiesContext ctx) {
		return (Properties_Assertion) visit(ctx.properties());
	}
	
	@Override
	public Len_Assertion visitNewLength(GrammaticaParser.NewLengthContext ctx) { 
		return (Len_Assertion) visit(ctx.length_assertion()); 
	}
	
	@Override
	public Len_Assertion visitParseLengthAssertion(GrammaticaParser.ParseLengthAssertionContext ctx) { 
		IntegerAntlr min = (IntegerAntlr) visit(ctx.json_value(0));
		IntegerAntlr max = (IntegerAntlr) visit(ctx.json_value(1));
		
		return new Len_Assertion(min.getValue(), max.getValue()); 
	}
}
