import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;

import analyse.BooleanExpReductor;
import analyse.BooleanExpTranslator;
import analyse.ParserException;
import analyse.SyntaxException;
import lexical.LexicalException;

public class TestReduction {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("expression >");
		while (s.hasNextLine()) {
			String line = s.nextLine();
			try {
				BooleanExpReductor parser = new BooleanExpReductor(new StringReader(line));
				String result = parser.parse();
				System.out.println("expression OK : " + result);
			} catch (LexicalException | SyntaxException | ParserException | IOException e) {
				System.out.println(e);
			}
			System.out.println();
			System.out.println("expression >");
		}
		s.close();
	}
}
