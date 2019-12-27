import com.WulaiClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.user.UserAttribute;
import com.module.request.user.UserAttributeUserAttributeValue;
import com.module.request.user.UserAttributeValue;
import com.module.response.user.UserInfo;
import com.wulai.user.*;
import org.junit.Test;

import java.util.List;

public class TestUser {
    private WulaiClient wulaiClient = new WulaiClient();

    @Test
    public void TestUser() throws ServerException, ClientException {
        String userId = "zhangtao@test";
        CreateUser createUser = new CreateUser();
        createUser.setUserId(userId);
        createUser.setNickname("zhangtao");
        int code = createUser.request(wulaiClient);
        if (200 != code) {
            throw new ServerException("1", "createuser error", 1);

        }

        QueryUserInfo queryUserInfo = new QueryUserInfo();
        queryUserInfo.setUserId(userId);
        UserInfo userInfo = queryUserInfo.request(wulaiClient);
        if (userInfo.getNickname() == null) {
            throw new ServerException("1", "query userinfo error ", 1);
        }


        UpdateUser updateUser = new UpdateUser();
        updateUser.setUserId(userId);
        updateUser.setNickname("Tom");
        updateUser.setAvatarUrl("http://www.baidu.com");
        int code2 = updateUser.request(wulaiClient);
        if (200 != code2) {
            throw new ServerException("1", "update user error", 1);
        }


        QueryUserAttribute queryUserAttribute = new QueryUserAttribute();
        queryUserAttribute.setUserId(userId);
        List<UserAttributeUserAttributeValue> list = queryUserAttribute.request(wulaiClient);
        UserAttributeUserAttributeValue userAttributeUserAttributeValue = list.get(0);
        UserAttribute userAttribute = userAttributeUserAttributeValue.getUserAttribute();
        UserAttributeValue userAttributeValue = userAttributeUserAttributeValue.getUserAttributeValue();
        if (userAttribute.getId() == null || userAttribute.getValueType() == null || userAttribute.getName() == null
                || userAttribute.getType() == null || userAttributeValue.getId() == null | userAttributeValue.getName() == null) {
            throw new ServerException("", "Query UserAttribute error", 1);
        }


        CreateUserAttribute createUserAttribute = new CreateUserAttribute();
        createUserAttribute.setUserId(userId);
        list.set(0, userAttributeUserAttributeValue);
        createUserAttribute.setUserAttributeUserAttributeValues(list);
        int code3 = createUserAttribute.request(wulaiClient);
        if (200 != code3) {
            throw new ServerException("1", "create userattribute error", 1);
        }

    }

}
