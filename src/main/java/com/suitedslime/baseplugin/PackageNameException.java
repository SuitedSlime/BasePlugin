/**
 * *****************************************************************************
 * BasePlugin
 *
 * PackageNameException
 *
 * @author SuitedSlime
 * @licence Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * *****************************************************************************
 */

package com.suitedslime.baseplugin;

public class PackageNameException extends RuntimeException {

    private static final long serialVersionUID = -111121073497919830L;

    public PackageNameException(String className) {
        super("BasePlugin library has incorrect package name: " + className);
    }
}
