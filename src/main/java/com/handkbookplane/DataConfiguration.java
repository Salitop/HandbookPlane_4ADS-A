package com.handkbookplane;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

/**
 * Classe de configuração da aplicação, responsável por administrar o banco de dados
 * Banco de dados: handbookplane
 * Usuário: root
 * Senha: password
 * MySQL
 *
 * @Author: Kevin Melo
 * @Since: 20/09/2021
 */

@Configuration
public class DataConfiguration {

    /**
     * Metódo responsável por configurar conexão com o banco MySQL
     * @return DataSource
     */
    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/handbookplane?useTimezone=true&serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("password");
        return dataSource;
    }

    /**
     * Metódo responsável por configurar conexão com o banco MySQL
     * @return adapter
     */
    @Bean
    public JpaVendorAdapter jpaVendorAdapter(){
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);
        adapter.setDatabasePlatform("org.hibernate.dialect.MariaDBDialect");
        adapter.setPrepareConnection(true);
        return adapter;
    }
}