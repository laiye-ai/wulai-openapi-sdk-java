package entity.requestentity.user;

import exceptions.ClientException;

import java.io.Serializable;

public class UserCreate implements Serializable {

    private static final long serialVersionUID = -6268132402244775802L;

    private String user_id;
    private String nikename;
    private String avatar_url;

    public void setUser_id(String user_id) throws ClientException {
        if (user_id.length()<128&&user_id.length()>0) {
            this.user_id = user_id;
        }else {
            throw new ClientException("","user_id length  must be between 1 and 128 runes");
        }
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
