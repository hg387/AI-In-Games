package s3.util;

public class Node implements Comparable<Node>{
    public Node parent;
    public double x;
    public double y;
    public double g;
    public double h;

    public Node(double x, double y){
        this.x = x;
        this.y = y;
    }

    // Manhattan distance
    public double heuristic(Node goal){
        return (Math.abs(this.x-goal.x) + Math.abs(this.y-goal.y) );
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Node)) return false;

        if (obj == this) return true;

        Node tmp = (Node) obj;

        return (tmp.x == this.x) && (tmp.y == this.y);
    }

    @Override
    public int hashCode() {
        return (int) (this.g + this.h);
    }

    @Override
    public int compareTo(Node tmp) {
        return Double.compare((this.g + this.h), (tmp.g + tmp.h));
    }
}
