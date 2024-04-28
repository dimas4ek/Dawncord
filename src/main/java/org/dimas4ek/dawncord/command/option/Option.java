package org.dimas4ek.dawncord.command.option;

import org.dimas4ek.dawncord.types.OptionType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Option {
    private final OptionType type;
    private final String name;
    private final String description;
    private boolean isRequired;
    private boolean isAutocomplete; //TODO add autocomplete
    private final List<Choice> choices = new ArrayList<>();

    public Option(OptionType type, String name, String description) {
        this.type = type;
        this.name = name;
        this.description = description;
        this.isRequired = false;
        isAutocomplete = false;
    }

    public Option(OptionType type, String name, String description, boolean isRequired) {
        this.type = type;
        this.name = name;
        this.description = description;
        this.isRequired = isRequired;
        isAutocomplete = false;
    }

    public Option(OptionType type, String name, String description, boolean isRequired, boolean isAutocomplete) {
        this.type = type;
        this.name = name;
        this.description = description;
        this.isRequired = isRequired;
        this.isAutocomplete = isAutocomplete;
    }

    public OptionType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public boolean isAutocomplete() {
        return isAutocomplete;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setRequired(boolean isRequired) {
        this.isRequired = isRequired;
    }

    public void setAutocomplete(boolean isAutocomplete) {
        this.isAutocomplete = isAutocomplete;
    }

    public Option addChoice(String name, String value) {
        choices.add(new Choice(name, value));
        return this;
    }

    public Option addChoice(Choice choice) {
        choices.add(choice);
        return this;
    }

    public Option addChoices(Choice... choices) {
        Collections.addAll(this.choices, choices);
        return this;
    }

    public static class Choice {
        private final String name;
        private final String value;

        public Choice(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public String getValue() {
            return value;
        }
    }
}

