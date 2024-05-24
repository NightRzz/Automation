package API.JsonPHolder;

import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class TestClass {
    private final static String URL = "https://jsonplaceholder.typicode.com/";
    @Test // Test 1
    public void GetPostWithId1() {
        PostsData post = given()
                .when()
                .get(URL + "posts/1")
                .then().statusCode(200)
                .extract().body()
                .as(PostsData.class);
        Assert.assertEquals("sunt aut facere repellat provident occaecati excepturi optio reprehenderit", post.getTitle());
        //checks if the title of the post with id 1 is correct
    }
    @Test // Test 2
    public void UpdatePostWithId2() {
        PostsData postPre = new PostsData("Post title", "Post body", 1);
        given().body(postPre)
                .when()
                .put(URL + "posts/2")
                .then().log().all().statusCode(200)
                .extract().body()
                .as(PostsData.class);
        PostsData postAfter = given()
                .when()
                .contentType(ContentType.JSON)
                .get(URL + "posts/2")
                .then().log().all()
                .extract().body()
                .as(PostsData.class);
        Assert.assertEquals(postPre.getBody(), postAfter.getBody());  // won't work because the API doesn't actually store any changes
    }
    @Test // Test 3
    public void CreatePost() {
        Integer postCount = given()
                .when()
                .get(URL + "posts")
                .then().statusCode(200)
                .extract().body()
                .jsonPath().getList("", PostsData.class).size();

        PostsData post = new PostsData("My Post", "My body", 3);
        PostsData afterPost = given()
                .body(post)
                .when()
                .post(URL + "posts")
                .then().statusCode(201)
                .extract().body()
                .as(PostsData.class);
        postCount++;
        Assert.assertEquals(postCount, afterPost.getId()); // checks if the post was created by comparing the posts count before and after
    }
    @Test // Test 4
    public void DeletePostWithId3() {
        given()
                .when()
                .delete(URL + "posts/3")
                .then().statusCode(200);
        given()
                .when()
                .get(URL + "posts/3")
                .then().statusCode(404); // won't work because the fake API doesn't actually delete anything
    }
}
