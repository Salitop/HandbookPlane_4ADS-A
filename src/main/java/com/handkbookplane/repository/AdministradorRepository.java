package com.handkbookplane.repository;

import com.handkbookplane.model.Administrador;
import com.handkbookplane.model.Bloco;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Interface responsável por comunicar com o banco de dados, utilizando funções básicas como:
 * save, delete, update etc
 *
 * @Author: Kevin Melo
 * @Since: 20/09/2021
 */
@Repository
public interface AdministradorRepository extends CrudRepository<Administrador, String>

{
    //Procura Admin por Login
    Administrador findByLogin(String login);

    Administrador findByIdAdmin(Integer idAdmin);

    ArrayList<Administrador>  findByNome (String nome);
}