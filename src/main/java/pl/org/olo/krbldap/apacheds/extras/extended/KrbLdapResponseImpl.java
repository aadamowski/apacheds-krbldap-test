package pl.org.olo.krbldap.apacheds.extras.extended;

import javax.naming.ldap.ExtendedResponse;

import org.apache.directory.shared.kerberos.messages.KerberosMessage;
import org.apache.directory.shared.ldap.model.message.ExtendedRequestImpl;
import org.apache.directory.shared.ldap.model.message.ExtendedResponseImpl;
import org.apache.directory.shared.ldap.model.message.LdapResult;
import org.apache.directory.shared.ldap.model.message.ResultCodeEnum;

/**
 *
 */
public class KrbLdapResponseImpl extends ExtendedResponseImpl implements KrbLdapResponse {
    // ------------------------------ FIELDS ------------------------------

    private KerberosMessage kerberosReply;

    // --------------------------- CONSTRUCTORS ---------------------------

    public KrbLdapResponseImpl() {
        super(EXTENSION_OID);
    }

    public KrbLdapResponseImpl(int messageId) {
        super(messageId, EXTENSION_OID);
    }

    // --------------------- GETTER / SETTER METHODS ---------------------

    public KerberosMessage getKerberosReply() {
        return kerberosReply;
    }

    public void setKerberosReply(KerberosMessage kerberosReply) {
        this.kerberosReply = kerberosReply;
    }

    @Override
    public String toString() {
        return "KrbLdapResponseImpl{" +
                "kerberosReply=" + kerberosReply +
                '}';
    }
}
