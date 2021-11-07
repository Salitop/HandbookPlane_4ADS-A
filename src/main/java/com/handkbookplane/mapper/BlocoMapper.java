package com.handkbookplane.mapper;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.handkbookplane.model.Bloco;
import com.handkbookplane.model.BlocoControllerModel;

public class BlocoMapper {

   public static Bloco converter(BlocoControllerModel bloco, MultipartFile pdf) throws IOException {

        var blocoBanco = new Bloco();
        blocoBanco.setNomeBloco(bloco.getNomeBloco());
        blocoBanco.setRemark(bloco.getRemark());
        blocoBanco.setRevisao(bloco.getRevisao());
        blocoBanco.setCode(bloco.getCode());
        blocoBanco.setDataRev(bloco.getDataRev());
        blocoBanco.setNbloco(bloco.getNbloco());
        blocoBanco.setSecao(bloco.getSecao());
        blocoBanco.setSubsecao(bloco.getSubsecao());
        blocoBanco.setPDF(pdf.getBytes());




        return blocoBanco;
    }
}

