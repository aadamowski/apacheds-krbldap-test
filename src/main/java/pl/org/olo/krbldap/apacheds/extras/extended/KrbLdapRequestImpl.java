package pl.org.olo.krbldap.apacheds.extras.extended;

import org.apache.directory.shared.kerberos.messages.KerberosMessage;
import org.apache.directory.shared.ldap.model.message.AbstractExtendedRequest;

/**
 *
 */
public class KrbLdapRequestImpl extends AbstractExtendedRequest<KrbLdapResponse> implements KrbLdapRequest {

    private KerberosMessage kerberosMessage;

    public KrbLdapRequestImpl() {
        setRequestName(EXTENSION_OID);
    }

    public KerberosMessage getKerberosMessage() {
        return kerberosMessage;
    }

    public void setKerberosMessage(KerberosMessage kerberosMessage) {
        this.kerberosMessage = kerberosMessage;
    }

    @Override
    public KrbLdapResponse getResultResponse() {
        if (response == null) {
            response = new KrbLdapResponseImpl();
        }
        return response;
    }
}
