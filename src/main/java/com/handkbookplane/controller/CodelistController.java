package com.handkbookplane.controller;

import com.handkbookplane.model.Administrador;
import com.handkbookplane.model.Bloco;
import com.handkbookplane.model.Codelist;
import com.handkbookplane.model.Usuario;
import com.handkbookplane.repository.AdministradorRepository;
import com.handkbookplane.repository.BlocoRepository;
import com.handkbookplane.repository.CodelistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ModelAndView telacodelist() {
        Administrador administrador = administradorRepository.findByIdAdmin(Usuario.IdUsu);

        ModelAndView mv = new ModelAndView("/menu/codelist");

       if()
        Iterable<Codelist> codelist = codelistRepository.findAll();

        List<Codelist> codelistTotais = new ArrayList<>();

        for (Codelist codelists : codelist) {
            codelistTotais.add(codelists);
        }

        mv.addObject("codelist", codelistTotais);


        mv.addObject("administrador", administrador);
        return mv;
    }



}