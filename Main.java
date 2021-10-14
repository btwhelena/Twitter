import java.util.Scanner;

import perfis.*;
import twitter.*;
import exceptions.PEException;
import exceptions.PIException;

class Main {

	public static void menuAcoes(String usuario) {
		System.out.println("");
		System.out.println("\n\nBem vindo " + usuario + "!\n");
		System.out.println("1. Tweetar");
		System.out.println("2. Ver timeline");
		System.out.println("3. Ver seus tweets");
		System.out.println("4. Seguir");
		System.out.println("5. Número de seguidores");
		System.out.println("6. Listar seguidores");
    System.out.println("7. Listar seguidos");
		System.out.println("8. Sair");
		System.out.println("9. Fechar\n");
		System.out.print("Escolha uma opção: ");
	}
	
	public static void menuLogin() {
		System.out.println("\n\nBEM VINDO AO MyTwitter\n");
		System.out.println("1. Criar perfil");
		System.out.println("2. Desativar perfil");
		System.out.println("3. Acessar perfil");
		System.out.println("4. Fechar\n");
		System.out.print("Escolha uma opção: ");
	}
	
	public static void main(String[] args) {
		String usuario;
		MyTwitter twitter = new MyTwitter();
		Scanner leitor = new Scanner(System.in);
		String entrada = "";
		while(entrada != "4") {
			menuLogin();
			entrada = leitor.nextLine();
			switch (entrada) {
			case "1":
				String pessoaTipo = "0";
				System.out.println("");
				boolean opcaoInvalida = false;
				while(pessoaTipo.charAt(0) != '1' && pessoaTipo.charAt(0) != '2') {
					if (opcaoInvalida) {
						System.out.println("\nERRO - OPÇÃO INVÁLIDA!\n");
					}
					System.out.println("Escolha uma opção:");
					System.out.println("1. Pessoa Física");
					System.out.println("2. Pessoa Jurídica");
					pessoaTipo = leitor.nextLine();
					opcaoInvalida = true;
				}
				System.out.print("\nUsuário: ");
				usuario = leitor.nextLine();
				Perfil pessoa;
				if (pessoaTipo.charAt(0) == '1') {
					System.out.print("\nInforme o CPF: ");
					long cpf = leitor.nextLong();
					leitor.nextLine();
					pessoa = new PessoaFisica(usuario, cpf);
					
				} else {
					System.out.print("\nInforme o CNPJ: ");
					long cnpj = leitor.nextLong();
					leitor.nextLine();
					pessoa = new PessoaJuridica(usuario, cnpj);
				}
				try {
					twitter.criarPerfil(pessoa);
				} catch (PEException pee) {
					System.out.println("\nERRO! " + pee.getMessage());
				}
				break;
			case "2":
				System.out.print("\nUsuário: ");
				usuario = leitor.nextLine();
				try {
					twitter.cancelarPerfil(usuario);
				} catch (Exception e) {
					System.out.println("\nERRO! " + e.getMessage());
				}
				break;
			case "3":
				System.out.print("\nUsuário: ");
				usuario = leitor.nextLine();
				boolean sair = false;
        
				while (entrada != "7" && sair == false) {
					menuAcoes(usuario);
					entrada = leitor.nextLine();
					switch (entrada) {
						case "1":
							String mensagem;
							System.out.println("\nTweet:");
							mensagem = leitor.nextLine();
							try {
								twitter.tweetar(usuario, mensagem);
							} catch (Exception e) {
								System.out.println("\nERRO! " + e.getMessage());
							}
							break;
						case "2":
							System.out.println("");
							try {
								for (Tweet tweet : twitter.timeline(usuario)) {
									System.out.println(tweet.getMensagem());
								}
							} catch (Exception e) {
								System.out.println("\nERRO! " + e.getMessage());
							}
							break;
						case "3":
							System.out.println("");
							try {
								for (Tweet tweet : twitter.tweets(usuario)) {
									System.out.println(tweet.getMensagem());
								}
							} catch(Exception e) {
								System.out.println("\nERRO! " + e.getMessage());
							}
							break;
						case "4":
							String usuarioSeguido;
							System.out.println("\nDigite o nome do usuário a ser seguido:");
							usuarioSeguido = leitor.nextLine();
							try {
								twitter.seguir(usuario, usuarioSeguido);
							} catch (Exception e) {
								System.out.println("\nERRO! " + e.getMessage());
							}			
							break;
						case "5":
							try {
								System.out.println("\nVocê tem " + twitter.numeroSeguidores(usuario) + " seguidor(es).");
							} catch (Exception e) {
								System.out.println("\nERRO! " + e.getMessage());
							}
							break;
						case "6":
							try {
								System.out.println("\nSeus seguidores são");
								for (Perfil seguidor : twitter.seguidores(usuario)) {
									System.out.println(seguidor.getUsuario());
								}
							} catch (Exception e) {
								System.out.println("\nERRO! " + e.getMessage());
							}
							break;
            case "7":
							try {
								System.out.println("\nSeus seguidos são");
								for (Perfil seguido : twitter.seguidos(usuario)) {
									System.out.println(seguido.getUsuario());
								}
							} catch (Exception e) {
								System.out.println("\nERRO! " + e.getMessage());
							}
							break;
						case "8":
							sair = true;
							break;
						case "9":
							System.exit(0);
							break;
						default:
							System.out.println("\nERRO - OPÇÃO INVÁLIDA!");
							break;
					}
				}
				break;
			case "4":
				System.exit(0);
				break;
			default:
				System.out.println("\nERRO - OPÇÃO INVÁLIDA!");
				break;
			}
		}
		leitor.close();
	}
}