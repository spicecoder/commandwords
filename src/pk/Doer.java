package pk;

public class Doer {
	private String output ="Nobby";
	private static int ii =0 ;
	private static Doer instance ; 
	
public Doer() {
	if (instance == null ) {
	ii =ii + 1 ;
	this.output ="grade 0" ;
	instance = this;
	}
	instance.ii ++; 
} 
public  static Doer getInstance() {ii = ii + 5 ;return instance; }
public String getOutput() {return ii + output;} ;  
}
