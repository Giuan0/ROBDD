

import ITE.*;
import java.util.ArrayList;

public class Test{

	
	public static void main(String[] args) {
		/*/
		Operations ops = new Operations();

		Node x1 = new Node("x1",2);
		Node x2 = new Node("x2",1);

		Node res = ops.ITEA(x1,x2);
		res = ops.ITEO(res,x2);
		System.out.println(res.expression);
		System.out.println(ops.count(res));
		/*/

		ArrayList<String> window = new ArrayList<>();

		window = Window.getResult("x1<x2<x3",2,"x2");

		for (String s : window ) {
			System.out.println(":> "+s);
			
		}

		
	}
}