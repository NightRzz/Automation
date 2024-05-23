package API.RegTestClasses;

public class RegTrue {
    private Integer id;
    private String token;

    public RegTrue() {
    }

    public RegTrue(Integer id, String token) {
        this.id = id;
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public String getToken() {
        return token;
    }
}
