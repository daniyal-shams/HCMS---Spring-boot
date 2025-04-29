package com.healthcare.inventory_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.healthcare.inventory_service.dto.InventoryDto;
import com.healthcare.inventory_service.model.Inventory;
import com.healthcare.inventory_service.repository.InventoryRepository;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public InventoryDto addInventory(InventoryDto inventoryDto) {
        Inventory inventory = mapToEntity(inventoryDto);
        Inventory savedInventory = inventoryRepository.save(inventory);
        return mapToDto(savedInventory);
    }

    @Override
    public InventoryDto getInventoryById(Long id) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory not found with ID: " + id));
        return mapToDto(inventory);
    }

    @Override
    public List<InventoryDto> getAllInventories() {
        List<Inventory> inventories = inventoryRepository.findAll();
        return inventories.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public InventoryDto updateInventory(Long id, InventoryDto inventoryDto) {
        Inventory existingInventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory not found with ID: " + id));
        existingInventory.setName(inventoryDto.getName());
        existingInventory.setQuantity(inventoryDto.getQuantity());
        existingInventory.setLocation(inventoryDto.getLocation());
        Inventory updatedInventory = inventoryRepository.save(existingInventory);
        return mapToDto(updatedInventory);
    }

    @Override
    public void deleteInventory(Long id) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory not found with ID: " + id));
        inventoryRepository.delete(inventory);
    }

    @Override
    public Page<InventoryDto> getInventoriesWithPagination(int page, int size) {
        Page<Inventory> inventoryPage = inventoryRepository.findAll(PageRequest.of(page, size));
        return inventoryPage.map(this::mapToDto);
    }

    @Override
    public List<InventoryDto> getInventoriesWithSorting(String sortBy) {
        List<Inventory> inventories = inventoryRepository.findAll(Sort.by(sortBy));
        return inventories.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public List<InventoryDto> getInventoriesWithPaginationAndSorting(int page, int size, String sortBy, String direction, String filter) {
        Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Page<Inventory> inventoryPage = inventoryRepository.findAll(PageRequest.of(page, size, sort));
        return inventoryPage.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    private InventoryDto mapToDto(Inventory inventory) {
        InventoryDto dto = new InventoryDto();
        dto.setId(inventory.getId());
        dto.setName(inventory.getName());
        dto.setQuantity(inventory.getQuantity());
        dto.setLocation(inventory.getLocation());
        return dto;
    }

    private Inventory mapToEntity(InventoryDto dto) {
        Inventory inventory = new Inventory();
        inventory.setId(dto.getId());
        inventory.setName(dto.getName());
        inventory.setQuantity(dto.getQuantity());
        inventory.setLocation(dto.getLocation());
        return inventory;
    }
}