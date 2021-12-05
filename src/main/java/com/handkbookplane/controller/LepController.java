package com.handkbookplane.controller;

import com.handkbookplane.model.Administrador;
import com.handkbookplane.model.LEP;
import com.handkbookplane.model.Traco;
import com.handkbookplane.model.Usuario;
import com.handkbookplane.repository.AdministradorRepository;
import com.handkbookplane.repository.LepRepository;
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
public class LepController {

    @Autowired
    AdministradorRepository administradorRepository;

    @Autowired
    LepRepository lepRepository;
    /**
     * Método responsável por dar um get na tela menu Traço
     * @return ModelAndView
     */
    @GetMapping(value = "/visualizarLep")
    public ModelAndView telalep(String nome) {
        Administrador administrador = administradorRepository.findByIdAdmin(Usuario.IdUsu);

        ModelAndView mv = new ModelAndView("/menu/visualizarLep");
        mv.addObject("lep", lepRepository.findAll());
        mv.addObject("administrador", administrador);
        System.out.println(nome);

        if (nome != null) {
            System.out.println(nome);
            ArrayList<LEP> lep = lepRepository.findByNomeBloco(nome);

            if (lep.size() != 0) {

                List<LEP> lepTotais = new ArrayList<>();

                for (LEP leps : lep) {
                    lepTotais.add(leps);
                }

                mv.addObject("lep", lepTotais);

                return mv;
            }
            Iterable<LEP> leps = lepRepository.findAll();
            mv.addObject("lep", leps);
            return mv;
        }
        Iterable<LEP> leps = lepRepository.findAll();
        mv.addObject("lep", leps);
        return mv;
    }

}