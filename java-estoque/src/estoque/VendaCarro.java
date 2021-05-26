package estoque;

import javax.swing.JOptionPane;

/**
 * Classe para efetuar o processamento de consulta e inclusão de estoque
 * 
 * @author Renato Duarte Sampaio
 * @since 17 de fev. de 2021
 */
public class VendaCarro {
	// Vetor para armazenar os carros cadastrados
	private Carro estoque[];

	// Variavel auxiliar para definir o tamanho do estoque
	private int tamanho = 0;

	// Método construtor da classe.
	public VendaCarro() {
		processar();

	}

	// Método Principal do Programa.
	public void processar() {

		// Capturando do usuario o tamanho do estoque.
		tamanho = Integer.parseInt(JOptionPane.showInputDialog("Informe o tamanho do estoque"));

		// Definindo o tamanho do estoque de carros
		estoque = new Carro[tamanho];

		// Processamento de looping do programa.
		while (true) {
			escolhaUsuario();
		}
	}

	// Método para capturar do usuario a opção.
	public void escolhaUsuario() {
		String menu = "Infome a opção desejada: \n\n" + "OPÇÃO 1: Cadastrar Carro \n " + "OPÇÃO 2: Listar Estoque \n"
				+ "OPÇÃO 3: Consultar Carro \n" + "OPÇÃO 4: Pesquisar por Modelo \n"
				+ "OPÇÃO 5: Pesquisar por Combustivel \n" + "OPÇÃO 6: Listar carros em promoção \n"
				+ "OPÇÃO 7: Sair do Sistema";

		int escolha = Integer.parseInt(JOptionPane.showInputDialog(menu));
		escolhaProcessamento(escolha);
	}

	// Método para seleção das opções.(menu)
	public void escolhaProcessamento(int opcao) {
		switch (opcao) {
		case 1:
			cadastrarCarro();
			break;

		case 2:
			listarEstoque();
			break;

		case 3:
			consultarCarro();
			break;

		case 4:
			pesquisarPorModelo();
			break;

		case 5:
			pesquisarPorCombustivel();
			break;

		case 6:
			listarCarrosEmPromocao();
			break;

		case 7:
			sairDoSistema();
			break;

		default:
			JOptionPane.showMessageDialog(null, "Opcão Inválida !", "Cadastro de Estoque", 0);// erro.
			break;
		}
	}

	// Método para efetuar cadastro de um novo carro no estoque.
	public void cadastrarCarro() {
		// Variavel para verificar se gravou o carro.
		boolean gravado = false;
		
		// Laço para verificar espaço em estoque
		for (int i = 0; i < estoque.length; i++) {//inicio for
			if (estoque[i] == null) {
				estoque[i] = criarCarro();
				gravado= true;
				break;
			}
		}// Fim do for
		
		// Exibindo o resultado para o usuario.
		if(gravado) {
			JOptionPane.showMessageDialog(null, "Veiculo gravado com sucesso!", "Cadastro de Veiculo", 1);
		} else {
			JOptionPane.showMessageDialog(null, "Estoque Cheio!", "Cadastro de Veiculo", 2);
		}
	} //Fim do Método.

	// Método para devolver um carro para cadastro no estoque.
	public Carro criarCarro() {
		Marca marca = new Marca();
		Modelo modelo = new Modelo();
		Carro carro = new Carro();
		
		// Recebendo a marca do carro.
		marca.setNome(JOptionPane.showInputDialog("Informe o nome da marca:"));
		 
		// Recebendo do usuario o nome do modelo
		modelo.setNome(JOptionPane.showInputDialog("Informe o modelo do carro:"));
		
		// Atribuindo o modelo a marca.
		modelo.setMarca(marca);
		
		// Recebendo os dados do carro.
		carro.setPlaca(JOptionPane.showInputDialog("Informe a PLACA"));
		carro.setVelocidadeMaxima(Integer.parseInt(JOptionPane.showInputDialog("Informe a velocidade MAXIMA so veiculo.")));
		carro.setCombustivel(JOptionPane.showInputDialog("Informe o tipo de combustivel do veiculo"));
		
		int disponivel = Integer.parseInt(JOptionPane.showInputDialog("1-Disponivel \n"
																	+ "2- Não Disponivel"));
		if (disponivel ==1) {
			carro.setDisponivel(true);
		}else {
			carro.setDisponivel(false);
		}
		
		int promocao = Integer.parseInt(JOptionPane.showInputDialog("1-Em Promoção \n"
																  + "2- Não está em Promoção"));
		if (promocao ==1) {
			carro.setPromocao(true);
			}else {
			carro.setPromocao(false);
		}
		carro.setValor(Double.parseDouble(JOptionPane.showInputDialog("Informe o Valor")));
		carro.setValorPromocao(Double.parseDouble(JOptionPane.showInputDialog("Informe o valor em Promoção")));
		carro.setDesconto(Double.parseDouble(JOptionPane.showInputDialog("Informe o valor do desconto")));
		
		// Atribuindo o modelo ao carro.
		carro.setModelo(modelo);
		
		return carro;
	}

	// Método para listar o Estoque total de veiculos cadastrados.
	public void listarEstoque() {
		
		// Varialvel auxiliar para exibir total de veiculos cadastrados
		String mensagem="";
		
		// Varrendo o estoque para verificar se existe carros cadastrados.
		for (int i = 0; i < estoque.length; i++) {// Inicio do for
			if (estoque [i] != null) {
				mensagem += (i+1) + " - " + estoque[i].getModelo().getNome()+"\n";
			}
		}// Fim do For
		JOptionPane.showMessageDialog(null, mensagem, "Cadastro de Estoque", 1);
	}

	// Método para consultar um carro especifico no estoque.
	public void consultarCarro() {
		// Variavel auxiliar para montar uma mensagem para usuario.
		String mensagem ="Informe o código do veiculo a ser consultado: \n \n";
		
		// Varrendo o estoque para verificar os carros cadastrados.
		for (int i = 0; i < estoque.length; i++) {
			if (estoque[i] != null) {
			mensagem += "Código " + (i+1) + " - " + estoque[i].getModelo().getNome()+"\n";
		}
	}
		
		// Capturando o modelo a ser consultado.
		int codigo = Integer.parseInt(JOptionPane.showInputDialog(mensagem));

		// Inicializando a variavel auxiliar para exibir mensagem
		mensagem ="";
		mensagem += "Modelo     : " + estoque[codigo -1].getModelo().getNome()+"\n";
		mensagem += "Marca      : " + estoque[codigo -1].getModelo().getMarca().getNome()+"\n";
		mensagem += "Placa      : " + estoque[codigo -1].getPlaca()+"\n";
		mensagem += "Combustivel: " + estoque[codigo -1].getCombustivel()+"\n";
		mensagem += "Vel. Maxima: " + estoque[codigo -1].getVelocidadeMaxima()+"\n";
		
		// Verificar se esta Disponivel.
		if(estoque[codigo -1].isDisponivel()) {
			mensagem += "Disponivel.: SIM \n";
		}else {
			mensagem += "Disponivel.: NÃO \n";
		}
		
		// Verificando se o carro esta em promoção.
		if(estoque[codigo - 1].isPromocao()) {
			mensagem += "Promoção   : SIM \n";
			mensagem += " Valor     : " + estoque[codigo-1].getValorPromocao()+"\n";
		} else {
			mensagem += "Promoção   : NÃO \n";
			mensagem += " Valor     : " + estoque[codigo-1].getValor()+"\n";
		}
		
		// Exibindo o resultado da consulta.
		JOptionPane.showMessageDialog(null, mensagem,"Cadastro de Estoque", 1);
		}
	
	// Método para retornar todos os carros por modelo escolhido.
	public void pesquisarPorModelo() {
		//recebendo do usuario o modelo a ser pesquisado.
		String modelo= JOptionPane.showInputDialog("Informe o modelo para pesquisa");
		
		//variavel auxiliar para exibir a mensagem do resultado pesquisado.
		String mensagem = " Pesquisa por Modelo: \n \n ";
		
		//variavel auxiliar para verificar se existe o modelo cadastrado.
		boolean existe = false;
		
		//varrendo o vetor e verefificando o carra cadastrado.
		for (int i = 0; i < estoque.length; i++) {
			if(estoque[i] != null && estoque[i].getModelo().getNome().equals(modelo)) {
				mensagem += mensagem(estoque[i]);
				existe = true;
			}
		}// Fim do for
		
		if(existe) {
			JOptionPane.showMessageDialog(null, mensagem, "Cadastro de Estoque", 1);
		} else {
			JOptionPane.showMessageDialog(null, "Não existe o modelo cadastrado ","Cadastro de Estoque", 2);
		}
	
	}// Fim do Método

	// Método para restornar todos os carros por combustivel escolhido.
	public void pesquisarPorCombustivel() {
		// recebendo do usuario qual modelo a ser pesquisado
		String combustivel = JOptionPane.showInputDialog("Informe o combustivel para pesquisa");

		// variavel auxiliar para exibir a mensagem do resultado pesquisado
		String mensagem = "Pesquisa por combustivel:\n\n";

		// variavel auxiliar para verificar se existe o modelo cadastrado
		boolean existe = false;

		// varrendo o vetor e verificando o carro cadastrado
		for (int i = 0; i < estoque.length; i++) {
			if (estoque[i] != null && estoque[i].getCombustivel().equals(combustivel)) {
				mensagem += mensagem(estoque[i]);
				existe = true;
			}
		} // fim do for

		// exibindo o resultado da pesquisa para o usuario
		if (existe) {
			JOptionPane.showMessageDialog(null, mensagem, "Cadastro de Estoque", 1);
		} else {
			JOptionPane.showMessageDialog(null, "Não existe o modelo cadastrado", "Cadastro de Estoque", 2);
		}

	}
	// Método para retornar todos os carros em promoção.
	public void listarCarrosEmPromocao() {
		// recebendo do usuario qual modelo a ser pesquisado
				String promocao = JOptionPane.showInputDialog("Informe a promoção para pesquisa");
				boolean aux = false;
				if (promocao.equals("SIM")) {
					aux = true;
				}

				// variavel auxiliar para exibir a mensagem do resultado pesquisado
				String mensagem = "Pesquisa por promoção:\n\n";

				// variavel auxiliar para verificar se existe o modelo cadastrado
				boolean existe = false;

				// varrendo o vetor e verificando o carro cadastrado
				for (int i = 0; i < estoque.length; i++) {
					if (estoque[i] != null && estoque[i].isPromocao() == aux) {
						mensagem += mensagem(estoque[i]);
						existe = true;
					}
				} // fim do for

				// exibindo o resultado da pesquisa para o usuario
				if (existe) {
					JOptionPane.showMessageDialog(null, mensagem, "Cadastro de Estoque", 1);
				} else {
					JOptionPane.showMessageDialog(null, "Não existe o modelo cadastrado", "Cadastro de Estoque", 2);
				}
	}
	
	// Método para retornar mensagem  para o usuario.
	public String mensagem (Carro carro) {
		//variavel auxiliar para retornar a mensagem formatada.
		String msg = "\n";
		
		// Montando a mensagem de retorno para o usuario
		msg += "Modelo: " + carro.getModelo().getNome()+ "\n";
		msg += "Marca: " + carro.getModelo().getMarca().getNome()+ "\n";
		msg += "Placa: " + carro.getPlaca() + "\n";
		msg += "Combustivel: " + carro.getCombustivel()+ "\n";
		msg += "Vel. Maxima: " + carro.getVelocidadeMaxima();
		
		
		//retornando mensagem formatada.
		return msg;
	}

	// Método para sair do sistema.
	public void sairDoSistema() {
		System.exit(0);
	}
}
