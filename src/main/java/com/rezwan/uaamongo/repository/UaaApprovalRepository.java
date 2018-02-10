package com.rezwan.uaamongo.repository;

import com.rezwan.uaamongo.model.UaaApproval;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.oauth2.provider.approval.Approval;

import java.util.Collection;
import java.util.List;


public interface UaaApprovalRepository extends MongoRepository<UaaApproval,String>{
    UaaApproval findByUserId(String userId);
    UaaApproval findByUserIdAndClientId(String userId, String clientId);
    UaaApproval findByUserIdAndClientIdAndScope(String userId, String clientId, String scope);
    List<UaaApproval> findAllByUserIdAndClientId(String userId, String clientId);
}
