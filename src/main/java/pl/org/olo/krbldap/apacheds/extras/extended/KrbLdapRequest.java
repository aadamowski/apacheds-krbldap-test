package pl.org.olo.krbldap.apacheds.extras.extended;

import org.apache.directory.shared.kerberos.messages.KerberosMessage;
import org.apache.directory.shared.ldap.model.message.ExtendedRequest;

/**
 * The interface for the KerbeLDAP extended operation request.
 *
 * @author <a href="mailto:aleksander.adamowski@gmail.com">Aleksander Adamowski</a>
 * @see <a href="http://www.ietf.org/rfc/rfc1510.txt">RFC 1510</a>
 */
public interface KrbLdapRequest extends ExtendedRequest<KrbLdapResponse> {
    public static final String EXTENSION_OID = KrbLdapResponse.EXTENSION_OID;

    KerberosMessage getKerberosMessage();

    void setKerberosMessage(KerberosMessage kerberosMessage);
}
