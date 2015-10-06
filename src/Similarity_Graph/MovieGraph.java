package mp4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

//CREDITS TO THE SOURCE PROVIDER:-

//The methods involved in the implementation of Dijkstra's algorithm are taken from this link and changed to suit the structure of this code.

//( http://stackoverflow.com/questions/17480022/java-find-shortest-path-between-2-points-in-a-distance-weighted-map )

//I could not find an author name to credit here, however, the author's alias on stackoverflow.com is "luke". 



//This class represents an undirected graph 
//with movies as vertices.
//The edges are weighted.
//This graph is immutable 
//except for the addition of vertices and edges. 
//It should not be possible to change a vertex or an edge
//after it has been added to the graph.
//
//
//Abstraction Function:
//movie graph is listofmovies, such that 
//each movie is a vertex, and is the source of its edges
//each movie has a listofedges and an ID, where
//each edge in listofedges contains a target, and the value of weight associated with that edge
//(the list of edges size may be different for different movies
//because it is not necessary for all vertices to be connected directly to all other vertices)
//the ID is used as the unique hashcode value of the movie
//
//	
//
//Representation Invariant for MovieGraph:
//	MovieGraph != null
// 	movieList.size() == idToMovie.size()
//	movieList.contains(m)==idToMovie.containsKey(m.hashCode())==idToMovie.containsValue(m)
//	Movie != null
//	each key-value pair in idToMovie = has a corresponding Movie in movieList
//	movies that have been added to the map idToMovie must also have been added to the movieList
//	so that at any given time movieList.size() == idToMovie.size()
//	edges != null
//
//For each edge, requires:
//	m1, m2 != null
//  m1, m2 are movies with valid, unique ID's
//	m1 != m2 
//	weight >= 1

public class MovieGraph {
	
	private ArrayList<Movie> movieList;
	private Map<Integer, Movie> idToMovie;
	private ArrayList<Edge> listOfEdges;
	
	/**
	* this is the constructor for movie graph
	**/
	public MovieGraph(){
		this.movieList = new ArrayList<Movie>();
		this.idToMovie = new HashMap<Integer, Movie>();
		this.listOfEdges = new ArrayList<Edge>();
		
	}
	

	/**
	 * Add a new movie to the graph. If the movie already exists in the graph
	 * then this method will return false. Otherwise this method will add the
	 * movie to the graph and return true.
	 * 
	 * @param movie
	 *            the movie to add to the graph. Requires that movie != null.
	 * @return true if the movie was successfully added and false otherwise.
	 * @modifies this by adding the movie to the graph if the movie did not
	 *           exist in the graph.
	 */
	public boolean addVertex(Movie movie) {
		// TODO: Implement this method
		
		if (movieList.contains(movie))
			return false;		// movie already exists in graph
		if ( movie == null)
			return false;
		
		movieList.add(movie);
		
		idToMovie.put(movie.hashCode(), movie);
		// add movie if it did not exist in graph and return true
		
		return true;
	}

	/**
	 * Add a new edge to the graph. If the edge already exists in the graph then
	 * this method will return false. Otherwise this method will add the edge to
	 * the graph and return true.
	 * 
	 * @param movie1
	 *            one end of the edge being added. Requires that m1 != null.
	 * @param movie2
	 *            the other end of the edge being added. Requires that m2 !=
	 *            null. Also require that m1 is not equal to m2 because
	 *            self-loops are not meaningful in this graph.
	 * @param edgeWeight
	 *            the weight of the edge being added. Requires that edgeWeight >
	 *            0.
	 * @return true if the edge was successfully added and false otherwise.
	 * @modifies this by adding the edge to the graph if the edge did not exist
	 *           in the graph.
	 */
	public boolean addEdge(Movie movie1, Movie movie2, int edgeWeight) {
		// TODO: Implement this method
		
		if (movie1 == null || movie2 == null){
			return false;
		}
		if (movie1.equals(movie2))
			return false;
		if ( edgeWeight < 1)	// edge values are >=1
			return false;
		
		
		Edge edge1to2 = new Edge (movie2,edgeWeight);
		// create a new edge with edgeWeight as edge value
		// where movie1 is the source and movie2 is the target
		
		if (listOfEdges.isEmpty()){
			// if list if empty, simply add the edge 
			
			listOfEdges.add(edge1to2);
			movie1.addEdge(edge1to2);
			// add edge from source to target
			
			Edge edge2to1 = new Edge (movie1,edgeWeight);
			
			listOfEdges.add(edge2to1);
			movie2.addEdge(edge2to1);
			// add edge from target to source
		}
		else {
		// if list is not empty, 
		// first check if edge from source to target exists already
		
		for ( int i = 0; i < listOfEdges.size();i++){
			if(edge1to2.equals( listOfEdges.get(i) ) )
				return false;
				
		}
		
		// once you are sure edge don't already exist
		// add edges
		
		listOfEdges.add(edge1to2);
		movie1.addEdge(edge1to2);
		// edge added from source to target
		
		Edge edge2to1 = new Edge (movie1,edgeWeight);
		
		for ( int i = 0; i < listOfEdges.size();i++){
			if(edge2to1.equals( listOfEdges.get(i) ) )
				return false;
				// checks if edge exists from target to source
		}
		
		listOfEdges.add(edge2to1);
		movie2.addEdge(edge2to1);
		// add edge from target to source
		
		}
		
		return true;
	}

	/**
	 * Add a new edge to the graph. If the edge already exists in the graph then
	 * this method should return false. Otherwise this method should add the
	 * edge to the graph and return true.
	 * 
	 * @param movieId1
	 *            the movie id for one end of the edge being added. Requires
	 *            that m1 != null.
	 * @param movieId2
	 *            the movie id for the other end of the edge being added.
	 *            Requires that m2 != null. Also require that m1 is not equal to
	 *            m2 because self-loops are not meaningful in this graph.
	 * @param edgeWeight
	 *            the weight of the edge being added. Requires that edgeWeight >
	 *            0.
	 * @return true if the edge was successfully added and false otherwise.
	 * @modifies this by adding the edge to the graph if the edge did not exist
	 *           in the graph.
	 */
	public boolean addEdge(int movieId1, int movieId2, int edgeWeight) {
		// TODO: Implement this method
		
		if (!(idToMovie.containsKey(movieId1)) || !(idToMovie.containsKey(movieId2)) ){
			return false;
		}
		
		if ( movieId1 == movieId2)
			return false;
		
		if (edgeWeight < 1)
			return false;
		
		// get movies from their ids
		
		Movie movie1 = idToMovie.get(movieId1);
		Movie movie2 = idToMovie.get(movieId2);
		
		return addEdge(movie1,movie2, edgeWeight);
		// adds edge between movies
	}

	/**
	 * Return the length of the shortest path between two movies in the graph.
	 * Throws an exception if the movie ids do not represent valid vertices in
	 * the graph.
	 * 
	 * @param moviedId1
	 *            the id of the movie at one end of the path.
	 * @param moviedId2
	 *            the id of the movie at the other end of the path.
	 * @throws NoSuchMovieException
	 *             if one or both arguments are not vertices in the graph.
	 * @throws NoPathException
	 *             if there is no path between the two vertices in the graph.
	 * 
	 * @return the length of the shortest path between the two movies
	 *         represented by their movie ids.
	 */
	public int getShortestPathLength(int moviedId1, int moviedId2)
			throws NoSuchMovieException, NoPathException { 
		// TODO: Implement this method
		
		if ( !idToMovie.containsKey(moviedId1) || !idToMovie.containsKey(moviedId2) )
			throw new NoSuchMovieException();
		
		Movie movie1 = idToMovie.get(moviedId1);
		Movie movie2 = idToMovie.get(moviedId2);
		
		List<Movie> tempList = getShortestPath(movie1.hashCode(),movie2.hashCode());
		
		if ( tempList.isEmpty())
			throw new NoPathException();
		
		int length = movie2.getCost();
		
		return length;
	}
	
	
	/*
	 
	 IMPORTANT COMMENT:-
	 
	 The following method is taken online from the following link and changed to satisfy the requirements
	 
	 
	 ( http://stackoverflow.com/questions/17480022/java-find-shortest-path-between-2-points-in-a-distance-weighted-map )
	 
	 I do not fully understand the implementation but it is the same as the posted in the MP4 explanation
	 
	 */
	 
	public static void computePaths(Movie movie){
		// set cost to 0
		movie.setCost(0);
		
		PriorityQueue<Movie> movieQueue = new PriorityQueue<Movie>();
		
		movieQueue.add(movie);
		
		while(!movieQueue.isEmpty()){
			Movie u = movieQueue.poll();
			
			for (Edge edge : u.getList()){
				Movie v = edge.getTarget();
				int weight = edge.getWeight();
				int distanceUsingU = u.getCost() + weight;
				
				if (distanceUsingU < v.getCost()){
					movieQueue.remove(v);
					v.setCost(distanceUsingU);
					v.setPrivousMovie(u);
					movieQueue.add(v);
				}
			}
		}
		
	}

	/**
	 * Return the shortest path between two movies in the graph. Throws an
	 * exception if the movie ids do not represent valid vertices in the graph.
	 * 
	 * @param moviedId1
	 *            the id of the movie at one end of the path.
	 * @param moviedId2
	 *            the id of the movie at the other end of the path.
	 * @throws NoSuchMovieException
	 *             if one or both arguments are not vertices in the graph.
	 * @throws NoPathException
	 *             if there is no path between the two vertices in the graph.
	 * 
	 * @return the shortest path, as a list, between the two movies represented
	 *         by their movie ids. This path begins at the movie represented by
	 *         movieId1 and ends with the movie represented by movieId2.
	 */
	 
	 
	 

	 
	public List<Movie> getShortestPath(int movieId1, int movieId2)
			throws NoSuchMovieException, NoPathException {
		// TODO: Implement this method

		if ( !idToMovie.containsKey(movieId1) || !idToMovie.containsKey(movieId2) )
			throw new NoSuchMovieException();
		
		Movie movie1 = idToMovie.get(movieId1);
		Movie movie2 = idToMovie.get(movieId2);
		
		computePaths(movie1);
		
		List<Movie> bestPath = new ArrayList<Movie>();
		
		for (Movie movie = movie2; movie != null ; movie = movie.getPrevious()){
			bestPath.add(movie);	
		}
		
		Collections.reverse(bestPath);
		
		if(bestPath.isEmpty())
			throw new NoPathException();
		
		return bestPath;
		
		
		
	}

	/**
	 * Returns the movie id given the name of the movie. For movies that are not
	 * in English, the name contains the English transliteration original name
	 * and the English translation. A match is found if any one of the two
	 * variations is provided as input. Typically the name string has <English
	 * Translation> (<English Transliteration>) for movies that are not in
	 * English.
	 * 
	 * @param name
	 *            the movie name for the movie whose id is needed.
	 * @return the id for the movie corresponding to the name. If an exact match
	 *         is not found then return the id for the movie with the best match
	 *         in terms of translation/transliteration of the movie name.
	 * @throws NoSuchMovieException
	 *             if the name does not match any movie in the graph.
	 */
	public int getMovieId(String name) throws NoSuchMovieException {
		// TODO: Implement this method
		for( int i = 0; i < movieList.size(); i++ ){
			if( name.equals( movieList.get(i).getName() ) ){
				return movieList.get(i).hashCode();
			}
			else if( name.indexOf("(") != -1 ){
				String transliteration = name.substring( name.indexOf("("), name.indexOf(")") + 1 );
				if( name.equals(transliteration) ){
					return movieList.get(i).hashCode();
				}
				
				String english = name.substring(0, name.indexOf(" ("));
				if( name.equals( english ) ){
					return movieList.get(i).hashCode();
				}
			}
		}
		throw new NoSuchMovieException();
	}

	// Implement the next two methods for completeness of the MovieGraph ADT
	/**
	* movie graphs are equal is their listofedges, listofmovies, and listofmoviesbyid are equal
	**/
	@Override
	public boolean equals(Object obj) {
		
		return true;
	}

	@Override
	public int hashCode() {
		
		return 0;
	}

}
