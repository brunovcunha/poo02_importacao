package br.edu.iftm.tspi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.edu.iftm.tspi.domain.Conta;

public class ContaDao extends ControleRecebimentoDao {

    public void salvarConta(Conta conta) throws Exception {
        Connection connection = Conexao.getConnection();
        String sql = "INSERT INTO tbConta(nrocta,cpfcli,"+
                     "vlrlim,diaven) "+
                     " values (?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1,conta.getNumero());
        ps.setString(2,conta.getCliente().getCpf());
        ps.setDouble(3,conta.getValorLimite());
        ps.setInt(4,conta.getDiaVencimento());
        ps.execute();
    }

    public void atualizarConta(Conta conta) throws Exception {
        Connection connection = Conexao.getConnection();
        String sql = "UPDATE tbConta set ";
        int sequencia = 1;
        
        if (conta.getDiaVencimento() != null && conta.isAtualizou(conta.getDiaVencimento().toString())) {
            sql += " diaven = ?,";
        }
        if (conta.getValorLimite() != null && conta.isAtualizou(conta.getValorLimite().toString())) {
            sql += " vlrlim = ?,";          
        }
        if (conta.isAtualizou(conta.getCliente().getCpf())) {
            sql += " cpfcli = ?,";        
        }
        
        sql = sql.substring(0, sql.length() - 1);
        sql += "where nrocta = ?";

        PreparedStatement ps = connection.prepareStatement(sql);

        if (conta.getDiaVencimento() != null && conta.isAtualizou(conta.getDiaVencimento().toString())) {
            ps.setInt(sequencia,conta.getDiaVencimento());
            sequencia++;
        }
        if (conta.getValorLimite() != null && conta.isAtualizou(conta.getValorLimite().toString())) {
            ps.setDouble(sequencia,conta.getValorLimite());
            sequencia++;            
        }
        if (conta.isAtualizou(conta.getCliente().getCpf())) {
            ps.setString(sequencia,conta.getCliente().getCpf());
            sequencia++;            
        }

        ps.setString(sequencia,conta.getNumero());
        ps.execute();
    }

    public void persistir(Conta conta) throws Exception {
        if (conta.getInclusaoAlteracao().equals("I")) {
            salvarConta(conta);
        } else if (conta.getInclusaoAlteracao().equals("A")) {
            atualizarConta(conta);
        } else {
            throw new Exception("desconheço essa opção de inclusão alteração:"
                                +conta.getInclusaoAlteracao());
        }
    }

}
