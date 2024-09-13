package br.edu.iftm.tspi;

import br.edu.iftm.tspi.domain.Cliente;
import br.edu.iftm.tspi.domain.Conta;
import br.edu.iftm.tspi.domain.Plastico;
import br.edu.iftm.tspi.domain.Transacao;
import br.edu.iftm.tspi.service.ProcessaArquivos;
import br.edu.iftm.tspi.service.ProcessaLinhasCliente;
import br.edu.iftm.tspi.service.ProcessaLinhasConta;
import br.edu.iftm.tspi.service.ProcessaLinhasPlastico;
import br.edu.iftm.tspi.service.ProcessaLinhasTransacao;

public class Main {

    public static void main(String[] args) throws Exception {
        ProcessaArquivos arquivos = new ProcessaArquivos(Cliente.CLIENTE_PREFIX, new ProcessaLinhasCliente());

        arquivos.processa();

        ProcessaArquivos arquivos2 = new ProcessaArquivos(Conta.CONTA_PREFIX, new ProcessaLinhasConta());
        
        arquivos2.processa();

        ProcessaArquivos arquivos3 = new ProcessaArquivos(Plastico.PLASTICO_PREFIX, new ProcessaLinhasPlastico());
        arquivos3.processa();

        ProcessaArquivos arquivos4 = new ProcessaArquivos(Transacao.TRANSACAO_PREFIX, new ProcessaLinhasTransacao());
        arquivos4.processa();
    }

}