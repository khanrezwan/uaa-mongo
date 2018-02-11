package com.rezwan.uaamongo.repository;

import com.rezwan.uaamongo.model.UaaOAuth2RefreshToken;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UaaOAuth2RefreshTokenRepository extends MongoRepository<UaaOAuth2RefreshToken, String>{
    UaaOAuth2RefreshToken findByTokenId(String tokenId);
    UaaOAuth2RefreshToken findByValue(String value);
}
