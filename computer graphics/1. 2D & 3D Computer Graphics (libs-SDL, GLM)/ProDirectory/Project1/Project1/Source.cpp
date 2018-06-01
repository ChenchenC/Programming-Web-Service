// Introduction lab that covers:
// * C++
// * SDL
// * 2D graphics
// * Plotting pixels
// * Video memory
// * Color representation
// * Linear interpolation
// * glm::vec3 and std::vector

#include "SDL.h"
#include <iostream>
#include <glm/glm.hpp>
#include <vector>
#include "SDLauxiliary.h"

using namespace std;
using glm::vec3;

// --------------------------------------------------------
// GLOBAL VARIABLES

const int SCREEN_WIDTH = 640;
const int SCREEN_HEIGHT = 480;
SDL_Surface* screen;

vector<vec3> stars(1000);//task 3
int t = SDL_GetTicks();//task 3
double v = 0.0001;//task 3


// --------------------------------------------------------
// FUNCTION DECLARATIONS

void Draw2D();
void Draw3D();
void Interpolate(vec3 a, vec3 b, vector<vec3>& result);
void update();
void inializeStars();


// --------------------------------------------------------
// FUNCTION DEFINITIONS

int main(int argc, char* argv[])
{
	screen = InitializeSDL(SCREEN_WIDTH, SCREEN_HEIGHT);
	inializeStars();//task3
	while (NoQuitMessageSDL())
	{	 
		//Draw2D();
		Draw3D();
	}
	SDL_SaveBMP(screen, "screenshot.bmp");
	return 0;
}

void Interpolate(vec3 a, vec3 b, vector<vec3>& result) {
	float disX = (b.x - a.x) / (result.size() - 1);
	float disY = (b.y - a.y) / (result.size() - 1);
	float disZ = (b.z - a.z) / (result.size() - 1);

	//std::vector<vec3>::iterator it;

	for (int i = 0; i < result.size();i++) {
		float curX = a.x + disX*i;
		float curY = a.y + disY*i;
		float curZ = a.z + disZ*i;
		vec3 color(curX, curY, curZ);
		result[i] = color;
	}
}

void Draw2D()
{
	vec3 topLeft(1, 0, 0); // red
	vec3 topRight(0, 0, 1); // blue
	vec3 bottomLeft(0, 1, 0); // green
	vec3 bottomRight(1, 1, 0); // yellow

	vector<vec3> leftSide(SCREEN_HEIGHT);
	vector<vec3> rightSide(SCREEN_HEIGHT);

	Interpolate(topLeft, bottomLeft, leftSide);
	Interpolate(topRight, bottomRight, rightSide);

	for (int y = 0; y<SCREEN_HEIGHT; ++y)
	{
		vec3 leftPoint = leftSide[y];
		vec3 rightPoint = rightSide[y];
		vector<vec3> rowColorArr(SCREEN_WIDTH);
		Interpolate(leftPoint, rightPoint, rowColorArr);

		for (int x = 0; x<SCREEN_WIDTH; ++x)
		{
			PutPixelSDL(screen, x, y, rowColorArr[x]);
		}
	}

	if (SDL_MUSTLOCK(screen))
		SDL_UnlockSurface(screen);

	SDL_UpdateRect(screen, 0, 0, 0, 0);
}

void Draw3D()
{
	update();
	SDL_FillRect(screen, 0, 0);
	if (SDL_MUSTLOCK(screen))
		SDL_LockSurface(screen);
	for (size_t s = 0; s<stars.size(); ++s)
	{
		// Add code for projecting and drawing each star
		float x = stars[s].x;
		float y = stars[s].y;
		float z = stars[s].z;

		//projecting on the screen
		float f = SCREEN_HEIGHT / 2;
		int u = f*(x / z) + SCREEN_WIDTH / 2;
		int v = f*(y / z) + SCREEN_HEIGHT / 2;

		vec3 color = 0.2f * vec3(1, 1, 1) / (stars[s].z*stars[s].z);
		PutPixelSDL(screen, u, v, color);
	}

	if (SDL_MUSTLOCK(screen))
		SDL_UnlockSurface(screen);
	SDL_UpdateRect(screen, 0, 0, 0, 0);
}

void update() {
	int t2 = SDL_GetTicks();
	float dt = float(t2 - t);
	t = t2;

	for (int s = 0; s<stars.size(); ++s)
	{
		// Add code for update of stars
		if (stars[s].z <= 0)
			stars[s].z += 1;
		if (stars[s].z > 1)
			stars[s].z -= 1;
		stars[s].z = stars[s-1].z - v*dt;
	}
}

void inializeStars() {

	for (size_t s = 0; s<stars.size(); ++s)
	{
		// Add code for projecting and drawing each star
		// randomly get position of each star
		float x = float(rand()) / float(RAND_MAX);
		float y = float(rand()) / float(RAND_MAX);
		float z = float(rand()) / float(RAND_MAX);

		stars[s].x = x * pow(-1, floor(x * 10));
		stars[s].y = y * pow(-1, floor(y * 10));

		stars[s].z = z;
	}
}


