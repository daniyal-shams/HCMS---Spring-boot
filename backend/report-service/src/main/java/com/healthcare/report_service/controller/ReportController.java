package com.healthcare.report_service.controller;

import java.io.ByteArrayInputStream;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.report_service.service.ReportService;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/patients/pdf")
    public ResponseEntity<byte[]> generatePatientReportPdf() {
        ByteArrayInputStream report = reportService.generatePatientReportPdf();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=patient_report.pdf");
        return ResponseEntity.ok().headers(headers).body(report.readAllBytes());
    }
    @GetMapping("/patients/excel")
    public ResponseEntity<byte[]> generatePatientReportExcel() {
        ByteArrayInputStream report = reportService.generatePatientReportExcel();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=patient_report.xlsx");
        return ResponseEntity.ok().headers(headers).body(report.readAllBytes());
    }
    @GetMapping("/patients/csv")
    public ResponseEntity<byte[]> generatePatientReportCsv() {
        ByteArrayInputStream report = reportService.generatePatientReportCsv();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=patient_report.csv");
        return ResponseEntity.ok().headers(headers).body(report.readAllBytes());
    }
    @GetMapping("/appointments/json")
    public ResponseEntity<byte[]> generatePatientReportJson() {
        ByteArrayInputStream report = reportService.generateAppointmentReportJson();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=appointment_report.json");
        return ResponseEntity.ok().headers(headers).body(report.readAllBytes());
    }

    @GetMapping("/appointments/pdf")
    public ResponseEntity<byte[]> generateAppointmentReportPdf() {
        ByteArrayInputStream report = reportService.generateAppointmentReportPdf();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=appointment_report.pdf");
        return ResponseEntity.ok().headers(headers).body(report.readAllBytes());
    }
    @GetMapping("/appointments/excel")
    public ResponseEntity<byte[]> generateAppointmentReportExcel() {
        ByteArrayInputStream report = reportService.generateAppointmentReportExcel();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=appointment_report.xlsx");
        return ResponseEntity.ok().headers(headers).body(report.readAllBytes());
    }
    @GetMapping("/appointments/csv")
    public ResponseEntity<byte[]> generateAppointmentReportCsv() {
        ByteArrayInputStream report = reportService.generateAppointmentReportCsv();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=appointment_report.csv");
        return ResponseEntity.ok().headers(headers).body(report.readAllBytes());
    }
    @GetMapping("/appointments/json")
    public ResponseEntity<byte[]> generateAppointmentReportJson() {
        ByteArrayInputStream report = reportService.generateAppointmentReportJson();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=appointment_report.json");
        return ResponseEntity.ok().headers(headers).body(report.readAllBytes());
    }
}