package graph;
import graph.WeightedGraph;
import maze.Juncture;
import maze.Maze;

/** 
 * <P>The MazeGraph is an extension of WeightedGraph.  
 * The constructor converts a Maze into a graph.</P>
 */
public class MazeGraph extends WeightedGraph<Juncture> {

	/* STUDENTS:  SEE THE PROJECT DESCRIPTION FOR A MUCH
	 * MORE DETAILED EXPLANATION ABOUT HOW TO WRITE
	 * THIS CONSTRUCTOR
	 */

	/** 
	 * <P>Construct the MazeGraph using the "maze" contained
	 * in the parameter to specify the vertices (Junctures)
	 * and weighted edges.</P>
	 * 
	 * <P>The Maze is a rectangular grid of "junctures", each
	 * defined by its X and Y coordinates, using the usual
	 * convention of (0, 0) being the upper left corner.</P>
	 * 
	 * <P>Each juncture in the maze should be added as a
	 * vertex to this graph.</P>
	 * 
	 * <P>For every pair of adjacent junctures (A and B) which
	 * are not blocked by a wall, two edges should be added:  
	 * One from A to B, and another from B to A.  The weight
	 * to be used for these edges is provided by the Maze. 
	 * (The Maze methods getMazeWidth and getMazeHeight can
	 * be used to determine the number of Junctures in the
	 * maze. The Maze methods called "isWallAbove", "isWallToRight",
	 * etc. can be used to detect whether or not there
	 * is a wall between any two adjacent junctures.  The 
	 * Maze methods called "getWeightAbove", "getWeightToRight",
	 * etc. should be used to obtain the weights.)</P>
	 * 
	 * @param maze to be used as the source of information for
	 * adding vertices and edges to this MazeGraph.
	 */
	public MazeGraph(Maze maze) {
		super();
		for(int j = 0; j < maze.getMazeWidth(); j++){
			for(int i = 0; i < maze.getMazeHeight(); i++){
				Juncture junc = new Juncture(j,i);
				addVertex(junc);
			}
		}
		for(int i = 0; i < maze.getMazeWidth(); i++){
			for(int j = 0; j < maze.getMazeHeight(); j++){
				Juncture middle = new Juncture(i, j);
				Juncture top = new Juncture(i, j-1);
				Juncture bottom = new Juncture(i, j+1);
				Juncture left = new Juncture(i-1, j);
				Juncture right = new Juncture(i+1, j);

				if(!maze.isWallAbove(middle)){
					addEdge(middle,top,maze.getWeightAbove(middle));
					//addEdge(top,middle,maze.getWeightBelow(top));
				}
				if(!maze.isWallBelow(middle)){
					addEdge(middle,bottom,maze.getWeightBelow(middle));
					//	addEdge(bottom,middle,maze.getWeightBelow(middle));
				}
				if(!maze.isWallToLeft(middle)){
					addEdge(middle,left,maze.getWeightToLeft(middle));
					//	addEdge(left,middle,maze.getWeightToLeft(middle));
				}
				if(!maze.isWallToRight(middle)){
					addEdge(middle,right,maze.getWeightToRight(middle));
					//	addEdge(right,middle,maze.getWeightToRight(middle));
				}
			}
		}
	}
}
