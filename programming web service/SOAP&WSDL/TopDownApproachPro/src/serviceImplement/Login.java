/**
 * Login.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package serviceImplement;

public interface Login extends java.rmi.Remote {
    public java.lang.String loginCheck(java.lang.String userName, java.lang.String password) throws java.rmi.RemoteException;
    public java.lang.String bookTicket(java.lang.String flightNumber, java.lang.String userId, java.lang.String creditCardNumber) throws java.rmi.RemoteException;
    public java.lang.String issueTicket(java.lang.String bookingNumber) throws java.rmi.RemoteException;
}
