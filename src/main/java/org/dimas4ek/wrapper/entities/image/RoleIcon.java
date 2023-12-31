package org.dimas4ek.wrapper.entities.image;

import org.dimas4ek.wrapper.Constants;
import org.dimas4ek.wrapper.types.ImageFormat;

public class RoleIcon implements Icon {
    private final String roleId;
    private final String hash;

    public RoleIcon(String roleId, String hash) {
        this.roleId = roleId;
        this.hash = hash;
    }

    @Override
    public String getUrl(ImageFormat format) {
        return Constants.CDN_URL + "/role-icons/" + roleId + "/" + hash + format.getFormat();
    }
}
