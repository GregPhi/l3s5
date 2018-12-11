/* postfixee version 1 */

/* Philippot Grégoire */

%%

%state YYSTOCK

CHIFFRE=[0-9]
ENTIER=[1-9]{CHIFFRE}*

OCTAL=(0)[0-7]+
HEX=0(x|X)[0-9A-Fa-f]+

PLUS=[+]
MINUS=[-]
MULTI=[*]
DIV=[/]
OPP=opp|OPP
LETTRE=[A-Za-z]
ALNUM=({CHIFFRE}|{LETTRE})
IDENT={LETTRE}({ALNUM})*

STOCK=->

COMMENT=(#.*)
COMMENT3=<!- [^"->"] ->

%%
<YYINITIAL>{
  {ENTIER}|{OCTAL}|{HEX}
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

  {COMMENT}|{COMMENT3}
    { return new Comment(yytext()); }

  {IDENT}
    { return new Ident(yytext()); }

  {STOCK}
    { yybegin(YYSTOCK); }

  [\s\n]|[" "]
    {}
}

<YYSTOCK>{
  [\s\n]|[" "]
    {}

  {IDENT}
    {
      yybegin(YYINITIAL);
      return new Stockage(yytext());
    }
}
