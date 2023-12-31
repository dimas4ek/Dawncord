package org.dimas4ek.wrapper.entities;

import com.fasterxml.jackson.databind.JsonNode;
import org.dimas4ek.wrapper.Constants;
import org.dimas4ek.wrapper.entities.image.Avatar;
import org.dimas4ek.wrapper.types.NitroType;
import org.dimas4ek.wrapper.types.UserFlag;
import org.dimas4ek.wrapper.utils.EnumUtils;

import java.util.List;

public class UserImpl implements User {
    private final JsonNode user;
    private String id;
    private String globalName;
    private String username;
    private Avatar avatar;
    private List<UserFlag> flags;
    private List<UserFlag> publicFlags;
    private Boolean hasNitro;
    private NitroType nitroType;
    private Boolean isBot;

    public UserImpl(JsonNode user) {
        this.user = user;
        this.id = user.get("id").asText();
    }

    @Override
    public String getId() {
        if (id == null) {
            id = user.get("id").asText();
        }
        return id;
    }

    @Override
    public long getIdLong() {
        return Long.parseLong(getId());
    }

    @Override
    public String getGlobalName() {
        if (globalName == null) {
            globalName = user.get("global_name").asText();
        }
        return globalName;
    }

    @Override
    public String getUsername() {
        if (username == null) {
            username = user.get("username").asText();
        }
        return username;
    }

    @Override
    public Avatar getAvatar() {
        if (avatar == null) {
            avatar = user.has("avatar") && user.hasNonNull("avatar")
                    ? new Avatar(getId(), user.get("avatar").asText())
                    : null;
        }
        return avatar;
    }

    @Override
    public List<UserFlag> getFlags() {
        if (flags == null) {
            flags = EnumUtils.getEnumListFromLong(user, "flags", UserFlag.class);
        }
        return flags;
    }

    @Override
    public List<UserFlag> getPublicFlags() {
        if (publicFlags == null) {
            publicFlags = EnumUtils.getEnumListFromLong(user, "public_flags", UserFlag.class);
        }
        return publicFlags;
    }

    @Override
    public boolean hasNitro() {
        if (hasNitro == null) {
            hasNitro = user.has("premium_type") || user.get("premium_type") != null;
        }
        return hasNitro;
    }

    @Override
    public NitroType getNitroType() {
        if (nitroType == null) {
            nitroType = EnumUtils.getEnumObject(user, "premium_type", NitroType.class);
        }
        return nitroType;
    }

    @Override
    public boolean isBot() {
        if (isBot == null) {
            isBot = Constants.APPLICATION_ID.equals(user.get("id").asText());
        }
        return isBot;
    }

    @Override
    public String getAsMention() {
        return "<@" + getId() + ">";
    }
}
