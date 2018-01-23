package gragh;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

//ͼ���ڽӾ���ʵ��
public class Graph implements Cloneable,Serializable{  
    private int MAX_VERTEX;  
    private Vertex[] vertexList;  
    private Stack stack;  
    private queue que;  
    private int adjMatrix[][];  
    private int nVertex;  
    private int nEdge;

    
    public Graph(){
    	
    }
    
   public Graph(int max){  
        MAX_VERTEX = max;  
       vertexList = new Vertex[MAX_VERTEX];  
        stack = new Stack(MAX_VERTEX);//��ȱ���ʹ��  
        que = new queue(MAX_VERTEX);//��ȱ���ʹ��  
        adjMatrix = new int[MAX_VERTEX][MAX_VERTEX];  
        nVertex = 0; 
        nEdge=0;
        
       for(int i = 0; i < MAX_VERTEX; i++){  
            for(int j = 0; j< MAX_VERTEX ; j++){  
               adjMatrix[i][j] = 0;  
            }  
       }  
          
    }  
   
   public void initial(int max){
  	 MAX_VERTEX = max;  
       vertexList = new Vertex[MAX_VERTEX];  
   //     stack = new Stack(MAX_VERTEX);//��ȱ���ʹ��  
   //     que = new queue(MAX_VERTEX);//��ȱ���ʹ��  
        adjMatrix = new int[MAX_VERTEX][MAX_VERTEX];  
        nVertex = 0; 
        nEdge=0;
        
        
        
       for(int i = 0; i < MAX_VERTEX; i++){  
            for(int j = 0; j< MAX_VERTEX ; j++){  
               adjMatrix[i][j] = 0;  
            }  
       }  
  }
    public boolean addVertex(int name){//��������Ϊname�Ķ��㣬�������ſ��ܲ�ͬ  
        if(nVertex < MAX_VERTEX){  
           vertexList[nVertex++] = new Vertex(name);;  
            return true;  
       }  
        else{  
            return false;  
       }  
    } 
    
    public boolean removeVertex(int v){//ɾ�����Ϊi�Ķ���
    	if ((v<0) || (v>=nVertex)) return false;
    	
    	vertexList[v]=vertexList[nVertex-1];//�������ɾ���õ�
    	
    	for (int i=0;i<nVertex;i++) 
    		if (adjMatrix[i][v]>0) nEdge--;//��ȥ��v��صı�
    	
    	for (int i=0;i<nVertex;i++)
    		adjMatrix[i][v]=adjMatrix[i][nVertex-1];//���һ������v��
    	
    	nVertex--;
    	
    	for (int j=0;j<nVertex;j++)
    		adjMatrix[v][j]=adjMatrix[nVertex][j];//���һ������v��
    	
    	return true;
    	
    }
    
    public boolean addEdge(int startVertex, int endVertex){  
        if(startVertex < nVertex && endVertex < nVertex && startVertex>-1 && 
        		endVertex>-1 && adjMatrix[startVertex][endVertex]==0){  
            adjMatrix[startVertex][endVertex] = 1;  
            adjMatrix[endVertex][startVertex] = 1; 
            nEdge++;
            return true;  
        }else{  
           return false;  
        }  
   } 
    
    public boolean removeEdge(int startVertex, int endVertex){
    	if(startVertex < nVertex && endVertex < nVertex && startVertex>-1 && endVertex>-1
    			&& adjMatrix[startVertex][endVertex]==1){  
            adjMatrix[startVertex][endVertex] = 0;  
           adjMatrix[endVertex][startVertex] = 0; 
           nEdge--;
            return true;  
        }else{  
           return false;  
        }  
    }
    
    public boolean edgeIsExist(int startVertex, int endVertex){
    	if (adjMatrix[startVertex][endVertex] == 1)
    		return true;
    	else return false;  	
    }
    
    //拥有最大的度的顶点编号
    public int maxDegree(){
    	int noOfMax=0;
    	int max=0;
    	
    	for (int j=0;j<nVertex;j++){
			if (adjMatrix[0][j]==1) max++;
		}
    	
    	for (int i=1;i<nVertex;i++){
    		int n=0;
    		for (int j=0;j<nVertex;j++){
    			if (adjMatrix[i][j]==1) n++;
    		}
    		if (n>max) {
    			max=n;
    			noOfMax=i;
    		}
    	}
    	return noOfMax;
    }
    
    public int firstneighbor(int i){
    	for (int j=0;j<nVertex;j++){
    		if (adjMatrix[i][j]==1) return j;
    	}
    	return i;//��ʾû���ڽӵ�
    }
    
 /*   public boolean hasDegreeZero(){
    	for (int i=0;i<nVertex;i++){
    		int flag=0;
			for (int j=0;j<nVertex;j++){
				if (adjMatrix[i][j]==1) flag=1;
			}
			if (flag==0) return true;
		}
    	return false;
    }
   */ 
    
    //分别找出所有点各自的邻居，wod1为一个点，wod2为该点的所有邻居
    public void selectVertexNeightbor(Set<Integer> wod1,Set<Integer> wod2){
    	for (int i=0;i<nVertex;i++){
    		for (int j=0;j<nVertex;j++){
    			if (adjMatrix[i][j]==1) {
    				wod1.add(nameOfVertex(i));
    				break;
    			}
    		}
    		if (!wod1.isEmpty()) {
    			for (int j=0;j<nVertex;j++){
        			if (adjMatrix[i][j]==1) {
        				wod2.add(nameOfVertex(j));
        			
        			}
        		}
    		break;	
    		}
    		
    	}
    	
    	
    }
    public boolean deleteDegreeZero(){
    	int flag;
    	for (int i=0;i<nVertex;i++){
    		 flag=0;
			for (int j=0;j<nVertex;j++){
				if (adjMatrix[i][j]==1) flag=1;
			}
			if (flag==0) {
				removeVertex(i);
				
				System.out.println("deletezero:"+i);
				return true;
			}
		}
    	return false;
    }
    
    
    public boolean deleteDegreeone(Set<String> words){
    //	int flag;
    	int n;
    	int neighbor;
   // 	System.out.println("nVertex:"+nVertex);
    	
    	for (int i=0;i<nVertex;i++){
    	//	 flag=0;
    		 n=0;
			for (int j=0;j<nVertex;j++){
				if (adjMatrix[i][j]==1) n=n+1;
			}
			
			
			if (n==1) {
				neighbor=firstneighbor(i);
				words.add(Integer.toString(nameOfVertex(neighbor)));
				
				if (neighbor<i){
			//		printGragh();
					removeVertex(i);
					
					System.out.println();
				
					removeVertex(neighbor);
					
				}
				else
				{
				//	printGragh();
					removeVertex(neighbor);
					
					System.out.println();
				
					removeVertex(i);
					
				}
				
				
				System.out.println("deleOne:"+i);
				return true;
			}
		}
    	return false;
    }
    
  
    
    public boolean deleteDegreetwo(Set<String> words){//û���
    //	int flag;
    	int n;
    	int neighbor1=0,neighbor2=0;
    	
    	Set<Integer> temp=new HashSet<Integer>();
    	
    	
    	
    	
    	for (int i=0;i<nVertex;i++){
    	//	 flag=0;
    		 n=0;
			for (int j=0;j<nVertex;j++){
				if (adjMatrix[i][j]==1) n++;
			}
			
			
			if (n==2)  {//��Ϊ2
							
				neighbor1=firstneighbor(i);//��һ���ڽӵ�
							
				int n1=0;
				for (int j=0;j<nVertex;j++){//��2���ڽӵ�
					if (adjMatrix[i][j]==1) {n1=n1+1;}
					
										
					if (n1==2) {neighbor2=j ; break;}
					
				}	
				
				if (adjMatrix[neighbor1][neighbor2]==1){//2���ڽӵ�����
					
				//	printGragh();
					System.out.println();
					System.out.println("delatetwo-1:"+i+" "+neighbor1+" "+neighbor2);
					
					words.add(Integer.toString(neighbor1));//�������
					words.add(Integer.toString(neighbor2));
					
					temp.clear();
					temp.add(Integer.valueOf(nameOfVertex(i)));//��Ҫɾ���Ķ������Ʊ���
					temp.add(Integer.valueOf(nameOfVertex(neighbor1)));
					temp.add(Integer.valueOf(nameOfVertex(neighbor2)));
					

					
					Iterator<Integer> iter=temp.iterator();     //ɾ�� 
			        while (iter.hasNext())
			        {     
			        	Integer it=iter.next();
			            for (int a=0;a<nVertex;a++){
			            	if (nameOfVertex(a)==it) {removeVertex(a);break;}
			            }
			        }      
					
			        return true;
					
				}//adjMatrix[neighbor1][neighbor2]==1
				
				else {
					
					try {
					 File file = new File("D:\\temp\\temp.txt");  
			         FileWriter fw=new FileWriter(file,true);
					
				//	printGragh();
					System.out.println();
					System.out.println("delatetwo-2:"+i+" "+neighbor1+" "+neighbor2);
					
					for (int j=0;j<nVertex;j++){
						if (adjMatrix[neighbor1][j]==1 && i!=j) {
						//	System.out.println("addedge is true");
							addEdge(i,j);}
						if (adjMatrix[neighbor2][j]==1 && i!=j) {
						//	System.out.println("addedge2 is true");
							addEdge(i,j);}
					}
					
					fw.write(Integer.toString(nameOfVertex(i))+",");//д��
					fw.write(Integer.toString(nameOfVertex(neighbor1))+",");
					fw.write(Integer.toString(nameOfVertex(neighbor2))+",");
					fw.write("\r\n");
					
					temp.clear();
				//	temp.add(Integer.valueOf(nameOfVertex(i)));//��Ҫɾ���Ķ������Ʊ���
					temp.add(Integer.valueOf(nameOfVertex(neighbor1)));
					temp.add(Integer.valueOf(nameOfVertex(neighbor2)));
					
					Iterator<Integer> iter=temp.iterator();     //ɾ�� 
			        while (iter.hasNext())
			        {     
			        	Integer it=iter.next();
			            for (int a=0;a<nVertex;a++){
			            	if (nameOfVertex(a)==it) {removeVertex(a);break;}
			            }
			        }    
			        
			        fw.flush();
			        
					}catch(Exception e){System.out.println("error: " + e);}	
					
					return true;
				}
				
				
			
				
			}//n==2
		}//for (int i=0;i<nVertex;i++)
    	return false;
    } 
    
    
    public int numberOfVertex(){
    	return nVertex;
    }
    
    public int numberOfEdge(){
    	return nEdge;
    }  
    
    public int nameOfVertex(int i){
    	return vertexList[i].nameOfVertex();
    }
    
    public boolean deleteVertexByName(int name){
    	for (int a=0;a<nVertex;a++){
        	if (nameOfVertex(a)==name) {removeVertex(a);return true;}
        }
    	
    	return false;
    }
    
    public void printGragh(){
        for(int i = 0; i < nVertex; i++){  
        	System.out.println();
            for(int j = 0; j< nVertex ; j++){  
               System.out.print(adjMatrix[i][j]);  
            }  
       } 
    }
    
    
    
    public void read(){
    	try{
 
    		String s;
    		String[] ss;
    		BufferedReader ir=new BufferedReader(new InputStreamReader(new FileInputStream(new File("D:\\temp\\a.txt"))));
    	//	FileInputStream fis = new FileInputStream(new File("D:\\temp\\a.txt"));
    	
    	
    		int max1=(s=ir.readLine()).split(",").length;
    		System.out.println(max1);
    		initial(max1);
    		
    		for (int i=0;i<max1;i++){//���ӵ�
    			addVertex(i);
    		}
    		
    		BufferedReader ir1=new BufferedReader(new InputStreamReader(new FileInputStream(new File("D:\\temp\\a.txt"))));
    		
    		int i=-1;
    		while ((s=ir1.readLine()) != null){

    			i=i+1;
    			ss=s.split(",");
    			for (int j=i+1;j<ss.length;j++) {
    				if (Integer.parseInt(ss[j])==1) addEdge(i,j);
    			}
    			
    		}
    		
    		ir.close();
    		ir1.close();
    	
    	}
    	
    	catch(Exception e){System.out.println("error: " + e);}	
    	
    	printGragh();
    	
    }
    
    
    public void write(){
    	try {

       File file = new File("D:\\temp\\a.txt");  
       FileWriter fw=new FileWriter(file);
    //   FileOutputStream fos = new FileOutputStream(file);
                    
       for (int i=0;i<nVertex;i++){
   		for (int j=0;j<nVertex;j++){
   	//		System.out.println(adjMatrix[i][j]);
   			
   			fw.write(Integer.toString(adjMatrix[i][j])+",");
   		  }
   		fw.write("\r\n");
     	  }
       fw.flush();
       System.out.println("write success!");
    	}
    	    	
       catch(Exception e){System.out.println("error: " + e);}	
    	
    }
    
    public int getUnvisitedVertex(int j){//��ȡ��ö����ڽӵ�δ���ʶ���  
        for(int i = 0; i < nVertex ; i++){  
            if(adjMatrix[j][i] == 1 && vertexList[i].wasVisited == false){//�ɴ��δ�����ʹ�  
                return i;  
            }  
        }  
        return -1;  
    }  
  /*  public void depthFirstSearch(){  
       stack.push(0);//���Ƚ���0��Ԫ����ջ��  
        //vertexList[0].displayVertexName();//******��Ҫ�����ͼ����С����������ɾ�����д��룻  
       vertexList[0].wasVisited = true;//��ʶΪ�ѷ���  
        while(!stack.isEmpty()){//��ջ����  
           int unvisitedVer = getUnvisitedVertex((int)stack.getTopValue());//��ȡһ����ö����ڽӵ�δ���ʶ���  
           if(unvisitedVer != -1){ //�����������������δ���ʶ��㣻  
               vertexList[(int)stack.getTopValue()].displayVertexName();
               //<span style="color:#FF0000;">//******��Ҫ�����ͼ����С����������������д��룻</span>  
                vertexList[unvisitedVer].displayVertexName();//������������ʶΪ�ѷ���  
               vertexList[unvisitedVer].wasVisited = true;  
                System.out.println(" ");  
               stack.push(unvisitedVer);//��ջ  
            }else{//��ö������������ж���������ʹ������ö����ջ��  
                stack.pop();  
           }  
       }//  
        for(int i = 0; i< nVertex; i++){//���ö����δ�����ʹ�����֤�´α����ܽ��С�  
            vertexList[i].wasVisited = false;  
        }  
    }  
    public void breadthFirstSearch(){  
        que.insert(0);  
        vertexList[0].displayVertexName();  
        vertexList[0].wasVisited = true;  
        while(!que.isEmpty()){  
            int unvisit = getUnvisitedVertex((int)que.getFrontElement());  
            if(unvisit != -1){  
               vertexList[unvisit].displayVertexName();  
                vertexList[unvisit].wasVisited = true;  
               que.insert(unvisit);  
           }else{  
                que.remove();  
            }  
       }  
       for(int i=0; i< nVertex ; i++){  
           vertexList[i].wasVisited = false;  
        }  
   }  */



	public Vertex[] getVertexList() {
		return vertexList;
	}

    
    public boolean isEmpty(){
    	return MAX_VERTEX==0;
    }
    
	public int degreeOfVertex(int vertex){//根据顶点编号，求度
		int degree = 0;
		for(int i=0; i<nVertex; i++){
			if(adjMatrix[vertex][i]==1)
				degree++;
		}
		return degree;
	}
	
	public int degreeOfVertexName(int name){//根据顶点名，求度
		for (int a=0;a<nVertex;a++){
        	if (nameOfVertex(a)==name) 
        		return degreeOfVertex(a);
        }
		return 0;
	}
	
	public List<Vertex> neighborOfVertexName(int vertexName){
		for (int a=0;a<nVertex;a++){
        	if (nameOfVertex(a)==vertexName) 
        		return neighborOfVertex(a);
        }
		return null;
	}
	
	public List<Vertex> neighborOfVertex(int vertex){//根据顶点编号，求邻居
		List<Vertex> neighbor = new ArrayList<Vertex>();
		for (int j=0;j<nVertex;j++){
			if (adjMatrix[vertex][j]==1) {
				neighbor.add(vertexList[j]);
			
			}
		}
		return neighbor;
	}
	
	@Override  
    public Object clone() throws CloneNotSupportedException  
    {  
        return super.clone();  
    } 
	
	 /**
     * 利用串行化深克隆一个对象，把对象以及它的引用读到流里，在写入其他的对象
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Object deepClone() throws IOException,ClassNotFoundException {
        //将对象写到流里
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(this);
        //从流里读回来
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return ois.readObject();
    }
    
}  
