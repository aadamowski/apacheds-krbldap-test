package pl.org.olo.krbldap.apacheds.extras.extended.ads_impl.krbLdap;

import org.apache.directory.shared.asn1.ber.grammar.States;

/**
 *
 */
public enum KrbLdapStatesEnum implements States {
    /**
     * The END_STATE
     */
    END_STATE,

    /**
     * start state
     */
    START_STATE;

    /**
     * {@inheritDoc}
     */
    public boolean isEndState() {
        return this == END_STATE;
    }

    /**
     * {@inheritDoc}
     */
    public Enum<?> getStartState() {
        return START_STATE;
    }
}
