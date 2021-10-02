package control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Vendedor extends Pessoa {
	private String salario, comissao, nroVendas, nroCadastro, descontoSalario;

	public Vendedor() {

	}

	Vendedor(String nome, String rg, String cpf, String tel) {
		this.setCpf(cpf);
		this.setRg(rg);
		this.setNome(nome);
		this.setTel(tel);
	}

	public void menu(String caminhoVendedor) {
		System.out.println("Carregando área do vendedor...\n");
		System.out.println(" Escolha a opção: \n==================");
		System.out.println("1 - para cadastrar-se no site;");
		System.out.println("2 - para verificar se o cadastro existe;");
		System.out.println("3 - para alterar informações do cadastro;");
		System.out.println("4 - para excluir um cadastro existente;");
		System.out.println("5 - para verificar quantas vendas realizou;");
		System.out.println("6 - para verificar dados do salário;");
		System.out.println("7 - para verificar informações de cadastros existentes.");
	}

	public void cadastrar(Scanner ler, String caminhoVendedor) {
		System.out.print("Digite o nome completo: ");
		setNome(ler.nextLine());
		System.out.print("Digite o CPF (somente números - 11 dígitos): ");
		String aux = ler.nextLine();
		System.out.print("Digite o RG: ");
		setRg(ler.nextLine());
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
				salvarVendedor(caminhoVendedor);

			}
		}
	}

	public int lerUltimoRegistro(String caminhoVendedor) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(caminhoVendedor));
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

	public void salvarVendedor(String caminhoVendedor) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoVendedor, true));
			bw.write(lerUltimoRegistro(caminhoVendedor) + "#" + getNome() + "#" + getCpf() + "#" + getRg() + "#"
					+ getTel());
			bw.newLine();
			bw.close();
			System.out.println("\nCadastro realizado com sucesso!\n");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public String[] localizarCadastro(String caminhoVendedor, Scanner ler) {
		try {
			System.out.println("Digite o número de localização do cadastro: ");
			String nrCadastro = ler.nextLine();
			setNroCadastro(nrCadastro);
			BufferedReader ler2 = new BufferedReader(new FileReader(caminhoVendedor));

			String[] vetor;

			while (ler2.ready()) {
				ler2.ready();
				vetor = ler2.readLine().split("#");

				if (vetor[0].equalsIgnoreCase(nrCadastro)) {

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

	public void atualizarNome(String caminhoVendedor, Scanner ler) {
		try {
			String i = caminhoVendedor.replace("caminhoVendedor.txt", "vendedorTemporaria.txt");
			File j = new File(i);
			j.createNewFile();

			BufferedReader br = new BufferedReader(new FileReader(caminhoVendedor));
			BufferedWriter bw = new BufferedWriter(new FileWriter(i));

			System.out.println("Digite o número de localização do cadastro: ");
			String nrCadastro = ler.nextLine();
			String[] vetor;

			while (br.ready()) {
				br.ready();
				String linha = br.readLine();
				vetor = linha.split("#");

				if (vetor[0].equalsIgnoreCase(nrCadastro)) {
					System.out.print("Digite o novo nome completo: ");
					setNome(ler.nextLine());
					bw.write(vetor[0] + "#" + getNome() + "#" + vetor[2] + "#" + vetor[3] + "#" + vetor[4]);
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
			BufferedWriter bwaux = new BufferedWriter(new FileWriter(caminhoVendedor));

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

	public void atualizarCpf(String caminhoVendedor, Scanner ler) {
		try {
			String i = caminhoVendedor.replace("caminhoVendedor.txt", "vendedorTemporaria.txt");
			File j = new File(i);
			j.createNewFile();

			BufferedReader br = new BufferedReader(new FileReader(caminhoVendedor));
			BufferedWriter bw = new BufferedWriter(new FileWriter(i));

			System.out.println("Digite o número de localização do cadastro: ");
			String nrCadastro = ler.nextLine();
			String[] vetor;

			while (br.ready()) {
				br.ready();
				String linha = br.readLine();
				vetor = linha.split("#");

				if (vetor[0].equalsIgnoreCase(nrCadastro)) {
					System.out.print("Digite o novo CPF: ");
					String aux = ler.nextLine();
					int m = 0;
					for (int n = 0; n < aux.length(); n++) {
						m++;
						if (m == 11) {
							String[] vetorAux = aux.split("");
							setCpf(vetorAux[0] + vetorAux[1] + vetorAux[2] + "." + vetorAux[3] + vetorAux[4]
									+ vetorAux[5] + "." + vetorAux[6] + vetorAux[7] + vetorAux[8] + "-" + vetorAux[9]
									+ vetorAux[10]);

							bw.write(vetor[0] + "#" + vetor[1] + "#" + getCpf() + "#" + vetor[3] + "#" + vetor[4]);
							bw.newLine();
							System.out.println("CPF atualizado com sucesso!");
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
			BufferedWriter bwaux = new BufferedWriter(new FileWriter(caminhoVendedor));

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

	public void atualizarRg(String caminhoVendedor, Scanner ler) {
		try {
			String i = caminhoVendedor.replace("caminhoVendedor.txt", "vendedorTemporaria.txt");
			File j = new File(i);
			j.createNewFile();

			BufferedReader br = new BufferedReader(new FileReader(caminhoVendedor));
			BufferedWriter bw = new BufferedWriter(new FileWriter(i));

			System.out.println("Digite o número de localização do cadastro: ");
			String nrCadastro = ler.nextLine();
			String[] vetor;

			while (br.ready()) {
				br.ready();
				String linha = br.readLine();
				vetor = linha.split("#");

				if (vetor[0].equalsIgnoreCase(nrCadastro)) {
					System.out.print("Digite o novo RG: ");
					setRg(ler.nextLine());
					bw.write(vetor[0] + "#" + vetor[1] + "#" + vetor[2] + "#" + getRg() + "#" + vetor[4]);
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
			BufferedWriter bwaux = new BufferedWriter(new FileWriter(caminhoVendedor));

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

	public void atualizarTel(String caminhoVendedor, Scanner ler) {
		try {
			String i = caminhoVendedor.replace("caminhoVendedor.txt", "vendedorTemporaria.txt");
			File j = new File(i);
			j.createNewFile();

			BufferedReader br = new BufferedReader(new FileReader(caminhoVendedor));
			BufferedWriter bw = new BufferedWriter(new FileWriter(i));

			System.out.println("Digite o número de localização do cadastro: ");
			String nrCadastro = ler.nextLine();
			String[] vetor;

			while (br.ready()) {
				br.ready();
				String linha = br.readLine();
				vetor = linha.split("#");

				if (vetor[0].equalsIgnoreCase(nrCadastro)) {
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

							bw.write(vetor[0] + "#" + vetor[1] + "#" + vetor[2] + "#" + vetor[3] + "#" + getTel());
							bw.newLine();
							System.out.println("Telefone atualizado com sucesso!");
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
			BufferedWriter bwaux = new BufferedWriter(new FileWriter(caminhoVendedor));

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

	public void excluirCadastro(String caminhoVendedor, Scanner ler) {
		try {
			String i = caminhoVendedor.replace("caminhoVendedor.txt", "vendedorTemporaria.txt");
			File f = new File(i);
			f.createNewFile();

			BufferedReader br = new BufferedReader(new FileReader(caminhoVendedor));
			BufferedWriter bw = new BufferedWriter(new FileWriter(i));

			System.out.println("Digite o número de localização do cadastro: ");
			String nrCadastro = ler.nextLine();
			String[] vetor;

			while (br.ready()) {
				br.ready();
				String linha = br.readLine();
				vetor = linha.split("#");

				if (vetor[0].equalsIgnoreCase(nrCadastro)) {
					System.out.println("Cadastro excluído com sucesso!");
				} else {
					bw.write(linha);
					bw.newLine();
				}
			}
			br.close();
			bw.close();

			BufferedReader braux2 = new BufferedReader(new FileReader(i));
			BufferedWriter bwaux2 = new BufferedWriter(new FileWriter(caminhoVendedor));

			while (braux2.ready()) {
				String[] vetor2;
				String linha = braux2.readLine();
				vetor2 = linha.split("#");
				bwaux2.write("#" + vetor2[1] + "#" + vetor2[2] + "#" + vetor2[3] + "#" + vetor2[4]);
				bwaux2.newLine();
			}
			bwaux2.close();
			braux2.close();
			f.delete();

			String j = caminhoVendedor.replace("caminhoVendedor.txt", "vendedorTemp.txt");
			File ff = new File(j);
			ff.createNewFile();

			BufferedReader br2 = new BufferedReader(new FileReader(caminhoVendedor));
			BufferedWriter bw2 = new BufferedWriter(new FileWriter(j));

			String[] vetor2;
			int inicial = 100;
			int m = 0;

			while (br2.ready()) {
				String linha = br2.readLine();
				vetor2 = linha.split("#");

				if (m == 0) {
					bw2.write(inicial + "#" + vetor2[1] + "#" + vetor2[2] + "#" + vetor2[3] + "#" + vetor2[4]);
					bw2.newLine();
					inicial++;
					m++;
				} else {
					bw2.write(inicial + "#" + vetor2[1] + "#" + vetor2[2] + "#" + vetor2[3] + "#" + vetor2[4]);
					bw2.newLine();
					inicial++;
				}
			}
			br2.close();
			bw2.close();

			BufferedReader braux = new BufferedReader(new FileReader(j));
			BufferedWriter bwaux = new BufferedWriter(new FileWriter(caminhoVendedor));

			while (braux.ready()) {
				String linha = braux.readLine();
				bwaux.write(linha);
				bwaux.newLine();
			}
			bwaux.close();
			braux.close();
			ff.delete();

		} catch (Exception e) {
			System.out.println("Erro no programa.");
		}
	}

	public void contarVendas(String passagemComprada) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(passagemComprada));
			int cont = 0;

			while (br.ready()) {
				String linha = br.readLine();
				String[] vetor = linha.split("#");
				if (!vetor[0].equals("")) {
					cont++;
				}
			}
			br.close();

			setNroVendas(cont + "");
			System.out.println("Total de vendas realizadas: " + getNroVendas() + " vendas.");
		} catch (Exception e) {
			System.out.println("Erro no programa.");
		}
	}

	public void calcularComissao(String passagemComprada) {
		try {
			contarVendas(passagemComprada);
			int calculoComissao = (2 * 1200 / 100) * Integer.parseInt(getNroVendas());
			setComissao(calculoComissao + "");
			System.out.println("Sua comissão, atualmente, é: " + getComissao() + ",00 reais.");
		} catch (Exception e) {
			System.out.println("Erro no programa.");
		}
	}

	public void calcularSalario(String passagemComprada, String caminhoVendedor, Scanner ler) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(caminhoVendedor));

			System.out.println("Digite o número de localização do seu cadastro: ");
			String nrCadastro = ler.nextLine();
			setDescontoSalario("100");
			setSalario("1200");

			while (br.ready()) {
				String linha = br.readLine();
				String[] vetor = linha.split("#");

				if (vetor[0].equalsIgnoreCase(nrCadastro)) {
					calcularComissao(passagemComprada);
					System.out.println("Soma dos descontos mensais fixos: " + getDescontoSalario() + ",00 reais.");
					int auxDesc = Integer.parseInt(getDescontoSalario());
					int auxSal = Integer.parseInt(getSalario());
					int auxComis = Integer.parseInt(getComissao());
					int total = auxSal + auxComis - auxDesc;
					System.out.println("Salário total: " + total + ",00 reais.");
				}
			}
			br.close();
		} catch (Exception e) {
			System.out.println("aq");
		}
	}

	public String[] listarCadastros(String caminhoVendedor, Scanner ler) {
		try {
			BufferedReader ler2 = new BufferedReader(new FileReader(caminhoVendedor));

			while (ler2.ready()) {
				String[] vetor;
				vetor = ler2.readLine().split("#");

				System.out.println("\nNúmero localização: " + vetor[0] + "\nNome: " + vetor[1] + "\nCPF: " + vetor[2]
						+ "\nRG: " + vetor[3] + "\nTelefone: " + vetor[4]);
			}
			ler2.close();

			return null;

		} catch (IOException e) {
			System.out.println("Erro no programa.");
		}
		ler.close();
		return null;
	}

	public String getSalario() {
		return salario;
	}

	public void setSalario(String salario) {
		this.salario = salario;
	}

	public String getComissao() {
		return comissao;
	}

	public void setComissao(String comissao) {
		this.comissao = comissao;
	}

	public String getNroVendas() {
		return nroVendas;
	}

	public void setNroVendas(String nroVendas) {
		this.nroVendas = nroVendas;
	}

	public String getNroCadastro() {
		return nroCadastro;
	}

	public void setNroCadastro(String nroCadastro) {
		this.nroCadastro = nroCadastro;
	}

	public String getDescontoSalario() {
		return descontoSalario;
	}

	public void setDescontoSalario(String descontoSalario) {
		this.descontoSalario = descontoSalario;
	}
}