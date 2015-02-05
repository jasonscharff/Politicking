import java.util.*;


public class ValueSignedGraph 
{
	Map<String, Map<String, Integer>> adjacencyMatrix;
	Map<String, Integer> numConnecting;

	
	
	public ValueSignedGraph()
	{
		adjacencyMatrix = new HashMap<String, Map<String, Integer>>();
		numConnecting = new HashMap<String, Integer>();
	}

	public void addVertex(String name)
	{
		if(adjacencyMatrix.get(name) == null)
		{
			Map<String, Integer> temp = new HashMap<String, Integer>();
			adjacencyMatrix.put(name,temp);
			numConnecting.put(name, 0);
		}
	}

	public void addEdge(String from, String to, int value)
	{
		Map<String, Integer> temp = adjacencyMatrix.get(from);
		temp.put(to, value);
		adjacencyMatrix.put(from, temp);
		numConnecting.put(to, numConnecting.get(to) + 1);
	}

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

	public boolean hasVertex(String name)
	{
		return adjacencyMatrix.containsKey(name);
	}

	public boolean hasEdge(String a, String b)
	{
		if(adjacencyMatrix.containsKey(a) == false)
		{
			throw new IndexOutOfBoundsException("Vertex does not exist");
		}

		return adjacencyMatrix.get(a).containsKey(b);
	}

	public Set<Connection> getConnections(String from)
	{
		Set<Connection> connections = new HashSet<Connection>();
		Map<String, Integer> values = adjacencyMatrix.get(from);
		for(String name : values.keySet())
		{
			Connection c = new Connection(name, values.get(name));
			connections.add(c);
		}

		return connections;

	}

	

	public Path Dijkstra(String from, String to)
	{
		
		Map<String, Integer> numberAdded= new HashMap<String, Integer>();
		for(String key : adjacencyMatrix.keySet())
		{
			numberAdded.put(key, 0);
		}
		
		PriorityQueue<Path> queue = new PriorityQueue<Path>();
		Map<String, Integer> level = adjacencyMatrix.get(from);
		if(level == null || adjacencyMatrix.get(to) == null)
		{
			return new Path(null);
		}
		for (String x : level.keySet())
		{
			queue.add(new Path(x, level.get(x)));
		}
		
		Path current;
		do
		{
			current = queue.poll();
			if(current.getText().equals(to))
			{
				return current;
			}
			else
			{
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

		if(queue.isEmpty())
		{
			return new Path(null);
		}
		else
		{
			System.out.println(current.getPath());
			return current;
		}


	}


}
