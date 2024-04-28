package org.dimas4ek.dawncord.types;

public enum ActivityFlag {
    INSTANCE(1 << 0),
    JOIN(1 << 1),
    SPECTATE(1 << 2),
    JOIN_REQUEST(1 << 3),
    SYNC(1 << 4),
    PLAY(1 << 5),
    PARTY_PRIVACY_FRIENDS(1 << 6),
    PARTY_PRIVACY_VOICE_CHANNEL(1 << 7),
    EMBEDDED(1 << 8);

    private final long value;

    ActivityFlag(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }
}
