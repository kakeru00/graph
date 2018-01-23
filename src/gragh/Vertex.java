package gragh;

import java.io.Serializable;

class Vertex implements Serializable{  
    private int vertexName;  
    public boolean wasVisited;  
     
   public Vertex(int name){  
        vertexName = name;  
        wasVisited = false;  
    }  
   
   public int nameOfVertex(){
	   return vertexName;
   }
   public void displayVertexName(){  
       System.out.println(" name:"+vertexName);  
    }  
}  

