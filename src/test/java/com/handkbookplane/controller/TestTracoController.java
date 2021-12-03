package com.handkbookplane.controller;

import com.handkbookplane.model.Traco;
import com.handkbookplane.repository.TracoRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Classe responsável por testar o insert do traços na base de dados.
 * @author Kevin Melo
 * @since 25/11/2021
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestTracoController {

    @Autowired
    TracoRepository tracoRepository;

    @Test
    public void testCadastroTraco() {

        Traco traco1 = new Traco();
        traco1.setRemark("5");
        traco1.setNomeTraco("Traço 1");

        Traco traco2 = new Traco();
        traco2.setRemark("6");
        traco2.setNomeTraco("Traço 2");

        tracoRepository.save(traco1);
        tracoRepository.save(traco2);

        Traco tracoAlvo = tracoRepository.findByNomeTraco("Traço 1");

        Assert.assertNotEquals(traco2.getNomeTraco(), tracoAlvo.getNomeTraco());
        Assert.assertEquals("Traço 1", tracoAlvo.getNomeTraco());
        Assert.assertEquals("5", tracoAlvo.getRemark());

        tracoRepository.delete(traco1);
        tracoRepository.delete(traco2);
    }
}
