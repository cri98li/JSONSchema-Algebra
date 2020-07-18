package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common.Utils;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Utils_FullAlgebra;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema.JSONSchema;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema.Utils_JSONSchema;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness.WitnessException;
import patterns.REException;

import java.util.*;

public class AWSHandler implements RequestHandler<LinkedHashMap<String, ?>, Object> {
	@Override
	public Object handleRequest(LinkedHashMap<String, ?> stringLinkedHashMap, Context context) {
		return null;
	}

	/*public Object handleRequest(LinkedHashMap<String, ?> input, Context context) {
		
		try {
			System.out.println("\tQUERY: "+input.get("queryStringParameters"));
			System.out.println("\tHEADERS: "+input.get("headers"));
			
			
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

			case "notEliminationFull":
				return notEliminationFull((String) input.get("body"));

			case "andMerging":
				return andMerging((String) input.get("body"));

			case "notEliminationWitness":
				return notEliminationWitness((String) input.get("body"));

			case "Canonicalization":
				return canonicalization((String) input.get("body"));
			}
			
			
			return new GatewayResponse("Unsupported "+action,
					200,
					"type", "application/json+schema",
					false);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new GatewayResponse(e.getMessage(),
					400,
					"type", "text/plain",
					false);
		}
	}



	private GatewayResponse toJSON(String body) throws ParseException {
		JsonObject object;
		object = (JSONObject) new JSONParser().parse(body.replace('\n', ' '));
		JSONSchema schema = new JSONSchema(object);

		return new GatewayResponse(schema.toJSON().toString(),
				200,
				"type", "application/schema+json",
				false);

	}

	private GatewayResponse normalize(String body) throws ParseException {
		JSONObject object = (JSONObject) new JSONParser().parse(body);
		JSONSchema schema = new JSONSchema(object);

		return new GatewayResponse(Utils_JSONSchema.normalize(schema).toJSON().toString(),
				200,
				"type", "application/schema+json",
				false);

	}

	private GatewayResponse assertionSeparation(String body) throws ParseException {
		JSONObject object;
		object = (JSONObject) new JSONParser().parse(body);

		JSONSchema schema = new JSONSchema(object);

		return new GatewayResponse(schema.assertionSeparation().toJSON().toString(),
				200,
				"type", "application/schema+json",
					false);
	}

	private GatewayResponse referenceNormalization(String body) throws ParseException {
		JSONObject object = (JSONObject) new JSONParser().parse(body);
		JSONSchema schema = new JSONSchema(object);
		schema = Utils_JSONSchema.referenceNormalization(schema);

		return new GatewayResponse(schema.toJSON().toString(),
				200,
				"type", "application/schema+json",
				false);
	}

	private GatewayResponse toGrammarString(String body) throws ParseException {
		JSONObject object = (JSONObject) new JSONParser().parse(body);

		JSONSchema schema = new JSONSchema(object);
		return new GatewayResponse(Utils_JSONSchema.toGrammarString(Utils_JSONSchema.normalize(schema)),
				200,
				"type", "application/json+schema",
				false);
	}

	private GatewayResponse grammarToJSON(String body) {
		Assertion schema = Utils_FullAlgebra.parseString(body);

		JSONObject JSON = (JSONObject)schema.toJSONSchema();

		return new GatewayResponse(JSON.toJSONString(),
				200,
				"type", "application/json+schema",
				false);
	}

	private GatewayResponse notEliminationFull(String body) {
		Assertion schema = Utils_FullAlgebra.parseString(body);

		return new GatewayResponse(Utils.beauty(schema.notElimination().toGrammarString()),
				200,
				"type", "application/json+schema",
				false);
	}

	private GatewayResponse andMerging(String body) throws REException {
		Assertion schema = Utils_FullAlgebra.parseString(body);

		return new GatewayResponse(Utils.beauty(Utils_FullAlgebra.getWitnessAlgebra(schema.notElimination()).mergeElement(null).getFullAlgebra().toGrammarString()),
				200,
				"type", "application/json+schema",
				false);
	}

	private GatewayResponse notEliminationWitness(String body) throws WitnessException, REException {
		Assertion schema = Utils_FullAlgebra.parseString(body).notElimination();

		return new GatewayResponse(Utils.beauty(Utils_FullAlgebra.getWitnessAlgebra(schema).groupize().getFullAlgebra().toGrammarString()),
				200,
				"type", "application/json+schema",
				false);
	}

	private GatewayResponse canonicalization(String body) throws WitnessException, REException {
		Assertion schema = Utils_FullAlgebra.parseString(body).notElimination();

		return new GatewayResponse(Utils.beauty(Utils_FullAlgebra.getWitnessAlgebra(schema).mergeElement(null).groupize().getFullAlgebra().toGrammarString()),
				200,
				"type", "application/json+schema",
				false);
	}*/
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