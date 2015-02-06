import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
/******************************************************************************
 * 
 * Name:		Jason Scharff
 * Block:		A
 * Date:		12/14/14
 * 
 *  Program #4: Shortest Path
 *  Description:
 *  	
 *      
 ******************************************************************************/

public class DijsktraMain 
{
	private final static String FILE_NAME = "values.txt";
	private final static String terminator =  "-1";



	public static void main(String[] args) 
	{
		ValueSignedGraph g = readFile();
		if(g != null)
		{
			callDijkstra(g);
		}
		
	}

	/**
	 * This method handles the main console input and the calling of the shortest path algorithm.
	 * It runs in a loop asking for the start and end point of the graph then prints out the results.
	 * To terminate the loop the user simply enters -1
	 * @param g	The graph to run the shortest path algorithim on. 
	 */
	public static void callDijkstra(ValueSignedGraph g)
	{
		Scanner in = new Scanner(System.in);
		System.out.println("At Any Point to Stop Please Enter -1");
		boolean shouldContinue = true;
		while(shouldContinue)
		{
			System.out.println("Please Type In the Origin:");
			String origin = in.nextLine();
			if(origin.equals(terminator))
			{
				shouldContinue = false;
			}
			else
			{
				System.out.println("Please Type In the Destination:");
				String destination = in.nextLine();
				if(destination.equals(terminator))
				{
					shouldContinue = false;
				}
				else
				{
					Path p = g.Dijkstra(origin, destination);	
					System.out.println(p);
				}
				
				
			}
			
		}
		System.out.println("Operation Completed");
	}
	
	/**
	 * This method handles the main file input. It reads the file (format described in README)
	 * then creates a ValueSignedGraph using the data (a custom class to handle the directed graph
	 * with specific weights) and returns that graph
	 * @return	A value signed graph representation of the data
	 */
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
