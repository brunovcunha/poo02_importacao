package br.edu.iftm.tspi.service;

import br.edu.iftm.tspi.dao.ControleRecebimentoDao;

public class ProcessaCabecalho {

    private ControleRecebimentoDao dao;

    public void processaCabecalho(String linha, String tipoArquivo) throws Exception {
        Integer lote = Integer.parseInt(linha.substring(1, 4));
        Integer loteBanco = dao.getUltimoLote(tipoArquivo);
        Integer loteEsperado = loteBanco + 1;
        if (!lote.equals(loteEsperado)) {
            throw new Exception("Lote recebido: " + lote +
                    "diferente do lote esperado:" + loteEsperado);
        }
        this.lote = lote;
    }

}
