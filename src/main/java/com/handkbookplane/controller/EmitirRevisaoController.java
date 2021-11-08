package com.handkbookplane.controller;

import com.handkbookplane.model.Administrador;
import com.handkbookplane.model.Bloco;
import com.handkbookplane.model.Usuario;
import com.handkbookplane.repository.AdministradorRepository;
import com.handkbookplane.repository.BlocoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
public class EmitirRevisaoController {

    @Autowired
    AdministradorRepository administradorRepository;

    @Autowired
    BlocoRepository blocoRepository;

    /**
     * Método responsável por dar um get na tela menu Traço
     *
     * @return ModelAndView
     */
    @GetMapping(value = "/emitirRevisao")
    public ModelAndView telaemitirRevisao() {
        Administrador administrador = administradorRepository.findByIdAdmin(Usuario.IdUsu);

        ModelAndView mv = new ModelAndView("/bloco/emitirRevisao");

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
//PÁGINA PARA CADASTRAR A REVISÃO

    @GetMapping(value = "/revisaoBloco/{idBloco}/{nomeBloco}")
    public ModelAndView telarevisaoBloco(Integer idBLoco, String nomeBloco) {
        Administrador administrador = administradorRepository.findByIdAdmin(Usuario.IdUsu);

        ModelAndView mv = new ModelAndView("/bloco/revisaoBloco");

        Bloco bloco = blocoRepository.findByIdBloco(idBLoco);

        Iterable<Bloco> blocoCode = blocoRepository.findByNomeBloco(nomeBloco);

        mv.addObject("blocoCode", blocoCode);
        mv.addObject("bloco", bloco);

        mv.addObject("administrador", administrador);
        return mv;
    }

    @PostMapping(value = "/revisaoBloco/{idBloco}/{nomeBloco}")
    public ModelAndView cadRevisao(Bloco bloco) {
        //Cadastrar revisão
        ModelAndView mv = new ModelAndView("/bloco/revisaoBloco");
        return mv;
    }



}