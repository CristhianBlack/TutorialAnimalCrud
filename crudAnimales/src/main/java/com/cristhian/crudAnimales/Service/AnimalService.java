package com.cristhian.crudAnimales.Service;

import com.cristhian.crudAnimales.Entity.Animal;
import com.cristhian.crudAnimales.Repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AnimalService {
    @Autowired
    AnimalRepository animalRepository;

    public List<Animal> ListaAnimales(){
        return animalRepository.findAll();
    }

    public Optional<Animal> BuscarId(int id){
        return animalRepository.findById(id);
    }

    public void Agregar(Animal animales){
        animalRepository.save(animales);
    }

    public void Eliminar(int id){
        animalRepository.deleteById(id);
    }

    public boolean ExisteId(int id){
        return animalRepository.existsById(id);
    }
}
