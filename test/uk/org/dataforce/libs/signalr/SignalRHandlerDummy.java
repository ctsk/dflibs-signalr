package uk.org.dataforce.libs.signalr;

public class SignalRHandlerDummy implements SignalRHandler {

    @Override
    public void handle(SignalRClient client, SignalRMessage message) {
        System.out.println(message.getArgs().get(0).toString());
    }

    @Override
    public void connectionClosed(SignalRClient client) {

    }

    @Override
    public void connectionAborted(SignalRClient client) {

    }

    @Override
    public void keepalive(SignalRClient client) {
        System.out.println("Hi");
    }
}
