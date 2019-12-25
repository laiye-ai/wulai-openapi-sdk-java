package com.module.response.msg;

import java.util.List;

public class UserSuggestions {

    private List<String> suggestion;

    public void setSuggestion(List<String> suggestion) {
        this.suggestion = suggestion;
    }

    public List<String> getSuggestion() {
        return suggestion;
    }
}
