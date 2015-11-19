package com.coo.texierchami.tools;

import java.awt.*;

/**
 * Created by othmanechamikhazraji on 13/11/15.
 */
public class Util {
    public static final int getComponentIndex(Component component) {
        if (component != null && component.getParent() != null) {
            Container c = component.getParent();
            for (int i = 0; i < c.getComponentCount(); i++) {
                if (c.getComponent(i) == component)
                    return i;
            }
        }

        return -1;
    }
}
