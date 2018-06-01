/**
 * LoginService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package serviceImplement;

public interface LoginService extends javax.xml.rpc.Service {
    public java.lang.String getLoginSOAPPortAddress();

    public serviceImplement.Login getLoginSOAPPort() throws javax.xml.rpc.ServiceException;

    public serviceImplement.Login getLoginSOAPPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
