import java.util.*;


public class ValueSignedGraph 
{
	Map<String, Map<String, Integer>> adjacencyMatrix;
	Map<String, Integer> numConnecting;


	/**
	 * This is the constructor creating two new HashMaps. The first serves
	 * as an adjacency Matrix to keep track of all of the connections and their values.
	 * The second keeps track of the number of connections to each vertex which is used
	 * in the modified Dijkstra's algorithm.	
	 */
	public ValueSignedGraph()
	{
		adjacencyMatrix = new HashMap<String, Map<String, Integer>>();
		numConnecting = new HashMap<String, Integer>();
	}

	/**
	 * This method adds a vertex to the graph
	 * @param name	The text of the vertex to be added
	 */
	public void addVertex(String name)
	{
		if(adjacencyMatrix.get(name) == null)
		{
			Map<String, Integer> temp = new HashMap<String, Integer>();
			adjacencyMatrix.put(name,temp);
			numConnecting.put(name, 0);
		}
	}

	/**
	 * This method adds an edge to the graph of a certain value
	 * @param from	The vertex being connected
	 * @param to	The vertex that is connected to
	 * @param value	The value (weight) of the connection
	 */
	public void addEdge(String from, String to, int value)
	{
		Map<String, Integer> temp = adjacencyMatrix.get(from);
		temp.put(to, value);
		adjacencyMatrix.put(from, temp);
		numConnecting.put(to, numConnecting.get(to) + 1);
	}

	/**
	 * This method returns the weight of the edge connecting two vertices. Throws exception
	 * if there is no connection at all.
	 * @param a	Vertex with the connection from
	 * @param b	Vertex being connected to
	 * @return	Weight of the connection between param a and param b
	 */
	public int getEdgeValue(String a, String b)
	{
		Map<String, Integer> temp = adjacencyMatrix.get(a);
		if(temp.get(b) == null)
		{
			throw new IndexOutOfBoundsException("No connection present.");
		}
		else
		{
			return temp.get(b);
		}
	}

	/**
	 * Returns true or false depending on whether or not that vertex is currently in the graph
	 * @param name	The text of the vertex being checked
	 * @return	True if vertex exists, false if not.
	 */
	public boolean hasVertex(String name)
	{
		return adjacencyMatrix.containsKey(name);
	}

	/**
	 * Returns true or false depending on if there is an edge connection vertex a and b. Throws
	 * an exception if vertex a does not exist.
	 * @param a		The vetex connected from
	 * @param b		The vertex connected to
	 * @return		True if a connection is present between param a and param b, false if not
	 */
	public boolean hasEdge(String a, String b)
	{
		if(adjacencyMatrix.containsKey(a) == false)
		{
			throw new IndexOutOfBoundsException("Vertex does not exist");
		}

		return adjacencyMatrix.get(a).containsKey(b);
	}



	/**
	 * This method implements a slightly varied version of Dijsktra's algorithm to find the
	 * shortest distance between two vertices. The variation is that instead of replacing
	 * elements inside of the priority queue when a better route is found all paths are considered
	 * and the heavier one is eventually weeded out. The reason for this is that using Java priority
	 * queue elements in the queue cannot be modified and copying the data into a whole new priority queue
	 * seems highly inefficient. If I was to have had time to write my own however
	 * then it would have matched Dijkstra's almost perfectly. This method fails however
	 * if no path can be found and there is a cycle (as it just searches that cycle infinitely)
	 * so a safeguard was added that only allows the vertex to be added to the priority queue
	 * the same number of times as there are connections to it. To implement this algorithm
	 * the priority queue adds a custom class known as "Path" which keeps track of all of the past
	 * visited vertices. At the end whichever ends up being the shortest (in weight) is returned
	 * in the custom class form of a path which can then be printed.
	 * @param from	Starting vertex
	 * @param to	Ending vertex
	 * @return		A Path object containing the proper path or an empty path that will print out
	 * 				"No Path Found" if no path is found.
	 */
	public Path Dijkstra(String from, String to)
	{

		//Initialize map keeping counted of times a vertex has been added to the priority queue
		//with all zeroes.
		Map<String, Integer> numberAdded= new HashMap<String, Integer>();
		for(String key : adjacencyMatrix.keySet())
		{
			numberAdded.put(key, 0);
		}

		PriorityQueue<Path> queue = new PriorityQueue<Path>();
		Map<String, Integer> level = adjacencyMatrix.get(from);
		//If a vertex does not exist return the "no path found" symbol.
		if(level == null || adjacencyMatrix.get(to) == null)
		{
			return new Path(null);
		}
		//Add all edges from starting vertex to queue
		for (String x : level.keySet())
		{
			queue.add(new Path(x, level.get(x)));
		}
		//Keep track of currently visited path
		Path current;
		//Begin do while loop such that the condition of being found can be checked at the end
		do
		{
			current = queue.poll();
			//Check if current item is the destination
			if(current.getText().equals(to))
			{
				current.addOrigin(from);
				return current;
			}
			else
			{
				//Add all of its connection to the priority queue.
				String text = current.getText();
				Set<String> connections = adjacencyMatrix.get(text).keySet();
				for(String connection : connections)
				{
					if(numberAdded.get(connection) <= numConnecting.get(connection))
					{
						queue.add(new Path(connection, adjacencyMatrix.get(text).get(connection), current));
						numberAdded.put(connection, numberAdded.get(connection) + 1);
					}
				}
			}

		}

		while(current.getText().equals(to) == false && queue.isEmpty() == false);

		//If the algorithm ends due to an empty queue (no path found) return that messgae
		if(queue.isEmpty())
		{
			return new Path(null);
		}
		//Return the pathway found.
		else
		{
			current.addOrigin(from);
			return current;
		}


	}


}
