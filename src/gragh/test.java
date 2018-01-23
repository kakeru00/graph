package gragh;

import java.io.IOException;

public class test {

	public static void main(String[] args) throws CloneNotSupportedException, ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		GenerateGragh gg=new GenerateGragh(50,50);//创建一个具有5个顶点、10条边的图
		Graph g=gg.generate();
		//g.printGragh();
		int a = GraphUtils.minvt(g);
		System.out.println();
		System.out.println("MIN-VT:"+a);

	}

}
