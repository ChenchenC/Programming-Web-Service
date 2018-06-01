#pragma once

#include "objloader.h"
#include "myvector.h"


	class BoundingSphere
	{
	public:
		BoundingSphere();
		~BoundingSphere();

		BoundingSphere initialiseBoundingShphere(ObjMesh *pMesh);
		float calculateRadius(ObjMesh *pMesh, MyPosition pCenter, float radiusTemp);
		void resetCenter(float x, float y, float z);

		MyPosition center;
		float radius;
		MyPosition remoteP;
	};



