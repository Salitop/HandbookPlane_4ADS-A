package com.handkbookplane.repository;

import com.handkbookplane.model.Bloco;
import com.handkbookplane.model.Codelist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

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
    ArrayList<Codelist> findByApelidoBloco(String apelidoBloco);

}