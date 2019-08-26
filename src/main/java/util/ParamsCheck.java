package util;

import exceptions.ClientException;

public class StringCheck {

    public static boolean checkUserId(String param) throws ClientException {
        if (null!=param){
            if (param.length()<1||param.length()>128){
                throw new ClientException("","userId长度须小于128位且不为0");
            }
        }else {
            throw new ClientException("","userId 不能为空");
        }
        return true;
    }

    public static boolean checkMsgId(String param) throws ClientException {
        if (null!=param){
            if (param.length()>18){
                throw new ClientException("","msgId长度须小于18位");
            }
        }else {
            throw new ClientException("","msgId 参数不能为空");
        }
        return true;
    }

    public static boolean checkExtra(String param) throws ClientException {
        if (param!=null){
            if (param.length()>1024){
                throw new ClientException("","extra长度须小于等于1024位");
            }
        }else{
            throw new ClientException("","extra 参数不能为空");
        }
        return true;
    }

    public static boolean checkPage(int param) throws ClientException {
        if (param<1){
            throw new ClientException("","page 须大于等于1");
        }
        return true;
    }

    public static boolean checkPageSize(int param) throws ClientException{
        if (param<1||param>200){
            throw new ClientException("","pageSize 须在1和200区间内");
        }
        return true;
    }
    public static boolean checkDirection(String param) throws ClientException{
        if (null!=param){
            if ("BACKWARD".equalsIgnoreCase(param)||"FORWARD".equalsIgnoreCase(param)){
                throw new ClientException("","direction 须为 FORWARD 或 BACKWARD ");
            }
        }else {
            throw new ClientException("", "direction 不能为空");
        }
        return true;
    }

    public static boolean checkNum(int param) throws ClientException {
        if (param<1||param>50){
            throw new ClientException("","num 须为大于1且小于50");
        }
        return true;
    }


}
