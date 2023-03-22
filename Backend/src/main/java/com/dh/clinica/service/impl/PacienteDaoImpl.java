package com.dh.clinica.service.impl;

import com.dh.clinica.config.ConfiguracaoJDBC;
import com.dh.clinica.model.Endereco;
import com.dh.clinica.model.Paciente;
import com.dh.clinica.model.Usuario;
import com.dh.clinica.service.EnderecoService;
import com.dh.clinica.service.IDao;
import org.springframework.stereotype.Service;
import util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteDaoImpl implements IDao<Paciente> {

    private ConfiguracaoJDBC configuracaoJDBC;
    public PacienteDaoImpl(){
        this.configuracaoJDBC = new ConfiguracaoJDBC();
    }
    @Override
    public Paciente salvar(Paciente paciente) {

        Connection connection = configuracaoJDBC.conectaBancoDeDados();
        Statement statement = null;
        String query = String.format("INSERT INTO PACIENTE (NOME ,SOBRENOME, RG, DATA_ALTA,ENDERECO_ID) " +
                        "VALUES ('%s','%s','%s','%s','%s')", paciente.getNome(),paciente.getSobrenome(),paciente.getRg(), Util.dateToTimestamp(paciente.getDataAlta()),paciente.getEndereco().getId());
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next())
                paciente.setId(keys.getInt(1));
            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return paciente;
    }

    @Override
    public List<Paciente> buscarTodos() {
        List<Paciente> listaUsuarios = new ArrayList<>();
        Connection connection = configuracaoJDBC.conectaBancoDeDados();
        Statement statement = null;
        String query = "SELECT * FROM PACIENTE";
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                listaUsuarios.add(criarPaciente(resultSet));
            }
            connection.close();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listaUsuarios;
    }

    @Override
    public Optional<Paciente> buscaPorId(Integer id) {
        Connection connection = configuracaoJDBC.conectaBancoDeDados();
        Statement statement = null;
        Paciente paciente = null;
        String query = String.format("SELECT * FROM PACIENTE WHERE ID='%s'",id);
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                paciente = criarPaciente(resultSet);
            }
            connection.close();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return paciente != null ? Optional.of(paciente) : Optional.empty();
    }

    @Override
    public void excluirID(Integer id) {
        Connection connection = configuracaoJDBC.conectaBancoDeDados();
        Statement statement = null;
        String query = String.format("DELETE FROM PACIENTE WHERE ID='%s'", id);
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
    public Paciente atualizar(Paciente paciente) {
        Connection connection = configuracaoJDBC.conectaBancoDeDados();
        Statement statement = null;
        String query = String.format("UPDATE PACIENTE SET NOME = '%s', SOBRENOME = '%s', RG = '%s' WHERE ID = '%s'",
                paciente.getNome(),paciente.getSobrenome(),paciente.getRg(),paciente.getId());
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return paciente;
    }


    public Paciente criarPaciente(ResultSet resultSet) throws SQLException {
        EnderecoDaoImpl enderecoDao = new EnderecoDaoImpl();
        Integer id = resultSet.getInt(1);
        String nome = resultSet.getString(2);
        String sobrenome = resultSet.getString(3);
        String rg = resultSet.getString(4);
        Date dataAlta = resultSet.getDate(5);
        Endereco endereco = enderecoDao.buscaPorId(resultSet.getInt(6)).orElse(null);
        return new Paciente(id,nome,sobrenome,rg,dataAlta,endereco);
    }
}
