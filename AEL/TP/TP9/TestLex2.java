

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;

import lexical.LexicalException;
import lexical.TokenType;
import lexical.Yylex;
import lexical.Yytoken;

public class TestLex2 {

	public static void main(String[] args) throws IOException {
		Scanner s = new Scanner(System.in);
		System.out.println("tokens >");
		while (s.hasNextLine()) {
			String line = s.nextLine();
			Yylex yy = new Yylex(new BufferedReader(new StringReader(line)));
			Yytoken token;
			try {
				do {
					token = yy.yylex();
					//System.out.print("[" + token.image() + "]");
					System.out.print(token);
				} while (token.getType() != TokenType.EOD );
			} catch(LexicalException e){
				System.out.println(e);
			}
			System.out.println();
			System.out.println("tokens >");


		}
	}

}
