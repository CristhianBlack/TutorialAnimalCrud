package com.cristhian.crudAnimales.Controller;

import com.cristhian.crudAnimales.DTO.AnimalDTO;
import com.cristhian.crudAnimales.DTO.Mensaje;
import com.cristhian.crudAnimales.Entity.Animal;
import com.cristhian.crudAnimales.Service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animal")
@CrossOrigin(origins = "http://localhost:4200")
public class AnimalController {

    @Autowired
    AnimalService animalService;

   @GetMapping("/listar")
    public ResponseEntity<List<Animal>> ListadoAnimales(){
        List mascotas = animalService.ListaAnimales();
        return new ResponseEntity(mascotas, HttpStatus.OK);
    }

    @GetMapping("/detalle/{id}")
    public ResponseEntity<Animal> DetalleAnimal(@PathVariable("id") int id){
       if(!animalService.ExisteId(id)){
           return new ResponseEntity(new Mensaje("No existe registro"), HttpStatus.NOT_FOUND);
       }else{
           Animal animal = animalService.BuscarId(id).get();
           return new ResponseEntity(animal, HttpStatus.OK);
       }
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> Almacenar(@RequestBody AnimalDTO animalDTO){
       Animal animalito = new Animal(animalDTO.getNombre(),animalDTO.getRaza(),
               animalDTO.getColor(),animalDTO.getEdad());

       animalService.Agregar(animalito);
       return new ResponseEntity<>(new Mensaje("Agregado con exito"), HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> Actualizar(@PathVariable("id") int id, @RequestBody AnimalDTO animalDTO){
       Animal mascota = animalService.BuscarId(id).get();
       mascota.setNombre(animalDTO.getNombre());
       mascota.setRaza(animalDTO.getRaza());
       mascota.setColor(animalDTO.getColor());
       mascota.setEdad(animalDTO.getEdad());

       animalService.Agregar(mascota);
       return  new ResponseEntity<>(new Mensaje("Actualizado con exito"), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public  ResponseEntity<?> Eliminar(@PathVariable("id") int id){
       if(!animalService.ExisteId(id)){
           return new ResponseEntity<>(new Mensaje("No exite registro"), HttpStatus.NOT_FOUND);
       }else{
           animalService.Eliminar(id);
           return new ResponseEntity<>(new Mensaje("Registro eliminado"),HttpStatus.OK);
       }
    }
}
