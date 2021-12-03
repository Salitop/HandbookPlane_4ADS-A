package com.handkbookplane.controller;

import com.handkbookplane.model.Bloco;
import com.handkbookplane.repository.BlocoRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Classe responsável por testar o insert do bloco na base de dados.
 * @author Kevin Melo
 * @since 25/11/2021
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestBlocoController {

    @Autowired
    BlocoRepository blocoRepository;

    @Test
    public void testCadastroBloco() {

        Bloco bloco = new Bloco();
        bloco.setNomeBloco("AVIAO-EMBRAER");
        bloco.setCode(5);
        bloco.setDataRev("10/05/2021");
        bloco.setNbloco(7);
        bloco.setRevisao(2);
        bloco.setSecao("3");
        bloco.setSubsecao("4");
        bloco.setDescRevisao("Revisão do avião que está em testes");

        blocoRepository.save(bloco);

        Bloco bloco2 = new Bloco();
        bloco2.setNomeBloco("BLOCO 2");
        bloco2.setCode(6);
        bloco2.setDataRev("30/11/2021");
        bloco2.setNbloco(8);
        bloco2.setRevisao(3);
        bloco2.setSecao("4");
        bloco2.setSubsecao("5");
        bloco2.setDescRevisao("Descrição do bloco 2");

        blocoRepository.save(bloco2);

        Bloco blocoAlvo = blocoRepository.findAllByNomeBloco("BLOCO 2");

        Assert.assertNotEquals(bloco.getNomeBloco(), blocoAlvo.getNomeBloco());
        Assert.assertEquals("BLOCO 2", blocoAlvo.getNomeBloco());
        Assert.assertEquals("30/11/2021", blocoAlvo.getDataRev());
        Assert.assertEquals(Integer.valueOf(6), blocoAlvo.getCode());
        Assert.assertEquals(Integer.valueOf(8), blocoAlvo.getNbloco());
        Assert.assertEquals(Integer.valueOf(3), blocoAlvo.getRevisao());
        Assert.assertEquals("4", blocoAlvo.getSecao());
        Assert.assertEquals("5", blocoAlvo.getSubsecao());
        Assert.assertEquals("Descrição do bloco 2", blocoAlvo.getDescRevisao());

        blocoRepository.delete(bloco);
        blocoRepository.delete(bloco2);
    }
}
