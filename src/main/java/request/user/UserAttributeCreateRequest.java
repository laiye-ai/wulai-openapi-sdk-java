package request.user;

import exceptions.ClientException;
import util.ParamsCheck;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserAttributeCreateRequest  implements Serializable {

    private static final long serialVersionUID = -7461605741953145813L;

    private String user_id;

    private List<UserAttributeUserAttributeValue> user_attribute_user_attribute_value=
            new ArrayList<>();

    public UserAttributeCreateRequest(String user_id) throws ClientException {
        ParamsCheck.checkUserId(user_id);
        this.user_id = user_id;
    }

    public List<UserAttributeUserAttributeValue>  getUser_attribute_user_attribute_value() {
        return user_attribute_user_attribute_value;
    }

    public String getUser_id() {
        return user_id;
    }

    public void  addUserAttributeUserAttributeValue(UserAttributeUserAttributeValue userAttributeUserAttributeValue){
        this.user_attribute_user_attribute_value.add(userAttributeUserAttributeValue);
    }
}
