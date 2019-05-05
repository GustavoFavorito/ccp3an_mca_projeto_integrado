package br.usjt.ccp3an_mca_projeto_integrado;

/**
 * Sample Java code for youtube.liveBroadcasts.insert
 * See instructions for running these code samples locally:
 * https://developers.google.com/explorer-help/guides/code_samples#java
 */

import com.google.api.client.auth.oauth2.Credential;
//import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
//import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.auth.oauth2.AuthorizationCodeResponseUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.LiveBroadcast;
import com.google.api.services.youtube.model.LiveBroadcastContentDetails;
import com.google.api.services.youtube.model.LiveBroadcastSnippet;
import com.google.api.services.youtube.model.LiveBroadcastStatus;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collection;

public class ApiExample {
    private static final String CLIENT_SECRETS= "client_secret.json";
    private static final Collection<String> SCOPES =
            Arrays.asList("https://www.googleapis.com/auth/youtube.force-ssl");

    private static final String APPLICATION_NAME = "API code samples";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    /**
     * Create an authorized Credential object.
     *
     * @return an authorized Credential object.
     * @throws IOException
     */
    public static Credential authorize(final NetHttpTransport httpTransport) throws IOException {
        // Load client secrets.
        InputStream in = ApiExample.class.getResourceAsStream(CLIENT_SECRETS);
        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY, clientSecrets, SCOPES)
                        .build();
        Credential credential =
                new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
        return credential;
    }

    /**
     * Build and return an authorized API client service.
     *
     * @return an authorized API client service
     * @throws GeneralSecurityException, IOException
     */
    public static YouTube getService() throws GeneralSecurityException, IOException {
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        Credential credential = authorize(httpTransport);
        return new YouTube.Builder(httpTransport, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    /**
     * Call function to create API service object. Define and
     * execute API request. Print API response.
     *
     * @throws GeneralSecurityException, IOException, GoogleJsonResponseException
     */
    public static void main(String[] args)
            throws GeneralSecurityException, IOException, GoogleJsonResponseException {
        YouTube youtubeService = getService();

        // Define the LiveBroadcast object, which will be uploaded as the request body.
        LiveBroadcast liveBroadcast = new LiveBroadcast();

        // Add the contentDetails object property to the LiveBroadcast object.
        LiveBroadcastContentDetails contentDetails = new LiveBroadcastContentDetails();
        contentDetails.setEnableClosedCaptions(true);
        contentDetails.setEnableContentEncryption(true);
        contentDetails.setEnableDvr(true);
        contentDetails.setEnableEmbed(true);
        contentDetails.setRecordFromStart(true);
        contentDetails.setStartWithSlate(true);
        liveBroadcast.setContentDetails(contentDetails);

        // Add the snippet object property to the LiveBroadcast object.
        LiveBroadcastSnippet snippet = new LiveBroadcastSnippet();
        snippet.setScheduledEndTime(new DateTime("YOUR_SCHEDULED_END_TIME"));
        snippet.setScheduledStartTime(new DateTime("YOUR_SCHEDULED_START_TIME"));
        snippet.setTitle("Test broadcast");
        liveBroadcast.setSnippet(snippet);

        // Add the status object property to the LiveBroadcast object.
        LiveBroadcastStatus status = new LiveBroadcastStatus();
        status.setPrivacyStatus("unlisted");
        liveBroadcast.setStatus(status);

        // Define and execute the API request
        YouTube.LiveBroadcasts.Insert request = youtubeService.liveBroadcasts()
                .insert("snippet,contentDetails,status", liveBroadcast);
        LiveBroadcast response = request.execute();
        System.out.println(response);
    }
}
