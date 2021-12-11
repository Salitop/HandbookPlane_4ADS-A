package com.handkbookplane.controller;

import com.handkbookplane.model.Administrador;
import com.handkbookplane.model.Bloco;
import com.handkbookplane.model.Traco;
import com.handkbookplane.model.Usuario;
import com.handkbookplane.model.Codelist;
import com.handkbookplane.repository.AdministradorRepository;
import com.handkbookplane.repository.BlocoRepository;
import com.handkbookplane.repository.CodelistRepository;
import com.handkbookplane.repository.TracoRepository;
import org.apache.logging.log4j.util.Base64Util;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
/**
 * Classe responsável por controlar as funções do sistema
 *
 * @Author: Luiz Miguel
 * @Since: 10/10/2021
 */
@Controller
public class AdicionarBlocoTracoController {
    @Autowired
    BlocoRepository blocoRepository;
    @Autowired
    AdministradorRepository administradorRepository;
    @Autowired
    TracoRepository tracoRepository;
    @Autowired
    CodelistRepository codelistRepository;

    /**
     * Método responsável por dar um get na tela menu Traço
     * @return ModelAndView
     */
    @GetMapping(value = "/adicionarBlocoTraco")
    public ModelAndView teladeletarBlocoTraco(String name) {
        Administrador administrador = administradorRepository.findByIdAdmin(Usuario.IdUsu);

        ModelAndView mv = new ModelAndView("/tracos/adicionarBlocoTraco");

        if (name != null) {

            ArrayList<Bloco> bloco = blocoRepository.findByNomeBloco(name);

            if (bloco.size() != 0) {

                List<Bloco> blocosTotais = new ArrayList<>();

                for (Bloco blocos : bloco) {

                    String pdf = Base64.getEncoder().encodeToString(blocos.getPDF());
                    blocos.setPDF_string(pdf);
                    blocosTotais.add(blocos);
                }

                mv.addObject("bloco", blocosTotais);

                mv.addObject("administrador", administrador);

                return mv;
            }

            Iterable<Bloco> blocos = blocoRepository.findAll();

            mv.addObject("bloco", blocos);

            mv.addObject("administrador", administrador);
            return mv;
        }

        Iterable<Bloco> bloco = blocoRepository.findAll();

        List<Bloco> blocosTotais = new ArrayList<>();

        for (Bloco blocos : bloco) {

            String pdf = Base64.getEncoder().encodeToString(blocos.getPDF());
            blocos.setPDF_string(pdf);
            blocosTotais.add(blocos);
        }

        mv.addObject("bloco", blocosTotais);

        mv.addObject("administrador", administrador);
        return mv;
    }

    //Deverá cadastrar tanto Traço quanto Codelist
    @PostMapping(value = "/adicionarBlocoTraco")
    public Object deletarBlocoTraco(Traco traco, Codelist codelist, Integer idBloco, Integer val) throws IOException {
        if(idBloco != null) {
            ModelAndView mv = new ModelAndView("redirect:/menuTraco");
            if(val == 2) {
                //Puxando informações do bloco
                Bloco bloco = blocoRepository.findByIdBloco(idBloco);
                //Salvando PDF do bloco no Traço
                byte[] pdf = bloco.getPDF();
                traco.setPDF(pdf);
                //Cadastrando Traço
                mv.addObject("traco", tracoRepository.save(traco));
                //Cadastrando no codelist
                codelist.setCode(bloco.getCode());
                codelist.setApelidoBloco(bloco.getNomeBloco());
                codelist.setNbloco(bloco.getNbloco());
                codelist.setSecao(bloco.getSecao());
                codelist.setSubsecao(bloco.getSubsecao());

                mv.addObject("codelist", codelistRepository.save(codelist));
                return mv;
            }
            else
            {
                //Puxando informações do bloco
                Bloco blocoPDF = blocoRepository.findByIdBloco(idBloco);
                Traco tracoPDF = tracoRepository.findByIdTraco(Usuario.idTracoGlobal);
                PDFMergerUtility pdfFinal = new PDFMergerUtility();
                String StringPDFt = Base64.getEncoder().encodeToString(tracoPDF.getPDF());
                String StringPDFb = Base64.getEncoder().encodeToString(blocoPDF.getPDF());
                //-------------------- ta cagado
                byte[] pdf1 = doIt(StringPDFt);
                byte[] pdf2 = doIt(StringPDFb);
                PDFMergerUtility merger = new PDFMergerUtility();
                merger.addSource(new ByteArrayInputStream(pdf1));
                merger.addSource(new ByteArrayInputStream(pdf2));
           //     merger.mergeDocuments();
           //     String pdf = Base64.getEncoder().encodeToString(merger); //salva (n ta funcionando pq tem q ser em base 64)
           //      traco.setPDF();
                return mv;
            }
        }
        else{
            ModelAndView mv = new ModelAndView("redirect:/menuTraco");
            return mv;
        }
    }

    static byte[] doIt(String message) throws IOException {
        PDDocument doc = new PDDocument();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        doc.save(baos);
        return baos.toByteArray();
    }

}