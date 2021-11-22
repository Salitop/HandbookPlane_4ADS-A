package com.handkbookplane.controller;

import com.handkbookplane.model.Administrador;
import com.handkbookplane.repository.AdministradorRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestAdministradorController {

    @Autowired
    AdministradorRepository administradorRepository;

    @Test
    public void testCadastroAdministrador() {

        Administrador administrador = new Administrador();
        administrador.setNome("Cuzão");
        administrador.setMatricula(UUID.randomUUID().toString());
        administrador.setLogin("cuzaologin");
        administrador.setSenha("senhadocuzao");

        Administrador administrador2 = new Administrador();
        administrador2.setNome("Cuzão 2");
        administrador2.setMatricula(UUID.randomUUID().toString());
        administrador2.setLogin("cuzaologin 2");
        administrador2.setSenha("senhadocuzao 2");

        administradorRepository.save(administrador);
        administradorRepository.save(administrador2);

        Administrador alvo = administradorRepository.findByLogin("cuzaologin 2");

        Assert.assertNotEquals(administrador.getNome(), alvo.getNome());
        Assert.assertEquals(administrador2.getNome(), alvo.getNome());

        administradorRepository.delete(administrador);
        administradorRepository.delete(administrador2);
    }
}
