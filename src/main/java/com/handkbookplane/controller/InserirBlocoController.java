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

import javax.persistence.GeneratedValue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
     *
     * @return ModelAndView
     */
    @Autowired
    AdministradorRepository administradorRepository;

    @Autowired
    BlocoRepository blocoRepository;

    /**
     * Método responsável por dar um get na tela menu Bloco
     *
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
    @RequestMapping(value = "/inserirBloco", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public String form(BlocoControllerModel bloco, @RequestParam(value = "PDF") MultipartFile PDF)
            throws IOException {
        BlocoRepository.save(BlocoMapper.converter(bloco, PDF));
        return "redirect:menuInicial";
    }

    @GetMapping(value = "/inserirBlocoCSV")
    public ModelAndView TelaInserirBlocoCSV() {

        Administrador administrador = administradorRepository.findByIdAdmin(Usuario.IdUsu);

        ModelAndView mv = new ModelAndView("/bloco/inserirBlocoCSV");
        mv.addObject("administrador", administrador);
        return mv;
    }

    @PostMapping(value = "/inserirBlocoCSV", consumes = {"multipart/form-data"})
    public String inserirBlocoCSV(@RequestParam(value = "file") MultipartFile CSV) throws IOException {

        Administrador administrador = administradorRepository.findByIdAdmin(Usuario.IdUsu);

        ModelAndView mv = new ModelAndView("/bloco/visualizarBloco");

        List<Bloco> blocos = new ArrayList<>();

        InputStream inputStreamReader = CSV.getInputStream();

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStreamReader))) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] atribbuites = line.split(",");

                Bloco bloco = createBloco(atribbuites);

                blocos.add(bloco);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        blocoRepository.saveAll(blocos);

        return "redirect:";
    }

    private Bloco createBloco(String[] metadata) {

        String nomeBloco = metadata[0];
        String secao = metadata[1];
        String subsecao = metadata[2];
        String nbloco = metadata[3];
        String code = metadata[4];
        String revisao = metadata[5];
        String dataRev = metadata[6];
        String remark = metadata[7];

        Bloco bloco = new Bloco();
        bloco.setNomeBloco(nomeBloco);
        bloco.setSecao(secao);
        bloco.setSubsecao(subsecao);
        bloco.setNbloco(Integer.parseInt(nbloco));
        bloco.setCode(Integer.parseInt(code));
        bloco.setRevisao(Integer.parseInt(revisao));
        bloco.setDataRev(dataRev);

        return bloco;
    }

}