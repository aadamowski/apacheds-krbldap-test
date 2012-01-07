package pl.org.olo.krbldap.apacheds.extras.extended.ads_impl.krbLdap;

import org.apache.directory.shared.kerberos.messages.KerberosMessage;
import org.apache.directory.shared.ldap.codec.api.ExtendedResponseDecorator;
import org.apache.directory.shared.ldap.codec.api.LdapApiService;
import pl.org.olo.krbldap.apacheds.extras.extended.KrbLdapResponse;

/**
 *
 */
public class KrbLdapResponseDecorator extends ExtendedResponseDecorator<KrbLdapResponse> implements KrbLdapResponse {
// ------------------------------ FIELDS ------------------------------

    private KerberosMessage kerberosReply;

// --------------------------- CONSTRUCTORS ---------------------------

    /**
     * Makes a ExtendedResponse encodable.
     *
     * @param decoratedMessage the decorated ExtendedResponse
     */
    public KrbLdapResponseDecorator(LdapApiService codec, KrbLdapResponse decoratedMessage) {
        super(codec, decoratedMessage);
    }

// --------------------- GETTER / SETTER METHODS ---------------------

    public KerberosMessage getKerberosReply() {
        return kerberosReply;
    }

    public void setKerberosReply(KerberosMessage kerberosReply) {
        this.kerberosReply = kerberosReply;
    }
}
