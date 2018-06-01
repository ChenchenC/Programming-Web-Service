#include "MyQuat.h"

///YOUR CODE HERE
MyQuat::MyQuat() {
	this->w = 0.0;
	this->v.x = this->v.y = this->v.z = 0.0;
}

MyQuat::MyQuat(float angleDeg, MyVector &axis) {
	this->w = angleDeg;
	this->v = axis;
}

MyQuat::MyQuat(MyPosition &p) {
	this->v.x = p.x;
	this->v.y = p.y;
	this->v.z = p.z;
}

MyQuat MyQuat::addTo(const MyQuat &other) const {
	MyQuat result;
	result.w = this->w + other.w;
	result.v.x = this->v.x + other.v.x;
	result.v.y = this->v.y + other.v.y;
	result.v.z = this->v.z + other.v.z;

	return result;
}

MyQuat MyQuat::multiplyBy(const MyQuat &other) const {
	MyQuat result;
	result.w = this->w*other.w - this->v.getDotProduct(other.v);
	result.v.x = this->v.getCrossProduct(other.v).x + this->w*other.v.x + other.w*this->v.x;
	result.v.y = this->v.getCrossProduct(other.v).y + this->w*other.v.y + other.w*this->v.y;
	result.v.z = this->v.getCrossProduct(other.v).z + this->w*other.v.z + other.w*this->v.z;

	return result;
}

float MyQuat::getMagnitude(void) const {
	float result;
	result = sqrt((this->v.x*this->v.x) + (this->v.y*this->v.y) + (this->v.z*this->v.z) + this->w*this->w);
	return result;
}
void MyQuat::normalise(void) {
	float length = getMagnitude();
	this->v.x = this->v.x / length;
	this->v.y = this->v.y / length;
	this->v.z = this->v.z / length;
	this->w = this->w / length;
}

MyQuat MyQuat::getConjugate(void) const {
	MyQuat result;
	result.w = this->w;
	result.v.x = (-1)*this->v.x;
	result.v.y = (-1)*this->v.y;
	result.v.z = (-1)*this->v.z;

	return result;
}

MyQuat MyQuat::getInverse(void) const {
	MyQuat result, cjg;
	float mgt = getMagnitude();
	cjg = getConjugate();
	result.w = cjg.w / (mgt*mgt);
	result.v.x = cjg.v.x / (mgt*mgt);
	result.v.y = cjg.v.y / (mgt*mgt);
	result.v.z = cjg.v.z / (mgt*mgt);

	return result;

}

MyMatrix MyQuat::convertToRotationMatrix(void) const {
	MyMatrix result;
	result.loadIdentity();

	double m00, m01, m02, m10, m11, m12, m20, m21, m22;

	double sqw = this->w*this->w;
	double sqx = this->v.x*this->v.x;
	double sqy = this->v.y*this->v.y;
	double sqz = this->v.z*this->v.z;

	// invs (inverse square length) is only required if quaternion is not already normalised
	double invs = 1 / (sqx + sqy + sqz + sqw);
	m00 = (sqx - sqy - sqz + sqw)*invs; // since sqw + sqx + sqy + sqz =1/invs*invs
	m11 = (-sqx + sqy - sqz + sqw)*invs;
	m22 = (-sqx - sqy + sqz + sqw)*invs;

	double tmp1 = this->v.x*this->v.y;
	double tmp2 = this->v.z*this->w;
	m10 = 2.0 * (tmp1 + tmp2)*invs;
	m01 = 2.0 * (tmp1 - tmp2)*invs;

	tmp1 = this->v.x*this->v.z;
	tmp2 = this->v.y*this->w;
	m20 = 2.0 * (tmp1 - tmp2)*invs;
	m02 = 2.0 * (tmp1 + tmp2)*invs;
	tmp1 = this->v.y*this->v.z;
	tmp2 = this->v.x*this->w;
	m21 = 2.0 * (tmp1 + tmp2)*invs;
	m12 = 2.0 * (tmp1 - tmp2)*invs;


	GLfloat tempM[16] = {
		m00, m01, m02, 0.0,
		m10, m11, m12, 0.0,
		m20, m21, m22, 0.0,
		0.0, 0.0, 0.0, 1.0
	};

	result.setMyMatrix(tempM);

	return result;
}