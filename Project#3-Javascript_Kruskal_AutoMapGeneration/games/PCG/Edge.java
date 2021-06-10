import java.util.Random;

public class Edge implements Comparable<Edge>{
    public int to;
    public int from;
    public int weight = new Random().nextInt(14) + 1;

    public Edge(int to, int from){
        this.to = to;
        this.from = from;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.weight, o.weight);
    }

    @Override
    public boolean equals(Object o){
        if (o == this) return true;

        if (!(o instanceof Edge)) return false;

        Edge e = (Edge) o;
        return (e.to == this.to) && (e.from == this.from) && (e.weight == this.weight);
    }
}