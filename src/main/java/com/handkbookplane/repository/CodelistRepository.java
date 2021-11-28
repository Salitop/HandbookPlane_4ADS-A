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
    //Procura Codelist pelo nome do bloco
    Iterable<Codelist> findByApelidoBloco(String apelidoBloco);

}