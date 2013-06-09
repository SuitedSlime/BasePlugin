/**
 * *****************************************************************************
 * BasePlugin
 *
 * CommandManager
 *
 * @author SuitedSlime
 * @licence Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * *****************************************************************************
 */

package com.suitedslime.baseplugin.command;

import com.suitedslime.baseplugin.BasePlugin;
import com.suitedslime.baseplugin.util.ReflectionUtils;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.SimplePluginManager;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Used to manage the commands for this plugin.
 */
public class CommandManager {

    private BasePlugin plugin;
    private CommandMap commandMap;

    public CommandManager(BasePlugin plugin) {
        this.plugin = plugin;

        try {
            this.commandMap = ReflectionUtils.getFieldValue(SimplePluginManager.class, "commandMap",
                    CommandMap.class, plugin.pluginManager);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean registerCommand(PluginCommand command) {
        return this.commandMap.register(plugin.description.getName(), command);
    }

    public void registerCommandExecutor(BaseCommandExecutor<? extends BasePlugin> executor) {
        Class<BaseCommandExecutor<? extends BasePlugin>> cls = (Class<BaseCommandExecutor<? extends BasePlugin>>)
                executor.getClass();

        for (Method method : cls.getDeclaredMethods()) {
            CommandHandler commandInfo = method.getAnnotation(CommandHandler.class);
            CommandTabCompletion tabInfo = method.getAnnotation(CommandTabCompletion.class);

            if (commandInfo != null) {
                if (!method.getReturnType().equals(Void.TYPE)) {
                    throw new CommandRegistrationException("Incorrect return type for command method " + method
                            .getName() + " in " + cls.getName());
                } else if (!Arrays.equals(method.getParameterTypes(), new Class<?>[]{CommandSender.class,
                        String.class, String[].class})) {
                    throw new CommandRegistrationException("Incorrect arguments for command method " + method.getName
                            () + " in " + cls.getName());
                } else {
                    PluginCommand command = new PluginCommand(plugin, executor, method, commandInfo.names(),
                            commandInfo.description(), commandInfo.usage(), ((tabInfo == null) ? new String[0] :
                            tabInfo.value()));

                    if (!this.registerCommand(command)) {
                        throw new CommandRegistrationException("Failed to register command for method " + method
                                .getName() + " in " + cls.getName());
                    }
                }
            }
        }

        for (Method method : cls.getDeclaredMethods()) {
            SubCommandHandler subCommandInfo = method.getAnnotation(SubCommandHandler.class);
            CommandTabCompletion tabInfo = method.getAnnotation(CommandTabCompletion.class);

            if (subCommandInfo != null) {
                if (!method.getReturnType().equals(Void.TYPE)) {
                    throw new CommandRegistrationException("Incorrect return type for command method " + method.getName() + " in " + cls.getName());
                } else if (!Arrays.equals(method.getParameterTypes(), new Class<?>[]{CommandSender.class, String.class, String[].class})) {
                    throw new CommandRegistrationException("Incorrect arguments for command method " + method.getName() + " in " + cls.getName());
                } else {
                    PluginCommand parent = (PluginCommand) this.commandMap.getCommand(subCommandInfo.parent());

                    if (parent == null) {
                        throw new CommandRegistrationException("Attempted to register sub-command of " + subCommandInfo.parent() + " before main handler.");
                    }

                    parent.registerSubCommandHandler(subCommandInfo.name(), new PluginSubCommand(executor, method, ((tabInfo == null) ? new String[0] : tabInfo.value())));
                }
            }
        }
    }
}
