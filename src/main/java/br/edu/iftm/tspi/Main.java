package br.edu.iftm.tspi;

import br.edu.iftm.tspi.domain.Cliente;
import br.edu.iftm.tspi.domain.Conta;
import br.edu.iftm.tspi.service.ProcessaArquivos;
import br.edu.iftm.tspi.service.ProcessaLinhasCliente;
import br.edu.iftm.tspi.service.ProcessaLinhasConta;

public class Main {

    public static void main(String[] args) throws Exception {
        ProcessaArquivos arquivos = new ProcessaArquivos(Cliente.CLIENTE_PREFIX, new ProcessaLinhasCliente());

        arquivos.processa();

        ProcessaArquivos arquivos2 = new ProcessaArquivos(Conta.CONTA_PREFIX, new ProcessaLinhasConta());
        
        arquivos2.processa();
    }

}