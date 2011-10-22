package pl.org.olo.krbldap.apacheds.extras.extended.ads_impl.krbLdap;

import org.apache.directory.shared.asn1.DecoderException;
import org.apache.directory.shared.ldap.codec.api.ExtendedRequestDecorator;
import org.apache.directory.shared.ldap.codec.api.ExtendedRequestFactory;
import org.apache.directory.shared.ldap.codec.api.ExtendedResponseDecorator;
import org.apache.directory.shared.ldap.codec.api.LdapApiService;
import org.apache.directory.shared.ldap.model.message.ExtendedRequest;
import org.apache.directory.shared.ldap.model.message.ExtendedResponse;
import pl.org.olo.krbldap.apacheds.extras.extended.KrbLdapRequest;
import pl.org.olo.krbldap.apacheds.extras.extended.KrbLdapRequestImpl;
import pl.org.olo.krbldap.apacheds.extras.extended.KrbLdapResponse;
import pl.org.olo.krbldap.apacheds.extras.extended.KrbLdapResponseImpl;

/**
 *
 */
public class KrbLdapFactory implements ExtendedRequestFactory<KrbLdapRequest, KrbLdapResponse> {
    private LdapApiService ldapApiService;

    public KrbLdapFactory(LdapApiService ldapApiService) {
        this.ldapApiService = ldapApiService;
    }

    public String getOid() {
        return KrbLdapResponse.EXTENSION_OID;
    }

    public KrbLdapRequest newRequest() {
        return new KrbLdapRequestDecorator(ldapApiService, new KrbLdapRequestImpl());
    }

    public KrbLdapRequest newRequest(byte[] value) {
        final KrbLdapRequestDecorator req = new KrbLdapRequestDecorator(ldapApiService, new KrbLdapRequestImpl());
        req.setRequestValue(value);
        return req;
    }

    public ExtendedRequestDecorator<KrbLdapRequest, KrbLdapResponse> decorate(ExtendedRequest<?> modelRequest) {
        if (modelRequest instanceof KrbLdapRequestDecorator) {
            return (KrbLdapRequestDecorator) modelRequest;
        }

        return new KrbLdapRequestDecorator(ldapApiService, (KrbLdapRequest) modelRequest);
    }

    public KrbLdapResponse newResponse(byte[] encodedValue) throws DecoderException {
        KrbLdapResponseDecorator response = new KrbLdapResponseDecorator(ldapApiService, new KrbLdapResponseImpl());
        response.setResponseValue(encodedValue);
        return response;
    }

    public ExtendedResponseDecorator<KrbLdapResponse> decorate(ExtendedResponse decoratedMessage) {
        if (decoratedMessage instanceof KrbLdapResponseDecorator) {
            return (KrbLdapResponseDecorator) decoratedMessage;
        }

        return new KrbLdapResponseDecorator(ldapApiService, (KrbLdapResponse) decoratedMessage);
    }
}
