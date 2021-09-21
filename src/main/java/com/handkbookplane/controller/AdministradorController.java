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
public class AdministradorController {

    /**
     * Instanciando repository para utilizar funções do banco de dados
     */
    @Autowired
    AdministradorRepository administradorRepository;

    /**
     * Método responsável por dar um get na tela de cadastro do administrador
     * @return ModelAndView
     */
    @GetMapping(value = "/cadastrarAdministrador")
    public ModelAndView telaCadastraAdministrador() {
        return new ModelAndView("/administrador/cadastrarAdministrador");
    }

    /**
     * Método responsável por cadastrar o administrador no banco de dados
     * @param administrador
     * @return ModelAndView
     */
    @PostMapping(value = "/cadastrarAdministrador")
    public ModelAndView cadastraAdministrador(Administrador administrador) {
        administradorRepository.save(administrador);
        return new ModelAndView("/administrador/cadastrarAdministrador");
    }
}