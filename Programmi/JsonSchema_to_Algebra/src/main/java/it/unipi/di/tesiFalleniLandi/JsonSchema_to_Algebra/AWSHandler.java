package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4.ErrorListener;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.Utils;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4.GrammaticaLexer;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4.GrammaticaParser;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4.AlgebraParser;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema.JSONSchema;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema.Utils_JSONSchema;

public class AWSHandler implements RequestHandler<LinkedHashMap<String, ?>, Object> {

	public Object handleRequest(LinkedHashMap<String, ?> input, Context context) {
		
		try {
			
			
			System.out.println("\tQUERY: "+input.get("queryStringParameters"));
			System.out.println("\tHEADERS: "+input.get("headers"));
			
			
			@SuppressWarnings("unchecked")
			LinkedHashMap<String, String> action = (LinkedHashMap<String, String>) input.get("queryStringParameters");
			switch(action.get("action")) {
			case "toJSON":
				System.out.println(toJSON((String) input.get("body")));
				return toJSON((String) input.get("body"));
			
			case "normalize":
				return normalize((String) input.get("body"));
			
			case "assertionSeparation":
				return assertionSeparation((String) input.get("body"));
				
			case "referenceNormalization":
				return referenceNormalization((String) input.get("body"));
			
			case "toGrammarString":
				return toGrammarString((String) input.get("body"));
				
			case "grammarToJSON":
				return grammarToJSON((String) input.get("body"));
				
			case "notElimination":
				return notElimination((String) input.get("body"));
			}
			
			
			return new GatewayResponse("unsupported "+action, 
					200,
					"type", "application/json+schema",
					false);
		}
		catch(Exception e) {
			e.printStackTrace();
			GatewayResponse response = new GatewayResponse("Error: "+ e,
					400,
					"type", "application/json+schema",
					false);

			return response;
		}
	}



	private GatewayResponse toJSON(String body) {
		JSONObject object;
		try {

			object = (JSONObject) new JSONParser().parse(body.replace('\n', ' '));

			JSONSchema schema = new JSONSchema(object);

			GatewayResponse response = new GatewayResponse(schema.toJSON().toString(),
					200,
					"type", "application/schema+json",
					false);


			return response;
		} catch (Exception e) {
			e.printStackTrace();
			GatewayResponse response = new GatewayResponse("Errore: "+e.getMessage(),
					400,
					"type", "application/json+schema",
					false);

			return response;
		}

	}

	private GatewayResponse normalize(String body) {
		JSONObject object;
		try {
			object = (JSONObject) new JSONParser().parse(body);

			JSONSchema schema = new JSONSchema(object);

			GatewayResponse response = new GatewayResponse(Utils_JSONSchema.normalize(schema).toJSON().toString(),
					200,
					"type", "application/schema+json",
					false);


			return response;
		} catch (ParseException e) {
			e.printStackTrace();
			GatewayResponse response = new GatewayResponse("Error: " +e,
					400,
					"type", "application/json+schema",
					false);

			return response;
		}

	}

	private GatewayResponse assertionSeparation(String body) {
		JSONObject object;
		try {
			object = (JSONObject) new JSONParser().parse(body);

			JSONSchema schema = new JSONSchema(object);

			GatewayResponse response = new GatewayResponse(schema.assertionSeparation().toJSON().toString(),
					200,
					"type", "application/schema+json",
					false);


			return response;
		} catch (ParseException e) {
			e.printStackTrace();
			GatewayResponse response = new GatewayResponse(e.getMessage(),
					200,
					"type", "application/json+schema",
					false);

			return response;
		}

	}

	private GatewayResponse referenceNormalization(String body) {
		JSONObject object;
		try {
			object = (JSONObject) new JSONParser().parse(body);

			JSONSchema schema = new JSONSchema(object);
			schema = Utils_JSONSchema.referenceNormalization(schema);
			GatewayResponse response = new GatewayResponse(schema.toJSON().toString(),
					200,
					"type", "application/schema+json",
					false);


			return response;
		} catch (ParseException e) {
			e.printStackTrace();
			GatewayResponse response = new GatewayResponse("Error: " + e,
					400,
					"type", "application/json+schema",
					false);

			return response;
		}

	}

	private GatewayResponse toGrammarString(String body) {
		JSONObject object;
		try {
			object = (JSONObject) new JSONParser().parse(body);

			JSONSchema schema = new JSONSchema(object);
			GatewayResponse response = new GatewayResponse(Utils_JSONSchema.toGrammarString(Utils_JSONSchema.normalize(schema)),
					200,
					"type", "application/json+schema",
					false);


			return response;
		} catch (ParseException e) {
			e.printStackTrace();
			GatewayResponse response = new GatewayResponse("Error: " + e.getMessage(),
					400,
					"type", "application/json+schema",
					false);

			return response;
		}

	}

	private GatewayResponse grammarToJSON(String body) {
		try{
			GrammaticaLexer lexer = new GrammaticaLexer(CharStreams.fromString(body));
			lexer.removeErrorListeners();
			lexer.addErrorListener(new ErrorListener());
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			GrammaticaParser parser = new GrammaticaParser(tokens);
			parser.removeErrorListeners();
			parser.addErrorListener(new ErrorListener());

	        ParseTree tree =  parser.assertion();
	        AlgebraParser p = new AlgebraParser();
	        Assertion schema = (Assertion) p.visit(tree);

	        JSONObject JSON = (JSONObject)schema.toJSONSchema();

	        GatewayResponse response = new GatewayResponse(JSON.toJSONString(),
	        		200,
	        		"type", "application/json+schema",
	        		false);

	        return response;
		}catch (ParseCancellationException e){
			GatewayResponse response = new GatewayResponse(e.getMessage(),
					400,
					"type", "application/json+schema",
					false);

			return response;
		}catch(Exception e) {
			e.printStackTrace();
			GatewayResponse response = new GatewayResponse("Error: " + e,
					400,
					"type", "application/json+schema",
					false);

			return response;
		}
	}

	private GatewayResponse notElimination(String body) {
		try{
			GrammaticaLexer lexer = new GrammaticaLexer(CharStreams.fromString(body));
			lexer.removeErrorListeners();
			lexer.addErrorListener(new ErrorListener());
	        CommonTokenStream tokens = new CommonTokenStream(lexer);
	        GrammaticaParser parser = new GrammaticaParser(tokens);
	        parser.removeErrorListeners();
	        parser.addErrorListener(new ErrorListener());

	        ParseTree tree =  parser.assertion();
	        AlgebraParser p = new AlgebraParser();
	        Assertion schema = (Assertion) p.visit(tree);

	        GatewayResponse response = new GatewayResponse(Utils.beauty(schema.notElimination().toGrammarString()),
	        		200,
	        		"type", "application/json+schema",
	        		false);

	        return response;
		}catch (ParseCancellationException e){
			GatewayResponse response = new GatewayResponse(e.getMessage(),
					400,
					"type", "application/json+schema",
					false);

			return response;
		}catch(Exception e) {
			GatewayResponse response = new GatewayResponse("Error: " + e,
					400,
					"type", "application/json+schema",
					false);
			
			return response;
		}
	}
}






class GatewayResponse {
    private Boolean isBase64Encoded;
    private Integer statusCode;
    private Map<String, String> headers;
    private Map<String, List<String>> multiValueHeaders;
    private String body;

    public GatewayResponse() {
    }

    public GatewayResponse(
    		final String body,
            final Integer statusCode,
            final String key,
            final String value,
            final Boolean isBase64Encoded
        ) {
            this.isBase64Encoded = isBase64Encoded;
            this.statusCode = statusCode;
            this.headers = new HashMap<>();

            headers.put("Access-Control-Allow-Origin", "*");
            headers.put(key, value);

            this.body = body;
        }

    public GatewayResponse(
        final Boolean isBase64Encoded,
        final Integer statusCode,
        final Map<String, String> headers,
        final Map<String, List<String>> multiValueHeaders,
        final String body
    ) {
        this.isBase64Encoded = isBase64Encoded;
        this.statusCode = statusCode;
        this.headers = headers;
        this.multiValueHeaders = multiValueHeaders;
        this.body = body;
    }

    public GatewayResponse(
        final Integer statusCode,
        final Map<String, String> headers,
        final Map<String, List<String>> multiValueHeaders,
        final String body
    ) {
        this(false, statusCode, headers, multiValueHeaders, body);
    }

    public GatewayResponse(
        final Integer statusCode,
        final Map<String, String> headers,
        final String body
    ) {
        this(false, statusCode, headers, null, body);
    }

    public GatewayResponse(
        final Integer statusCode,
        final Map<String, String> headers,
        final Map<String, List<String>> multiValueHeaders
    ) {
        this(false, statusCode, headers, multiValueHeaders, null);
    }

    public GatewayResponse(
        final Integer statusCode,
        final Map<String, String> headers
    ) {
        this(false, statusCode, headers, null, null);
    }

    public Boolean getBase64Encoded() {
        return isBase64Encoded;
    }

    public void setBase64Encoded(final Boolean base64Encoded) {
        isBase64Encoded = base64Encoded;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(final Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(final Map<String, String> headers) {
        this.headers = headers;
    }

    public Map<String, List<String>> getMultiValueHeaders() {
        return multiValueHeaders;
    }

    public void setMultiValueHeaders(Map<String, List<String>> multiValueHeaders) {
        this.multiValueHeaders = multiValueHeaders;
    }

    public String getBody() {
        return body;
    }

    public void setBody(final String body) {
        this.body = body;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GatewayResponse that = (GatewayResponse) o;
        return Objects.equals(isBase64Encoded, that.isBase64Encoded) &&
               Objects.equals(statusCode, that.statusCode) &&
               Objects.equals(headers, that.headers) &&
               Objects.equals(multiValueHeaders, that.multiValueHeaders) &&
               Objects.equals(body, that.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isBase64Encoded, statusCode, headers, multiValueHeaders, body);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", GatewayResponse.class.getSimpleName() + "[", "]")
            .add("isBase64Encoded=" + isBase64Encoded)
            .add("statusCode=" + statusCode)
            .add("headers=" + headers)
            .add("multiValueHeaders=" + multiValueHeaders)
            .add("body='" + body + "'")
            .toString();
    }
}