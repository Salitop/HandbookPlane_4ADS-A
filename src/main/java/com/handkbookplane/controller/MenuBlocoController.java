package com.handkbookplane.controller;

import com.handkbookplane.model.Administrador;
import com.handkbookplane.model.Usuario;
import com.handkbookplane.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Classe responsável por controlar as funções do sistema
 *
 * @Author: Luiz Miguel
 * @Since: 10/10/2021
 */
@Controller
public class MenuBlocoController {

    @Autowired
    AdministradorRepository administradorRepository;

    /**
     * Método responsável por dar um get na tela menu Bloco
     * @return ModelAndView
     */
    @GetMapping(value = "/menuBloco")
    public ModelAndView telamenuBloco() {
        Administrador administrador = administradorRepository.findByIdAdmin(Usuario.IdUsu);

        ModelAndView mv = new ModelAndView("/menu/menuBloco");
        mv.addObject("administrador", administrador);
        return mv;
    }
}