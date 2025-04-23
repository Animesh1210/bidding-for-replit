package com.enam.bid.seller.repository;

import com.enam.bid.seller.entity.ondc.ProviderCredential;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderCredentialRepository extends JpaRepository<ProviderCredential, Integer> {
}