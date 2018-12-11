package automata;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Alexandre Boulay
 * Philippot Grégoire 
 */

public class TestAhoCo {
	public static void dotToFile(Automaton a, String fileName) {
		File f = new File(fileName);
		try {
			PrintWriter sortieDot = new PrintWriter(f);
			sortieDot.println(a.toGraphviz());
			sortieDot.close();
		} catch (IOException e) {
			System.out.println("création du fichier " + fileName + " impossible");
		}
	}
	
	public static void startCMD(String command){
		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = "";
			while((line=r.readLine())!=null){
				System.out.println(line);
			}
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		AhoCorasick aC = new AhoCorasick("try","cry","create","at");
		dotToFile(aC, "automate-test.dot");
		startCMD("dot -Tpdf automate-test.dot -o automate-test.pdf");
		System.out.println("That's all folks");
	}
}
