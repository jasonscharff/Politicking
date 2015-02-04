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
	
	public Path(String person, int value, Path former)
	{
		previous = former.getPath();
		System.out.println("FORMER = " + previous);
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
		return getText();
	}
	
	


	
	

}
