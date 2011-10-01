package pl.org.olo.krbldap.apacheds.test;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.directory.server.annotations.CreateKdcServer;
import org.apache.directory.server.annotations.CreateLdapServer;
import org.apache.directory.server.annotations.CreateTransport;
import org.apache.directory.server.annotations.SaslMechanism;
import org.apache.directory.server.core.annotations.ApplyLdifFiles;
import org.apache.directory.server.core.annotations.ContextEntry;
import org.apache.directory.server.core.annotations.CreateDS;
import org.apache.directory.server.core.annotations.CreateIndex;
import org.apache.directory.server.core.annotations.CreatePartition;
import org.apache.directory.server.core.integ.AbstractLdapTestUnit;
import org.apache.directory.server.core.integ.FrameworkRunner;
import org.apache.directory.server.core.kerberos.KeyDerivationInterceptor;
import org.apache.directory.server.ldap.LdapServer;
import org.apache.directory.server.ldap.handlers.bind.cramMD5.CramMd5MechanismHandler;
import org.apache.directory.server.ldap.handlers.bind.digestMD5.DigestMd5MechanismHandler;
import org.apache.directory.server.ldap.handlers.bind.gssapi.GssapiMechanismHandler;
import org.apache.directory.server.ldap.handlers.bind.ntlm.NtlmMechanismHandler;
import org.apache.directory.server.ldap.handlers.bind.plain.PlainMechanismHandler;
import org.apache.directory.shared.ldap.model.constants.SupportedSaslMechanisms;
import org.junit.Test;
import org.junit.runner.RunWith;
import pl.org.olo.krbldap.apacheds.handlers.extended.KrbLdapAuthServiceHandler;

/**
 * This test suite tests PAM-krbldap integration with ApacheDS-krbldap.
 */
@RunWith(FrameworkRunner.class)
@CreateDS(name = "SaslGssapiBindITest-class",
        allowAnonAccess = true,
        partitions = {@CreatePartition(
                name = "example",
                suffix = "dc=example,dc=com",
                contextEntry = @ContextEntry(
                        entryLdif = "dn: dc=example,dc=com\n" + "dc: example\n" + "objectClass: top\n" +
                                "objectClass: domain\n\n"),
                indexes = {@CreateIndex(attribute = "objectClass"), @CreateIndex(attribute = "dc"),
                        @CreateIndex(attribute = "ou"), @CreateIndex(attribute = "uid")})},
        additionalInterceptors = {KeyDerivationInterceptor.class})
@CreateLdapServer(
        transports = {@CreateTransport(protocol = "LDAP", port = 1389)},
        allowAnonymousAccess = true,
        extendedOpHandlers = KrbLdapAuthServiceHandler.class,
        saslHost = "localhost",
        saslPrincipal = "ldap/localhost@EXAMPLE.COM",
        saslMechanisms = {@SaslMechanism(name = SupportedSaslMechanisms.PLAIN, implClass = PlainMechanismHandler.class),
                @SaslMechanism(name = SupportedSaslMechanisms.CRAM_MD5, implClass = CramMd5MechanismHandler.class),
                @SaslMechanism(name = SupportedSaslMechanisms.DIGEST_MD5, implClass = DigestMd5MechanismHandler.class),
                @SaslMechanism(name = SupportedSaslMechanisms.GSSAPI, implClass = GssapiMechanismHandler.class),
                @SaslMechanism(name = SupportedSaslMechanisms.NTLM, implClass = NtlmMechanismHandler.class),
                @SaslMechanism(name = SupportedSaslMechanisms.GSS_SPNEGO, implClass = NtlmMechanismHandler.class)})
@CreateKdcServer(
        transports = {@CreateTransport(protocol = "UDP", port = 6088), @CreateTransport(protocol = "TCP", port = 6088)})
@ApplyLdifFiles("test.ldif")
public class KrbLdapIntegrationTest extends AbstractLdapTestUnit {
    public static LdapServer ldapServer;
    /**
     * Pathname of the client test shell script
     */
    private static final String CLIENT_TEST_SCRIPT = "/var/soft/PAM/run-tests-krbldap-direct.sh";
    /**
     * KRB5 conf file location relative to classpath
     */
    private static final String KRB5_CONF_RESOURCE_LOCATION = "krb5.conf";

    /**
     * Creates a new instance of SaslGssapiBindTest and sets JAAS system properties.
     */
    public KrbLdapIntegrationTest() {
        String krbConfPath = getClass().getClassLoader().getResource(KRB5_CONF_RESOURCE_LOCATION).getFile();
        System.setProperty("java.security.krb5.conf", krbConfPath);
        System.setProperty("sun.security.krb5.debug", "true");
    }


    @Test
    public void testShouldPerformSuccessfulAuthentication() throws Exception {

        final Process process = Runtime.getRuntime().exec(CLIENT_TEST_SCRIPT);
        final InputStream errorStream = process.getErrorStream();
        final InputStream inputStream = process.getInputStream();
        final int retValue = process.waitFor();
        System.out.println("Return code: [" + retValue + "]");
        System.out.println("STDOUT:");
        System.out.println(IOUtils.toString(inputStream));
        System.out.println("STDOUT END.");
        System.out.println("STDERR:");
        System.out.println(IOUtils.toString(errorStream));
        System.out.println("STDERR END.");
        System.out.println("Check your system's syslog (facility AUTH) for any messages from the PAM module.");
        if (retValue != 0) {
            throw new RuntimeException("error code [" + retValue + "] returned from process.");
        }
    }

}
