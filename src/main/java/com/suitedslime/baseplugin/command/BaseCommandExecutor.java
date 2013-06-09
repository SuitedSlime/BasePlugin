/**
 * *****************************************************************************
 * BasePlugin
 *
 * BaseCommandExecutor
 *
 * @author SuitedSlime
 * @licence Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * *****************************************************************************
 */

package com.suitedslime.baseplugin.command;

import com.suitedslime.baseplugin.BaseObject;

/**
 * The base class that all command executors should extend. Commands are registered
 * using {@link CommandManager} and defined using the {@link CommandHandler} annotations.
 *
 * @param <Type> The plugin that this executor belongs to.
 */

public class BaseCommandExecutor<Type> extends BaseObject<Type> {

    /**
     * @param plugin The plugin that this executor belongs to.
     */
    public BaseCommandExecutor(Type plugin) {
        super(plugin);
    }
}
