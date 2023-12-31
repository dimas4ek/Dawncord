package org.dimas4ek.wrapper.entities.message;

import org.dimas4ek.wrapper.entities.Emoji;

import java.util.List;

public interface Reaction {
    int getTotal();

    int getNormalCount();

    int getBurstCount();

    boolean isMe();

    boolean isMeBurst();

    Emoji getGuildEmoji();

    boolean isGuildEmoji();

    String getEmoji();

    List<String> getBurstColors();

    void delete(String userId);

    void delete(long userId);
}
