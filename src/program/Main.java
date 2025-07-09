package program;

import java.util.Locale;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.println("O que você deseja fazer?");
		System.out.println("");
		System.out.println("[1] Criar conta");
		System.out.println("[2] Acessar conta");

		sc.close();
	}

}