package com.rezwan.uaamongo.repository;

import com.rezwan.uaamongo.model.UaaOAuth2AccessToken;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UaaOAuth2AccessTokenRepository extends MongoRepository<UaaOAuth2AccessToken,String>{

    UaaOAuth2AccessToken findByTokenId(String tokenId);
}
