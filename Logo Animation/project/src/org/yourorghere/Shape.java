package org.yourorghere;

import java.util.ArrayList;

public class Shape {

    private float x, y, z;
    private float minX, minY, maxX, maxY;
    protected float R, G, B;

    //private HashMap<String,Vertex> Vertices;
    ArrayList<Vertex> Vertices = new ArrayList<Vertex>();

    public void addVertex(Vertex v) {
        Vertices.add(v);

    }

    public void addVertex(int index, Vertex v) {
        Vertices.add(index, v);

    }

    public void deleteVertex(Vertex v) {
        Vertices.remove(v);
    }

    public int getVertexIndex(Vertex v) {
        return Vertices.indexOf(v);
    }

    public Vertex getVertex(int index) {
        return Vertices.get(index);
    }

    public Vertex findVertex(float x, float y, float z) {
        for (Vertex v : Vertices) {
            if (v.getX() == x && v.getY() == y && v.getZ() == z) {
                return v;
            }
        }
        return null;
    }

    public void setColor(float R, float G, float B) {
        this.R = R;
        this.G = G;
        this.B = B;
    }

}
