package ITE;

public class NodeList<T>{

	public class NodeL{
		NodeL nextNode;
		public Node data;

		public NodeL(Node data){
			nextNode = null;
			this.data = data;
		}

	}



	public NodeL first;
	public NodeL last;

	public NodeList(){
		last = first = null;
	}
	
	public void addBack(Node data){
		if(first == null)
			first = last = new NodeL(data);
		else
			last = last.nextNode = new NodeL(data);
	
	}

	public void deleteAll(){
		last = first = null;
	}

	public boolean exists(String expression){
		NodeL aux = first;

		while(aux != null){
			if(aux.data.expression.equals(expression) )
				return true;
			aux = aux.nextNode;
		}
		return false;
	}

	public Node returnNode(String expression){
		NodeL aux = first;

		while(aux != null){
			if(aux.data.expression.equals(expression) ){
				//System.out.println(expression+"aqui");
				return aux.data;
			}
			aux = aux.nextNode;
		}
		return null;
	}

	public void print(){
		NodeL aux = first;
		while(aux != null){
			System.out.println(aux.data.expression+"  --"+ aux.data.data);
			aux = aux.nextNode;
		}
	}
	
}