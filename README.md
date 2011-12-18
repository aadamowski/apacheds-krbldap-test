
# ApacheDS KrbLDAP proof of concept implementation and test suite

  ApacheDS-based server side implementation of KrbLDAP protocol (Kerberos v5 using LDAP extended operations as carrier protocol).

  Encloses:

  * ApacheDS extended operation handler implementation that takes care of extracting Kerberos v5 messages from the LDAP extended operation message and feeding it to the ApacheDS Kerberos KDC / TGS service
  * Test suite that launches customized pam-krb5 module's tests against a KrbLDAP-ized test instance of the ApacheDS server.

  The test suite requires a customized build of pam-krb5 PAM module, which acts as a KrbLDAP client. It's available on Github as [pam_krb5-krbldap](http://github.com/aadamowski/pam_krb5-krbldap).

  The pam_krb5-krbldap itself requires a customized version of MIT Kerberos v5 library, which is also present on Github as [krb5-krbldap](http://github.com/aadamowski/krb5-krbldap).

