package net.yorksolutions.aishagpantrybe.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import net.yorksolutions.aishagpantrybe.model.Pantry;
import net.yorksolutions.aishagpantrybe.repository.PantryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/pantry")
public class PantryController {

    @Autowired
    PantryRepository pantryRepository;

    @GetMapping("/get-foods")
    public ResponseEntity<List<Pantry>> requestFood(String foodCategory) {
        try {
            List<Pantry> pantryList = new ArrayList<Pantry>();
            pantryRepository.findAll().forEach(pantryList::add);
            if (foodCategory == null)
                pantryRepository.findAll().forEach(pantryList::add);
            else
                pantryList.addAll(pantryRepository.findByCategory(foodCategory));
            if (pantryList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(pantryList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/favorites")
    public ResponseEntity<List<Pantry>> findByFavorite() {
        try {
            List<Pantry> food = pantryRepository.findByFavorite(true);

            if (food.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(food, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/available")
    public ResponseEntity<List<Pantry>> findByAvailable() {
        try {
            List<Pantry> food = pantryRepository.findByAvailable(true);

            if (food.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(food, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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
    public ResponseEntity<Pantry> createFood(@RequestBody Pantry pantry) {
        try {
            Pantry food = pantryRepository
                    .save(new Pantry(
                            pantry.getFoodName(),
                            pantry.getFoodImage(),
                            pantry.getWeight(),
                            pantry.getCalories(),
                            true,
                            false,
                            pantry.getFoodCategory()
                            ));
            return new ResponseEntity<>(food, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pantry> updateFood(@PathVariable("id") Long id, @RequestBody Pantry pantry) {
        Optional<Pantry> pantryData = pantryRepository.findById(id);

        if (pantryData.isPresent()) {
            Pantry food = pantryData.get();
            pantry.setFoodName(food.getFoodName());
            pantry.setFoodImage(pantry.getFoodImage());
            pantry.setWeight(pantry.getWeight());
            pantry.setCalories(pantry.getCalories());
            pantry.setAvailable(pantry.getAvailable());
            pantry.setFavorite(pantry.getFavorite());
            pantry.setFoodCategory(pantry.getFoodCategory());
            return new ResponseEntity<>(pantryRepository.save(food), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteFood(@PathVariable("id") Long id) {
        try {
            pantryRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}



