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
 * Classe responsável por controlar as funções de login do sistema
 *
 * @Author: Kevin Melo
 * @Since: 05/10/2021
 */
@Controller
public class LoginController {

    /**
     * Instanciando repository para utilizar funções do banco de dados
     */
    @Autowired
    AdministradorRepository administradorRepository;

    /**
     * Método responsável por dar um get na tela de login do sistema
     * @return ModelAndView
     */
    @GetMapping(value = "/login")
    public ModelAndView telaLogin() {
        return new ModelAndView("/login/login");
    }

    /**
     * Método responsável por realizar o login do sistema
     * @return String
     */
    @PostMapping(value = "/login")
    public String logar(String login, String senha) {

        Administrador administrador = administradorRepository.findByLogin(login);

        if(administrador != null && administrador.getLogin().equals(login) && administrador.getSenha().equals(senha)) {

            Usuario usuario = new Usuario();

            usuario.setIdUsu(administrador.getIdAdmin());
            return "redirect:menuAdministrador";
        }
        return "redirect:login";
    }
}