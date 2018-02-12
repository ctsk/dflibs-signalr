/*
 *  Copyright 2018 Shane Mc Cormack <shanemcc@gmail.com>.
 *  See LICENSE.txt for licensing details.
 */
package uk.org.dataforce.libs.signalr;

import java.util.List;

/**
 * @deprecated Experimental, might be removed in future, don't rely on this.
 * @author Shane Mc Cormack <shanemcc@gmail.com>
 */
@Deprecated
public interface SignalRMultiHandler extends SignalRHandler {
    public void multihandle(final SignalRClient client, final List<SignalRMessage> message);
}
