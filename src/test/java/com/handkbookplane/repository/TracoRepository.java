package com.handkbookplane.repository;

import com.handkbookplane.model.Traco;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface responsável por comunicar com o banco de dados, utilizando funções básicas como:
 * save, delete, update etc
 *
 * @Author: Luiz Miguel
 * @Since: 24/11/2021
 */
@Repository
public interface TracoRepository extends CrudRepository<Traco, String> {

    Traco findByNomeTraco(String nomeTraco);

}