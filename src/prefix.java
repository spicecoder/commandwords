import java.util.ArrayList;
import java.util.Arrays;

public class prefix implements LService {
    
	@Override
	public String serve(String input) {
		// the prefix takes the  first input string and append it to the rest of the input with a decoration
		
		int pos     = input.indexOf(",");
		String fs = input.substring(0,pos);
		String ls = input.substring(pos)  ;
		
		return "fs" + "is :" + ls;
	}

	 

}
