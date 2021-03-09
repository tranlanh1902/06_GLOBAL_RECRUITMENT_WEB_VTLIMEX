package common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.node.ArrayNode;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.node.ObjectNode;

//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.node.ArrayNode;
//import com.fasterxml.jackson.databind.node.ObjectNode;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;

public class CommontResAPI {
	/* ========== 1: get Request ========== */
	public static RequestSpecification getRequest() {
		RequestSpecification request = SerenityRest.given();
		return request;
	}

	/* ========== 2: add request ========== */

	// url
	public static RequestSpecification getRequestAddRequestUrl(RequestSpecification request, String url) {
		return request.baseUri(url);
	}

	public static RequestSpecification getRequestAddRequestContentType(RequestSpecification request, ContentType contentType) {
		// ANY, JSON, XML...
		return request.contentType(contentType);
	}

	// header
	public static RequestSpecification getRequestAddRequestHeader(RequestSpecification request, Map<String, String> requestHeadrs) {
		return request.headers(requestHeadrs);
	}

	// add body Map
	public static RequestSpecification getRequestAddRequestBody(RequestSpecification request, Map<String, Object> mapRequestBody) {
		return request.body(mapRequestBody);
	}

	// add body param
	public static RequestSpecification getRequestAddRequestParam(RequestSpecification request, Map<String, Object> requestParam) {
		return request.params(requestParam);
	}

	/* ==========3: get Response========== */

	public static Response getResponse(RequestSpecification request, String method) {
		Response response = null;
		switch (method) {
		case "GET":
			response = request.get();
			break;
		case "POST":
			response = request.post();
			break;
		case "PUT":
			response = request.put();
			break;
		case "PATCH":
			response = request.patch();
			break;
		case "DELETE":
			response = request.delete();
			break;

		default:
			System.out.println("method not valid");
			break;
		}
		return response;
	}

	/* get response */
	// get response String
	public static String getResponseString(Response response) {
		return response.asString();
	}

	// get statusCode
	public static int getStatusCode(Response response) {
		return response.getStatusCode();
	}

	// get statusLine
	public static String getStatusLine(Response response) {
		return response.getStatusLine();
	}

	// get all Header: name-value
	public static Headers getAllHeader(Response response) {
		Headers allHeaders = response.getHeaders();
		return allHeaders;
	}

	public List<String> getNameHeaders(Response response, List<String> lstNameHeaders) {
		Headers allHeaders = response.getHeaders();
		for (Header header : allHeaders) {
			lstNameHeaders.add(header.getName());
		}
		return lstNameHeaders;
	}

	public List<String> getValueHeaders(Response response, List<String> lstValueHeaders) {
		Headers allHeaders = response.getHeaders();
		for (Header header : allHeaders) {
			lstValueHeaders.add(header.getValue());
		}
		return lstValueHeaders;
	}

	public static String getValueHeaderWithNameHeader(Response response, String nameHeader) {
		String value = response.getHeader(nameHeader);
		return value;
	}

	/* get value response chỉ định rõ path Node */
	public static String getValueResponseStringNode(Response response, String pathNode) throws IOException {
		String jsonString = response.asString();
		ObjectMapper objectMapper = new ObjectMapper();

		JsonNode jsonTree = objectMapper.readTree(jsonString);

		String value = jsonTree.at(pathNode).asText();
		return value;
	}

	public static int getValueResponseIntegerNode(Response response, String pathNode) throws IOException {
		String jsonString = response.asString();
		ObjectMapper objectMapper = new ObjectMapper();

		JsonNode jsonTree = objectMapper.readTree(jsonString);

		int value = jsonTree.at(pathNode).asInt();
		return value;
	}

	public static boolean getValueResponseBooleanNode(Response response, String pathNode) throws IOException {
		String jsonString = response.asString();
		ObjectMapper objectMapper = new ObjectMapper();

		JsonNode jsonTree = objectMapper.readTree(jsonString);

		boolean value = jsonTree.at(pathNode).asBoolean();
		return value;
	}

	public static Double getValueResponseDoubleNode(Response response, String pathNode) throws IOException {
		String jsonString = response.asString();
		ObjectMapper objectMapper = new ObjectMapper();

		JsonNode jsonTree = objectMapper.readTree(jsonString);

		Double value = jsonTree.at(pathNode).asDouble();
		return value;
	}

	public static Long getValueResponseLongNode(Response response, String pathNode) throws IOException {
		String jsonString = response.asString();
		ObjectMapper objectMapper = new ObjectMapper();

		JsonNode jsonTree = objectMapper.readTree(jsonString);

		Long value = jsonTree.at(pathNode).asLong();
		return value;
	}

	// get all value response of all node
	public static List<Object> getListAllValueResponse(String jsonString) throws IOException {
		// response = getResponsePostRequest(request);
		// jsonString = getResponseString(response);
		// String jsonString = response.asString();

		// System.out.println("jsonArray=" + jsonString);
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonTree = objectMapper.readTree(jsonString);

		List<Object> lstAllValueJsonResponse = new ArrayList<Object>();

		if (jsonTree instanceof ArrayNode) {
			for (int i = 0; i < jsonTree.size(); i++) {
				List<String> lstKeyJsonObjectResponse = new ArrayList<String>();

				JsonNode jsonNodeSelf = jsonTree.get(i);
				// System.out.println(jsonNodeSelf);

				/* =====Json Object node self ===== */
				Iterator<String> allKeysSelf = jsonNodeSelf.fieldNames();

				allKeysSelf.forEachRemaining(kSelf -> {
					Object valueNodeSelf = jsonNodeSelf.get(kSelf);

					if (valueNodeSelf instanceof ObjectNode) {
						// System.out.println(valueNodeSelf);
						Iterator<String> keyssSelf = ((ObjectNode) valueNodeSelf).fieldNames();
						keyssSelf.forEachRemaining(keSelf -> {
							lstKeyJsonObjectResponse.add(kSelf + "/" + keSelf);
						});
					} else if (valueNodeSelf instanceof ArrayNode) {
						int sizejsonNodeArray = ((ArrayNode) valueNodeSelf).size();

						/* =====Json Object node child ===== */
						for (int j = 0; j < sizejsonNodeArray; j++) {
							JsonNode jsonNodeChild = ((ArrayNode) valueNodeSelf).get(j);
							// System.out.println(jsonNodeChild);
							int index = j;

							if (jsonNodeChild instanceof ObjectNode) {
								Iterator<String> allKeysChild = jsonNodeChild.fieldNames();
								allKeysChild.forEachRemaining(kChild -> {
									lstKeyJsonObjectResponse.add(kSelf + "/" + index + "/" + kChild);
								});
							}

						}

					} else {
						lstKeyJsonObjectResponse.add(kSelf);
					}
				});

				for (String keyNode : lstKeyJsonObjectResponse) {
					lstAllValueJsonResponse.add(jsonNodeSelf.at("/" + keyNode).asText());
				}
			}

		} else if (jsonTree instanceof ObjectNode) {
			List<String> lstKeyJsonArray = new ArrayList<String>();

			Iterator<String> allKeys = jsonTree.fieldNames();

			allKeys.forEachRemaining(k -> {
				Object value = jsonTree.get(k);

				if (value instanceof ObjectNode) {
					// System.out.println(value);
					Iterator<String> keyss = ((ObjectNode) value).fieldNames();
					keyss.forEachRemaining(ke -> lstKeyJsonArray.add(k + "/" + ke));
				} else if (value instanceof ArrayNode) {
					int sizejsonNodeArray = ((ArrayNode) value).size();

					/* =====Json Object node self ===== */
					for (int j = 0; j < sizejsonNodeArray; j++) {
						JsonNode jsonNodeSelf = ((ArrayNode) value).get(j);
						// System.out.println(jsonNodeSelf);
						int index = j;

						if (jsonNodeSelf instanceof ObjectNode) {
							Iterator<String> allKeysSelf = jsonNodeSelf.fieldNames();
							allKeysSelf.forEachRemaining(kSelf -> {
								lstKeyJsonArray.add(k + "/" + index + "/" + kSelf);
							});
						}

					}
				} else {
					lstKeyJsonArray.add(k);
				}
			});

			for (String keyNode : lstKeyJsonArray) {
				// System.out.println("/" + keyNode);
				lstAllValueJsonResponse.add(jsonTree.at("/" + keyNode).asText());
			}
		}

		return lstAllValueJsonResponse;
	}

	// get list value response of nodes contains path /%keyContains%
	public static List<Object> getListValueResponseOfNode(String jsonString, String keyContains) throws IOException {
		// response = getResponsePostRequest(request);
		// jsonString = getResponseString(response);
		// String jsonString = response.asString();

		// System.out.println("jsonArray=" + jsonString);
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonTree = objectMapper.readTree(jsonString);

		List<Object> lstValueJsonResponseWithKey = new ArrayList<Object>();

		if (jsonTree instanceof ArrayNode) {
			for (int i = 0; i < jsonTree.size(); i++) {
				List<String> lstKeyJsonObjectResponse = new ArrayList<String>();

				JsonNode jsonNodeSelf = jsonTree.get(i);
				// System.out.println(jsonNodeSelf);

				/* =====Json Object node self ===== */
				Iterator<String> allKeysSelf = jsonNodeSelf.fieldNames();

				allKeysSelf.forEachRemaining(kSelf -> {
					Object valueNodeSelf = jsonNodeSelf.get(kSelf);

					if (valueNodeSelf instanceof ObjectNode) {
						// System.out.println(valueNodeSelf);
						Iterator<String> keyssSelf = ((ObjectNode) valueNodeSelf).fieldNames();
						keyssSelf.forEachRemaining(keSelf -> {
							lstKeyJsonObjectResponse.add(kSelf + "/" + keSelf);
						});
					} else if (valueNodeSelf instanceof ArrayNode) {
						int sizejsonNodeArray = ((ArrayNode) valueNodeSelf).size();

						/* =====Json Object node child ===== */
						for (int j = 0; j < sizejsonNodeArray; j++) {
							JsonNode jsonNodeChild = ((ArrayNode) valueNodeSelf).get(j);
							// System.out.println(jsonNodeChild);
							int index = j;

							if (jsonNodeChild instanceof ObjectNode) {
								Iterator<String> allKeysChild = jsonNodeChild.fieldNames();
								allKeysChild.forEachRemaining(kChild -> {
									lstKeyJsonObjectResponse.add(kSelf + "/" + index + "/" + kChild);
								});
							}

						}

					} else {
						lstKeyJsonObjectResponse.add(kSelf);
					}
				});

				for (String keyNode : lstKeyJsonObjectResponse) {
					if (keyNode.contains(keyContains)) {
						lstValueJsonResponseWithKey.add(jsonNodeSelf.at("/" + keyNode).asText());
					}
				}
			}

		} else if (jsonTree instanceof ObjectNode) {
			List<String> lstKeyJsonArray = new ArrayList<String>();

			Iterator<String> allKeys = jsonTree.fieldNames();

			allKeys.forEachRemaining(k -> {
				Object value = jsonTree.get(k);

				if (value instanceof ObjectNode) {
					// System.out.println(value);
					Iterator<String> keyss = ((ObjectNode) value).fieldNames();
					keyss.forEachRemaining(ke -> lstKeyJsonArray.add(k + "/" + ke));
				} else if (value instanceof ArrayNode) {
					int sizejsonNodeArray = ((ArrayNode) value).size();

					/* =====Json Object node self ===== */
					for (int j = 0; j < sizejsonNodeArray; j++) {
						JsonNode jsonNodeSelf = ((ArrayNode) value).get(j);
						// System.out.println(jsonNodeSelf);
						int index = j;

						if (jsonNodeSelf instanceof ObjectNode) {
							Iterator<String> allKeysSelf = jsonNodeSelf.fieldNames();
							allKeysSelf.forEachRemaining(kSelf -> {
								lstKeyJsonArray.add(k + "/" + index + "/" + kSelf);
							});
						}

					}
				} else {
					lstKeyJsonArray.add(k);
				}
			});

			for (String keyNode : lstKeyJsonArray) {
				// System.out.println("/" + keyNode);
				lstValueJsonResponseWithKey.add(jsonTree.at("/" + keyNode).asText());
			}
		}

		return lstValueJsonResponseWithKey;
	}

	// get number count object response
	public static int getCountObjectResponse(String jsonString) throws IOException {
		// response = getResponsePostRequest(request);
		// jsonString = getResponseString(response);
		// String jsonString = response.asString();

		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonTree = objectMapper.readTree(jsonString);

		int countObjectResponse = 1;
		if (jsonTree instanceof ArrayNode) {
			countObjectResponse = jsonTree.size();
		}

		return countObjectResponse;
	}

	/* ========== 4: verify ======= */
	public static void VerifyStatusCode(Response response, int statusCodeEx) {
		response.then().statusCode(statusCodeEx);
	}

	public static void VerifySatusLine(Response response, String statusLineEx) {
		response.then().statusLine(statusLineEx);
	}

	/* ===========5: Check respose is Json ObjectNode or ArrayNode =========== */
	public boolean IsJSONObjectNode(Response response) throws IOException {
		String jsonString = response.asString();
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonTree = objectMapper.readTree(jsonString);
		return (jsonTree instanceof ObjectNode);
	}

	public boolean IsJSONArrayNode(Response response) throws IOException {
		String jsonString = response.asString();
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonTree = objectMapper.readTree(jsonString);
		return (jsonTree instanceof ArrayNode);
	}

	public boolean IsJsonObjectOrArrayNode(Response response) {
		return true;
	}

}
