package com.rezwan.uaamongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.*;

public class UaaClientDeatails implements ClientDetails{
    @Id
    private String clientId;
    private Set<String> resourceIds = Collections.emptySet();
    private String secret;
    private Set<String> scopes= Collections.emptySet();
    private Set<String> authorizedGrantTypes= Collections.emptySet();
    private Set<String> registeredRedirectUri= Collections.emptySet();
    private Collection<GrantedAuthority> authorities= Collections.emptyList();
    private Integer accessTokenValiditySeconds;
    private Integer refreshTokenValiditySeconds;
    private Map<String, Object> additionalInformation = new LinkedHashMap<String, Object>();
    private Set<String> autoApproveScopes= Collections.emptySet();

    @PersistenceConstructor
    public UaaClientDeatails(String clientId,
                             Set<String> resourceIds,
                             String secret,
                             Set<String> scopes,
                             Set<String> authorizedGrantTypes,
                             Set<String> registeredRedirectUri,
                             Collection<GrantedAuthority> authorities,
                             Integer accessTokenValiditySeconds,
                             Integer refreshTokenValiditySeconds,
                             Map<String, Object> additionalInformation,
                             Set<String> autoApproveScopes) {
        this.clientId = clientId;
        this.resourceIds = resourceIds;
        this.secret = secret;
        this.scopes = scopes;
        this.authorizedGrantTypes = authorizedGrantTypes;
        this.registeredRedirectUri = registeredRedirectUri;
        this.authorities = authorities;
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
        this.additionalInformation = additionalInformation;
        this.autoApproveScopes = autoApproveScopes;
    }

    @Override
    public String getClientId() {
        return this.clientId;
    }

    @Override
    public Set<String> getResourceIds() {
        return this.resourceIds;
    }

    @Override
    public boolean isSecretRequired() {
        return this.secret != null;
    }

    @Override
    public String getClientSecret() {
        return this.secret;
    }

    @Override
    public boolean isScoped() {
        return this.scopes != null && !this.scopes.isEmpty();
    }

    @Override
    public Set<String> getScope() {
        return this.scopes;
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return this.authorizedGrantTypes;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return this.registeredRedirectUri;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return this.accessTokenValiditySeconds;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return this.refreshTokenValiditySeconds;
    }

    @Override
    public boolean isAutoApprove(String scope) {
        if (autoApproveScopes == null) {
            return false;
        }
        for (String auto : autoApproveScopes) {
            if (auto.equals("true") || scope.matches(auto)) {
                return true;
            }
        }
        return false;

    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return this.additionalInformation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UaaClientDeatails)) return false;
        UaaClientDeatails that = (UaaClientDeatails) o;
        return Objects.equals(clientId, that.clientId) &&
                Objects.equals(resourceIds, that.resourceIds) &&
                Objects.equals(secret, that.secret) &&
                Objects.equals(scopes, that.scopes) &&
                Objects.equals(authorizedGrantTypes, that.authorizedGrantTypes) &&
                Objects.equals(registeredRedirectUri, that.registeredRedirectUri) &&
                Objects.equals(authorities, that.authorities) &&
                Objects.equals(accessTokenValiditySeconds, that.accessTokenValiditySeconds) &&
                Objects.equals(refreshTokenValiditySeconds, that.refreshTokenValiditySeconds) &&
                Objects.equals(additionalInformation, that.additionalInformation) &&
                Objects.equals(autoApproveScopes, that.autoApproveScopes);
    }

    @Override
    public int hashCode() {

        return Objects.hash(clientId, resourceIds, secret, scopes, authorizedGrantTypes, registeredRedirectUri, authorities, accessTokenValiditySeconds, refreshTokenValiditySeconds, additionalInformation, autoApproveScopes);
    }

    @Override
    public String toString() {
        return "UaaClientDeatails{" +
                "clientId='" + clientId + '\'' +
                ", resourceIds=" + resourceIds +
                ", secret='" + secret + '\'' +
                ", scopes=" + scopes +
                ", authorizedGrantTypes=" + authorizedGrantTypes +
                ", registeredRedirectUri=" + registeredRedirectUri +
                ", authorities=" + authorities +
                ", accessTokenValiditySeconds=" + accessTokenValiditySeconds +
                ", refreshTokenValiditySeconds=" + refreshTokenValiditySeconds +
                ", additionalInformation=" + additionalInformation +
                ", autoApproveScopes=" + autoApproveScopes +
                '}';
    }
}
