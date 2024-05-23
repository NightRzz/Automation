package API;

import API.RegTestClasses.*;
import API.UsersTestClassess.ListUsersData;
import API.UsersTestClassess.UserData;
import API.UsersTestClassess.UserDataResp;
import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Test;

import java.time.Clock;
import java.util.List;

import static io.restassured.RestAssured.given;

public class TestClass {
    private final static String URL = "https://reqres.in/";
    //TEST 1
    @Test
    public void AvatarAndEmailTest() {
        Specifications.setSpec(Specifications.ReqSpec(URL), Specifications.RespSpecCodeN(200));
        List<ListUsersData> users = given()
                .when()
                .contentType(ContentType.JSON)
                .get("api/users?page=2")
                .then()
                .extract().body()
                .jsonPath().getList("data", ListUsersData.class); // get a list of users from the second page
        for (ListUsersData user : users) {
            Assert.assertTrue(user.getAvatar().contains(user.getId().toString())); // names of the user avatar files match
            Assert.assertTrue(user.getEmail().endsWith("@reqres.in")); // users emails ends with regres.in
        }
    }
    //TEST 2
    @Test
    public void RegTestOK() {
        Specifications.setSpec(Specifications.ReqSpec(URL), Specifications.RespSpecCodeN(200)); // Check the error codes.
        Integer id = 4;
        String token = "QpwL5tke4Pnpja7X4";
        Registration account = new Registration("eve.holt@reqres.in", "pistol");
        RegTrue regTrue = given()
                .body(account)
                .when()
                .post("api/register")
                .then()
                .extract().as(RegTrue.class);
        Assert.assertEquals(id, regTrue.getId());
        Assert.assertEquals(token, regTrue.getToken()); // Successful registration test
    }
    @Test
    public void RegTestFail() {
        Specifications.setSpec(Specifications.ReqSpec(URL), Specifications.RespSpecCodeN(400)); // Check the error codes.
        Registration account = new Registration("sydney@fife", ""); // empty password reg data
        RegFail regFail = given()
                .body(account)
                .when()
                .post("api/register")
                .then()
                .extract().as(RegFail.class);
        Assert.assertEquals("Missing password", regFail.getError()); // lack of a password test
    }
//TEST 3
    @Test
    public void ListDataSortTest(){
        Specifications.setSpec(Specifications.ReqSpec(URL), Specifications.RespSpecCodeN(200));
        List<ListUnknownData> data = given()
                .when()
                .contentType(ContentType.JSON)
                .get("api/unknown")
                .then()
                .extract().body()
                .jsonPath().getList("data", ListUnknownData.class);
        int year = data.getFirst().getYear();
        for(ListUnknownData datum : data){
            Assert.assertTrue(year <= datum.getYear()); // check if the data is sorted by year
            year = datum.getYear();
        }
    }
    //TEST 4
    @Test
    public void DeleteTest(){
        Specifications.setSpec(Specifications.ReqSpec(URL), Specifications.RespSpecCodeN(204)); // status code test after deletion
        given()
                .when()
                .delete("api/users/2")
                .then()
                .log().all();
    }
    //TEST 5
    @Test
    public void UpdateTest(){
        Specifications.setSpec(Specifications.ReqSpec(URL), Specifications.RespSpecCodeN(200));
        UserData user = new UserData("morpheus", "zion resident");
        UserDataResp response = given()
                .body(user)
                .when()
                .put("api/users/2")
                .then()
                .extract().as(UserDataResp.class);
        String CurrentTime = Clock.systemUTC().instant().toString().replaceAll("[^.]+", "");
        Assert.assertEquals(CurrentTime, response.getUpdatedAt().replaceAll("[^.]+", "")); // check if the update time is as expected
    }
}
