README:

To generate the map, run the main method of CreateMap class which exists
under the “games/PCG/” directory. This generates a new map in the
example game called “PCGMap.tmx”. Also, this program edits the
example.xml of the example game to change the starting coordinates of
the character according to the method used in the map generation. The
generated map consists of elements of the outdoor environment making it
an outdoor map.

For this assignment, I have started creating a map with two layers. The
first basic layer is just full of grass. The second layer is the
interesting one. For the second layer, boundaries are covered by pine
trees. All the other tiles are first covered with the other trees. With
this set, we have to create edges with weights to perform Kruskal’s
algorithm. I have used JAVA to generate the map. All the relevant
classes used for this assignment are under the directory “games/PCG/”.

As Kruskal’s algorithm forms a minimum spanning tree, we need a gap
between two edges otherwise every tile would be covered without any
obstacles. To create this kind of edge, the program purposefully leaves
the gap tile to be unchanged (set to be a tree) and sets the adjacent
tiles to the gap tile as grass so that the main character can walk on
it. For more specifics, the makeEdges() method is used for the creation
of edges.

Next, to apply Kruskal’s algorithm, first subsets are created which is
just a list of lists where the first index of each list is the parent
tile, and the second index represents the rank of the tile. I have
specifically used the Path compression and rank method to maintain the
time complexity of Kruskal’s algorithm to be O(nlogn) where n = number
of tiles (vertices). The find and union method is also used which is
present in the CreateMap class under the directory “PCG/CreateMap”. The
rest of Kruskal’s algorithm code is taken from the lecture slides.

Once the program has the output tree, it uses the randomizeObjects()
method to randomly put obstacles on the map to make the game more
interesting. With this final array of the map, starting coordinates are
calculated using the first edge of the output tree. These three
parameters are passed in the class MapWriter which exists under the
“PCG/” directory. This class writes the new map as “PCGMap.tmx” in the
correct map format for the example game.

Once the map is written in the example game directory, then example.xml
is edited out by the class XMLWriter method writeXML() to include the
appropriate starting coordinates generated from Kruskal’s algorithm. To
not add any redundant dependencies to edit XML, program edits tmx and
XML files as plain text and change/produce them accordingly. With both
the map and XML set, the program’s work is done and we can start playing
the game.
