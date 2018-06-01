#define WIN32_LEAN_AND_MEAN

#pragma comment(lib, "opengl32.lib")
#pragma comment(lib, "glu32.lib")
#pragma comment(lib, "glaux.lib")
#pragma comment(linker, "/subsystem:console")

#include "windows.h"

#include <gl/gl.h>            // standard OpenGL include
#include <gl/glu.h>           // OpenGL utilties
#include <glut.h>             // OpenGL utilties

#include <mmsystem.h>

#include "myvector.h"
#include "mymatrix.h"
#include "myquat.h"

#include "stdlib.h"
#include "stdio.h"

#include "objloader.h"
#include "particleSystem.h"
#include "boundingsphere.h"


//define the particle systems
//int g_nActiveSystem = 2;
//CParticleSystem *g_pParticleSystems[7];
void initParticles( void );
float  g_fElpasedTime;
double g_dCurTime;
double g_dLastTime;

ObjMesh* tankBody;
ObjMesh* tankTurret;
ObjMesh* tankMainGun;
ObjMesh* tankSecondaryGun;
ObjMesh* tankWheel;

//task 3
ObjMesh* tankBodyLow;
ObjMesh* tankTurretLow;
ObjMesh* tankMainGunLow;
ObjMesh* tankSecondaryGunLow;
ObjMesh* tankWheelLow;

//task 2,2 question 2
BoundingSphere tankBodySphere;
BoundingSphere tankTurretSphere;
BoundingSphere tankMainGunSphere;
BoundingSphere tankSecondaryGunSphere;
BoundingSphere tankWheelSphere;
BoundingSphere tankSphere;

void load_tank_objs(void);

float zPos = -30.0;
float yRot = 0.0;

// task 1.2 question 2
float secGunRot = 0.0;
float turRot = 0.0;
float wheeRot = 0.0;

// task 1.2 question 3
int tankBodyID = 0;

float angleX = 0.0;
float angleY = 0.0;
float angleZ = 0.0;

//prototypes for our callback functions
void draw(void);    //our drawing routine
void idle(void);    //what to do when nothing is happening
void key(unsigned char k, int x, int y);  //handle key presses
void reshape(int width, int height);      //when the window is resized
void init_drawing(void);                  //drawing intialisation

void load_tank_objs(void);
void load_low_detail_tank_objs(void);

void collideLine(int a, int b, int c, int d, MyPosition startPosition);

CParticleSystem *g_pParticleSystems;// task 2.2 question 6
void initParticles(float x, float y, float z); //  task 2.2 question 6

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

	load_tank_objs();
	load_low_detail_tank_objs(); //task 3

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

void load_tank_objs(void)
{
	tankBody = LoadOBJ(".\\tankobjs\\tankbody.obj");
	tankTurret = LoadOBJ(".\\tankobjs\\tankturret.obj");
	tankMainGun = LoadOBJ(".\\tankobjs\\tankmaingun.obj");
	tankSecondaryGun = LoadOBJ(".\\tankobjs\\tanksecondarygun.obj");
	tankWheel = LoadOBJ(".\\tankobjs\\tankwheel.obj");
	SetTextures(tankBody->m_iMeshID, NULL, ".\\tankobjs\\texture.tga");

	// task 1.2 question 3
	tankBodyID = glGenLists(1);
	glNewList(tankBodyID, GL_COMPILE);
	DrawOBJ(tankBody->m_iMeshID);
	//draw_object(tankBody);
	glEndList();
}

void load_low_detail_tank_objs(void)
{
	tankBodyLow = LoadOBJ(".\\tankobjs\\tankbodyLow.obj");
	tankTurretLow = LoadOBJ(".\\tankobjs\\tankturretLow.obj");
	tankMainGunLow = LoadOBJ(".\\tankobjs\\tankmaingunLow.obj");
	tankSecondaryGunLow = LoadOBJ(".\\tankobjs\\tanksecondarygunlow.obj");
	tankWheelLow = LoadOBJ(".\\tankobjs\\tankwheelLow.obj");
	SetTextures(tankBody->m_iMeshID, NULL, ".\\tankobjs\\texture.tga");

}

void create_BoundingSphere(void) {
	//task 2.2 question 2
	tankBodySphere = tankBodySphere.initialiseBoundingShphere(tankBody);
	tankTurretSphere = tankTurretSphere.initialiseBoundingShphere(tankTurret);
	tankMainGunSphere = tankMainGunSphere.initialiseBoundingShphere(tankMainGun);
	tankSecondaryGunSphere = tankSecondaryGunSphere.initialiseBoundingShphere(tankSecondaryGun);
	//tankWheelSphere = tankWheelSphere.initialiseBoundingShphere(tankWheel);

	tankSphere.center.x = (tankBodySphere.center.x + tankTurretSphere.center.x + tankMainGunSphere.center.x
		+ tankSecondaryGunSphere.center.x) / 4;
	tankSphere.center.y = (tankBodySphere.center.y + tankTurretSphere.center.y + tankMainGunSphere.center.y
		+ tankSecondaryGunSphere.center.y) / 4;
	tankSphere.center.z = (tankBodySphere.center.z + tankTurretSphere.center.z + tankMainGunSphere.center.z
		+ tankSecondaryGunSphere.center.z) / 4;

	tankSphere.radius = 0.0;
	tankSphere.radius = tankSphere.calculateRadius(tankBody, tankSphere.center, tankSphere.radius);
	tankSphere.radius = tankSphere.calculateRadius(tankTurret, tankSphere.center, tankSphere.radius);
	tankSphere.radius = tankSphere.calculateRadius(tankMainGun, tankSphere.center, tankSphere.radius);
	tankSphere.radius = tankSphere.calculateRadius(tankSecondaryGun, tankSphere.center, tankSphere.radius);
}

void collidePosition(MyPosition collidingPoint, MyPosition startPosition) {
	printf("\ntesting position is %f ,%f %f\n", collidingPoint.x, collidingPoint.y, collidingPoint.z);

	float distance = MyVector(collidingPoint, tankSphere.center).getMagnitude();
	if (distance <= tankSphere.radius) {
		printf("distance is %f ,radius is %f \n", distance, tankSphere.radius);
		printf("tank center is %f ,%f %f, radius is %f\n", tankSphere.center.x,
			tankSphere.center.y, tankSphere.center.z, tankSphere.radius);
		glPushMatrix();
		glDisable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE);
		glTranslatef(startPosition.x, startPosition.y, startPosition.z);
		glScalef(0.1, 0.1, 0.1);
		glColor4f(1.0, 1.0, 1.0, 0.4);
		GLUquadric *pTankSphere = gluNewQuadric();
		glTranslatef(tankSphere.center.x, tankSphere.center.y, tankSphere.center.z);
		gluQuadricDrawStyle(pTankSphere, GLU_FILL);
		gluQuadricNormals(pTankSphere, GLU_SMOOTH);
		gluSphere(pTankSphere, tankSphere.radius, 15, 10);
		gluDeleteQuadric(pTankSphere);
		glDisable(GL_BLEND);
		glEnable(GL_TEXTURE_2D);
		glPopMatrix();

		//task 2.2 question 6
		initParticles(collidingPoint.x, collidingPoint.y, collidingPoint.z);

		glPushMatrix();
			glEnable(GL_DEPTH_TEST);
			glDepthMask(GL_FALSE);

			glEnable(GL_TEXTURE_2D);
			glEnable(GL_BLEND);

			//specify blending function here using glBlendFunc
			glBlendFunc(GL_SRC_COLOR, GL_DST_ALPHA);
			glBindTexture(GL_TEXTURE_2D, g_pParticleSystems->GetTextureID());
			g_pParticleSystems->Render();

			glDepthMask(GL_TRUE);
			glDisable(GL_BLEND);

		glPopMatrix();

		distance = MyVector(collidingPoint, tankBodySphere.center).getMagnitude();
		if (distance <= tankBodySphere.radius) {
			printf("tank body center is %f ,%f %f, radius is %f\n", tankBodySphere.center.x,
				tankBodySphere.center.y, tankBodySphere.center.z, tankBodySphere.radius);

			glPushMatrix();
			glDisable(GL_TEXTURE_2D);
			glEnable(GL_BLEND);
			glBlendFunc(GL_SRC_ALPHA, GL_ONE);
			glTranslatef(startPosition.x, startPosition.y, startPosition.z);
			glScalef(0.1, 0.1, 0.1);
			glColor4f(1.0, 1.0, 1.0, 0.4);
			GLUquadric *p = gluNewQuadric();
			glTranslatef(tankBodySphere.center.x, tankBodySphere.center.y, tankBodySphere.center.z);
			//gluQuadricDrawStyle(p, GLU_FILL);
			//gluQuadricNormals(p, GLU_SMOOTH);
			gluSphere(p, tankBodySphere.radius, 15, 10);
			gluDeleteQuadric(p);
			glDisable(GL_BLEND);
			glEnable(GL_TEXTURE_2D);
			glPopMatrix();
		}
		distance = MyVector(collidingPoint, tankTurretSphere.center).getMagnitude();
		if (distance <= tankTurretSphere.radius) {
			printf("tank turret center is %f ,%f %f, radius is %f\n", tankTurretSphere.center.x,
				tankTurretSphere.center.y, tankTurretSphere.center.z, tankTurretSphere.radius);

			glPushMatrix();
			glDisable(GL_TEXTURE_2D);
			glEnable(GL_BLEND);
			glBlendFunc(GL_SRC_ALPHA, GL_ONE);
			glTranslatef(startPosition.x, startPosition.y, startPosition.z);
			glScalef(0.1, 0.1, 0.1);
			glColor4f(1.0, 1.0, 1.0, 0.1);
			GLUquadric *pTurret = gluNewQuadric();
			glTranslatef(tankTurretSphere.center.x, tankTurretSphere.center.y, tankTurretSphere.center.z);
			//gluQuadricDrawStyle(p, GLU_FILL);
			//gluQuadricNormals(p, GLU_SMOOTH);
			gluSphere(pTurret, tankTurretSphere.radius, 30, 30);
			gluDeleteQuadric(pTurret);
			glDisable(GL_BLEND);
			glEnable(GL_TEXTURE_2D);
			glPopMatrix();
		}
		distance = MyVector(collidingPoint, tankMainGunSphere.center).getMagnitude();
		if (distance <= tankMainGunSphere.radius) {
			printf("tank main gun center is %f ,%f %f, radius is %f\n", tankMainGunSphere.center.x,
				tankMainGunSphere.center.y, tankMainGunSphere.center.z, tankMainGunSphere.radius);

			glPushMatrix();
			glDisable(GL_TEXTURE_2D);
			glEnable(GL_BLEND);
			glBlendFunc(GL_SRC_ALPHA, GL_ONE);
			glTranslatef(startPosition.x, startPosition.y, startPosition.z);
			glScalef(0.1, 0.1, 0.1);
			glColor4f(1.0, 1.0, 1.0, 0.1);
			GLUquadric *pMainGun = gluNewQuadric();
			glTranslatef(tankMainGunSphere.center.x, tankMainGunSphere.center.y, tankMainGunSphere.center.z);
			//gluQuadricDrawStyle(p, GLU_FILL);
			//gluQuadricNormals(p, GLU_SMOOTH);
			gluSphere(pMainGun, tankMainGunSphere.radius, 30, 30);
			gluDeleteQuadric(pMainGun);
			glDisable(GL_BLEND);
			glEnable(GL_TEXTURE_2D);
			glPopMatrix();
		}
		distance = MyVector(collidingPoint, tankSecondaryGunSphere.center).getMagnitude();
		if (distance <= tankSecondaryGunSphere.radius) {
			printf("tank secondary gun center is %f ,%f %f, radius is %f\n", tankSecondaryGunSphere.center.x,
				tankSecondaryGunSphere.center.y, tankSecondaryGunSphere.center.z, tankSecondaryGunSphere.radius);

			printf("tank secondary gun remote point is %f ,%f %f\n", tankSecondaryGunSphere.remoteP.x,
				tankSecondaryGunSphere.remoteP.y, tankSecondaryGunSphere.remoteP.z);

			glPushMatrix();
			glDisable(GL_TEXTURE_2D);
			glEnable(GL_BLEND);
			glBlendFunc(GL_SRC_ALPHA, GL_ONE);
			glTranslatef(startPosition.x, startPosition.y, startPosition.z);
			glScalef(0.1, 0.1, 0.1);
			glColor4f(1.0, 1.0, 1.0, 0.1);
			GLUquadric *pSecondaryGun = gluNewQuadric();
			glTranslatef(tankSecondaryGunSphere.center.x, tankSecondaryGunSphere.center.y, tankSecondaryGunSphere.center.z);
			//gluQuadricDrawStyle(p, GLU_FILL);
			//gluQuadricNormals(p, GLU_SMOOTH);
			gluSphere(pSecondaryGun, tankSecondaryGunSphere.radius, 30, 30);
			gluDeleteQuadric(pSecondaryGun);
			glDisable(GL_BLEND);
			glEnable(GL_TEXTURE_2D);
			glPopMatrix();
		}
	}
	else {
		printf("testing point is not in whole tank sphere\n ");
	}
}

void draw_BoundingSphere(float x, float y, float z) {

	//task 2,2
	create_BoundingSphere();
	MyPosition testPoint;
	testPoint.x = 10.0 + angleX;
	testPoint.y = -10.0 + angleY;
	testPoint.z = -10.0 + angleZ;

	MyPosition startPosition;
	startPosition.x = x;
	startPosition.y = y;
	startPosition.z = z;

	//draw the testing point
	glPushMatrix();

	glDisable(GL_TEXTURE_2D);
	glEnable(GL_BLEND);
	//glDisable(GL_DEPTH_TEST);
	glTranslatef(startPosition.x, startPosition.y, startPosition.z);
	glScalef(0.1, 0.1, 0.1);

	glPointSize(20.0);
	glColor4f(1.0, 1.0, 1.0, 1);
	//glBlendFunc(GL_SRC_ALPHA, GL_ONE);
	glBlendFunc(GL_SRC_COLOR, GL_DST_ALPHA);

	glBegin(GL_POINTS);
	//glVertex3f(collidingPoint.x, collidingPoint.y, collidingPoint.z);
	glVertex3f(testPoint.x, testPoint.x, testPoint.x);
	glEnd();

	glEnable(GL_TEXTURE_2D);
	glDisable(GL_BLEND);
	//glEnable(GL_DEPTH_TEST);
	glPopMatrix();

	collidePosition(testPoint, startPosition);

}

void call_collideTest(float x, float y, float z) {

	MyPosition startPosition;
	startPosition.x = x;
	startPosition.y = y;
	startPosition.z = z;

	int a = 1;
	int b = 1;
	int c = 1;
	int d = 0;
	/*scanf("According to AX+BY+CZ+D=0, please input the parameters of testing line. \n");
	scanf("A:%d\n", &a);
	scanf("B:%d\n", &b);
	scanf("C:%d\n", &c);
	scanf("D:%d\n", &d);*/

	collideLine(a, b, c, d, startPosition);

}

void collideLine(int a, int b, int c, int d, MyPosition startPosition) {

	create_BoundingSphere();
	double tank = fabs(a*tankSphere.center.x + b*tankSphere.center.y + c*tankSphere.center.z + d) / sqrt(a*a + b*b + c*c);
	double tankBody = fabs(a*tankBodySphere.center.x + b*tankBodySphere.center.y + c*tankBodySphere.center.z + d) / sqrt(a*a + b*b + c*c);
	double tankTurret = fabs(a*tankTurretSphere.center.x + b*tankTurretSphere.center.y + c*tankTurretSphere.center.z + d) / sqrt(a*a + b*b + c*c);
	double tankMainGun = fabs(a*tankMainGunSphere.center.x + b*tankMainGunSphere.center.y + c*tankMainGunSphere.center.z + d) / sqrt(a*a + b*b + c*c);
	double tankSecondaryGun = fabs(a*tankSecondaryGunSphere.center.x + b*tankSecondaryGunSphere.center.y + c*tankSecondaryGunSphere.center.z + d) / sqrt(a*a + b*b + c*c);

	if (tank <= tankSphere.radius) {
		printf("distance is %f ,radius is %f \n", tank, tankSphere.radius);
		printf("tank center is %f ,%f %f, radius is %f\n", tankSphere.center.x,
			tankSphere.center.y, tankSphere.center.z, tankSphere.radius);
		glPushMatrix();
		glDisable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE);
		glTranslatef(startPosition.x, startPosition.y, startPosition.z);
		glScalef(0.1, 0.1, 0.1);
		glColor4f(1.0, 1.0, 1.0, 0.4);
		GLUquadric *pTankSphere = gluNewQuadric();
		glTranslatef(tankSphere.center.x, tankSphere.center.y, tankSphere.center.z);
		gluQuadricDrawStyle(pTankSphere, GLU_FILL);
		gluQuadricNormals(pTankSphere, GLU_SMOOTH);
		gluSphere(pTankSphere, tankSphere.radius, 15, 10);
		gluDeleteQuadric(pTankSphere);
		glDisable(GL_BLEND);
		glEnable(GL_TEXTURE_2D);
		glPopMatrix();

		if (tankBody <= tankBodySphere.radius) {
			printf("tank body center is %f ,%f %f, radius is %f\n", tankBodySphere.center.x,
				tankBodySphere.center.y, tankBodySphere.center.z, tankBodySphere.radius);

			glPushMatrix();
			glDisable(GL_TEXTURE_2D);
			glEnable(GL_BLEND);
			glBlendFunc(GL_SRC_ALPHA, GL_ONE);
			glTranslatef(startPosition.x, startPosition.y, startPosition.z);
			glScalef(0.1, 0.1, 0.1);
			glColor4f(1.0, 1.0, 1.0, 0.4);
			GLUquadric *p = gluNewQuadric();
			glTranslatef(tankBodySphere.center.x, tankBodySphere.center.y, tankBodySphere.center.z);
			//gluQuadricDrawStyle(p, GLU_FILL);
			//gluQuadricNormals(p, GLU_SMOOTH);
			gluSphere(p, tankBodySphere.radius, 15, 10);
			gluDeleteQuadric(p);
			glDisable(GL_BLEND);
			glEnable(GL_TEXTURE_2D);
			glPopMatrix();
		}

		if (tankTurret <= tankTurretSphere.radius) {
			printf("tank turret center is %f ,%f %f, radius is %f\n", tankTurretSphere.center.x,
				tankTurretSphere.center.y, tankTurretSphere.center.z, tankTurretSphere.radius);

			glPushMatrix();
			glDisable(GL_TEXTURE_2D);
			glEnable(GL_BLEND);
			glBlendFunc(GL_SRC_ALPHA, GL_ONE);
			glTranslatef(startPosition.x, startPosition.y, startPosition.z);
			glScalef(0.1, 0.1, 0.1);
			glColor4f(1.0, 1.0, 1.0, 0.1);
			GLUquadric *pTurret = gluNewQuadric();
			glTranslatef(tankTurretSphere.center.x, tankTurretSphere.center.y, tankTurretSphere.center.z);
			//gluQuadricDrawStyle(p, GLU_FILL);
			//gluQuadricNormals(p, GLU_SMOOTH);
			gluSphere(pTurret, tankTurretSphere.radius, 30, 30);
			gluDeleteQuadric(pTurret);
			glDisable(GL_BLEND);
			glEnable(GL_TEXTURE_2D);
			glPopMatrix();
		}

		if (tankMainGun <= tankMainGunSphere.radius) {
			printf("tank main gun center is %f ,%f %f, radius is %f\n", tankMainGunSphere.center.x,
				tankMainGunSphere.center.y, tankMainGunSphere.center.z, tankMainGunSphere.radius);

			glPushMatrix();
			glDisable(GL_TEXTURE_2D);
			glEnable(GL_BLEND);
			glBlendFunc(GL_SRC_ALPHA, GL_ONE);
			glTranslatef(startPosition.x, startPosition.y, startPosition.z);
			glScalef(0.1, 0.1, 0.1);
			glColor4f(1.0, 1.0, 1.0, 0.1);
			GLUquadric *pMainGun = gluNewQuadric();
			glTranslatef(tankMainGunSphere.center.x, tankMainGunSphere.center.y, tankMainGunSphere.center.z);
			//gluQuadricDrawStyle(p, GLU_FILL);
			//gluQuadricNormals(p, GLU_SMOOTH);
			gluSphere(pMainGun, tankMainGunSphere.radius, 30, 30);
			gluDeleteQuadric(pMainGun);
			glDisable(GL_BLEND);
			glEnable(GL_TEXTURE_2D);
			glPopMatrix();
		}

		if (tankSecondaryGun <= tankSecondaryGunSphere.radius) {
			printf("tank secondary gun center is %f ,%f %f, radius is %f\n", tankSecondaryGunSphere.center.x,
				tankSecondaryGunSphere.center.y, tankSecondaryGunSphere.center.z, tankSecondaryGunSphere.radius);

			printf("tank secondary gun remote point is %f ,%f %f\n", tankSecondaryGunSphere.remoteP.x,
				tankSecondaryGunSphere.remoteP.y, tankSecondaryGunSphere.remoteP.z);

			glPushMatrix();
			glDisable(GL_TEXTURE_2D);
			glEnable(GL_BLEND);
			glBlendFunc(GL_SRC_ALPHA, GL_ONE);
			glTranslatef(startPosition.x, startPosition.y, startPosition.z);
			glScalef(0.1, 0.1, 0.1);
			glColor4f(1.0, 1.0, 1.0, 0.1);
			GLUquadric *pSecondaryGun = gluNewQuadric();
			glTranslatef(tankSecondaryGunSphere.center.x, tankSecondaryGunSphere.center.y, tankSecondaryGunSphere.center.z);
			//gluQuadricDrawStyle(p, GLU_FILL);
			//gluQuadricNormals(p, GLU_SMOOTH);
			gluSphere(pSecondaryGun, tankSecondaryGunSphere.radius, 30, 30);
			gluDeleteQuadric(pSecondaryGun);
			glDisable(GL_BLEND);
			glEnable(GL_TEXTURE_2D);
			glPopMatrix();
		}
	}
	else {
		printf("testing point is not in whole tank sphere\n ");
	}
}

//drawing function implementation
void draw_object(ObjMesh *pMesh) {
	glBegin(GL_TRIANGLES);
	int faceNum = pMesh->m_iNumberOfFaces;
	for (int i = 0; i < faceNum; i++) {
		ObjFace *pf = &pMesh->m_aFaces[i];
		float vx, vy, vz, nx, ny, nz, u, v;
		for (int j = 0; j < 3; j++) {

			vx = pMesh->m_aVertexArray[pf->m_aVertexIndices[j]].x;
			vy = pMesh->m_aVertexArray[pf->m_aVertexIndices[j]].y;
			vz = pMesh->m_aVertexArray[pf->m_aVertexIndices[j]].z;
			glVertex3f(vx, vy, vz);

			if (pMesh->m_aNormalArray != NULL) {
				nx = pMesh->m_aNormalArray[pf->m_aNormalIndices[j]].x;
				ny = pMesh->m_aNormalArray[pf->m_aNormalIndices[j]].y;
				nz = pMesh->m_aNormalArray[pf->m_aNormalIndices[j]].z;
				glNormal3f(nx, ny, nz);
			}
			if (pMesh->m_aTexCoordArray != NULL) {
				u = pMesh->m_aTexCoordArray[pf->m_aTexCoordIndicies[j]].u;
				v = pMesh->m_aTexCoordArray[pf->m_aTexCoordIndicies[j]].v;
				glTexCoord2f(u, v);
			}
		}
	}
	glEnd();
}

void draw_tank(float x, float y, float z)
{
	glPushMatrix();
	glTranslatef(x, y, z);

	//added by ccc for changing view
	//glRotatef(90, 0, 1, 0);
	//glRotatef(90, 0, 0, 1);

	glScalef(0.1, 0.1, 0.1);		//reduce the size of the tank on screen
									//DrawOBJ(tankBody->m_iMeshID);
	glCallList(tankBodyID); // task 1.2 question 3


							//Use your own draw code here to draw the rest of the tank
							//Here's the code for each individual part
							//Each part is placed with respect to the origin
							//you'll need to add in glPushMatrix/glTranslatef/glRotatef/glPopMatrix commands as necessary

							// turret rotation 
	glPushMatrix();
	glRotatef(turRot, 0, 1, 0); //rotation

								//draw tankTurret
	glPushMatrix();
	glTranslatef(x, y + 15.0, z);// x+-> right,y+->up,z+->toward you
	DrawOBJ(tankTurret->m_iMeshID);
	glPopMatrix();

	//draw main gun
	glPushMatrix();
	glTranslatef(x + 54.0, y - 88.0, z + 11.0);// x+-> right,y+->up,z+->toward you
	DrawOBJ(tankMainGun->m_iMeshID);
	glPopMatrix();

	//draw second gun
	glPushMatrix();
	glTranslatef(x - 12.0, y + 32.0, z - 5.0);// x+-> right,y+->up,z+->toward you
	glTranslatef(x, y, z - 9.0);  // translate the rotation center
	glRotatef(secGunRot, 0, 1, 0); //rotation
	glTranslatef(x, y, 9.0 - z);
	DrawOBJ(tankSecondaryGun->m_iMeshID);
	glPopMatrix();

	glPopMatrix();


	//draw wheels at left side
	for (int i = 0; i < 7; i++) {

		if (i < 6) {
			glPushMatrix();
			glTranslatef(x - 24.0, y - 11.0, z - 56.0 + 16 * i);
			glRotatef(wheeRot, 1, 0, 0);
			DrawOBJ(tankWheel->m_iMeshID);
			glPopMatrix();
		}
		else {
			glPushMatrix();
			glTranslatef(x - 24.0, y - 11.0, z - 56.0 + 16 * i + 4);
			glRotatef(wheeRot, 1, 0, 0);
			DrawOBJ(tankWheel->m_iMeshID);
			glPopMatrix();
		}
	}

	//draw wheels at right side
	for (int j = 0; j < 7; j++) {

		if (j == 0) {
			glPushMatrix();
			glRotatef(180, 0, 0, 1);
			glTranslatef(x - 24.0, y + 11.0, z - 16 * j + 44.0);
			glRotatef(wheeRot, -1, 0, 0);
			DrawOBJ(tankWheel->m_iMeshID);
			glPopMatrix();
		}
		else {
			glPushMatrix();
			glRotatef(180, 0, 0, 1);
			glTranslatef(x - 24.0, y + 11.0, z - 16 * j + 40.0);
			glRotatef(wheeRot, -1, 0, 0);
			DrawOBJ(tankWheel->m_iMeshID);
			glPopMatrix();
		}
	}

	glPopMatrix();
}

//draw low detail version of the tank model
void draw_tank_low(float x, float y, float z) {
	glPushMatrix();
	glTranslatef(x, y, z);

	//added by ccc for changing view
	//glRotatef(90, 0, 1, 0);
	//glRotatef(90, 0, 0, 1);

	glScalef(0.1, 0.1, 0.1);		//reduce the size of the tank on screen

	glDisable(GL_TEXTURE_2D); // disable lighting
	glDisable(GL_LIGHTING); // disable texturing
	glColor3ub(49, 41, 30); // select RGB colour ( 4 9 , 4 1 , 3 0 )
	DrawOBJ(tankBodyLow->m_iMeshID);

	//Use your own draw code here to draw the rest of the tank
	//Here's the code for each individual part
	//Each part is placed with respect to the origin
	//you'll need to add in glPushMatrix/glTranslatef/glRotatef/glPopMatrix commands as necessary

	// turret rotation 
	glPushMatrix();
	glRotatef(turRot, 0, 1, 0); //rotation

								//draw tankTurret
	glPushMatrix();
	glTranslatef(x, y + 15.0, z);// x+-> right,y+->up,z+->toward you
	DrawOBJ(tankTurretLow->m_iMeshID);
	glPopMatrix();

	//draw main gun
	glPushMatrix();
	glTranslatef(x + 54.0, y - 88.0, z + 11.0);// x+-> right,y+->up,z+->toward you
	DrawOBJ(tankMainGunLow->m_iMeshID);
	glPopMatrix();

	//draw second gun
	glPushMatrix();
	glTranslatef(x - 12.0, y + 32.0, z - 5.0);// x+-> right,y+->up,z+->toward you
	glTranslatef(x, y, z - 9.0);  // translate the rotation center
	glRotatef(secGunRot, 0, 1, 0); //rotation
	glTranslatef(x, y, 9.0 - z);
	DrawOBJ(tankSecondaryGunLow->m_iMeshID);
	glPopMatrix();

	glPopMatrix();


	//draw wheels at left side
	for (int i = 0; i < 7; i++) {

		if (i < 6) {
			glPushMatrix();
			glTranslatef(x - 24.0, y - 11.0, z - 56.0 + 16 * i);
			glRotatef(wheeRot, 1, 0, 0);
			DrawOBJ(tankWheelLow->m_iMeshID);
			glPopMatrix();
		}
		else {
			glPushMatrix();
			glTranslatef(x - 24.0, y - 11.0, z - 56.0 + 16 * i + 4);
			glRotatef(wheeRot, 1, 0, 0);
			DrawOBJ(tankWheelLow->m_iMeshID);
			glPopMatrix();
		}
	}

	//draw wheels at right side
	for (int j = 0; j < 7; j++) {

		if (j == 0) {
			glPushMatrix();
			glRotatef(180, 0, 0, 1);
			glTranslatef(x - 24.0, y + 11.0, z - 16 * j + 44.0);
			glRotatef(wheeRot, -1, 0, 0);
			DrawOBJ(tankWheelLow->m_iMeshID);
			glPopMatrix();
		}
		else {
			glPushMatrix();
			glRotatef(180, 0, 0, 1);
			glTranslatef(x - 24.0, y + 11.0, z - 16 * j + 40.0);
			glRotatef(wheeRot, -1, 0, 0);
			DrawOBJ(tankWheelLow->m_iMeshID);
			glPopMatrix();
		}
	}

	glEnable(GL_TEXTURE_2D); // enable lighting
	glEnable(GL_LIGHTING); // enable texturing

	glPopMatrix();


}

//draw callback function - this is called by glut whenever the 
//window needs to be redrawn
void draw(void)
{
	//clear the current window
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	//make changes to the modelview matrix
	glMatrixMode(GL_MODELVIEW);
	//initialise the modelview matrix to the identity matrix
	glLoadIdentity();

	glTranslatef(0.0, 0.0, zPos);

	glRotatef(yRot, 0.0, 1.0, 0.0);

	//draw the tank on screen at a position
	draw_tank(-5.0, 0.0, 0.0);
	//draw_BoundingSphere(0.0, 0.0, 0.0);
	//call_collideTest(0.0, 0.0, 0.0);
	draw_tank_low(5.0, 0.0, 0.0);

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
}

//key callback function - called whenever the user presses a 
//key
void key(unsigned char k, int x, int y)
{
	switch (k)
	{
	case 'd':
		zPos--;
		break;
	case 'e':
		zPos++;
		break;
	case 'r':
		yRot++;
		break;

	case 's': //ANTI-CLOCKWISE
		secGunRot++;
		break;
	case 'S': //CLOCKWISE
		secGunRot--;
		break;
	case 't':
		turRot++;
		break;
	case 'T':
		turRot--;
		break;
	case 'w':
		wheeRot++;
		break;
	case 'W':
		wheeRot--;
		break;

	case 'x':
		angleX++;
		break;
	case 'X':
		angleX--;
		break;
	case 'y':
		angleY++;
		break;
	case 'Y':
		angleY--;
		break;
	case 'z':
		angleZ++;
		break;
	case 'Z':
		angleZ--;
		break;

	case 27: //27 is the ASCII code for the ESCAPE key
		exit(0);
		break;
	}
	glutPostRedisplay();
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
	gluPerspective(45.0, (float)width / (float)height, 1.0, 1000.0);
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

	GLfloat specular[] = { 0.2,0.2,0.2,1.0 };
	GLfloat ambient[] = { 1.0,1.0,1.0,1.0 };
	GLfloat diffuse[] = { 1.0,1.0,1.0,1.0 };
	GLfloat position[] = { 0.0,30.0,0.0,1.0 };
	glLightfv(GL_LIGHT0, GL_SPECULAR, specular);
	glLightfv(GL_LIGHT0, GL_AMBIENT, ambient);
	glLightfv(GL_LIGHT0, GL_DIFFUSE, diffuse);
	glLightfv(GL_LIGHT0, GL_POSITION, position);
	glEnable(GL_LIGHTING);

	GLfloat position1[] = { 10.0,30.0,0.0,1.0 };
	glLightfv(GL_LIGHT1, GL_SPECULAR, specular);
	glLightfv(GL_LIGHT1, GL_AMBIENT, ambient);
	glLightfv(GL_LIGHT1, GL_DIFFUSE, diffuse);
	glLightfv(GL_LIGHT1, GL_POSITION, position1);
	glEnable(GL_LIGHT1);
	glEnable(GL_LIGHTING);

	glEnable(GL_COLOR_MATERIAL);
	glEnable(GL_TEXTURE_2D);
}

void initParticles(float x, float y, float z) {

g_pParticleSystems = new CParticleSystem();

//g_pParticleSystems[0]->SetTexture( "..\\particle.bmp" );
g_pParticleSystems->SetTexture("particle.bmp");
g_pParticleSystems->SetMaxParticles(100);
g_pParticleSystems->SetNumToRelease(100);
g_pParticleSystems->SetReleaseInterval(0.05f);
g_pParticleSystems->SetLifeCycle(0.5f);
g_pParticleSystems->SetSize(30.0f);
g_pParticleSystems->SetColor(MyVector(1.0f, 1.0f, 1.0f));
g_pParticleSystems->SetPosition(MyVector(x, y, z));
g_pParticleSystems->SetVelocity(MyVector(1.0f, 1.0f, 1.0f));
g_pParticleSystems->SetGravity(MyVector(.0f, 1.0f, 0.0f));
g_pParticleSystems->SetWind(MyVector(0.0f, 0.0f, 0.0f));
g_pParticleSystems->SetVelocityVar(10.0f);

g_pParticleSystems->Init();

}