
public class Connection implements Comparable{

	String connectedTo;
	int value;
	
	
	public Connection(String name, int value)
	{
		connectedTo = name;
		this.value = value;
	}
	
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int hashCode()
	{
		return connectedTo.hashCode();
	}
}
