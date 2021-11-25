package com.handkbookplane.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.UUID;

/**
 * Classe responsável por ser o modelo do traço, além de criar a tabela no banco de dados
 *
 * @Author: Luiz Miguel
 * Since: 24/11/2021
 */
@Entity
@Getter
@Setter


public class Traco {

    private String NomeTraço;
    private String remark;
    @GeneratedValue
    private String nomeBloco;
    private Integer codeBloco;


    /**
     * Gerando campos do Bloco
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idTraco;

    public Integer getIdTraco() {
        return idTraco;
    }

    public void setIdTraco(Integer idTraco) {
        this.idTraco = idTraco;
    }

    public String getNomeBloco() {
        return nomeBloco;
    }

    public void setNomeBloco(String nomeBloco) {
        this.nomeBloco = nomeBloco;
    }

    public Integer getCodeBloco() {
        return codeBloco;
    }

    public void setCodeBloco(Integer codeBloco) {
        this.codeBloco = codeBloco;
    }
}