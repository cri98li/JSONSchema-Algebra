package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema.JSONSchema;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema.Utils;

public class AWSHandler implements RequestHandler<LinkedHashMap<String, ?>, GatewayResponse> {

	public GatewayResponse handleRequest(LinkedHashMap<String, ?> input, Context context) {
		try {
			String action = (String) input.get("rawQueryString");
			System.out.println(action);
			switch(action.split("=")[1]) {
			case "toJSON":
				return toJSON((String) input.get("body"));
			
			case "normalize":
				return normalize((String) input.get("body"));
			
			case "assertionSeparation":
				return assertionSeparation((String) input.get("body"));
				
			case "referenceNormalization":
				return referenceNormalization((String) input.get("body"));
			
			case "toGrammarString":
				return toGrammarString((String) input.get("body"));
			}
			
			
			return new GatewayResponse("unsupported "+action, 
					501,
					Collections.singletonMap("type", "text"),
					false);
		}
		catch(Exception e) {
			GatewayResponse response = new GatewayResponse(e.getLocalizedMessage(), 
					501,
					Collections.singletonMap("type", "text"),
					false);
			
			return response;
		}
	}

	
	
	private GatewayResponse toJSON(String body) {
		JSONObject object;
		try {
			object = (JSONObject) new JSONParser().parse(body);
		
			JSONSchema schema = new JSONSchema(object);
			
			GatewayResponse response = new GatewayResponse(schema.toJSON(), 
					200,
					Collections.singletonMap("type", "application/schema+json"),
					false);
			
			
			return response;
		} catch (ParseException e) {
			GatewayResponse response = new GatewayResponse(e.getLocalizedMessage(), 
					501,
					Collections.singletonMap("type", "text"),
					false);
			
			return response;
		}
	
	}
	
	private GatewayResponse normalize(String body) {
		JSONObject object;
		try {
			object = (JSONObject) new JSONParser().parse(body);
		
			JSONSchema schema = new JSONSchema(object);
			
			GatewayResponse response = new GatewayResponse(Utils.normalize(schema).toJSON(), 
					200,
					Collections.singletonMap("type", "application/schema+json"),
					false);
			
			
			return response;
		} catch (ParseException e) {
			GatewayResponse response = new GatewayResponse(e.getLocalizedMessage(), 
					501,
					Collections.singletonMap("type", "text"),
					false);
			
			return response;
		}
	
	}
	
	private GatewayResponse assertionSeparation(String body) {
		JSONObject object;
		try {
			object = (JSONObject) new JSONParser().parse(body);
		
			JSONSchema schema = new JSONSchema(object);
			
			GatewayResponse response = new GatewayResponse(schema.assertionSeparation().toJSON(), 
					200,
					Collections.singletonMap("type", "application/schema+json"),
					false);
			
			
			return response;
		} catch (ParseException e) {
			GatewayResponse response = new GatewayResponse(e.getLocalizedMessage(), 
					501,
					Collections.singletonMap("type", "text"),
					false);
			
			return response;
		}
	
	}
	
	private GatewayResponse referenceNormalization(String body) {
		JSONObject object;
		try {
			object = (JSONObject) new JSONParser().parse(body);
		
			JSONSchema schema = new JSONSchema(object);
			Utils.referenceNormalization(schema);
			GatewayResponse response = new GatewayResponse(schema.toJSON(), 
					200,
					Collections.singletonMap("type", "application/schema+json"),
					false);
			
			
			return response;
		} catch (ParseException e) {
			GatewayResponse response = new GatewayResponse(e.getLocalizedMessage(), 
					501,
					Collections.singletonMap("type", "text"),
					false);
			
			return response;
		}
	
	}

	private GatewayResponse toGrammarString(String body) {
		JSONObject object;
		try {
			object = (JSONObject) new JSONParser().parse(body);
		
			JSONSchema schema = new JSONSchema(object);
			
			GatewayResponse response = new GatewayResponse(Utils.normalize(schema).toGrammarString(), 
					200,
					Collections.singletonMap("type", "text"),
					false);
			
			
			return response;
		} catch (ParseException e) {
			GatewayResponse response = new GatewayResponse(e.getLocalizedMessage(), 
					501,
					Collections.singletonMap("type", "text"),
					false);
			
			return response;
		}
	
	}
}








class GatewayResponse{
	private String body;
	private Integer statusCode;
	private Map<String, String> headers;
	private boolean isBase64Encoded;
	private String[] cookies = {};
	
	public GatewayResponse(Object body, Integer statusCode, Map<String, String> headers, boolean isBase64Encoded) {
		this.body = body.toString();
		this.statusCode = statusCode;
		this.headers = headers;
		this.isBase64Encoded = isBase64Encoded;
	}
	
	public GatewayResponse(String body, Integer statusCode, Map<String, String> headers, boolean isBase64Encoded) {
		this.body = body;
		this.statusCode = statusCode;
		this.headers = headers;
		this.isBase64Encoded = isBase64Encoded;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public boolean isBase64Encoded() {
		return isBase64Encoded;
	}

	public void setBase64Encoded(boolean isBase64Encoded) {
		this.isBase64Encoded = isBase64Encoded;
	}

	public String[] getCookies() {
		return cookies;
	}

	public void setCookies(String[] cookies) {
		this.cookies = cookies;
	}
	
	
}