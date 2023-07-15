package org.yourorghere;

import com.sun.opengl.util.Animator;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import java.util.Scanner;
//import static org.lwjgl.opengl.GL11.glTranslatef;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

/**
 * Project.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel)
 * <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class Project implements GLEventListener {

    //Animation Variables:
    Scanner in = new Scanner(System.in);
    int decsion = in.nextInt();
    double A1 = 0;
    double A2 = 1.2;
    double A3 = 0;
    double A31 = 1;
    double A4 = 0;
    double A5 = 0;
    double A6 = 0;
    double A7 = -0.05;
    double A8 = 0;
    double A9 = 0;
    double A10 = 1;
    double B1 = -1;
    double B2 = 1;
    double B3 = 0;
    double B4 = 0; 
    double B5 = 1;
    double B6 = 0;
    double B7 = 0;
    double B8 = 720;
    double B9 = 1;
    double B10 = 0;
    double B11 = 1;
    double B12 = 0;

    // animation values for whole logo
    private float scale = 1.0f;
    // animation values for white square shape #2
    // finish x, y = 1.0f 
    private float scaleXY_BS = 0.3f;
    // animation values for white square shape #2
    // finish x = -0.08
    private float translateX_WS = -4.0f;
    // animation values for white triangle shape #3
    // finish x = 0.3
    private float translateX_WT = 4.0f;
    // animation value for rotating logo
    private float rotation = 0.0f;

    // blue color
    private float c1 = 40f;
    private float c2 = 100f;
    private float c3 = 255f;

    // boolean value to stop the rotation when needed
    private boolean stopRot = false;

    //****************************************************
    //      End of animation variables
    //****************************************************
    public static void main(String[] args) {
        System.out.println("What animation do you want to run \"1,2,3\" ");
        Frame frame = new Frame("Zoom Logo");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new Project());
        frame.add(canvas);
        frame.setSize(640, 480);
        final Animator animator = new Animator(canvas);
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before
                // exiting
                new Thread(new Runnable() {

                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
        // Center frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        animator.start();
    }

    public void init(GLAutoDrawable drawable) {
        // Use debug pipeline
        // drawable.setGL(new DebugGL(drawable.getGL()));

        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());

        // Enable VSync
        gl.setSwapInterval(1);

        // Setup the drawing area and shading mode
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glShadeModel(GL.GL_SMOOTH); // try setting this to GL_FLAT and see what happens.
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();

        if (height <= 0) { // avoid a divide by zero error!

            height = 1;
        }
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, h, 1.0, 20.0);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    //Choose the animation to display:
    @Override
    public void display(GLAutoDrawable drawable) {

        switch (decsion) {
            case 1:
                renderA(drawable);
                break;
            case 2:
                renderB(drawable);
                break;
            case 3:
                renderC(drawable);
                break;
            default:
                System.exit(0);
        }
    }

    //Animation A:
    public void renderA(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        // Reset the current matrix to the "identity"
        gl.glLoadIdentity();

        //start position
        gl.glTranslatef(0f, 0.0f, -2f);

        //Grow Logo
        if (A1 < 1.2) {
            A1 = A1 + 0.03;
            SceneA1(drawable);
        } //Shrink Logo
        else if (A2 > 1) {
            A2 = A2 - 0.005;
            SceneA2(drawable);
        } //Scale and Rotate Animation
        else if (A3 < 1) {
            A3 = A3 + 0.01;
            A31 = A31 - 0.003;
            SceneA3(drawable);
        } //Grow Tripods
        else if (A4 < 0.5) {
            A4 = A4 + 0.01;
            SceneA4(drawable);
        } //Connect Camera
        else if (A5 > -0.05) {
            A5 = A5 - 0.001;
            SceneA5(drawable);
        } //change background color to orange + add light
        else if (A6 < 1) {
            A6 = A6 + 0.01;
            SceneA6(drawable);
        } //remove light and background
        else if (A7 < 0) {
            A7 = A7 + 0.001;
            A31 = A31 + 0.003;

            SceneA7(drawable);
        } //Reposition camera logo
        else if (A8 > -0.3) {
            A31 = A31 + 0.003;
            A8 = A8 - 0.01;

            SceneA8(drawable);
        } //grow background
        else if (A9 < 1) {
            A9 = A9 + 0.03;
            SceneA9(drawable);
        } //Last scene - shrink
        else if (A10 > 0) {
            A10 = A10 - 0.01;
            SceneA10(drawable);
        }

        // Flush all drawing operations to the graphics card
        gl.glFlush();
    }

    //Animation B:
    public void renderB(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        // Reset the current matrix to the "identity"
        gl.glLoadIdentity();

        //start position
        gl.glTranslatef(0f, 0.0f, -2f);
        
        //Create Logo
        if(B1 < 0) {
            B1 = B1 + 0.05;
            SceneB1(drawable);
        }
        //Shrink Camera
        else if (B2 > 0){
            B2 = B2 - 0.03;
            SceneB2(drawable);
        }
        //Grow Comment Box
        else if (B3 < 1){
            B3 = B3 + 0.03;
            SceneB3(drawable);
        }
        //Dots Animation
        else if (B4 <= 3){
            SceneB4(drawable);
            B4 = B4 + 0.1;
        }//Shrink Comment Box
        else if (B5 > 0){
            SceneB5(drawable);
            B5 = B5 - 0.03;
        }
        //Grow phone
        else if (B6 < 1){
            B6 = B6 + 0.03;
            SceneB6(drawable);
        }
        //Shake phone
        else if (B7 < 720){ 
            B7 = B7 + 80;
            SceneB7(drawable);
        }
        //Shake phone
        else if (B8 > 0){ 
            B8 = B8 - 80;
            SceneB8(drawable);
        }
        //Shrink phone
        else if (B9 > 0){
            B9 = B9 - 0.03;
            SceneB9(drawable);
        }
        //Grow house
        else if (B10 < 1){
            B10 = B10 + 0.03;
            SceneB10(drawable);
        }
        //Shrink house
        else if (B11 > 0){
            B11 = B11 - 0.03;
            SceneB11(drawable);
        }
        //Final Scene, Grow Camera
        else if (B12 < 1){
            B12 = B12 + 0.03;
            SceneB12(drawable);
        }
        else{
            B1 = B1 + 0.03;
            SceneB1(drawable);
        }
    }

    //Animation C:
    public void renderC(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        // Reset the current matrix to the "identity"
        gl.glLoadIdentity();

        //start position
        gl.glTranslatef(0f, 0.0f, -3f);

        // zoom into the entire logo Animation 1
        if (translateX_WS >= -0.08) {
            if (scale < 1.3f) {
                scale += 0.005;
            }
            gl.glScalef(scale, scale, 1.0f);
        }

        // rotate the entire logo Animation 2
        if (scale >= 1.3f && !stopRot) {
            gl.glRotatef(rotation, 1.0f, 1.0f, 1.0f);
            rotation += 2.5f;
        }

        // SHAPE 1 (BLUE BACKGROUND)
        gl.glPushMatrix();
        Vertex v0 = new Vertex(-0.6f, 0.6f, 0);
        Vertex v1 = new Vertex(0.6f, 0.6f, 0);
        Vertex v2 = new Vertex(0.6f, -0.6f, 0);
        Vertex v3 = new Vertex(-0.6f, -0.6f, 0);

        Rectangle r1 = new Rectangle(v0, v1, v2, v3); //create shape from v0,v1,v2,v3

        //changing the colors
        if (rotation == 90) {
            c1 = 255.0f;
            c2 = 0.0f;
            c3 = 0.0f;
        }
        if (rotation == 270) {
            c1 = 0.0f;
            c2 = 255.0f;
            c3 = 0.0f;
        }
        if (rotation == 270 + 180) {
            c1 = 0.0f;
            c2 = 255.0f;
            c3 = 255.0f;
        }
        if (rotation == 270 + 180 + 180) {
            c1 = 98.0f;
            c2 = 35.0f;
            c3 = 22.0f;
        }
        if (rotation >= 270 + 180 + 180 + 180) {
            c1 = 40.0f;
            c2 = 100.0f;
            c3 = 255.0f;
        }
        if (rotation >= 270 + 180 + 180 + 180 + 180 + 90) {
            c1 = 40.0f;
            c2 = 100.0f;
            c3 = 255.0f;
            stopRot = true; //stop rotation
        }

        r1.setColor(c1 / 255f, c2 / 255f, c3 / 255f); // blue

        r1.smoothEdges(0.4, 0.4, 0.4, 0.4);

        gl.glScalef(scaleXY_BS, scaleXY_BS, 1.0f);
        r1.draw(drawable);
        gl.glScalef(1.0f, 1.0f, 1.0f);

        gl.glPopMatrix();
        // SHAPE 2 (WHITE SQUARE)
        Vertex v4 = new Vertex(-0.3f, 0.2f, 0);
        Vertex v5 = new Vertex(0.25f, 0.2f, 0);
        Vertex v6 = new Vertex(0.25f, -0.2f, 0);
        Vertex v7 = new Vertex(-0.3f, -0.2f, 0);

        Rectangle r2 = new Rectangle(v4, v5, v6, v7); //create shape from v4,v5,v6,v7

        r2.smoothEdges(0.15, 0.05, 0.15, 0.05);

        gl.glTranslatef(translateX_WS, 0, 0); // translation x = -0.08
        r2.draw(drawable);
        gl.glTranslatef(-translateX_WS, 0, 0);

        // SHAPE 3 (WHITE TRIANGLE)
        Vertex v8 = new Vertex(-0.1f, 0.11f, 0);
        Vertex v9 = new Vertex(0.08f, 0.18f, 0);
        Vertex v10 = new Vertex(0.08f, -0.18f, 0);
        Vertex v11 = new Vertex(-0.1f, -0.11f, 0);

        Rectangle r3 = new Rectangle(v8, v9, v10, v11); //create shape from v8,v9,v10,v11

        r3.smoothEdges(0.02, 0.02, 0.05, 0.05);

        gl.glTranslatef(translateX_WT, 0, 0);
        r3.draw(drawable);
        gl.glTranslatef(-translateX_WT, 0, 0);

        // Flush all drawing operations to the graphics card
        gl.glFlush();

        if (scaleXY_BS < 1.0f) {
            scaleXY_BS += 0.05;
            if (scaleXY_BS > 1.0f) {
                scaleXY_BS = 1.0f;
            }
        }
        if (scaleXY_BS == 1.0f && translateX_WS < -0.08) {
            translateX_WS += 0.05;
            if (translateX_WS > -0.08) {
                translateX_WS = -0.08f;
            }
        }
        if (scaleXY_BS == 1.0f && translateX_WT > 0.3) {
            translateX_WT -= 0.05;
            if (translateX_WT < 0.3) {
                translateX_WT = 0.3f;
            }
        }
    }

    //Grow Logo
    private void SceneA1(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glPushMatrix();
        gl.glScaled(A1, A1, 0);

        createShape1(drawable);  //blue background
        createShape2(drawable);
        createShape3(drawable);

        gl.glPopMatrix();

    }

    //Shrink Logo
    private void SceneA2(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glPushMatrix();
        gl.glScaled(A2, A2, 0);

        createShape1(drawable);  //blue background
        createShape2(drawable);
        createShape3(drawable);

        gl.glPopMatrix();

    }

    //Rotate and Scale Animation
    private void SceneA3(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        gl.glPushMatrix();

        gl.glPushMatrix();
        gl.glRotated(A3 * 70, 1, 0, 0);
        gl.glRotated(A3 * 45, 0, 0, 1);
        createShape1(drawable);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslated(0, A3 * 0.35, 0);
        gl.glScaled(A31, A31, 0);

        createShape2(drawable);
        createShape3(drawable);

        gl.glPopMatrix();

        gl.glPopMatrix();

    }

    //Grow Tripods
    private void SceneA4(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        gl.glPushMatrix();

        gl.glPushMatrix();
        gl.glRotatef(70, 1, 0, 0);
        gl.glRotatef(45, 0, 0, 1);
        createShape1(drawable);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslated(0, A3 * 0.35, 0);
        gl.glScaled(A31, A31, 0);
        createShape2(drawable);
        createShape3(drawable);

        gl.glPopMatrix();

        gl.glPopMatrix();

        gl.glPushMatrix();

        gl.glLineWidth(10);
        gl.glScaled(1, A4, 0);
        gl.glTranslated(-0.05, 0, 0);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex3d(0, 0.5, 0);
        gl.glVertex3d(-0.2, 0, 0.2);
        gl.glVertex3d(0, 0.5, 0);
        gl.glVertex3d(0.2, 0, 0.2);
        gl.glVertex3d(0, 0.5, 0);
        gl.glVertex3d(0, 0.05, -0.2);

        gl.glEnd();
        gl.glPopMatrix();

    }

    //Connect Camera
    private void SceneA5(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        gl.glPushMatrix();

        gl.glPushMatrix();
        gl.glRotatef(70, 1, 0, 0);
        gl.glRotatef(45, 0, 0, 1);
        // SHAPE 1 (BLUE BACKGROUND to ORANGE)
        Vertex v0 = new Vertex(-0.6f, 0.6f, 0);
        Vertex v1 = new Vertex(0.6f, 0.6f, 0);
        Vertex v2 = new Vertex(0.6f, -0.6f, 0);
        Vertex v3 = new Vertex(-0.6f, -0.6f, 0);

        Rectangle r1 = new Rectangle(v0, v1, v2, v3); //create shape from v0,v1,v2,v3

        if (A5 < -0.02) {
            r1.setColor(1f, 165 / 255f, 0); // orange
        } else {
            r1.setColor(40 / 255f, 100 / 255f, 1); // blue
        }

        r1.smoothEdges(0.4, 0.4, 0.4, 0.4);
        r1.draw(drawable);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslated(0, 0.35, 0);
        gl.glScaled(A31, A31, 0);
        createShape2(drawable);
        gl.glPushMatrix();
        gl.glTranslated(A5, 0, 0);
        createShape3(drawable);
        gl.glPopMatrix();
        gl.glPopMatrix();

        gl.glPopMatrix();

        gl.glPushMatrix();

        gl.glLineWidth(10);
        gl.glScaled(1, 0.5, 0);
        gl.glTranslated(-0.05, 0, 0);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex3d(0, 0.5, 0);
        gl.glVertex3d(-0.2, 0, 0.2);
        gl.glVertex3d(0, 0.5, 0);
        gl.glVertex3d(0.2, 0, 0.2);
        gl.glVertex3d(0, 0.5, 0);
        gl.glVertex3d(0, 0.05, -0.2);

        gl.glEnd();
        gl.glPopMatrix();

    }

    private void SceneA6(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        gl.glPushMatrix();

        gl.glPushMatrix();
        gl.glRotatef(70, 1, 0, 0);
        gl.glRotatef(45, 0, 0, 1);

        // SHAPE 1 (BLUE BACKGROUND to ORANGE)
        Vertex v0 = new Vertex(-0.6f, 0.6f, 0);
        Vertex v1 = new Vertex(0.6f, 0.6f, 0);
        Vertex v2 = new Vertex(0.6f, -0.6f, 0);
        Vertex v3 = new Vertex(-0.6f, -0.6f, 0);

        Rectangle r1 = new Rectangle(v0, v1, v2, v3); //create shape from v0,v1,v2,v3

        r1.setColor(1f, 165 / 255f, 0); // orange

        r1.smoothEdges(0.4, 0.4, 0.4, 0.4);
        r1.draw(drawable);
        gl.glPopMatrix();

        //Light:
        gl.glPushMatrix();
        gl.glTranslated(0.1, 0.18, 0.0f);
        gl.glColor3f(1, 1, 0);
        gl.glBegin(GL.GL_POLYGON);
        gl.glVertex3d(0, 0.18, 0);
        for (double theta = Math.toRadians(-10); theta <= Math.toRadians(20); theta += 0.001f / 2) {
            gl.glVertex3d(2 * cos(theta), 2 * sin(theta), 0);
        }
        gl.glEnd();
        gl.glPopMatrix();

        //shape 2
        gl.glPushMatrix();
        gl.glTranslated(0, 0.35, 0);
        gl.glScaled(A31, A31, 0);
        createShape2(drawable);
        gl.glPushMatrix();
        gl.glTranslated(-0.05, 0, 0);

        //shape 3
        createShape3(drawable);
        gl.glPopMatrix();
        gl.glPopMatrix();

        gl.glPopMatrix();

        gl.glPushMatrix();

        gl.glLineWidth(10);
        gl.glScaled(1, 0.5, 0);
        gl.glTranslated(-0.05, 0, 0);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex3d(0, 0.5, 0);
        gl.glVertex3d(-0.2, 0, 0.2);
        gl.glVertex3d(0, 0.5, 0);
        gl.glVertex3d(0.2, 0, 0.2);
        gl.glVertex3d(0, 0.5, 0);
        gl.glVertex3d(0, 0.05, -0.2);

        gl.glEnd();

        gl.glPopMatrix();

    }
    //remove light and background

    private void SceneA7(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        gl.glPushMatrix();

        //shape 2
        gl.glTranslated(0, 0.35, 0);
        gl.glScaled(A31, A31, 0);
        createShape2(drawable);

        //shape 3
        gl.glPushMatrix();
        gl.glTranslated(A7, 0, 0);
        createShape3(drawable);

        gl.glPopMatrix();

        gl.glPopMatrix();

    }

    private void SceneA8(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        gl.glPushMatrix();

        //shape 2
        gl.glTranslated(0, 0.35 + A8, 0);
        gl.glScaled(A31, A31, 0);
        createShape2(drawable);

        //shape 3
        gl.glPushMatrix();
        gl.glTranslated(A7, 0, 0);
        createShape3(drawable);

        gl.glPopMatrix();

        gl.glPopMatrix();

    }

    private void SceneA9(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        gl.glPushMatrix();

        gl.glScaled(A9, A9, 0);
        createShape1(drawable);
        gl.glPopMatrix();

        gl.glPushMatrix();

        //shape 2
        gl.glTranslated(0, 0.35 + A8, 0);
        gl.glScaled(A31, A31, 0);
        createShape2(drawable);

        //shape 3
        gl.glPushMatrix();
        gl.glTranslated(A7, 0, 0);
        createShape3(drawable);

        gl.glPopMatrix();

        gl.glPopMatrix();

    }

    private void SceneA10(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glScaled(A10, A10, 0);

        gl.glPushMatrix();

        createShape1(drawable);

        //shape 2
        gl.glTranslated(0, 0.35 + A8, 0);
        gl.glScaled(A31, A31, 0);
        createShape2(drawable);

        //shape 3
        gl.glPushMatrix();
        gl.glTranslated(A7, 0, 0);
        createShape3(drawable);

        gl.glPopMatrix();

        gl.glPopMatrix();

    }

    //Blue background
    public void createShape1(GLAutoDrawable drawable) {
        // SHAPE 1 (BLUE BACKGROUND)
        Vertex v0 = new Vertex(-0.6f, 0.6f, 0);
        Vertex v1 = new Vertex(0.6f, 0.6f, 0);
        Vertex v2 = new Vertex(0.6f, -0.6f, 0);
        Vertex v3 = new Vertex(-0.6f, -0.6f, 0);

        Rectangle r1 = new Rectangle(v0, v1, v2, v3); //create shape from v0,v1,v2,v3

        r1.setColor(40 / 255f, 100 / 255f, 1); // blue

        r1.smoothEdges(0.4, 0.4, 0.4, 0.4);
        r1.draw(drawable);

    }

    //White body of Camera
    public void createShape2(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        Vertex v4 = new Vertex(-0.3f, 0.2f, 0);
        Vertex v5 = new Vertex(0.25f, 0.2f, 0);
        Vertex v6 = new Vertex(0.25f, -0.2f, 0);
        Vertex v7 = new Vertex(-0.3f, -0.2f, 0);

        Rectangle r2 = new Rectangle(v4, v5, v6, v7); //create shape from v4,v5,v6,v7

        r2.smoothEdges(0.15, 0.05, 0.15, 0.05);

        gl.glTranslatef(-0.08f, 0, 0); // translation x = -0.08
        r2.draw(drawable);
        gl.glTranslatef(0.08f, 0, 0); // undo translation
    }

    //White Lens of Camera
    public void createShape3(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        Vertex v8 = new Vertex(-0.1f, 0.11f, 0);
        Vertex v9 = new Vertex(0.08f, 0.18f, 0);
        Vertex v10 = new Vertex(0.08f, -0.18f, 0);
        Vertex v11 = new Vertex(-0.1f, -0.11f, 0);

        Rectangle r3 = new Rectangle(v8, v9, v10, v11); //create shape from v8,v9,v10,v11

        r3.smoothEdges(0.02, 0.02, 0.05, 0.05);

        gl.glTranslatef(0.3f, 0, 0); // translation x = 0.3
        r3.draw(drawable);
        gl.glTranslatef(-0.3f, 0, 0); // undo translation

    }

    //Create Logo:
    private void SceneB1(GLAutoDrawable drawable){
        GL gl = drawable.getGL();

        gl.glPushMatrix();
        gl.glTranslated(B1,0,0);

        createShape1(drawable);  //blue background
        createShape2(drawable);
        createShape3(drawable);

        gl.glPopMatrix();

    }
  
    //Shrink Camera:
    private void SceneB2(GLAutoDrawable drawable){
        GL gl = drawable.getGL();
        gl.glPushMatrix();
        gl.glColor3f(40/255f,100/255f,1);
        createShape1(drawable);  //blue background

        gl.glScaled(B2,B2,0);

        Vertex v4 = new Vertex(-0.3f,0.2f,0);
        Vertex v5 = new Vertex(0.25f,0.2f,0);
        Vertex v6 = new Vertex(0.25f,-0.2f,0);
        Vertex v7 = new Vertex(-0.3f,-0.2f,0);
        Rectangle r2 = new Rectangle(v4,v5,v6,v7);

        r2.setColor(1,1,1);
        r2.smoothEdges(0.15,0.05,0.15,0.05);

        gl.glTranslatef(-0.08f,0,0);
        r2.draw(drawable);
        gl.glTranslatef(0.08f,0,0);


        createShape3(drawable);

        gl.glPopMatrix();

    }

    //Grow Comment Box:
    private void SceneB3(GLAutoDrawable drawable){
        GL gl = drawable.getGL();
        gl.glPushMatrix();

        gl.glColor3f(40/255f,100/255f,1);
        createShape1(drawable);  //blue background

        gl.glScaled(this.B3,this.B3,0);

        Vertex v4 = new Vertex(-0.3f,0.2f,0);
        Vertex v5 = new Vertex(0.25f,0.2f,0);
        Vertex v6 = new Vertex(0.25f,-0.2f,0);
        Vertex v7 = new Vertex(-0.3f,-0.2f,0);
        Rectangle r2 = new Rectangle(v4,v5,v6,v7);

        r2.setColor(1,1,1);
        r2.smoothEdges(0.15,0.05,0.15,0.05);

        r2.draw(drawable);

        //comment box
        gl.glBegin(GL.GL_POLYGON);
        gl.glVertex3d(-0.3f,-0.1f,0);
        gl.glVertex3d(-0.2f,-0.2f,0);
        gl.glVertex3d(-0.4f,-0.3f,0);
        gl.glEnd();


        gl.glPopMatrix();

    }

    //Comment Dots Animation:
    private void SceneB4(GLAutoDrawable drawable){
        GL gl = drawable.getGL();
        gl.glPushMatrix();

        gl.glColor3f(40/255f,100/255f,1);
        createShape1(drawable);  //blue background

        Vertex v4 = new Vertex(-0.3f,0.2f,0);
        Vertex v5 = new Vertex(0.25f,0.2f,0);
        Vertex v6 = new Vertex(0.25f,-0.2f,0);
        Vertex v7 = new Vertex(-0.3f,-0.2f,0);
        Rectangle r2 = new Rectangle(v4,v5,v6,v7);

        r2.setColor(1,1,1);
        r2.smoothEdges(0.15,0.05,0.15,0.05);

        r2.draw(drawable);

        //comment box
        gl.glBegin(GL.GL_POLYGON);
        gl.glVertex3d(-0.3f,-0.1f,0);
        gl.glVertex3d(-0.2f,-0.2f,0);
        gl.glVertex3d(-0.4f,-0.3f,0);
        gl.glEnd();

        //Dots:
        gl.glColor3d(0.5,0.5,0.5);
        switch((int)this.B4) {
            case 2:
                drawCircle(drawable,-0.15, 0, 0.05);
            case 1:
                drawCircle(drawable,0, 0, 0.05);
            case 0:
                drawCircle(drawable,0.15, 0, 0.05);
        }
        gl.glPopMatrix();

    }

    //Shrink Comment Box:
    private void SceneB5(GLAutoDrawable drawable){
        GL gl = drawable.getGL();
        gl.glPushMatrix();

        gl.glColor3f(40/255f,100/255f,1);
        createShape1(drawable);  //blue background

        gl.glScaled(B5, B5,0);
        Vertex v4 = new Vertex(-0.3f,0.2f,0);
        Vertex v5 = new Vertex(0.25f,0.2f,0);
        Vertex v6 = new Vertex(0.25f,-0.2f,0);
        Vertex v7 = new Vertex(-0.3f,-0.2f,0);
        Rectangle r2 = new Rectangle(v4,v5,v6,v7);

        r2.setColor(1,1,1);
        r2.smoothEdges(0.15,0.05,0.15,0.05);

        r2.draw(drawable);

        //comment box
        gl.glBegin(GL.GL_POLYGON);
        gl.glVertex3d(-0.3f,-0.1f,0);
        gl.glVertex3d(-0.2f,-0.2f,0);
        gl.glVertex3d(-0.4f,-0.3f,0);
        gl.glEnd();

        //Dots:
        gl.glColor3d(0.5,0.5,0.5);
        drawCircle(drawable,-0.15, 0, 0.05);
        drawCircle(drawable,0.00, 0, 0.05);
        drawCircle(drawable,0.15, 0, 0.05);

        gl.glPopMatrix();

    }

    //Grow Phone
    private void SceneB6(GLAutoDrawable drawable){
        GL gl = drawable.getGL();
        gl.glPushMatrix();

        createShape1(drawable);

        gl.glColor3d(1,1,1);
        gl.glScaled(B6, B6, B6);
        drawPhone(drawable);

        gl.glPopMatrix();

    }
    
    //Shake Phone
    private void SceneB7(GLAutoDrawable drawable){
        GL gl = drawable.getGL();
        gl.glPushMatrix();

        createShape1(drawable);

        gl.glColor3d(1,1,1);
        gl.glRotated(Math.toRadians(B7),0,0,1);
        drawPhone(drawable);

        gl.glPopMatrix();

    }
    //Shake Phone
    private void SceneB8(GLAutoDrawable drawable){
        GL gl = drawable.getGL();
        gl.glPushMatrix();

        createShape1(drawable);

        gl.glColor3d(1,1,1);
        gl.glRotated(Math.toRadians(B8),0,0,1);
        drawPhone(drawable);

        gl.glPopMatrix();

    }
    
    //Shrink Phone
    private void SceneB9(GLAutoDrawable drawable){
        GL gl = drawable.getGL();
        gl.glPushMatrix();

        createShape1(drawable);

        gl.glColor3d(1,1,1);
        gl.glScaled(B9, B9, B9);
        drawPhone(drawable);


        gl.glPopMatrix();

    }
    //grow house
    private void SceneB10(GLAutoDrawable drawable){
        GL gl = drawable.getGL();
        gl.glPushMatrix();

        createShape1(drawable);

        gl.glColor3d(1,1,1);
        gl.glScaled(B10, B10, B10);
        
        drawHouse(drawable);
        gl.glScaled(0.5,0.5,0);
        createShape2(drawable);
        createShape3(drawable);

        gl.glPopMatrix();

    }
    //shrink house
    private void SceneB11(GLAutoDrawable drawable){
        GL gl = drawable.getGL();
        gl.glPushMatrix();

        createShape1(drawable);

        gl.glColor3d(1,1,1);
        gl.glScaled(B11, B11, B11);
        
        drawHouse(drawable);
        gl.glScaled(0.5,0.5,0);
        createShape2(drawable);
        createShape3(drawable);

        gl.glPopMatrix();

    }
    //Final Scene, Grow Camera
    private void SceneB12(GLAutoDrawable drawable){
        GL gl = drawable.getGL();
        gl.glPushMatrix();

        createShape1(drawable);

        gl.glColor3d(1,1,1);
        gl.glScaled(B12, B12, B12);
        
        createShape2(drawable);
        createShape3(drawable);

        gl.glPopMatrix();

    }

    private void drawHouse(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glPushMatrix();

        gl.glColor3f(30 / 255f, 90 / 255f, 0.9f);
        gl.glTranslated(0, 0.2, 0);
        gl.glScaled(0.7, 0.7, 0.7);
        gl.glBegin(GL.GL_POLYGON);
        gl.glVertex3d(0, 0.4, 0); //tip
        gl.glVertex3d(0.7, 0, 0); //right
        gl.glVertex3d(0.5, 0, 0); //inner corner
        gl.glVertex3d(0.5, -0.7, 0); //bottom right
        gl.glVertex3d(-0.5, -0.7, 0); //bottom left
        gl.glVertex3d(-0.5, 0, 0); //inner corner
        gl.glVertex3d(-0.7, 0, 0); //left
        gl.glEnd();

        gl.glPopMatrix();
    }

    private void drawCircle(GLAutoDrawable drawable, double xc, double yc, double r) {
        GL gl = drawable.getGL();
        gl.glPushMatrix();
        gl.glTranslated(xc, yc, 0.0f);

        gl.glBegin(GL.GL_POLYGON);

        for (float theta = 0; theta <= 2 * 3.14; theta += 0.01f / r) {
            gl.glVertex3d(r * cos(theta), r * sin(theta), 0);
        }
        gl.glEnd();
        gl.glPopMatrix();
    }

    private void drawPhone(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        gl.glScaled(0.5, 0.5, 0);
        //Handle
        gl.glPushMatrix();
        gl.glTranslated(0.5, 0.5, 0.0f);

        gl.glBegin(GL.GL_POLYGON);

        for (double theta = 3.14; theta <= Math.toRadians(270); theta += 0.01f / 1) {
            gl.glVertex3d(1 * cos(theta), 1 * sin(theta), 0);
        }
        gl.glEnd();
        gl.glPopMatrix();

        //Upper Circle
        gl.glPushMatrix();
        gl.glTranslated(-0.3, 0.45, 0);
        gl.glBegin(GL.GL_POLYGON);

        for (double theta = 0; theta <= 2 * 3.14; theta += 0.01f / 0.2) {
            gl.glVertex3d(0.2 * cos(theta), 0.2 * sin(theta), 0);
        }
        gl.glEnd();
        gl.glPopMatrix();

        //Lower Circle
        gl.glPushMatrix();
        gl.glTranslated(0.45, -0.3, 0);
        gl.glBegin(GL.GL_POLYGON);
        for (double theta = 0; theta <= 2 * 3.14; theta += 0.01f / 0.2) {
            gl.glVertex3d(0.2 * cos(theta), 0.2 * sin(theta), 0);
        }
        gl.glEnd();
        gl.glPopMatrix();

    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}
