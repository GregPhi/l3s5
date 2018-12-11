package ard;

import java.io.StringReader;
import java.util.Scanner;

public class TestARDExo1 {

	public static void main(String[] args) throws SyntaxException, ParserException {
		Scanner input = new Scanner(System.in);
		System.out.print("mot ? > ");
		while (input.hasNextLine()) {
			String word = input.nextLine();
			ArdExo1 parser = new ArdExo1(new StringReader(word));
			try {
				parser.parse();
				System.out.println("OK");
			} catch (SyntaxException e) {
				System.out.println("Erreur : " + e.getMessage());
				//throw e;
			}
			System.out.print("mot ? > ");
		}
		input.close();
	}

}
