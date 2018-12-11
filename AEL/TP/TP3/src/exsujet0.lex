/* Version 2 */

/* Philippot Grégoire */

%%

%unicode
LETTRE=[A-Za-zéèàâäêëôöùîï]
CHIFFRE=[0-9]

ENTIER={CHIFFRE}+
ENTIER2={ENTIER}[_]?{ENTIER}

ALNUM=({CHIFFRE}|{LETTRE})
IDENT={LETTRE}{ALNUM}*
IDENT2={IDENT}[_]?{ALNUM}+
IDENT3=([{]?){IDENT}
IDENT4={IDENT}[}]?

OP=[-+*/]

COMMENT=(#.*)
COMMENT2=("{"[^"}"]*"}")
COMMENT3=(<!-[^"->"]->)

%%
{ENTIER}
  { return new Yytoken(TokenType.ENTIER,yytext()); }
{IDENT}|{IDENT3}|{IDENT4}
  { return new Yytoken(TokenType.IDENT,yytext()); }
{ENTIER2}
  { return new Yytoken(TokenType.ENTIER2,yytext()); }
{IDENT2}
  { return new Yytoken(TokenType.IDENT2,yytext()); }
{OP}
  { return new Yytoken(TokenType.OPERATEUR,yytext()); }
{COMMENT}|{COMMENT2}|{COMMENT3}
  { System.out.print("COMMENTAIRE\n"); }
[\s\n]
  {}
