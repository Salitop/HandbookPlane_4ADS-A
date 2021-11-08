package com.handkbookplane.controller;

import com.handkbookplane.model.Administrador;
import com.handkbookplane.model.Usuario;
import com.handkbookplane.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
     *
     * @return ModelAndView
     */
    @GetMapping(value = "/cadastrarAdministrador")
    public ModelAndView telaCadastraAdministrador() {
        Administrador administrador = administradorRepository.findByIdAdmin(Usuario.IdUsu);

        ModelAndView mv = new ModelAndView("/administrador/cadastrarAdministrador");
        mv.addObject("administrador", administrador);
        return mv;
    }

    /**
     * Método responsável por cadastrar o administrador no banco de dados
     *
     * @param administrador
     * @return ModelAndView
     */
    @PostMapping(value = "/cadastrarAdministrador")
    public ModelAndView cadastraAdministrador(Administrador administrador) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("admin", administradorRepository.save(administrador).getIdAdmin());
        modelAndView.setViewName("/administrador/cadastrarAdministrador");
        return modelAndView;
    }

    @GetMapping(value = "/alterarAdministrador")
    public ModelAndView telaAlterarAdministrador() {
        ModelAndView modelAndView = new ModelAndView("/administrador/alterarAdministrador");
        modelAndView.addObject("admin", administradorRepository.findByIdAdmin(Usuario.IdUsu));
        return modelAndView;
    }

    @PostMapping(value = "/alterarAdministrador")
    public ModelAndView alteraAdministrador(Administrador administrador) {
        ModelAndView modelAndView = new ModelAndView();
        administrador.setIdAdmin(Usuario.IdUsu);
        modelAndView.addObject("admin", administradorRepository.save(administrador).getIdAdmin());
        modelAndView.setViewName("/administrador/cadastrarAdministrador");
        return modelAndView;
    }

    @GetMapping(value = "/listarAdministrador")
    public ModelAndView telaListaAdministradores() {

        ModelAndView modelAndView = new ModelAndView("/administrador/listaAdministrador");
        modelAndView.addObject("admin", administradorRepository.findAll());
        Administrador administrador = administradorRepository.findByIdAdmin(Usuario.IdUsu);
        modelAndView.addObject("administrador", administrador);

        return modelAndView;
    }

    @RequestMapping("/deletar/{id_admin}")
    public String deletarTutor(Integer idAdmin) {
        Administrador administrador = administradorRepository.findByIdAdmin(idAdmin);
        administradorRepository.delete(administrador);
        return "redirect:/listarAdministrador";
    }
}