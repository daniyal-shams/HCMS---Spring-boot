package com.healthcare.nurse_service.controller;

import com.healthcare.nurse_service.dto.NurseDto;
import com.healthcare.nurse_service.service.NurseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nurses")
@CrossOrigin(origins="http://localhost:8080")
public class NurseController {

    @Autowired
    private NurseService nurseService;

    @GetMapping("/{id}")
    public ResponseEntity<NurseDto> getNurseById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(nurseService.getNurseById(id));
    }

    @GetMapping
    public ResponseEntity<List<NurseDto>> getAllNurses() {
        return ResponseEntity.ok(nurseService.getAllNurses());
    }

    @PostMapping
    public ResponseEntity<NurseDto> createNurse(@RequestBody NurseDto nurseDto) {
        return ResponseEntity.ok(nurseService.createNurse(nurseDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NurseDto> updateNurse(@PathVariable("id") Long id, @RequestBody NurseDto nurseDto) {
        return ResponseEntity.ok(nurseService.updateNurse(id, nurseDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNurse(@PathVariable("id") Long id) {
        nurseService.deleteNurse(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/page")
    public ResponseEntity<List<NurseDto>> getNursesByPage(
            @RequestParam int page,
            @RequestParam int size) {
        return ResponseEntity.ok(nurseService.getNursesByPage(page, size));
    }

    @GetMapping("/page-sort")
    public ResponseEntity<List<NurseDto>> getNursesByPageAndSort(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy) {
        return ResponseEntity.ok(nurseService.getNursesByPageAndSort(page, size, sortBy));
    }
}