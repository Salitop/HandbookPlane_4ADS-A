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
 * Classe responsável por ser o modelo do bloco, além de criar a tabela no banco de dados
 *
 * @Author: Edryan Maciel
 * Since: 06/09/2021
 */
@Entity
@Getter
@Setter
public class Bloco {

    private String nomeBloco;
    @GeneratedValue
    private String secao;
    private String subsecao;
    private Integer nbloco;
    private Integer code;
    private Integer revisao;
    private String dataRev;
    private String remark;
    private byte[] PDF;
    private String PDF_string;
    private String descRevisao;

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

    public Integer getRevisao() {
        return revisao;
    }

    public void setRevisao(Integer revisao) {
        this.revisao = revisao;
    }

    public String getDataRev() {
        return dataRev;
    }

    public void setDataRev(String dataRev) {
        this.dataRev = dataRev;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public byte[] getPDF() {
        return PDF;
    }

    public void setPDF(byte[] PDF) {
        this.PDF = PDF;
    }

    public String getPDF_string() {
        return PDF_string;
    }

    public void setPDF_string(String PDF_string) {
        this.PDF_string = PDF_string;
    }

    public String getDescRevisao() {
        return descRevisao;
    }

    public void setDescRevisao(String descRevisao) {
        this.descRevisao = descRevisao;
    }
}