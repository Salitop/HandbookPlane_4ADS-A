package com.handkbookplane.controller;

import com.handkbookplane.model.LEP;
import com.handkbookplane.repository.LepRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Classe responsável por testar o insert do LEP na base de dados.
 * @author Kevin Melo
 * @since 25/11/2021
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestLepController {

    @Autowired
    LepRepository lepRepository;

    @Test
    public void testCadastroLep() {

        LEP lep1 = new LEP();
        lep1.setAcao("LEP1");
        lep1.setData("02/12/2022");
        lep1.setNbloco(100);
        lep1.setSecao("101");
        lep1.setNomeBloco("BlocoTestLep1");

        LEP lep2 = new LEP();
        lep2.setAcao("LEP2");
        lep2.setData("03/12/2022");
        lep2.setNbloco(200);
        lep2.setSecao("201");
        lep2.setNomeBloco("BlocoTestLep2");

        lepRepository.save(lep1);
        lepRepository.save(lep2);

        LEP lepAlvo = lepRepository.findAllByNomeBloco("BlocoTestLep2");

        Assert.assertNotEquals(lep1.getNomeBloco(), lepAlvo.getNomeBloco());
        Assert.assertEquals("LEP2", lepAlvo.getAcao());
        Assert.assertEquals("03/12/2022", lepAlvo.getData());
        Assert.assertEquals(Integer.valueOf(200), lepAlvo.getNbloco());
        Assert.assertEquals("201", lepAlvo.getSecao());
        Assert.assertEquals("BlocoTestLep2", lepAlvo.getNomeBloco());

        lepRepository.delete(lep1);
        lepRepository.delete(lep2);
    }
}
