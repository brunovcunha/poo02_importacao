package br.edu.iftm.tspi.service;

import java.util.List;

import br.edu.iftm.tspi.dao.PlasticoDao;
import br.edu.iftm.tspi.domain.Plastico;

public class ProcessaLinhasPlastico extends ProcessaCabecalho implements ProcessaLinha {

    private PlasticoDao plasticoDao;
    private String tipoArquivo = "PLA";

    public ProcessaLinhasPlastico() {
        plasticoDao = new PlasticoDao();
    }

    @Override
    public void processa(List<String> lines) throws Exception {
        for (String linha : lines) {
            String opcao = linha.substring(0, 1);
            if (opcao.equals("2")) {
                processaDetalhe(linha);
            } else if (opcao.equals("1")) {
                processaCabecalho(linha, tipoArquivo, plasticoDao);
            } else {
                throw new Exception("Desconheço essa opção de processar a linha: " + opcao);
            }
        }
        plasticoDao.salvarLote(getLote(), tipoArquivo);
    }

    private void processaDetalhe(String linha) throws Exception {
        Plastico plastico = getPlastico(linha);
        plasticoDao.salvarPlastico(plastico);
    }

    private Plastico getPlastico(String linha) {
        Plastico plastico = new Plastico();
        plastico.setNumeroConta(linha.substring(0, 4).trim()); 
        plastico.setNumeroPlastico(linha.substring(4, 12).trim()); 
        plastico.setNome(linha.substring(12, 32).trim()); 
        plastico.setCpf(linha.substring(32, 43).trim()); 
    
        return plastico;
    }
    
}
