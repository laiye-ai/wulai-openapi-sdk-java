import com.DefaultClient;
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
    private DefaultClient defaultClient = new DefaultClient();

    @Test
    public void TestUser() throws ServerException, ClientException {
        String userId = "zhangtao@test";
        CreateUser createUser = new CreateUser();
        createUser.setUserId(userId);
        createUser.setNickname("zhangtao");
        int code = createUser.request(defaultClient);
        if (200 != code) {
            throw new ServerException("1", "createuser error", 1);

        }

        QueryUserInfo queryUserInfo = new QueryUserInfo();
        queryUserInfo.setUserId(userId);
        UserInfo userInfo = queryUserInfo.request(defaultClient);
        if (userInfo.getNickname() == null) {
            throw new ServerException("1", "query userinfo error ", 1);
        }

        UpdateUser updateUser = new UpdateUser();
        updateUser.setUserId(userId);
        updateUser.setNickname("Tom");
        updateUser.setAvatarUrl("http://www.baidu.com");
        int code2 = updateUser.request(defaultClient);
        if (200 != code2) {
            throw new ServerException("1", "update user error", 1);
        }

        QueryUserAttribute queryUserAttribute = new QueryUserAttribute();
        queryUserAttribute.setUserId(userId);
        List<UserAttributeUserAttributeValue> list = queryUserAttribute.request(defaultClient);
        UserAttributeUserAttributeValue userAttributeUserAttributeValue = list.get(0);
        UserAttribute userAttribute = userAttributeUserAttributeValue.getUserAttribute();
        UserAttributeValue userAttributeValue = userAttributeUserAttributeValue.getUserAttributeValue();
        System.out.println(userAttribute.getId());
        System.out.println(userAttribute.getValueType());
        System.out.println(userAttribute.getLifespan());
        System.out.println(userAttribute.getName());
        System.out.println(userAttribute.getType());
        System.out.println(userAttributeValue.getId());
        System.out.println(userAttributeValue.getName());

        CreateUserAttribute createUserAttribute = new CreateUserAttribute();
        createUserAttribute.setUserId(userId);
        list.set(0, userAttributeUserAttributeValue);
        createUserAttribute.setUserAttributeUserAttributeValues(list);
        int code3 = createUserAttribute.request(defaultClient);
        if (200 != code3) {
            throw new ServerException("1", "create userattribute error", 1);
        }

    }

}
