package com.handkbookplane.repository;

import com.handkbookplane.model.Bloco;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Interface responsável por comunicar com o banco de dados, utilizando funções básicas como:
 * save, delete, update etc
 *
 * @Author: Edryan maciel
 * @Since: 06/11/2021
 */
@Repository
public interface BlocoRepository extends CrudRepository<Bloco, String>

{
    //Procura Bloco por nome
    ArrayList<Bloco> findByNomeBloco(String nomeBloco);

    //Procura Bloco por Id
    Bloco findByIdBloco(Integer idBloco);

    Bloco findAllByNomeBloco(String nomeBloco);
}