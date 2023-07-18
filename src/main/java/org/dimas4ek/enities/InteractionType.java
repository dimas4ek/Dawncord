package org.dimas4ek.enities;

public enum InteractionType {
    PONG(1),
    CHANNEL_MESSAGE_WITH_SOURCE(4),
    DEFERRED_CHANNEL_MESSAGE_WITH_SOURCE(5),
    DEFERRED_UPDATE_MESSAGE(6),
    UPDATE_MESSAGE(7),
    APPLICATION_COMMAND_AUTOCOMPLETE_RESULT(8),
    MODAL(9);
    
    final int value;
    InteractionType(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
}
