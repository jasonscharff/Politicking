#Dijkstra's

###Context
The purpose of Dijsktra's algorithim is to find the shortest path between two vertices in a graph. Each edge is assigned a "weight" and the shortest path is not by number of veritces travelled through, but rather the sum of all edge weights or the weight of the path. The shortest path algorithim was here assigned to the somewhat novel problem of asking people for favors in the arena of politics, informally known as "buttkissing". In essence for a low-level congressman to reach the President of the United States takes a multitude of steps that could require pulling lots of favors with some costing more than others. By entering in the data of how much it "costs" for each favor to be done the algorithim finds the cheapest way to indirectly ask the person you want a favor. In the program a sample file is provided with various names from Congress, Donald Trump, and the President of the United States.

###Tools
The program was written in Java version 6. No external libraries were used excluding those built into the JRE such as the Collections Interface. For example the priority queue was taken from the Java Collections interface, not built from scratch.

###Capabilities
This program is able to handle any graph so long as there are no negatives, just positive integers. Logically though this makes sense because asking someone for a favor never gives you weight but always costs you due to the effort required and longstanding memory of you getting something from a person which can harm you later in the political world. 

###File Format
All data for the graph is read off of the file then parsed to create a graph where the Dijstrka's algorithm can be run. The graph follows the following format.
```
Vertex A : Vertex B : Value X
Vertex C : Vertex D : Value Y
etc.
```
In this example vertex A connects to vertex B with a weight of X and Vertex C connects to Vertex D with a value of Y. 
Any data vertex mentioned is automatically added and the proper connections are filled in. An example file is provided under the name _"values.txt"_.
<br/>**Please Note That the File Does not contain the origin and destination
for the algorithm**


###Algorithm
The algorithm followed a slightly modified form of Edsger Dijkstra's famous shortest path algorithim. The algorithim in essence works by starting with the origin node than pulling all of its connections onto a min-priority queue (usually implemented through a min heap for maximum efficiency). From there each vetex is marked as visited and the algorithim continues traversing through the graph by popping the lowest weight off of the priority queue. If a shorter path to a particular vertex is found it is replaced with the newer path. This is where my implemenation differs from that of Dijkstra's. Because using the java priority queue (part of the collections interface) you cannot change something already in the priority queue a slight modification is made. Nothing is ever marked as visited, but rather the shortest route to each place is eventually explored if necessary. At the end the inefficient routes end up being weeded out as another route has a higher priority (a lower value) so it is explored. The algorithm stops when the "cheapest" vertex in the queue is the destination (as  nothing else could then possibly be cheaper) so it still works. However, if there is no route from vertex A to vertex B then eventually this algorithim would fail if there is a cycle in the route from A to B. This is because no matter what that cycle would end up being the "cheapest" so it would cycle through that forever. To solve this issue, a tracker is kept of the number of times each vertex is added to the priority queue. The number of times added cannot exceed the total of connections (or that is just impractical) so the cycle would stop and the priority queue would eventually empty out. If the priority queue empties out then no path is found. The advantage to this is that instead of having to copy in each item into a new priority queue when something needs to be changed (an O(N) operation) you just need to add the extra O(1) operation of keeping track of everything. This also, however, does reduce efficiency in that some vertices could be extraneously taversed, but without an in depth study it would be difficult to ascertain which is more efficient overall assuming the O(N) required copying time. Overall though this algorithm appears to work from the testing completed

###Classes and Interfaces
This program has three classes.

#####Main
The main class handles mostly file input and the creation of the ValueSignedGraph described below. The first step of the class is to read in the file using the format described above. A graph is then created from that dataset. Then the user is prompted to enter in the origin and destination. The Dijsktra's algorithm is then run on that data with the entered start and end and the full pathway as well as the total weight is printed. The user is then once again prompted for another origin and destination so they can perform anotehr shortest path finder on the dataset. At any point the user can enter -1 to stop the program. 

#####ValueSignedGraph
This class handles the bulk of the work for the program. It essentially serves two main tasks: actually storing the graph and running the variation of the Dijstrka's algorithm. The actual graph is stored as an adjacency matrix of each connection. This is implemented through a HashMap of HashMaps. Essentially, the key to the first HashMap is the vertex which then returns another HashMap of all of its connections. For example to check if vertex B connects to A and get the value the call would look like this:
<br/>
<br/>
adjacencyMatrix.get(A).get(B)
<br/>
<br/>
If that was to return an Integer then there would be a connection with the Integer representing its value. If it was to return null then no connection would be present.
In addition to storing just the graph it stores the number of connections to each vertex which is used in the modified version of Dijkstra's described above.
<br/>
<br/>
The other major component of the class is to handle the implementation of Dijkstra's. This algorithm is described above in the section labeld "Algorithm"

#####Path
The last class used in this program is aptly named Path. In the priority queue for the Dijkstra's implementation every vertex is stored as a Path object and thus this class implements Comparable. Essentially because Dijkstra's is finding a path between two vertices the entire path to a vertex needs to be stored and this is done through this class. The Path class has three data fields: A LinkedList (a LinkedList is used over an ArrayList because things are added to the beginning frequently and direct access is not needed), the value or weight of the current path, and the origin (usually left null). The LinkedList is probably the most simple: it just holds a record of everything in order (index zero of the LinkedList is the current thing). The value is just as simple storing the sum of all edge weights in the path. The origin field is normally null as to not store extraneous information, but at the end it should print the origin in addition to the destination so right before being printed it is added. If that field is absent it just exlcudes it in the toString.

##### &copy; Jason Scharff, 2015.
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:
