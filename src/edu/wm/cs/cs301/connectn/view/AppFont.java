/**
 * Set of fonts used in UI. Aggregated for reuse and visual consistency.
 */
package edu.wm.cs.cs301.connectn.view;

import java.awt.*;

public enum AppFont {
    TITLE(new Font("Dialog", Font.BOLD, 36)),
    TEXT(new Font("Dialog", Font.PLAIN, 16)),
    FOOTER(new Font("Dialog", Font.PLAIN, 12));

    public final Font font;

    private AppFont(Font font) {
        this.font = font;
    }
}
