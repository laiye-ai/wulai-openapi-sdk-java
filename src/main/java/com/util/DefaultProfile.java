package com.util;

import java.net.URI;

public class DefaultProfile {

    private static URI endpoint=URI.create("http://openapi.wul.ai/");
    private static String apiVersion="v2";

    public static URI getEndpoint() {
        return endpoint;
    }

    public static String getApiVersion(){
        return apiVersion;
    }

}
