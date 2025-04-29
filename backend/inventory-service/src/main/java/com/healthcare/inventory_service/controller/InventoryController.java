package com.healthcare.inventory_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.healthcare.inventory_service.dto.InventoryDto;
import com.healthcare.inventory_service.service.InventoryService;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping
    public ResponseEntity<InventoryDto> addInventory( /* @Valid */ @RequestBody InventoryDto inventoryDto) {
        InventoryDto createdInventory = inventoryService.addInventory(inventoryDto);
        return new ResponseEntity<>(createdInventory, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryDto> getInventoryById(@PathVariable Long id) {
        InventoryDto inventoryDto = inventoryService.getInventoryById(id);
        return new ResponseEntity<>(inventoryDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<InventoryDto>> getAllInventories() {
        List<InventoryDto> inventories = inventoryService.getAllInventories();
        return new ResponseEntity<>(inventories, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryDto> updateInventory(@PathVariable Long id, /* @Valid */ @RequestBody InventoryDto inventoryDto) {
        InventoryDto updatedInventory = inventoryService.updateInventory(id, inventoryDto);
        return new ResponseEntity<>(updatedInventory, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventory(@PathVariable Long id) {
        inventoryService.deleteInventory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/page")
    public ResponseEntity<Page<InventoryDto>> getInventoriesWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<InventoryDto> inventoryPage = inventoryService.getInventoriesWithPagination(page, size);
        return new ResponseEntity<>(inventoryPage, HttpStatus.OK);
    }
    @GetMapping("/sort")
    public ResponseEntity<List<InventoryDto>> getInventoriesWithSorting(@RequestParam String sortBy) {
        List<InventoryDto> sortedInventories = inventoryService.getInventoriesWithSorting(sortBy);
        return new ResponseEntity<>(sortedInventories, HttpStatus.OK);
    }
    @GetMapping("/page-sort")
    public ResponseEntity<List<InventoryDto>> getInventoriesWithPaginationAndSorting(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam String sortBy,
            @RequestParam String direction,
            @RequestParam String filter) {
        List<InventoryDto> filteredSortedInventories = inventoryService.getInventoriesWithPaginationAndSorting(page, size, sortBy, direction, filter);
        return new ResponseEntity<>(filteredSortedInventories, HttpStatus.OK);
    }
}
