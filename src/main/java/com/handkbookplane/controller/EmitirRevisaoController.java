package com.handkbookplane.controller;

import com.handkbookplane.model.Administrador;
import com.handkbookplane.model.Bloco;
import com.handkbookplane.model.Usuario;
import com.handkbookplane.model.LEP;
import com.handkbookplane.repository.AdministradorRepository;
import com.handkbookplane.repository.BlocoRepository;
import com.handkbookplane.repository.LepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Classe responsável por controlar as funções do sistema
 *
 * @Author: Luiz Miguel
 * @Since: 10/10/2021
 */
@Controller
public class EmitirRevisaoController {

    @Autowired
    AdministradorRepository administradorRepository;

    @Autowired
    BlocoRepository blocoRepository;

    @Autowired
    LepRepository lepRepository;

    /**
     * Método responsável por dar um get na tela menu Traço
     *
     * @return ModelAndView
     */
    @GetMapping(value = "/emitirRevisao")
    public ModelAndView telaemitirRevisao() {
        Administrador administrador = administradorRepository.findByIdAdmin(Usuario.IdUsu);

        ModelAndView mv = new ModelAndView("/bloco/emitirRevisao");

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
//PÁGINA PARA CADASTRAR A REVISÃO

    @GetMapping(value = "/revisaoBloco/{idBloco}")
    public ModelAndView telarevisaoBloco(Integer idBloco) {
        Administrador administrador = administradorRepository.findByIdAdmin(Usuario.IdUsu);

        ModelAndView mv = new ModelAndView("/bloco/revisaoBloco");

        Bloco bloco = blocoRepository.findByIdBloco(idBloco);

        mv.addObject("bloco", bloco);

        mv.addObject("administrador", administrador);
        return mv;
    }

    @PostMapping(value = "/revisaoBloco/{idBloco}")
    public ModelAndView cadRevisao(Bloco bloco,LEP lep, Integer idBloco, Integer acao) {

        Administrador administrador = administradorRepository.findByIdAdmin(Usuario.IdUsu);

        //Cadastrando a revisão
        ModelAndView modelAndView = new ModelAndView();
        Bloco blocoAlterado = blocoRepository.findByIdBloco(idBloco);
        blocoAlterado.setDescRevisao(bloco.getDescRevisao());
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String dateFormatado = date.format(formatter);
        blocoAlterado.setDataRev(dateFormatado);
        modelAndView.addObject("bloco", blocoRepository.save(blocoAlterado));

        lep.setIdBloco(blocoAlterado.getIdBloco());
        lep.setNbloco(blocoAlterado.getNbloco());
        lep.setNomeBloco(blocoAlterado.getNomeBloco());

        //Cadastrando na LEP
        if(acao == 1)
        {
            lep.setAcao("* new"); // Adicionar
        }
        else
        {
           if (acao == 2)
           {
             lep.setAcao("*"); // Editar
           }
           else
           {
                lep.setAcao("* del"); // Deletar
           }
        }
        modelAndView.addObject("lep", lepRepository.save(lep));



        modelAndView.setViewName("/bloco/revisaoBloco");

        modelAndView.addObject("administrador", administrador);

        return modelAndView;
    }
}