package libraryapi;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.Payload;
import files.RawtoJson;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Array;

public class DynamicJson {
int s = 1123;
String aisle = String.valueOf(s);

String path = System.getProperty("user.dir")+"\\src\\files\\AddBook.json";

	@Test(dataProvider="BooksData")
	public void addBook(String aisle, String isbn) throws IOException {
		System.out.println(path);
		RestAssured.baseURI = "http://216.10.245.166";
	 String ss = given().log().all().header("Content-Type", "application/json").body(Payload.AddBook(aisle,isbn))
	  .when().post(Payload.AddBookResource())
	  .then().log().all().extract().response().asString();
	  s++;
	 System.out.println("check: "+ss);
	 JsonPath js1 = RawtoJson.rawjson(ss);
	   String id = js1.getString("ID");
	 System.out.println(id);
	String s = given().log().all().body(new String(Files.readAllBytes(Paths.get(path)))).when().post("/Library/DeleteBook.php").then().log().all().assertThat().statusCode(200).extract().response().asString();
	System.out.println(s);
	}
	
	
	@DataProvider(name ="BooksData")
	public Object[][] getData() {
		return new Object[][]  {{"1136","aa" },{"1137","bb"},{"1138","cc"}} ;
	}
}
