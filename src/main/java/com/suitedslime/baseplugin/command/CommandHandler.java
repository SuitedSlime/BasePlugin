/**
 * *****************************************************************************
 * BasePlugin
 *
 * CommandHandler
 *
 * @author SuitedSlime
 * @licence Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * *****************************************************************************
 */

package com.suitedslime.baseplugin.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that this method should be used to handle a command.
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CommandHandler {

    /**
     * @return The names that can be used for this command.
     */
    String[] names();

    /**
     * @return The long description of this command.
     */
    String description();

    /**
     * @return The usage information for this command.
     */
    String usage() default "";
}
