package requestentity.user;

import exceptions.ClientException;

import java.io.Serializable;

public class UserAttributeCreate  implements Serializable {

    private static final long serialVersionUID = -7461605741953145813L;

    private String user_id;
    private Object[] user_attribute_user_attribute_value;

    private UserAttributeCreate(){

    }

    public UserAttributeCreate(String user_id,Object[] user_attribute_user_attribute_value) throws ClientException {
        if(user_id.length()<1||user_id.length()>128){
            throw new ClientException("","");
        }

        this.user_id=user_id;
        this.user_attribute_user_attribute_value=user_attribute_user_attribute_value;

    }
    public String getUser_id() {

        return user_id;
    }

    public void setUser_id(String user_id) {

        this.user_id = user_id;
    }

    public Object[] getUser_attribute_user_attribute_value() {

        return user_attribute_user_attribute_value;
    }

    public void setUser_attribute_user_attribute_value(Object[] user_attribute_user_attribute_value) {
        this.user_attribute_user_attribute_value = user_attribute_user_attribute_value;
    }
}