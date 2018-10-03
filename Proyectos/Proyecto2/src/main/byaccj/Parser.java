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






//#line 3 "parser.y"

import java.io.*;

//#line 21 "Parser.java"




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
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short SALTO=257;
public final static short IDENTA=258;
public final static short DEIDENTA=259;
public final static short IDENTIFICADOR=260;
public final static short ENTERO=261;
public final static short CADENA=262;
public final static short REAL=263;
public final static short BOOLEANO=264;
public final static short AND=265;
public final static short OR=266;
public final static short NOT=267;
public final static short WHILE=268;
public final static short IF=269;
public final static short ELSE=270;
public final static short PRINT=271;
public final static short ADD=272;
public final static short SUB=273;
public final static short MULT=274;
public final static short DIV=275;
public final static short LT=276;
public final static short BT=277;
public final static short LTE=278;
public final static short BTE=279;
public final static short EQUAL=280;
public final static short INC=281;
public final static short SEPARADOR=282;
public final static short ASIG=283;
public final static short DIST=284;
public final static short DIVE=285;
public final static short POWER=286;
public final static short PAR_O=287;
public final static short PAR_C=288;
public final static short MOD=289;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    0,    1,    1,    2,    2,    3,    5,    5,    6,
    6,    7,    4,    4,    9,    9,   10,   11,   11,   22,
   22,    8,   12,   12,   24,   24,   13,   13,   25,   25,
   14,   23,   23,   15,   26,   26,   18,   18,   18,   18,
   18,   18,   16,   16,   16,   19,   19,   19,   19,   19,
   17,   17,   17,   21,   21,   20,   20,   20,   20,   20,
   20,
};
final static short yylen[] = {                            2,
    0,    1,    2,    2,    1,    1,    2,    1,    1,    1,
    2,    2,    1,    1,    4,    7,    4,    1,    4,    2,
    1,    1,    1,    2,    3,    0,    1,    2,    3,    0,
    2,    2,    0,    2,    3,    0,    1,    1,    1,    1,
    1,    1,    3,    3,    1,    3,    3,    3,    3,    1,
    2,    2,    1,    1,    3,    1,    1,    1,    1,    1,
    3,
};
final static short yydefred[] = {                         1,
    0,    0,    3,   33,   33,   33,   33,    4,    5,    6,
    0,    8,    9,   10,   13,   14,   22,    0,    0,    0,
    0,    0,   12,   11,    7,    0,    0,   56,   57,   58,
   59,   60,   32,    0,    0,   33,   31,    0,   50,    0,
    0,   53,    0,    0,   33,   33,   51,   52,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   18,   17,
    0,   25,   29,   61,    0,    0,   37,   38,   41,   40,
   39,   42,    0,   46,   47,   49,   48,   55,    0,    0,
    0,   21,    0,    0,   19,   20,   16,
};
final static short yydgoto[] = {                          1,
    2,    8,    9,   10,   11,   12,   13,   14,   15,   16,
   60,   17,   18,   19,   37,   38,   39,   73,   40,   41,
   42,   83,   20,   26,   27,   52,
};
final static short yysindex[] = {                         0,
    0,  -36,    0,    0,    0,    0,    0,    0,    0,    0,
 -252,    0,    0,    0,    0,    0,    0,    0,    0,  -93,
 -273, -271,    0,    0,    0, -250, -231,    0,    0,    0,
    0,    0,    0,  -61,  -61,    0,    0, -249,    0, -268,
 -240,    0, -132, -132,    0,    0,    0,    0, -236,  -61,
  -61,  -39,  -61,  -61,  -61,  -61,  -61, -222,    0,    0,
 -217,    0,    0,    0, -268, -268,    0,    0,    0,    0,
    0,    0,  -61,    0,    0,    0,    0,    0, -119, -226,
 -249,    0,  -46, -132,    0,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,   22,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0, -142, -190,    0,
    0,    0,    0,    0,    0, -237, -129,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0, -146,    0, -218,
 -247,    0,  -77,  -77,    0,    0,    0,    0,    0,    0,
    0, -166,    0,    0,    0,    0,    0,    0,    0,    0,
    1,    0,    0,    0, -194, -170,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  -77,    0,
 -122,    0,  -77,  -77,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,  -75,  -41,    0,    0,    0,    0,    8,    0,    0,
  -44,    0,   12,   19,    0,   -5,  163,    0,   -1,    0,
    0,    0,    0,    0,    0,    0,
};
final static int YYTABLESIZE=309;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         61,
   15,   59,   59,   82,   25,   53,   54,   86,   43,   54,
   44,   21,   22,   23,   24,   45,   55,   54,   54,   24,
   56,    2,   50,   51,   54,   54,   54,   54,   54,   54,
   54,   54,   54,   46,   54,   79,   54,   54,   45,   87,
   54,   54,   59,   49,   24,   57,   45,   45,   65,   66,
   24,   64,   80,   45,   45,   84,   62,   45,   45,   45,
   45,   45,   43,   45,   63,   45,   27,   81,    0,   45,
   43,   43,    0,    0,   30,   27,    0,   43,   43,    0,
    0,   43,   43,   43,   43,   43,   44,   43,    0,   43,
   34,   27,    0,   43,   44,   44,    0,   27,   34,   34,
    0,   44,   44,    0,    0,   44,   44,   44,   44,   44,
   36,   44,    0,   44,   23,   34,    0,   44,   36,   36,
    0,   34,    0,   26,   58,    0,    0,   28,    0,   36,
   36,   36,   36,   36,   35,   36,   28,   36,    6,   23,
    0,   36,   35,   35,    0,   23,    0,    7,    4,    5,
    0,    6,   28,   35,   35,   35,   35,   35,   28,   35,
    7,   35,    0,    0,    0,   35,   28,   29,   30,   31,
   32,    0,    0,   33,    0,    0,    0,    0,   34,   35,
    0,    0,   33,   33,   33,   33,   33,    0,    0,   33,
    0,    0,    0,   36,   33,   33,   47,   48,   28,   29,
   30,   31,   32,    0,    0,    0,    0,    0,    0,   33,
   34,   35,   85,    0,    0,   74,   75,   76,   77,   78,
    3,    4,    5,    0,    6,   36,    0,    0,    0,    0,
    0,    4,    5,    7,    6,    0,   67,   68,   69,   70,
   71,    0,    0,    7,   72,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   15,    0,   15,
   15,   15,   15,   15,   15,    0,    0,   15,   15,   15,
    0,   15,   15,   15,    0,    0,    0,    0,    0,    0,
   15,   33,   33,   33,   33,   33,    0,   15,   33,    0,
    0,    0,    0,   33,   33,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   33,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         44,
    0,   43,   44,   79,  257,  274,  275,   83,  282,  257,
  282,    4,    5,    6,    7,  266,  285,  265,  266,  257,
  289,    0,  272,  273,  272,  273,  274,  275,  276,  277,
  278,  279,  280,  265,  282,  258,  284,  285,  257,   84,
  288,  289,   84,   36,  282,  286,  265,  266,   50,   51,
  288,  288,  270,  272,  273,  282,   45,  276,  277,  278,
  279,  280,  257,  282,   46,  284,  257,   73,   -1,  288,
  265,  266,   -1,   -1,  265,  266,   -1,  272,  273,   -1,
   -1,  276,  277,  278,  279,  280,  257,  282,   -1,  284,
  257,  282,   -1,  288,  265,  266,   -1,  288,  265,  266,
   -1,  272,  273,   -1,   -1,  276,  277,  278,  279,  280,
  257,  282,   -1,  284,  257,  282,   -1,  288,  265,  266,
   -1,  288,   -1,  266,  257,   -1,   -1,  257,   -1,  276,
  277,  278,  279,  280,  257,  282,  266,  284,  271,  282,
   -1,  288,  265,  266,   -1,  288,   -1,  280,  268,  269,
   -1,  271,  282,  276,  277,  278,  279,  280,  288,  282,
  280,  284,   -1,   -1,   -1,  288,  260,  261,  262,  263,
  264,   -1,   -1,  267,   -1,   -1,   -1,   -1,  272,  273,
   -1,   -1,  260,  261,  262,  263,  264,   -1,   -1,  267,
   -1,   -1,   -1,  287,  272,  273,   34,   35,  260,  261,
  262,  263,  264,   -1,   -1,   -1,   -1,   -1,   -1,  287,
  272,  273,  259,   -1,   -1,   53,   54,   55,   56,   57,
  257,  268,  269,   -1,  271,  287,   -1,   -1,   -1,   -1,
   -1,  268,  269,  280,  271,   -1,  276,  277,  278,  279,
  280,   -1,   -1,  280,  284,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,   -1,  259,
  260,  261,  262,  263,  264,   -1,   -1,  267,  268,  269,
   -1,  271,  272,  273,   -1,   -1,   -1,   -1,   -1,   -1,
  280,  260,  261,  262,  263,  264,   -1,  287,  267,   -1,
   -1,   -1,   -1,  272,  273,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  287,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=289;
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
null,null,null,"SALTO","IDENTA","DEIDENTA","IDENTIFICADOR","ENTERO","CADENA",
"REAL","BOOLEANO","AND","OR","NOT","WHILE","IF","ELSE","PRINT","ADD","SUB",
"MULT","DIV","LT","BT","LTE","BTE","EQUAL","INC","SEPARADOR","ASIG","DIST",
"DIVE","POWER","PAR_O","PAR_C","MOD",
};
final static String yyrule[] = {
"$accept : start",
"start :",
"start : file_input",
"file_input : file_input SALTO",
"file_input : file_input stmt",
"stmt : simple_stmt",
"stmt : compound_stmt",
"simple_stmt : small_stmt SALTO",
"small_stmt : expr_stmt",
"small_stmt : print_stmt",
"expr_stmt : test",
"expr_stmt : EQUAL test",
"print_stmt : PRINT test",
"compound_stmt : if_stmt",
"compound_stmt : while_stmt",
"if_stmt : IF test SEPARADOR suite",
"if_stmt : IF test SEPARADOR suite ELSE SEPARADOR suite",
"while_stmt : WHILE test SEPARADOR suite",
"suite : simple_stmt",
"suite : SALTO IDENTA stmt_aux DEIDENTA",
"stmt_aux : stmt_aux stmt",
"stmt_aux : stmt",
"test : or_test",
"or_test : and_test",
"or_test : and_test or_2",
"or_2 : or_2 OR and_test",
"or_2 :",
"and_test : not_test",
"and_test : not_test and_2",
"and_2 : and_2 AND not_test",
"and_2 :",
"not_test : not_test_2 comparison",
"not_test_2 : not_test_2 NOT",
"not_test_2 :",
"comparison : expr comparison_2",
"comparison_2 : comparison_2 comp_op expr",
"comparison_2 :",
"comp_op : LT",
"comp_op : BT",
"comp_op : EQUAL",
"comp_op : BTE",
"comp_op : LTE",
"comp_op : DIST",
"expr : expr ADD term",
"expr : expr SUB term",
"expr : term",
"term : term MULT factor",
"term : term DIV factor",
"term : term MOD factor",
"term : term DIVE factor",
"term : factor",
"factor : ADD factor",
"factor : SUB factor",
"factor : power",
"power : atom",
"power : atom POWER factor",
"atom : IDENTIFICADOR",
"atom : ENTERO",
"atom : CADENA",
"atom : REAL",
"atom : BOOLEANO",
"atom : PAR_O test PAR_C",
};

//#line 55 "parser.y"

private Letras alexico;

// Regresar átomos
private int yylex() {
  int yyl_return = -1;

  try{
    yyl_return = alexico.yylex();

  }catch (IOException e){
    System.err.println("Error de IO." + e);
  }
  return yyl_return;
}

public void yyerror (String error){
  System.err.println("[ERROR] " +error);
  System.exit(2);
}

public Parser(Reader r){
  alexico = new Letras(r,this);
}

public static void main(String args[]){
  try{
   Parser yyparser = new Parser(new FileReader(args[0]));
   yyparser.yydebug = true;
   yyparser.yyparse();
  }catch(FileNotFoundException e){
    System.err.println("El Archivo " + args[0] + " no existe.");
  }

}
//#line 385 "Parser.java"
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
//#line 19 "parser.y"
{System.out.println("O");}
break;
case 2:
//#line 20 "parser.y"
{System.out.println("[OK] " );}
break;
//#line 542 "Parser.java"
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
