import exceptions.ClientException;
import exceptions.ServerException;
import module.request.msg.BotResponseRequest;
import module.request.msg.HistoryRequest;
import module.request.msg.ReceiveRequest;
import module.request.msg.SyncRequest;
import module.request.user.UserAttributeCreateRequest;
import module.request.user.UserAttributeListRequest;
import module.request.user.UserCreateRequest;
import module.response.msg.*;
import module.response.user.UserAttributeListResponse;

public interface IUser {

    int userCreate(UserCreateRequest userCreateRequest)
            throws ClientException, ServerException;

    int userAttributeCreate(UserAttributeCreateRequest userAttributeCreateRequest)
            throws ClientException, ServerException;

    UserAttributeListResponse userAttributeList(UserAttributeListRequest userAttributeListRequest)
            throws ClientException, ServerException;

}
