package pl.org.olo.krbldap.apacheds.extras.extended.ads_impl.krbLdap;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

import org.apache.commons.io.HexDump;
import org.apache.directory.shared.asn1.Asn1Object;
import org.apache.directory.shared.asn1.DecoderException;
import org.apache.directory.shared.asn1.ber.Asn1Container;
import org.apache.directory.shared.asn1.ber.Asn1Decoder;
import org.apache.directory.shared.kerberos.codec.KerberosMessageContainer;
import org.bouncycastle.util.encoders.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class KrbLdapDecoder extends Asn1Decoder {
    private static final Logger LOG = LoggerFactory.getLogger(KrbLdapDecoder.class);
    /**
     * The decoder
     */
    private static final Asn1Decoder decoder = new Asn1Decoder();

    /**
     * Decodes the incoming byte stream to {@link org.apache.directory.shared.kerberos.messages.KerberosMessage}.
     *
     * @param stream
     * @return
     * @throws DecoderException
     */
    public Asn1Object decode(byte[] stream) throws DecoderException {
        LOG.debug(
                "KrbLDAP message data to be decoded: [" + new String(Hex.encode(stream), Charset.forName("US-ASCII")) +
                        "]");
        ByteBuffer bb = ByteBuffer.wrap(stream);
        final KerberosMessageContainer kerberosMessageContainer = new KerberosMessageContainer();
        kerberosMessageContainer.setStream(bb);
        kerberosMessageContainer.setGathering( true );
        decoder.decode(bb, kerberosMessageContainer);
        return kerberosMessageContainer.getMessage();
    }
}
