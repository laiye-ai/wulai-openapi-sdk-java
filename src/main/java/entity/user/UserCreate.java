package entity.user;

import java.io.Serializable;

public class UserCreate implements Serializable {

    private static final long serialVersionUID = -6268132402244775802L;

    private String user_id;
    private String nikename;
    private String avatar_url;

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setNikename(String nikename) {
        this.nikename = nikename;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getNikename() {
        return nikename;
    }

    public String getAvatar_url() {
        return avatar_url;
    }
}
