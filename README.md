# Uni-Ariel-OOP-Ex2

### About the project
In this assignment we were given a json file and had using it, to implement a Directed weighted graph using the given Interfaces.<br/>
In Addition we were given a list of algorithms that we had to implement and then showcase all of it using GUI

### How to run
Find The Jar called Ex2.jar
open terminal, and type the following: java -jar Ex2.jar argument
Note that the argument should be replaced with the file name like "G1.json" which is located at data folder.
**IF THE FILE IS NOT LOCATED INSIDE THE DATA FOLDER THE JAR WILL NOT FIND IT.**

### Our Idea
We implmented the directive weighted graph using 2 hashmaps,<br/>
the first is a hashmaps of all the nodes in the graph,<br/>
the second is a hashmaps of all the edges in the graph.<br/>
<br/The nodes were implemented using id's and a hashmap of all the edges going out from this node<br/>

__Algorithms__<br/>

E - the number of edges<br/>
V - the number of vertexes


_isConnected_ - O(|V| + |E|)<br/>
we Run the the DFS algorithem on the graph from the same node twice.<br/>If in the DFS the first and last node we visited was the same node,
we would build the reverse graph(make all edges point in the other direction) and Run DFS again and if then the first and last node is still the same, we know the graph is Connected.<br> Why? Because we can reach every node from the given node and every node can reach the given node so that means the whole graph is connected. 

_shortestPathDist_ - O(|E|log|V|)<br/>
we are given two nodes id's of the source and the destination, we then run Dijstra using those two id's and then return the distance.

_shortestPath_ - O(|E|log|V|)<br/>
we are given two nodes id's of the source and the destination, we then run Dijstra using those two id's and then return the path to get from the source to the destination.

_center_ - O(|V||E|log|V|)<br/>
The cetnter is the node which minimizes the max distance to all the other nodes.<br/>
First we check if the graph is even connected else there won't be a center at all.<br>
If the graph is connected we run Dijkstra from every node we return the node minimizes the max distance to all the other nodes.

_tsp_ - O(n^2*|E|log|V|)<br/>
traveling salesman problem(almost), we get a list of cities(id's of nodes) and have to return a path that passes through all cities(not the shortest just a path).<br/>
We do this using a greedy algorithem, we start from the first city, the next city is the closest unvisited city to it, which we find with Dijkstra, and we contuine like this until we went over all city's. After which we return the path we went.


### Files 

| given Interfaces(api)| Descripition |
| ---------- | --------- |
| DirectedWeightedGraph | interface for a Directed Weighted Graph |
| DirectedWeightedGraphAlgorithms  |  an Interface with the algorithms listed before |
| EdgeData | an Interface for the edges in the graph   |
| GeoLocation | an Interface for the coordinates of the node in the real world, <x,y,z>   |
| NodeData | an Interface for the nodes in the graph    |


| Classes| Descripition |
| ---------- | --------- |
| DWG | Implements the DirectedWeightedGraph interface |
| NodeData | a Node in the graph(DWG) , Implements the NodeData interface  |
| EdgeData | a Edge in the graph(DWG) , Implements the EdgeData interface   |
| GeoLoc | the coordinates of the a nodes,  Implements the GeoLocation interface |
| dwgFromJson| An auxiliary class to create graph(DWG) given a json file   |
| dwgToJson|  An auxiliary class to create a new json file given a graph(DWG)  |
| primitiveNodeData|  An auxiliary class used by dwgToJson  |
| primitiveEdgeData|  An auxiliary class used by dwgToJson  |

| Algorithms| Descripition |
| ---------- | --------- |
| DWG_algo | hold all the listed algorithems and the way to load and save a json file of a graph,</br> Implements the DirectedWeightedGraphAlgorithms interface |
| Dijkstra  | Implements the Dijjstra Algorithm  |
| DFS |  Implements the DFS Algorithm  |
| MinHeap |  Impletments a min heap  |

| GUI| Descripition |
| ---------- | --------- |
| GraphJFrame | JFrame which contains the lightweight component which holds the graph  |
| GraphPanel | JPanel which contains all the user program interfaces and graphics for the Graph itself |
| MenuJFrame | A menu JFrame which contains the lightweight component which holds the menu interface |
| MenuPanel | A JPanel which contains comboBox interface that allows the user to select a file and load a file from the data folder using a Jbutton |
| Arrow2D | A Shape class which extends 2DPath, it represents a directed edge ( --> ) |
| Constants | A class containing static final contants which each UI is built by |
| Run Gui | A class which is a control center. Each operation to switch frames or change ranges of the canvas goes through "Run Gui"  |

Test exists but we wont shown them here

### UML
![alt text](https://github.com/Inon-Sinn/Uni-Ariel-OOP-Ex2/blob/master/UML.png)

### GUI
Graphical User Interface which represents the project done by visual detail and allows the user to 
interact with the graph, play with the algorithms. The GUI allowed us a better tool, which was used to test the algorithms more efficiently.
![alt text](https://github.com/Inon-Sinn/Uni-Ariel-OOP-Ex2/blob/master/Gui.PNG)

### Results

| NodeSize| Construction |isConnected|shortestPath|shortestPathDist|center|tsp: 5 nodes|
| ---------- | --------- |--------- | --------- |--------- | --------- |--------- |
| G1 |131 ms|0.42 ms|0.245 ms|0.245 ms|5.57 ms|7 ms|
| G2 |21 ms|0.31 ms|0.155 ms|0.155 ms|11.75 ms|4.25 ms|
| G3 |56 ms|0.435 ms|0.435 ms|0.435 ms|10.5 ms|20.5 ms|
| 1,000 |522 ms| 11.25 ms|8.53 ms|8.53 ms|6s|176 ms|
| 10,000 |684 ms|178.5 ms|337.4 ms|337.4 ms|aprox: 56.23 min|3.6 s|
| 100,000 |13 s|8.286 s|45 s|45 s|approx 52 days|80 min|


Written by: Inon Sinn, yan naigebaver, yaron Syrota




