package estoque;

/**
 * Classe para armazenar os atributos e metodos do objeto Marca.
 * 
 * @author Renato Duarte Sampaio
 * @since 17 de fev. de 2021
 */
public class Marca {
	//M�todo construtor da classe.
	public Marca() {
		
	}

	// declarando os atributos
	private String nome;

	//M�todos para acessar os atributos (get e set)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
