package mp4;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

//JUnit tests to verify that the implementation of the MovieGraph class is correct and functioning properly.

public class MovieGraphTest {
	
	
		static Movie movie1 = new Movie(1, "Movie1", 2000, "A");
		static Movie movie2 = new Movie(2, "Movie2", 2000, "B");
		static Movie movie3 = new Movie(3, "Movie3", 2000, "C");
		static Movie movie4 = new Movie(4, "Movie4", 2000, "D");
		static Movie movie5 = new Movie(5, "Movie5", 2000, "E");
		static Movie movie6 = new Movie(6, "Movie6", 2000, "F");
		
		static MovieGraph movieGraph = new MovieGraph();
		
		
	@Before
	public void setup() {
		
		movieGraph.addVertex(movie1);
		movieGraph.addVertex(movie2);
		movieGraph.addVertex(movie3);
		movieGraph.addVertex(movie4);
		movieGraph.addVertex(movie5);
		movieGraph.addVertex(movie6);
		// create a movie graph with 6 vertices
		
		movieGraph.addEdge(movie1, movie2, 4);
		movieGraph.addEdge(movie1, movie3, 5);
		movieGraph.addEdge(movie1, movie4, 6);
		movieGraph.addEdge(movie2, movie3, 2);
		movieGraph.addEdge(movie2, movie4, 1);
		movieGraph.addEdge(movie2, movie6, 7);
		movieGraph.addEdge(movie3, movie4, 2);
		movieGraph.addEdge(movie3, movie5, 4);
		movieGraph.addEdge(movie4, movie5, 1);
		movieGraph.addEdge(movie4, movie6, 2);
		movieGraph.addEdge(movie5, movie6, 3);
		// add edges between the vertices
		
		
	}
	
	@Test
	public void test() throws NoSuchMovieException, NoPathException{
		
		assertTrue(movieGraph.getShortestPathLength(1, 2) == 4);
		// there is only one path: 1-2 = 4
		System.out.println("Case 1");
		System.out.println("Distance: 4");
		System.out.println("Path: "+movieGraph.getShortestPath(1, 2));
		
		
		assertTrue(movieGraph.getShortestPathLength(1, 3) == 5);
		// path 1-2-3 = 6
		// path 1-3 = 5 --------> shortest path
		System.out.println("Case 2");
		System.out.println("Distance: 5");
		System.out.println(movieGraph.getShortestPath(1, 3));
		
		assertFalse(movieGraph.getShortestPath(1, 3).contains(movie2));
		// shortest path = movie1, movie3
		// movie 2 is not in the shortest path
		
		assertTrue(movieGraph.getShortestPathLength(1, 6) == 7);
		// path 1-2-6 = 11
		// path 1-2-4-6 = 7 -------> shortest path
		// path 1-2-4-5-6 = 9
		
		assertTrue(movieGraph.getShortestPath(1, 6).contains(movie1));
		assertTrue(movieGraph.getShortestPath(1, 6).contains(movie2));
		assertTrue(movieGraph.getShortestPath(1, 6).contains(movie4));
		assertTrue(movieGraph.getShortestPath(1, 6).contains(movie6));
		assertFalse(movieGraph.getShortestPath(1, 6).contains(movie3));
		assertFalse(movieGraph.getShortestPath(1, 6).contains(movie5));
		System.out.println("Case 3");
		System.out.println("Distance: 7");
		System.out.println(movieGraph.getShortestPath(1, 6));
		
	}

}