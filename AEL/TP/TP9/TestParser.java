import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;

import analyse.BooleanExpParser;
import analyse.ParserException;
import analyse.SyntaxException;
import lexical.LexicalException;

public class TestParser {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("expression >");
		while (s.hasNextLine()) {
			String line = s.nextLine();
			try {
				BooleanExpParser parser = new BooleanExpParser(new StringReader(line));
				parser.parse();
				System.out.println("expression OK");
			} catch (LexicalException | SyntaxException | ParserException | IOException e) {
				System.out.println(e);
			}
			System.out.println();
			System.out.println("expression >");
		}
		s.close();
	}
}
