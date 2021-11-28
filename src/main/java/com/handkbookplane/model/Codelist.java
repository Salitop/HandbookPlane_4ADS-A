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
 * Classe responsável por ser o modelo do Codelist, além de criar a tabela no banco de dados
 *
 * @Author: Luiz Miguel
 * Since: 23/11/2021
 */
@Entity
@Getter
@Setter


public class Codelist {

    @GeneratedValue
    private String secao;
    private String subsecao;
    private Integer nbloco;
    private String apelidoBloco; //Nome do bloco que será exibido no codelist
    private Integer code;
    private String remark; //Identificador do traço

    /**
     * Gerando campos do Bloco
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idCodelist;

    public Integer getIdCodelist() {
        return idCodelist;
    }

    public void setIdCodelist(Integer idCodelist) {
        this.idCodelist = idCodelist;
    }

    public String getApelidoBloco() {
        return apelidoBloco;
    }

    public void setApelidoBloco(String apelidoBloco) {
        this.apelidoBloco = apelidoBloco;
    }

    public String getSecao() {
        return secao;
    }

    public void setSecao(String secao) {
        this.secao = secao;
    }

    public String getSubsecao() {
        return subsecao;
    }

    public void setSubsecao(String subsecao) {
        this.subsecao = subsecao;
    }

    public Integer getNbloco() {
        return nbloco;
    }

    public void setNbloco(Integer nbloco) {
        this.nbloco = nbloco;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}