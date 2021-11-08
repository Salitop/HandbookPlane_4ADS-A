package com.handkbookplane.controller;

import com.handkbookplane.model.Administrador;
import com.handkbookplane.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.handkbookplane.repository.AdministradorRepository;
/**
 * Classe responsável por controlar as funções do sistema
 *
 * @Author: Kevin Melo
 * @Since: 21/09/2021
 */
@Controller
public class MenuUsuarioController {

    @Autowired
    AdministradorRepository administradorRepository;

    /**
     * Método responsável por dar um get na tela de menu
     * @return ModelAndView
     */
    @GetMapping(value = "/menuUsuario")
    public ModelAndView telamenuUsuario() {
        Administrador administrador = administradorRepository.findByIdAdmin(Usuario.IdUsu);

        ModelAndView mv = new ModelAndView("/menu/menuUsuario");
        mv.addObject("administrador", administrador);
        return mv;

    }
}