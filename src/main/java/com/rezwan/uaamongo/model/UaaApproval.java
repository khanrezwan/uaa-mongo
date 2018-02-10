package com.rezwan.uaamongo.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.security.oauth2.common.util.JsonDateDeserializer;
import org.springframework.security.oauth2.common.util.JsonDateSerializer;
import org.springframework.security.oauth2.provider.approval.Approval;

import java.util.Calendar;
import java.util.Date;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class UaaApproval {
    @Id
    private String id;

    private String userId;

    private String clientId;

    private String scope;

//    public enum ApprovalStatus {
//        APPROVED,
//        DENIED;
//    }

    private Approval.ApprovalStatus status;

    private Date expiresAt;

    private Date lastUpdatedAt;

    public UaaApproval(String userId, String clientId, String scope, int expiresIn, Approval.ApprovalStatus status) {
        this(userId, clientId, scope, new Date(), status, new Date());
        Calendar expiresAt = Calendar.getInstance();
        expiresAt.add(Calendar.MILLISECOND, expiresIn);
        setExpiresAt(expiresAt.getTime());
    }

    public UaaApproval(String userId, String clientId, String scope, Date expiresAt, Approval.ApprovalStatus status) {
        this( userId, clientId, scope, expiresAt, status, new Date());
    }

    @PersistenceConstructor
    public UaaApproval(String id, String userId, String clientId, String scope, Date expiresAt, Approval.ApprovalStatus status) {
        this( userId, clientId, scope, expiresAt, status, new Date());
        this.id = id;

    }

    private UaaApproval( String userId, String clientId, String scope, Date expiresAt, Approval.ApprovalStatus status, Date lastUpdatedAt) {

        this.userId = userId;
        this.clientId = clientId;
        this.scope = scope;
        this.expiresAt = expiresAt;
        this.status = status;
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public UaaApproval() { }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? "" : userId;
    }

    public String getClientId() {
        return clientId;
    }

     public Approval toApproval(){
        return new Approval(this.getUserId(),this.getClientId(),this.getScope(),this.getExpiresAt(),this.getStatus());
//        approval.setUserId(this.getUserId());
//        approval.setClientId(this.getClientId());
//        approval.setScope(this.getScope());
//        approval.setStatus(this.getStatus());
//        approval.setExpiresAt(this.getExpiresAt());
//        approval.setLastUpdatedAt(this.getLastUpdatedAt());

      //  return approval;
    }

    public void fromApproval(Approval approval){
        this.setUserId(approval.getUserId());
        this.setClientId(approval.getClientId());
        this.setScope(approval.getScope());
        this.setStatus(approval.getStatus() == null ? Approval.ApprovalStatus.APPROVED: approval.getStatus());
        this.setExpiresAt(approval.getExpiresAt());
        this.setLastUpdatedAt(approval.getLastUpdatedAt());

    }

    public void setClientId(String clientId) {
        this.clientId = clientId == null ? "" : clientId;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope == null ? "" : scope;
    }

    @JsonSerialize(using = JsonDateSerializer.class, include = JsonSerialize.Inclusion.NON_NULL)
    public Date getExpiresAt() {
        return expiresAt;
    }

    @JsonDeserialize(using = JsonDateDeserializer.class)
    public void setExpiresAt(Date expiresAt) {
        if (expiresAt == null) {
            Calendar thirtyMinFromNow = Calendar.getInstance();
            thirtyMinFromNow.add(Calendar.MINUTE, 30);
            expiresAt = thirtyMinFromNow.getTime();
        }
        this.expiresAt = expiresAt;
    }

    @JsonSerialize(using = JsonDateSerializer.class, include = JsonSerialize.Inclusion.NON_NULL)
    public Date getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    @JsonDeserialize(using = JsonDateDeserializer.class)
    public void setLastUpdatedAt(Date lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    @JsonIgnore
    public boolean isCurrentlyActive() {
        return expiresAt != null && expiresAt.after(new Date());
    }

    @JsonIgnore
    public boolean isApproved() {
        return isCurrentlyActive() && status== Approval.ApprovalStatus.APPROVED;
    }

    public void setStatus(Approval.ApprovalStatus status) {
        this.status = status;
    }

    public Approval.ApprovalStatus getStatus() {
        return status;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + userId.hashCode();
        result = prime * result + clientId.hashCode();
        result = prime * result + scope.hashCode();
        result = prime * result + status.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof UaaApproval)) {
            return false;
        }
        UaaApproval other = (UaaApproval) o;
        return userId.equals(other.userId) && clientId.equals(other.clientId) && scope.equals(other.scope) && status == other.status;
    }

    @Override
    public String toString() {
        return String.format("[%s, %s, %s, %s, %s, %s]", userId, scope, clientId, expiresAt, status.toString(), lastUpdatedAt);
    }

}
