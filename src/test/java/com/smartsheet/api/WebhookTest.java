package com.smartsheet.api;

import com.smartsheet.api.models.Webhook;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by skaoth on 6/8/17.
 */
@Ignore
public class WebhookTest {
    String accessToken = "1szje047plr9r08tluy09twmir";
    // Get API access token from properties file or environment
    Smartsheet ss = new SmartsheetBuilder().setAccessToken(accessToken).build();

    @Test
    public void createWebhook() throws SmartsheetException {

        WebhookResources webhook = ss.webhookResources();

        Webhook wh = new Webhook();
        wh.setName("webhook1");
        wh.setScope("sheet");
        wh.setScopeObjectId(4305281023797124L);
        wh.setEvents(Arrays.asList("*.*"));
        wh.setVersion(1);
        wh.setCallbackUrl("http://ec2-54-245-163-160.us-west-2.compute.amazonaws.com:3000");
        wh = webhook.createWebhook(wh);
        enableWebhook(wh);
    }

    @Test
    public void enableWebHook() throws SmartsheetException {
        Webhook webhook = ss.webhookResources().getWebhook(8886329044952964L);
        webhook.setCreatedAt(null);
        webhook.setModifiedAt(null);
        webhook.setSharedSecret(null);
        webhook.setStatus(null);
        webhook.setScopeObjectId(null);
        webhook.setScope(null);

        enableWebhook(webhook);
    }
    private void enableWebhook(Webhook wh) throws SmartsheetException {

        wh.setEnabled(true);
        ss.webhookResources().updateWebhook(wh);
    }


    @Test
    public void deleteWebhook() {

    }
}
