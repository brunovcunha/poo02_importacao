package br.edu.iftm.tspi.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;

import br.edu.iftm.tspi.domain.Plastico;

public class PlasticoDao extends ControleRecebimentoDao {

    public void salvarPlastico(Plastico plastico) throws Exception {
        Connection connection = Conexao.getConnection();
        String sql = "INSERT IGNORE INTO tbPlastico(nropla,nrocta,nompla,cpfpla) " +
                     "VALUES (?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, plastico.getNumeroPlastico());
        ps.setString(2, plastico.getNumeroConta());
        ps.setString(3, plastico.getNome());
        ps.setString(4, plastico.getCpf());
        ps.execute();
    }

    public void atualizarPlastico(Plastico plastico) throws Exception {
        Connection connection = Conexao.getConnection();
        String sql = "UPDATE tbPlastico SET ";
        int sequencia = 1;

        if (plastico.isAtualizou(plastico.getNome())) {
            sql += "nompla = ?,";
        }
        if (plastico.isAtualizou(plastico.getCpf())) {
            sql += "cpfpla = ?,";
        }
        sql = sql.substring(0, sql.length() - 1);  // Remove a última vírgula
        sql += " WHERE nrocta = ? AND nropla = ?";

        PreparedStatement ps = connection.prepareStatement(sql);

        if (plastico.isAtualizou(plastico.getNome())) {
            ps.setString(sequencia, plastico.getNome());
            sequencia++;
        }
        if (plastico.isAtualizou(plastico.getCpf())) {
            ps.setString(sequencia, plastico.getCpf());
            sequencia++;
        }

        ps.setString(sequencia, plastico.getNumeroConta());
        sequencia++;
        ps.setString(sequencia, plastico.getNumeroPlastico());
        ps.execute();
    }

}
