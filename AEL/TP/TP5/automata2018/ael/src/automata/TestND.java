package automata;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestND {

	/*
	 * Écriture de la représentation graphviz de l'automate dans un fichier
	 *
	 */
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

	/*
	 * Test de la méthode accept()
	 */
	@SuppressWarnings("resource")
	private static void testAccept(Automaton a) {
		if (a.accept(""))
			System.out.println("Le mot vide est accepté. ");
		else
			System.out.println("Le mot vide n'est pas accepté. ");

		Scanner in = new Scanner(System.in);
		System.out.println("Mot non vide à tester ? (mot vide pour terminer)");
		String word = in.nextLine();
		while (word.length() != 0) {
			System.out.print("> " + word);
			if (a.accept(word))
				System.out.print(" est accepté. ");
			else
				System.out.print(" n'est pas accepté.");
			System.out.println("Mot non vide à tester ? (mot vide pour terminer)");
			word = in.nextLine();
		}

	}

	public static void main(String[] args) throws StateException {

		/* Fabrication de l'automate */

		AutomatonBuilder a = new NDAutomaton();

		/*
		 * Définition des états Notez que les états sont numérotés 0, 1, 2, ... dans
		 * l'ordre de leur création dans l'automate par défaut les états sont nommés
		 * "qi", où i est leur numéro On peut leur choisir un autre nom en le passant en
		 * paramètre de la méthode addNewState
		 */

		a.addNewState();
		a.addNewState();
		a.addNewState();
		a.addNewState();
		// a.addNewState("NomQuiMePlait");

		/*
		 * Définition des états initiaux et des états acceptants Le paramètre est
		 * indifféremment le numéro ou le nom d'un état
		 */
		a.setAccepting("q0");
		a.setInitial("q0");
		a.setAccepting("q2");
		a.setInitial("q2");

		/*
		 * Définition des transitions
		 */
		a.addTransition("q0", 'a', "q1");
		a.addTransition("q1", 'b', "q0");
		a.addTransition("q2", 'b', "q3");
		a.addTransition("q3", 'a', "q2");

		/*
		 * Dessin de l'automate (fabrication d'un fichier Graphviz)
		 */
		dotToFile(a, "automate-test.dot");

		/*
		 * Affichage de l'automate, en mode texte
		 */
		System.out.println(a);

		/*
		 * Test de la méthode accept() à réactiver quand vous aurez développé une classe
		 * avec une vraie méthode accept()
		 */

		testAccept(a);

		System.out.println("Tests ACCEPT");
		System.out.println(">Test : [ ] -> "+a.accept(""));
		System.out.println(">Test : [a] -> "+a.accept("a"));
		System.out.println(">Test : [b] -> "+a.accept("b"));
		System.out.println(">Test : [aa] -> "+a.accept("aa"));
		System.out.println(">Test : [ab] -> "+a.accept("ab"));
		System.out.println(">Test : [ba] -> "+a.accept("ba"));
		System.out.println(">Test : [bb] -> "+a.accept("bb"));
		System.out.println(">Test : [aba] -> "+a.accept("aba"));
		System.out.println("Tests ACCEPT : fini\n");

		System.out.println("Tests AUTOMATAUTILS :");

		System.out.println("> Test addSingleton");
		AutomatonBuilder autB = new NDAutomaton();
		AutomatonBuilder autC = new NDAutomaton();
		AutomataUtils.addSingleton("mot", autB);
		dotToFile(autB,"autosingle.dot");
		AutomataUtils.addSingleton("abracadabra", autC);
		dotToFile(autC,"autosingle2.dot");

		System.out.println("> Test addFiniteSet");
		AutomatonBuilder aut = new NDAutomaton();
		List<String> mots = new ArrayList<String>();
		mots.add("aze");
		mots.add("qsd");
		mots.add("wxc");
		AutomataUtils.addFiniteSet(mots, aut);
		dotToFile(aut,"autoFiniteSet.dot");

		System.out.println("> Test flatExp");
		AutomatonBuilder autExp = new NDAutomaton();
		String exp = "ab*a*";
		AutomataUtils.addFlatExp(exp, autExp);
		dotToFile(autExp, "autoFlatExp.dot");

		System.out.println("> Test transpose");
		AutomatonBuilder autTrans = new NDAutomaton();
		AutomataUtils.transpose(aut, autTrans);
		dotToFile(autExp, "autoTranspose.dot");

		System.out.println("> Test determinize");
		AutomatonBuilder autDeter = new NDAutomaton();
		List<String> mots2 = new ArrayList<String>();
		mots2.add("create");
		mots2.add("at");
		mots2.add("cry");
		AutomataUtils.addFiniteSet(mots2, autDeter);
		AutomatonBuilder at = new NDAutomaton();
		AutomataUtils.determinize(autDeter, at);
		dotToFile(autExp, "autoDeterminize.dot");

		System.out.println("> Test minimalize");
		AutomatonBuilder autMin = new NDAutomaton();
		AutomataUtils.minimalise(autDeter, autMin);
		dotToFile(autMin, "autoMinimalize.dot");
		System.out.println("That's all folks");
	}
}
