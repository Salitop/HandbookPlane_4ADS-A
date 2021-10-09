package com.handkbookplane.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.nio.ByteBuffer;
import java.util.UUID;

/**
 * Classe responsável por ser o modelo do administrador, além de criar a tabela no banco de dados
 *
 * @Author: Kevin Melo
 * Since: 20/09/2021
 */
@Entity
@Getter
@Setter
public class Administrador {

    /**
     * Gerando campos do Administrador
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idAdmin;
    private String nome;
    @GeneratedValue
    private String matricula;
    private String login;
    private String senha;

    /**
     * Gerando construtor do administrador
     */
    public Administrador(Integer idAdmin, String nome, UUID matricula, String login, String senha) {
        this.idAdmin = idAdmin;
        this.nome = nome;
        this.matricula = gerarUUIDpequeno();
        this.login = login;
        this.senha = senha;
    }

    /**
     * Gerando construtor do administrador default
     */
    public Administrador() {}

    /**
     * Método para gerar matricula do usuário
     * @return String
     */
    public static String gerarUUIDpequeno() {
        UUID uuid = UUID.randomUUID();
        long l = ByteBuffer.wrap(uuid.toString().getBytes()).getLong();
        return Long.toString(l, Character.MAX_RADIX);
    }
}