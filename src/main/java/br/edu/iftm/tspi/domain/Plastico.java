package br.edu.iftm.tspi.domain;

/**
 * Plastico
 */
public class Plastico {

    private String numeroConta;
    private String nome;
    private String cpf;
    private String numeroPlastico;

    public static final String PLASTICO_PREFIX = "Plastico_*";

    public boolean isAtualizou(String valor) {
        for (char c: valor.toCharArray()) {
            if (c != '_') {
                return true;
            }
        }
        return false;
    }

    // Getters e Setters
    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNumeroPlastico() {
        return numeroPlastico;
    }

    public void setNumeroPlastico(String numeroPlastico) {
        this.numeroPlastico = numeroPlastico;
    }

    @Override
    public String toString() {
        return "Plastico [numeroConta=" + numeroConta + ", nome=" + nome + ", cpf=" + cpf + ", numeroPlastico=" + numeroPlastico + "]";
    }
}