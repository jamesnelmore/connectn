/**
 * Set of named row/column/N configurations that can be played in the game.
 */
package edu.wm.cs.cs301.connectn.model;

public enum GameMode {
    SMALL(4, 5, 3, "Small"),
    MEDIUM(6, 7, 4, "Medium"),
    LARGE(8, 9, 5, "Large");

    public final int rows;
    public final int columns;
    public final int n;
    public final String name;

    GameMode(int rows, int columns, int n, String name) {
        this.rows = rows;
        this.columns = columns;
        this.n = n;
        this.name = name;
    }
}
