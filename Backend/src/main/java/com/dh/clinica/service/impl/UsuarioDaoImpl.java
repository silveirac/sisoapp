package com.dh.clinica.service.impl;

import com.dh.clinica.config.ConfiguracaoJDBC;
import com.dh.clinica.model.Usuario;
import com.dh.clinica.service.IDao;
import org.springframework.stereotype.Service;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioDaoImpl implements IDao<Usuario> {

    private ConfiguracaoJDBC configuracaoJDBC;

    public UsuarioDaoImpl() {
        this.configuracaoJDBC = new ConfiguracaoJDBC();
    }

    @Override
    public Usuario salvar(Usuario usuario) {

        Connection connection = configuracaoJDBC.conectaBancoDeDados();
        Statement statement = null;
        String query = String.format("INSERT INTO USUARIO (NOME ,EMAIL, SENHA, NIVEL_ACESSO) " +
                "VALUES ('%s','%s','%s','%s')", usuario.getNome(), usuario.getEmail(), usuario.getSenha(),
                usuario.getNivelAcesso());
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next())
                usuario.setId(keys.getInt(1));
            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return usuario;
    }

    @Override
    public List<Usuario> buscarTodos() {
        List<Usuario> listaUsuarios = new ArrayList<>();
        Connection connection = configuracaoJDBC.conectaBancoDeDados();
        Statement statement = null;
        String query = "SELECT * FROM USUARIO";
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                listaUsuarios.add(criarUsuario(resultSet));
            }
            connection.close();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listaUsuarios;
    }

    @Override
    public Optional<Usuario> buscaPorId(Integer id) {
        Connection connection = configuracaoJDBC.conectaBancoDeDados();
        Statement statement = null;
        Usuario usuario = null;
        String query = String.format("SELECT * FROM USUARIO WHERE ID='%s'", id);
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                usuario = criarUsuario(resultSet);
            }
            connection.close();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return usuario != null ? Optional.of(usuario) : Optional.empty();
    }

    @Override
    public void excluirID(Integer id) {
        Connection connection = configuracaoJDBC.conectaBancoDeDados();
        Statement statement = null;
        String query = String.format("DELETE FROM USUARIO WHERE ID='%s'", id);
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
    public Usuario atualizar(Usuario usuario) {
        Connection connection = configuracaoJDBC.conectaBancoDeDados();
        Statement statement = null;
        String query = String.format(
                "UPDATE USUARIO SET NOME = '%s', EMAIL = '%s', SENHA = '%s', NIVEL_ACESSO = '%s' WHERE ID = '%s'",
                usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.getNivelAcesso(), usuario.getId());
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return usuario;
    }

    public Usuario criarUsuario(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt(1);
        String nome = resultSet.getString(2);
        String email = resultSet.getString(3);
        String senha = resultSet.getString(4);
        String nivelAcesso = resultSet.getString(5);
        return new Usuario(id, nome, email, senha, nivelAcesso);
    }
}
