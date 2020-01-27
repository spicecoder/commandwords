import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Stack;

public class CommandExecutor {

	Stack<String> inputStack = new Stack<>();
	String  currentInput = "" ;
	String  currentCommand = "" ;
	ArrayList<CommandIn> resultProgress =  new ArrayList<CommandIn>();
	
public String executeCommand(ArrayList<CommandIn> commandStack ) {
	Object outv = null ;
	System.out.println("command stack:" + commandStack.toString());
	for (CommandIn cmdin : commandStack) { 
		currentCommand = cmdin.command;
		//inputStack.push(currentInput);
		currentInput =currentInput +";"+  cmdin.input;
		Class cl;
		try {
			cl = Class.forName(currentCommand);
		
		Constructor  ctor = null;
		try {
			ctor = cl.getConstructor();
		} catch (NoSuchMethodException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		  try {
			outv =  ctor.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	String 	pout = ((LService)outv).serve(currentInput) ;
	CommandIn outResult = new CommandIn(currentCommand,currentInput);
	outResult.setOutput(pout);	
	resultProgress.add(outResult);
	currentInput = pout ;
		
		
		
	} 
	
	return currentInput ;
	
}
	public ArrayList<CommandIn>  getResultStack() { 
	
		
		
		return resultProgress;}
	
}
