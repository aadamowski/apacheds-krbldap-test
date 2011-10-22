package pl.org.olo.krbldap.apacheds.extras.extended.ads_impl.krbLdap;

import org.apache.directory.shared.asn1.DecoderException;
import org.apache.directory.shared.kerberos.messages.KerberosMessage;
import org.apache.directory.shared.ldap.codec.api.ExtendedRequestDecorator;
import org.apache.directory.shared.ldap.codec.api.LdapApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.org.olo.krbldap.apacheds.extras.extended.KrbLdapRequest;
import pl.org.olo.krbldap.apacheds.extras.extended.KrbLdapResponse;

/**
 *
 */
public class KrbLdapRequestDecorator extends ExtendedRequestDecorator<KrbLdapRequest, KrbLdapResponse>
        implements KrbLdapRequest {
    private static final Logger LOG = LoggerFactory.getLogger(KrbLdapRequestDecorator.class);

    /**
     * Makes a ExtendedRequest a MessageDecorator.
     *
     * @param decoratedMessage the decorated ExtendedRequest
     */
    public KrbLdapRequestDecorator(LdapApiService codec, KrbLdapRequest decoratedMessage) {
        super(codec, decoratedMessage);
    }

    @Override
    public void setRequestValue(byte[] requestValue) {
        final KrbLdapDecoder decoder = new KrbLdapDecoder();
        try {
            final KerberosMessage kerberosMessage = (KerberosMessage) decoder.decode(requestValue);
            LOG.info("kerberosMessage: [" + kerberosMessage + "]");
        } catch (DecoderException e) {
            LOG.error("Error decoding KerbeLDAP message: " + e.getMessage(), e);
        }
    }
}
