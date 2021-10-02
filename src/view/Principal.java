package view;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import control.Vendedor;
import control.Viajante;

public class Principal {

	public static void main(String[] args) {
		String caminhoViajante = System.getProperty("user.dir") + "\\src\\control\\caminhoViajante.txt";
		String caminhoVendedor = System.getProperty("user.dir") + "\\src\\control\\caminhoVendedor.txt";
		String caminhoPassagem = System.getProperty("user.dir") + "\\src\\control\\caminhoPassagem.txt";
		String passagemComprada = System.getProperty("user.dir") + "\\src\\control\\passagemComprada.txt";

		verificarArquivo(caminhoViajante);
		verificarArquivo(caminhoVendedor);
		verificarArquivo(caminhoPassagem);
		verificarArquivo(passagemComprada);

		String op = null;
		Scanner ler = new Scanner(System.in);

		System.out.println("\n Bem-vindo(a)!");
		do {
			do {
				System.out.println("\nEscolha a forma de acesso:\n==========================");
				System.out.println("1 - para acessar como Viajante;");
				System.out.println("2 - para acessar como Vendedor.");
				System.out.println("s - para sair.");

				op = ler.nextLine();

			} while (!(op.equals("1") | op.equals("2") | op.equals("3") | op.equalsIgnoreCase("S")));

			switch (op) {

			case "1": {
				Viajante vj = new Viajante();
				vj.menu(caminhoViajante);
				op = ler.nextLine();

				switch (op) {
				case "1": {
					System.out.println("Iniciando sessão de cadastramento...\n");
					vj.cadastrar(ler, caminhoViajante);
					break;
				}
				case "2": {
					vj.localizarCadastro(caminhoViajante, ler);
					break;
				}
				case "3": {
					System.out.println(" Escolha a opção: \n==================");
					System.out.println("1 - para alterar nome;");
					System.out.println("2 - para alterar CPF;");
					System.out.println("3 - para alterar RG;");
					System.out.println("4 - para alterar estado onde reside;");
					System.out.println("5 - para alterar telefone.");
					String op2 = ler.nextLine();

					switch (op2) {

					case "1": {
						vj.atualizarNome(caminhoViajante, ler);
						break;
					}
					case "2": {
						vj.atualizarCpf(caminhoViajante, ler);
						break;
					}
					case "3": {
						vj.atualizarRg(caminhoViajante, ler);
						break;
					}
					case "4": {
						vj.atualizarEstado(caminhoViajante, ler);
						break;
					}
					case "5": {
						vj.atualizarTel(caminhoViajante, ler);
						break;
					}
					default: {
						System.out.println("Opção inválida.");
						break;
					}
					}
					break;
				}
				case "4": {
					vj.excluirCadastro(caminhoViajante, ler);
					break;
				}
				case "5": {
					String opcao;
					vj.opcoesPassagens(caminhoPassagem, ler);
					do {

						System.out.println("\nDeseja fazer outra consulta? \n1 - sim; \n2 - não.");
						opcao = ler.nextLine();

						switch (opcao) {
						case "1": {
							vj.opcoesPassagens(caminhoPassagem, ler);

						}
						case "2": {
							break;
						}
						default: {
							System.out.println("Opção inválida.");
							break;
						}
						}

					} while (!opcao.equalsIgnoreCase("2"));
					break;
				}
				case "6": {
					vj.comprarPassagem(caminhoPassagem, caminhoViajante, ler, passagemComprada);
					break;
				}
				case "7": {
					vj.listarCadastros(caminhoViajante, ler);
					break;
				}
				default: {
					System.out.println("Opção inválida.");
					break;
				}
				}
				break;
			}
			case "2": {
				Vendedor vd = new Vendedor();
				vd.menu(caminhoVendedor);
				op = ler.nextLine();

				switch (op) {
				case "1": {
					System.out.println("Iniciando sessão de cadastramento...\n");
					vd.cadastrar(ler, caminhoVendedor);
					break;
				}
				case "2": {
					vd.localizarCadastro(caminhoViajante, ler);
					break;
				}
				case "3": {
					System.out.println(" Escolha a opção: \n==================");
					System.out.println("1 - para alterar nome;");
					System.out.println("2 - para alterar CPF;");
					System.out.println("3 - para alterar RG;");
					System.out.println("4 - para alterar telefone.");
					String op2 = ler.nextLine();

					switch (op2) {

					case "1": {
						vd.atualizarNome(caminhoVendedor, ler);
						break;
					}
					case "2": {
						vd.atualizarCpf(caminhoVendedor, ler);
						break;
					}
					case "3": {
						vd.atualizarRg(caminhoVendedor, ler);
						break;
					}
					case "4": {
						vd.atualizarTel(caminhoVendedor, ler);
						break;
					}
					default: {
						System.out.println("Opção inválida.");
						break;
					}
					}
					break;
				}
				case "4": {
					vd.excluirCadastro(caminhoVendedor, ler);
					break;
				}
				case "5": {
					vd.contarVendas(passagemComprada);
					break;
				}
				case "6": {
					vd.calcularSalario(passagemComprada, caminhoVendedor, ler);
					break;
				}
				case "7": {
					vd.listarCadastros(caminhoVendedor, ler);
					break;
				}
				}
				break;
			}
			case "s":
			case "S": {
				System.out.println("Saindo...\n");
				break;
			}
			default: {
				System.out.println("Opção inválida.");
				break;
			}
			}

		} while (!(op.equalsIgnoreCase("s") | op.equals("S")));
		System.out.println("Fim do programa.\n");
	}

	public static void verificarArquivo(String caminho) {
		try {
			File f = new File(caminho);
			if (!f.exists()) {
				String auxCaminho = caminho.substring(0, caminho.lastIndexOf("\\"));
				File fDir = new File(auxCaminho);
				fDir.mkdir();
				f.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}