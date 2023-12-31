package org.dimas4ek.wrapper.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MfaLevel {
    NONE(0, "guild has no MFA/2FA requirement for moderation actions"),
    ELEVATED(1, "guild has a 2FA requirement for moderation actions");

    private final int value;
    private final String description;
}
