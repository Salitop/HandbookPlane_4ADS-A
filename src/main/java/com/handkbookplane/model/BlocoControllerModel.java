package com.handkbookplane.model;

import javax.persistence.GeneratedValue;
import java.io.Serializable;

public class BlocoControllerModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idBloco;
    private String nomeBloco;
    private String secao;
    private String subsecao;
    private Integer nbloco;
    private Integer code;
    private Integer revisao;
    private String dataRev;
    private String remark;

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
}