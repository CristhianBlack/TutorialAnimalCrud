package com.cristhian.crudAnimales.Repository;

import com.cristhian.crudAnimales.Entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {
}
