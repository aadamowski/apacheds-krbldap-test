package pl.org.olo.krbldap.apacheds.extras.extended;

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
        return null;
    }
}
