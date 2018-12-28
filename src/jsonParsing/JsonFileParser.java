package jsonParsing;

import java.io.*;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonValue;
import javax.json.JsonObject;
import static javax.json.JsonValue.ValueType.*;
import javax.json.JsonReader;
import javax.json.JsonStructure;

public class JsonFileParser {

	public static void parseAndPrint(String jsonFile) throws FileNotFoundException {
		try {
			JsonReader reader = Json.createReader(new FileReader(jsonFile));
			JsonStructure jsonStruct = reader.read();
			reader.close();
			// System.out.println(jsonStruct);
			if (jsonStruct.getValueType().equals(OBJECT)) {
				JsonObject jo = (JsonObject) jsonStruct;
				System.out.println("First name: " + jo.getString("firstName"));
				System.out.println("Last name: " + jo.getString("lastName"));
				System.out.println("Age: " + jo.getInt("age"));
				System.out.println("Street address: " + jo.getString("streetAddress", "No st. addr."));
				System.out.println("City: " + jo.getString("city", "No city"));
				System.out.println("State: " + jo.getString("state", "No state"));
				System.out.println("Postal code: " + jo.getString("postalCode", "No postal code"));
				JsonArray arr = jo.getJsonArray("phoneNumbers");
				System.out.println("Phone numbers:");
				for (JsonValue jsonValue : arr) {
					if (((JsonObject) jsonValue).keySet().contains("Mobile")) {
						String mobile = ((JsonObject) jsonValue).getString("Mobile");
						if (!mobile.equals(NULL)) {
							System.out.println("Mobile: "+mobile);
						}
					} else {
						String home = ((JsonObject) jsonValue).getString("Home");
						if (!home.equals(NULL)) {
							System.out.println("Home: "+home);
						}
					}
				}
				
			}

		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("jsonFile");
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			parseAndPrint(args[0]);

		} catch (FileNotFoundException e) {
			System.err.println("File not found");
		}
	}
}
