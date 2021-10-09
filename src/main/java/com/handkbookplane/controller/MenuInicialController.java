package com.handkbookplane.controller;

import com.handkbookplane.model.Administrador;
import com.handkbookplane.model.Usuario;
import com.handkbookplane.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Classe responsável por controlar as funções do sistema
 *
 * @Author: Kevin Melo
 * @Since: 21/09/2021
 */
@Controller
public class MenuInicialController {

    @Autowired
    AdministradorRepository administradorRepository;

    /**
     * Método responsável por dar um get na tela de cadastro do administrador
     * @return ModelAndView
     */
    @GetMapping(value = "/menuInicial")
    public ModelAndView telaMenuInicial()
    {
        Administrador administrador = administradorRepository.findByIdAdmin(Usuario.IdUsu);

        ModelAndView mv = new ModelAndView("/menu/menuInicial");
        mv.addObject("administrador", administrador);
        return mv;
    }
}