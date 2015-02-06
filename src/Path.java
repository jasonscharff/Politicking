import java.util.*;


public class Path implements Comparable
{
	List<String> previous;
	int currentVal;
	String starter;
	
	/**
	 * Constructor method for creating a brand new path. This
	 * is to be used when there is no previous record of people
	 * travelled through so it just creates a new path out of a single person
	 * with the starting value
	 * @param person
	 * @param value
	 */
	public Path(String person, int value)
	{
		currentVal = value;
		previous = new LinkedList<String>();
		previous.add(person);
		starter = null;
	}
	
	/**
	 * This constructor is to be used to when no path is found. To ensure consistency
	 * within the to string instead of just printing null it should say "No Path Found"
	 * so in the implementation of Dijsktra it creates a path when a null object
	 * that the toString in Path then realizes to print "No Path Found"
	 * @param o	An object that should be null
	 */
	public Path (Object o)
	{
		if (o == null)
		{
			previous = null;
		}
	}
	
	/**
	 * This constructor creates a Path out of an old path essentially adding the
	 * latest visit to the path. This is used to build upon paths in the Dijkstra's implementation
	 * @param person	The new person to add to the path
	 * @param value		The value of the current addition (from last visit to param person)
	 * @param former	The previous path that is being added to.
	 */
	public Path(String person, int value, Path former)
	{
		previous = former.getPath();
		previous.add(0, person);
		currentVal = value + former.getValue();
		starter = null;
	}
	
	/**
	 * To beautify the toString at the very end the origin is added to a separate parameter that allows
	 * it to be printed. However, to avoid holding all of this extraneous data and such that the normal printed
	 * data just displays the real path this is added separately and called right before the final return
	 * in the Dijkstra's implementation. 
	 * @param start	The origin of the path
	 */
	public void addOrigin (String start)
	{
		starter = start;
	}
	
	/**
	 * This returns the path as a list. Instead of just returning the path by itself it is copied
	 * into a new List to avoid the pointer conflicts that arise from passing it to create a Path based
	 * off of another (it would lead to many lists being changed at once as many of them stem from one
	 * original pathway).
	 * @return	The path as a list
	 */
	public List<String> getPath()
	{
		List<String>newPath = new LinkedList<String>();
		for(String c : previous)
		{
			newPath.add(c);
		}
		return newPath;
	}
	
	/**
	 * Adds a person to the front of the path
	 * @param person	Person to be added
	 * @param val	The value of the new connection
	 */
	public void add(String person, int val)
	{
		previous.add(0, person);
		currentVal += val;
				
	}
	
	/**
	 * Getter method for the current value of all of the connections in the path
	 * @return	Value of all connections in the path
	 */
	public int getValue()
	{
		return currentVal;
	}
	
	/**
	 * Returns the top of the path (the current vertex in the graph)
	 * @return	Last item added to path which is the current vertex to be visited.
	 */
	public String getText()
	{
		return previous.get(0);
	}


	@Override
	/**
	 * This method overides the compareTo in comparable by comparing two paths by value.
	 */
	public int compareTo(Object o) 
	{
		return this.currentVal - ((Path) o).currentVal;
	}
	
	
	/**
	 * This overides the toString method to print out the pathway in a beautiful manner.
	 * At the end when the path is found the toString is called to display the path and value.
	 * It prints it in order (starting with the origin if that value has been entered) then prints
	 * the value out at the end.
	 */
	public String toString()
	{
		if(previous == null)
		{
			return "No Path Found";
		}
		String toReturn = "";
		int count;
		if(starter != null)
		{
			count = 2;
			toReturn += "1. " + starter + "\n";
		}
		else
		{
			count = 1;
		}
		for (int i = previous.size() - 1; i >= 0; i--)
		{
			
			toReturn += count + ". " + previous.get(i);
			toReturn += "\n";
			count++;
		}
		toReturn += "Value: " + currentVal;
		return toReturn;
	}
	
	
	


	
	

}
