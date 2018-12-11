/* postfixee version 1 */

/* Philippot Grégoire */

%%

%unicode
CHIFFRE=[0-9]
ENTIER={CHIFFRE}+
ENTIER_SIGNE=[+-]{ENTIER}
PLUS=[+]
MINUS=[-]
MULTI=[*]
DIV=[/]
SPACE=[:space:]
END_LINE=[^$]
OPP=opp|OPP

COMMENT=(#.*)
COMMENT3=<!- [^"->"] ->

%%
{ENTIER}|{ENTIER_SIGNE}
  { return new Entier(yytext()); }

{PLUS}
  { return new Plus(yytext()); }

{MINUS}
  { return new Moins(yytext()); }

{MULTI}
  { return new Mult(yytext()); }

{DIV}
  { return new Div(yytext()); }

{OPP}
  { return new Opp(yytext()); }

{SPACE}|{END_LINE}
  {}

{COMMENT}|{COMMENT3}
  { return new Comment(yytext()); }
