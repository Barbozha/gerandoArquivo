package aplication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Produto;

public class Principal {

	public static void main(String[] args) {
		// Lendo arquivo produtos.txt e gerando arquivo valores.csv
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		String entrada = "C:\\Barboza\\Cursos\\JAVA\\ws2-eclipse\\lendoarquivo\\entrada\\produtos.txt";
		String saida = "C:\\Barboza\\Cursos\\JAVA\\ws2-eclipse\\lendoarquivo\\saida\\";
		String arquivoGerado = "valores.csv";
		List<Produto> lista = new ArrayList<>();
		
		// Lendo o arquivo de entrada (produtos.txt)
		try(BufferedReader br = new BufferedReader(new FileReader(entrada))){
			String line = br.readLine();
			while(line != null) {
				String[] campo = line.split(",");
				String nome = campo[0];
				double preco = Double.parseDouble(campo[1]);
				int quantidade = Integer.parseInt(campo[2]);
				lista.add(new Produto(nome, quantidade, preco));
				line = br.readLine();
			}
			
			// Gerando o arquivo de saída (valores.csv)
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(saida+arquivoGerado))){
				for(Produto prod : lista) {
					bw.write(prod.getNome() + "," + String.format("%.2f",prod.calcularPreco()));
					bw.newLine();
				}
				System.out.println("Arquivo "+arquivoGerado + " Criado");
			}
			catch(IOException e) {
				System.out.println("Erro de gravação: "+e.getMessage());
			}
		}
	
		catch(IOException e) {
			System.out.println("Erro de leitura: "+e.getMessage());
		}
		
		// Imprimindo o arquivo gerado
		try(BufferedReader bre = new BufferedReader(new FileReader(saida+arquivoGerado))){
			String linha = bre.readLine();
			while(linha != null) {
				System.out.println(linha);
				linha = bre.readLine();
			}
		}
		catch(IOException e){
			System.out.println("Erro de Leitura no arquivo "+arquivoGerado);
		}
		
		sc.close();

	}

}
