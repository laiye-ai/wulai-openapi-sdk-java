package util;

import exceptions.ClientException;
import exceptions.ClientExceptionConstant;

public class ParamsCheck {


    public static void checkApiVersion(String version) throws ClientException {
        if (!"v2".equalsIgnoreCase(version)) {
            throw new ClientException(ClientExceptionConstant.SDK_NOT_SUPPORT, "apiVersion only support v2");
        }
    }

    public static void checkUserId(String param) throws ClientException {
        if (null != param) {
            if (param.length() < 1 || param.length() > 128) {
                throw new ClientException(ClientExceptionConstant.SDK_INVALID_PARAMS, "must be [1..1024] characters");
            }
        } else {
            throw new ClientException(ClientExceptionConstant.SDK_INVALID_PARAMS, "userId can not be null");
        }
    }
    public static void checkAvatarUrl(String param) throws ClientException {
        if (param!=null && param.length()>512){
            throw new ClientException(ClientExceptionConstant.SDK_INVALID_PARAMS,"avatarUrl need less than 512 characters");
        }
    }

    public static void checkNickname(String param) throws ClientException {
        if (param !=null && param.length() > 128){
            throw new ClientException(ClientExceptionConstant.SDK_INVALID_PARAMS,"nickname need less than 128 characters");
        }
    }

    public static void checkMsgId(String param) throws ClientException {
        if (param != null && param.length() > 18) {
            throw new ClientException(ClientExceptionConstant.SDK_INVALID_PARAMS,
                    "msgId must be less than or equal 18 characters");
        }
    }

    public static void checkExtra(String param) throws ClientException {
        if (param != null && param.length() > 1024) {
            throw new ClientException(ClientExceptionConstant.SDK_INVALID_PARAMS, "extra must be [1..1024] characters");
        }
    }

    public static void checkPage(int param) throws ClientException {
        if (param < 1) {
            throw new ClientException(ClientExceptionConstant.SDK_INVALID_PARAMS, "page need >=1");
        }
    }

    public static void checkPageSize(int param) throws ClientException {
        if (param < 1 || param > 200) {
            throw new ClientException(ClientExceptionConstant.SDK_INVALID_PARAMS, "pageSize must be [1..200] characters");
        }
    }


    public static void checkNum(int param) throws ClientException {
        if (param < 1 || param > 50) {
            throw new ClientException(ClientExceptionConstant.SDK_INVALID_PARAMS, "num must be [1..50] characters");
        }
    }

    public static void checkThirdMsgId(String param) throws ClientException {
        if (param != null && param.length() > 64) {
            throw new ClientException(ClientExceptionConstant.SDK_INVALID_PARAMS, "thirdMsgId must be <= 64 characters");
        }
    }

    public static void checkMsgTs(String param) throws ClientException {
        String re="[0-9]{13}";
        if (param != null && !param.matches(re)){
            throw new ClientException(ClientExceptionConstant.SDK_INVALID_PARAMS,
                    "msgTs'length must be 13 characters");
        }else if (param == null){
            throw new ClientException(ClientExceptionConstant.SDK_INVALID_PARAMS,"msgTs can not be null");
        }
    }

    public static void checkEndPoint(String endpoint) {
        endpoint.trim().matches("https?://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]");
    }

}
