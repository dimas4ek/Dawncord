package org.dimas4ek.wrapper.entities.application;

import com.fasterxml.jackson.databind.JsonNode;
import org.dimas4ek.wrapper.types.PermissionType;
import org.dimas4ek.wrapper.types.Scope;
import org.dimas4ek.wrapper.utils.EnumUtils;

import java.util.List;

public class InstallParams {
    private final JsonNode params;
    private List<Scope> scopes;
    private List<PermissionType> permissions;

    public InstallParams(JsonNode params) {
        this.params = params;
    }

    public List<Scope> getScopes() {
        if (scopes == null) {
            scopes = EnumUtils.getEnumList(params.get("scopes"), Scope.class);
        }
        return scopes;
    }

    public List<PermissionType> getPermissions() {
        if (permissions == null) {
            permissions = EnumUtils.getEnumListFromLong(params, "permissions", PermissionType.class);
        }
        return permissions;
    }
}
