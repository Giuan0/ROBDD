import ITE.*;
public class Test{
	public static void main(String[] args) {
		Tree tree = new Tree();
		Operations op = new Operations();
		tree.setOrdering("x1<x3<x5<x7<x2<x4<x6<x8<");
		Node root = tree.generateG("((x1*x2) + (x3*x4) + (x5*x6) + (x7*x8))");

		for (String i : tree.allReducedPaths(root) ) {
			System.out.println(i);	
		}

		System.out.println("size = "+op.count(root));

		System.out.println(root.expression);
	}
}