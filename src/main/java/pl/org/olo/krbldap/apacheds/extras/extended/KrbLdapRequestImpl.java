package pl.org.olo.krbldap.apacheds.extras.extended;

import org.apache.directory.shared.ldap.extras.extended.CancelResponseImpl;
import org.apache.directory.shared.ldap.model.message.AbstractExtendedRequest;

/**
 *
 */
public class KrbLdapRequestImpl extends AbstractExtendedRequest<KrbLdapResponse> implements KrbLdapRequest {

    public KrbLdapRequestImpl() {
        setRequestName(EXTENSION_OID);
    }

    @Override
    public KrbLdapResponse getResultResponse() {
        if (response == null) {
            response = new KrbLdapResponseImpl();
        }
        return response;
    }
}
