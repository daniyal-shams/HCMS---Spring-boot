package com.healthcare.billing_service.repository;

import com.healthcare.billing_service.model.Billing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BillingRepository extends JpaRepository<Billing, Long> {
}