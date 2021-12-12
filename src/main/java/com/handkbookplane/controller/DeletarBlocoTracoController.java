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
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
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
public class DeletarBlocoTracoController {
    @Autowired
    BlocoRepository blocoRepository;
    @Autowired
    AdministradorRepository administradorRepository;
    @Autowired
    TracoRepository tracoRepository;
    @Autowired
    CodelistRepository codelistRepository;

    @GetMapping(value = "/deletarTracoPDF")
    public ModelAndView teladeletarTracoPDF(Integer idTraco) throws IOException {
        Administrador administrador = administradorRepository.findByIdAdmin(Usuario.IdUsu);
        idTraco = Usuario.idTracoGlobal;

        if(idTraco != null)
        {
            //Carregar PDF
            ModelAndView mv = new ModelAndView("/tracos/deletarTracoPDF");
            Traco traco = tracoRepository.findByIdTraco(idTraco);

            String pdf = Base64.getEncoder().encodeToString(traco.getPDF());
            traco.setPDF_string(pdf);

            mv.addObject("traco", traco);

            mv.addObject("administrador", administrador);

            //Exibir número de páginas
            Traco tracoPDF = tracoRepository.findByIdTraco(idTraco);

            byte[] pdfs = tracoPDF.getPDF();

            File file = new File("pdfs");

            FileUtils.writeByteArrayToFile(file, pdfs);

            PDDocument document = PDDocument.load(file);

            Integer numpag = document.getNumberOfPages();

            System.out.println(numpag);


            List<Integer> pagTotais = new ArrayList<>();

            //Por começar a contar desde 0, este for precisa somar +1 a fim de exibição para o usuário
            for (int i = 1; i <= numpag; i++)
            {
                pagTotais.add(i);

                System.out.println(i);
            }
            mv.addObject("numpag", pagTotais);

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
    public ModelAndView eventodeletarTracoPDF(HttpSession session, HttpServletRequest request, Integer idTraco, @RequestParam("select") Integer pagina) throws IOException {
        Administrador administrador = administradorRepository.findByIdAdmin(Usuario.IdUsu);
        FileOutputStream fos = new FileOutputStream("pdf");

        idTraco = Usuario.idTracoGlobal;
        Traco tracoPDF = tracoRepository.findByIdTraco(idTraco);
        ModelAndView mv = new ModelAndView("/menu/menuTraco");
        mv.addObject("administrador", administrador);

        byte[] pdf = tracoPDF.getPDF();
        fos.write(pdf);
        File pdfF = new File("pdf");
        PDDocument document = PDDocument.load(pdfF);

        Integer no0fpages = document.getNumberOfPages();
        System.out.println(no0fpages);
        //Subtração realizada para encontrar a página correta
        document.removePage(pagina - 1);

        document.save("pdf");

        document.close();
        byte[] pdfFinal = Files.readAllBytes(pdfF.toPath());

        tracoPDF.setPDF(pdfFinal);
        tracoPDF.setIdTraco(Usuario.idTracoGlobal);
        tracoRepository.save(tracoPDF);
        return mv;
    }
}