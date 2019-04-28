package uk.org.dataforce.libs.signalr;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

import javax.annotation.Nullable;
import java.io.IOException;

public class WwesAuthenticator implements Authenticator {
    private String token;

    WwesAuthenticator(String token) {
        this.token = token;
    }


    @Nullable
    @Override
    public Request authenticate(@Nullable Route route, Response response) throws IOException {
        if (response.request().header("Authorization") != null) {
            return null; // Give up, we've already failed to authenticate.
        }

        return response.request().newBuilder()
                .header("Authorization", token)
                .build();
    }
}
