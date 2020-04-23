/* Generated By:JavaCC: Do not edit this line. AutomataParser.java */
package automata.parser;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import automata.ast.* ;
import automata.util.* ;

@SuppressWarnings("unchecked")
public class AutomataParser implements AutomataParserConstants {

  public static AST from_file(String path_file) throws Exception
  {
    AST ast = new AutomataParser(new BufferedReader(new FileReader(path_file))).Run();
    return ast;
  }

  public static AST from_string(String input) throws Exception
  {
    AST ast = new AutomataParser(new java.io.StringReader(input)).Run();
    return ast;
  }

  public static boolean option(String option, String [] args)
  {
    int i = 0 ;
    while (i < args.length){
      if (args [i].equals(option)){ return true; }
      i++;
    }
    return false;
  }


  public static int index_of_input(String[] args) {
    // find the input among the options of the command line
        int i = 0 ;
        while (i<args.length) {
          if ( (   args[i].equals("-ast")
                        || args[i].equals("-string")
                        || args[i].equals("-aut")
                        || args[i].equals("-file") ) == false )
            { return i ; }
          else
            { i++ ; }
        }
        return 0 ;
  }

  public static void main(String[] args) throws Exception {
        AST ast;
        int i = index_of_input(args) ;
        if ( option("-string",args) )
            ast = from_string(args[i]) ;
        else
            ast = from_file(args[i]) ;

                if ( option("-ast",args) ) {
                AstPrinter ast_printer = new AstPrinter();
                ast.accept(ast_printer);
            System.out.println("// THE AST of the PARSED AUTOMATA in DOT FORMAT \u005cn");
            System.out.println(ast_printer.to_dot());
        }
        if ( option("-aut",args) ) {
            System.out.println("// THE AUTOMATA in DOT FORMAT \u005cn");
                        new AutPrinter(System.out, ast);
        }
        /*
        if ( option("-AST",args) ) {
          	ASTPrinter ast_printer = new ASTPrinter();
          	ast.accept(ast_printer);
            System.out.println("// THE AST of the PARSED AUTOMATA in DOT FORMAT \n");
            System.out.println(ast_printer.to_dot());
        }
        if ( option("-AUT",args) ) {         
            System.out.println("// THE AUTOMATA in DOT FORMAT \n"); 
			AutomataPrinter v = new AutomataPrinter();
			v.print(System.out, ast);
      	}
      	*/

        }

// THE PARSER

/**
 ** See package ast for the definitions of types returned by Parsers
 **/

/* Identifier */
  
final public String P_Identifier() throws ParseException {
  Token token;
    token = jj_consume_token(ID);
               {if (true) return token.image;}
    throw new Error("Missing return statement in function");
  }

/* Direction
 * | "N" | "S" | "W" | "E" | "F" | "B" | "L" | "R"
 * | "d"
 */
  final public Direction P_Direction() throws ParseException {
  Token token;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case DIRECTION:
      token = jj_consume_token(DIRECTION);
                      {if (true) return new Direction(new Terminal(token.image));}
      break;
    case DIRVAR:
      token = jj_consume_token(DIRVAR);
                      {if (true) return new Direction(new Terminal(token.image));}
      break;
    default:
      jj_la1[0] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

/* Category
 * | "A" | "C" |"D" | "G" | "J" | "M" | "O" | "P" | "T" | "V" | "@"  
 * | "e"
 * | "_"
 */
  final public Category P_Category() throws ParseException {
  Token token;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case CATEGORY:
      token = jj_consume_token(CATEGORY);
          {if (true) return new Category(new Terminal(token.image));}
      break;
    case ENTVAR:
      token = jj_consume_token(ENTVAR);
          {if (true) return new Category(new Terminal(token.image));}
      break;
    case 20:
      jj_consume_token(20);
          {if (true) return new Category(new Terminal("_"));}
      break;
    default:
      jj_la1[1] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

/* Run
 * | Some_Automata . <EOF>
 */
  final public AST Run() throws ParseException {
  List<Automaton> automata ;
    automata = P_Some_Automata(new LinkedList<Automaton>());
    jj_consume_token(0);
          {if (true) return new AST(automata) ;}
    throw new Error("Missing return statement in function");
  }

/* Some_Automata
 * | Automaton . Some_Automata
 * | epsilon
 */
  final public List<Automaton> P_Some_Automata(List<Automaton> input_list) throws ParseException {
  Automaton aut ; List<Automaton> list ;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ID:
      aut = P_Automaton();
      list = P_Some_Automata(input_list);
          list.add(0,aut) ; {if (true) return list ;}
      break;
    default:
      jj_la1[2] = jj_gen;
          {if (true) return input_list ;}
    }
    throw new Error("Missing return statement in function");
  }

/* Automaton
 * | Identifier . "(" . Identifier . ")" . "{" . At_least.one_Mode . "}"
 */
  final public Automaton P_Automaton() throws ParseException {
  String name ; String entry_state ; List<Mode> modes ;
    name = P_Identifier();
    jj_consume_token(21);
    entry_state = P_Identifier();
    jj_consume_token(22);
    jj_consume_token(23);
    modes = P_At_least_one_Mode(new LinkedList<Mode>());
    jj_consume_token(24);
          {if (true) return new Automaton(name, new State(entry_state), modes) ;}
    throw new Error("Missing return statement in function");
  }

/* At_least.one_Mode
 * | Mode . Some_Mode
 */
  final public List<Mode> P_At_least_one_Mode(List<Mode> input_list) throws ParseException {
  Mode mode ; List<Mode> list ;
    mode = P_Mode();
    list = P_Some_Mode(input_list);
          list.add(0,mode) ; {if (true) return list ;}
    throw new Error("Missing return statement in function");
  }

/* Some_Mode
 * | At_least.one_Mode
 * | epsilon
 */
  final public List<Mode> P_Some_Mode(List<Mode> input_list) throws ParseException {
  List<Mode> list ;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 25:
      list = P_At_least_one_Mode(input_list);
          {if (true) return list ;}
      break;
    default:
      jj_la1[3] = jj_gen;
          {if (true) return input_list ;}
    }
    throw new Error("Missing return statement in function");
  }

/* Mode
 * | "*" . State . Opt_TwoPoints . Behaviour
 */
  final public Mode P_Mode() throws ParseException {
  State state;
  Mode mode;
  Behaviour behaviour;
    jj_consume_token(25);
    state = P_State();
    P_Opt_TwoPoints();
    behaviour = P_Behaviour();
          {if (true) return new Mode(state,behaviour) ;}
    throw new Error("Missing return statement in function");
  }

/* State
 * | "(" . State_Identifier . ")"
 */
  final public State P_State() throws ParseException {
  String name ;
    jj_consume_token(21);
    name = P_State_Identifier();
    jj_consume_token(22);
          {if (true) return new State(name);}
    throw new Error("Missing return statement in function");
  }

/* State_Identifier
 * | Identifier
 * | "_"
 * | epsilon
 */
  final public String P_State_Identifier() throws ParseException {
  String name ;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ID:
      name = P_Identifier();
          {if (true) return name;}
      break;
    case 20:
      jj_consume_token(20);
          {if (true) return "_";}
      break;
    default:
      jj_la1[4] = jj_gen;
          {if (true) return "";}
    }
    throw new Error("Missing return statement in function");
  }

/* Opt_TwoPoints
 * | ":" 
 * | epsilon
 */
  final public Void P_Opt_TwoPoints() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 26:
      jj_consume_token(26);
          {if (true) return null;}
      break;
    default:
      jj_la1[5] = jj_gen;
          {if (true) return null;}
    }
    throw new Error("Missing return statement in function");
  }

/* Behaviour
 * | Some_Transitions
 */
  final public Behaviour P_Behaviour() throws ParseException {
  List<Transition> transitions ;
    transitions = P_Some_Transitions(new LinkedList<Transition>());
          {if (true) return new Behaviour(transitions);}
    throw new Error("Missing return statement in function");
  }

/* Some_Transitions
 * | Transition . Some_Transitions 
 * | epsilon
 */
  final public List<Transition> P_Some_Transitions(List<Transition> input_list) throws ParseException {
  Transition transition ; List<Transition> list ;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case CONDITION:
    case UNARY:
    case 21:
    case 28:
    case 29:
      transition = P_Transition();
      list = P_Some_Transitions(input_list);
          list.add(0,transition) ; {if (true) return list ;}
      break;
    default:
      jj_la1[6] = jj_gen;
          {if (true) return input_list ;}
    }
    throw new Error("Missing return statement in function");
  }

/* Transition
 * | Opt_Bar . Condition . "?" . Opt_Action . ":" . State
 */
  final public Transition P_Transition() throws ParseException {
  Condition condition ; Action action ; State state ;
    P_Opt_Bar();
    condition = P_Condition();
    jj_consume_token(27);
    action = P_Opt_Action();
    jj_consume_token(26);
    state = P_State();
          {if (true) return new Transition(condition,action,state) ;}
    throw new Error("Missing return statement in function");
  }

/*  Opt_Bar
 *  | "|"
 *  | epsilon
 */
  final public Void P_Opt_Bar() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 28:
      jj_consume_token(28);
                {if (true) return null;}
      break;
    default:
      jj_la1[7] = jj_gen;
                {if (true) return null;}
    }
    throw new Error("Missing return statement in function");
  }

/*  Condition
 *  | Expression
 */
  final public Condition P_Condition() throws ParseException {
  Expression expression ;
    expression = P_Expression();
          {if (true) return new Condition(expression) ;}
    throw new Error("Missing return statement in function");
  }

/*  Opt_Action
 *  | Choices
 *  | epsilon
*/
  final public Action P_Opt_Action() throws ParseException {
  List<FunCall> choices ;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ACTION:
    case INT:
      choices = P_Choices(new LinkedList<FunCall>());
          {if (true) return new Action(choices) ;}
      break;
    default:
      jj_la1[8] = jj_gen;
          {if (true) return new Action( new LinkedList<FunCall>() );}
    }
    throw new Error("Missing return statement in function");
  }

/*  Choices
 *  | Probabilistic_Action . Opt_More_Choices
 */
  final public List<FunCall> P_Choices(List<FunCall> input_choices) throws ParseException {
  FunCall p_action ;  List<FunCall> choices;
    p_action = P_Probabilistic_Action();
    choices = P_Opt_More_Choices(input_choices);
            choices.add(0,p_action) ; {if (true) return choices;}
    throw new Error("Missing return statement in function");
  }

/*  Opt_More_Choices
 *  | <BINOP> . Choices
 *  | epsilon
 */
  final public List<FunCall> P_Opt_More_Choices(List<FunCall> input_choices) throws ParseException {
  List<FunCall> choices ;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case BINOP:
      jj_consume_token(BINOP);
      choices = P_Choices(input_choices);
       {if (true) return choices;}
      break;
    default:
      jj_la1[9] = jj_gen;
           {if (true) return input_choices;}
    }
    throw new Error("Missing return statement in function");
  }

/* Probabilistic_Action
 * | Percent . ACTION . Opt_Parameters
 * |           ACTION . Opt_Parameters
 */
  final public FunCall P_Probabilistic_Action() throws ParseException {
  int percent ; Token action ; List<Parameter> parameters = new LinkedList<Parameter >();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INT:
      percent = P_Percent();
      action = jj_consume_token(ACTION);
      parameters = P_Opt_Parameters(parameters);
          {if (true) return new FunCall(percent, action.image, parameters) ;}
      break;
    case ACTION:
      action = jj_consume_token(ACTION);
      parameters = P_Opt_Parameters(parameters);
          {if (true) return new FunCall(action.image, parameters) ;}
      break;
    default:
      jj_la1[10] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

/* Michael > OLD VERSION 7/04/2020
 *  Opt_Action
 *  | ACTION . Opt_Parameters
 *  | ""
  
Action OLD_P_Opt_Action():
{ Token token ; List<Parameter> parameters = new LinkedList<Parameter>() ; }
{ token = <ACTION>
  parameters = P_Opt_Parameters( parameters )
  	{ return new Action(new FunCall(token.image, parameters)) ; }
| //epsilon
	{ return null; } 
}
*/


/* Expression
   | "(" . Expression . ")" . Op_Expression
   | <UNARYOP> . Expression 
   | FunCall . Op_Expression
*/
  final public Expression P_Expression() throws ParseException {
  Expression expression ; Token token ; FunCall funcall ;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 21:
      jj_consume_token(21);
      expression = P_Expression();
      jj_consume_token(22);
      expression = P_Op_Expression(expression);
          {if (true) return expression ;}
      break;
    case UNARY:
      token = jj_consume_token(UNARY);
      expression = P_Expression();
          {if (true) return new UnaryOp(token.image,expression) ;}
      break;
    case CONDITION:
    case 29:
      funcall = P_FunCall();
      expression = P_Op_Expression(funcall);
          {if (true) return expression ;}
      break;
    default:
      jj_la1[11] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

/* Op_Expression
   | <BINOP> . Expression
   | epsilon
*/
  final public Expression P_Op_Expression(Expression e1) throws ParseException {
  Token token ; Expression e2 ;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case BINOP:
      token = jj_consume_token(BINOP);
      e2 = P_Expression();
          {if (true) return new BinaryOp(token.image,e1,e2) ;}
      break;
    default:
      jj_la1[12] = jj_gen;
          {if (true) return e1 ;}
    }
    throw new Error("Missing return statement in function");
  }

/* FunCall
 * | "Key(" . KeyName . ")"  // Special case for the "Key" condition which has a fixed arity
 * | CONDITION . Opt_Parameters
 */
  final public FunCall P_FunCall() throws ParseException {
  Token token ; List<Parameter> parameters = new LinkedList<Parameter>() ; Key key ; int percent ;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 29:
      jj_consume_token(29);
      key = P_Key_Name();
      jj_consume_token(22);
          parameters.add(key) ; {if (true) return new FunCall("Key", parameters) ;}
      break;
    case CONDITION:
      token = jj_consume_token(CONDITION);
      parameters = P_Opt_Parameters(parameters);
          {if (true) return new FunCall(token.image, parameters) ;}
      break;
    default:
      jj_la1[13] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

/* Percent
 * | INT . %
 */
  final public int P_Percent() throws ParseException {
  Token token ;
    token = jj_consume_token(INT);
    jj_consume_token(30);
          {if (true) return Integer.parseInt(token.image);}
    throw new Error("Missing return statement in function");
  }

/* KeyName
 * | INT
 * | LOWERCASE
 * | DIRVAR | ENTVAR
 * | SPECIAL_KEY
 * | "_"
 */
  final public Key P_Key_Name() throws ParseException {
  Token token ;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INT:
      token = jj_consume_token(INT);
          {if (true) return new Key(new Terminal(token.image)) ;}
      break;
    case LOWERCASE:
      token = jj_consume_token(LOWERCASE);
          {if (true) return new Key(new Terminal(token.image)) ;}
      break;
    case DIRVAR:
      token = jj_consume_token(DIRVAR);
          {if (true) return new Key(new Terminal(token.image)) ;}
      break;
    case ENTVAR:
      token = jj_consume_token(ENTVAR);
          {if (true) return new Key(new Terminal(token.image)) ;}
      break;
    case SPECIAL_KEY:
      token = jj_consume_token(SPECIAL_KEY);
          {if (true) return new Key(new Terminal(token.image)) ;}
      break;
    case 20:
      jj_consume_token(20);
          {if (true) return new Key(new Terminal("_")) ;}
      break;
    default:
      jj_la1[14] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

/* Opt_Parameters
 * | "(" Some_Parameters ")"
 * | epsilon
 */
  final public List<Parameter> P_Opt_Parameters(List<Parameter> input_list) throws ParseException {
  List<Parameter> list ;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 21:
      jj_consume_token(21);
      list = P_Some_Parameters(input_list);
      jj_consume_token(22);
          {if (true) return list ;}
      break;
    default:
      jj_la1[15] = jj_gen;
          {if (true) return input_list ;}
    }
    throw new Error("Missing return statement in function");
  }

/* Some_Parameters
 * | Parameter  More_Parameters
 * | epsilon
 */
  final public List<Parameter> P_Some_Parameters(List<Parameter> input_list) throws ParseException {
  Parameter parameter ; List<Parameter> list ;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case DIRVAR:
    case ENTVAR:
    case DIRECTION:
    case CATEGORY:
    case INT:
    case 20:
      parameter = P_Parameter();
      list = P_More_Parameters(input_list);
          list.add(0,parameter) ; {if (true) return list ;}
      break;
    default:
      jj_la1[16] = jj_gen;
          {if (true) return input_list ;}
    }
    throw new Error("Missing return statement in function");
  }

/* More_Parameters
 * | ","  Parameter  More_Parameters 
 * | epsilon
 */
  final public List<Parameter> P_More_Parameters(List<Parameter> input_list) throws ParseException {
  Parameter parameter ; List<Parameter> list ;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 31:
      jj_consume_token(31);
      parameter = P_Parameter();
      list = P_More_Parameters(input_list);
          list.add(0,parameter) ; {if (true) return list ;}
      break;
    default:
      jj_la1[17] = jj_gen;
          {if (true) return input_list ;}
    }
    throw new Error("Missing return statement in function");
  }

/* Parameters
 * | Direction
 * | Category
 * | Integer
 */
  final public Parameter P_Parameter() throws ParseException {
  Token token ; Parameter parameter ;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case DIRVAR:
    case DIRECTION:
      parameter = P_Direction();
          {if (true) return parameter ;}
      break;
    case ENTVAR:
    case CATEGORY:
    case 20:
      parameter = P_Category();
          {if (true) return parameter ;}
      break;
    case INT:
      token = jj_consume_token(INT);
          {if (true) return new Value(Integer.parseInt(token.image)) ;}
      break;
    default:
      jj_la1[18] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  /** Generated Token Manager. */
  public AutomataParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[19];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x500,0x100a00,0x40000,0x2000000,0x140000,0x4000000,0x30210020,0x10000000,0x80040,0x20000,0x80040,0x20210020,0x20000,0x20000020,0x182380,0x200000,0x180f00,0x80000000,0x180f00,};
   }

  /** Constructor with InputStream. */
  public AutomataParser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public AutomataParser(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new AutomataParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 19; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 19; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public AutomataParser(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new AutomataParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 19; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 19; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public AutomataParser(AutomataParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 19; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(AutomataParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 19; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[32];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 19; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 32; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

}
