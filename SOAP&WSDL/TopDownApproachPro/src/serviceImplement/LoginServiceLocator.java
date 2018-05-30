/**
 * LoginServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package serviceImplement;

public class LoginServiceLocator extends org.apache.axis.client.Service implements serviceImplement.LoginService {

    public LoginServiceLocator() {
    }


    public LoginServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public LoginServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for LoginSOAPPort
    private java.lang.String LoginSOAPPort_address = "http://localhost:8080/TopDownService1/services/Login";

    public java.lang.String getLoginSOAPPortAddress() {
        return LoginSOAPPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String LoginSOAPPortWSDDServiceName = "LoginSOAPPort";

    public java.lang.String getLoginSOAPPortWSDDServiceName() {
        return LoginSOAPPortWSDDServiceName;
    }

    public void setLoginSOAPPortWSDDServiceName(java.lang.String name) {
        LoginSOAPPortWSDDServiceName = name;
    }

    public serviceImplement.Login getLoginSOAPPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(LoginSOAPPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getLoginSOAPPort(endpoint);
    }

    public serviceImplement.Login getLoginSOAPPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            serviceImplement.LoginSOAPStub _stub = new serviceImplement.LoginSOAPStub(portAddress, this);
            _stub.setPortName(getLoginSOAPPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setLoginSOAPPortEndpointAddress(java.lang.String address) {
        LoginSOAPPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (serviceImplement.Login.class.isAssignableFrom(serviceEndpointInterface)) {
                serviceImplement.LoginSOAPStub _stub = new serviceImplement.LoginSOAPStub(new java.net.URL(LoginSOAPPort_address), this);
                _stub.setPortName(getLoginSOAPPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("LoginSOAPPort".equals(inputPortName)) {
            return getLoginSOAPPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://serviceImplement", "LoginService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://serviceImplement", "LoginSOAPPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("LoginSOAPPort".equals(portName)) {
            setLoginSOAPPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
