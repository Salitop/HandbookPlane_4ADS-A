package com.handkbookplane.controller;

import com.handkbookplane.model.Administrador;
import com.handkbookplane.model.Usuario;
import com.handkbookplane.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por controlar as funções do sistema
 *
 * @Author: Luiz Miguel
 * @Since: 10/10/2021
 */
@Controller
public class ListarUsuarioController {

    @Autowired
    AdministradorRepository administradorRepository;
    /**
     * Método responsável por dar um get na tela menu Traço
     * @return ModelAndView
     */
    @GetMapping(value = "/listarUsuario")
    public ModelAndView telalistarUsuario( String nome) {
        Administrador administrador = administradorRepository.findByIdAdmin(Usuario.IdUsu);
        ModelAndView mv = new ModelAndView("/administrador/listaAdministrador");


        mv.addObject("administrador", administrador);
        return mv;
    }
}