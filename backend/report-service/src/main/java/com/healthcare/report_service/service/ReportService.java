package com.healthcare.report_service.service;

import java.io.ByteArrayInputStream;

public interface ReportService {

    
    ByteArrayInputStream generatePatientReportPdf();
    ByteArrayInputStream generatePatientReportExcel();
    ByteArrayInputStream generatePatientReportCsv();
    ByteArrayInputStream generatePatientReportJson();
    
    ByteArrayInputStream generateAppointmentReportPdf();
    ByteArrayInputStream generateAppointmentReportExcel();
    ByteArrayInputStream generateAppointmentReportCsv();
    ByteArrayInputStream generateAppointmentReportJson();






}