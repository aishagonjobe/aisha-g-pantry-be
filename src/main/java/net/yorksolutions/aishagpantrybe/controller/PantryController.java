package net.yorksolutions.aishagpantrybe.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import net.yorksolutions.aishagpantrybe.model.Pantry;
import net.yorksolutions.aishagpantrybe.repository.PantryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/pantry")
public class PantryController {

    @Autowired
    PantryRepository pantryRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/get-foods")
    public ResponseEntity<List<Pantry>> requestFood() {
        try {
            List<Pantry> pantryList = new ArrayList<Pantry>();
            pantryRepository.findAll().forEach(pantryList::add);
            if (pantryList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(pantryList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pantry> requestFoodItem(@PathVariable("id") Long id) {
        Optional<Pantry> pantryData = pantryRepository.findById(id);

        if (pantryData.isPresent()) {
            return new ResponseEntity<>(pantryData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create-food")
    public ResponseEntity<Pantry> createFood(@RequestBody String pantry) {
        try {
            Pantry food = objectMapper.readValue(pantry, Pantry.class);
            pantryRepository.save(food);
            return new ResponseEntity<>(food, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @PutMapping("/update-food")
    public ResponseEntity<?> updateFood(@RequestParam("id") Long id, @RequestBody Pantry pantry) {
        Optional<Pantry> pantryData = pantryRepository.findById(id);
        if (pantryData.isPresent()) {
            Pantry food = pantryData.get();
            food.setFoodName(pantry.getFoodName());
            food.setFoodImage(pantry.getFoodImage());
            food.setWeight(pantry.getWeight());
            food.setCalories(pantry.getCalories());
            food.setFoodCategory(pantry.getFoodCategory());
            return new ResponseEntity<Pantry>(pantryRepository.save(food), HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Not found.", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete-food")
    public ResponseEntity<?> deleteFood(@RequestParam("id") Long id) {
        try {
            pantryRepository.deleteById(id);
            return new ResponseEntity<String>("Deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Internal error.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}



