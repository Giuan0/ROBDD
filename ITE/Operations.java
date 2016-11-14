package ITE;
import java.util.ArrayList;

public class Operations{

	public static int count = 0;
	NodeList<Node> table = new NodeList<Node>();
	NodeList<Node> rebuildList = new NodeList<Node>();


	public String negationIte( String ite ){
		ite = ite.replace(",1", ",+");
		ite = ite.replace(",0", ",1");
		ite = ite.replace(",+", ",0");
		return ite;
	}
	public int reverse(int i){
		if(i == 1)
			return 0;
		else
			return 1;
	}
	public Node negation(Node root){
		if( isSingle(root) ){
			Node node = new Node(root.ID, root.ordering);
			node.expression = negationIte(root.expression);
			
			node.finalT.data = reverse(node.finalT.data);
			node.finalF.data = reverse(node.finalF.data);
			node.finalT.ID = ""+reverse(Integer.parseInt(node.finalT.ID));
			node.finalF.ID = ""+reverse(Integer.parseInt(node.finalF.ID));
			node.finalT.expression = node.finalT.ID;
			node.finalF.expression = node.finalF.ID;


			return node;
		}
		root.finalT.data = reverse(root.finalT.data);
		root.finalF.data = reverse(root.finalF.data);
		root.finalT.ID = ""+reverse(Integer.parseInt(root.finalT.ID));
		root.finalF.ID = ""+reverse(Integer.parseInt(root.finalF.ID));
		root.finalT.expression = root.finalT.ID;
		root.finalF.expression = root.finalF.ID;

		//System.out.println("op1.finalT.ID");
		negationA(root);
		setTrue(root);
		return root;
	}

	public void negationA(Node f){
		if(f.counted == false)
			return;
		if(isTerminal(f))
			return;
		negationA(f.left);
		if(f.counted){
			f.expression = negationIte(f.expression);
			f.counted = false;
		}
		negationA(f.right);
	}

	public int count(Node f){
		int aux = count(f,0);
		this.count = 0;
		setTrue(f);
		return aux;
	}

	public void rebuild(Node root){
		rebuildR(root);
		rebuildList.deleteAll();
	}

	public void rebuildR(Node root){
		if(isTerminal(root))
			return;
		if( !rebuildList.exists(root.expression) ){
			rebuildList.addBack(root);
			if(rebuildList.exists(root.left.expression))
				root.left = rebuildList.returnNode(root.left.expression);
			rebuildR(root.left);
		}else{
			return;
		}
		if(rebuildList.exists(root.right.expression))
			root.right = rebuildList.returnNode(root.right.expression);
		rebuildR(root.right);
	}



	public void resize(Node root){// deixa o robdd com apenas dois nodos finais, 0 e 1
		resize(root, root.finalT, root.finalF);
	}

	public void resize(Node root, Node finalT, Node finalF){
		if(isTerminal(root))
			return;
		resize(root.left, finalT, finalF);
		if(isTerminal(root.left)){
			if(root.left.data == 0)
				root.left = finalF;
			else
				root.left = finalT;
		}
		resize(root.right, finalT, finalF);
		if(isTerminal(root.right)){
			if(root.right.data == 0)
				root.right = finalF;
			else
				root.right = finalT;
		}

	}

	public int count(Node f, int n){//melhorar(sem usar count estatico, por favor ne, algoritimo bosta em)
		if(f.counted == false)
			return count;
		if(isTerminal(f))
			return this.count;
		count(f.left,n);
		if(f.counted){
			this.count++;
			f.counted = false;
			//System.out.println(f.ID+"- ordering -> "+ f.ordering+ "- >"+"right: "+f.right.ID+" left: "+f.left.ID);
		}
		return count(f.right,n);

	}
	public void setTrue(Node f){
		if(isTerminal(f))
			return;
		setTrue(f.left);
		f.counted = true;
		setTrue(f.right);
	}

	public void printInfo(Node node){
		System.out.println("Ordering:"+ node.ordering);
		System.out.println("ID:"+ node.ID);
		System.out.println("Expression:"+ node.expression);
		System.out.println("Data:"+ node.data);
		System.out.println("finalT: "+node.finalT+" - "+node.finalT.data);
		System.out.println("finalF: "+node.finalF+" - "+node.finalF.data);
		System.out.println("Node left:"+node.left);
		System.out.println("Data:"+ node.left.data);
		System.out.println("Left:"+ node.left.left);
		System.out.println("Right:"+ node.left.right);
		System.out.println("ID:"+ node.left.ID);
		System.out.println("Expression:"+ node.left.expression);
		System.out.println("Node right:"+node.right);
		System.out.println("Data:"+ node.right.data);
		System.out.println("Left:"+ node.right.left);
		System.out.println("Right:"+ node.right.right);
		System.out.println("ID:"+ node.right.ID);
		System.out.println("Expression:"+ node.right.expression);
	}

	public Node copy(Node root){
		Node node = new Node(root.ID, root.ordering);
		node.expression = root.expression;
		node.left = node.finalT = new Node(root.left.data);
		node.right = node.finalF = new Node(root.right.data);

		return node; 
	}


	public boolean finG(Node f, Node g){
		if(isTerminal(g))
			return false;
		if(f.expression.equals(g.expression))
			return true;
		return (finG(f, g.left)||finG(f,g.right));
	}
 
	public Node returnFinG(Node f, Node g){
		if(isTerminal(g))
			return null;
		if(f.expression.equals(g.expression))
			return g;
		Node G = returnFinG(f, g.left);
		Node F = returnFinG(f,g.right);
		if(G!=null)
			return G;
		if(F!=null)
			return F;
		return null;
	}


	public Node ITEA(Node f, Node g){
		Node R;
		if(isSingle(f)&&isSingle(g)){
			//System.out.println(f.ID);
			R = ITEa(copy(f), copy(g));
		}else if(isSingle(f)){
			R = ITEa(copy(f), g);
		}else if(isSingle(g)){
			//System.out.println(g.ID);
			R = ITEa(f, copy(g) );
		}else{
			R = ITEa(f,g);
		}
		table.deleteAll();
		resize(R);
		return R;
	}

public Node ITEa(Node f, Node g){


		if(f.ordering>g.ordering){
			Node aux = f;
			f = g;
			g = aux;
		}
		//System.out.println(f.ID);
		
		if(f.data == 1){
			if(table.exists(g.expression))
				return table.returnNode(g.expression);
			else
				return g;
		}if(f.data == 0){
			return new Node(0);
		}
		
		Node x = new Node(f.ID,f.ordering);
		x.expression = (f.ID.equals(g.ID))? f.expression : "("+x.ID+","+g.expression+","+f.right.expression+")";

		table.addBack(x);
		if(f.ID.equals(g.ID)){
			x.left = ITEa(f.left, g.left);
		}else{
			x.left = ITEa(f.left, g);
		}
		if(f.ID.equals(g.ID)){
			x.right = ITEa(f.right, g.right);
		}else{
			x.right = ITEa(f.right, g);
		}

		x.expression = "("+x.ID+","+x.left.expression+","+x.right.expression+")";
		if(x.left.expression.equals(x.right.expression))
			return x.left;
		else
			return x;
	}

	public Node ITEO(Node f, Node g){
		Node R;
		if( isSingle(f)&&isSingle(g) ){
			R =  ITEo(copy(f), copy(g) );
		}else if( isSingle(f) ){
			R = ITEo(copy(f), g );
		}else if( isSingle(g) ){
			R = ITEo(f, copy(g) );
		}else{
			R = ITEo(f,g);
		}
		table.deleteAll();
		resize(R);
		return R;

	}

	public Node ITEo(Node f, Node g){
		
		if(f.ordering>g.ordering){
			Node aux = f;
			f = g;
			g = aux;
		}
		//System.out.println(f.ID);
		
		if(f.data == 1){
			return new Node (1);
		}if(f.data == 0){
			if(table.exists(g.expression))
				return table.returnNode(g.expression);
			else
				return g;
		}
		
		Node x = new Node(f.ID,f.ordering);
		x.expression = (f.ID.equals(g.ID))? f.expression : "("+x.ID+","+f.left.expression+","+g.expression+")" ;

		table.addBack(x);
		if(f.ID.equals(g.ID)){
			x.left = ITEo(f.left, g.left);
		}else{
			x.left = ITEo(f.left, g);
		}
		if(f.ID.equals(g.ID)){
			x.right = ITEo(f.right, g.right);
		}else{
			x.right = ITEo(f.right, g);
		}

		x.expression = "("+x.ID+","+x.left.expression+","+x.right.expression+")";
		if(x.left.expression.equals(x.right.expression))
			return x.left;
		else
			return x;
	}

	public boolean isSingle(Node g){
		if( (isTerminal(g.right) ) && ( isTerminal(g.left) ) )
			return true;
		return false;
	}

	public boolean isComputedAnd(String expression){
		return table.exists(expression);
	}

	public boolean isTerminal(Node node){
		if( node.data == 1 || node.data == 0 )
			return true;
		return false;
	}

	public void print(Node f){
		if(isTerminal(f.right)){
			System.out.println(f.ID+" - right - "+f.right.data);
		}else{
			System.out.println(f.ID+" - right - "+f.right.ID);
		}
		if(isTerminal(f.left)){
			System.out.println(f.ID+" - left - "+f.left.data);
		}else{
			System.out.println(f.ID+" - left - "+f.left.ID);
		}
		
	}

}
