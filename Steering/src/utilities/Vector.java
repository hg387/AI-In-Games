package utilities;

public class Vector {

    public double x;
    public double y;
    public double z;

    public Vector(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double magnitude(){
        return Math.sqrt((Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2)));
    }

    public double dot(Vector v){
        return (this.x * v.x) + (this.y * v.y) + (this.z * v.z);
    }

    public Vector add(Vector v){
        return new Vector((this.x + v.x), (this.y + v.y), (this.z + v.z));
    }

    public Vector subtract(Vector v){
        return new Vector((this.x - v.x), (this.y - v.y), (this.z - v.z));
    }

    public double getAngle(){
        return Math.atan(this.z/this.x);
    }

    public Vector unitVector(){
        double m = this.magnitude();
        return new Vector((this.x/m), (this.y/m), (this.z/m));
    }

    public Vector multiply(double scalar){
        return new Vector((this.x * scalar), (this.y * scalar), (this.z * scalar));
    }

    public Vector divide(double scalar){
        return new Vector((this.x / scalar), (this.y / scalar), (this.z / scalar));
    }
}
