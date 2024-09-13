package br.edu.iftm.tspi.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import br.edu.iftm.tspi.dao.TransacaoDao;
import br.edu.iftm.tspi.domain.Transacao;

public class ProcessaLinhasTransacao extends ProcessaCabecalho implements ProcessaLinha {

    private TransacaoDao transacaoDao;
    private String tipoArquivo = "TRA";

    public ProcessaLinhasTransacao() {
        transacaoDao = new TransacaoDao();
    }

    @Override
    public void processa(List<String> lines) throws Exception {
        for (String linha : lines) {
            String opcao = linha.substring(0, 1);
            if (opcao.equals("2")) {
                processaDetalhe(linha);
            } else if (opcao.equals("1")) {
                processaCabecalho(linha, tipoArquivo, transacaoDao);
            } else {
                throw new Exception("Desconheço essa opção de processar a linha: " + opcao);
            }
        }
        transacaoDao.salvarLote(getLote(), tipoArquivo);
    }

    private void processaDetalhe(String linha) throws Exception {
        Transacao transacao = getTransacao(linha);
        System.out.println("Transacao a ser salva: " + transacao);
        transacaoDao.salvarTransacao(transacao);
    }
    

    private Transacao getTransacao(String linha) throws ParseException {
        Transacao transacao = new Transacao();
        
        transacao.setNumeroConta(linha.substring(2, 9).trim()); 
        transacao.setNumeroPlastico(linha.substring(9, 16).trim()); 
        
        String valorTransacaoStr = linha.substring(16, 28).trim();
        double valorTransacao = Double.parseDouble(valorTransacaoStr) / 100.0;
        transacao.setValorTransacao(valorTransacao); 
        
        SimpleDateFormat sdfData = new SimpleDateFormat("yyyyMMdd");
        String dataTransacaoStr = linha.substring(28, 36).trim();
        Date dataTransacao = sdfData.parse(dataTransacaoStr);
        transacao.setDataTransacao(dataTransacao);
        
        String horaTransacaoStr = linha.substring(36, 42).trim();
        String horaTransacao = horaTransacaoStr.substring(0, 2) + ":" + horaTransacaoStr.substring(2, 4) + ":" + horaTransacaoStr.substring(4, 6);
        transacao.setHoraTransacao(horaTransacao);
        
        transacao.setCodigoEstabelecimento(linha.substring(42, 48).trim()); 
        
        return transacao;
    }
    

}
