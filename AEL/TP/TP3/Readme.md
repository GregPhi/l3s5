# TP 3 : Analyse lexicale avec JFlex - 1° partie

### Philippot Grégoire

### Commandes :

Compilation :
-------------

  * $ make compil

Exécution :
-----------

  * $ make exe

Création pdf :
--------------

  * $ make pdf

Clean :
-------

  * $ make clean

OU :
----

  * $ chmod +x exe.sh
  * $ ./exe.sh

### Exercice 1 :

<code>
  * Yytoken.java:
  private String source;
  public String getSource(){
    return source;
  }

  * ExSujet0.java:
  System.out.println( "token : " + token.getType() +" ("+token.getSource()+")");

  * exsujet0.lex:
  {X}
    { return new Yytoken(TokenType.X,yytext()); }
</code>

### Exercice 2 :

<code>
  * exsujet0.lex:
  LETTRE=[A-Za-zéèàâäêëôöùîï]
  CHIFFRE=[0-9]
  ENTIER={CHIFFRE}+
  ENTIER2={ENTIER}[\_]?{ENTIER}
  ALNUM=({CHIFFRE}|{LETTRE})
  IDENT={LETTRE}{ALNUM}*
  IDENT2={IDENT}[\_]?{ALNUM}+

  * TokenType.java:
  public enum TokenType{
    ENTIER, ENTIER2, IDENT, IDENT2, OPERATEUR;
  }
</code>

### Exercice 3 :

<code>
  jflex -dot src/exsujet0.lex
  dot -T pdf -O dfa-big.dot
  dot -T pdf -O dfa-min.dot
  dot -T pdf -O nfa.dot
</code>
