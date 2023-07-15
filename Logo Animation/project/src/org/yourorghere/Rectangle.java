package org.yourorghere;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;


public class Rectangle extends Shape {

    public Rectangle(Vertex v1, Vertex v2, Vertex v3, Vertex v4) {
        this.setColor(1, 1, 1); //default color white
        this.addVertex(v1);
        this.addVertex(v2);
        this.addVertex(v3);
        this.addVertex(v4);

    }

    public void draw(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glColor3f(R, G, B);
        gl.glBegin(gl.GL_POLYGON);
        float x, y, z;
        for (int i = Vertices.size() - 1; i >= 0; i--) {
            Vertex v = Vertices.get(i);
            x = v.getX();
            y = v.getY();
            z = v.getZ();
            gl.glVertex3f(x, y, z);
        }
        gl.glEnd();
    }

    /**
     * Increasing radius r makes the circle larger.
     *
     * @param r1: radius of first edge (top right)
     * @param r2: second edge (bottom right)
     * @param r3: (bottom left)
     * @param r4: (top left)
     */
    public void smoothEdges(double r1, double r2, double r3, double r4) {
        Vertex v0 = Vertices.get(0);
        Vertex v1 = Vertices.get(1);
        Vertex v2 = Vertices.get(2);
        Vertex v3 = Vertices.get(3);

        this.smoothEdge(r1, v0, v1, v2);
        this.smoothEdge(r2, v1, v2, v3);
        this.smoothEdge(r3, v2, v3, v0);
        this.smoothEdge(r4, v3, v0, v1);
    }

    /**
     *
     * @param v1
     * @param v2 the corner vertex between v1 and v3.
     * @param v3
     */
    private void smoothEdge(double r, Vertex v1, Vertex v2, Vertex v3) {
        float pi = (float) Math.PI;
        float x1 = v1.getX();
        float y1 = v1.getY();

        float x2 = v2.getX();
        float y2 = v2.getY();

        float x3 = v3.getX();
        float y3 = v3.getY();

        double angle = Math.atan2(y3 - y2, x3 - x2) - Math.atan2(y1 - y2, x1 - x2);
        float l = (float) (r / Math.tan(angle / 2)); // length to clip

        int index = this.getVertexIndex(v2);
        this.deleteVertex(v2);

        Vertex a;
        Vertex b;

        // top left
        if (x2 < 0 && y2 > 0) {

            //draw circle with center (circleX,circleY) and angle = circleAngle
            float circleX = (float) (x2 + r);
            float circleY = (float) (y2 - r);

            for (double i = pi; i >= pi / 2; i -= pi / 32) {
                this.addVertex(index++, new Vertex(circleX + (float) (cos(i) * r), circleY + (float) (sin(i) * r), 0.0f));
            }
            //top left is a special case, so:
            b = new Vertex(x2 + l, y2, 0);
            this.addVertex(index++, b); //first point to be drawn

            a = new Vertex(x2, y2 - l, 0);
            this.addVertex(Vertices.size(), a); // last point to be drawn

        } // top right
        else if (x2 > 0 && y2 > 0) {

            //first vertex
            a = new Vertex(x2 - l, y2, 0);
            this.addVertex(index++, a);

            //draw circle with center (c,d) and angle = circleAngle
            float circleX = (float) (x2 - r);
            float circleY = (float) (y2 - r);
            double circleAngle = 2 * (pi - angle); //angle of circle to draw

            for (double i = pi / 2; i > 0; i -= pi / 32) {
                this.addVertex(index++, new Vertex(circleX + (float) (cos(i) * r), circleY + (float) (sin(i) * r), 0.0f));
            }

            //last vertex
            b = new Vertex(x2, y2 - l, 0);
            this.addVertex(index++, b);

        } // bottom right
        else if (x2 > 0 && y2 < 0) {
            a = new Vertex(x2, y2 + l, 0);
            this.addVertex(index++, a);

            //draw circle with center (circleX,circleY) and angle = circleAngle
            float circleX = (float) (x2 - r);
            float circleY = (float) (y2 + r);

            for (float i = 0; i > -3 * pi / 2; i -= pi / 32) {
                this.addVertex(index++, new Vertex(circleX + (float) (cos(i) * r), circleY + (float) (sin(i) * r), 0.0f));
            }

            b = new Vertex(x2 - l, y2, 0);
            this.addVertex(index++, b);

        } // bottom left
        else if (x2 < 0 && y2 < 0) {
            a = new Vertex(x2 + l, y2, 0);
            this.addVertex(index++, a);

            //draw circle with center (circleX,circleY) and angle = circleAngle
            float circleX = (float) (x2 + r);
            float circleY = (float) (y2 + r);
            for (float i = 3 * pi / 2; i >= pi; i -= pi / 32) {
                this.addVertex(index++, new Vertex(circleX + (float) (cos(i) * r), circleY + (float) (sin(i) * r), 0.0f));
            }

            b = new Vertex(x2, y2 + l, 0);
            this.addVertex(index++, b);

        }

    }

}
