README:

Running Sample:https://drive.google.com/file/d/1K-38FgIYEpvtYs5X7uPuQvaZfSROJzVX/view?usp=sharing

Node Class Details:

For converting axis coordinates into the iterable entities in
collections used in the implementation of A\*, Node class is used which
exists under the directory (src/util/Node). Each node contains x and y
coordinates as variables, reference to parent node used in path
construction, g and h variables whose sum is used in nodes selection.
This class has a heuristic() method that takes the goal node and
calculates heuristics value using the Manhattan distance formula | (x2 -
x1) | + | (y2 - y1) |. This Node class also has equals() and hashcode()
overridden so that contains() method of lists could check equality of
nodes, two nodes are equal if their coordinates are same. Likewise, the
compareTo() method is overridden to compare two nodes, the comparison is
done based on the sum of g and h values of the nodes.

A\* Pathfinding Implementation Details:

As the class AStar is already given under the directory
(src/s3/ai/Astar), we just have to implement the computePath() method.
Before that, we need to declare some class variables to avoid
re-initialization of lists and other variables with every single
iteration. A nested array containing pairs of integers is also used to
simulate action movements of up, right, left, up, and down.

The closedList is just an ArrayList as we do not require to maintain
order among the nodes collected in this list. The openList is a priority
queue of nodes from the standard java library, priority is set using the
overridden compareTo() method of the Node class. The openList initially
just contains the start node as described in the A\* implementation
given in the lecture slides. Start and goal nodes variables are set
during the A\* constructor initialization. To use anyLevelCollision()
method, we also need the S3 type variable and S3physicalEntity variable.

Next, the Astar path finding algorithm follows the same code as shown in
the lecture slides starting with a while loop with removing nodes with
the lowest priority from the openList. Again, priority is re-calculated
based on the sum of g and h values of the nodes. Removed nodes are added
into closedList, and moves are simulated on the removed nodes
coordinated using the nested array.

With each move, we check whether the resulting nodes are already in the
closedList or openList. Also, the resulting nodes are checked for any
collision with other entities using the method isMovePossible present in
the AStar class. If any of these conditions are true, then that node
resulting from that move is not added in the openList. This
isMovePossible() method utilizes S3 class anyLevelCollision() method to
check collision alongside the resulting node is still inside the map of
the game. It is because of this isMovePossible() method that moves
resulting over water and trees are not avoided in the pathfinding.

As new nodes are added to the openList with their g and h values, queue
gets heapify again to have the lowest priority node ready for the next
iteration. This process is looped until goal is reached.

Once the goal is found, path reconstruction starts through backtracking.
Using the parent reference of the goal node, path is reconstructed by
iteratively translating the nodes into Pair of coordinates and adding
them serially to the output list. On completion, this output list is
returned from the computePath() method of the Astar class. No changes in
the pathDistance() methd is required as it uses the computePath() method
to get the list and return the size of output list as the path distance.
WUnit entity using this class and computePath() method move to the goal
location with all the benefits of Astar pathfinding.
