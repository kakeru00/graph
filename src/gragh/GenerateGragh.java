package gragh;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class GenerateGragh {
	
	int numofvertex;//顶点
	int numofedge;//边
	Graph gh;
	Random r=new Random();
	
	public GenerateGragh(int numberofvertex,int numberofedge){
		numofvertex=numberofvertex;
		numofedge=numberofedge;
	}
	
	public GenerateGragh(){
		
	}
	
	
	
	public Graph generateByFile(){
		gh=new Graph();
		try{
			 
    		String s;
    		String[] ss;
    		BufferedReader ir=new BufferedReader(new InputStreamReader(new FileInputStream(new File("D:\\temp\\a.txt"))));
    	//	FileInputStream fis = new FileInputStream(new File("D:\\temp\\a.txt"));
    	
    	
    		int max1=(s=ir.readLine()).split(",").length;
    		System.out.println(max1);
    		gh.initial(max1);
    		
    		for (int i=0;i<max1;i++){//���ӵ�
    			gh.addVertex(i);
    		}
    		
    		BufferedReader ir1=new BufferedReader(new InputStreamReader(new FileInputStream(new File("D:\\temp\\a.txt"))));
    		
    		int i=-1;
    		while ((s=ir1.readLine()) != null){

    			i=i+1;
    			ss=s.split(",");
    			for (int j=i+1;j<ss.length;j++) {
    				if (Integer.parseInt(ss[j])==1) gh.addEdge(i,j);
    			}
    			
    		}
    		
    		ir.close();
    		ir1.close();
    	
    	}
    	
    	catch(Exception e){System.out.println("error: " + e);}	
    	
 //   	gh.printGragh();
    	
    	return gh;
	}
	
	public Graph generate(){
		gh=new Graph(numofvertex);//具有numofvertex个顶点的图
		//逐个顶点按名添加进去
		for (int i=0;i<numofvertex;i++){
			gh.addVertex(i);
		}
		
		
		for (int i=0;i<numofedge;i++){
			int startvertex=0;
			int endvertex=0;
			while (true){
			 startvertex=generateRandomNumber(numofvertex);
			 endvertex=generateRandomNumber(numofvertex);
			if ((startvertex!=endvertex) && (!gh.edgeIsExist(startvertex,endvertex))) break;
			
			}
		//	System.out.println("start:"+startvertex);
		//	System.out.print("end:"+endvertex);
		//	System.out.println();
			
			gh.addEdge(startvertex, endvertex);
		}
	//	gh.printGragh();
		//System.out.println();
		
		return gh;
		
	}
	
	public int generateRandomNumber(int maxindex){
		int i=r.nextInt(maxindex);
		return i;
	}
	
	
	public static void main(String[] args) throws ClassNotFoundException, CloneNotSupportedException, IOException {
		// TODO Auto-generated method stub
		Set<String> words=new HashSet<String>();
		
		
		GenerateGragh gg=new GenerateGragh(5,10);
//����һ������Ϊ50����Ϊ100��ͼ
		Graph g=gg.generate();
		
		int a = GraphUtils.minvt(g);
		System.out.println("MIN-VT:"+a);
		g.printGragh();
		


		

		

	}


}
