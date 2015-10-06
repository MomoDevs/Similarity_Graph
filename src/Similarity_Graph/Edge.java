package mp4;

import java.util.Comparator;

public class Edge {

	private Movie target;
	private int weight;
	
	/**
	 * creates a new edge
	 * 
	 * @param vertex2 is the target
	 * @param weight is the value of the edge
	 */
	public Edge(Movie vertex1,int weight){
		
		this.target = vertex1;
		this.weight = weight;
			
	}
	/**
	 * gets the weight of the edge
	 *
	 * @return weight
	 */
	public int getWeight(){
		return weight;
		
	}
	
	/**
	 * gets the target of the edge
	 *
	 * @return target
	 */
	public Movie getTarget(){
		return target;
	}
	
	
	
}
