package pl.org.olo.krbldap.apacheds.extras.extended;

import org.apache.directory.shared.ldap.model.message.ExtendedRequestImpl;
import org.apache.directory.shared.ldap.model.message.LdapResult;

/**
 *
 */
public class KrbLdapResponseImpl extends ExtendedRequestImpl implements KrbLdapResponse {
    public KrbLdapResponseImpl() {
        setResponseName(EXTENSION_OID);
    }

    public String getResponseName() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setResponseName(String oid) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public LdapResult getLdapResult() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
