package pl.org.olo.krbldap.apacheds.extras.extended.ads_impl.krbLdap;

import org.apache.directory.shared.asn1.DecoderException;
import org.apache.directory.shared.ldap.codec.api.ExtendedRequestDecorator;
import org.apache.directory.shared.ldap.codec.api.ExtendedRequestFactory;
import org.apache.directory.shared.ldap.codec.api.ExtendedResponseDecorator;
import org.apache.directory.shared.ldap.model.message.ExtendedRequest;
import org.apache.directory.shared.ldap.model.message.ExtendedResponse;
import pl.org.olo.krbldap.apacheds.extras.extended.KrbLdapRequest;
import pl.org.olo.krbldap.apacheds.extras.extended.KrbLdapResponse;

/**
 *
 */
public class KrbLdapFactory implements ExtendedRequestFactory<KrbLdapRequest, KrbLdapResponse> {
    public String getOid() {
        return KrbLdapResponse.EXTENSION_OID;
    }

    public KrbLdapRequest newRequest() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public KrbLdapRequest newRequest(byte[] value) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public ExtendedRequestDecorator<KrbLdapRequest, KrbLdapResponse> decorate(ExtendedRequest<?> modelRequest) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public KrbLdapResponse newResponse(byte[] encodedValue) throws DecoderException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public ExtendedResponseDecorator<KrbLdapResponse> decorate(ExtendedResponse decoratedMessage) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
