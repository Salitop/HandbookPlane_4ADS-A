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
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * Classe responsável por controlar as funções do sistema
 *
 * @Author: Luiz Miguel
 * @Since: 28/10/2021
 */
@Controller
public class ListarTracoController {

    @Autowired
    AdministradorRepository administradorRepository;
    @Autowired
    TracoRepository tracoRepository;
    /**
     * Método responsável por dar um get na tela menu Traço
     * @return ModelAndView
     */

    @GetMapping(value = "/listarTraco")
    public ModelAndView telaListarTraco(String nome) {

        ModelAndView modelAndView = new ModelAndView("/tracos/listarTraco");
        modelAndView.addObject("traco", tracoRepository.findAll());
        Administrador administrador = administradorRepository.findByIdAdmin(Usuario.IdUsu);
        modelAndView.addObject("administrador", administrador);
        System.out.println(nome);
        if (nome != null) {
            System.out.println(nome);
            ArrayList<Traco> traco = tracoRepository.findByNomeTraco(nome);

            if (traco.size() != 0) {

                List<Traco> tracoTotais = new ArrayList<>();

                for (Traco tracos : traco) {


                    tracoTotais.add(tracos);
                }

                modelAndView.addObject("traco", tracoTotais);

                return modelAndView;
            }
            Iterable<Traco> tracos = tracoRepository.findAll();
            modelAndView.addObject("traco", tracos);
            return modelAndView;
        }
        Iterable<Traco> tracos = tracoRepository.findAll();
        modelAndView.addObject("traco", tracos);
        return modelAndView;
    }

    @GetMapping(value = "/visualizarTracoPDF/{id_traco}")
    public ModelAndView telavisualizarBlocoPDF(Integer idTraco) {
        Administrador administrador = administradorRepository.findByIdAdmin(Usuario.IdUsu);

        if(idTraco != null)
        {
            ModelAndView mv = new ModelAndView("/tracos/visualizarTracoPDF");

            Traco traco = tracoRepository.findByIdTraco(idTraco);

            String pdf = Base64.getEncoder().encodeToString(traco.getPDF());
            traco.setPDF_string(pdf);

            mv.addObject("traco", traco);

            mv.addObject("administrador", administrador);
            return mv;
        }
        else
        {
            ModelAndView mv = new ModelAndView("/tracos/menuTraco.html");
            mv.addObject("administrador", administrador);
            return mv;
        }
    }
}