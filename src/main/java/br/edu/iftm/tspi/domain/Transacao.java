package br.edu.iftm.tspi.domain;

import java.util.Date;

public class Transacao {

    private String numeroConta;
    private String numeroPlastico;
    private Double valorTransacao;
    private Date dataTransacao;
    private String horaTransacao;
    private String codigoEstabelecimento;

    public static final String TRANSACAO_PREFIX = "Transacao_*";

    public boolean isAtualizou(String valor) {
        for (char c: valor.toCharArray()) {
            if (c != '_') {
                return true;
            }
        }
        return false;
    }


    public String getNumeroConta() {
        return numeroConta;
    }
    
    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }
    public String getNumeroPlastico() {
        return numeroPlastico;
    }
    public void setNumeroPlastico(String numeroPlastico) {
        this.numeroPlastico = numeroPlastico;
    }
    public Double getValorTransacao() {
        return valorTransacao;
    }
    public void setValorTransacao(Double valorTransacao) {
        this.valorTransacao = valorTransacao;
    }
    public Date getDataTransacao() {
        return dataTransacao;
    }
    public void setDataTransacao(Date dataTransacao) {
        this.dataTransacao = dataTransacao;
    }
    public String getHoraTransacao() {
        return horaTransacao;
    }
    public void setHoraTransacao(String horaTransacao) {
        this.horaTransacao = horaTransacao;
    }
    public String getCodigoEstabelecimento() {
        return codigoEstabelecimento;
    }
    public void setCodigoEstabelecimento(String codigoEstabelecimento) {
        this.codigoEstabelecimento = codigoEstabelecimento;
    }

    @Override
    public String toString() {
        return "Transacao [numeroConta=" + numeroConta + ", numeroPlastico=" + numeroPlastico + ", valorTransacao="
                + valorTransacao + ", dataTransacao=" + dataTransacao + ", horaTransacao=" + horaTransacao
                + ", codigoEstabelecimento=" + codigoEstabelecimento + "]";
    }

    

    
}
