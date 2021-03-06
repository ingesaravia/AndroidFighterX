package com.ils.androidfighterx.clasesOpengl;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by Laura on 07/09/2017.
 */

public class GestorProcesador  {
    private final String vertexShaderCode =
            // This matrix member variable provides a hook to manipulate
            // the coordinates of the objects that use this vertex shader
            "uniform mat4 uMVPMatrix;" +
                    "attribute vec4 vPosition;" +
                    "void main() {" +
                    // the matrix must be included as a modifier of gl_Position
                    // Note that the uMVPMatrix factor *must be first* in order
                    // for the matrix multiplication product to be correct.
                    "  gl_Position = uMVPMatrix * vPosition;" +
                    "}";
    // Use to access and set the view transformation
    private int mMVPMatrixHandle;
    private final String fragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor = vColor;" +
                    "}";
    private final int mProgram;
    private int mPositionHandle;
    private int mColorHandle;

    public GestorObjeto3D miGestorObjeto3D;
    public int opcionObjeto;
    public int vertexCount;
    private final int vertexStride = COORDS_PER_VERTEX * 4; // 4 bytes per vertex
    private FloatBuffer vertexBuffer;
    // number of coordinates per vertex in this array
    static final int COORDS_PER_VERTEX = 3;
    // Set color with red, green, blue and alpha (opacity) values
    float red = 0.63671875f;
    float green = 0.76953125f;
    float blue = 0.22265625f;
    float alpha = 1.0f;
    float[] color ;
    //float color[] = {red, green, blue, alpha};

    public void colorear (float red, float green, float blue, float alpha){
        float auxP[] = {red, green, blue, alpha};
        this.color = auxP;
    }

    public GestorProcesador(int opcionObjeto) {
        this.opcionObjeto = opcionObjeto;
        miGestorObjeto3D = new GestorObjeto3D(opcionObjeto);
        this.vertexCount = miGestorObjeto3D.CharacterVerts.length/COORDS_PER_VERTEX;
        // initialize vertex byte buffer for shape coordinates
        ByteBuffer bb = ByteBuffer.allocateDirect(
                // (number of coordinate values * 4 bytes per float)
                miGestorObjeto3D.CharacterVerts.length * 4);
        // use the device hardware's native byte order
        bb.order(ByteOrder.nativeOrder());

        // create a floating point buffer from the ByteBuffer
        vertexBuffer = bb.asFloatBuffer();
        // add the coordinates to the FloatBuffer
        vertexBuffer.put(miGestorObjeto3D.CharacterVerts);
        // set the buffer to read the first coordinate
        vertexBuffer.position(0);

        int vertexShader = GestorRenderer.loadShader(GLES20.GL_VERTEX_SHADER,
                vertexShaderCode);
        int fragmentShader = GestorRenderer.loadShader(GLES20.GL_FRAGMENT_SHADER,
                fragmentShaderCode);
        // create empty OpenGL ES Program
        mProgram = GLES20.glCreateProgram();
        // add the vertex shader to program
        GLES20.glAttachShader(mProgram, vertexShader);
        // add the fragment shader to program
        GLES20.glAttachShader(mProgram, fragmentShader);
        // creates OpenGL ES program executables
        GLES20.glLinkProgram(mProgram);
    }

    public void draw(float[] mvpMatrix) {
        //colorear
        colorear(red, green, blue, alpha);

        // Add program to OpenGL ES environment
        GLES20.glUseProgram(mProgram);
        // get handle to vertex shader's vPosition member
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
        // Enable a handle to the triangle vertices
        GLES20.glEnableVertexAttribArray(mPositionHandle);
        // Prepare the triangle coordinate data
        GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                vertexStride, vertexBuffer);
        // get handle to fragment shader's vColor member
        mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");
        // Set color for drawing the triangle
        GLES20.glUniform4fv(mColorHandle, 1, color, 0);
        // get handle to shape's transformation matrix
        mMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix");
        // Pass the projection and view transformation to the shader
        GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mvpMatrix, 0);
        // Draw the triangle
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);
        // Disable vertex array
        GLES20.glDisableVertexAttribArray(mPositionHandle);
    }
}
