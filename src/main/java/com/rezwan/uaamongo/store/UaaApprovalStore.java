package com.rezwan.uaamongo.store;

import com.rezwan.uaamongo.model.UaaApproval;
import com.rezwan.uaamongo.repository.UaaApprovalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.approval.Approval;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UaaApprovalStore implements ApprovalStore {
    private final UaaApprovalRepository uaaApprovalRepository;

    public void setHandleRevocationsAsExpiry(boolean handleRevocationsAsExpiry) {
        this.handleRevocationsAsExpiry = handleRevocationsAsExpiry;
    }

    private boolean handleRevocationsAsExpiry = false;

    @Autowired
    public UaaApprovalStore(UaaApprovalRepository uaaApprovalRepository) {
        this.uaaApprovalRepository = uaaApprovalRepository;
    }

    @Override
    public boolean addApprovals(Collection<Approval> approvals) {
        boolean result = false;
        UaaApproval tempApproval;
        for (Approval approval : approvals) {

            tempApproval = uaaApprovalRepository.findByUserIdAndClientId(approval.getUserId(), approval.getClientId());
            if (tempApproval != null) {
                tempApproval.fromApproval(approval);
                if (this.uaaApprovalRepository.save(tempApproval) != null) {
                    result = true;
                }
            } else {
                tempApproval = new UaaApproval();
                tempApproval.fromApproval(approval);
                if (this.uaaApprovalRepository.save(tempApproval) != null) {
                    result = true;
                }
            }

        }
        return result;
    }

    @Override
    public boolean revokeApprovals(Collection<Approval> approvals) {
        //final Collection <UaaApproval> uaaApprovals= new ArrayList<>();
        boolean result = true;
        UaaApproval tempUaaApproval;
        for (Approval approval : approvals) {
            tempUaaApproval = uaaApprovalRepository.findByUserIdAndClientIdAndScope(approval.getUserId(), approval.getClientId(), approval.getScope());
            if (handleRevocationsAsExpiry) {

                if (tempUaaApproval != null) {
                    tempUaaApproval.setExpiresAt(approval.getExpiresAt());
                    uaaApprovalRepository.save(tempUaaApproval);
                    result = true;
                } else {
                    result = false;
                }
            } else {
                if (tempUaaApproval != null) {
                    uaaApprovalRepository.delete(tempUaaApproval);
                    result = true;
                } else {
                    result = false;
                }
            }
        }
        return result;
    }

    @Override
    public Collection<Approval> getApprovals(String userId, String clientId) {
        final Collection<UaaApproval> uaaApprovals = uaaApprovalRepository.findAllByUserIdAndClientId(userId, clientId);
        final Collection<Approval> approvals = new ArrayList<>();
        if (uaaApprovals.size()!=0) {
            for (UaaApproval uaaApproval : uaaApprovals) {
                approvals.add(uaaApproval.toApproval());
            }
        }
        return approvals;
    }
}
