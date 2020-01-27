import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

 // THIS CLASS IS NOT IN USE atm


/*QuadrooServer  servlet is able to invoke all methods depending on the URL 
 * 
 * Author: Pronob Pal ; code borrowed from core servlets with thanks.
  
 * 
 * */

public class ContextpathServer extends HttpServlet {
	
 
	 String commandStream;
	 List<String> resultHead  = new ArrayList<String>();
	 List<String> resultBottom = new ArrayList<String>();
	 String resultOut;
	//doPost/ doget invokes methods matching the verb extracted from URI
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {doGet( request,  response) ;}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
					String ruri = request.getRequestURI();
					System.out.println("context uri:" + ruri);
					CommandParser cParser = new CommandParser();
					CommandExecutor cXer = new CommandExecutor();
					ArrayList <CommandIn> commandStack = new ArrayList<CommandIn>();
					int ms = ruri.lastIndexOf("/");

					if ((ms > 0) && (ms < ruri.length())) {
						commandStream = ruri.substring(ms + 1); 
						System.out.println("command stream :" + commandStream);
						cParser.parse(commandStream);
						
					}
			resultOut = 	     cXer.executeCommand(cParser.getCommands());
					
					
	 	String rt = "execution result:verbs" + ruri.substring(ms + 1) +"out put:" + resultOut ;
	   // Time to Set up the reply Text
		response.setContentType("text/html");
		// Prevent the browser from caching the response. See
		// Section 7.2 of Core Servlets and JSP for details.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
		PrintWriter out = response.getWriter();
		showIt(resultOut,out,cXer);
		System.out.println("put out:" + rt);
	//	out.write(rt);
		 
	}

	
	public void init(ServletConfig config) {
		try {
			super.init(config);
			String filename = "/WEB-INF/configuration.properties";

	        ServletContext context = getServletContext();
			
			storeTop();
			storeBottom();
		} catch (ServletException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();}
		}
/*
 */ 
   public void showIt(String cout, PrintWriter printWrite, CommandExecutor cXer ) {
 
	   printTop( printWrite);
    /*    printWrite.println("<!DOCTYPE html>");
        printWrite.println("<html><head>");
        printWrite.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>");
        printWrite.println("<title>Result of lambdas</title></head>");
        printWrite.println("<body>");
         //
        printWrite.println("<h1>Result of  Lambda</h1>");  
        printWrite.println("<h2>" + cout+  "</h2>"); */
	   printResult(  printWrite);
	   
        ArrayList<CommandIn> resx = cXer.getResultStack();
        int ir = 0;
        for (CommandIn rxo : resx) {
        	ir = ir + 1;
        	printSteps( printWrite, rxo, ir);
        	
            }
             // Generate a random number upon each request
      /*  printWrite.println("<p>A Random Number: <strong>" + Math.random() + "</strong></p>");
        printWrite.println("</body>");
        printWrite.println("</html>");
      */
          printBottom( printWrite);
    //	  printWrite.close();  // Always close the output writer
      }
  /*
   <button class='collapsible'>Open Section 3</button>
<div class='content'>
  <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
</div>
   * 
   */
  public List<String> readsFile(String fn){
    List<String> linesread = new ArrayList<String>() ;
	  try {
		   ServletContext context = getServletContext();
		 // String path = context.getRealPath("Toplamda.html"); 
		  InputStream is = context.getResourceAsStream( fn);
	        if (is != null) {
	            InputStreamReader isr = new InputStreamReader(is);
	            BufferedReader reader = new BufferedReader(isr);
	            
	            String text;

	            // We read the file line by line and later will be displayed on the
	            // browser page.
	            while ((text = reader.readLine()) != null) {
	               linesread.add(text);
	            }
	        }
		  
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return linesread;    
	  
	  
  }
   
  public void storeTop() {
	  
	  try {
		   ServletContext context = getServletContext();
		 // String path = context.getRealPath("Toplamda.html"); 
		  InputStream is = context.getResourceAsStream( "/WEB-INF/Toplamda.html");
	        if (is != null) {
	            InputStreamReader isr = new InputStreamReader(is);
	            BufferedReader reader = new BufferedReader(isr);
	            
	            String text;

	            // We read the file line by line and later will be displayed on the
	            // browser page.
	            while ((text = reader.readLine()) != null) {
	               resultHead.add(text);
	            }
	        }
		  
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  
  	  
  }
  public void storeBottom() {
	    resultBottom = readsFile("/WEB-INF//BottomLamda.html");    
	  
  }
  public void printArray(List<String> flines , PrintWriter printWrite) {
	  for (String ln: flines) { 
		  printWrite.println(ln); 
		  
	  }
	  
  }
  public void printTop( PrintWriter printWrite) { 
	  printArray(resultHead,printWrite);
  }
  
  public void printBottom(PrintWriter printWrite) { 
	  printArray(resultBottom,printWrite); printWrite.close(); 
	  }
  
  public void printResult( PrintWriter printWrite) {
	  
	//  printWrite.println( "  <button class='steadily'>Lamda result</button>");
	  printWrite.println( " <div class='steadily'>");
	   printWrite.println("<p>Result From Lambda Chain=> "+commandStream.replace("%3E",">") + "</p>");  
       printWrite.println("<p>" + resultOut+  "</p>"); 
	  printWrite.println( " </div >");
	  }
 
  public void printSteps(PrintWriter printWrite,CommandIn rxo,int stepno){
	  printWrite.println( "  <button class='collapsible'>Lamda step:"+ stepno+" </button>");
	  printWrite.println( " <div class='content' >");
	   printWrite.println("<p>Result of  Lambda Command:"+ rxo.command +   "</p>");  
	  // printWrite.println("<div>  <p> Lambda " + stepno + " Result </p>" ); 
       printWrite.println("<p>   input->"+ rxo.input + "</p>"); 
       printWrite.println("<p>   result->"+ rxo.getOutput()+ "</p>"); 
 
	  printWrite.println( " </div >");
	  
  }
		

	public void destroy() {
		 
	}
 
 
}