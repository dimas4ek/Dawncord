package org.dimas4ek.wrapper.action;

import org.dimas4ek.wrapper.ApiClient;
import org.dimas4ek.wrapper.types.GuildEventEntityType;
import org.dimas4ek.wrapper.types.GuildEventStatus;
import org.json.JSONObject;
import org.slf4j.helpers.CheckReturnValue;

import java.time.ZonedDateTime;

public class GuildEventModifyAction {
    private final String guildId;
    private final String eventId;
    private final JSONObject jsonObject;

    public GuildEventModifyAction(String guildId, String eventId) {
        this.guildId = guildId;
        this.eventId = eventId;
        this.jsonObject = new JSONObject();
    }

    private void setProperty(String key, Object value) {
        jsonObject.put(key, value);
    }

    @CheckReturnValue
    public GuildEventModifyAction setName(String name) {
        setProperty("name", name);
        return this;
    }

    @CheckReturnValue
    public GuildEventModifyAction setDescription(String description) {
        setProperty("description", description);
        return this;
    }

    @CheckReturnValue
    public GuildEventModifyAction setLocation(String location) {
        setProperty("location", location);
        return this;
    }

    //todo set image data
    /*public GuildEventModifyAction setImage() {

    }*/

    @CheckReturnValue
    public GuildEventModifyAction setStatus(GuildEventStatus status) {
        setProperty("status", status.getValue());
        return this;
    }

    @CheckReturnValue
    public GuildEventModifyAction setChannelEntityType(GuildEventEntityType entityType, String channelId) {
        setProperty("entity_type", entityType.getValue());
        setProperty("channel_id", channelId);
        return this;
    }

    @CheckReturnValue
    public GuildEventModifyAction setChannelEntityType(GuildEventEntityType entityType, long channelId) {
        setProperty("entity_type", entityType.getValue());
        setProperty("channel_id", channelId);
        return this;
    }

    @CheckReturnValue
    public GuildEventModifyAction setExternalEntityType(String location, ZonedDateTime endTimestamp) {
        setProperty("entity_type", GuildEventEntityType.EXTERNAL.getValue());
        setProperty("channel_id", null);
        setProperty("entity_metadata", new JSONObject().put("location", location));
        setProperty("scheduled_end_time", endTimestamp);
        return this;
    }

    public void submit() {
        ApiClient.patch(jsonObject, "/guilds/" + guildId + "/scheduled-events/" + eventId);
        jsonObject.clear();
    }
}
