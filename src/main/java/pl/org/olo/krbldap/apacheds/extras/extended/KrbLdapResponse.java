package pl.org.olo.krbldap.apacheds.extras.extended;

import org.apache.directory.shared.kerberos.messages.KerberosMessage;
import org.apache.directory.shared.ldap.model.message.ExtendedResponse;

/**
 * The interface for the KerbeLDAP extended operation response.
 *
 * @author <a href="mailto:aleksander.adamowski@gmail.com">Aleksander Adamowski</a>
 * @see <a href="http://www.ietf.org/rfc/rfc1510.txt">RFC 1510</a>
 */
public interface KrbLdapResponse extends ExtendedResponse {
    public static final String EXTENSION_OID = "1.3.6.1.4.1.38261.1.1";

    KerberosMessage getKerberosReply();

    void setKerberosReply(KerberosMessage kerberosReply);
}
