package io.hedwig.modules.internal.classfinder;

import java.net.URL;

/**
 * 1. author: patrick 2. address: github.com/ideafortester
 */
public interface ClassFinder {
    public URL[] findClasses(String className);
}
