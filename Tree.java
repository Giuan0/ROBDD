import java.util.*;
import ITE.*;
import java.util.ArrayList;
public class Tree{
	String expression;
	Operations op = new Operations();
	ArrayList<String> ordering = new ArrayList<String>();//lista com todas as variaveis

	public int count(Node root){
		return op.count(root);
	}

	public String getOrdering(){
		String ordering = "";
		for (int i = 0;i<this.ordering.size() ;i++ ) {
			ordering += this.ordering.get(i);
			ordering+="<";
		}
		return ordering;
	}
    public void setOrdering(String ordering){
		ArrayList<String> order = new ArrayList<String>();
		String var = "";
		for (int i = 0;i<ordering.length() ;i++ ) {
			if(ordering.charAt(i) == '<'){
				if(var.equals(""))
					continue;
				else{
					order.add(var);
					var = "";
				}
			}else
				var += ordering.charAt(i);
		}
		this.ordering = order;
    }

    public String validate(String expression){
    	expression = expression.replaceAll(" ","");
    	//expression = expression.replaceAll("||","+");
    	//expression = expression.replaceAll("&&","*");
    	expression = expression.replace('∧','*');
    	expression = expression.replace('∨','+');
    	expression = expression.replace('∼','!');
    	return expression;
    }

	public void autoDeclareVariables(String expression){
		this.ordering.clear();
		expression = expression.replaceAll(" ","");
		expression = expression.replace('*','<');
		expression = expression.replace('+','<');
		expression = expression.replace('(','<');
		expression = expression.replace(')','<');
		expression = expression.replace('!','<');
		expression = expression+"<";
		String var = "";
		for (int i = 0;i<expression.length() ;i++ ) {
			if(expression.charAt(i) == '<'){
				if(var.equals(""))
					continue;
				else{
					if(	!(this.ordering.contains(var)))
						ordering.add(var);
					var = "";
				}
			}else
				var += expression.charAt(i);
		}
	}

	public Node generateG(String expression){
		expression = validate(expression);
		if(ordering.size() == 0)
			autoDeclareVariables(expression);
		this.expression = expression;
		Node node = generate(this.expression);
		op.rebuildR(node);
		return node;
	}
	public Node generate(String expressiomn){
		String var = "";
		Node node = new Node("x",0);
		for(int i = 0; i<this.expression.length();i++){

			if( this.expression.charAt(i) == '!'){
				this.expression = this.expression.substring(i+2,this.expression.length());
				//System.out.println(expression);
				node = op.negation(generate(this.expression));
			}

			if((this.expression.length() == 0))
				return node;

			if( this.expression.charAt(i) == '('){
				this.expression = this.expression.substring(i+1,expression.length());
				node = generate(this.expression);
			}

			if((this.expression.length() == 0))
				return node;	

			if(this.expression.charAt(i) == '+'){
				int aux = i;
				i = 0;
				if(var.equals("")){
					this.expression = this.expression.substring(aux+1,this.expression.length());
					return op.ITEO(node, generate( this.expression) );
				}else{
					this.expression = this.expression.substring(aux+1,this.expression.length());
					return op.ITEO(new ITE.Node(var,ordering.indexOf(var)), generate( this.expression) );
				}	
			}else if( this.expression.charAt(i) == '*'){
				int aux = i;
				i = 0;
				if(var.equals("")){
					this.expression = this.expression.substring(aux+1,this.expression.length());
					return op.ITEA(node, generate( this.expression) );
				}else{
					this.expression = this.expression.substring(aux+1,this.expression.length());
					return op.ITEA(new ITE.Node(var,ordering.indexOf(var)), generate( this.expression) );
				}
			}else if( this.expression.charAt(i) == ')'){
				this.expression = this.expression.substring(i+1,this.expression.length());
				if(var.equals("")){
					return node;
				}
				return new ITE.Node(var,ordering.indexOf(var));
			}
			else{
				var+= this.expression.substring(i,i+1);
			}
		}
		if(this.expression.length() == 0)
			return node;
		return new ITE.Node(var,ordering.indexOf(var));
	}

// metodos que pegam os caminhos#######################################################

	public ArrayList<String> allPaths(int num){// number of paths given the number of variables
		ArrayList<String> paths = new ArrayList<String>();
		for (int i=0;i<(Math.pow(2,num));i++){
	    	int mask = (int)(Math.pow(2,num));
	    	String path = "";
		    while (mask > 0){
		        if ((mask & i) == 0){
		            path+="0";
		        } else {
		            path+="1";
		        }
		        mask = mask >> 1;
		    }
		    paths.add(path.substring(1,path.length()));
		    path = "";
		}
		return paths;
	}


	public String reducedPath(Node root, String path){  //gets a path and reduce it(ex? 00011001 => 000)
		Node aux = root;
		String reducedPath = "";
		for (int i = 0;i<path.length() ;i++ ) {
			if(aux.right == null){
				reducedPath+=" ("+aux.data+")";
				return reducedPath;
			}
			if(path.charAt(i) == '1'){
				reducedPath+="1";
				aux = aux.left;
			}else{
				reducedPath+="0";
				aux = aux.right;
			}
			reducedPath = path;
		}
		return reducedPath+=" ("+aux.data+")";
	}




	public ArrayList<String> allReducedPaths(Node root, ArrayList<String> ordering){
		ArrayList<String> allPaths = allPaths(ordering.size());
		ArrayList<String> allReducedPaths = new ArrayList<String>();
		for (int i = 0; i<allPaths.size() ;i++ ) {
			if(!(allReducedPaths.contains( reducedPath( root, allPaths.get(i) ))))
				allReducedPaths.add( reducedPath( root, allPaths.get(i) ));
		}

		allReducedPaths.remove("");
		Collections.sort(allReducedPaths, new Comparator<String>() {
		    public int compare(String a, String b) {
		        return Integer.compare(a.length(), b.length());
		    }
			});
		return allReducedPaths;

	}

	public String getMinPath(Node root, ArrayList<String> ordering){
		ArrayList<String> allPaths = this.allReducedPaths(root, ordering);
		for (String i : allPaths) {
			if(i.charAt(i.length()-2) == '1')
				return i;
		}
		return "";
	} 

//#######################################################################


}