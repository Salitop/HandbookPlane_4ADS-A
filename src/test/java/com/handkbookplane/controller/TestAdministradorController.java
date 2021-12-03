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

/**
 * Classe responsável por testar o insert do administrador na base de dados.
 * @author Kevin Melo
 * @since 25/11/2021
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestAdministradorController {

    @Autowired
    AdministradorRepository administradorRepository;

    @Test
    public void testCadastroAdministrador() {

        Administrador administrador = new Administrador();
        administrador.setNome("Adm 1");
        administrador.setMatricula(UUID.randomUUID().toString());
        administrador.setLogin("adm1login");
        administrador.setSenha("adms1enha");

        Administrador administrador2 = new Administrador();
        administrador2.setNome("Adm 2");
        administrador2.setMatricula(UUID.randomUUID().toString());
        administrador2.setLogin("adm2login");
        administrador2.setSenha("adm2senha");

        administradorRepository.save(administrador);
        administradorRepository.save(administrador2);

        Administrador alvo = administradorRepository.findByLogin("adm2login");

        Assert.assertNotEquals(administrador.getNome(), alvo.getNome());
        Assert.assertEquals(administrador2.getNome(), alvo.getNome());

        administradorRepository.delete(administrador);
        administradorRepository.delete(administrador2);
    }
}
