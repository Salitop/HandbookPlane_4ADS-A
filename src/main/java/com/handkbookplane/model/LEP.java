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
 * Classe responsável por ser o modelo do LEP, além de criar a tabela no banco de dados
 *
 * @Author: Luiz Miguel
 * Since: 05/12/2021
 */
@Entity
@Getter
@Setter
public class LEP {

    private String nomeBloco;
    private String nomeTraco;
    @GeneratedValue
    private String secao;
    private Integer nbloco;
    private String acao;
    private String data;

    /**
     * Gerando campos do Bloco
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idBloco;

    public Integer getIdBloco() {
        return idBloco;
    }

    public void setIdBloco(Integer idBloco) {
        this.idBloco = idBloco;
    }

    public String getNomeBloco() {
        return nomeBloco;
    }

    public void setNomeBloco(String nomeBloco) {
        this.nomeBloco = nomeBloco;
    }

    public String getNomeTraco() {
        return nomeTraco;
    }

    public void setNomeTraco(String nomeTraco) {
        this.nomeTraco = nomeTraco;
    }


    public String getSecao() {
        return secao;
    }

    public void setSecao(String secao) {
        this.secao = secao;
    }

    public Integer getNbloco() {
        return nbloco;
    }

    public void setNbloco(Integer nbloco) {
        this.nbloco = nbloco;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


}