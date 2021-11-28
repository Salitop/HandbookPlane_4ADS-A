package com.handkbookplane.model;

import javax.persistence.GeneratedValue;
import java.io.Serializable;

public class CodelistControllerModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idCodelist;
    private String secao;
    private String subsecao;
    private Integer nbloco;
    private String apelidoBloco; //Nome do bloco que será exibido no codelist
    private Integer code;
    private byte[] PDF;
    private String PDF_string;
    private String remark; //Identificador do traço


    public Integer getIdCodelist() {
        return idCodelist;
    }

    public void setIdCodelist(Integer idCodelist) {
        this.idCodelist = idCodelist;
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

    public String getApelidoBloco() {
        return apelidoBloco;
    }

    public void setApelidoBloco(String apelidoBloco) {
        this.apelidoBloco = apelidoBloco;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}