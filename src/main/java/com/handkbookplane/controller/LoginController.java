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
 * @Since: 06/10/2021
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

        Administrador adm;
        adm = administradorRepository.findByLogin(login);

        if(adm != null && adm.getLogin().equals(login) && adm.getSenha().equals(senha)) {

            Usuario usuario = new Usuario();

            usuario.IdUsu = (adm.getIdAdmin());
            return "redirect:menuInicial";
        }
        return "redirect:login";
    }
}