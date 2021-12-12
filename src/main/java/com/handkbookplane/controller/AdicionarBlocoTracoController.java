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
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.util.Base64Util;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

import java.io.*;
import java.nio.file.Files;
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
    public ModelAndView telaAddBlocoTraco(String name) {
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

    @RequestMapping(value = "/adicionarBlocoTraco/{id_bloco}")
    public ModelAndView AdicionarBlocoTraco(Traco traco, Codelist codelist, Integer idBloco, Integer val) throws IOException {
        Administrador administrador = administradorRepository.findByIdAdmin(Usuario.IdUsu);

        //Gerando arquivos para poder realizar a adição do bloco no traço
        FileOutputStream fosT = new FileOutputStream("pdfT");
        FileOutputStream fosB = new FileOutputStream("pdfB");

        if(idBloco != null) {
            ModelAndView mv = new ModelAndView("/menu/menuTraco");
                Integer idTraco = Usuario.idTracoGlobal;

            //Salvando pdf do Traço em uma File
            Traco tracoPdf = tracoRepository.findByIdTraco(idTraco);
            byte[] pdfT = tracoPdf.getPDF();
            fosT.write(pdfT);
            File pdfFT = new File("pdfT");
            PDDocument docTraco = PDDocument.load(pdfFT);

            //Salvando pdf do bloco em uma File
            Bloco blocoPdf = blocoRepository.findByIdBloco(idBloco);
            byte[] pdfB = blocoPdf.getPDF();
            fosB.write(pdfB);
            File pdfFB = new File("pdfB");
            PDDocument pdfBlocoDoc = PDDocument.load(pdfFB);

            //Contando as paginas que o arquivo possui e adiciona as páginas do Bloco no Traço
            Integer numpag = pdfBlocoDoc.getNumberOfPages();
                List<Integer> pagTotais = new ArrayList<>();
               for (int i = 0; i < numpag; i++)
               {
                 System.out.println(i);
                 docTraco.addPage(pdfBlocoDoc.getPage(i));
                 docTraco.save("pdfT");
               }
             //Salvando o arquivo e salvando no banco
            docTraco.close();
               byte[] pdfFinal = Files.readAllBytes(pdfFT.toPath());
                tracoPdf.setPDF(pdfFinal);
                tracoPdf.setIdTraco(Usuario.idTracoGlobal);
                tracoRepository.save(tracoPdf);
                mv.addObject("administrador", administrador);
                return mv;
            }

        else{
            ModelAndView mv = new ModelAndView("redirect:/menuTraco");
            mv.addObject("administrador", administrador);
            return mv;
        }
    }

}