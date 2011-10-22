package pl.org.olo.krbldap.apacheds.extras.extended.ads_impl.krbLdap;

import java.nio.ByteBuffer;

import org.apache.directory.shared.asn1.Asn1Object;
import org.apache.directory.shared.asn1.DecoderException;
import org.apache.directory.shared.asn1.ber.Asn1Container;
import org.apache.directory.shared.asn1.ber.Asn1Decoder;
import org.apache.directory.shared.kerberos.codec.KerberosMessageContainer;

/**
 *
 */
public class KrbLdapDecoder extends Asn1Decoder {
    /**
     * The decoder
     */
    private static final Asn1Decoder decoder = new Asn1Decoder();

    public Asn1Object decode(byte[] stream) throws DecoderException {
        ByteBuffer bb = ByteBuffer.wrap(stream);
        final KerberosMessageContainer container = new KerberosMessageContainer();
        decoder.decode(bb, container);
        return container.getMessage();
    }
}
