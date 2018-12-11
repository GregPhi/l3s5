package ard;

/**
 * TP 8 AEL : Main 
 * @author : Philippot Gregoire
 */

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) throws ParserException {
		List<String> mots = new ArrayList<String>();
		mots.add("a");
		mots.add("(a)");
		mots.add("abc");
		mots.add("a000");
		System.out.println("--> EXERCICE 1 : ");
		ArdExo1 exo1;
		Reader word;
		for(String m : mots) {
			System.out.print(m+" => ");
			word = new StringReader(m);
			exo1 = new ArdExo1(word);
			try {
				exo1.parse();
				System.out.println("OK");
			} catch (SyntaxException e) {
				System.out.println("Erreur : " + e.getMessage());
			}
		}
		System.out.println("--> EXERCICE 2 : ");
		ArdExo2 exo2;
		for(String m : mots) {
			System.out.print(m+" => ");
			word = new StringReader(m);
			exo2 = new ArdExo2(word);
			try {
				exo2.parse();
				System.out.println("OK");
			} catch (SyntaxException e) {
				System.out.println("Erreur : " + e.getMessage());
			}
		}
	}
}
