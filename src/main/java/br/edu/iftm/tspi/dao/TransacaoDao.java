package br.edu.iftm.tspi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.edu.iftm.tspi.domain.Transacao;

public class TransacaoDao extends ControleRecebimentoDao {

    public void salvarTransacao(Transacao transacao) throws Exception {
        Connection connection = Conexao.getConnection();
        String sql = "INSERT IGNORE INTO tbTransacao(nrocta, nropla, vlrtra, dattra, codloj) " +
                     "VALUES (?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, transacao.getNumeroConta());
        ps.setString(2, transacao.getNumeroPlastico());
        ps.setDouble(3, transacao.getValorTransacao());
        
        // Combina data e hora
        String dataHoraStr = formatDataHora(transacao.getDataTransacao(), transacao.getHoraTransacao());
        Timestamp timestamp = Timestamp.valueOf(dataHoraStr);
        ps.setTimestamp(4, timestamp);
        
        ps.setString(5, transacao.getCodigoEstabelecimento());
        ps.execute();
    }
    

    private String formatDataHora(Date data, String hora) throws ParseException {
        SimpleDateFormat sdfData = new SimpleDateFormat("yyyy-MM-dd");
    
        String dataStr = sdfData.format(data);
        
        // Verifica se a hora é válida e formata corretamente
        String horaStr = hora != null && hora.length() == 6 ? hora.substring(0, 2) + ":" + hora.substring(2, 4) + ":" + hora.substring(4, 6) : "00:00:00";
        
        return dataStr + " " + horaStr;
    }
    

    public void atualizarTransacao(Transacao transacao) throws Exception {
        Connection connection = Conexao.getConnection();
        String sql = "UPDATE tbTransacao SET ";
        int sequencia = 1;

        if (transacao.isAtualizou(transacao.getValorTransacao().toString())) {
            sql += "vlrtra = ?,";
        }
        if (transacao.isAtualizou(transacao.getCodigoEstabelecimento())) {
            sql += "codest = ?,";
        }
        sql = sql.substring(0, sql.length() - 1);  // Remove a última vírgula
        sql += " WHERE nrocta = ? AND nropla = ? AND dattra = ? AND hortra = ?";

        PreparedStatement ps = connection.prepareStatement(sql);

        if (transacao.isAtualizou(transacao.getValorTransacao().toString())) {
            ps.setDouble(sequencia, transacao.getValorTransacao());
            sequencia++;
        }
        if (transacao.isAtualizou(transacao.getCodigoEstabelecimento())) {
            ps.setString(sequencia, transacao.getCodigoEstabelecimento());
            sequencia++;
        }

        ps.setString(sequencia, transacao.getNumeroConta());
        sequencia++;
        ps.setString(sequencia, transacao.getNumeroPlastico());
        sequencia++;
        ps.setDate(sequencia, new java.sql.Date(transacao.getDataTransacao().getTime()));
        sequencia++;
        ps.setString(sequencia, transacao.getHoraTransacao());
        ps.execute();
    }

}
