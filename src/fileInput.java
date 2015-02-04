import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class fileInput 
{
	private final static String FILE_NAME = "values.txt";



	public static void main(String[] args) 
	{
		ValueSignedGraph g = readFile();
		Path p = g.Dijkstra("Kevin McCarthy", "Joe Biden");
		List<String> theOrder = p.getPath();
		Collections.reverse(theOrder);
		System.out.println("ORDER" + theOrder);
	}

	public static ValueSignedGraph readFile()
	{
		ValueSignedGraph graph = new ValueSignedGraph();
		try 
		{
			Scanner s = new Scanner(new BufferedReader(new FileReader(FILE_NAME)));
			while(s.hasNext())
			{
				String line = s.nextLine();
				int breakPoint = line.indexOf(":");
				String from = line.substring(0, breakPoint - 1);
				line = line.substring(breakPoint + 2);
				breakPoint = line.indexOf(":");
				String to = line.substring(0, breakPoint - 1);
				String value = line.substring(breakPoint + 2);
				if(graph.hasVertex(from) == false)
				{
					graph.addVertex(from);
				}
				if(graph.hasVertex(to) == false)
				{
					graph.addVertex(to);
				}
				graph.addEdge(from, to, Integer.parseInt(value));
			}
			
		}
		
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			System.out.println("File is not present");
			return null;
		}


		return graph;
	}


}
