

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;

import lexical.LexicalException;
import lexical.TokenType;
import lexical.Yylex;
import lexical.Yytoken;

public class TestLex {

	public static void main(String[] args) throws IOException {
		File f = new File("dataBool.txt");
		Yylex yy = new Yylex(new BufferedReader(new FileReader(f)));
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

	}

}
