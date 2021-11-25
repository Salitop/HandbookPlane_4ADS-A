package com.handkbookplane.controller;

import com.handkbookplane.model.Administrador;
import com.handkbookplane.model.Bloco;
import com.handkbookplane.model.Usuario;
import com.handkbookplane.repository.AdministradorRepository;
import com.handkbookplane.repository.BlocoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
/**
 * Classe responsável por controlar as funções do sistema
 *
 * @Author: Luiz Miguel
 * @Since: 10/10/2021
 */
@Controller
public class CriarTracoController {
    @Autowired
    BlocoRepository blocoRepository;
    @Autowired
    AdministradorRepository administradorRepository;
    /**
     * Método responsável por dar um get na tela menu Traço
     * @return ModelAndView
     */
    @GetMapping(value = "/criarTraco")
    public ModelAndView telacriarTraco(String name) {
        Administrador administrador = administradorRepository.findByIdAdmin(Usuario.IdUsu);

        ModelAndView mv = new ModelAndView("/tracos/criarTraco");

        if (name != null) {

            ArrayList<Bloco> bloco = blocoRepository.findByNomeBloco(name);

            if (bloco.size() != 0) {

                List<Bloco> blocosTotais = new ArrayList<>();

                for (Bloco blocos : bloco) {

                    String pdf = Base64.getEncoder().encodeToString(blocos.getPDF());
                    blocos.setPDF_string(pdf);
                    blocosTotais.add(blocos);
                }

                mv.addObject("bloco", blocosTotais);

                mv.addObject("administrador", administrador);

                return mv;
            }

            Iterable<Bloco> blocos = blocoRepository.findAll();

            mv.addObject("bloco", blocos);

            mv.addObject("administrador", administrador);
            return mv;
        }

        Iterable<Bloco> bloco = blocoRepository.findAll();

        List<Bloco> blocosTotais = new ArrayList<>();

        for (Bloco blocos : bloco) {

            String pdf = Base64.getEncoder().encodeToString(blocos.getPDF());
            blocos.setPDF_string(pdf);
            blocosTotais.add(blocos);
        }

        mv.addObject("bloco", blocosTotais);

        mv.addObject("administrador", administrador);
        return mv;
    }
}