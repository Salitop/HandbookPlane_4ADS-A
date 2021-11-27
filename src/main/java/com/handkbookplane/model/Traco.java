package com.handkbookplane.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

    @GeneratedValue
    private String nomeTraco;
    private String remark;
    private String nomeBloco;
    private Integer codeBloco;
    private byte[] PDF;
    private String PDF_string;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idTraco;

    public Integer getIdTraco() {
        return idTraco;
    }

    public void setIdTraco(Integer idTraco) {
        this.idTraco = idTraco;
    }

    public String getNomeTraco() {
        return nomeTraco;
    }

    public void setNomeTraco(String nomeTraco) {
        this.nomeTraco = nomeTraco;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

}