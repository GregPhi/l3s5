package condenses_lex;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

import condenses_lex.*;

public class Exo2 {
 /*
  * Usage :
  *
  *   java ExSujet0 <nom_de_fichier> : analyse des données contenues dans le fichier
  *   ou
  *   java ExSujet0  : analyse des données sur l'entrée standard (console)
  */
  public static void main(String[] args) throws java.io.FileNotFoundException, java.io.IOException {
   Yylex tokenizer;
      // instanciation de l'analyseur lexical (tokenizer) :
        //tokenizer = new Yylex(new BufferedReader(new FileReader("data.txt")));
   if (args.length>0)
    tokenizer = new Yylex(new BufferedReader(new FileReader(args[0])));          // lecture dans le fichier donné en argument
   else
    tokenizer = new Yylex(new BufferedReader(new InputStreamReader(System.in))); // lecture de l'entrée standard

    // recherche du premier token :
   Yytoken token  = tokenizer.yylex();
    // traitement et lecture des suivants :
    while ( token != null ) {
      System.out.println( "token : " + token.getType()+" value : "+token.toString());
      token =  tokenizer.yylex();  // recherche du token suivant
    }
  }
}
