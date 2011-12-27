/*
 *   Licensed to the Apache Software Foundation (ASF) under one
 *   or more contributor license agreements.  See the NOTICE file
 *   distributed with this work for additional information
 *   regarding copyright ownership.  The ASF licenses this file
 *   to you under the Apache License, Version 2.0 (the
 *   "License"); you may not use this file except in compliance
 *   with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing,
 *   software distributed under the License is distributed on an
 *   "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *   KIND, either express or implied.  See the License for the
 *   specific language governing permissions and limitations
 *   under the License.
 *
 */
package pl.org.olo.krbldap.apacheds.handlers.extended;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.directory.server.ldap.ExtendedOperationHandler;
import org.apache.directory.server.ldap.LdapServer;
import org.apache.directory.server.ldap.LdapSession;
import org.apache.directory.shared.ldap.model.exception.LdapProtocolErrorException;
import org.apache.directory.shared.ldap.model.message.ExtendedRequest;
import org.apache.directory.shared.ldap.model.message.ExtendedResponse;
import org.apache.directory.shared.ldap.model.message.LdapResult;
import org.apache.directory.shared.ldap.model.message.ResultCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.org.olo.krbldap.apacheds.extras.extended.KrbLdapRequest;
import pl.org.olo.krbldap.apacheds.extras.extended.KrbLdapResponse;

/**
 * Handler for the KrbLDAP Authentication Service request (Kerberos' AS-REQ).
 *
 * @author <a href="mailto:aleksander.adamowski@gmail.com">Aleksander Adamowski</a>
 * @see <a href="http://www.ietf.org/rfc/rfc1510.txt">RFC 1510</a>
 */
public class KrbLdapAuthServiceHandler implements ExtendedOperationHandler<KrbLdapRequest, KrbLdapResponse> {
    // ------------------------------ FIELDS ------------------------------

    private static final Set<String> EXTENSION_OIDS;
    private static final Logger LOG = LoggerFactory.getLogger(KrbLdapAuthServiceHandler.class);

    private LdapServer ldapServer;

    // -------------------------- STATIC METHODS --------------------------

    static {
        Set<String> set = new HashSet<String>(3);
        set.add(KrbLdapRequest.EXTENSION_OID);
        set.add(KrbLdapResponse.EXTENSION_OID);
        EXTENSION_OIDS = Collections.unmodifiableSet(set);
    }

    // --------------------- GETTER / SETTER METHODS ---------------------

    public void setLdapServer(LdapServer ldapServer) {
        LOG.debug("Setting LDAP Service");
        this.ldapServer = ldapServer;
    }

    // ------------------------ INTERFACE METHODS ------------------------


    // --------------------- Interface ExtendedOperationHandler ---------------------

    public String getOid() {
        return KrbLdapResponse.EXTENSION_OID;
    }

    public Set<String> getExtensionOids() {
        return EXTENSION_OIDS;
    }

    public void handleExtendedOperation(LdapSession session, KrbLdapRequest req) throws Exception {
        LOG.info("Handling KrbLdap AS request.");
        if (LOG.isDebugEnabled()) {
            LOG.debug("LdapSession: [" + session.toString() + "]");
            LOG.debug("ExtendedRequest: [" + req.toString() + "]");
            LOG.debug("KerberosMessage contained in ExtendedRequest: " + req.getKerberosMessage());
            LOG.debug("ldapServer available: " + this.ldapServer);
            /** TODO: perform message processing similar in behaviour to
             * {@link org.apache.directory.server.kerberos.protocol.KerberosProtocolHandler#messageReceived}
             */

            final ExtendedResponse resultResponse = req.getResultResponse();
            if (resultResponse == null) {
                final String message = "Request has no resultResponse!";
                LOG.error(message + " request is: " + req.toString());
                throw new LdapProtocolErrorException(message);
            }

            final LdapResult ldapResult = resultResponse.getLdapResult();
            if (ldapResult == null) {
                final String message = "Response has no ldapResult!";
                LOG.error(message + " response is: " + resultResponse.toString());
                throw new LdapProtocolErrorException(message);
            }

            ldapResult.setResultCode(ResultCodeEnum.SUCCESS);
            session.getIoSession().write(resultResponse);
        }
    }
}
