package com.handkbookplane.controller;

import com.handkbookplane.model.Administrador;
import com.handkbookplane.model.Traco;
import com.handkbookplane.model.Usuario;
import com.handkbookplane.repository.AdministradorRepository;
import com.handkbookplane.repository.TracoRepository;
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
public class EmitirRelatorioController {

    @Autowired
    AdministradorRepository administradorRepository;
    @Autowired
    TracoRepository tracoRepository;
    /**
     * Método responsável por dar um get na tela menu Traço
     * @return ModelAndView
     */
    @GetMapping(value = "/emitirRelatorio")
    public ModelAndView telaemitirRelatorio(String nome) {
        Administrador administrador = administradorRepository.findByIdAdmin(Usuario.IdUsu);

        ModelAndView mv = new ModelAndView("/tracos/emitirRelatorio");
        mv.addObject("traco", tracoRepository.findAll());
        mv.addObject("administrador", administrador);
        System.out.println(nome);
        if (nome != null) {
            System.out.println(nome);
            ArrayList<Traco> traco = tracoRepository.findByNomeTraco(nome);

            if (traco.size() != 0) {

                List<Traco> tracoTotais = new ArrayList<>();

                for (Traco tracos : traco) {


                    tracoTotais.add(tracos);
                }

                mv.addObject("traco", tracoTotais);

                return mv;
            }
            Iterable<Traco> tracos = tracoRepository.findAll();
            mv.addObject("traco", tracos);
            return mv;
        }
        Iterable<Traco> tracos = tracoRepository.findAll();
        mv.addObject("traco", tracos);
        return mv;

    }
}