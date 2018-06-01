#include "MyVector.h"

MyVector::MyVector(void)
{
	this->x = this->y = this->z = 0.0;
}

MyVector::MyVector(float x, float y, float z)
{
	this->x = x; this->y = y; this->z = z;
}

MyVector::MyVector(MyPosition& start, MyPosition& end)
{
	this->x = end.x - start.x;
	this->y = end.y - start.y;
	this->z = end.z - start.z;
}

MyVector MyVector::addTo(const MyVector &other) const
{
	MyVector result;
	//your code here
	result.x = other.x + this->x;
	result.y = other.y + this->y;
	result.z = other.z + this->z;
	return result;
}

MyVector MyVector::subtractFrom(const MyVector &other) const
{
	MyVector result;
	//your code here
	result.x = other.x - this->x;
	result.y = other.y - this->y;
	result.z = other.z - this->z;
	return result;
}

float MyVector::getMagnitude(void) const
{
	float result;
	//your code here
	result = sqrt((this->x*this->x) + (this->y*this->y) + (this->z*this->z));
	return result;
}

void MyVector::uniformScale(const float m)
{
	//your code here
	this->x = this->x*m;
	this->y = this->y*m;
	this->z = this->z*m;
}

void MyVector::normalise(void)
{
	//your code here
	float length;
	length = sqrt((this->x*this->x) + (this->y*this->y) + (this->z*this->z));
	this->x = this->x / length;
	this->y = this->y / length;
	this->z = this->z / length;
}


void MyVector::setMagnitude(const float m)
{
	if (this->getMagnitude() > 0.0)
	{
		this->normalise();
		this->uniformScale(m);
	}
}
	
float MyVector::getDotProduct(const MyVector &other) const
{
	float result;
	//your code here
	result = other.x*this->x + other.y*this->y + other.z*this->z;
	return result;
}

MyVector MyVector::getCrossProduct(const MyVector &other) const
{
	MyVector result;
	//your code here
	result.x = other.y*this->z - other.z*this->y;
	result.y = other.z*this->x - other.x*this->z;
	result.z = other.x*this->y - other.y*this->x;
	return result;
}

MyVector MyVector::operator + ( const MyVector &other )
{
    MyVector vResult(0.0f, 0.0f, 0.0f);

    vResult.x = x + other.x;
    vResult.y = y + other.y;
    vResult.z = z + other.z;

    return vResult;
}

MyVector MyVector::operator + ( void ) const
{
    return *this;
}

MyVector MyVector::operator - ( const MyVector &other )
{
    MyVector vResult(0.0f, 0.0f, 0.0f);

    vResult.x = x - other.x;
    vResult.y = y - other.y;
    vResult.z = z - other.z;

    return vResult;
}

MyVector MyVector::operator - ( void ) const
{
    MyVector vResult(-x, -y, -z);

    return vResult;
}

MyVector MyVector::operator * ( const MyVector &other )
{
    MyVector vResult(0.0f, 0.0f, 0.0f);

    vResult.x = x * other.x;
    vResult.y = y * other.y;
    vResult.z = z * other.z;

    return vResult;
}

MyVector MyVector::operator * ( const float scalar )
{
    MyVector vResult(0.0f, 0.0f, 0.0f);

    vResult.x = x * scalar;
    vResult.y = y * scalar;
    vResult.z = z * scalar;

    return vResult;
}

MyVector operator * ( const float scalar, const MyVector &other )
{
    MyVector vResult(0.0f, 0.0f, 0.0f);

    vResult.x = other.x * scalar;
    vResult.y = other.y * scalar;
    vResult.z = other.z * scalar;

    return vResult;
}

MyVector MyVector::operator / ( const MyVector &other )
{
    MyVector vResult(0.0f, 0.0f, 0.0f);

    vResult.x = x / other.x;
    vResult.y = y / other.y;
    vResult.z = z / other.z;

    return vResult;
}

MyVector& MyVector::operator = ( const MyVector &other )
{
	x = other.x;
	y = other.y;
	z = other.z;

    return *this;
}

MyVector& MyVector::operator += ( const MyVector &other )
{
    x += other.x;
    y += other.y;
    z += other.z;

    return *this;
}

MyVector& MyVector::operator -= ( const MyVector &other )
{
    x -= other.x;
    y -= other.y;
    z -= other.z;

    return *this;
}