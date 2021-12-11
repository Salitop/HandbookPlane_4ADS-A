package com.handkbookplane.controller;

import com.handkbookplane.model.Administrador;
import com.handkbookplane.model.Bloco;
import com.handkbookplane.model.Traco;
import com.handkbookplane.model.Usuario;
import com.handkbookplane.repository.AdministradorRepository;
import com.handkbookplane.repository.TracoRepository;
import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * Classe responsável por controlar as funções do sistema
 *
 * @Author: Luiz Miguel
 * @Since: 28/10/2021
 */
@Controller
public class ListarTracoController {

    @Autowired
    AdministradorRepository administradorRepository;
    @Autowired
    TracoRepository tracoRepository;
    /**
     * Método responsável por dar um get na tela menu Traço
     * @return ModelAndView
     */

    @GetMapping(value = "/listarTraco")
    public ModelAndView telaListarTraco(String nome) {

        ModelAndView modelAndView = new ModelAndView("/tracos/listarTraco");
        modelAndView.addObject("traco", tracoRepository.findAll());
        Administrador administrador = administradorRepository.findByIdAdmin(Usuario.IdUsu);
        modelAndView.addObject("administrador", administrador);
        System.out.println(nome);
        if (nome != null) {
            System.out.println(nome);
            ArrayList<Traco> traco = tracoRepository.findByNomeTraco(nome);

            if (traco.size() != 0) {

                List<Traco> tracoTotais = new ArrayList<>();

                for (Traco tracos : traco) {


                    tracoTotais.add(tracos);
                }

                modelAndView.addObject("traco", tracoTotais);

                return modelAndView;
            }
            Iterable<Traco> tracos = tracoRepository.findAll();
            modelAndView.addObject("traco", tracos);
            return modelAndView;
        }
        Iterable<Traco> tracos = tracoRepository.findAll();
        modelAndView.addObject("traco", tracos);
        return modelAndView;
    }

    @GetMapping(value = "/visualizarTracoPDF/{id_traco}")
    public ModelAndView telavisualizarBlocoPDF(Integer idTraco) {
        Administrador administrador = administradorRepository.findByIdAdmin(Usuario.IdUsu);

        if(idTraco != null)
        {
            ModelAndView mv = new ModelAndView("/tracos/visualizarTracoPDF");

            Traco traco = tracoRepository.findByIdTraco(idTraco);

            String pdf = Base64.getEncoder().encodeToString(traco.getPDF());
            traco.setPDF_string(pdf);

            mv.addObject("traco", traco);

            mv.addObject("administrador", administrador);
            return mv;
        }
        else
        {
            ModelAndView mv = new ModelAndView("/tracos/menuTraco.html");
            mv.addObject("administrador", administrador);
            return mv;
        }
    }

    @GetMapping(value = "/deletarTracoPDF")
    public ModelAndView teladeletarTracoPDF(Integer idTraco) throws IOException {
        Administrador administrador = administradorRepository.findByIdAdmin(Usuario.IdUsu);

        Traco tracoPDF = tracoRepository.findByIdTraco(idTraco);

        byte[] pdfs = tracoPDF.getPDF();

        File file = new File("filekk");

        FileUtils.writeByteArrayToFile(file, pdfs);

        PDDocument document = PDDocument.load(file);

        Integer no0fpages = document.getNumberOfPages();
        System.out.println(no0fpages);

        document.removePage(/*paginaqueusuiariofalou*/ 2);


        if(idTraco != null)
        {
            ModelAndView mv = new ModelAndView("/tracos/deletarTracoPDF");

            Traco traco = tracoRepository.findByIdTraco(idTraco);

            String pdf = Base64.getEncoder().encodeToString(traco.getPDF());
            traco.setPDF_string(pdf);

            mv.addObject("traco", traco);

            mv.addObject("administrador", administrador);
            return mv;
        }
        else
        {
            ModelAndView mv = new ModelAndView("redirect:/menuTraco");
            mv.addObject("administrador", administrador);
            return mv;
        }
    }

    @PostMapping(value = "/deletarTracoPDF")
    public ModelAndView eventodeletarTracoPDF(Integer idTraco) throws IOException {

        idTraco = Usuario.idTracoGlobal;

        Traco tracoPDF = tracoRepository.findByIdTraco(idTraco);

        byte[] pdf = tracoPDF.getPDF();
        ModelAndView mv = new ModelAndView("/tracos/deletarTracoPDF");

        Path files = Files.write(new File("kk").toPath(), pdf);

        PDDocument document = PDDocument.load((InputStream) files);

        Integer no0fpages = document.getNumberOfPages();
        System.out.println(no0fpages);

        return mv;
    }


}