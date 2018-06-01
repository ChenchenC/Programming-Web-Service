/**
 * LoginSOAPSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package serviceImplement;

public class LoginSOAPSkeleton implements serviceImplement.Login, org.apache.axis.wsdl.Skeleton {
    private serviceImplement.Login impl;
    private static java.util.Map _myOperations = new java.util.Hashtable();
    private static java.util.Collection _myOperationsList = new java.util.ArrayList();

    /**
    * Returns List of OperationDesc objects with this name
    */
    public static java.util.List getOperationDescByName(java.lang.String methodName) {
        return (java.util.List)_myOperations.get(methodName);
    }

    /**
    * Returns Collection of OperationDescs
    */
    public static java.util.Collection getOperationDescs() {
        return _myOperationsList;
    }

    static {
        org.apache.axis.description.OperationDesc _oper;
        org.apache.axis.description.FaultDesc _fault;
        org.apache.axis.description.ParameterDesc [] _params;
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "userName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("loginCheck", _params, new javax.xml.namespace.QName("", "ifPermitted"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://serviceImplement", "loginCheck"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("loginCheck") == null) {
            _myOperations.put("loginCheck", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("loginCheck")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "flightNumber"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "userId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "creditCardNumber"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("bookTicket", _params, new javax.xml.namespace.QName("", "bookTicketReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://serviceImplement", "bookTicket"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("bookTicket") == null) {
            _myOperations.put("bookTicket", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("bookTicket")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "bookingNumber"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("issueTicket", _params, new javax.xml.namespace.QName("", "issueTicketReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://serviceImplement", "issueTicket"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("issueTicket") == null) {
            _myOperations.put("issueTicket", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("issueTicket")).add(_oper);
    }

    public LoginSOAPSkeleton() {
        this.impl = new serviceImplement.LoginSOAPImpl();
    }

    public LoginSOAPSkeleton(serviceImplement.Login impl) {
        this.impl = impl;
    }
    public java.lang.String loginCheck(java.lang.String userName, java.lang.String password) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.loginCheck(userName, password);
        return ret;
    }

    public java.lang.String bookTicket(java.lang.String flightNumber, java.lang.String userId, java.lang.String creditCardNumber) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.bookTicket(flightNumber, userId, creditCardNumber);
        return ret;
    }

    public java.lang.String issueTicket(java.lang.String bookingNumber) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.issueTicket(bookingNumber);
        return ret;
    }

}
