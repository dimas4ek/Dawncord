package org.dimas4ek.dawncord.types;

public enum InteractionType {
    PING(1),
    APPLICATION_COMMAND(2),
    MESSAGE_COMPONENT(3),
    APPLICATION_COMMAND_AUTOCOMPLETE(4),
    MODAL_SUBMIT(5);

    private final int value;

    InteractionType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}