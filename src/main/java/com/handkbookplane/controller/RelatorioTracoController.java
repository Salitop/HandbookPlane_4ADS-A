package com.handkbookplane.controller;

import com.handkbookplane.model.Administrador;
import com.handkbookplane.model.Bloco;
import com.handkbookplane.model.Traco;
import com.handkbookplane.model.Usuario;
import com.handkbookplane.repository.AdministradorRepository;
import com.handkbookplane.repository.TracoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Classe responsável por controlar as funções do sistema
 *
 * @Author: Luiz Miguel
 * @Since: 10/10/2021
 */
@Controller
public class RelatorioTracoController {

    @Autowired
    AdministradorRepository administradorRepository;

    @Autowired
    TracoRepository tracoRepository;

    /**
     * Método responsável por dar um get na tela menu Traço
     * @return ModelAndView
     */
    @GetMapping(value = "/relatorioTraco/{idTraco}")
    public ModelAndView telarevisaoBloco(Integer idTraco) {
        Administrador administrador = administradorRepository.findByIdAdmin(Usuario.IdUsu);

        ModelAndView mv = new ModelAndView("/tracos/relatorioTraco");

        Traco traco = tracoRepository.findByIdTraco(idTraco);

        mv.addObject("traco", traco);

        mv.addObject("administrador", administrador);
        return mv;
    }

    @PostMapping(value = "/relatorioTraco/{idTraco}")
    public ModelAndView CadrelatorioTraco(Integer idTraco, Traco traco, String descRel, String pagRel) {
        Administrador administrador = administradorRepository.findByIdAdmin(Usuario.IdUsu);

        //Cadastrando a revisão
        ModelAndView modelAndView = new ModelAndView();
        Traco tracoAlterado = tracoRepository.findByIdTraco(idTraco);
        modelAndView.addObject("traco", tracoAlterado);
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String dateFormatado = date.format(formatter);
        tracoAlterado.setDataRel(dateFormatado);
        tracoAlterado.setDescRel(descRel);
        tracoAlterado.setPagRel(pagRel);
        modelAndView.addObject("traco", tracoRepository.save(tracoAlterado));


        ModelAndView mv = new ModelAndView("/menu/menuTraco");
        mv.addObject("administrador", administrador);
        return mv;
    }
}