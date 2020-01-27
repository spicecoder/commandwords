
public class pickfirst implements LService {
    
	@Override
	public String serve(String input) {
		String fs =( input.split(",") )[0];
		return fs; 
	}

	 

}
