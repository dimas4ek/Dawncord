package org.dimas4ek.wrapper.types;

public enum OptionType {
    SUB_COMMAND(1),
    SUB_COMMAND_GROUP(2),
    STRING(3),
    INTEGER(4),
    BOOLEAN(5),
    USER(6),
    CHANNEL(7),
    ROLE(8),
    MENTIONABLE(9),
    NUMBER(10),
    ATTACHMENT(11);

    private final int value;

    OptionType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
