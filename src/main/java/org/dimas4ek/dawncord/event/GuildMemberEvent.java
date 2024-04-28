package org.dimas4ek.dawncord.event;

import org.dimas4ek.dawncord.entities.guild.Guild;
import org.dimas4ek.dawncord.entities.guild.GuildMember;

public class GuildMemberEvent implements Event {
    private final Guild guild;
    private final GuildMember member;

    public GuildMemberEvent(Guild guild, GuildMember member) {
        this.guild = guild;
        this.member = member;
    }

    @Override
    public Guild getGuild() {
        return guild;
    }

    public GuildMember getMember() {
        return member;
    }
}