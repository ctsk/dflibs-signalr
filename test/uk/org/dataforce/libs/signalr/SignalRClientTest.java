package uk.org.dataforce.libs.signalr;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.launchdarkly.eventsource.MessageEvent;
import com.sun.xml.internal.ws.api.message.Message;
import okhttp3.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Runner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class SignalRClientTest {

    private SignalRClient signalRClient;
    private OkHttpClient okHttpClient;

    @Before
    public void setUp() throws Exception {
        okHttpClient = new OkHttpClient();
        okHttpClient = new OkHttpClient.Builder().authenticator(new WwesAuthenticator(login())).build();
        signalRClient = new SignalRClient(new SignalRHandlerDummy(), null, okHttpClient);
        signalRClient.setHost("werewolv.es");
    }

    private String login() throws IOException {
        FormBody credentials = new FormBody.Builder()
                .add("username", "username")
                .add("password", "password")
                .add("grant_type", "password")
                .build();

        Request req = new Request.Builder()
                .url(HttpUrl.parse("https://werewolv.es/oauth2/token"))
                .post(credentials).build();

        Response res = okHttpClient.newCall(req).execute();

        return generateToken(res.body().string());
    }

    private String generateToken(String str) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode obj = mapper.readTree(str);

        return obj.get("token_type") + " " + obj.get("access_token");
    }

    @Test
    public void connectionInfoTest() throws IOException {
        log(signalRClient.getNewConnectionInfo().getClientProtocol());
        log(signalRClient.getNewConnectionInfo().getConnectionID());
        log(signalRClient.getNewConnectionInfo().getConnectionToken());
    }

    @Test
    public void connectionTest() throws Exception {
        signalRClient.setHubs(Arrays.asList("lobbyHub"));
        signalRClient.waitForReady();
        signalRClient.connect();
        signalRClient.waitForReady();

        while(true) {
        }
    }

    private void send(String message) throws IOException{
        List<Object> args = new ArrayList<>();
        args.add(message);
        signalRClient.send("lobbyHub", "Handle", args, null);
    }


    private void log(Object text) {
        System.out.println(text);
    }

    @After
    public void tearDown() throws Exception {
    }
}
