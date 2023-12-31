package org.dimas4ek.wrapper.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.dimas4ek.wrapper.ApiClient;
import org.dimas4ek.wrapper.types.GuildMemberFlag;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class GuildMemberModifyAction {
    private static final ObjectMapper mapper = new ObjectMapper();
    private final ObjectNode jsonObject;
    private final String guildId;
    private final String userId;
    private boolean hasChanges = false;

    public GuildMemberModifyAction(String guildId, String userId) {
        this.guildId = guildId;
        this.userId = userId;
        this.jsonObject = mapper.createObjectNode();
    }

    private void setProperty(String name, Object value) {
        jsonObject.set(name, mapper.valueToTree(value));
        hasChanges = true;
    }

    public GuildMemberModifyAction setNick(String nickName) {
        setProperty("nick", nickName);
        return this;
    }

    public GuildMemberModifyAction setRoles(List<String> roleIds) {
        setProperty("roles", roleIds);
        return this;
    }

    public GuildMemberModifyAction mute(boolean isMuted) {
        setProperty("mute", isMuted);
        return this;
    }

    public GuildMemberModifyAction deafen(boolean isDeafened) {
        setProperty("deaf", isDeafened);
        return this;
    }

    public GuildMemberModifyAction moveToChannel(String channelId) {
        setProperty("channel_id", channelId);
        return this;
    }

    public GuildMemberModifyAction moveToChannel(long channelId) {
        moveToChannel(String.valueOf(channelId));
        return this;
    }

    public GuildMemberModifyAction setTimeoutUntil(ZonedDateTime timeout) {
        setProperty("communication_disabled_until", timeout.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        return this;
    }

    public GuildMemberModifyAction removeTimeout() {
        setProperty("communication_disabled_until", NullNode.instance);
        return this;
    }

    public GuildMemberModifyAction setFlags(GuildMemberFlag... flags) {
        long value = 0;
        for (GuildMemberFlag flag : flags) {
            value |= flag.getValue();
        }
        setProperty("flags", value);
        return this;
    }

    private void submit() {
        if (hasChanges) {
            ApiClient.patch(jsonObject, "/guilds/" + guildId + "/members/" + userId);
            hasChanges = false;
        }
        jsonObject.removeAll();
    }
}
