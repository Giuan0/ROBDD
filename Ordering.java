import ITE.*;
import java.util.ArrayList;
import java.util.Arrays;
public class Ordering{

    ArrayList<String> orderings = new ArrayList<String>();
    Tree tree = new Tree();
    Operations op = new Operations();


    public String arrayToOrdering(String [] list){
        String ordering = "";
        for (String i :list ) {
            ordering += i+"<";
        }
        return ordering;
    }

    public Node getMin(String expression){
        System.out.println("##################################################");
        Node root = tree.generateG(expression);
        root.bOrdering = tree.getOrdering();
        Node minN = root;
        int min = op.count(root);
        perm(tree.ordering.toArray(new String[tree.ordering.size()]),tree.ordering.size());
        int totalPermutations = orderings.size();
        int count = 0;
        double percentage = 2;

        for (String i : orderings ) {
            count++;
            if( Math.round( totalPermutations*(percentage/100) ) == count ){
                System.out.print("#");
                percentage +=2; 
            }
            tree.setOrdering(i);
            root = tree.generateG(expression);
            int n = op.count(root);
            if(n<min){
                min = n;
                minN = root;
                root.bOrdering = i;
            }
        }
        System.out.println();
        orderings.clear();
        return minN;
    }

    public Node window(String expression, String ordering, int window, String level){
        ArrayList<String> windowList = Window.getResult(ordering, window, level);
        Node min = tree.generateG(expression);
        int minSize = op.count(min);
        for (String i : windowList ) {
            tree.setOrdering(i);
            Node root = tree.generateG(expression);
            int count = op.count(root);
            System.out.println(i+" size - "+ count);
            if(count < minSize){
                minSize = count;
                min = root;
                min.bOrdering = i;
            }
        }
        return min;
    }

    public Node window(String expression, int window, String level){
        tree.autoDeclareVariables(expression);
        System.out.println(tree.getOrdering());
        ArrayList<String> windowList = Window.getResult(tree.getOrdering(), window, level);
        
        Node min = tree.generateG(expression);
        int minSize = op.count(min);
        for (String i : windowList ) {
            tree.setOrdering(i);
            Node root = tree.generateG(expression);
            int count = op.count(root);
            System.out.println(i+" size - "+ count);
            if(count < minSize){
                minSize = count;
                min = root;
                min.bOrdering = i;
            }
        }
        return min;
    }

    public String arrayListToOrdering(ArrayList<String> list){
        String ordering = "";
        for (String e : list ) {
            ordering +=e+"<"; 
        }
        return ordering;

    }

    public String weighting(String expression){
        return arrayListToOrdering( Weighting.getResult(expression) );
    }

    //heaps algorith
    public void perm(String [] list, int n) 
    {
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

}