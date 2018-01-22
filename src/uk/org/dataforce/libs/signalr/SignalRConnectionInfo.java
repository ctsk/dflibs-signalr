/*
 *  Copyright 2018 Shane Mc Cormack <shanemcc@gmail.com>.
 *  See LICENSE for licensing details.
 */
package uk.org.dataforce.dflibs.signalr;

import com.google.common.base.Strings;

/**
 *
 * @author shane
 */
public class SignalRConnectionInfo {
    private final String connectionID;
    private final String connectionToken;
    private final String clientProtocol;

    public SignalRConnectionInfo(final String connectionID, final String connectionToken, final String clientProtocol) {
        this.connectionID = connectionID;
        this.connectionToken = connectionToken;
        this.clientProtocol = clientProtocol;
    }

    public String getClientProtocol() {
        return Strings.isNullOrEmpty(clientProtocol) ? "1.0" : clientProtocol;
    }

    public String getConnectionID() {
        return connectionID;
    }

    public String getConnectionToken() {
        return connectionToken;
    }
}
