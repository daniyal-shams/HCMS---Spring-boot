package com.healthcare.billing_service.controller ;

import com.healthcare.billing_service.dto.BillingDto;
import com.healthcare.billing_service.service.BillingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/billing")
public class BillingController {

    private final BillingService billingService;

    public BillingController(BillingService billingService) {
        this.billingService = billingService;
    }

    @PostMapping
    public ResponseEntity<String> createBillingRecord(@RequestBody BillingDto billingDto) {
        try {
            billingService.createBillingRecord(billingDto);
            return ResponseEntity.ok("Billing record created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create billing record: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBillingRecord(@PathVariable Long id, @RequestBody BillingDto billingDto) {
        try {
            billingService.updateBillingRecord(id, billingDto);
            return ResponseEntity.ok("Billing record updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update billing record: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBillingRecord(@PathVariable Long id) {
        try {
            billingService.deleteBillingRecord(id);
            return ResponseEntity.ok("Billing record deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete billing record: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<BillingDto>> getAllBillingRecords() {
        try {
            List<BillingDto> billingRecords = billingService.getAllBillingRecords();
            return ResponseEntity.ok(billingRecords);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<BillingDto> getBillingRecordById(@PathVariable Long id) {
        try {
            BillingDto billingRecord = billingService.getBillingRecordById(id);
            return ResponseEntity.ok(billingRecord);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/page")
    public ResponseEntity<List<BillingDto>> getAllBillingRecords(@RequestParam int page, @RequestParam int size, @RequestParam String sortBy, @RequestParam String sortDir) {
        try {
            List<BillingDto> billingRecords = billingService.getAllBillingRecords(page, size, sortBy, sortDir);
            return ResponseEntity.ok(billingRecords);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("/search")
    public ResponseEntity<List<BillingDto>> searchBillingRecords(@RequestParam String query) {
        try {
            List<BillingDto> billingRecords = billingService.searchBillingRecords(query);
            return ResponseEntity.ok(billingRecords);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
