package com.dh.clinica.repository;

import com.dh.clinica.config.ConfiguracaoJDBC;
import com.dh.clinica.model.Usuaio;
import com.dh.clinica.service.IDao;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class DentistaDaoImpl implements IDao<Usuaio> {




    private ConfiguracaoJDBC configuracaoJDBC;
    final static Logger logger = Logger.getLogger(DentistaDaoImpl.class);

    public DentistaDaoImpl(){
        this.configuracaoJDBC = new ConfiguracaoJDBC();

    }


    public Usuaio salvar(Usuaio dentista) {

        Connection connection = configuracaoJDBC.conectaBancoDeDados();
        Statement statement = null;
        String query = String.format("INSERT INTO DENTISTA (NOME ,SOBRENOME, MATRICULA) " +
                "VALUES ('%s','%s','%s')", dentista.getNome(), dentista.getSobrenome(), dentista.getMatricula());
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next())
                dentista.setId(keys.getInt(1));
            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return dentista;
    }

    @Override
    public List<Usuaio> buscarTodos() {
        List<Usuaio> listaDentistas = new ArrayList<>();
        Connection connection = configuracaoJDBC.conectaBancoDeDados();
        Statement statement = null;
        String query = "SELECT * FROM DENTISTA";
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                listaDentistas.add(criarDentista(resultSet));
            }
            connection.close();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listaDentistas;
    }

    @Override
    public Optional<Usuaio> buscaPorId(Integer id){
        Connection connection = configuracaoJDBC.conectaBancoDeDados();
        Statement statement = null;
        Usuaio dentista = null;
        String query = String.format("SELECT * FROM DENTISTA WHERE ID='%s'", id);
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                dentista = criarDentista(resultSet);
            }
            connection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dentista != null ? Optional.of(dentista) : Optional.empty();
    }

    @Override
    public void excluirID(Integer id) {
        Connection connection = configuracaoJDBC.conectaBancoDeDados();
        Statement statement = null;
        String query = String.format("DELETE FROM DENTISTA WHERE ID='%s'", id);
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.close();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    @Override
    public Usuaio atualizar(Usuaio dentista) {
        // logger.debug("Atualizando um paciente: " + dentista.toString());
        Connection connection = configuracaoJDBC.conectaBancoDeDados();
        Statement statement = null;
        String query = String.format(
                "UPDATE DENTISTA SET NOME = '%s', SOBRENOME = '%s', MATRICULA = '%s' WHERE ID = '%s'",
                dentista.getNome(), dentista.getSobrenome(), dentista.getMatricula(), dentista.getId());
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return dentista;
    }

    public Usuaio criarDentista(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt(1);
        String nome = resultSet.getString(2);
        String sobrenome = resultSet.getString(3);
        String matricula = resultSet.getString(4);
        return new Usuaio(id, nome, sobrenome, matricula);
    }

}
