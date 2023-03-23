package com.dh.clinica.service.impl;

import com.dh.clinica.config.ConfiguracaoJDBC;
import com.dh.clinica.model.Endereco;
import com.dh.clinica.service.IDao;
import org.springframework.stereotype.Service;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EnderecoDaoImpl implements IDao<Endereco> {
    private ConfiguracaoJDBC configuracaoJDBC;

    public EnderecoDaoImpl() {
        this.configuracaoJDBC = new ConfiguracaoJDBC();
    }

    @Override
    public Endereco salvar(Endereco endereco) {
        Connection connection = configuracaoJDBC.conectaBancoDeDados();
        Statement statement = null;
        String query = String.format("INSERT INTO ENDERECO (RUA ,NUMERO, BAIRRO, CIDADE) " +
                "VALUES ('%s','%s','%s','%s')", endereco.getRua(), endereco.getNumero(), endereco.getBairro(),
                endereco.getCidade());
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next())
                endereco.setId(keys.getInt(1));
            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return endereco;
    }

    @Override
    public List<Endereco> buscarTodos() {
        List<Endereco> listaEnderecos = new ArrayList<>();
        Connection connection = configuracaoJDBC.conectaBancoDeDados();
        Statement statement = null;
        String query = "SELECT * FROM ENDERECO";
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                listaEnderecos.add(criarEndereco(resultSet));
            }
            connection.close();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listaEnderecos;
    }

    @Override
    public Optional<Endereco> buscaPorId(Integer id) {
        Connection connection = configuracaoJDBC.conectaBancoDeDados();
        Statement statement = null;
        Endereco endereco = null;
        String query = String.format("SELECT * FROM ENDERECO WHERE ID = '%s'", +id);
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                endereco = criarEndereco(resultSet);
            }
            connection.close();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return endereco != null ? Optional.of(endereco) : Optional.empty();
    }

    @Override
    public void excluirID(Integer id) {
        Connection connection = configuracaoJDBC.conectaBancoDeDados();
        Statement statement = null;
        String query = String.format("DELETE FROM ENDERECO WHERE ID='%s'", id);
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
    public Endereco atualizar(Endereco endereco) {
        Connection connection = configuracaoJDBC.conectaBancoDeDados();
        Statement statement = null;
        String query = String.format(
                "UPDATE ENDERECO SET RUA = '%s', NUMERO = '%s', BAIRRO = '%s', CIDADE = '%s' WHERE ID = '%s'",
                endereco.getRua(), endereco.getNumero(), endereco.getBairro(), endereco.getCidade(), endereco.getId());
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return endereco;
    }

    public Endereco criarEndereco(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt(1);
        String rua = resultSet.getString(2);
        String numero = resultSet.getString(3);
        String bairro = resultSet.getString(4);
        String cidade = resultSet.getString(5);
        return new Endereco(id, rua, numero, bairro, cidade);
    }
}
