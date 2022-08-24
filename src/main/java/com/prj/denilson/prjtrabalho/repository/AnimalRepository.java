package com.prj.denilson.prjtrabalho.repository;

import com.prj.denilson.prjtrabalho.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    @Query(value = "SELECT a FROM Animal a WHERE id_user = ?1")
    List<Animal> findByAnimalsByUser(Long user);
}
