package lexical;

%%
%public
%unicode
%line
%column

%yylexthrow{
LexicalException
%yylexthrow}

%eofval{
 return new EndOfData();
%eofval}

IDENT=[A-Za-z](_?[A-Za-z0-9])*
TRUE="true"|"TRUE"
FALSE="false"|"FALSE"
AND="&&"
OR="||"
NOT="!"
OPENBRACKET="("
CLOSEBRACKET=")"

%% 

       {TRUE}
            {
                  return new Constant(true);
            }
      {FALSE}
            {
                  return new Constant(false);
            }
      {AND}
            {
                  return new And();
            }
      {OR}
            {
                  return new Or();
            }
      {NOT}
            {
                  return new Not();
            }
      {OPENBRACKET}
            {
                  return new OpenBracket();
            }
      {CLOSEBRACKET}
            {
                  return new CloseBracket ();
            }
      {IDENT}
            {
                  return new Ident(yytext());
            }


\s|\n|\r
      {
      }
.
{
  throw new LexicalException(yytext());
}
