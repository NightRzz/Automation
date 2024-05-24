package API.ReqRes.UsersTestClassess;

public class UserDataResp extends UserData {
    private String updatedAt;
    public UserDataResp() {
    }
    public UserDataResp(String name, String job, String updatedAt) {
        super(name, job);
        this.updatedAt = updatedAt;
    }
    public String getUpdatedAt() {
        return updatedAt;
    }
}
