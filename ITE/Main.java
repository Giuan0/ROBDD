public class Main{
	static int count = 0;
	public static void main(String args[]){
		Operations o = new Operations();

		Node [] x = new Node[12];
		for (int i = 0;i<12 ; i++ ) {
			x[i] = new Node(("x"+(""+(i+1))),i);
		}


		Node x1 = new Node("x1",1);
		Node x2 = new Node("x2",2);
		Node x3 = new Node("x3",3);
		Node x4 = new Node("x4",4);
		Node x5 = new Node("x5",5);
		Node x6 = new Node("x6",6);
		Node x7 = new Node("x7",7);
		Node x8 = new Node("x8",8);
		Node x9 = new Node("x9",9);
		Node x10 = new Node("x10",10);
		Node x11 = new Node("x11",11);
		Node x12 = new Node("x12",12);

		Node op1;
		Node op2;
		Node op3;
		Node op4;
		Node op5;
		Node op6;
		Node op7;
		Node op8;
		Node op9;
		Node op10;
		Node op11;
		Node op12;
		Node op13;
		Node op14;
		Node op15;
		Node op16;
		Node op17;

		op1 = o.ITEA(x[0],x[1]);
		op1 = o.ITEA(op1,x[2]);
		op2 = o.ITEA(x[2],x[3]);
		op1 = o.ITEA(op1,op2);
		op2 = o.ITEO(x[3],x[4]);
		op1 = o.ITEO(op1,op2);


		op2 = o.ITEA(x[0],x[5]);
		op3 = o.ITEA(x[6],x8);
		op2 = o.ITEO(op2,op3);
		op2 = o.ITEA(op2,x9);
		op3 = o.ITEA(x6,x3);
		op2 = o.ITEO(op2,op3);

		op3 = o.ITEA(x7,x2);
		op4 = o.ITEA(x7,x4);
		op5 = o.ITEO(op4,op3);
		op5 = o.ITEA(op5,x10);
		op4 = o.ITEA(x2,x11);
		op5 = o.ITEO(op5,op4);

		op6 = o.ITEA(op2,op5);

		op7 = o.ITEO(op1,op6);
		//System.out.println("jhk");
		System.out.println(o.count(op7));
		//o.print(op1);
		System.out.println(op7.expression);
		//o.print(op5.right);
		//o.print(op5.left);
		//System.out.println("table:");
		//o.table.print();
		//System.out.println(op1.expression);




		//operations.ITE(x5,new Node(1), x6);
		//operations.ITE(x1,x5, new Node(0));
		//operations.ITE(x1_1,x4, new Node(0));
		//operations.ITE(x1_1,new Node(1),x1);
		//operations.and(x1,x2);
		//Operations.and(x1,x3);

		//System.out.println(x1.ID);
		//System.out.println(x2.ID);
		//System.out.println(x3);
		//System.out.println(x4.ID);
		//System.out.println(x5);
		//System.out.println();
		//operations.print(op5);
		//operations.print(op5.right);
		//operations.print(op5.right.left);
		//operations.print(op5.right.left.left);
		//operations.print(op5.right.left.right);
		//System.out.println(op5.right.left.left.right);
		//System.out.println(op5.right.left.right.left);
		//operations.print(x1);
		//operations.print(x1.left);
		//operations.print(x1.right);
		//operations.print(x4);
		//operations.count(op5);
		//System.out.println(operations.count(op5));
		//System.out.println(x1_1.right.data);
		//System.out.println(x1.left.ID );
		//System.out.println(x3+" ->"+ x3.left.data);



	}
}

//((x[1]*x[2]+x[3]))+(x[1]*x[4])
//(((x[1]*x[2])+(x[2]+x[4])))*(x[5]+x[2])
//(((((x[1]*x[2])+(x[2]+x[4])))*(x[5]+x[2]))*x[6])+x[7] - Visbdd gerando errado
//(((x[1]*x[2])*x[3])*(x[3]*x[4])+(x[4]+x[5]))+(((x[1]*x[6])+(x[7]*x[8]))*(x[9])+(x[6]*x[3]))*(((x[7]*x[2])+(x[7]*x[4]))*(x[10])+(x[2]*x[11])) 
