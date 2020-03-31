package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4;

import java.util.List;

import org.antlr.v4.runtime.tree.TerminalNode;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.And_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.Annotation_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.AssertionList;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.BetItems_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.Bet_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.Const_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.Defs_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.Enum_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.Exist_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.IfThenElse_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.Items_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.Len_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.Mof_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.Names_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.Not_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.Or_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.Pattern_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.Pro_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.Properties_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.Ref_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.Required_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.Type_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.UniqueItems_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.Xor_Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4.GrammaticaParser.AdditionalPropertiesContext;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4.GrammaticaParser.AssertionContext;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4.GrammaticaParser.Json_valueContext;

public class AlgebraParser extends GrammaticaBaseVisitor<Assertion>{

	@Override
	public AntlrBoolean visitParseBooleanSchema(GrammaticaParser.ParseBooleanSchemaContext ctx) {
		return new AntlrBoolean(Boolean.parseBoolean(ctx.BOOLEAN().getText()));
	}
	
	public AssertionList visitParseAssertionList(GrammaticaParser.ParseAssertionListContext ctx) {
		AssertionList list = new AssertionList();
		
		List<AssertionContext> keywords = ctx.assertion();
		
		for(AssertionContext key : keywords) {
			Assertion schema = visit(key);
			list.add(schema);
		}
		
		return list; 
	}
	
	@Override
	public Bet_Assertion visitNewBetweenAssertion(GrammaticaParser.NewBetweenAssertionContext ctx) {

		return (Bet_Assertion) visit(ctx.between_assertion());
	}
	
	@Override
	public Bet_Assertion visitParseBetweenAssertion(GrammaticaParser.ParseBetweenAssertionContext ctx) {
		
		AntlrValue min = (AntlrValue) visit(ctx.json_value(0));
		AntlrValue max = (AntlrValue) visit(ctx.json_value(1));
		
		return new Bet_Assertion(min.getValue(), max.getValue());
	}
	
	@Override
	public Bet_Assertion visitNewXBetweenAssertion(GrammaticaParser.NewXBetweenAssertionContext ctx) {
		
		Bet_Assertion bet = (Bet_Assertion) visit(ctx.xbetween_assertion());

		return bet;
	}
	
	@Override
	public Bet_Assertion visitParseXBetweenAssertion(GrammaticaParser.ParseXBetweenAssertionContext ctx) {
		
		AntlrLong min = (AntlrLong) visit(ctx.json_value(0));
		AntlrLong max = (AntlrLong) visit(ctx.json_value(1));
		
		return new Bet_Assertion(min.getValue(), max.getValue());
	}
	

	@Override 
	public AntlrLong visitIntValue(GrammaticaParser.IntValueContext ctx) { 
		return new AntlrLong(Long.valueOf(ctx.INT().getText()));
	}
	
	@Override 
	public AntlrDouble visitDoubleValue(GrammaticaParser.DoubleValueContext ctx) { 
		return new AntlrDouble(Double.valueOf(ctx.DOUBLE().getText()));
	}
	
	@Override
	public AntlrLong visitNullValue(GrammaticaParser.NullValueContext ctx) {
		AntlrLong value = new AntlrLong(null);

		return value;
	}
	
	@Override 
	public AntlrArray visitArrayValue(GrammaticaParser.ArrayValueContext ctx) {
		AntlrArray array = new AntlrArray();
		List<Json_valueContext> list = ctx.json_value();
		
		for(Json_valueContext el : list)
			array.add((AntlrValue) visit(el));
		
		return array;
	}
	
	@Override 
	public AntlrJsonObject visitJsonObjectValue(GrammaticaParser.JsonObjectValueContext ctx) {
		AntlrJsonObject jsonObject = new AntlrJsonObject();
		
		List<Json_valueContext> list = ctx.json_value();
		List<TerminalNode> idList = ctx.STRING();
		
		for(int i = 0; i < list.size(); i++) {
			jsonObject.add(idList.get(i).getText().subSequence(1, idList.get(i).getText().length()-1).toString(),  visit(list.get(i)));
		}
		
		return jsonObject;
	}

	@Override
	public Type_Assertion visitNewTypeAssertion(GrammaticaParser.NewTypeAssertionContext ctx) {  
		Type_Assertion type = new Type_Assertion();
		type.add(((AntlrString) visit(ctx.type_assertion())).getValue());
		
		
		return type; 
	}
	
	@Override
	public AntlrString visitParseTypeAssertion(GrammaticaParser.ParseTypeAssertionContext ctx) { 
		String type;
		if(ctx.TYPE() != null)
			type = ctx.TYPE().getText();
		else
			type = ctx.NULL().getText();
		
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
			req.add(str.getText().subSequence(1, str.getText().length()-1).toString());
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
			_enum.add(tmp.getValue());
		}
		
		return _enum;
	}
	
	@Override 
	public Mof_Assertion visitNewMultipleOf(GrammaticaParser.NewMultipleOfContext ctx) { 
		
		return (Mof_Assertion) visit(ctx.multiple_of_assertion()); 
	}
	
	@Override 
	public Mof_Assertion visitParseMultipleOf(GrammaticaParser.ParseMultipleOfContext ctx) { 
		
		AntlrLong value = (AntlrLong) visit(ctx.json_value());
		
		return new Mof_Assertion(value.getValue()); 
	}
	
	@Override 
	public AntlrString visitStringValue(GrammaticaParser.StringValueContext ctx) { 
		String str = ctx.STRING().getText();
		return new AntlrString(str.substring(1, str.length()-1)); 
	}
	
	@Override 
	public AntlrBoolean visitBooleanValue(GrammaticaParser.BooleanValueContext ctx) { 
		
		return new AntlrBoolean(Boolean.valueOf(ctx.BOOLEAN().getText())); 
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
		String str = ctx.STRING().getText();
		return new Pattern_Assertion(str.substring(1, str.length()-1)); 
	}
	
	@Override 
	public Exist_Assertion visitNewContains(GrammaticaParser.NewContainsContext ctx) { 
		return (Exist_Assertion) visit(ctx.contains_assertion()); 
	}
	
	@Override 
	public Exist_Assertion visitParseContains(GrammaticaParser.ParseContainsContext ctx) { 
		
		AntlrLong min = (AntlrLong) visit(ctx.json_value(0));
		AntlrLong max = (AntlrLong) visit(ctx.json_value(1));
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
		
		return new Const_Assertion(value.getValue()); 
	}
	
	@Override 
	public Items_Assertion visitNewItems(GrammaticaParser.NewItemsContext ctx) {
		
		return (Items_Assertion) visit(ctx.items_assertion()); 
	}
	
	@Override 
	public Items_Assertion visitParseOnlyItems(GrammaticaParser.ParseOnlyItemsContext ctx) { 
		List<AssertionContext> list = ctx.assertion();
		Items_Assertion items= new Items_Assertion();
		
		for(AssertionContext item : list)
			items.add(visit(item));
		
		return items; 
	}

	@Override 
	public Items_Assertion visitParseAdditionalItems(GrammaticaParser.ParseAdditionalItemsContext ctx) {
		List<AssertionContext> list = ctx.assertion();
		Items_Assertion items= new Items_Assertion();
		
		for(int i = 0; i < list.size()-1; i++)
			items.add(visit(list.get(i)));
		
		items.setAdditionalItems(visit(list.get(list.size()-1)));
		
		return items; 
	}

	
	@Override
	public Properties_Assertion visitParseProperties(GrammaticaParser.ParsePropertiesContext ctx) {
		Properties_Assertion p = new Properties_Assertion();
		
		List<AssertionContext> list = ctx.assertion();
		List<TerminalNode> idList = ctx.STRING();
		List<AdditionalPropertiesContext> additionalPro = ctx.additionalProperties();
		
		for(int i = 0; i < list.size(); i++) {
			p.add(idList.get(i).getText().subSequence(1, idList.get(i).getText().length()-1).toString(),  visit(list.get(i)));
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
		AntlrLong min = (AntlrLong) visit(ctx.json_value(0));
		AntlrLong max = (AntlrLong) visit(ctx.json_value(1));
		
		return new Len_Assertion(min.getValue(), max.getValue()); 
	}
	
	@Override 
	public Pro_Assertion visitNewBetweenProperties(GrammaticaParser.NewBetweenPropertiesContext ctx) { 
		return (Pro_Assertion) visit(ctx.between_properties_assertion()); 
	}
	
	@Override 
	public Pro_Assertion visitParseBetProAssertion(GrammaticaParser.ParseBetProAssertionContext ctx) { 
		AntlrLong min = (AntlrLong) visit(ctx.json_value(0));
		AntlrLong max = (AntlrLong) visit(ctx.json_value(1));
		
		return new Pro_Assertion(min.getValue(), max.getValue());
	}
	
	@Override 
	public BetItems_Assertion visitNewBetweenItems(GrammaticaParser.NewBetweenItemsContext ctx) { 
		return (BetItems_Assertion) visit(ctx.bet_items_assertion()); 
	}
	
	@Override 
	public BetItems_Assertion visitParseBetItemsAssertion(GrammaticaParser.ParseBetItemsAssertionContext ctx) { 
		AntlrLong min = (AntlrLong) visit(ctx.json_value(0));
		AntlrLong max = (AntlrLong) visit(ctx.json_value(1));
		
		return new BetItems_Assertion(min.getValue(), max.getValue());
	}
	
	@Override
	public Defs_Assertion visitNewDef(GrammaticaParser.NewDefContext ctx) { 
		return (Defs_Assertion) visit(ctx.def_assertion()); 
	}
	
	@Override
	public Defs_Assertion visitParseDef(GrammaticaParser.ParseDefContext ctx) { 
		Defs_Assertion defs = new Defs_Assertion();
		
		List<AssertionContext> list = ctx.assertion();
		List<TerminalNode> idList = ctx.STRING();
		
		for(int i = 0; i < list.size(); i++) {
			defs.add(idList.get(i).getText().subSequence(1, idList.get(i).getText().length()-1).toString(),  visit(list.get(i)));
		}
		
		return defs;
	}

	@Override
	public Ref_Assertion visitNewRef(GrammaticaParser.NewRefContext ctx) { 
		return (Ref_Assertion) visit(ctx.ref_assertion());
	}
	
	@Override
	public Ref_Assertion visitParseRef(GrammaticaParser.ParseRefContext ctx) { 
		String str = ctx.STRING().getText();
		return new Ref_Assertion(str.substring(1, str.length()-1));
	}
	
	@Override 
	public Names_Assertion visitNewPropertyNames(GrammaticaParser.NewPropertyNamesContext ctx) { 
		return (Names_Assertion) visit(ctx.propertyNames()); 
	}
	
	@Override 
	public Names_Assertion visitParsePropertyNames(GrammaticaParser.ParsePropertyNamesContext ctx) {
		return new Names_Assertion(visit(ctx.assertion()));
	}
	
	
	public Annotation_Assertion visitNewAnnotations(GrammaticaParser.NewAnnotationsContext ctx) { 
		return (Annotation_Assertion) visitChildren(ctx); 
	}
	
	public Annotation_Assertion visitParseAnnotations(GrammaticaParser.ParseAnnotationsContext ctx) { 
		List<TerminalNode> stringList = ctx.STRING();
		Annotation_Assertion annotations = new Annotation_Assertion();
		
		for(int i = 0; i < stringList.size(); i += 2)
			annotations.add(stringList.get(i).getText().subSequence(1, stringList.get(i).getText().length()-1).toString(), 
					stringList.get(i+1).getText().subSequence(1, stringList.get(i+1).getText().length()-1).toString());
		
		return annotations;
	}
}
