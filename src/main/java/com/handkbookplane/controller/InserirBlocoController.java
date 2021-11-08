package com.handkbookplane.controller;

import com.handkbookplane.mapper.BlocoMapper;
import com.handkbookplane.model.Administrador;
import com.handkbookplane.model.Bloco;
import com.handkbookplane.model.BlocoControllerModel;
import com.handkbookplane.model.Usuario;
import com.handkbookplane.repository.AdministradorRepository;
import com.handkbookplane.repository.BlocoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * Classe responsável por controlar as funções do sistema
 *
 * @Author: Luiz Miguel
 * @Since: 10/10/2021
 */
@Controller
public class InserirBlocoController {

    @Autowired
    BlocoRepository BlocoRepository;
    /**
     * Método responsável por dar um get na tela menu Traço
     * @return ModelAndView
     */
    @Autowired
    AdministradorRepository administradorRepository;

    /**
     * Método responsável por dar um get na tela menu Bloco
     * @return ModelAndView
     */
    @GetMapping(value = "/inserirBloco")
    public ModelAndView inserirBloco() {
        Administrador administrador = administradorRepository.findByIdAdmin(Usuario.IdUsu);

        ModelAndView mv = new ModelAndView("/bloco/inserirBloco");
        mv.addObject("administrador", administrador);
        return mv;
    }

/*    @PostMapping(value = "/inserirBloco")
    public ModelAndView inserirBloco(Bloco bloco) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("bloco", BlocoRepository.save(bloco).getIdBloco());
        modelAndView.setViewName("/bloco/inserirBloco");
        return modelAndView;
    }*/
    @RequestMapping(value = "/inserirBloco", method = RequestMethod.POST, consumes = { "multipart/form-data" })
    public String form(BlocoControllerModel bloco, @RequestParam(value = "PDF") MultipartFile PDF)
            throws IOException {
        BlocoRepository.save(BlocoMapper.converter(bloco,PDF));
        return "redirect:menuInicial";
    }
}