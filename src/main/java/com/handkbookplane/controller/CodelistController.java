package com.handkbookplane.controller;

import com.handkbookplane.model.Administrador;
import com.handkbookplane.model.Bloco;
import com.handkbookplane.model.Codelist;
import com.handkbookplane.model.Usuario;
import com.handkbookplane.repository.AdministradorRepository;
import com.handkbookplane.repository.BlocoRepository;
import com.handkbookplane.repository.CodelistRepository;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
public class CodelistController {
    @Autowired
    BlocoRepository blocoRepository;
    @Autowired
    CodelistRepository codelistRepository;
    @Autowired
    AdministradorRepository administradorRepository;
    /**
     * Método responsável por dar um get na tela menu Traço
     * @return ModelAndView
     */


    @GetMapping(value = "/codelist")
    public ModelAndView telacodelist(String nome) {
        Administrador administrador = administradorRepository.findByIdAdmin(Usuario.IdUsu);

        ModelAndView mv = new ModelAndView("/menu/codelist");

       if(nome != null)
       {
           ArrayList<Codelist> codelist = codelistRepository.findByApelidoBloco(nome);

           if (codelist.size() != 0) {

               List<Codelist> codelistTotais = new ArrayList<>();

               for (Codelist codelists : codelist) {

                   codelistTotais.add(codelists);
               }

               mv.addObject("codelist", codelistTotais);

               mv.addObject("administrador", administrador);

               return mv;
           }

       }
        Iterable<Codelist> codelist = codelistRepository.findAll();

        List<Codelist> codelistTotais = new ArrayList<>();

        for (Codelist codelists : codelist) {
            codelistTotais.add(codelists);
        }

        mv.addObject("codelist", codelistTotais);


        mv.addObject("administrador", administrador);
        return mv;
    }

    @RequestMapping("/deletar/{id_codelist}")
    public String deletarTutor(Integer idCodelist) {
        Codelist codelist = codelistRepository.findByIdCodelist(idCodelist);
        codelistRepository.delete(codelist);
        return "redirect:/codelist";
    }

}