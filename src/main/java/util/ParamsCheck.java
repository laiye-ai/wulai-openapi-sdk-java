package util;

import exceptions.ClientException;
import exceptions.ClientExceptionConstant;

public class ParamsCheck {

    public static void checkUserId(String param) throws ClientException {
        if (null!=param){
            if (param.length()<1||param.length()>128){
                throw new ClientException(ClientExceptionConstant.SDK_INVALID_PARAMS,"must be [1..1024] characters");
            }
        }else {
            throw new ClientException(ClientExceptionConstant.SDK_INVALID_PARAMS,"userId can not be null");
        }
    }

    public static void checkMsgId(String param) throws ClientException {
        if (null!=param){
            if (param.length()>18){
                throw new ClientException(ClientExceptionConstant.SDK_INVALID_PARAMS,"msgId must be less than or equal 18 characters");
            }
        }else {
            throw new ClientException(ClientExceptionConstant.SDK_INVALID_PARAMS,"msgId can not be null");
        }
    }

    public static void checkExtra(String param) throws ClientException {
        if (param!=null){
            if (param.length()>1024){
                throw new ClientException(ClientExceptionConstant.SDK_INVALID_PARAMS,"extra must be [1..1024] characters");
            }
        }else{
            throw new ClientException(ClientExceptionConstant.SDK_INVALID_PARAMS,"extra must be not null");
        }
    }

    public static void checkPage(int param) throws ClientException {
        if (param<1){
            throw new ClientException(ClientExceptionConstant.SDK_INVALID_PARAMS,"page need >=1");
        }
    }

    public static void checkPageSize(int param) throws ClientException{
        if (param<1||param>200){
            throw new ClientException(ClientExceptionConstant.SDK_INVALID_PARAMS,"pageSize must be [1..200] characters");
        }
    }
    public static void checkDirection(String param) throws ClientException{
        if (null!=param){
            if ("BACKWARD".equalsIgnoreCase(param)||"FORWARD".equalsIgnoreCase(param)){
                throw new ClientException(ClientExceptionConstant.SDK_INVALID_PARAMS,"direction need be  FORWARD or BACKWARD ");
            }
        }else {
           // ClientException e=new ClientException(ClientExceptionConstant.SDK_INVALID_PARAMS);
           throw new ClientException(ClientExceptionConstant.SDK_INVALID_PARAMS, "direction must be not null");
            //throw e ;
        }
    }

    public static void checkNum(int param) throws ClientException {
        if (param<1||param>50){
            throw new ClientException(ClientExceptionConstant.SDK_INVALID_PARAMS,"num must be [1..50] characters");
        }
    }
    public static void checkThirdMsgId(String param) throws ClientException {
        if (null==param||param.length()>64){
            throw new ClientException(ClientExceptionConstant.SDK_INVALID_PARAMS,"thirdMsgId must be <= 64 characters");
        }
    }
    public static void checkApiVersion(String version) throws ClientException {
        if (!"v2".equalsIgnoreCase(version)){
            throw new ClientException(ClientExceptionConstant.SDK_NOT_SUPPORT,"apiVersion only support v2");
        }
    }

    public static void checkOpts(String method) throws ClientException {
        if (!"post".equalsIgnoreCase(method)){
            throw new ClientException(ClientExceptionConstant.SDK_NOT_SUPPORT,"Wulai openApi is only support POST method");
        }
    }

    public static void checkEndPoint(String endpoint){
        endpoint.trim().matches("https?://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]");
    }


}
