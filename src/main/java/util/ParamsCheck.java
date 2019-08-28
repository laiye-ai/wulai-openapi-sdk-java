package util;

import exceptions.ClientException;
import exceptions.ClientExceptionConstant;

public class ParamsCheck {


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


}
