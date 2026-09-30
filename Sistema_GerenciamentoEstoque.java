package main;
import java.util.Scanner;
import java.util.ArrayList;
public class Sistema_GerenciamentoEstoque {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		ArrayList<String> lista = new ArrayList<>();
		
	
		int opcao = 0;
		
		while (opcao != 4) {
			
			System.out.println("---------------------------------------");
			System.out.println("Sistema de gerencimanto de estoque");
			System.out.println("1) - Ver estoque ");
			System.out.println("2) - Adicionar produto ");
			System.out.println("3) - Remover produto ");
			System.out.println("4) - Sair ");
			System.out.println("---------------------------------------");
			System.out.println("Digite um numero: ");
			opcao = sc.nextInt();
			
			
			
			if (opcao == 1) {
				System.out.println("Voce escolheu para ver o estoque: ");
				System.out.println("Total de itens: " + lista.size());
				System.out.println("Itens: ");
				System.out.println(lista);
				} 
			
			if (opcao == 2) {
				System.out.println("Digite o item que deseja: ");
				String item = sc.next();
				lista.add(item);
			}
			
			if (opcao == 3) {
			    sc.nextLine(); 
			    
			    System.out.println("Qual item deseja remover: ");
			    String remover = sc.nextLine();
			    
			    
			    if (lista.remove(remover)) {
			        System.out.println("Sucesso: '" + remover + "' foi excluído da lista!");
			    } else {
			        System.out.println("Erro: '" + remover + "' não foi encontrado no estoque!");
			    }
			}

				
			
			if (opcao == 4) {
				break;
			}
		}
		
		
		sc.close();
		
	}
		
}
