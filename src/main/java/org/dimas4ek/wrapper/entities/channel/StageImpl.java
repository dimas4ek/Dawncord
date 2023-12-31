package org.dimas4ek.wrapper.entities.channel;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import org.dimas4ek.wrapper.ApiClient;
import org.dimas4ek.wrapper.entities.guild.Guild;
import org.dimas4ek.wrapper.entities.guild.event.GuildEvent;
import org.dimas4ek.wrapper.entities.guild.event.GuildEventImpl;
import org.dimas4ek.wrapper.types.StagePrivacyLevel;
import org.dimas4ek.wrapper.utils.JsonUtils;

import java.util.Map;

public class StageImpl implements Stage {
    private final JsonNode stage;
    private final Guild guild;
    private String id;
    private GuildChannel channel;
    private String topic;
    private Boolean isDiscoverable;
    private GuildEvent guildEvent;

    public StageImpl(JsonNode stage, Guild guild) {
        this.stage = stage;
        this.guild = guild;
    }

    @Override
    public Guild getGuild() {
        return guild;
    }

    @Override
    public String getId() {
        if (id == null) {
            id = stage.get("id").asText();
        }
        return id;
    }

    @Override
    public long getIdLong() {
        return Long.parseLong(getId());
    }

    @Override
    public GuildChannel getChannel() {
        if (channel == null) {
            channel = new GuildChannelImpl(JsonUtils.fetchEntity("/channels/" + stage.get("channel_id").asText()), guild);
        }
        return channel;
    }

    @Override
    public String getTopic() {
        if (topic == null) {
            topic = stage.get("topic").asText();
        }
        return topic;
    }

    @Override
    public StagePrivacyLevel getPrivacyLevel() {
        return StagePrivacyLevel.GUILD_ONLY;
    }

    @Override
    public boolean isDiscoverable() {
        if (isDiscoverable == null) {
            isDiscoverable = !stage.get("discoverable_disabled").asBoolean();
        }
        return isDiscoverable;
    }

    @Override
    public GuildEvent getGuildEvent() {
        if (guildEvent == null) {
            guildEvent = new GuildEventImpl(
                    JsonUtils.fetchEntityParams(
                            "/guilds/" + getGuild().getId() + "/scheduled-events/" + stage.get("guild_scheduled_event_id").asText(),
                            Map.of("with_user_count", "true")
                    ), guild);
        }
        return guildEvent;
    }

    @Override
    public void delete() {
        ApiClient.delete("/stage-instances/" + getId());
    }

    @Override
    public void modify(String topic) {
        ApiClient.patch(JsonNodeFactory.instance.objectNode().put("topic", topic), "/stage-instances/" + getId());
    }
}
