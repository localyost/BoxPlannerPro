package com.localyost.boxplannerpro.remote;

public class LoginBody {

    private final String headeremail;
    private final String headerpassword;

    public LoginBody(String headeremail, String headerpassword) {
        this.headeremail = headeremail;
        this.headerpassword = headerpassword;
    }
}
