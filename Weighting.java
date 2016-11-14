import java.util.ArrayList;
import java.util.List;

public class Weighting{
	//keeps the weighting final result
	private static ArrayList<String> arr = new ArrayList<String>();
	
    public static void clearArray(){
        arr.clear();
    }  
	//get
    public static ArrayList<String> getResult(String expression){
        clearArray();
        weighting(expression);
        //printWeighting();
        return arr;
    }

    public static void weighting( String expression ){
		String[] str = clearEntry( expression ); 
		int m = 0;
		String r = "";
		if( expression.length() > 1 ){
			for (int j = 0 ; j < str.length ; j++ ) {
				int in = 0;
				
				for (int k = 0 ; k < str.length ; k ++   ) {
					
					if ( str[ j ].equals( str[ k ] ) ) 
						in++;	
					}
				if ( in > m ){
					m = in;
					r = str[ j ];	
					}
				}
			
			String replaced = join( str , r );
			
			arr.add(r);
			
			weighting( replaced );	
        }
		else{
			arr.add( expression );
			//System.out.println(">: " + expression);
		}		  
	}
    //used in weighting
	private static String join(String[] a , String r){
		String ret = "";
		//System.out.println();
		for (int i = 0 ; i < a.length ; i++ ){ 
			if ( a[i].equals("") || a[i].equals(" ") )
				continue;
			ret = ret + "+" + a[i];
		}
		ret = ret.replaceFirst("[+]","");
		return ret.replaceAll( r , "" );
		}

	//used both in windowing and weighting
	private static String[] clearEntry( String expression ){
		String array[];
		String newOne = "";
		for ( int i = 0 ; i < expression.length() ; i++ ) {	
			if (String.valueOf(expression.charAt(i)).equals(" ") || 
				String.valueOf(expression.charAt(i)).equals("(") || 
				String.valueOf(expression.charAt(i)).equals(")") ||
				String.valueOf(expression.charAt(i)).equals("!")) {
				continue;
			}
			newOne = newOne + String.valueOf( expression.charAt(i) ); 					
		}
		array = newOne.split( "[+*]" );
			
		return array;
	}
    //prints the weighting result
	public static void printWeighting(){
		for (String s : arr ){ 
			if(s.equals(""))
				continue;
			System.out.print( s + "<" );
			
		}
	}
    
    //used in both windowing and weighting
    private static String[] fix( String [] toFix ){
    	ArrayList<String> newer = new ArrayList<String>();
    	String[] toReturn;
    	int i;
    	for (String s : toFix ) {
    		if ( !newer.contains( s )  )
    			newer.add(s);
    		}
    	toReturn = new String[ newer.size() ];
    	i = 0;
    	
    	for (String s : newer ) {
    		toReturn[i] = s;
    		i++;
    	}
    	return toReturn;
    }
   }












