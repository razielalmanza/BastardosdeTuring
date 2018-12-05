//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";



package ast;



//#line 2 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
  import ast.patron.compuesto.*;
  import ast.patron.tipos.*;
  import java.lang.Math;
  import java.io.*;
//#line 22 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//## **user defined:Nodo
String   yytext;//user variable to return contextual strings
Nodo yyval; //used to return semantic vals from action routines
Nodo yylval;//the 'lval' (result) I got from yylex()
Nodo valstk[] = new Nodo[YYSTACKSIZE];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
final void val_init()
{
  yyval=new Nodo();
  yylval=new Nodo();
  valptr=-1;
}
final void val_push(Nodo val)
{
  try {
    valptr++;
    valstk[valptr]=val;
  }
  catch (ArrayIndexOutOfBoundsException e) {
    int oldsize = valstk.length;
    int newsize = oldsize*2;
    Nodo[] newstack = new Nodo[newsize];
    System.arraycopy(valstk,0,newstack,0,oldsize);
    valstk = newstack;
    valstk[valptr]=val;
  }
}
final Nodo val_pop()
{
  return valstk[valptr--];
}
final void val_drop(int cnt)
{
  valptr -= cnt;
}
final Nodo val_peek(int relative)
{
  return valstk[valptr-relative];
}
final Nodo dup_yyval(Nodo val)
{
  return val;
}
//#### end semantic value section ####
public final static short CADENA=257;
public final static short SALTO=258;
public final static short IDENTIFICADOR=259;
public final static short ENTERO=260;
public final static short REAL=261;
public final static short BOOLEANO=262;
public final static short DEINDENTA=263;
public final static short INDENTA=264;
public final static short AND=265;
public final static short OR=266;
public final static short NOT=267;
public final static short WHILE=268;
public final static short FOR=269;
public final static short PRINT=270;
public final static short ELIF=271;
public final static short ELSE=272;
public final static short IF=273;
public final static short MAS=274;
public final static short MENOS=275;
public final static short POR=276;
public final static short POTENCIA=277;
public final static short DIV=278;
public final static short DIVENTERA=279;
public final static short MODULO=280;
public final static short LE=281;
public final static short GR=282;
public final static short LEQ=283;
public final static short GRQ=284;
public final static short EQUALS=285;
public final static short DIFF=286;
public final static short EQ=287;
public final static short PA=288;
public final static short PC=289;
public final static short DOBLEPUNTO=290;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    0,    1,    1,    1,    1,    2,    2,    4,    4,
    5,    5,    6,    8,    8,    9,    9,    3,   10,   10,
   11,   11,   12,    7,   13,   13,   15,   15,   14,   14,
   17,   17,   16,   16,   18,   18,   20,   20,   21,   21,
   21,   21,   21,   21,   19,   19,   23,   23,   23,   23,
   22,   22,   25,   25,   25,   25,   25,   25,   25,   25,
   24,   24,   24,   26,   26,   27,   27,   27,   27,   27,
   27,
};
final static short yylen[] = {                            2,
    0,    1,    1,    1,    2,    2,    1,    1,    1,    1,
    7,    4,    4,    1,    4,    1,    2,    2,    1,    1,
    1,    3,    2,    1,    1,    2,    2,    3,    1,    2,
    2,    3,    2,    1,    1,    2,    2,    3,    1,    1,
    1,    1,    1,    1,    1,    2,    2,    2,    3,    3,
    1,    2,    2,    2,    2,    2,    3,    3,    3,    3,
    2,    2,    1,    1,    3,    1,    1,    1,    1,    1,
    3,
};
final static short yydefred[] = {                         0,
   68,    3,   66,   67,   69,   70,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    4,    7,    8,    9,   10,
    0,    0,   19,   20,   24,    0,    0,    0,    0,   34,
    0,    0,    0,    0,    0,    0,   63,    0,   33,    0,
   23,    0,   61,   62,    0,    5,    6,    0,   18,   27,
    0,   31,    0,   39,   40,   43,   42,   41,   44,   37,
    0,   47,   48,    0,   53,   56,   54,   55,    0,    0,
    0,    0,   71,   22,   28,   32,   38,   49,   50,   57,
   60,   58,   59,   65,    0,   14,   13,    0,    0,    0,
   16,    0,    0,   15,   17,   11,
};
final static short yydgoto[] = {                         14,
   15,   16,   17,   18,   19,   20,   21,   87,   92,   22,
   23,   24,   25,   26,   27,   28,   29,   30,   31,   32,
   60,   33,   34,   35,   36,   37,   38,
};
final static short yysindex[] = {                       -97,
    0,    0,    0,    0,    0,    0,   20,   20,   20,   20,
   31,   31,   20,    0,  -78,    0,    0,    0,    0,    0,
 -280, -240,    0,    0,    0, -247,   20, -242,   20,    0,
 -153,   31, -264,   31,   21,   31,    0, -230,    0, -239,
    0, -235,    0,    0, -233,    0,    0,   20,    0,    0,
 -216,    0, -203,    0,    0,    0,    0,    0,    0,    0,
 -153,    0,    0, -262,    0,    0,    0,    0,   33,   31,
  -21,  -21,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0, -201,    0,    0, -196,  -40, -210,
    0,  -59,  -21,    0,    0,    0,
};
final static short yyrindex[] = {                        85,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   86,    0,    0,    0,    0,    0,
 -167,    0,    0,    0,    0, -135,    0, -165,    0,    0,
 -244,    0, -176,    0, -217,    0,    0, -250,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
 -131,    0, -140,    0,    0,    0,    0,    0,    0,    0,
 -206,    0,    0, -146,    0,    0,    0,    0, -187,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    1,    0,    0,
    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,  -15,  -18,    0,    0,    0,   -4,  -55,    0,    0,
    0,    0,    0,   65,    0,   13,    0,    0,   72,    0,
   54,   82,    0,   -9,    0,    0,    0,
};
final static int YYTABLESIZE=319;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         47,
   12,   43,   44,   40,   41,   42,   48,   64,   45,   62,
   63,   78,   79,   35,   64,   64,   88,   49,   50,   39,
   35,   35,   52,   64,   64,   64,   69,   64,   64,   64,
   64,   64,   64,   64,   64,   64,   64,   96,   64,   64,
   51,   53,   35,   74,   35,   35,   70,   51,   51,   75,
   71,   36,   86,   86,   72,   73,   51,   51,   36,   36,
   84,   76,   89,   51,   51,   51,   51,   51,   51,   51,
   52,   51,   51,   91,   86,   90,   95,   52,   52,   93,
   36,   45,   36,   36,    1,    2,   52,   52,   45,   45,
   21,   51,   29,   52,   52,   52,   52,   52,   52,   52,
   29,   52,   52,   61,   45,   45,   45,   45,   45,   45,
   45,   46,   45,   45,   77,   64,    0,   30,   46,   46,
    0,   29,   25,   29,   29,   30,   26,   54,   55,   56,
   57,   58,   59,    0,   46,   46,   46,   46,   46,   46,
   46,    0,   46,   46,    0,    0,   30,    0,   30,   30,
    0,   25,    0,   25,   25,   26,    0,   26,   26,    1,
    2,    3,    4,    5,    6,    0,    0,    0,    0,    7,
    8,    0,    9,    0,    0,   10,   11,   12,    1,   46,
    3,    4,    5,    6,    0,    0,    0,    0,    7,    8,
   13,    9,    0,    0,   10,   11,   12,    1,    0,    3,
    4,    5,    6,   94,    0,    0,    0,    7,    8,   13,
    9,    0,    0,   10,   11,   12,    1,    0,    3,    4,
    5,    6,    0,    0,    0,    0,    7,    8,   13,    9,
    0,    0,   10,   11,   12,    1,   85,    3,    4,    5,
    6,    0,    0,    0,    0,    7,    0,   13,    9,    0,
    0,    0,   11,   12,    0,    0,    0,   12,   12,   12,
   12,   12,   12,   12,    0,    0,   13,   12,   12,    0,
   12,    0,    0,   12,   12,   12,    1,    0,    3,    4,
    5,    6,    0,    0,    0,    0,    7,    1,   12,    3,
    4,    5,    6,   11,   12,    0,   65,    0,   66,   67,
   68,    0,    0,    0,   11,   12,    0,   13,   80,    0,
   81,   82,   83,    0,    0,    0,    0,    0,   13,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         15,
    0,   11,   12,    8,    9,   10,  287,  258,   13,  274,
  275,  274,  275,  258,  265,  266,   72,  258,  266,    7,
  265,  266,  265,  274,  275,  276,   36,  278,  279,  280,
  281,  282,  283,  284,  285,  286,  287,   93,  289,  290,
  258,   29,  287,   48,  289,  290,  277,  265,  266,  266,
  290,  258,   71,   72,  290,  289,  274,  275,  265,  266,
   70,  265,  264,  281,  282,  283,  284,  285,  286,  287,
  258,  289,  290,   89,   93,  272,   92,  265,  266,  290,
  287,  258,  289,  290,    0,    0,  274,  275,  265,  266,
  258,   27,  258,  281,  282,  283,  284,  285,  286,  287,
  266,  289,  290,   32,  281,  282,  283,  284,  285,  286,
  287,  258,  289,  290,   61,   34,   -1,  258,  265,  266,
   -1,  287,  258,  289,  290,  266,  258,  281,  282,  283,
  284,  285,  286,   -1,  281,  282,  283,  284,  285,  286,
  287,   -1,  289,  290,   -1,   -1,  287,   -1,  289,  290,
   -1,  287,   -1,  289,  290,  287,   -1,  289,  290,  257,
  258,  259,  260,  261,  262,   -1,   -1,   -1,   -1,  267,
  268,   -1,  270,   -1,   -1,  273,  274,  275,  257,  258,
  259,  260,  261,  262,   -1,   -1,   -1,   -1,  267,  268,
  288,  270,   -1,   -1,  273,  274,  275,  257,   -1,  259,
  260,  261,  262,  263,   -1,   -1,   -1,  267,  268,  288,
  270,   -1,   -1,  273,  274,  275,  257,   -1,  259,  260,
  261,  262,   -1,   -1,   -1,   -1,  267,  268,  288,  270,
   -1,   -1,  273,  274,  275,  257,  258,  259,  260,  261,
  262,   -1,   -1,   -1,   -1,  267,   -1,  288,  270,   -1,
   -1,   -1,  274,  275,   -1,   -1,   -1,  257,  258,  259,
  260,  261,  262,  263,   -1,   -1,  288,  267,  268,   -1,
  270,   -1,   -1,  273,  274,  275,  257,   -1,  259,  260,
  261,  262,   -1,   -1,   -1,   -1,  267,  257,  288,  259,
  260,  261,  262,  274,  275,   -1,  276,   -1,  278,  279,
  280,   -1,   -1,   -1,  274,  275,   -1,  288,  276,   -1,
  278,  279,  280,   -1,   -1,   -1,   -1,   -1,  288,
};
}
final static short YYFINAL=14;
final static short YYMAXTOKEN=290;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"CADENA","SALTO","IDENTIFICADOR","ENTERO","REAL","BOOLEANO",
"DEINDENTA","INDENTA","AND","OR","NOT","WHILE","FOR","PRINT","ELIF","ELSE","IF",
"MAS","MENOS","POR","POTENCIA","DIV","DIVENTERA","MODULO","LE","GR","LEQ","GRQ",
"EQUALS","DIFF","EQ","PA","PC","DOBLEPUNTO",
};
final static String yyrule[] = {
"$accept : input",
"input :",
"input : aux0",
"aux0 : SALTO",
"aux0 : stmt",
"aux0 : aux0 SALTO",
"aux0 : aux0 stmt",
"stmt : simple_stmt",
"stmt : compound_stmt",
"compound_stmt : if_stmt",
"compound_stmt : while_stmt",
"if_stmt : IF test DOBLEPUNTO suite ELSE DOBLEPUNTO suite",
"if_stmt : IF test DOBLEPUNTO suite",
"while_stmt : WHILE test DOBLEPUNTO suite",
"suite : simple_stmt",
"suite : SALTO INDENTA auxstmt DEINDENTA",
"auxstmt : stmt",
"auxstmt : auxstmt stmt",
"simple_stmt : small_stmt SALTO",
"small_stmt : expr_stmt",
"small_stmt : print_stmt",
"expr_stmt : test",
"expr_stmt : test EQ test",
"print_stmt : PRINT test",
"test : or_test",
"or_test : and_test",
"or_test : aux2 and_test",
"aux2 : and_test OR",
"aux2 : aux2 and_test OR",
"and_test : not_test",
"and_test : aux7 not_test",
"aux7 : not_test AND",
"aux7 : aux7 not_test AND",
"not_test : NOT not_test",
"not_test : comparison",
"comparison : expr",
"comparison : aux4 expr",
"aux4 : expr comp_op",
"aux4 : aux4 expr comp_op",
"comp_op : LE",
"comp_op : GR",
"comp_op : EQUALS",
"comp_op : GRQ",
"comp_op : LEQ",
"comp_op : DIFF",
"expr : term",
"expr : aux8 term",
"aux8 : term MAS",
"aux8 : term MENOS",
"aux8 : aux8 term MAS",
"aux8 : aux8 term MENOS",
"term : factor",
"term : aux9 factor",
"aux9 : factor POR",
"aux9 : factor DIVENTERA",
"aux9 : factor MODULO",
"aux9 : factor DIV",
"aux9 : aux9 factor POR",
"aux9 : aux9 factor DIVENTERA",
"aux9 : aux9 factor MODULO",
"aux9 : aux9 factor DIV",
"factor : MAS factor",
"factor : MENOS factor",
"factor : power",
"power : atom",
"power : atom POTENCIA factor",
"atom : IDENTIFICADOR",
"atom : ENTERO",
"atom : CADENA",
"atom : REAL",
"atom : BOOLEANO",
"atom : PA test PC",
};

//#line 241 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
private Flexer lexer;
/* Nodo Raiz del AST */
public Nodo raíz;

/* Comunicación con el analizador léxico */
private int yylex () {
    int yyl_return = -1;
    try {
      yyl_return = lexer.yylex();
    }
    catch (IOException e) {
      System.err.println("IO error :"+e);
    }
    return yyl_return;
}

/* Reporta errores y para ejecución. */
public void yyerror (String error) {
   System.err.println ("Error sintáctico en la línea " + lexer.line());
   System.exit(1);
}

/* lexer es creado en el constructor. */
public Parser(Reader r) {
    lexer = new Flexer(r, this);
    //yydebug = true;
}
//#line 396 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 21 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{raíz = yyval; System.out.println("Reconocimiento Exitoso");}
break;
case 2:
//#line 22 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{raíz = val_peek(0); System.out.println("Reconocimiento Exitoso");}
break;
case 4:
//#line 27 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{yyval = val_peek(0);}
break;
case 5:
//#line 28 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{yyval = val_peek(1);}
break;
case 6:
//#line 29 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{yyval = new NodoOperador("raiz",Operador.RAIZ);
    yyval.agregaHijoPrincipio(val_peek(1));
    yyval.agregaHijoFinal(val_peek(0));
    }
break;
case 7:
//#line 36 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{yyval = val_peek(0);}
break;
case 8:
//#line 37 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{yyval = val_peek(0);}
break;
case 9:
//#line 41 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{yyval = val_peek(0);}
break;
case 10:
//#line 42 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{yyval = val_peek(0);}
break;
case 11:
//#line 46 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{
    yyval = new NodoOperador("if",Operador.ELSE);yyval.agregaHijoPrincipio(val_peek(5));
    yyval.agregaHijoFinal(val_peek(3));
    yyval.agregaHijoFinal(val_peek(0));
}
break;
case 12:
//#line 51 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{
            yyval= new NodoOperador("if",Operador.IF);
            yyval.agregaHijoPrincipio(val_peek(2));
            yyval.agregaHijoFinal(val_peek(0));
        }
break;
case 13:
//#line 59 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{
    yyval = new NodoOperador("while",Operador.WHILE);
    yyval.agregaHijoPrincipio(val_peek(2));
    yyval.agregaHijoFinal(val_peek(0));
}
break;
case 14:
//#line 67 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{yyval = val_peek(0);}
break;
case 15:
//#line 68 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{yyval = val_peek(1);}
break;
case 16:
//#line 72 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{yyval = val_peek(0);}
break;
case 17:
//#line 73 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{yyval = new NodoOperador("bloque",Operador.BLOQUE);
       yyval.agregaHijoPrincipio(val_peek(1));
       yyval.agregaHijoFinal(val_peek(0));}
break;
case 18:
//#line 79 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{yyval = val_peek(1);}
break;
case 19:
//#line 83 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{yyval = val_peek(0);}
break;
case 20:
//#line 84 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{yyval = val_peek(0);}
break;
case 21:
//#line 88 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{yyval = val_peek(0);}
break;
case 22:
//#line 89 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{yyval=new NodoOperador("=",Operador.EQ);
         yyval.agregaHijoPrincipio(val_peek(2));
         yyval.agregaHijoFinal(val_peek(0));
         }
break;
case 23:
//#line 96 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{yyval = new NodoOperador("print",Operador.PRINT); yyval.agregaHijoPrincipio(val_peek(0));}
break;
case 24:
//#line 100 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{yyval = val_peek(0);}
break;
case 25:
//#line 104 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{yyval = val_peek(0);}
break;
case 26:
//#line 105 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{val_peek(1).agregaHijoFinal(val_peek(0));yyval=val_peek(1);}
break;
case 27:
//#line 108 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{yyval = new NodoOperador("or",Operador.OR);yyval.agregaHijoPrincipio(val_peek(1));}
break;
case 28:
//#line 109 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{
        yyval = new NodoOperador("or",Operador.OR);
        val_peek(2).agregaHijoFinal(val_peek(1));
        yyval.agregaHijoPrincipio(val_peek(2));
    }
break;
case 29:
//#line 117 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{yyval = val_peek(0);}
break;
case 30:
//#line 118 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{val_peek(1).agregaHijoFinal(val_peek(0));yyval=val_peek(1);}
break;
case 31:
//#line 122 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{yyval = new NodoOperador("and");yyval.agregaHijoPrincipio(val_peek(1));}
break;
case 32:
//#line 123 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{
        yyval = new NodoOperador("and",Operador.AND);
        val_peek(2).agregaHijoFinal(val_peek(1));
        yyval.agregaHijoPrincipio(val_peek(2));
    }
break;
case 33:
//#line 131 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{
    yyval = new NodoOperador("not",Operador.NOT); yyval.agregaHijoPrincipio(val_peek(0));
    }
break;
case 34:
//#line 134 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{yyval = val_peek(0);}
break;
case 35:
//#line 138 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{yyval = val_peek(0);}
break;
case 36:
//#line 139 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{val_peek(1).agregaHijoFinal(val_peek(0));yyval=val_peek(1);}
break;
case 37:
//#line 143 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{val_peek(0).agregaHijoPrincipio(val_peek(1));yyval=val_peek(0);}
break;
case 38:
//#line 144 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{
        val_peek(2).agregaHijoFinal(val_peek(1));
        val_peek(0).agregaHijoPrincipio(val_peek(2));
        yyval=val_peek(0);
    }
break;
case 39:
//#line 152 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{yyval = new NodoOperador("<",Operador.LE);}
break;
case 40:
//#line 153 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{yyval = new NodoOperador(">",Operador.GR);}
break;
case 41:
//#line 154 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{yyval = new NodoOperador("==",Operador.EQUALS);}
break;
case 42:
//#line 155 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{yyval = new NodoOperador(">=",Operador.GRQ);}
break;
case 43:
//#line 156 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{yyval = new NodoOperador("<=",Operador.LEQ);}
break;
case 44:
//#line 157 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{yyval = new NodoOperador("!=",Operador.DIFF);}
break;
case 45:
//#line 161 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{yyval = val_peek(0);}
break;
case 46:
//#line 162 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{
      yyval = val_peek(1);
      yyval.agregaHijoFinal(val_peek(0));
      dump_stacks(stateptr);
    }
break;
case 47:
//#line 170 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{
      yyval = new AddNodo(val_peek(1), null);
      dump_stacks(stateptr);
    }
break;
case 48:
//#line 174 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{
      yyval = new DiffNodo(val_peek(1), null);
    }
break;
case 49:
//#line 177 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{val_peek(2).agregaHijoFinal(val_peek(1));
    yyval = new AddNodo(val_peek(2), null);
    dump_stacks(stateptr);}
break;
case 50:
//#line 180 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{val_peek(2).agregaHijoFinal(val_peek(1));
    /* los constructores agregan el hijo a la izquierda */
    yyval = new DiffNodo(val_peek(2), null);}
break;
case 51:
//#line 186 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{yyval = val_peek(0);}
break;
case 52:
//#line 187 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{val_peek(1).agregaHijoFinal(val_peek(0)); yyval=val_peek(1);}
break;
case 53:
//#line 189 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{ 
    yyval = new NodoOperador(val_peek(1),"*",Operador.POR);
    }
break;
case 54:
//#line 192 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{
    yyval = new NodoOperador(val_peek(1),"//",Operador.DIVENTERA);    
    }
break;
case 55:
//#line 195 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{
    yyval = new NodoOperador(val_peek(1),"%",Operador.MODULO);    
    }
break;
case 56:
//#line 198 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{
    yyval = new NodoOperador(val_peek(1),"/",Operador.DIV);
    }
break;
case 57:
//#line 201 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{        
    val_peek(2).agregaHijoFinal(val_peek(1));
    yyval = new NodoOperador(val_peek(2),"*",Operador.POR); 
    }
break;
case 58:
//#line 205 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{
    val_peek(2).agregaHijoFinal(val_peek(1));
    yyval = new NodoOperador(val_peek(2),"//",Operador.DIVENTERA); 
    }
break;
case 59:
//#line 209 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{
    val_peek(2).agregaHijoFinal(val_peek(1));
    yyval = new NodoOperador(val_peek(2),"%",Operador.MODULO); 
    }
break;
case 60:
//#line 213 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{
    val_peek(2).agregaHijoFinal(val_peek(1));
    yyval = new NodoOperador(val_peek(2),"/",Operador.DIV); 
    }
break;
case 61:
//#line 219 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{yyval = new AddNodo(); yyval.agregaHijoFinal(val_peek(0));}
break;
case 62:
//#line 220 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{yyval = new DiffNodo(); yyval.agregaHijoFinal(val_peek(0));}
break;
case 63:
//#line 221 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{yyval = val_peek(0);}
break;
case 64:
//#line 224 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{yyval = val_peek(0);}
break;
case 65:
//#line 225 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{
          yyval = new NodoOperador(val_peek(2),"**",Operador.POTENCIA);
          yyval.agregaHijoFinal(val_peek(0));
      }
break;
case 66:
//#line 233 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{ yyval = val_peek(0);}
break;
case 67:
//#line 234 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{yyval = val_peek(0); }
break;
case 68:
//#line 235 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{yyval = val_peek(0);}
break;
case 69:
//#line 236 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{yyval = val_peek(0);}
break;
case 70:
//#line 237 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{yyval = val_peek(0);}
break;
case 71:
//#line 238 "/home/naranjo/github/Compiladores/LosBastardosdeTuring/Proyectos/Proyecto5/src/main/byaccj/Parser.y"
{yyval = val_peek(1);}
break;
//#line 895 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
