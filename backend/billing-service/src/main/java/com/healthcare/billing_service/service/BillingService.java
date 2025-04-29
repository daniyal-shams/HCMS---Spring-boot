package com.healthcare.billing_service.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.healthcare.billing_service.dto.BillingDto;

@Service
public interface BillingService {

    void createBillingRecord(Long appointmentId, Double amount);

    void updateBillingRecord(Long billingId, Double amount);

    void deleteBillingRecord(Long billingId);

    List<BillingDto> getAllBillingRecords();

    BillingDto getBillingRecordById(Long billingId);

    //pagination and sorting
    Page<BillingDto> getAllBillingRecords(int page, int size, String sortBy, String sortDir);   



}
