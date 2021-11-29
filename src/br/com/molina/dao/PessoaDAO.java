package br.com.molina.dao;

import br.com.molina.dto.PessoaDTO;
import br.com.molina.jdbc.ConexaoUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;


public class PessoaDAO {

    public void inserir(PessoaDTO pessoaDTO)  {
        try {

            Connection connection = ConexaoUtil.getInstance().getConnection();

            String sql = "INSERT INTO PESSOA(ID_PESSOA, NOME) VALUES(?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, pessoaDTO.getId_pessoa());
            statement.setString(2, pessoaDTO.getNome());
            statement.execute();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
