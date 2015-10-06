package mp4;

import java.util.ArrayList;
import java.util.Comparator;

public class Movie implements Comparable<Movie> {
	private final int id;
	private final String name;
	private final int releaseYear;
	private final String imdbUrl;
	private ArrayList<Edge> listOfEdges; 
	private int cost = Integer.MAX_VALUE;
	private Movie previous;
	/**
	 * Create a new Movie object with the given information.
	 * 
	 * @param id
	 *            the movie id
	 * @param name
	 *            the name of the movie
	 * @param releaseYear
	 *            the year of the movie's release
	 * @param imdbUrl
	 *            the movie's IMDb URL
	 */
	public Movie(int id, String name, int releaseYear, String imdbUrl) {
		this.id = id;
		this.name = name;
		this.releaseYear = releaseYear;
		this.imdbUrl = imdbUrl;
		this.listOfEdges = new ArrayList<Edge>();
	}
	
	/**
	 * sets the previous movie
	 * 
	 * @param previous movie
	 */
	public void setPrivousMovie(Movie previous){
		this.previous = previous;
	}
	
	/**
	 * Return the previous movie
	 * 
	 */
	public Movie getPrevious(){
		return previous;
	}

	/**
	 * sets the cost of movie
	 * 
	 * @param  new cost of movie
	 */
	public void setCost(int newCost){
		this.cost = newCost;
	}
	
	/**
	 * Return the cost of the movie
	 * 
	 */
	public int getCost(){
		return cost;
	}
	
	/**
	 * add an edge to the movie
	 * 
	 * @param edge
	 */
	public void addEdge(Edge edge){
		listOfEdges.add(edge);
	}
	
	/**
	 * gets the list of edges from movie
	 * 
	 */
	public ArrayList<Edge> getList(){
		return listOfEdges;
	}
	
	/**
	 * Return the name of the movie
	 * 
	 * @return movie name
	 */
	public String getName() {
		return name;
	}
	

	/**
	* movies are compared by cost
	**/
	@Override
	public int compareTo(Movie other){
		
		return Double.compare(cost, other.cost);
		
	}
	
	/**
	* hashcode is the movie's ID
	**/
	@Override
	public int hashCode() {

		return id;
	}
	
	/**
	* two movies are equal if and only if their ID is equal
	* @param other: movie object to be checked for equality with this movie object
	**/
	@Override
	public boolean equals(Object other) {
		if( ! (other instanceof Movie) ){
			return false;
		}
		if( id != ( (Movie) other).id ){
			return false;
		}
		if( !name.equals(( (Movie) other).name ) ){
			return false;
		}
		if( releaseYear != ( (Movie) other).releaseYear ){
			return false;
		}
		if( !imdbUrl.equals( ( (Movie) other).imdbUrl )){
			return false;
		}
		return true;
	}
	
}
