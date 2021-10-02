package control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Viajante extends Pessoa {
	private String nroCompra, estado, nroLocalizacao;

	public Viajante() {

	}

	Viajante(String nome, String rg, String cpf, String tel) {
		this.setCpf(cpf);
		this.setRg(rg);
		this.setNome(nome);
		this.setTel(tel);
	}

	public void menu(String caminhoViajante) {
		System.out.println("Carregando área do viajante...\n");
		System.out.println(" Escolha a opção: \n==================");
		System.out.println("1 - para cadastrar-se no site;");
		System.out.println("2 - para verificar se o cadastro existe;");
		System.out.println("3 - para alterar informações do cadastro;");
		System.out.println("4 - para excluir um cadastro existente;");
		System.out.println("5 - para verificar opções de viagens;");
		System.out.println("6 - para comprar passagens;");
		System.out.println("7 - para listar cadastros.");
	}

	public void cadastrar(Scanner ler, String caminhoViajante) {
		System.out.print("Digite o nome completo: ");
		setNome(ler.nextLine());
		System.out.print("Digite o CPF (somente números - 11 dígitos): ");
		String aux = ler.nextLine();
		System.out.print("Digite o RG: ");
		setRg(ler.nextLine());
		System.out.print("Digite o estado onde reside: ");
		setEstado(ler.nextLine());
		System.out.print("Digite o telefone (com prefixo e somente números - 11 dígitos): ");
		String aux2 = ler.nextLine();
		int j = 0;
		for (int i = 0; i < aux.length() && i < aux2.length(); i++) {
			j++;
			if (j == 11) {
				String[] vetorAux = aux.split("");
				String[] vetorAux2 = aux2.split("");
				setCpf(vetorAux[0] + vetorAux[1] + vetorAux[2] + "." + vetorAux[3] + vetorAux[4] + vetorAux[5] + "."
						+ vetorAux[6] + vetorAux[7] + vetorAux[8] + "-" + vetorAux[9] + vetorAux[10]);
				setTel("(" + vetorAux2[0] + vetorAux2[1] + ")" + vetorAux2[2] + vetorAux2[3] + vetorAux2[4]
						+ vetorAux2[5] + vetorAux2[6] + "-" + vetorAux2[7] + vetorAux2[8] + vetorAux2[9]
						+ vetorAux2[10]);
				salvarViajante(caminhoViajante);
				System.out.println("\nCadastro realizado com sucesso!\n");
			}
		}
	}

	public int lerUltimoRegistro(String caminhoViajante) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(caminhoViajante));
			int i = 99;
			String[] aux;
			while (br.ready()) {
				br.ready();
				aux = br.readLine().split("#");
				i = Integer.parseInt(aux[0]);
			}
			br.close();
			return ++i;
		} catch (IOException e) {
			System.out.print("Erro no programa.");
		}
		return 0;
	}

	public void salvarViajante(String caminhoViajante) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoViajante, true));
			bw.write(lerUltimoRegistro(caminhoViajante) + "#" + getNome() + "#" + getCpf() + "#" + getRg() + "#"
					+ getEstado() + "#" + getTel());
			bw.newLine();
			bw.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void atualizarNome(String caminhoViajante, Scanner ler) {
		try {
			String i = caminhoViajante.replace("caminhoViajante.txt", "viajanteTemporaria.txt");
			File j = new File(i);
			j.createNewFile();

			BufferedReader br = new BufferedReader(new FileReader(caminhoViajante));
			BufferedWriter bw = new BufferedWriter(new FileWriter(i));

			System.out.println("Digite o número de localização do cadastro: ");
			String nrLocalizacao = ler.nextLine();
			String[] vetor;

			while (br.ready()) {
				br.ready();
				String linha = br.readLine();
				vetor = linha.split("#");

				if (vetor[0].equalsIgnoreCase(nrLocalizacao)) {
					System.out.print("Digite o novo nome completo: ");
					setNome(ler.nextLine());
					bw.write(vetor[0] + "#" + getNome() + "#" + vetor[2] + "#" + vetor[3] + "#" + vetor[4] + "#"
							+ vetor[5]);
					bw.newLine();
					System.out.println("Nome atualizado com sucesso!");
				} else {
					bw.write(linha);
					bw.newLine();
				}
			}
			br.close();
			bw.close();

			BufferedReader braux = new BufferedReader(new FileReader(i));
			BufferedWriter bwaux = new BufferedWriter(new FileWriter(caminhoViajante));

			while (braux.ready()) {
				bwaux.write(braux.readLine());
				bwaux.newLine();
			}
			bwaux.close();
			braux.close();
			j.delete();

		} catch (Exception e) {
			System.out.println("Erro no programa.");
		}
	}

	public void atualizarCpf(String caminhoViajante, Scanner ler) {
		try {
			String i = caminhoViajante.replace("caminhoViajante.txt", "viajanteTemporaria.txt");
			File j = new File(i);
			j.createNewFile();

			BufferedReader br = new BufferedReader(new FileReader(caminhoViajante));
			BufferedWriter bw = new BufferedWriter(new FileWriter(i));

			System.out.println("Digite o número de localização do cadastro: ");
			String nrLocalizacao = ler.nextLine();
			String[] vetor;

			while (br.ready()) {
				br.ready();
				String linha = br.readLine();
				vetor = linha.split("#");

				if (vetor[0].equalsIgnoreCase(nrLocalizacao)) {
					System.out.print("Digite o novo CPF: ");
					setCpf(ler.nextLine());
					bw.write(vetor[0] + "#" + vetor[1] + "#" + getCpf() + "#" + vetor[3] + "#" + vetor[4] + "#"
							+ vetor[5]);
					bw.newLine();
					System.out.println("CPF atualizado com sucesso!");
				} else {
					bw.write(linha);
					bw.newLine();
				}
			}
			br.close();
			bw.close();

			BufferedReader braux = new BufferedReader(new FileReader(i));
			BufferedWriter bwaux = new BufferedWriter(new FileWriter(caminhoViajante));

			while (braux.ready()) {
				bwaux.write(braux.readLine());
				bwaux.newLine();
			}
			bwaux.close();
			braux.close();
			j.delete();

		} catch (Exception e) {
			System.out.println("Erro no programa.");
		}
	}

	public void atualizarRg(String caminhoViajante, Scanner ler) {
		try {
			String i = caminhoViajante.replace("caminhoViajante.txt", "viajanteTemporaria.txt");
			File j = new File(i);
			j.createNewFile();

			BufferedReader br = new BufferedReader(new FileReader(caminhoViajante));
			BufferedWriter bw = new BufferedWriter(new FileWriter(i));

			System.out.println("Digite o número de localização do cadastro: ");
			String nrLocalizacao = ler.nextLine();
			String[] vetor;

			while (br.ready()) {
				br.ready();
				String linha = br.readLine();
				vetor = linha.split("#");

				if (vetor[0].equalsIgnoreCase(nrLocalizacao)) {
					System.out.print("Digite o novo RG: ");
					setRg(ler.nextLine());
					bw.write(vetor[0] + "#" + vetor[1] + "#" + vetor[2] + "#" + getRg() + "#" + vetor[4] + "#"
							+ vetor[5]);
					bw.newLine();
					System.out.println("RG atualizado com sucesso!");
				} else {
					bw.write(linha);
					bw.newLine();
				}
			}
			br.close();
			bw.close();

			BufferedReader braux = new BufferedReader(new FileReader(i));
			BufferedWriter bwaux = new BufferedWriter(new FileWriter(caminhoViajante));

			while (braux.ready()) {
				bwaux.write(braux.readLine());
				bwaux.newLine();
			}
			bwaux.close();
			braux.close();
			j.delete();

		} catch (Exception e) {
			System.out.println("Erro no programa.");
		}
	}

	public void atualizarEstado(String caminhoViajante, Scanner ler) {
		try {
			String i = caminhoViajante.replace("caminhoViajante.txt", "viajanteTemporaria.txt");
			File j = new File(i);
			j.createNewFile();

			BufferedReader br = new BufferedReader(new FileReader(caminhoViajante));
			BufferedWriter bw = new BufferedWriter(new FileWriter(i));

			System.out.println("Digite o número de localização do cadastro: ");
			String nrLocalizacao = ler.nextLine();
			String[] vetor;

			while (br.ready()) {
				br.ready();
				String linha = br.readLine();
				vetor = linha.split("#");

				if (vetor[0].equalsIgnoreCase(nrLocalizacao)) {
					System.out.print("Digite o novo estado: ");
					setEstado(ler.nextLine());
					bw.write(vetor[0] + "#" + vetor[1] + "#" + vetor[2] + "#" + vetor[3] + "#" + getEstado() + "#"
							+ vetor[5]);
					bw.newLine();
					System.out.println("Estado atualizado com sucesso!");
				} else {
					bw.write(linha);
					bw.newLine();
				}
			}
			br.close();
			bw.close();

			BufferedReader braux = new BufferedReader(new FileReader(i));
			BufferedWriter bwaux = new BufferedWriter(new FileWriter(caminhoViajante));

			while (braux.ready()) {
				bwaux.write(braux.readLine());
				bwaux.newLine();
			}
			bwaux.close();
			braux.close();
			j.delete();

		} catch (Exception e) {
			System.out.println("Erro no programa.");
		}
	}

	public void atualizarTel(String caminhoViajante, Scanner ler) {
		try {
			String i = caminhoViajante.replace("caminhoViajante.txt", "viajanteTemporaria.txt");
			File j = new File(i);
			j.createNewFile();

			BufferedReader br = new BufferedReader(new FileReader(caminhoViajante));
			BufferedWriter bw = new BufferedWriter(new FileWriter(i));

			System.out.println("Digite o número de localização do cadastro: ");
			String nrLocalizacao = ler.nextLine();
			String[] vetor;

			while (br.ready()) {
				br.ready();
				String linha = br.readLine();
				vetor = linha.split("#");

				if (vetor[0].equalsIgnoreCase(nrLocalizacao)) {
					System.out.print("Digite o novo telefone: ");
					String aux = ler.nextLine();
					int m = 0;
					for (int n = 0; n < aux.length(); n++) {
						m++;
						if (m == 11) {
							String[] vetorAux = aux.split("");
							setTel("(" + vetorAux[0] + vetorAux[1] + ")" + vetorAux[2] + vetorAux[3] + vetorAux[4]
									+ vetorAux[5] + vetorAux[6] + "-" + vetorAux[7] + vetorAux[8] + vetorAux[9]
									+ vetorAux[10]);

							bw.write(vetor[0] + "#" + vetor[1] + "#" + vetor[2] + "#" + vetor[3] + "#"+ vetorAux[4] + "#" + getTel());
							bw.newLine();
							System.out.println("/nTelefone atualizado com sucesso!");
						}
					}
				} else {
					bw.write(linha);
					bw.newLine();
				}
			}
			br.close();
			bw.close();

			BufferedReader braux = new BufferedReader(new FileReader(i));
			BufferedWriter bwaux = new BufferedWriter(new FileWriter(caminhoViajante));

			while (braux.ready()) {
				bwaux.write(braux.readLine());
				bwaux.newLine();
			}
			bwaux.close();
			braux.close();
			j.delete();

		} catch (Exception e) {
			System.out.println("Erro no programa.");
		}
	}

	public String[] localizarCadastro(String caminhoViajante, Scanner ler) {
		try {
			System.out.println("Digite o número de localização do cadastro: ");
			String nrLocalizacao = ler.nextLine();
			setNroLocalizacao(nrLocalizacao);
			BufferedReader ler2 = new BufferedReader(new FileReader(caminhoViajante));

			String[] vetor;

			while (ler2.ready()) {
				ler2.ready();
				vetor = ler2.readLine().split("#");

				if (vetor[0].equalsIgnoreCase(nrLocalizacao)) {
					System.out.println("Cadastro localizado!");
					ler2.close();

					return vetor;
				}
			}
			System.out.println("Cadastro não localizado. Verifique os dígitos ou efetue um cadastro.");
			ler2.close();

			return null;

		} catch (IOException e) {
			System.out.println("Erro no programa.");
		}
		ler.close();
		return null;
	}

	public void excluirCadastro(String caminhoViajante, Scanner ler) {
		try {
			String i = caminhoViajante.replace("caminhoViajante.txt", "viajanteTemporaria.txt");
			File f = new File(i);
			f.createNewFile();

			BufferedReader br = new BufferedReader(new FileReader(caminhoViajante));
			BufferedWriter bw = new BufferedWriter(new FileWriter(i));

			System.out.println("Digite o número de localização do cadastro: ");
			String nrLocalizacao = ler.nextLine();
			String[] vetor;

			while (br.ready()) {
				br.ready();
				String linha = br.readLine();
				vetor = linha.split("#");

				if (vetor[0].equalsIgnoreCase(nrLocalizacao)) {
					System.out.println("Cadastro excluído com sucesso!");
				} else {
					bw.write(linha);
					bw.newLine();
				}
			}
			br.close();
			bw.close();

			BufferedReader braux = new BufferedReader(new FileReader(i));
			BufferedWriter bwaux = new BufferedWriter(new FileWriter(caminhoViajante));

			while (braux.ready()) {
				String linha = braux.readLine();
				bwaux.write(linha);
				bwaux.newLine();
			}
			bwaux.close();
			braux.close();

		} catch (Exception e) {
			System.out.println("Erro no programa.");
		}
	}

	public void opcoesPassagens(String caminhoPassagem, Scanner ler) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(caminhoPassagem));

			System.out.println("Digite a sigla do estado de partida: (exempo: RJ, MG, SP.)");
			String estadoPartida = ler.nextLine();
			System.out.println("Digite a sigla do estado de chegada: (exempo: RJ, MG, SP.)");
			String estadoChegada = ler.nextLine();
			String[] vetor;

			while (br.ready()) {
				br.ready();
				String linha = br.readLine();
				vetor = linha.split("#");

				if (vetor[0].equalsIgnoreCase(estadoPartida)) {

					if (vetor[1].equalsIgnoreCase(estadoChegada)) {
						int valorPassagem = (Integer.parseInt(vetor[2]) / Integer.parseInt(vetor[3])) + 400;
						System.out.print("Dados da viagem \n=============== \nDistância de " + vetor[0] + " até "
								+ vetor[1] + ": " + vetor[2] + "km;\n" + "Tempo de viagem: " + vetor[3]
								+ "h;\nValor da passagem: " + valorPassagem + ",00 reais.\n");
					}
				}

			}

			br.close();
		} catch (Exception e) {
			System.out.println("Passagem não encontrada.");
		}
	}

	public void comprarPassagem(String caminhoPassagem, String caminhoViajante, Scanner ler, String passagemComprada) {
		try {
			BufferedReader bp = new BufferedReader(new FileReader(caminhoPassagem));

			System.out.println("Digite a sigla do estado de partida: (exempo: RJ, MG, SP.)");
			String partida = ler.nextLine();

			System.out.println("Digite a sigla do estado de chegada: (exempo: RJ, MG, SP.)");
			String chegada = ler.nextLine();

			while (bp.ready()) {
				bp.ready();
				String linha;
				String[] vetor;

				linha = bp.readLine();
				vetor = linha.split("#");

				if (vetor[0].equalsIgnoreCase(partida)) {
					if (vetor[1].equalsIgnoreCase(chegada)) {
						int v = (Integer.parseInt(vetor[2]) / Integer.parseInt(vetor[3])) + 400;
						String valorPassagem = v + "";
						System.out.println("Passagem disponível!");

						if (localizarCadastro(caminhoViajante, ler) == null) {
						} else {
							BufferedWriter bw = new BufferedWriter(new FileWriter(passagemComprada, true));
							bw.write(getNroLocalizacao() + "#" + valorPassagem + "#" + partida + "#" + chegada);
							bw.newLine();
							bw.close();
							System.out.println("Passagem comprada com sucesso!");
						}
						break;
					}
				}
			}
			bp.close();

		} catch (Exception e) {
		}
	}

	public String[] listarCadastros(String caminhoViajante, Scanner ler) {
		try {
			BufferedReader ler2 = new BufferedReader(new FileReader(caminhoViajante));

			while (ler2.ready()) {
				String[] vetor;
				vetor = ler2.readLine().split("#");

				System.out.println("\nNúmero localização: " + vetor[0] + "\nNome: " + vetor[1] + "\nCPF: " + vetor[2]
						+ "\nRG: " + vetor[3] + "\nEstado onde reside: " + vetor[4] + "\nTelefone: " + vetor[5]);
			}
			ler2.close();

			return null;

		} catch (IOException e) {
			System.out.println("Erro no programa.");
		}
		ler.close();
		return null;
	}

	public String getNroCompra() {
		return nroCompra;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setNroCompra(String nroCompra) {
		this.nroCompra = nroCompra;
	}

	public String getNroLocalizacao() {
		return nroLocalizacao;
	}

	public void setNroLocalizacao(String nroLocalizacao) {
		this.nroLocalizacao = nroLocalizacao;
	}

}