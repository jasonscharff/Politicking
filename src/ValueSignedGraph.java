import java.util.*;


public class ValueSignedGraph 
{
	Map<String, Map<String, Integer>> adjacencyMatrix;
	public ValueSignedGraph()
	{
		adjacencyMatrix = new HashMap<String, Map<String, Integer>>();
	}
	
	public void addVertex(String name)
	{
		if(adjacencyMatrix.get(name) == null)
		{
			Map<String, Integer> temp = new HashMap<String, Integer>();
			adjacencyMatrix.put(name,temp);
		}
	}
	
	public void addEdge(String from, String to, int value)
	{
		Map<String, Integer> temp = adjacencyMatrix.get(from);
		temp.put(to, value);
		adjacencyMatrix.put(from, temp);
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
		PriorityQueue<Path> queue = new PriorityQueue<Path>();
		Map<String, Integer> level = adjacencyMatrix.get(from);
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
				Set<String> connections = adjacencyMatrix.get(current.getText()).keySet();
				for (String connection : connections)
				{
					queue.add(new Path(connection, 
							adjacencyMatrix.get(current.getText()).get(connection), current));
				}
			}
			
		}
		while(current.getText().equals(to) == false && queue.isEmpty() == false);
		if(queue.isEmpty())
		{
			return null;
		}
		else
		{
			return current;
		}
		
		
	}
	
	
}
