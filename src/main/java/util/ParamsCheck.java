package util;

import exceptions.ClientException;

public class ParamsCheck {

    public static boolean checkUserId(String param) throws ClientException {
        if (null!=param){
            if (param.length()<1||param.length()>128){
                throw new ClientException("SDK_INVALID_PARAMS","userId长度须小于128位且不为0");
            }
        }else {
            throw new ClientException("SDK_INVALID_PARAMS","userId 不能为空");
        }
        return true;
    }

    public static boolean checkMsgId(String param) throws ClientException {
        if (null!=param){
            if (param.length()>18){
                throw new ClientException("SDK_INVALID_PARAMS","msgId长度须小于18位");
            }
        }else {
            throw new ClientException("SDK_INVALID_PARAMS","msgId 参数不能为空");
        }
        return true;
    }

    public static boolean checkExtra(String param) throws ClientException {
        if (param!=null){
            if (param.length()>1024){
                throw new ClientException("SDK_INVALID_PARAMS","extra must be [1..1024] characters");
            }
        }else{
            throw new ClientException("SDK_INVALID_PARAMS","extra must be not null");
        }
        return true;
    }

    public static boolean checkPage(int param) throws ClientException {
        if (param<1){
            throw new ClientException("SDK_INVALID_PARAMS","page need >=1");
        }
        return true;
    }

    public static boolean checkPageSize(int param) throws ClientException{
        if (param<1||param>200){
            throw new ClientException("SDK_INVALID_PARAMS","pageSize must be [1..200] characters");
        }
        return true;
    }
    public static boolean checkDirection(String param) throws ClientException{
        if (null!=param){
            if ("BACKWARD".equalsIgnoreCase(param)||"FORWARD".equalsIgnoreCase(param)){
                throw new ClientException("SDK_INVALID_PARAMS","direction need be  FORWARD or BACKWARD ");
            }
        }else {
            throw new ClientException("SDK_INVALID_PARAMS", "direction must be not null");
        }
        return true;
    }

    public static boolean checkNum(int param) throws ClientException {
        if (param<1||param>50){
            throw new ClientException("SDK_INVALID_PARAMS","num must be [1..50] characters");
        }
        return true;
    }
    public static boolean checkThirdMsgId(String param) throws ClientException {
        if (null==param||param.length()>64){
            throw new ClientException("SDK_INVALID_PARAMS","thirdMsgId must be <= 64 characters");
        }
        return true;
    }


}
