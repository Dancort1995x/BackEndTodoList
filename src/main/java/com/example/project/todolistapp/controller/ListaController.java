package com.example.project.todolistapp.controller;


import com.example.project.todolistapp.exception.ResourceNotFoundException;
import com.example.project.todolistapp.model.Lista;
import com.example.project.todolistapp.repository.ListaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ListaController {

    @Autowired
    private ListaRepository listaRepository;

    @GetMapping("/listas")
    public List<Lista> getAllListas(){
        return listaRepository.findAll();
    }

    @GetMapping("/listas/{id}")
    public ResponseEntity<Lista> getListaById(@PathVariable(value = "id") Long listaId)
            throws ResourceNotFoundException
    {
        Lista lista = listaRepository.findById(listaId)
                .orElseThrow(() -> new ResourceNotFoundException("lista no se encuentra con el id: " + listaId));
        return ResponseEntity.ok().body(lista);
    }

    @PostMapping("/listas")
    public Lista createLista(@Valid @RequestBody Lista lista){
        return listaRepository.save(lista);
    }

    @PutMapping("/listas/{id}")
    public ResponseEntity<Lista> updateLista(@PathVariable(value = "id") Long listaId,
        @Valid @RequestBody Lista listaDetails) throws ResourceNotFoundException{
        Lista lista = listaRepository.findById(listaId)
                .orElseThrow(() -> new ResourceNotFoundException("lista no se encuentra con el id: " + listaId));

        lista.setDescripcion(listaDetails.getDescripcion());
        lista.setVigente(listaDetails.isVigente());

        final Lista updateLista = listaRepository.save(lista);

        return ResponseEntity.ok(updateLista);
    }


    @DeleteMapping("/listas/{id}")
    public Map<String,Boolean> deleteLista(@PathVariable(value = "id") Long listaId)
        throws ResourceNotFoundException{
        Lista lista = listaRepository.findById(listaId)
                .orElseThrow(() -> new ResourceNotFoundException("lista no se encuentra con el id: " + listaId));
        listaRepository.delete(lista);
        Map<String, Boolean> resp = new HashMap<>();
        resp.put("Eliminada la Lista",Boolean.TRUE);

        return resp;
    }

}
