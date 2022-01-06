package com.example.project.todolistapp.controller;

import com.example.project.todolistapp.model.Lista;
import com.example.project.todolistapp.repository.ListaRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:sqlserver://localhost;databaseName=TodoList",
        "spring.datasource.username=admin",
        "spring.datasource.password=password"
})
class ListaControllerTest {


    @Autowired
    private ListaRepository listaRepository;


    @Test
    void getAllListas() {

        List<Lista> listaList = listaRepository.findAll();
        Assertions.assertThat(listaList.size()).isGreaterThan(0);
    }


    @Test
    void getListaById() {

        Lista lista = listaRepository.findById(1L).get();

        Assertions.assertThat(lista.getIdentificador()).isEqualTo(1L);


    }

    @Test
    void createLista() {
        Lista lista = Lista.builder()
                .descripcion("Test de creacion")
                .vigente(true)
                .build();
        listaRepository.save(lista);
        Assertions.assertThat(lista.getIdentificador()).isGreaterThan(0);
    }

    @Test
    void updateLista() {
        Lista lista = listaRepository.findById(1L).get();

        lista.setDescripcion("He modificado mediante test");
        Lista listaUpdated = listaRepository.save(lista);
        Assertions.assertThat(listaUpdated.getDescripcion()).isEqualTo("He modificado mediante test");

    }

    @Test
    void deleteLista() {

        Lista lista = listaRepository.findById(1L).get();
        listaRepository.delete(lista);
        Lista lista1 = null;
        Optional<Lista> optionalLista = listaRepository.findById(1L);
        if(optionalLista.isPresent()){
            lista1 = optionalLista.get();
        }
        Assertions.assertThat(lista1).isNull();

    }
}