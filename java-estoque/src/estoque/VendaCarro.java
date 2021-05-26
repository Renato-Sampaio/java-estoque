package estoque;

import javax.swing.JOptionPane;

/**
 * Classe para efetuar o processamento de consulta e inclus�o de estoque
 * 
 * @author Renato Duarte Sampaio
 * @since 17 de fev. de 2021
 */
public class VendaCarro {
	// Vetor para armazenar os carros cadastrados
	private Carro estoque[];

	// Variavel auxiliar para definir o tamanho do estoque
	private int tamanho = 0;

	// M�todo construtor da classe.
	public VendaCarro() {
		processar();

	}

	// M�todo Principal do Programa.
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

	// M�todo para capturar do usuario a op��o.
	public void escolhaUsuario() {
		String menu = "Infome a op��o desejada: \n\n" + "OP��O 1: Cadastrar Carro \n " + "OP��O 2: Listar Estoque \n"
				+ "OP��O 3: Consultar Carro \n" + "OP��O 4: Pesquisar por Modelo \n"
				+ "OP��O 5: Pesquisar por Combustivel \n" + "OP��O 6: Listar carros em promo��o \n"
				+ "OP��O 7: Sair do Sistema";

		int escolha = Integer.parseInt(JOptionPane.showInputDialog(menu));
		escolhaProcessamento(escolha);
	}

	// M�todo para sele��o das op��es.(menu)
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
			JOptionPane.showMessageDialog(null, "Opc�o Inv�lida !", "Cadastro de Estoque", 0);// erro.
			break;
		}
	}

	// M�todo para efetuar cadastro de um novo carro no estoque.
	public void cadastrarCarro() {
		// Variavel para verificar se gravou o carro.
		boolean gravado = false;
		
		// La�o para verificar espa�o em estoque
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
	} //Fim do M�todo.

	// M�todo para devolver um carro para cadastro no estoque.
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
																	+ "2- N�o Disponivel"));
		if (disponivel ==1) {
			carro.setDisponivel(true);
		}else {
			carro.setDisponivel(false);
		}
		
		int promocao = Integer.parseInt(JOptionPane.showInputDialog("1-Em Promo��o \n"
																  + "2- N�o est� em Promo��o"));
		if (promocao ==1) {
			carro.setPromocao(true);
			}else {
			carro.setPromocao(false);
		}
		carro.setValor(Double.parseDouble(JOptionPane.showInputDialog("Informe o Valor")));
		carro.setValorPromocao(Double.parseDouble(JOptionPane.showInputDialog("Informe o valor em Promo��o")));
		carro.setDesconto(Double.parseDouble(JOptionPane.showInputDialog("Informe o valor do desconto")));
		
		// Atribuindo o modelo ao carro.
		carro.setModelo(modelo);
		
		return carro;
	}

	// M�todo para listar o Estoque total de veiculos cadastrados.
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

	// M�todo para consultar um carro especifico no estoque.
	public void consultarCarro() {
		// Variavel auxiliar para montar uma mensagem para usuario.
		String mensagem ="Informe o c�digo do veiculo a ser consultado: \n \n";
		
		// Varrendo o estoque para verificar os carros cadastrados.
		for (int i = 0; i < estoque.length; i++) {
			if (estoque[i] != null) {
			mensagem += "C�digo " + (i+1) + " - " + estoque[i].getModelo().getNome()+"\n";
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
			mensagem += "Disponivel.: N�O \n";
		}
		
		// Verificando se o carro esta em promo��o.
		if(estoque[codigo - 1].isPromocao()) {
			mensagem += "Promo��o   : SIM \n";
			mensagem += " Valor     : " + estoque[codigo-1].getValorPromocao()+"\n";
		} else {
			mensagem += "Promo��o   : N�O \n";
			mensagem += " Valor     : " + estoque[codigo-1].getValor()+"\n";
		}
		
		// Exibindo o resultado da consulta.
		JOptionPane.showMessageDialog(null, mensagem,"Cadastro de Estoque", 1);
		}
	
	// M�todo para retornar todos os carros por modelo escolhido.
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
			JOptionPane.showMessageDialog(null, "N�o existe o modelo cadastrado ","Cadastro de Estoque", 2);
		}
	
	}// Fim do M�todo

	// M�todo para restornar todos os carros por combustivel escolhido.
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
			JOptionPane.showMessageDialog(null, "N�o existe o modelo cadastrado", "Cadastro de Estoque", 2);
		}

	}
	// M�todo para retornar todos os carros em promo��o.
	public void listarCarrosEmPromocao() {
		// recebendo do usuario qual modelo a ser pesquisado
				String promocao = JOptionPane.showInputDialog("Informe a promo��o para pesquisa");
				boolean aux = false;
				if (promocao.equals("SIM")) {
					aux = true;
				}

				// variavel auxiliar para exibir a mensagem do resultado pesquisado
				String mensagem = "Pesquisa por promo��o:\n\n";

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
					JOptionPane.showMessageDialog(null, "N�o existe o modelo cadastrado", "Cadastro de Estoque", 2);
				}
	}
	
	// M�todo para retornar mensagem  para o usuario.
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

	// M�todo para sair do sistema.
	public void sairDoSistema() {
		System.exit(0);
	}
}
