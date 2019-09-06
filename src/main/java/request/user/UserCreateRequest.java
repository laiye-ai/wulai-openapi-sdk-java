package request.user;

import exceptions.ClientException;
import exceptions.ClientExceptionConstant;
import util.ParamsCheck;

import java.io.Serializable;

public class UserCreateRequest implements Serializable {

    private static final long serialVersionUID = -6268132402244775802L;

    private String userId;
    private String nickname;
    private String avatarUrl;

    public UserCreateRequest(String userId) throws ClientException {
        ParamsCheck.checkUserId(userId);
        this.userId = userId;
    }

    public void setUserId(String userId) throws ClientException {
        ParamsCheck.checkUserId(userId);
        this.userId = userId;
    }

    public void setNickname(String nickname) throws ClientException {
        ParamsCheck.checkNickname(nickname);
        this.nickname = nickname;
    }

    public void setAvatarUrl(String avatarUrl) throws ClientException {
        ParamsCheck.checkAvatarUrl(avatarUrl);
        this.avatarUrl = avatarUrl;
    }

    public String getUserId() {
        return userId;
    }

    public String getNickname() {
        return nickname;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

}