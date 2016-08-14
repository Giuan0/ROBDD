package ITE;
public class Node {

	public String bOrdering;
	public String ID, expression;// expression = ITE
	public int data;
	public Node left;
	public Node right;
	public int ordering;
	public boolean counted;//serve para o count
	public Node finalT;//serve para reduzir a quantidade de nodos finais, caso esse seja o nodo root
	public Node finalF;



	public Node(String ID, int ordering){
		this.ordering = ordering;
		this.ID = ID;
		expression = "("+ID+",1,0)";
		data = -1;
		left = finalT = new Node(1);
		right = finalF = new Node(0);
		counted = true;

	}

	public Node(int i){
		data = i;
		left = null;
		right = null;
		ID = expression = ""+i;
		counted = true;
	}
}