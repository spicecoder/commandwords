import java.util.ArrayList;
import java.util.Arrays;

public class CommandParser {
//this class takes input as > seperated string of text, commas and dots and : ; 
//a colon indicates a service name ;general idea 22,33,55>:sort>:print>882.990>:roundup>show
//should produce array of function with corresponding input in the order of appearance
CommandParser cParse ;
ArrayList<CommandIn> commands  = new ArrayList<CommandIn>();
	public static void main(String[] args) {
		CommandParser cParser = new CommandParser();
		// TODO Auto-generated method stub
      cParser.parse("22,33,55>:sort>:print>882.990>:roundup>show>::abc.com.io::");
      // root , verb/action  , data , i/o[domain]
	}

	//parseIn(String xx) 
	

public   void  parse(String instream1) { //rule 0 > are seperators;commands preceded by : , others are input
//rules	1. noncommand string followed by another noncommand string ,simple gets concatenated;
// rule 2, command inputs precedes commannd ;rules3: if commands produce string, that is passed 
//as input further down ; commandstream dont use / ;.'s are meant to be part of command name 	
System.out.println("for parse:" + instream1);
//String[]incoming = instream1.split("E63");	
 String[]incoming = instream1.split("%3E");
System.out.println("after parse:" + Arrays.toString(incoming));
String current_input = "" ;
for (String vv : incoming) { 
	 
	
	if (vv.length() > 0) {  
	// vv = vv.substring(1) ; // shave off the extra space added	
	if(vv.charAt(0) != ':') {
		// input encountered 	
		current_input = current_input + vv;	
		}
	//^
	if(vv.charAt(0) == ':' & vv.length() > 1  ) {
	// command encountered 	
	System.out.println("after char 0:" + vv);	
	//make a commanin  object with command and the current input.& clean current input
	String verb =vv.substring(1);	
    CommandIn curr_command = new  CommandIn(verb, current_input);	
    commands.add(curr_command);
    current_input ="" ;
    
	} }
}
}
 public ArrayList<CommandIn> getCommands() {return commands;  }
 public void resetCommands() {commands =  new ArrayList<CommandIn>(); }
 
}
