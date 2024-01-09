
package fr.insa.soap.wsdltojava;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fr.insa.soap.wsdltojava package. 
 * &lt;p&gt;An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AddUser_QNAME = new QName("http://soap.insa.fr/", "addUser");
    private final static QName _AddUserResponse_QNAME = new QName("http://soap.insa.fr/", "addUserResponse");
    private final static QName _GetAllUser_QNAME = new QName("http://soap.insa.fr/", "getAllUser");
    private final static QName _GetAllUserResponse_QNAME = new QName("http://soap.insa.fr/", "getAllUserResponse");
    private final static QName _GetAllUsername_QNAME = new QName("http://soap.insa.fr/", "getAllUsername");
    private final static QName _GetAllUsernameResponse_QNAME = new QName("http://soap.insa.fr/", "getAllUsernameResponse");
    private final static QName _GetUserById_QNAME = new QName("http://soap.insa.fr/", "getUserById");
    private final static QName _GetUserByIdResponse_QNAME = new QName("http://soap.insa.fr/", "getUserByIdResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fr.insa.soap.wsdltojava
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddUser }
     * 
     */
    public AddUser createAddUser() {
        return new AddUser();
    }

    /**
     * Create an instance of {@link AddUserResponse }
     * 
     */
    public AddUserResponse createAddUserResponse() {
        return new AddUserResponse();
    }

    /**
     * Create an instance of {@link GetAllUser }
     * 
     */
    public GetAllUser createGetAllUser() {
        return new GetAllUser();
    }

    /**
     * Create an instance of {@link GetAllUserResponse }
     * 
     */
    public GetAllUserResponse createGetAllUserResponse() {
        return new GetAllUserResponse();
    }

    /**
     * Create an instance of {@link GetAllUsername }
     * 
     */
    public GetAllUsername createGetAllUsername() {
        return new GetAllUsername();
    }

    /**
     * Create an instance of {@link GetAllUsernameResponse }
     * 
     */
    public GetAllUsernameResponse createGetAllUsernameResponse() {
        return new GetAllUsernameResponse();
    }

    /**
     * Create an instance of {@link GetUserById }
     * 
     */
    public GetUserById createGetUserById() {
        return new GetUserById();
    }

    /**
     * Create an instance of {@link GetUserByIdResponse }
     * 
     */
    public GetUserByIdResponse createGetUserByIdResponse() {
        return new GetUserByIdResponse();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddUser }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddUser }{@code >}
     */
    @XmlElementDecl(namespace = "http://soap.insa.fr/", name = "addUser")
    public JAXBElement<AddUser> createAddUser(AddUser value) {
        return new JAXBElement<AddUser>(_AddUser_QNAME, AddUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddUserResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddUserResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://soap.insa.fr/", name = "addUserResponse")
    public JAXBElement<AddUserResponse> createAddUserResponse(AddUserResponse value) {
        return new JAXBElement<AddUserResponse>(_AddUserResponse_QNAME, AddUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllUser }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetAllUser }{@code >}
     */
    @XmlElementDecl(namespace = "http://soap.insa.fr/", name = "getAllUser")
    public JAXBElement<GetAllUser> createGetAllUser(GetAllUser value) {
        return new JAXBElement<GetAllUser>(_GetAllUser_QNAME, GetAllUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllUserResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetAllUserResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://soap.insa.fr/", name = "getAllUserResponse")
    public JAXBElement<GetAllUserResponse> createGetAllUserResponse(GetAllUserResponse value) {
        return new JAXBElement<GetAllUserResponse>(_GetAllUserResponse_QNAME, GetAllUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllUsername }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetAllUsername }{@code >}
     */
    @XmlElementDecl(namespace = "http://soap.insa.fr/", name = "getAllUsername")
    public JAXBElement<GetAllUsername> createGetAllUsername(GetAllUsername value) {
        return new JAXBElement<GetAllUsername>(_GetAllUsername_QNAME, GetAllUsername.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllUsernameResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetAllUsernameResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://soap.insa.fr/", name = "getAllUsernameResponse")
    public JAXBElement<GetAllUsernameResponse> createGetAllUsernameResponse(GetAllUsernameResponse value) {
        return new JAXBElement<GetAllUsernameResponse>(_GetAllUsernameResponse_QNAME, GetAllUsernameResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserById }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetUserById }{@code >}
     */
    @XmlElementDecl(namespace = "http://soap.insa.fr/", name = "getUserById")
    public JAXBElement<GetUserById> createGetUserById(GetUserById value) {
        return new JAXBElement<GetUserById>(_GetUserById_QNAME, GetUserById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserByIdResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetUserByIdResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://soap.insa.fr/", name = "getUserByIdResponse")
    public JAXBElement<GetUserByIdResponse> createGetUserByIdResponse(GetUserByIdResponse value) {
        return new JAXBElement<GetUserByIdResponse>(_GetUserByIdResponse_QNAME, GetUserByIdResponse.class, null, value);
    }

}
