package gragh;

import java.io.IOException;
import java.util.List;

public class GraphUtils {
	
/*	MIN-VT(G)
	{
	  int MIN=0;
	  if G=empty, then return MIN;//若图为空，返回0
	  for any vertex v in G;//遍历图中每一个顶点
	    if degree(v)==0,
	       then G=G-v;//若某顶点v的度为0，则从图中移除该顶点。即移除单独分开的点。
	    else//若某顶点v的度不为0，
	        if MIN-VX(G-v)+1<MIN-VX(G-N[v])+|N(v)|,
	           then return MIN-VX(G-v)+1;
	        else return MIN-VX(G-N[v])+|N(v)|
	}*/
	//N-邻居
	
	public static int minvt(Graph g) throws CloneNotSupportedException, ClassNotFoundException, IOException{
		int min=0;
		if(g==null||g.isEmpty())
			return min;
		for(Vertex v : g.getVertexList()){
			int vertex = v.nameOfVertex();
			if(g.degreeOfVertexName(vertex)==0){
				g.deleteVertexByName(vertex);
			}else{
				Graph g2 = (Graph) g.deepClone();

				List<Vertex> neighbor = g.neighborOfVertexName(vertex);
				for (Vertex v2 : neighbor) {
					g2.deleteVertexByName(v2.nameOfVertex());
				}
				g.deleteVertexByName(vertex);
				if( (minvt(g)+1) < 
						(minvt(g2)+neighbor.size()))
					return minvt(g)+1;
				else return minvt(g2)+neighbor.size();
			}
		}
		return 0;
	}
}
