package org.yourorghere;

public class Vertex {
    private static int id = 0;
    private float x, y, z;
    private String stringID;

    public Vertex(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        //this.stringID = "v" + id++;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", stringID='" + stringID + '\'' +
                '}';
    }
}
