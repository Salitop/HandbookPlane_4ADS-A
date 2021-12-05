package com.handkbookplane.controller;

import com.handkbookplane.model.Codelist;
import com.handkbookplane.repository.CodelistRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Classe responsável por testar o insert do code list na base de dados.
 * @author Kevin Melo
 * @since 25/11/2021
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestCodelistController {

    @Autowired
    CodelistRepository codelistRepository;

    @Test
    public void testCadastroCodelist() {

        Codelist codelist1 = new Codelist();
        codelist1.setCode(7);
        codelist1.setNbloco(8);
        codelist1.setSecao("9");
        codelist1.setSubsecao("10");
        codelist1.setApelidoBloco("AVIAO-TEST");
        codelist1.setRemark("11");

        Codelist codelist2 = new Codelist();
        codelist2.setCode(8);
        codelist2.setNbloco(9);
        codelist2.setSecao("10");
        codelist2.setSubsecao("11");
        codelist2.setApelidoBloco("AVIAO-TEST-2");
        codelist2.setRemark("12");

        codelistRepository.save(codelist1);
        codelistRepository.save(codelist2);

        Codelist codelistAlvo = codelistRepository.findAllByApelidoBloco("AVIAO-TEST");

        Assert.assertNotEquals(codelist1.getApelidoBloco(), codelist2.getApelidoBloco());
        Assert.assertEquals("AVIAO-TEST", codelistAlvo.getApelidoBloco());
        Assert.assertEquals(Integer.valueOf(7), codelistAlvo.getCode());
        Assert.assertEquals(Integer.valueOf(8), codelistAlvo.getNbloco());
        Assert.assertEquals("9", codelistAlvo.getSecao());
        Assert.assertEquals("10", codelistAlvo.getSubsecao());
        Assert.assertEquals("11", codelistAlvo.getRemark());

        codelistRepository.delete(codelist1);
        codelistRepository.delete(codelist2);
    }
}
