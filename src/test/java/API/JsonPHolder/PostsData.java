package API.JsonPHolder;

public class PostsData {
    private Integer userId;
    private Integer id;
    private String title;
    private String body;

    public PostsData() {
    }

    public PostsData(Integer userId, Integer id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }
    public PostsData(String title, String body, Integer userId) {
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
