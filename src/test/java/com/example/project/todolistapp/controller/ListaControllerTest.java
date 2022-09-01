package com.example.project.todolistapp.controller;

import com.example.project.todolistapp.exception.ResourceNotFoundException;
import com.example.project.todolistapp.model.Lista;
import com.example.project.todolistapp.service.ListaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

class ListaControllerTest {

    @InjectMocks
    ListaController listaController;

    @Mock
    private ListaService listaService;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);

    }
    private Lista getMockLista() {

        return Lista.builder()
                .identificador(1L)
                .descripcion("descrip")
                .fechaCreacion(new Timestamp(System.currentTimeMillis()))
                .vigente(true)
                .build();
    }

    @Test
    void getAllListas() {

        Lista lista = getMockLista();

        List<Lista> listaList = new ArrayList<>();
        listaList.add(lista);
        Mockito.when(listaService.findAll()).thenReturn(listaList);

        List<Lista> response = listaController.getAllListas();
        Assertions.assertNotNull(response);
        Assertions.assertEquals(listaList.get(0).getDescripcion(),response.get(0).getDescripcion());
    }

    @Test
    void getListaById() throws ResourceNotFoundException {

        Lista lista = getMockLista();

        Mockito.when(listaService.getLista(1L)).thenReturn(lista);

        ResponseEntity<Lista> response = listaController.getListaById(1L);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(response.getBody().getIdentificador(),lista.getIdentificador());

    }

    @Test
    void createLista() {
        Lista lista = getMockLista();
        Mockito.when(listaService.createLista(lista)).thenReturn(lista);
        Lista response = listaController.createLista(lista);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getIdentificador(),lista.getIdentificador());
    }

    @Test
    void updateLista() throws ResourceNotFoundException {
        Lista lista = getMockLista();
        lista.setDescripcion("He modificado mediante test");
        Mockito.when(listaService.UpdateLista(1L,lista)).thenReturn(lista);

        ResponseEntity<Lista> response = listaController.updateLista(1L,lista);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals("He modificado mediante test",response.getBody().getDescripcion());
        Assertions.assertEquals(1L,response.getBody().getIdentificador());
    }

    @Test
    void deleteLista() throws ResourceNotFoundException {
        Mockito.doNothing().when(listaService).deleteLista(1L);
        Assertions.assertTrue(true);
    }
}