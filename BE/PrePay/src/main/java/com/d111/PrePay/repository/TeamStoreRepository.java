package com.d111.PrePay.repository;

import com.d111.PrePay.model.TeamStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamStoreRepository extends JpaRepository<TeamStore,Long> {
}
