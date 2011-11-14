package pl.org.olo.krbldap.apacheds.extras.extended;

import javax.naming.ldap.ExtendedResponse;

import org.apache.directory.shared.ldap.model.message.ExtendedRequestImpl;
import org.apache.directory.shared.ldap.model.message.ExtendedResponseImpl;
import org.apache.directory.shared.ldap.model.message.LdapResult;
import org.apache.directory.shared.ldap.model.message.ResultCodeEnum;

/**
 *
 */
public class KrbLdapResponseImpl extends ExtendedResponseImpl implements KrbLdapResponse {
    public KrbLdapResponseImpl(int messageId) {
        super(messageId, EXTENSION_OID);
    }

    public KrbLdapResponseImpl() {
        super(EXTENSION_OID);

    }

}
