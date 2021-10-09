package com.handkbookplane.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Classe responsável por controlar as funções do sistema
 *
 * @Author: Kevin Melo
 * @Since: 21/09/2021
 */
@Controller
public class MenuUsuarioController {

    /**
     * Método responsável por dar um get na tela de cadastro do administrador
     * @return ModelAndView
     */
    @GetMapping(value = "/menuUsuario")
    public ModelAndView telamenuUsuario() {
        return new ModelAndView("/menu/menuUsuario");
    }
}