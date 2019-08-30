package requestentity.user;

import exceptions.ClientException;

import java.io.Serializable;

public class UserCreate implements Serializable {

    private static final long serialVersionUID = -6268132402244775802L;

    private String user_id;
    private String nickname;
    private String avatar_url;


    public UserCreate(String user_id,String nickname,String avatar_url) throws ClientException {
        if(user_id.length()<1||user_id.length()>128){
            throw new ClientException("","");
        }
        if (nickname.length()>128){
            throw new ClientException("","");
        }
        if (avatar_url.length()>512){
            throw new ClientException("","");
        }
        this.user_id=user_id;
        this.nickname =user_id;
        this.avatar_url=avatar_url;
    }

    public UserCreate(String user_id) throws ClientException {
        if(user_id.length()<1||user_id.length()>128){
            throw new ClientException("","");
        }
        this.user_id=user_id;
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
