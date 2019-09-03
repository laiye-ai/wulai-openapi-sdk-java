package requestBean.user;

import java.io.Serializable;

public class UserCreateRequest implements Serializable {

    private static final long serialVersionUID = -6268132402244775802L;

    private String user_id;
    private String nickname;
    private String avatar_url;

    public UserCreateRequest(String user_id) {
            this.user_id=user_id;
         }

    public void setUser_id(String user_id){
        if (user_id.length()<=128&&user_id.length()>=1){
            this.user_id=user_id;
        }
    }

    public void setNickname(String nickname) {
        if (nickname.length()<=128){
            this.nickname = nickname;
        }
    }

    public void setAvatar_url(String avatar_url) {
        if (avatar_url.length()<=512){
            this.avatar_url = avatar_url;
        }
    }

    public String getUser_id() {
        return user_id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

}