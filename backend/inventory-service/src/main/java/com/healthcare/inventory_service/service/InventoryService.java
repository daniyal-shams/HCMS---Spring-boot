package com.healthcare.inventory_service.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.healthcare.inventory_service.dto.InventoryDto;

@Service
public interface InventoryService {

    InventoryDto addInventory(InventoryDto inventoryDto);

    InventoryDto getInventoryById(Long id);

    List<InventoryDto> getAllInventories();

    InventoryDto updateInventory(Long id, InventoryDto inventoryDto);

    void deleteInventory(Long id);

    Page<InventoryDto> getInventoriesWithPagination(int page, int size);
    List<InventoryDto> getInventoriesWithSorting(String sortBy);
    List<InventoryDto> getInventoriesWithPaginationAndSorting(int page, int size, String sortBy, String direction, String filter);
}
