import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class sort implements LService {
    
	@Override
	public String serve(String input) {
		// turn comma seperated strings into array of numbers
		// sort that , turn back into string 
		String[] inumStrings = input.split("\\,");
		Arrays.sort(inumStrings );
		System.out.println("sorted" +  Arrays. toString(inumStrings));
		return Arrays. toString(inumStrings);
		
	}

	 

}
