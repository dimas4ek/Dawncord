package org.dimas4ek.wrapper.entities.message.component;

import org.dimas4ek.wrapper.entities.DefaultEmoji;
import org.dimas4ek.wrapper.entities.Emoji;

public class SelectOption {
    private final String label;
    private final String value;
    private String description;
    private Emoji emoji;
    private boolean isDefault;

    public SelectOption(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public SelectOption setDescription(String description) {
        this.description = description;
        return this;
    }

    public SelectOption withEmoji(Emoji emoji) {
        this.emoji = emoji;
        return this;
    }

    public SelectOption withEmoji(String name) {
        this.emoji = new DefaultEmoji(name);
        return this;
    }

    public void setDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public String getLabel() {
        return label;
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public Emoji getEmoji() {
        return emoji;
    }

    public boolean isDefault() {
        return isDefault;
    }
}
