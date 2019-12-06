package com.util;

import java.net.URI;

public class Credentials {

    private String pubkey = "84Mb38jJR6Bg7qB3MT09cSHPdrEImIgv00362b370af3cc02eb";
    private String secret = "x9xBAnz8JHrI8GdsdVoJ";

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
