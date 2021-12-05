package com.handkbookplane.repository;
import com.handkbookplane.model.LEP;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Interface responsável por comunicar com o banco de dados, utilizando funções básicas como:
 * save, delete, update etc
 *
 * @Author: Luiz Miguel
 * @Since: 05/12/2021
 */
@Repository
public interface LepRepository extends CrudRepository<LEP, String>

{
}