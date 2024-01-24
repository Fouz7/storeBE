package com.store.store.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.store.store.DTO.ItemsDTO;
import com.store.store.models.Items;
import com.store.store.repositories.ItemsRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ItemsService {
    private final ItemsRepository itemsRepository;

    public ItemsDTO createItem(ItemsDTO itemsDto) {
        Items item = convertToEntity(itemsDto);
        if(item.getStock() == 0){
            item.setIs_available(false);
        }

        return convertToDTO(itemsRepository.save(item));
    }

    public List<Items> getAllItems() {
        return itemsRepository.findAll();
    }

    public Optional<Items> getItemById(Long id) {
        return itemsRepository.findById(id);
    }

    public ItemsDTO updateItems(Long id, ItemsDTO itemsDto){
        return itemsRepository.findById(id)
            .map(i -> {
                i.setIs_available(itemsDto.getIs_available());
                i.setItem_name(itemsDto.getItem_name());
                i.setItem_code(itemsDto.getItem_code());
                i.setLast_re_stock(itemsDto.getLast_re_stock());
                i.setPrice(itemsDto.getPrice());
                i.setStock(itemsDto.getStock());

                if(i.getStock() == 0){
                    i.setIs_available(false);
                }
                return convertToDTO(itemsRepository.save(i));
            })
            .orElseThrow(() -> new RuntimeException("Item not found with id " + id));
    }

    public void deleteItem(Long id) {
        itemsRepository.deleteById(id);
    }

    private ItemsDTO convertToDTO(Items items) {
        ItemsDTO itemDTO = new ItemsDTO();
        itemDTO.setIs_available(items.getIs_available());
        itemDTO.setItem_name(items.getItem_name());
        itemDTO.setItem_code(items.getItem_code());
        itemDTO.setLast_re_stock(items.getLast_re_stock());
        itemDTO.setPrice(items.getPrice());
        itemDTO.setStock(items.getStock());

        return itemDTO;
    }

    private Items convertToEntity(ItemsDTO itemsDTO) {
        Items item = new Items();
        item.setIs_available(itemsDTO.getIs_available());
        item.setItem_name(itemsDTO.getItem_name());
        item.setItem_code(itemsDTO.getItem_code());
        item.setLast_re_stock(itemsDTO.getLast_re_stock());
        item.setPrice(itemsDTO.getPrice());
        item.setStock(itemsDTO.getStock());
        
        return item;
    }
}
