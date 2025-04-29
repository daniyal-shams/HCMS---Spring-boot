package com.healthcare.report_service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.data.domain.Page;

import com.healthcare.report_service.dto.PatientDto;
import com.healthcare.report_service.dto.AppointmentDto;
import com.healthcare.report_service.client.PatientServiceClient;
import com.healthcare.report_service.client.AppointmentServiceClient;

import com.mysql.cj.result.Row; 

@Service
public class ReportServiceImpl implements ReportService {

    // private static final Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);

    private final PatientServiceClient patientServiceClient;

    private final AppointmentServiceClient appointmentServiceClient;

    public ReportServiceImpl(PatientServiceClient patientServiceClient, AppointmentServiceClient appointmentServiceClient) {
        this.patientServiceClient = patientServiceClient;
        this.appointmentServiceClient = appointmentServiceClient;
    }

    @Override
    public ByteArrayInputStream generatePatientReportPdf() {
        // logger.info("Starting to generate patient report in PDF format with pagination.");
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(out);
            com.itextpdf.kernel.pdf.PdfDocument pdfDocument = new com.itextpdf.kernel.pdf.PdfDocument(writer);
            Document document = new Document(pdfDocument);
    
            document.add(new Paragraph("Patient Report").setFontSize(16));
    
            Table table = new Table(7);
            table.addCell(new Cell().add(new Paragraph("ID")));
            table.addCell(new Cell().add(new Paragraph("First Name")));
            table.addCell(new Cell().add(new Paragraph("Last Name")));
            table.addCell(new Cell().add(new Paragraph("Email")));
            table.addCell(new Cell().add(new Paragraph("Phone Number")));
            table.addCell(new Cell().add(new Paragraph("Address")));
            table.addCell(new Cell().add(new Paragraph("Date of Birth")));
    
            int page = 0;
            int size = 10;
    
            while (true) {
                // logger.info("Fetching page {} with size {} from patient-service.", page, size);
                Page patientPage = patientServiceClient.getAllPatients(page, size);
    
                List<PatientDto> patients = patientPage.getContent();
                if (patients.isEmpty()) {
                    break;
                }
    
                for (PatientDto patient : patients) {
                    table.addCell(new Cell().add(new Paragraph(String.valueOf(patient.getId()))));
                    table.addCell(new Cell().add(new Paragraph(patient.getFirstName())));
                    table.addCell(new Cell().add(new Paragraph(patient.getLastName())));
                    table.addCell(new Cell().add(new Paragraph(patient.getEmail())));
                    table.addCell(new Cell().add(new Paragraph(patient.getPhoneNumber())));
                    table.addCell(new Cell().add(new Paragraph(patient.getAddress())));
                    table.addCell(new Cell().add(new Paragraph(patient.getDateOfBirth())));
                }
    
                if (patientPage.isLast()) {
                    break;
                }
                page++;
            }
    
            document.add(table);
            document.close();
    
            // logger.info("Successfully generated patient report in PDF format.");
            return new ByteArrayInputStream(out.toByteArray());
        } catch (Exception e) {
            // logger.error("Failed to generate patient report PDF.", e);
            throw new RuntimeException("Failed to generate patient report PDF", e);
        }
    }

    @Override
    public ByteArrayInputStream generatePatientReportExcel() {
        // logger.info("Starting to generate patient report in Excel format with pagination.");
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Patients");
    
            // Header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("First Name");
            headerRow.createCell(2).setCellValue("Last Name");
            headerRow.createCell(3).setCellValue("Email");
            headerRow.createCell(4).setCellValue("Phone Number");
            headerRow.createCell(5).setCellValue("Address");
            headerRow.createCell(6).setCellValue("Date of Birth");
    
            int rowIndex = 1;
            int page = 0;
            int size = 10;
    
            while (true) {
                // logger.info("Fetching page {} with size {} from patient-service.", page, size);
                Page<PatientDto> patientPage = patientServiceClient.getAllPatients(page, size);
    
                List<PatientDto> patients = patientPage.getContent();
                if (patients.isEmpty()) {
                    break;
                }
    
                for (PatientDto patient : patients) {
                    Row dataRow = sheet.createRow(rowIndex++);
                    dataRow.createCell(0).setCellValue(patient.getId());
                    dataRow.createCell(1).setCellValue(patient.getFirstName());
                    dataRow.createCell(2).setCellValue(patient.getLastName());
                    dataRow.createCell(3).setCellValue(patient.getEmail());
                    dataRow.createCell(4).setCellValue(patient.getPhoneNumber());
                    dataRow.createCell(5).setCellValue(patient.getAddress());
                    dataRow.createCell(6).setCellValue(patient.getDateOfBirth());
                }
    
                if (patientPage.isLast()) {
                    break;
                }
                page++;
            }
    
            workbook.write(out);
            // logger.info("Successfully generated patient report in Excel format.");
            return new ByteArrayInputStream(out.toByteArray());
        } catch (Exception e) {
            // logger.error("Failed to generate patient report Excel.", e);
            throw new RuntimeException("Failed to generate patient report Excel", e);
        }
    }

    @Override
    public ByteArrayInputStream generatePatientReportCsv() {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream(); PrintWriter writer = new PrintWriter(out)) {
            // Header row
            writer.println("ID,First Name,Last Name,Email,Phone Number,Address,Date of Birth");
    
            int page = 0;
            int size = 10;
    
            while (true) {
                Page<PatientDto> patientPage = patientServiceClient.getAllPatients(page, size);
    
                List<PatientDto> patients = patientPage.getContent();
                if (patients.isEmpty()) {
                    break;
                }
    
                // Add data rows
                for (PatientDto patient : patients) {
                    writer.printf("%d,%s,%s,%s,%s,%s,%s%n",
                            patient.getId(),
                            patient.getFirstName(),
                            patient.getLastName(),
                            patient.getEmail(),
                            patient.getPhoneNumber(),
                            patient.getAddress(),
                            patient.getDateOfBirth());
                }
    
                if (patientPage.isLast()) {
                    break;
                }
                page++;
            }
    
            writer.flush();
            // logger.info("Successfully generated patient report in CSV format.");
            return new ByteArrayInputStream(out.toByteArray());
        } catch (Exception e) {
            // logger.error("Failed to generate patient report CSV.", e);
            throw new RuntimeException("Failed to generate patient report CSV", e);
        }
    }

    @Override
    public ByteArrayInputStream generatePatientReportJson() {
        // logger.info("Fetching patient data from patient-service...");
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            ObjectMapper objectMapper = new ObjectMapper();

            List<PatientDto> patients = patientServiceClient.getAllPatients();
            // logger.info("Successfully fetched {} patients from patient-service.", patients.size());

            objectMapper.writeValue(out, patients);
            // logger.info("Successfully generated patient report in JSON format.");

            return new ByteArrayInputStream(out.toByteArray());
        } catch (Exception e) {
            // logger.error("Failed to generate patient report JSON.", e);
            throw new RuntimeException("Failed to generate patient report JSON", e);
        }
    }

    @Override
    public ByteArrayInputStream generateAppointmentReportPdf() {
    // logger.info("Starting to generate appointment report in PDF format with pagination.");
    try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);

        document.add(new Paragraph("Appointment Report").setFontSize(16));

        Table table = new Table(6);
        table.addCell(new Cell().add("ID").setBold());
        table.addCell(new Cell().add("Patient Name").setBold());
        table.addCell(new Cell().add("Doctor Name").setBold());
        table.addCell(new Cell().add("Appointment Date").setBold());
        table.addCell(new Cell().add("Appointment Time").setBold());
        table.addCell(new Cell().add("Status").setBold());

        int page = 0;
        int size = 10;

        while (true) {
            Page<AppointmentDto> appointmentPage = appointmentServiceClient.getAllAppointments(page, size);

            List<AppointmentDto> appointments = appointmentPage.getContent();
            if (appointments.isEmpty()) {
                break;
            }

            // Add data rows
            for (AppointmentDto appointment : appointments) {
                table.addCell(new Cell().add(new Paragraph(String.valueOf(appointment.getId()))));
                table.addCell(new Cell().add(appointment.getPatientName()));
                table.addCell(new Cell().add(appointment.getDoctorName()));
                table.addCell(new Cell().add(appointment.getAppointmentDate()));
                table.addCell(new Cell().add(appointment.getAppointmentTime()));
                table.addCell(new Cell().add(appointment.getStatus()));
            }

            if (appointmentPage.isLast()) {
                break;
            }
            page++;
        }

        document.add(table);
        document.close();

        return new ByteArrayInputStream(out.toByteArray());
    } catch (Exception e) {
        throw new RuntimeException("Failed to generate appointment report PDF", e);
    }
}

    @Override
    public ByteArrayInputStream generateAppointmentReportExcel() {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Appointments");
    
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Patient Name");
            headerRow.createCell(2).setCellValue("Doctor Name");
            headerRow.createCell(3).setCellValue("Appointment Date");
            headerRow.createCell(4).setCellValue("Appointment Time");
            headerRow.createCell(5).setCellValue("Status");
    
            int rowIndex = 1;
            int page = 0;
            int size = 10;
    
            while (true) {
                Page<AppointmentDto> appointmentPage = appointmentServiceClient.getAllAppointments(page, size);
    
                List<AppointmentDto> appointments = appointmentPage.getContent();
                if (appointments.isEmpty()) {
                    break;
                }
    
                for (AppointmentDto appointment : appointments) {
                    Row dataRow = sheet.createRow(rowIndex++);
                    dataRow.createCell(0).setCellValue(appointment.getId());
                    dataRow.createCell(1).setCellValue(appointment.getPatientName());
                    dataRow.createCell(2).setCellValue(appointment.getDoctorName());
                    dataRow.createCell(3).setCellValue(appointment.getAppointmentDate());
                    dataRow.createCell(4).setCellValue(appointment.getAppointmentTime());
                    dataRow.createCell(5).setCellValue(appointment.getStatus());
                }
    
                if (appointmentPage.isLast()) {
                    break;
                }
                page++;
            }
    
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate appointment report Excel", e);
        }
    }

    @Override
    public ByteArrayInputStream generateAppointmentReportCsv() {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream(); PrintWriter writer = new PrintWriter(out)) {
            writer.println("ID,Patient Name,Doctor Name,Appointment Date,Appointment Time,Status");
    
            int page = 0;
            int size = 10;
    
            while (true) {
                Page<AppointmentDto> appointmentPage = appointmentServiceClient.getAllAppointments(page, size);
    
                List<AppointmentDto> appointments = appointmentPage.getContent();
                if (appointments.isEmpty()) {
                    break;
                }
    
                for (AppointmentDto appointment : appointments) {
                    writer.printf("%d,%s,%s,%s,%s,%s%n",
                            appointment.getId(),
                            appointment.getPatientName(),
                            appointment.getDoctorName(),
                            appointment.getAppointmentDate(),
                            appointment.getAppointmentTime(),
                            appointment.getStatus());
                }
    
                if (appointmentPage.isLast()) {
                    break;
                }
                page++;
            }
    
            writer.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate appointment report CSV", e);
        }
    }


    @Override
    public ByteArrayInputStream generateAppointmentReportJson() {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            ObjectMapper objectMapper = new ObjectMapper();

            List<PatientDto> appointments = appointmentServiceClient.getAllAppointments();

            objectMapper.writeValue(out, appointments);

            return new ByteArrayInputStream(out.toByteArray());
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate patient report JSON", e);
        }
    }
}
