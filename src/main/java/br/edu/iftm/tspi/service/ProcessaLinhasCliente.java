package br.edu.iftm.tspi.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import br.edu.iftm.tspi.dao.ClienteDao;

import br.edu.iftm.tspi.domain.Cliente;

public class ProcessaLinhasCliente extends ProcessaCabecalho implements ProcessaLinha{

    private ClienteDao clienteDao;

    private String tipoArquivo = "CLI";

    public ProcessaLinhasCliente() {
        clienteDao = new ClienteDao();
    }

    @Override
    public void processa(List<String> lines) throws Exception {
        for (String linha: lines) {
            String opcao = linha.substring(0, 1);
            if (opcao.equals("2")) {
                processaDetalhe(linha);
            } else if (opcao.equals("1")) {
                processaCabecalho(linha, tipoArquivo, clienteDao);
            } else {
                throw new Exception("Desconheço essa opção de processar a linha: "+opcao);
            }
        }
        clienteDao.salvarLote(getLote(), tipoArquivo);
    }


    private void processaDetalhe(String linha) throws Exception {
        Cliente cliente = getCliente(linha);
        clienteDao.persistir(cliente);
    }

    private Cliente getCliente(String linha) throws ParseException {
        Cliente cliente = new Cliente();
        cliente.setInclusaoAlteracao(linha.substring(1,2));
        cliente.setCpf(linha.substring(2,13));
        cliente.setNome(linha.substring(13,43));
        cliente.setEndereco(linha.substring(43,73));
        cliente.setBairro(linha.substring(73,103));
        cliente.setCidade(linha.substring(103,133));
        cliente.setEstado(linha.substring(133, 135));
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyhhmmss");
        String dataCadastro = linha.substring(135, 143);
        String horaCadastro = linha.substring(143, 149);
        Date dataHoraCadastro = sdf.parse(dataCadastro+horaCadastro);
        cliente.setDataHoraCadastro(dataHoraCadastro);
        return cliente;
    }

    

}
