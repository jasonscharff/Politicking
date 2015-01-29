import java.util.*;


public class Path implements Comparable
{
	List<String> previous;
	int currentVal;
	
	
	public Path(String person, int value)
	{
		currentVal = value;
		previous = new ArrayList<String>();
		previous.add(person);
	}
	
	public Path(String person, int value, Path former)
	{
		previous = former.getPath();
		previous.add(person);
		currentVal = value + former.getValue();
	}
	
	
	public List<String> getPath()
	{
		return previous;
	}
	
	public void add(String person, int val)
	{
		previous.add(person);
		currentVal += val;
				
	}
	
	public int getValue()
	{
		return currentVal;
	}
	public String getText()
	{
		return previous.get(previous.size() - 1);
	}


	@Override
	public int compareTo(Object o) 
	{
		return this.currentVal - ((Path) o).currentVal;
	}


	
	

}
