package br.edu.iftm.tspi.service;

import br.edu.iftm.tspi.dao.ControleRecebimentoDao;

public class ProcessaCabecalho {

    private Integer lote;

    public void processaCabecalho(String linha, String tipoArquivo, ControleRecebimentoDao dao) throws Exception {
        Integer lote = Integer.parseInt(linha.substring(1, 4));
        Integer loteBanco = dao.getUltimoLote(tipoArquivo);
        Integer loteEsperado = loteBanco + 1;
        if (!lote.equals(loteEsperado)) {
            throw new Exception("Lote recebido: " + lote +
                    "diferente do lote esperado:" + loteEsperado);
        }
        this.lote = lote;
    }

    public Integer getLote() {
        return lote;
    }

}
