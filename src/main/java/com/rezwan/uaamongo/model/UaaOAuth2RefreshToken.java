package com.rezwan.uaamongo.model;

import org.springframework.security.oauth2.common.DefaultOAuth2RefreshToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;

public class UaaOAuth2RefreshToken implements DefaultOAuth2RefreshToken {
    @Override
    public String getValue() {
        return null;
    }
}
