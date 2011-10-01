package pl.org.olo.krbldap.apacheds.extras.extended.ads_impl.krbLdap;

import org.apache.directory.shared.ldap.codec.api.ExtendedRequestDecorator;
import org.apache.directory.shared.ldap.codec.api.LdapApiService;
import pl.org.olo.krbldap.apacheds.extras.extended.KrbLdapRequest;
import pl.org.olo.krbldap.apacheds.extras.extended.KrbLdapResponse;

/**
 *
 */
public class KrbLdapRequestDecorator extends ExtendedRequestDecorator<KrbLdapRequest, KrbLdapResponse>
        implements KrbLdapRequest {
    /**
     * Makes a ExtendedRequest a MessageDecorator.
     *
     * @param decoratedMessage the decorated ExtendedRequest
     */
    public KrbLdapRequestDecorator(LdapApiService codec, KrbLdapRequest decoratedMessage) {
        super(codec, decoratedMessage);
    }
}
