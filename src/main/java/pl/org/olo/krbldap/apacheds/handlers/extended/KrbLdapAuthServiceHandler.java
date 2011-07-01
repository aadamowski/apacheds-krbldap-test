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
import org.apache.directory.shared.ldap.model.message.ExtendedRequest;
import org.apache.directory.shared.ldap.model.message.ExtendedResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handler for the KrbLDAP Authentication Service request (Kerberos' AS-REQ).
 *
 * @author <a href="mailto:aleksander.adamowski@gmail.com">Aleksander Adamowski</a>
 * @see <a href="http://www.ietf.org/rfc/rfc1510.txt">RFC 1510</a>
 */
public class KrbLdapAuthServiceHandler
        implements ExtendedOperationHandler<ExtendedRequest<ExtendedResponse>, ExtendedResponse> {
    public static final String EXTENSION_OID = "1.3.6.1.4.1.38261.1.1";
    private static final Set<String> EXTENSION_OIDS;

    private static final Logger LOG = LoggerFactory.getLogger(KrbLdapAuthServiceHandler.class);
    private LdapServer ldapServer;

    static {
        Set<String> set = new HashSet<String>(3);
        set.add(EXTENSION_OID);
        EXTENSION_OIDS = Collections.unmodifiableSet(set);
    }

    public String getOid() {
        return EXTENSION_OID;
    }

    public Set<String> getExtensionOids() {
        return EXTENSION_OIDS;
    }


    //    public void handleExtendedOperation(LdapSession ldapSession, InternalExtendedRequest internalExtendedRequest)
    //            throws Exception {
    //        LOG.info("Handling KrbLdap AS request.");
    //        if (LOG.isDebugEnabled()) {
    //            LOG.debug("LdapSession: [" + ldapSession.toString() + "]");
    //            LOG.debug("InternalExtendedRequest: [" + internalExtendedRequest.toString() + "]");
    //        }
    //    }

    public void handleExtendedOperation(LdapSession session, ExtendedRequest<ExtendedResponse> req) throws Exception {
        LOG.info("Handling KrbLdap AS request.");
        if (LOG.isDebugEnabled()) {
            LOG.debug("LdapSession: [" + session.toString() + "]");
            LOG.debug("ExtendedRequest: [" + req.toString() + "]");
            //  final ExtendedResponse resultResponse = req.getResultResponse();
        }
    }

    public void setLdapServer(LdapServer ldapServer) {
        LOG.debug("Setting LDAP Service");
        this.ldapServer = ldapServer;
    }
}
