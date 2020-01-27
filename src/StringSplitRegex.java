import java.util.Arrays;

public class StringSplitRegex {

	public static void main(String[] args) {
		// Declare string object
		String StringValue = ">>42,73,55,65>>66.99";
		// define the regex parameter
		String regex = ">>";
		// split the string object
		String[]output = StringValue.split(regex);
		System.out.println("from array"+Arrays.toString(output));
		// printing the array contents
		System.out.println("Name:"+output[1]);
		 System.out.println("Age:"+output[2]);
		 System.out.println("Country:"+output[0]);
        System.out.println();
	}

}
