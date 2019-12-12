package com.util;

import java.net.URI;

public class Credentials {

    private String pubkey = "G2qcOW4lnwAUHlKq7WKOaJ1Obww4fQCf00205e7cc49ca502b1";
    private String secret = "x79ATwIV4dM7H2GiVLj7";

    public Credentials() {

    }

    public Credentials(String pubkey,String secret){
        this.pubkey=pubkey;
        this.secret=secret;
    }

    public String getSecret() {
        return secret;
    }

    public String getPubkey() {
        return pubkey;
    }


}
