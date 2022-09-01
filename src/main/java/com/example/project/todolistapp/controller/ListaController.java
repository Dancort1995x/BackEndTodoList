package com.example.project.todolistapp.controller;


import com.example.project.todolistapp.exception.ResourceNotFoundException;
import com.example.project.todolistapp.model.Lista;
import com.example.project.todolistapp.service.ListaService;
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

    private final ListaService listaService;

    @Autowired
    public ListaController(ListaService listaService){
        this.listaService = listaService;
    }

    @GetMapping("/listas")
    public List<Lista> getAllListas(){
        return listaService.findAll();
    }

    @GetMapping("/listas/{id}")
    public ResponseEntity<Lista> getListaById(@PathVariable(value = "id") Long listaId) throws ResourceNotFoundException {
        Lista lista = listaService.getLista(listaId);
        return ResponseEntity.ok().body(lista);
    }

    @PostMapping("/listas")
    public Lista createLista(@Valid @RequestBody Lista lista){
        return listaService.createLista(lista);
    }

    @PutMapping("/listas/{id}")
    public ResponseEntity<Lista> updateLista(@PathVariable(value = "id") Long listaId,
        @Valid @RequestBody Lista listaDetails) throws ResourceNotFoundException{
        Lista listaUpdate = listaService.UpdateLista(listaId,listaDetails);
        return ResponseEntity.ok(listaUpdate);
    }


    @DeleteMapping("/listas/{id}")
    public Map<String,Boolean> deleteLista(@PathVariable(value = "id") Long listaId)
        throws ResourceNotFoundException{
        listaService.deleteLista(listaId);
        Map<String, Boolean> resp = new HashMap<>();
        resp.put("Eliminada la Lista",Boolean.TRUE);
        return resp;
    }

}
