import java.util.ArrayList;
import java.util.List;
//x1<x2<x3<x4<
public class Window{
	
	//keeps the windowing final result
    private static ArrayList<String> orderings = new ArrayList<String>();

	private static int globalIndex = 0;
    //get
	public static ArrayList<String> getResult(String expression, int window, String level){
        windowing(expression, window, level);
        return orderings;
    }
    //old
    private static String[] remove( String toRemove , String[] str ){
		String [] updated = str;
		for (int i = 0 ; i < str.length ; i++ ) {
			if ( str[ i ].equals( toRemove ) )
				str[i] = "";
			
		}
		return updated;
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
		array = newOne.split( "[+*<]" );
			
		return array;
	}
    
    //prints the orderings array
	public static void printOrderings(){
		for (String s : orderings ) 
			System.out.println( s );
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
    
    //used in windowing
    private static ArrayList<String> arrayToList(String [] s){
    	
    	ArrayList<String> newArr = new ArrayList<String>();
    	for ( String str : s ) 
    		newArr.add(str);
    	return newArr;
    }
    
    public static void window(String expression , int window , String level){
    	//orderings.clear();
        String localArr[] = clearEntry(expression);
        localArr = fix( localArr );



    	List<String> toUse = new ArrayList<String>();
    	String [] toUseArr;
        String before = "";
    	String after = "";
    	
        int tmpI = 0;
    	for (int i = 0 ; i < localArr.length ; i++ ) {
    		if ( localArr[i].equals( level ) )
    			tmpI = i;
    	}
    	ArrayList<String> arrList = arrayToList( localArr );
    	if ( tmpI + window > arrList.size() ) {
    		//System.out.println("It's not possible!");
        }else{
    		toUse = arrList.subList( tmpI , tmpI + window );	
            for (String s : localArr ) {
                if ( !s.equals( toUse.get( 0 ) ) )
                    before = before + "<" + s;
                else
                    break;
            }
            boolean tmp = false;
            for (int i = 0 ; i < localArr.length ; i++ ) {
            	if (localArr[i].equals( toUse.get( toUse.size() - 1) ) ) {
            		tmp = !tmp;
            		if ( i + 1 < localArr.length )
            			i++;	
            	}
            	if ( tmp )
            		after = after +"<"+ localArr[i];
            }   
    	}
       	toUseArr = listToArray( toUse );

        globalIndex = orderings.size();
        
        perm( toUseArr , toUseArr.length );
		
        for ( int i = globalIndex ; i < orderings.size() ; i++ ) {
            orderings.set( i , before + "<" + orderings.get( i ) + after );
            
        }
        /*/for ( String print : orderings ){ 
    		System.out.println(before+"<"+print + after);
    	}/*/
    }
    
    public static void windowing( String expression , int window , String level ){
        orderings.clear();
        String localArr[] = clearEntry( expression );
        localArr = fix( localArr );
        
        ArrayList<String> toUse = arrayToList( localArr );
         for ( int i = toUse.indexOf( level ) ; i < toUse.size() -1 ; i++ ) {
            
            if ( ( window + i ) -1 > toUse.size() ) 
                break;
            
            window( expression , window , toUse.get( i ) );    
        }
    }

    //used in windowing
    private static String[] listToArray( List<String> list ){
        String[] newOne = new String[ list.size() ];
        
        int i = 0;
        for (String s : list ) {
            newOne[i] = s;
            i++;
        }
        return newOne;
    } 
    //heaps algorithm, used in windowing
    private static void perm(String [] list, int n) {
        if(n == 1)
        {
            orderings.add(arrayToOrdering(list));
            
        } 
        else 
        {
            for(int i=0; i<n; i++)
            {
                perm(list,n-1);

                int j = ( n % 2 == 0 ) ? i : 0; 

                String t = list[n-1];              
                list[n-1] = list[j];
                list[j] = t;                
            }
        }
    }
    //used in perm
    private static String arrayToOrdering( String [] list ){
        String ordering = "";
        for (String i :list ) {
            ordering += i+"<";
        }
        return ordering;
    }
	
}












