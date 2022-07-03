package files;

import io.restassured.path.json.JsonPath;

public class RawtoJson {
	public static JsonPath rawjson(String res) {
	JsonPath js1 = new JsonPath(res);
return js1;
}
	}
