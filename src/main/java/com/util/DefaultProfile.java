package com.util;

import java.net.URI;

public class DefaultProfile {

    private static URI endpoint = URI.create("http://openapi.wul.ai/");


    public static URI getEndpoint() {
        return endpoint;
    }


    public static void setEndpoint(URI endpoint) {
        DefaultProfile.endpoint = endpoint;
    }
}
