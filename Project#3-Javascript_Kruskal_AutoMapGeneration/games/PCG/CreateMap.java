import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.*;

public class CreateMap {
    public int width = 33;
    public int height = 24;
    public int verticesCount;
    public int[] map;
    public ArrayList<Edge> edges = new ArrayList<>();

    // a subset is vertex -> [parent, rank]
    public HashMap<Integer, ArrayList<Integer>> subsets = new HashMap<>();

    // 67 -> wall, 31 -> grass, 45 -> tree, 22 -> plant, 43 -> houses, 47 -> rock, 41 -> soil
    public int[] objects = {67, 31, 45, 46, 22, 43, 47, 41};

    public CreateMap(){
        this.map = new int[this.width*this.height];
        this.verticesCount = (this.width-2)*(this.height-2);
    }

    public int find(int i){
        if (this.subsets.get(i).get(0) != i){
            this.subsets.get(i).set(0, this.find(this.subsets.get(i).get(0)));
        }

        return this.subsets.get(i).get(0);
    }

    public void union(int to, int from){
        int toParent = this.find(to);
        int fromParent = this.find(from);

        if (this.subsets.get(fromParent).get(1) < this.subsets.get(toParent).get(1))
            this.subsets.get(fromParent).set(0, toParent);
        else if (this.subsets.get(fromParent).get(1) > this.subsets.get(toParent).get(1))
            this.subsets.get(toParent).set(0, fromParent);
        else{
            this.subsets.get(fromParent).set(0, toParent);
            this.subsets.get(toParent).set(1, (this.subsets.get(toParent).get(1)+1));
        }
    }

    public void makeEdges(){
        for (int j = 1; j<=(height-3);j+=2){
            for (int i=1; i<=(width-2); i+=2) {
                if (i != (width-2)) {
                    Edge edgeToRight = new Edge(((33 * j) + i), ((33 * j) + i + 2));
                    this.edges.add(edgeToRight);

                    this.map[edgeToRight.to] = this.objects[1];
                    this.map[edgeToRight.from] = this.objects[1];
                }

                if (j != (height-3)) {
                    Edge edgeToDown = new Edge(((33*j) + i), ((33*(j+2)) + i));
                    this.edges.add(edgeToDown);

                    this.map[edgeToDown.to] = this.objects[1];
                    this.map[edgeToDown.from] = this.objects[1];
                }
            }
        }
    }

    public void randomizeObjects(){
        for (int j = 1; j<=(height-2);j++){
            for (int i=1; i<=(width-2); i++){

                if (this.map[((33*j)+i)] != objects[1]){
                    int prob = new Random().nextInt(7);

                    if (prob > 1) this.map[((33*j)+i)] = this.objects[prob];
                }
            }
        }
    }

    public void makeSubsets(){
        for (int j = 1; j<=(height-2);j++){
            for (int i=1; i<=(width-2); i++){
                ArrayList<Integer> tmp = new ArrayList<>();
                tmp.add(((33*j)+i));
                tmp.add(0);

                this.subsets.put(((33*j)+i), tmp);
            }
        }
    }
    
    public ArrayList<Edge> performKruskalAlgorithm(){
        ArrayList<Edge> outputTree = new ArrayList<>();

        this.makeSubsets();

        this.makeEdges();
        Collections.sort(this.edges);

        for (Edge e: this.edges){
            if (this.find(e.to) != this.find(e.from)){
                outputTree.add(e);
                this.union(e.to, e.from);
            }
        }

        return outputTree;
    }

    public void initializeMap(){

        Arrays.fill(map, objects[3]);

        for (int j = 1; j<=(height-2);j++){
            for (int i=1; i<=(width-2); i++){
                if (new Random().nextInt() >= 0)
                    this.map[((33*j)+i)] = this.objects[2];
                else
                    this.map[((33*j)+i)] = this.objects[4];
            }
        }

        ArrayList<Edge> spanningTree = this.performKruskalAlgorithm();

        for (Edge e: spanningTree){
            int to = e.to;
            int from = e.from;

            if (from - to <= 3) this.map[to+1] = this.objects[1];
            else this.map[to+33] = this.objects[1];
        }

        this.randomizeObjects();

        int initialX = spanningTree.get(0).to % 33;
        int initialY = spanningTree.get(0).from / 33;

        MapWriter.writeMap(map);
        XMLWriter.writeXML(initialX, initialY);
    }

    public static void main(String[] args){
        CreateMap createMap = new CreateMap();
        createMap.initializeMap();
    }
}
