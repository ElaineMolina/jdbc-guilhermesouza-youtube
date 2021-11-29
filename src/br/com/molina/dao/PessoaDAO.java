package br.com.molina.dao;

import br.com.molina.dto.PessoaDTO;
import br.com.molina.jdbc.ConexaoUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class PessoaDAO {

    public void inserir(PessoaDTO pessoaDTO)  {
        try {

            Connection connection = ConexaoUtil.getInstance().getConnection();

            String sql = "INSERT INTO PESSOA(NOME) VALUES(?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, pessoaDTO.getNome());
            statement.execute();
            connection.close();

            System.out.println("Inserido com sucesso");

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void remover(int idPessoa){
        try{
            Connection connection = ConexaoUtil.getInstance().getConnection();
            String sql = "DELETE FROM PESSOA WHERE ID_PESSOA = ?";
            PreparedStatement statement= connection.prepareStatement(sql);
            statement.setInt(1, idPessoa);
            statement.execute();
            statement.close();

            System.out.println("Removido com sucesso");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<PessoaDTO> listarTodos(){
        List<PessoaDTO> listaPessoas = new ArrayList<PessoaDTO>();
        try{
            Connection connection = ConexaoUtil.getInstance().getConnection();
            String sql = "SELECT * FROM PESSOA";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultset = statement.executeQuery();
            while(resultset.next()){
                PessoaDTO pessoaDTO = new PessoaDTO();
                pessoaDTO.setId_pessoa(resultset.getInt("ID_PESSOA"));
                pessoaDTO.setNome(resultset.getString("NOME"));

                listaPessoas.add(pessoaDTO);
            }
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return listaPessoas;
    }

    public void atualizar(PessoaDTO pessoaDTO){
        try {
            Connection connection = ConexaoUtil.getInstance().getConnection();
            String sql = "UPDATE PESSOA SET NOME = ? WHERE ID_PESSOA = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, pessoaDTO.getNome());
            statement.setInt(2, pessoaDTO.getId_pessoa());
            statement.execute();
            statement.close();

            System.out.println("Atualizado com sucesso");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args){

        PessoaDAO pessoaDAO = new PessoaDAO();
        List<PessoaDTO> listaPessoas = new ArrayList<>();
        listaPessoas = pessoaDAO.listarTodos();
        for(int i = 0; i < listaPessoas.size(); i++){
            System.out.println(listaPessoas.get(i));
        }

    }


}
