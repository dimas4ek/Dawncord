package org.dimas4ek.interaction.response.interaction;

import org.dimas4ek.api.ApiClient;
import org.json.JSONObject;

public class InteractionCallbackImpl implements InteractionCallback {
    private final JSONObject jsonObject;
    private final String interactionId;
    private final String interactionToken;
    
    public InteractionCallbackImpl(JSONObject jsonObject, String interactionId, String interactionToken) {
        this.jsonObject = jsonObject;
        this.interactionId = interactionId;
        this.interactionToken = interactionToken;
    }
    
    @Override
    public InteractionResponse setEphemeral(boolean ephemeral) {
        if (ephemeral) {
            jsonObject.getJSONObject("data")
                .put("flags", 1 << 6);
        }
        return new InteractionResponseImpl(jsonObject, interactionId, interactionToken);
    }
    
    @Override
    public void execute() {
        ApiClient.sendResponse(jsonObject, interactionId, interactionToken);
    }
}
