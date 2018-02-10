package com.rezwan.uaamongo;

import com.rezwan.uaamongo.repository.UaaApprovalRepository;
import com.rezwan.uaamongo.store.UaaApprovalStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.approval.Approval;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import static org.springframework.security.oauth2.provider.approval.Approval.ApprovalStatus.APPROVED;

@SpringBootApplication
@EnableResourceServer
public class UaaMongoApplication implements CommandLineRunner {

    @Autowired
    private UaaApprovalStore uaaApprovalStore;

    @Autowired
    private UaaApprovalRepository uaaApprovalRepository;

	public static void main(String[] args) {
		SpringApplication.run(UaaMongoApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
	    uaaApprovalRepository.deleteAll();
	    //Approval(String userId, String clientId, String scope, int expiresIn, ApprovalStatus status)
        Approval approval1 = new Approval("123","234","read", new Date(), APPROVED);
        Approval approval2 = new Approval("123","234","read", new Date(), APPROVED);
        Collection <Approval> approvals = new ArrayList<>();
        approvals.add(approval1);
        approvals.add(approval2);
        uaaApprovalStore.addApprovals(approvals);
        Collection<Approval>approvals1 = uaaApprovalStore.getApprovals("123","23444");
	}
}
