#include "BoundingSphere.h"


BoundingSphere::BoundingSphere()
{
}


BoundingSphere::~BoundingSphere()
{
}

BoundingSphere BoundingSphere::initialiseBoundingShphere(ObjMesh *pMesh) {

	BoundingSphere result;
	int vNum = pMesh->m_iNumberOfVertices;
	float avx, avy, avz;
	avx = avy = avz = 0.0;
	MyPosition vp_avg; // average vertex over all the vertices
	for (int i = 0; i < vNum; i++) {
		avx += pMesh->m_aVertexArray[i].x;
		avy += pMesh->m_aVertexArray[i].y;
		avz += pMesh->m_aVertexArray[i].z;
	}
	vp_avg.x = avx / vNum;
	vp_avg.y = avy / vNum;
	vp_avg.z = avz / vNum;
	result.center = vp_avg; // sphere center

	float tempR = 0;
	MyPosition vp, rp;

	for (int i = 0; i < vNum; i++) {
		vp.x = pMesh->m_aVertexArray[i].x;
		vp.y = pMesh->m_aVertexArray[i].y;
		vp.z = pMesh->m_aVertexArray[i].z;
		MyVector dist = MyVector(vp, vp_avg);
		if (tempR < dist.getMagnitude()) {
			tempR = dist.getMagnitude();
			rp = vp;
		}
	}
	result.radius = tempR; // shere radius
	result.remoteP = rp; // the most remote point from object center

	return result;
}

float BoundingSphere::calculateRadius(ObjMesh *pMesh, MyPosition pCenter, float radiusTemp) {
	float result = 0.0;
	int vNum = pMesh->m_iNumberOfVertices;

	MyPosition vp;
	for (int i = 0; i < vNum; i++) {
		vp.x = pMesh->m_aVertexArray[i].x;
		vp.y = pMesh->m_aVertexArray[i].y;
		vp.z = pMesh->m_aVertexArray[i].z;
		MyVector dist = MyVector(vp, pCenter);
		if (result < dist.getMagnitude()) {
			result = dist.getMagnitude();
		}
	}
	if (result > radiusTemp) {
		return result;
	}
	else {
		return radiusTemp;
	}
}

void BoundingSphere::resetCenter(float x, float y, float z) {

	this->center.x += x;
	this->center.y += y;
	this->center.z += z;
}