package com.rezwan.uaamongo.model;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonValue;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.oauth2.common.DefaultOAuth2RefreshToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;

import java.io.Serializable;

@Document
public class UaaOAuth2RefreshToken implements Serializable, OAuth2RefreshToken {
    private static final long serialVersionUID = 8349970621900575838L;


    @Id
    private String tokenId;
    private String value;

    @JsonCreator
    public UaaOAuth2RefreshToken(String value) {
        this.value = value;
    }

    private UaaOAuth2RefreshToken() {
        this(null);
    }

    @Override
    @JsonValue
    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof DefaultOAuth2RefreshToken)) {
            return false;
        } else {
            DefaultOAuth2RefreshToken that = (DefaultOAuth2RefreshToken)o;
            if (this.value != null) {
                if (!this.value.equals(that.getValue())) {
                    return false;
                }
            } else if (that.getValue() != null) {
                return false;
            }

            return true;
        }
    }
    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }
    @Override
    public int hashCode() {
        return this.value != null ? this.value.hashCode() : 0;
    }
}