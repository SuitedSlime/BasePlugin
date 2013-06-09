/**
 * *****************************************************************************
 * BasePlugin
 *
 * PermissionManager
 *
 * @author SuitedSlime
 * @licence Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * *****************************************************************************
 */

package com.suitedslime.baseplugin.permissions;

import com.suitedslime.baseplugin.BaseObject;
import com.suitedslime.baseplugin.BasePlugin;
import org.bukkit.permissions.Permission;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class PermissionManager extends BaseObject<BasePlugin> {

    public PermissionManager(BasePlugin plugin) {
        super(plugin);
    }

    public void registerPermissions(PluginPermission[] permissions) {
        for (PluginPermission permission : permissions) {
            plugin.pluginManager.addPermission(new Permission(permission.getNode(), permission.getDescription(),
                    permission.getDefault()));
        }
    }

    /**
     * Registers the default permissions for a plugin.
     *
     * @param permissionHolder The class holding all of the {@link PluginPermission} objects.
     */
    public void registerPermissions(Class<?> permissionHolder) {
        ArrayList<PluginPermission> perms = new ArrayList<PluginPermission>();

        for (Field field : permissionHolder.getDeclaredFields()) {
            if (field.getType().equals(PluginPermission.class)) {
                try {
                    perms.add((PluginPermission) field.get(null));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        this.registerPermissions(perms.toArray(new PluginPermission[0]));
    }
}
