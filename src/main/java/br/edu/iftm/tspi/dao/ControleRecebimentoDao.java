package br.edu.iftm.tspi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ControleRecebimentoDao {
    public Integer getUltimoLote(String tipoArquivo) throws Exception {
        Connection conexao = Conexao.getConnection();
        String sql = "select MAX(numlot) as maxLote "+ 
                     "from tbControleRecebimento "+
                     "where tiparq = ?";
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setString(1, tipoArquivo);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
           return rs.getInt(1);
        }
        throw new Exception("Não encontrei o último lote de cliente");
     }
 
     public void salvarLote(Integer lote, String tipoArquivo) throws Exception {
         Connection conexao = Conexao.getConnection();
         String sql = "INSERT INTO tbControleRecebimento(tiparq,numlot,dathraprc) "+
                      "values (?,?,now())";
         PreparedStatement ps = conexao.prepareStatement(sql);
         ps.setString(1, tipoArquivo);
         ps.setInt(2, lote);
         
         ps.execute();
     }
}
