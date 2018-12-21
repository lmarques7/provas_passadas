package br.ufrpe.flight_system.negocio.beans;

import java.io.Serializable;

public class Passageiro implements Serializable {
    
	private static final long serialVersionUID = 2217141640899720839L;
	
	private String nome;
    private String ultimoNome;
    private String passaporte;
    private String cpf;
    
    public Passageiro(String nome, String ultimoNome, String cpf) {
        this.nome = nome;
        this.ultimoNome = ultimoNome;
        this.cpf = cpf;
    }    
    
    public Passageiro(String nome, String ultimoNome, String passaporte,
            String cpf) {
        this.nome = nome;
        this.ultimoNome = ultimoNome;
        this.passaporte = passaporte;
        this.cpf = cpf;
    }
    
    public Passageiro() {
        /*Construtor vazio sem inicialização de atributos*/
    }

    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getUltimoNome() {
        return ultimoNome;
    }
    
    public void setUltimoNome(String ultimoNome) {
        this.ultimoNome = ultimoNome;
    }
    
    public String getPassaporte() {
        return passaporte;
    }
    
    public void setPassaporte(String passaporte) {
        this.passaporte = passaporte;
    }
    
    public String getCpf() {
        return cpf;
    }
    
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * A ASSINATURA DO MÉTODO DEVE SER MANTIDA.
     * 
     * Este método deve testar a igualdade do objeto corrente (this) com outro
     * objeto informado como parâmetro e retornar 'true' somente se as seguintes
     * condições forem atingidas: 
     * - O parâmetro deve ser do tipo Passageiro
     * 
     * - Ambos os atributos 'nome' e 'ultimoNome' do parâmetro devem ser iguais
     * ao do que objeto corrente
     * 
     * - PELO MENOS UM DOS ATRIBUTOS 'passaporte' ou 'cpf' do parâmetro deve ser
     * igual ao do objeto corrente. Entenda que PELO MENOS UM DELES DEVE SER
     * IGUAL implica que ambos pode ser iguais
     * 
     * Não esquecer de testar potenciais condições de NullPointerException.
     */
    @Override
    public boolean equals(Object obj) {
        // TODO Implementar seguindo comentários do método. Não alterar a assinatura do método
        return false;
    }
    
    @Override
    public String toString() {
        return String.format("Nome: %s %s\n CPF: %s\n Passaporte: %s", this.nome, this.ultimoNome, this.cpf, this.passaporte);
    }
}
