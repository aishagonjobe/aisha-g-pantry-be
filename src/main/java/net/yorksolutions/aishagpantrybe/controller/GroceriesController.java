package net.yorksolutions.aishagpantrybe.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.yorksolutions.aishagpantrybe.model.Groceries;
import net.yorksolutions.aishagpantrybe.repository.GroceriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/pantry")
@RestController
public class GroceriesController {
    @Autowired
    GroceriesRepository groceriesRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/all")
    String getAllGroceries() throws JsonProcessingException {
        return objectMapper.writeValueAsString(groceriesRepository.findAll());
    }

    @GetMapping("/getByRowAmount")
    String getGroceriesByRows(@RequestParam("rows") String rows) throws JsonProcessingException {
        List<Groceries> groceriesList = (List<Groceries>) groceriesRepository.findAll();
        groceriesList = groceriesList.stream().limit(Long.parseLong(rows)).collect(Collectors.toList());
        return objectMapper.writeValueAsString(groceriesList);
    }

    @PostMapping("/add")
    String postGroceries(@RequestBody String body) {
        try {
            Groceries groceries = objectMapper.readValue(body, Groceries.class);
            groceriesRepository.save(groceries);
        } catch (JsonProcessingException e) {
            return e.getMessage();
        }
        return "success";
    }
}
