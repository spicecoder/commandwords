
public class picklast implements LService {
    
	@Override
	public String serve(String input) {
		String[] st =  input.split(",")   ;
		int ll = st.length;
		String ls = st[ll-1];
		return ls;
	}

	 

}
