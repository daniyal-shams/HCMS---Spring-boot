package com.healthcare.billing_service.service;

import com.healthcare.billing_service.dto.BillingDto;
import com.healthcare.billing_service.model.Billing;
import com.healthcare.billing_service.repository.BillingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillingServiceImpl implements BillingService {

    @Autowired
    private BillingRepository billingRepository;

    @Override
    public void createBillingRecord(Long appointmentId, Double amount) {
        Billing billing = new Billing();
        billing.setAppointmentId(appointmentId);
        billing.setAmount(amount);
        billing.setStatus("PENDING");
        billing.setBillingDate(LocalDateTime.now());
        billingRepository.save(billing);
    }

    @Override
    public void updateBillingRecord(Long billingId, Double amount) {
        Billing billing = billingRepository.findById(billingId)
                .orElseThrow(() -> new RuntimeException("Billing record not found"));
        billing.setAmount(amount);
        billing.setStatus("UPDATED");
        billingRepository.save(billing);
    }

    @Override
    public void deleteBillingRecord(Long billingId) {
        Billing billing = billingRepository.findById(billingId)
                .orElseThrow(() -> new RuntimeException("Billing record not found"));
        billingRepository.delete(billing);
    }

    @Override
    public List<BillingDto> getAllBillingRecords() {
        List<Billing> billings = billingRepository.findAll();
        return billings.stream()
                .map(billing -> new BillingDto(
                        billing.getId(),
                        billing.getAppointmentId(),
                        billing.getAmount(),
                        billing.getStatus(),
                        billing.getBillingDate()))
                .collect(Collectors.toList());
    }

    @Override
    public BillingDto getBillingRecordById(Long billingId) {
        Billing billing = billingRepository.findById(billingId)
                .orElseThrow(() -> new RuntimeException("Billing record not found"));
        return new BillingDto(
                billing.getId(),
                billing.getAppointmentId(),
                billing.getAmount(),
                billing.getStatus(),
                billing.getBillingDate());
    }

    @Override
    public Page<BillingDto> getAllBillingRecords(int page, int size, String sortBy, String sortDir) {
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(sortDir.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy));
        Page<Billing> billingPage = billingRepository.findAll(pageable);
        return billingPage.map(billing -> new BillingDto(
                billing.getId(),
                billing.getAppointmentId(),
                billing.getAmount(),
                billing.getStatus(),
                billing.getBillingDate()));
    }
}