import ITE.*;
import java.util.ArrayList;
import java.util.Scanner;
public class ROBDD{
	
	private Node root;
	private ArrayList<String> ordering;
	private String expression;

	private Tree treeOps;
	private Operations logicalOps;
	private Ordering orderingsOps;


	public ROBDD(){
		this.init();
	}

	public ROBDD(String expression){
		this.init();
		this.expression = expression;
		this.root       = treeOps.generateG(expression); 
		this.setOrdering(treeOps.getOrdering());
	}

	public ROBDD(String expression, String ordering ){
		this.init();
		this.expression = expression;
		this.setOrdering(ordering);
	}

	public void init(){
		this.treeOps    = new Tree();
		this.ordering   = new ArrayList<String>();
		this.logicalOps = new Operations();
		this.orderingsOps = new Ordering();
	}

	public String getExpression(){
		return this.expression;
	}

	public void setExpression(String expression){//procurar como chamar proprio construtor
		treeOps         = new Tree();
		this.expression = expression;
		this.root       = treeOps.generateG(expression);
		this.setOrdering(treeOps.getOrdering());
	}

	public void setExpression(String expression, String ordering){
		treeOps         = new Tree();
		this.expression = expression;
		this.setOrdering(ordering);
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

		//System.out.println("set :"+ordering);
		this.init();
		this.ordering = order;
		this.treeOps.setOrdering(ordering);

		this.root       = treeOps.generateG(this.expression);
    }

    public String getITE(){
    	return this.root.expression;
    }
	
	public int size(){
		return logicalOps.count(this.root);
	}

	public void setMin(){
		this.root = orderingsOps.getMin(this.expression);
		//this.treeOps.setOrdering(this.root.bOrdering);

		//System.out.println(logicalOps.count(treeOps.generateG(this.expression)));
		this.setOrdering(this.root.bOrdering);
	}
	public ArrayList<String> getAllResults(){
		return orderingsOps.getAllResults(this.expression);
	}
	public void setWindowOrdering(int window, int level){
		this.root = orderingsOps.window(this.expression, this.getOrdering(), window, level );
		this.setOrdering(root.bOrdering);
	}

	public void setWindowOrdering(int window, String var){
		this.root = orderingsOps.window(this.expression, this.getOrdering(), window, this.ordering.indexOf(var) );	
		this.setOrdering(this.root.bOrdering);
	}
	public void setMinOrdering(){
		this.root = orderingsOps.getMin(this.expression);
		this.setOrdering(this.root.bOrdering);
	}
	public void setWeightingOrdering(){
		this.init();
		this.setOrdering(this.orderingsOps.weighting(this.expression));
		//System.out.println(this.getOrdering()); //alguma coisa errada com o m[etodo weighting,
	}
	public ArrayList<String> getAllPaths(){
		return treeOps.allReducedPaths(this.root, this.ordering);
	}
	public ArrayList<String> getAllPaths_Maped(){
		ArrayList<String> mapedPaths = new ArrayList<String>();
		getAllPaths().forEach(path->{
			String mapPath = mapPath(path);
			if(!mapedPaths.contains(mapPath))
				mapedPaths.add(mapPath);
		});
		return mapedPaths;
	}
	public String getMinPath(){
		return treeOps.getMinPath(this.root, this.ordering);
	}
	private String mapPath(String path){
		String mapedPath = "";
		Node aux = this.root;
		for (int i = 0;i<path.length()-4 ;i++) {
			if(aux.ID.equals("1")||aux.ID.equals("0"))
				return mapedPath.substring(0, mapedPath.length()-1)+" - "+path.substring(path.length()-3,path.length());;
			mapedPath+= aux.ID+":"+path.charAt(i)+",";
			if(path.charAt(i) == '0'){
				aux = aux.right;
			}else{
				aux = aux.left;
			}
		}
		return mapedPath.substring(0, mapedPath.length()-1)+" - "+path.substring(path.length()-3,path.length());
	}
	public String getMinPath_Maped(){
		return this.mapPath(getMinPath());
	}
	public String getPath(String path){//alterar
		return treeOps.reducedPath(this.root, path);
	}
}