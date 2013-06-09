/**
 * *****************************************************************************
 * BasePlugin
 *
 * PluginSubCommand
 *
 * @author SuitedSlime
 * @licence Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * *****************************************************************************
 */

package com.suitedslime.baseplugin.command;

import com.suitedslime.baseplugin.BasePlugin;

import java.lang.reflect.Method;

public class PluginSubCommand {

    protected BaseCommandExecutor<? extends BasePlugin> handler;
    protected Method handlerMethod;
    protected String[] tabCompletion;

    public PluginSubCommand(BaseCommandExecutor<? extends BasePlugin> handler, Method handlerMethod,
                            String[] tabCompletion) {
        this.handler = handler;
        this.handlerMethod = handlerMethod;
        this.tabCompletion = tabCompletion;
    }
}
