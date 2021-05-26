package estoque;

/**
 * Classe para armazenar os atributos e metodos do objeto Modelo.
 * 
 * @author Renato Duarte Sampaio 
 * @since 17 de fev. de 2021
 */
public class Modelo {
//Método construtor da classe.
	public Modelo() {

	}

	// declarando os atributos
	private String nome;
	private Marca marca;

	//Método para acessar os atributos (get e set).
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

}
