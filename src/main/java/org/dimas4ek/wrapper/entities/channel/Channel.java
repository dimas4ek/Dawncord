package org.dimas4ek.wrapper.entities.channel;

import org.dimas4ek.wrapper.entities.IMentionable;
import org.dimas4ek.wrapper.entities.guild.Guild;
import org.dimas4ek.wrapper.types.ChannelType;
import org.dimas4ek.wrapper.types.GuildMemberFlag;

import java.util.List;

public interface Channel extends IMentionable {
    String getId();

    long getIdLong();

    String getName();

    ChannelType getType();

    Guild getGuild();

    List<GuildMemberFlag> getFlags();

    void delete();
}
