package com.module.response.msg;

import java.util.List;

public class UserSuggestions {

    private List<Suggestion> suggestion;

    public void setSuggestion(List<Suggestion> suggestion) {
        this.suggestion = suggestion;
    }

    public List<Suggestion> getSuggestion() {
        return suggestion;
    }
}
