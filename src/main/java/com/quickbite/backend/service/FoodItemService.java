package com.quickbite.backend.service;

import com.quickbite.backend.model.FoodItem;
import com.quickbite.backend.repository.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodItemService {

    private FoodItemRepository foodItemRepository;

    @Autowired
    public FoodItemService(FoodItemRepository foodItemRepository) {
        this.foodItemRepository = foodItemRepository;
    }

    public FoodItem addFoodItem(FoodItem foodItem) {
        return foodItemRepository.save(foodItem);
    }

    public List<FoodItem> getAllFoodItems() {
        return foodItemRepository.findAll();
    }

    public FoodItem getFoodItemById(Long id) {
        return foodItemRepository.findById(id).orElse(null);
    }

    public void deleteFoodItem(Long id) {
        foodItemRepository.deleteById(id);
    }

    public FoodItem updateFoodItem(Long id, FoodItem updatedFoodItem) {

        // Step 1 — find existing food item from DB
        FoodItem existingFoodItem = foodItemRepository.findById(id).orElse(null);

        // Step 2 — update its fields with new values
        existingFoodItem.setName(updatedFoodItem.getName());
        existingFoodItem.setDescription(updatedFoodItem.getDescription());
        existingFoodItem.setPrice(updatedFoodItem.getPrice());
        existingFoodItem.setCategory(updatedFoodItem.getCategory());
        existingFoodItem.setAvailable(updatedFoodItem.getAvailable());

        // Step 3 — save updated object back to DB
        return foodItemRepository.save(existingFoodItem);   
    }
}