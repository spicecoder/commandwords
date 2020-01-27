import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

//import javax.servlet.ServletContext;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

/*
 * a simple static http server
*/
public class FlowSyntaxServer {

	 	
  public static void main(String[] args) throws Exception {
    HttpServer server = HttpServer.create(new InetSocketAddress(8998), 0);
    server.createContext("/lamdax", new MyHandler());
    server.setExecutor(null); // creates a default executor
    server.start();
  }

  static class MyHandler implements HttpHandler {
	  String commandStream;
		 List<String> resultHead  = new ArrayList<String>();
		 List<String> resultBottom = new ArrayList<String>();
		 String resultOut;
	  
    public void handle(HttpExchange t) throws IOException {
    	URI uuri = t.getRequestURI();
    	String ruri = uuri.getPath();
    	//String ruri = request.getRequestURI();
    	System.out.println("flow uri:" + ruri);
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
	//PrintStream os = (PrintStream) t.getResponseBody();
	//PrintWriter out = os.wr
	// t.sendResponseHeaders(200, 600);
	// Time to Set up the reply Text
			//( t.getResponseHeaders() ).setType("type","text/html");
			// Prevent the browser from caching the response. See
			// Section 7.2 of Core Servlets and JSP for details.
			//response.setHeader("Pragma", "no-cache"); // HTTP 1.0
			//response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
	//showIt(resultOut,os,cXer);
	System.out.println("put out:" + resultOut);



     String response = "Welcome" + resultOut  + "the path:" + ruri;
     t.sendResponseHeaders(200,response.length());
      OutputStream os = t.getResponseBody();
     os.write(response.getBytes());
          os.write(("<!DOCTYPE html>").getBytes());
     os.write(("<html><head>").getBytes());
     os.write(("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>").getBytes());
     os.write(("<title>Result of lambdas</title></head>").getBytes());
     os.write(("<body>").getBytes());
      //
     os.write(("<h1>Result of  Lambda</h1>").getBytes());  
     os.write(("<h2>" + response+  "</h2>").getBytes());  
       os.close();
    }
     
      
       public void showIt(String cout, PrintStream printWrite, CommandExecutor cXer ) {
     
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
    		//   ServletContext context = getServletContext();
    		 // String path = context.getRealPath("Toplamda.html"); 
    		  InputStream is = new  FileInputStream(new File(fn));
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
    		 //  ServletContext context = getServletContext();
    		 // String path = context.getRealPath("Toplamda.html"); 
    		  InputStream is = new  FileInputStream(new File("/WEB-INF/Toplamda.html"));
    		//  InputStream is = context.getResourceAsStream( "/WEB-INF/Toplamda.html");
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
      public void printArray(List<String> flines , PrintStream printWrite) {
    	  for (String ln: flines) { 
    		  ((PrintStream) printWrite).println(ln); 
    		  
    	  }
    	  
      }
      public void printTop(PrintStream printWrite) { 
    	  printArray(resultHead,printWrite);
      }
      
      public void printBottom(PrintStream printWrite) { 
    	  printArray(resultBottom,printWrite); printWrite.close(); 
    	  }
      
      public void printResult(PrintStream printWrite) {
    	  
    	//  printWrite.println( "  <button class='steadily'>Lamda result</button>");
    	  printWrite.println( " <div class='steadily'>");
    	   printWrite.println("<p>Result From Lambda Chain=> "+commandStream.replace("%3E",">") + "</p>");  
           printWrite.println("<p>" + resultOut+  "</p>"); 
    	  printWrite.println( " </div >");
    	  }
     
      public void printSteps(PrintStream printWrite,CommandIn rxo,int stepno){
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
  
  
}