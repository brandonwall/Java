import java.util.*;

public class WeightedGraph<V> {

	
	private Collection<GraphAlgorithmObserver<V>> observerList;
	public HashMap<V,HashMap<V,Integer>> map;
	private int size;
	Set<V> visitedSet2 = new LinkedHashSet<V>();

	/** Initialize the data structures to "empty", including
	 * the collection of GraphAlgorithmObservers (observerList).
	 */
	public WeightedGraph() {
		observerList = new HashSet<GraphAlgorithmObserver<V>>();
		map = new HashMap<V, HashMap<V,Integer>>();
		size = 0;
	}

	/** Add a GraphAlgorithmObserver to the collection maintained
	 * by this graph (observerList).
	 * 
	 * @param observer
	 */
	public void addObserver(GraphAlgorithmObserver<V> observer) {
		observerList.add(observer);
	}

	/** Add a vertex to the graph.  If the vertex is already in the
	 * graph, throw an IllegalArgumentException.
	 * 
	 * @param vertex vertex to be added to the graph
	 * @throws IllegalArgumentException if the vertex is already in
	 * the graph
	 */
	public void addVertex(V vertex) {
		if(map.containsKey(vertex)){
			throw new IllegalArgumentException();
		}
		HashMap<V, Integer> temp = new HashMap<V, Integer>();
		map.put(vertex,temp);
		size++;
	}

	/** Searches for a given vertex.
	 * 
	 * @param vertex the vertex we are looking for
	 * @return true if the vertex is in the graph, false otherwise.
	 */
	public boolean containsVertex(V vertex) {
		return map.containsKey(vertex);
	}

	/** 
	 * <P>Add an edge from one vertex of the graph to another, with
	 * the weight specified.</P>
	 * 
	 * <P>The two vertices must already be present in the graph.</P>
	 * 
	 * <P>This method throws an IllegalArgumentExeption in three
	 * cases:</P>
	 * <P>1. The "from" vertex is not already in the graph.</P>
	 * <P>2. The "to" vertex is not already in the graph.</P>
	 * <P>3. The weight is less than 0.</P>
	 * 
	 * @param from the vertex the edge leads from
	 * @param to the vertex the edge leads to
	 * @param weight the (non-negative) weight of this edge
	 * @throws IllegalArgumentException when either vertex
	 * is not in the graph, or the weight is negative.
	 */
	public void addEdge(V from, V to, Integer weight) {
		if(map.containsKey(from) && map.containsKey(to) && weight >= 0){
			map.get(from).put(to, weight);	
		}else{
			throw new IllegalArgumentException();
		}
	}

	/** 
	 * <P>Returns weight of the edge connecting one vertex
	 * to another.  Returns null if the edge does not
	 * exist.</P>
	 * 
	 * <P>Throws an IllegalArgumentException if either
	 * of the vertices specified are not in the graph.</P>
	 * 
	 * @param from vertex where edge begins
	 * @param to vertex where edge terminates
	 * @return weight of the edge, or null if there is
	 * no edge connecting these vertices
	 * @throws IllegalArgumentException if either of
	 * the vertices specified are not in the graph.
	 */
	public Integer getWeight(V from, V to) {
		if(map.containsKey(from) && map.containsKey(to)){
			return map.get(from).get(to);
		}else{
			throw new IllegalArgumentException();
		}
	}

	/** 
	 * <P>This method will perform a Breadth-First-Search on the graph.
	 * The search will begin at the "start" vertex and conclude once
	 * the "end" vertex has been reached.</P>
	 * 
	 * <P>Before the search begins, this method will go through the
	 * collection of Observers, calling notifyBFSHasBegun on each
	 * one.</P>
	 * 
	 * <P>Just after a particular vertex is visited, this method will
	 * go through the collection of observers calling notifyVisit
	 * on each one (passing in the vertex being visited as the
	 * argument.)</P>
	 * 
	 * <P>After the "end" vertex has been visited, this method will
	 * go through the collection of observers calling 
	 * notifySearchIsOver on each one, after which the method 
	 * should terminate immediately, without processing further 
	 * vertices.</P> 
	 * 
	 * @param start vertex where search begins
	 * @param end the algorithm terminates just after this vertex
	 * is visited
	 */
	public void DoBFS(V start, V end) {
		for(GraphAlgorithmObserver<V> k:observerList){
			k.notifyBFSHasBegun();
		}
		List<V> discoveredSet = new ArrayList<V>();
		Set<V> visitedSet = new LinkedHashSet<V>();
		discoveredSet.add(start);
		while(discoveredSet != null){
			V curr = discoveredSet.remove(0);
			visitedSet.add(curr);
			for(GraphAlgorithmObserver<V> k:observerList){
				k.notifyVisit(curr);
			}
			if(map.get(curr).containsKey(end)){
				visitedSet.add(end);
				for(GraphAlgorithmObserver<V> k:observerList){
					k.notifySearchIsOver();
				}
				return ;
			}else{
				for(V key:map.get(curr).keySet()){
					if(!visitedSet.contains(key) && !discoveredSet.contains(key)){
						discoveredSet.add(key);
					}
				}
			}
		}
	}

	/** 
	 * <P>This method will perform a Depth-First-Search on the graph.
	 * The search will begin at the "start" vertex and conclude once
	 * the "end" vertex has been reached.</P>
	 * 
	 * <P>Before the search begins, this method will go through the
	 * collection of Observers, calling notifyDFSHasBegun on each
	 * one.</P>
	 * 
	 * <P>Just after a particular vertex is visited, this method will
	 * go through the collection of observers calling notifyVisit
	 * on each one (passing in the vertex being visited as the
	 * argument.)</P>
	 * 
	 * <P>After the "end" vertex has been visited, this method will
	 * go through the collection of observers calling 
	 * notifySearchIsOver on each one, after which the method 
	 * should terminate immediately, without visiting further 
	 * vertices.</P> 
	 * 
	 * @param start vertex where search begins
	 * @param end the algorithm terminates just after this vertex
	 * is visited
	 */

	public void DoDFS(V start, V end) {
		//List<V> discoveredSet = new ArrayList<V>();
		Set<V> visitedSet2 = new LinkedHashSet<V>();
		Stack<V> stack = new Stack<V>();
		stack.push(start);
		for(GraphAlgorithmObserver<V> k:observerList){
			k.notifyDFSHasBegun();
		}
		while(!stack.isEmpty()){
			V curr = stack.pop();
			if(!visitedSet2.contains(curr)){
				visitedSet2.add(curr);
				for(GraphAlgorithmObserver<V> k:observerList){
					k.notifyVisit(curr);
				}
			}
			if(map.get(curr).keySet().contains(end)){
				visitedSet2.add(end);
				for(GraphAlgorithmObserver<V> k:observerList){
					k.notifySearchIsOver();
				}
				return ;
			}else{
				for(V key: map.get(curr).keySet()){
					if(!visitedSet2.contains(key)){
						stack.push(key);
					}
				}
			}
		}
	}

	/** 
	 * <P>Perform Dijkstra's algorithm, beginning at the "start"
	 * vertex.</P>
	 * 
	 * <P>The algorithm DOES NOT terminate when the "end" vertex
	 * is reached.  It will continue until EVERY vertex in the
	 * graph has been added to the finished set.</P>
	 * 
	 * <P>Before the algorithm begins, this method goes through 
	 * the collection of Observers, calling notifyDijkstraHasBegun 
	 * on each Observer.</P>
	 * 
	 * <P>Each time a vertex is added to the "finished set", this 
	 * method goes through the collection of Observers, calling 
	 * notifyDijkstraVertexFinished on each one (passing the vertex
	 * that was just added to the finished set as the first argument,
	 * and the optimal "cost" of the path leading to that vertex as
	 * the second argument.)</P>
	 * 
	 * <P>After all of the vertices have been added to the finished
	 * set, the algorithm will calculate the "least cost" path
	 * of vertices leading from the starting vertex to the ending
	 * vertex.  Next, it will go through the collection 
	 * of observers, calling notifyDijkstraIsOver on each one, 
	 * passing in as the argument the "lowest cost" sequence of 
	 * vertices that leads from start to end (I.e. the first vertex
	 * in the list will be the "start" vertex, and the last vertex
	 * in the list will be the "end" vertex.)</P>
	 * 
	 * @param start vertex where algorithm will start
	 * @param end special vertex used as the end of the path 
	 * reported to observers via the notifyDijkstraIsOver method.
	 */
	public void DoDijsktra(V start, V end) {
		if(!map.containsKey(start) || !map.containsKey(end)){
			throw new IllegalArgumentException();
		}
		Set<V> finishedSet = new LinkedHashSet<V>();
		Map<V,Integer> costs = new HashMap<V,Integer>();
		Map<V,V> discoveredSet = new HashMap<V,V>();

		for(V key: map.keySet()){
			if(key.equals(start)){
				costs.put(key, 0);
				discoveredSet.put(key, null);
			}else{
				costs.put(key, -1);
				discoveredSet.put(key, null);
			}
		}
		for(GraphAlgorithmObserver<V> k: observerList){
			k.notifyDijkstraHasBegun();
		}
		while(finishedSet.size() != map.size()){
			int minimum = 0;
			V lowest = null;
			for(V key: costs.keySet()){
				if(finishedSet.contains(key) || costs.get(key) == -1){
					continue;
				}
				if(costs.get(key) <= minimum || minimum == 0){
					lowest = key;
					minimum = costs.get(key);
				}
			}
			finishedSet.add(lowest);
			for(GraphAlgorithmObserver<V> k: observerList){
				k.notifyDijkstraVertexFinished(lowest, minimum);
			}
			for(V key: map.get(lowest).keySet()){
				if(!finishedSet.contains(key)){
					if(costs.get(key) > minimum + map.get(lowest).get(key)
							|| costs.get(key) == -1){
						costs.put(key, minimum + map.get(lowest).get(key));
						discoveredSet.put(key, lowest);
					}
				}
			}
		}
		Stack<V> holder = new Stack<V>();
		List<V> finalSet = new ArrayList<V>();
		holder.push(end);
		V past = end;
		while(!past.equals(start)){
			V prev = discoveredSet.get(past);
			past = prev;
			holder.push(prev);
		}
		while(!holder.isEmpty()){
			finalSet.add(holder.pop());
		}
		for(GraphAlgorithmObserver<V> k: observerList){
			k.notifyDijkstraIsOver(finalSet);
		}
	}

}
