package com.handkbookplane.controller;

import com.handkbookplane.model.Administrador;
import com.handkbookplane.model.Bloco;
import com.handkbookplane.model.Usuario;
import com.handkbookplane.repository.AdministradorRepository;
import com.handkbookplane.repository.BlocoRepository;
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
public class VisualizarBlocoController {

    @Autowired
    AdministradorRepository administradorRepository;

    @Autowired
    BlocoRepository blocoRepository;

    /**
     * Método responsável por dar um get na tela menu Traço
     * @return ModelAndView
     */
    @GetMapping(value = "/visualizarBloco")
    public ModelAndView telavisualizarBloco(String name) {
        Administrador administrador = administradorRepository.findByIdAdmin(Usuario.IdUsu);

        ModelAndView mv = new ModelAndView("/bloco/visualizarBloco");

        if (name != null) {

            ArrayList<Bloco> bloco = blocoRepository.findByNomeBloco(name);

            if (bloco.size() != 0) {

                mv.addObject("bloco", bloco);

                mv.addObject("administrador", administrador);

                return mv;
            }

            Iterable<Bloco> blocos = blocoRepository.findAll();

            mv.addObject("bloco", blocos);

            mv.addObject("administrador", administrador);
            return mv;
        }

        Iterable<Bloco> bloco = blocoRepository.findAll();

        mv.addObject("bloco", bloco);

        mv.addObject("administrador", administrador);
        return mv;
    }

    @GetMapping(value = "/visualizarBlocoPDF/{id_bloco}")
    public ModelAndView telavisualizarBlocoPDF(Integer idBloco) {
        Administrador administrador = administradorRepository.findByIdAdmin(Usuario.IdUsu);

        ModelAndView mv = new ModelAndView("/bloco/visualizarBlocoPDF");

        Bloco bloco = blocoRepository.findByIdBloco(idBloco);

            String pdf = Base64.getEncoder().encodeToString(bloco.getPDF());
            bloco.setPDF_string(pdf);

        mv.addObject("bloco", bloco);

        mv.addObject("administrador", administrador);
        return mv;
    }
}