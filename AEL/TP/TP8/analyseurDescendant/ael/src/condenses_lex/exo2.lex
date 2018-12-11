/* Philippot Grégoire */

%%

%unicode
ENTIER=[0-9]
LETTRE=[a|b|c]
OUVRANTE=[(]
FERMANTE=[)]
EOD=[#]
UNKNOWN=[^ENTIER||LETTRE||OUVRANTE||FERMANTE||EOD]

%%
{ENTIER}
  { return new Entier(Integer.valueOf(yytext())); }

{LETTRE}
  { return new Lettre(yytext()); }

{OUVRANTE}
  { return new Ouvrante(true);  }

{FERMANTE}
  { return new Fermante(true); }

{EOD}
  { return new Eod(yytext()); }

{UNKNOWN}
  { return new Unknown(yytext()); }
