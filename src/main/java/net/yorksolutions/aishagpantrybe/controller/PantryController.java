package net.yorksolutions.aishagpantrybe.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.yorksolutions.aishagpantrybe.model.Pantry;
import net.yorksolutions.aishagpantrybe.repository.PantryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/pantry")
@RestController
public class PantryController {
    @Autowired
    PantryRepository pantryRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/get-foods")
    String requestFood() throws JsonProcessingException {
        return objectMapper.writeValueAsString(pantryRepository.findAll());
    }

    @PostMapping("/create-food")
    String createFood(@RequestBody String body) {
        try {
            Pantry pantry = objectMapper.readValue(body, Pantry.class);
            pantryRepository.save(pantry);
        } catch (JsonProcessingException e) {
            return e.getMessage();
        }
        return "success";
    }

    @GetMapping("/get-food-item/id")
    String requestFoodItem(@RequestParam("rows") String rows) throws JsonProcessingException {
        List<Pantry> pantryList = (List<Pantry>) pantryRepository.findAll();
        pantryList = pantryList.stream().limit(Long.parseLong(rows)).collect(Collectors.toList());
        return objectMapper.writeValueAsString(pantryList);
    }

    @PutMapping("/update-food/id")
    String updateFood(@RequestParam("rows") String rows) throws JsonProcessingException {
        List<Pantry> pantryList = (List<Pantry>) pantryRepository.findAll();
        pantryList = pantryList.stream().limit(Long.parseLong(rows)).collect(Collectors.toList());
        return objectMapper.writeValueAsString(pantryList);
    }

    @DeleteMapping("/delete-food/id")
    String deleteFood(@RequestParam("rows") String rows) throws JsonProcessingException {
        List<Pantry> pantryList = (List<Pantry>) pantryRepository.findAll();
        pantryList = pantryList.stream().limit(Long.parseLong(rows)).collect(Collectors.toList());
        return objectMapper.writeValueAsString(pantryList);
    }

    @GetMapping("/food-category")
    String requestFoodCategory() throws JsonProcessingException {
        return objectMapper.writeValueAsString(pantryRepository.findAll());
    }
}
