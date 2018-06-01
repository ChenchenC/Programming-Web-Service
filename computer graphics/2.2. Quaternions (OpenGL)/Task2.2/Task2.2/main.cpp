#define WIN32_LEAN_AND_MEAN

#pragma comment(lib, "opengl32.lib")
#pragma comment(lib, "glu32.lib")
#pragma comment(linker, "/subsystem:console")


#include "windows.h"
#include "stdlib.h"
#include <gl/gl.h>            // standard OpenGL include
#include <gl/glu.h>           // OpenGL utilties
#include <glut.h>             // OpenGL utilties

#include "myvector.h"
#include "mymatrix.h"
#include "myQuat.h"
using namespace MyMathLab;


float angle = 0; // rotation unit
bool isRotation = false;

//prototypes for our callback functions
void draw(void);    //our drawing routine
void idle(void);    //what to do when nothing is happening
void key(unsigned char k, int x, int y);  //handle key presses
void reshape(int width, int height);      //when the window is resized
void init_drawing(void);                  //drawing intialisation
void draw_square(void);                   //drawing square
void draw_vector(MyPosition& startPos, MyVector& v1); //drawing vector
void draw_task32(MyPosition& initialPoint, float angle, MyVector& axis);
void draw_oridinate(void);

										  //our main routine
int main(int argc, char *argv[])
{
	//Initialise Glut and create a window
	glutInit(&argc, argv);
	//sets up our display mode
	//here we've selected an RGBA display with depth testing 
	//and double buffering enabled
	glutInitDisplayMode(GLUT_RGBA | GLUT_DEPTH | GLUT_DOUBLE);
	//create a window and pass through the windows title
	glutCreateWindow("Basic Glut Application");

	//run our own drawing initialisation routine
	init_drawing();

	//tell glut the names of our callback functions point to our 
	//functions that we defined at the start of this file
	glutReshapeFunc(reshape);
	glutKeyboardFunc(key);
	glutIdleFunc(idle);
	glutDisplayFunc(draw);

	//request a window size of 600 x 600
	glutInitWindowSize(600, 600);
	glutReshapeWindow(600, 600);

	//go into the main loop
	//this loop won't terminate until the user exits the 
	//application
	glutMainLoop();

	return 0;
}


//draw callback function - this is called by glut whenever the 
//window needs to be redrawn
void draw(void)
{
	//clear the current window
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	//make changes to the modelview matrix
	//glMatrixMode(GL_MODELVIEW);
	//initialise the modelview matrix to the identity matrix
	//glLoadIdentity();

	//***DO ALL YOUR DRAWING HERE****//
	//glTranslatef(0.0, 0.0, -10.0);

	GLfloat myIdentityMatrix[16] = {
		1.0, 0.0, 0.0, 0.0,
		0.0, 1.0, 0.0, 0.0,
		0.0, 0.0, 1.0, 0.0,
		0.0, 0.0, 0.0, 1.0
	};

	glMatrixMode(GL_MODELVIEW);
	glLoadMatrixf(myIdentityMatrix);

	GLfloat myTranslationMatrix[16] = {
		1.0, 0.0, 0.0, 0.0,
		0.0, 1.0, 0.0, 0.0,
		0.0, 0.0, 1.0, 0.0,
		1.0, 1.0, -5.0, 1.0
	};

	GLfloat myRotationMatrix[16] = {
		cos(DEG2RAD(-angle)),-sin(DEG2RAD(-angle)),0.0,0.0,
		sin(DEG2RAD(-angle)),cos(DEG2RAD(-angle)),0.0,0.0,
		0.0,0.0,1.0,0.0,
		0.0,0.0,0.0,1.0
	};

	//Section 2 drawing square	
	/*glPushMatrix();
		//glRotatef(angle, 0.0, 0.0, 1.0);	
		glMultMatrixf(myRotationMatrix);
		//glTranslatef(1.0, 1.0, -5.0);//setting position
		glMultMatrixf(myTranslationMatrix);		
		draw_square();
	glPopMatrix();

	glPushMatrix();
		glRotatef(angle, 0.0, 0.0, 1.0);
		glTranslatef(-1.0, 1.0, -5.0);//setting position
		draw_square();
	glPopMatrix();*/

	// question 3
	// 1.Move that vertice to central position of this square; 
	// 2. rotation; 3. Move that vertice to previous place
	

	//draw_oridinate();

	//question 2
	// Task 3.2.2 rotate point(1,1,0) 45 degrees around (0,0,1)
	MyPosition initialPoint;
	MyVector axis(0,0,1);
	float angle = 45;
	initialPoint.x = 1;
	initialPoint.y = 1;
	initialPoint.z = 0;

	//question 3
	/*MyPosition initialPoint;
	MyVector axis(10, 0, 0);
	float angle = 45;
	initialPoint.x = 0;
	initialPoint.y = -10;
	initialPoint.z = 0;*/

	draw_task32(initialPoint,angle,axis);

	//flush what we've drawn to the buffer
	glFlush();
	//swap the back buffer with the front buffer
	glutSwapBuffers();
}

//idle callback function - this is called when there is nothing 
//else to do
void idle(void)
{
	//this is a good place to do animation
	//since there are no animations in this test, we can leave 
	//idle() empty
	if (isRotation) {
		angle += 5;
		isRotation = false;
		draw();
	}
}

//key callback function - called whenever the user presses a 
//key
void key(unsigned char k, int x, int y)
{
	switch (k)
	{
		case 27: //27 is the ASCII code for the ESCAPE key
			exit(0);
		case 'r':
			isRotation = true;
			idle();
			break;
		case 'e':
			exit(0);
		break;
	}
}

//reshape callback function - called when the window size changed
void reshape(int width, int height)
{
	//set the viewport to be the same width and height as the window
	glViewport(0, 0, width, height);
	//make changes to the projection matrix
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();

	//set up our projection type
	//we'll be using a perspective projection, with a 90 degree 
	//field of view
	gluPerspective(45.0, (float)width / (float)height, 1.0, 100.0);
	//redraw the view during resizing
	draw();
}

//set up OpenGL before we do any drawing
//this function is only called once at the start of the program
void init_drawing(void)
{
	//blend colours across the surface of the polygons
	//another mode to try is GL_FLAT which is flat shading
	glShadeModel(GL_SMOOTH);
	//turn lighting off
	glDisable(GL_LIGHTING);
	//enable OpenGL hidden surface removal
	glDepthFunc(GL_LEQUAL);
	glEnable(GL_DEPTH_TEST);
}

void draw_square(void)
{
	//a simple function to draw a square with the current markers
	//orientation and position on screen
	glBegin(GL_POLYGON);
	glColor3f(1.0, 0.0, 0.0);
	glVertex3f(-1.0, 1.0, 0.0);
	glColor3f(0.0, 1.0, 0.0);
	glVertex3f(-1.0, -1.0, 0.0);
	glColor3f(0.0, 0.0, 1.0);
	glVertex3f(1.0, -1.0, 0.0);
	glColor3f(1.0, 1.0, 1.0);
	glVertex3f(1.0, 1.0, 0.0);
	glEnd();
}

void draw_vector(MyPosition& startPos, MyVector& v1)
{
	//draw the vector v1 starting from position startPos
	//this function will only work as long as the z coordinate for both positions is zero
	float length = sqrt((v1.x * v1.x) + (v1.y * v1.y) + (v1.z * v1.z));
	MyVector v;
	if (length > 0.0) { v.x = v1.x / length; v.y = v1.y / length; v.z = v1.z / length; }
	else return;
	float d = (v.x * 0.0) + (v.y * 1.0) + (v.z * 0.0);
	float a = RAD2DEG(acos(d));
	if (v.x > 0.0) a = -a;

	glPushMatrix();
	glTranslatef(startPos.x, startPos.y, startPos.z);
	glRotatef(a, 0.0, 0.0, 1.0);
	float space = 0.25;
	glBegin(GL_LINES);
	glVertex3f(0.0, 0.0, 0.0);
	glVertex3f(0.0, length, 0.0);

	glVertex3f(0.0, length, 0.0);
	glVertex3f(-space, length - (space * 1.5), 0.0);

	glVertex3f(0.0, length, 0.0);
	glVertex3f(space, length - (space * 1.5), 0.0);
	glEnd();
	glPopMatrix();
}

void draw_task32(MyPosition& initialPoint, float angle, MyVector& axis) {
	
	// Task 3.2.2 rotate point(1,1,0) 45 degrees around (0,0,1)
	//draw the vector at position
	glDisable(GL_LINE_STIPPLE);
	glLineWidth(1.0);
	glColor3f(1.0, 1.0, 0.0);
	//define a position and a vector
	MyPosition origin;
	origin.x = origin.y = origin.z = 0.0;
	draw_vector(origin, axis);

	//draw the initial point
	glPointSize(2.0);
	glColor3f(1.0, 1.0, 1.0);
	glPushMatrix();
	glTranslatef(0.0, 0.0, 0.0);
	glBegin(GL_POINTS);
	glVertex2f(initialPoint.x, initialPoint.y);
	glEnd();
	glPopMatrix();

	//calculate the rotated point
	axis.normalise();
	MyQuat qvec;
	qvec.w = 0.0;
	qvec.v.x = initialPoint.x;
	qvec.v.y = initialPoint.y;
	qvec.v.z = initialPoint.z;

	MyQuat q1;
	q1.w = cos(angle / 2);
	q1.v.x = axis.x*sin(angle / 2);
	q1.v.y = axis.y*sin(angle / 2);
	q1.v.z = axis.z*sin(angle / 2);

	MyQuat q1Conj = q1.getConjugate();

	MyQuat qrA = qvec.multiplyBy(q1Conj);
	MyQuat qr = q1.multiplyBy(qrA);

	MyVector vr = qr.v;

	//draw the rotated point
	glPointSize(2.0);
	glColor3f(1.0, 1.0, 1.0);
	glPushMatrix();
	glTranslatef(0.0, 0.0, 0.0);
	glBegin(GL_POINTS);
	glVertex2f(vr.x, vr.y);
	glEnd();
	glPopMatrix();
	//draw_vector(origin, vr);
}

void draw_oridinate() {

	//draw a red horizontal line, one unit long
	glLineWidth(3.0);
	glColor3f(1.0, 0.0, 0.0);
	glPushMatrix();
	glTranslatef(0.0, 0.0, 0.0);
	glBegin(GL_LINES);
	glVertex2f(0.0, 0.0);
	glVertex2f(1.0, 0.0);
	glEnd();
	glPopMatrix();

	//draw a green vertical line, one unit high
	glLineWidth(3.0);
	glColor3f(0.0, 0.0, 1.0);
	glPushMatrix();
	glBegin(GL_LINES);
	glVertex2f(0.0, 0.0);
	glVertex2f(0.0, 1.0);
	glEnd();
	glPopMatrix();

	//draw a white point at the origin
	glPointSize(2.0);
	glColor3f(1.0, 1.0, 1.0);
	glPushMatrix();
	glTranslatef(0.0, 0.0, 0.0);
	glBegin(GL_POINTS);
	glVertex2f(0.0, 0.0);
	glEnd();
	glPopMatrix();

}

