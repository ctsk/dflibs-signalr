/*
 *  Copyright 2018 Shane Mc Cormack <shanemcc@gmail.com>.
 *  See LICENSE for licensing details.
 */
package uk.org.dataforce.libs.signalr;

/**
 *
 * @author shane
 */
public interface SignalRHandler {
    public void handle(final SignalRClient client, final SignalRMessage message);
    public void connectionClosed(final SignalRClient client);
    public void keepalive(final SignalRClient client);
}
