package br.edu.iftm.tspi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import br.edu.iftm.tspi.domain.Cliente;

public class ClienteDao extends ControleRecebimentoDao{

    public void salvarCliente(Cliente cliente) throws Exception {
        Connection connection = Conexao.getConnection();
        String sql = "INSERT IGNORE INTO tbCliente(cpfcli,nomcli,"+
                     "endcli,baicli,cidcli,sigest,datcad) "+
                     " values (?,?,?,?,?,?,now())";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1,cliente.getCpf());
        ps.setString(2,cliente.getNome());
        ps.setString(3,cliente.getEndereco());
        ps.setString(4,cliente.getBairro());
        ps.setString(5,cliente.getCidade());
        ps.setString(6,cliente.getEstado());
        ps.execute();
    }

    public void atualizarCliente(Cliente cliente) throws Exception {
        Connection connection = Conexao.getConnection();
        String sql = "UPDATE tbCliente set ";
        int sequencia = 1;
        
        if (cliente.isAtualizou(cliente.getNome())) {
            sql += " nomcli = ?,";
        }
        if (cliente.isAtualizou(cliente.getEndereco())) {
            sql += " endcli = ?,";          
        }
        if (cliente.isAtualizou(cliente.getBairro())) {
            sql += " baicli = ?,";        
        }
        if (cliente.isAtualizou(cliente.getCidade())) {
            sql += " cidcli = ?,";         
        }
        if (cliente.isAtualizou(cliente.getEstado())) {
            sql += " sigest = ? ,";          
        }
        
        sql = sql.substring(0, sql.length() - 1);
        sql += "where cpfcli = ?";

        PreparedStatement ps = connection.prepareStatement(sql);

        if (cliente.isAtualizou(cliente.getNome())) {
            ps.setString(sequencia,cliente.getNome());
            sequencia++;
        }
        if (cliente.isAtualizou(cliente.getEndereco())) {
            ps.setString(sequencia,cliente.getEndereco());
            sequencia++;            
        }
        if (cliente.isAtualizou(cliente.getBairro())) {
            ps.setString(sequencia,cliente.getBairro());
            sequencia++;            
        }
        if (cliente.isAtualizou(cliente.getCidade())) {
            ps.setString(sequencia,cliente.getCidade());
            sequencia++;            
        }
        if (cliente.isAtualizou(cliente.getEstado())) {
            ps.setString(sequencia,cliente.getEstado());
            sequencia++;            
        }

        ps.setString(sequencia,cliente.getCpf());
        ps.execute();
    }

    public void persistir(Cliente cliente) throws Exception {
        if (cliente.getInclusaoAlteracao().equals("I")) {
            salvarCliente(cliente);
        } else if (cliente.getInclusaoAlteracao().equals("A")) {
            atualizarCliente(cliente);
        } else {
            throw new Exception("desconheço essa opção de inclusão alteração:"
                                +cliente.getInclusaoAlteracao());
        }
    }

}
