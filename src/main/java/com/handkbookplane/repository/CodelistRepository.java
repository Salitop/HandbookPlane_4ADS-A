package com.handkbookplane.repository;

import com.handkbookplane.model.Codelist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface responsável por comunicar com o banco de dados, utilizando funções básicas como:
 * save, delete, update etc
 *
 * @Author: Luiz Miguel
 * @Since: 23/11/2021
 */
@Repository
public interface CodelistRepository extends CrudRepository<Codelist, String>

{
    Iterable<Codelist> findByApelidoBloco(String apelidoBloco); //Procura Codelist pelo nome do bloco
    Codelist findAllByApelidoBloco(String apelidoBloco); //Procura todos os blocos pelo seu nome
}
