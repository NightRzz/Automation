package API;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specifications {
    public static RequestSpecification ReqSpec(String url){
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .setContentType(ContentType.JSON).build();
    }
    public static ResponseSpecification RespSpecCodeN(int code){
        return new ResponseSpecBuilder()
                .expectStatusCode(code).build();
    }
    public static void setSpec(RequestSpecification reqSpec, ResponseSpecification respSpec){
        RestAssured.requestSpecification = reqSpec;
        RestAssured.responseSpecification = respSpec;
    }
}
