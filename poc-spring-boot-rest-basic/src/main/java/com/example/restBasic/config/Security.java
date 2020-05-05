package com.example.restBasic.config;

public class Security {
    static final String SERCRET = "restBasic";
    static final String TOKEN_PREFIX = "Beare ";
    static final String HEAD_STRING = "Authorization";
    static final String SING_UP_URL = "/users/sing-up";
    static final long EXPIRATION_TIME = 86400000l;
}
