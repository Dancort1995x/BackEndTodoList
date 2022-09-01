package com.example.project.todolistapp.service;

import com.example.project.todolistapp.exception.ResourceNotFoundException;
import com.example.project.todolistapp.model.Lista;
import com.example.project.todolistapp.repository.ListaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ListaService {

    private final ListaRepository repository;

    @Autowired
    public ListaService (ListaRepository repository){
        this.repository = repository;
    }

    public Lista createLista(Lista lista){
        return repository.save(lista);
    }

    public List<Lista> findAll(){
        return repository.findAll();
    }

    public Lista getLista(Long id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("lista no se encuentra con el id: " + id));
    }

    @Transactional
    public Lista UpdateLista(Long id, Lista lista) throws ResourceNotFoundException {
        Lista listaUpdate = getLista(id);
        listaUpdate.setDescripcion(lista.getDescripcion());
        listaUpdate.setVigente(lista.isVigente());
        return listaUpdate;
    }

    public void deleteLista(Long id) throws ResourceNotFoundException {
        Lista listaDelete = getLista(id);
        repository.delete(listaDelete);
    }

}
