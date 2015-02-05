import java.util.*;


public class Path implements Comparable
{
	List<String> previous;
	int currentVal;
	
	
	public Path(String person, int value)
	{
		currentVal = value;
		previous = new LinkedList<String>();
		previous.add(person);
	}
	
	public Path (Object o)
	{
		if (o == null)
		{
			previous = null;
		}
	}
	
	public Path(String person, int value, Path former)
	{
		previous = former.getPath();
		previous.add(0, person);
		currentVal = value + former.getValue();
	}
	
	
	
	public List<String> getPath()
	{
		List<String>newPath = new LinkedList<String>();
		for(String c : previous)
		{
			newPath.add(c);
		}
		return newPath;
	}
	
	public void add(String person, int val)
	{
		previous.add(0, person);
		currentVal += val;
				
	}
	
	public int getValue()
	{
		return currentVal;
	}
	public String getText()
	{
		return previous.get(0);
	}


	@Override
	public int compareTo(Object o) 
	{
		return this.currentVal - ((Path) o).currentVal;
	}
	
	public String toString()
	{
		if(previous == null)
		{
			return "No Path Found";
		}
		String toReturn = "";
		int count = 1;
		for (int i = previous.size() - 1; i >= 0; i--)
		{
			
			toReturn += count + ". " + previous.get(i);
			toReturn += "\n";
			count++;
		}
		toReturn += "Value: " + currentVal;
		return toReturn;
//		return getText();
	}
	
	
	


	
	

}
