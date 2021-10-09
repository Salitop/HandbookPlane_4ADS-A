package com.handkbookplane.controller;

import com.handkbookplane.model.Administrador;
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

    /**
     * Método responsável por dar um get na tela de cadastro do administrador
     * @return ModelAndView
     */
    @GetMapping(value = "/menuInicial")
    public ModelAndView telaMenuInicial() {
        return new ModelAndView("/menu/menuInicial");
    }
}