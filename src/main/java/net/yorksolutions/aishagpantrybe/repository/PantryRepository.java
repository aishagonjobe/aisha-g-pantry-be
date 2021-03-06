package net.yorksolutions.aishagpantrybe.repository;

import net.yorksolutions.aishagpantrybe.model.Pantry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PantryRepository extends CrudRepository<Pantry, Long> {
}
