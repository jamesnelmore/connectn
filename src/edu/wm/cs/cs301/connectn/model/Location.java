/**
 * Represents a location in a GameBoard. Can contain a character marking a player's move, 'X', 'O', or ' ' if empty.
 */
package edu.wm.cs.cs301.connectn.model;

public class Location {
    private Character symbol;
//	private Boolean empty;

    public Location() {
        this.symbol = ' ';
    }

    public Location(Location loc) {
        this.symbol = loc.symbol;
    }

    public boolean isEmpty() {
        return this.symbol.equals(' ');
    }

    public Character getToken() {
        return symbol;
    }

    public void placeToken(char token) {
        this.symbol = token;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Location)) {
            return false;
        }

        Location otherLocation = (Location) other;
        if (this.isEmpty() | otherLocation.isEmpty()) {
            return false;
        } else {
            return this.getToken().equals(otherLocation.getToken());
        }
    }

    @Override
    public int hashCode() {
        return this.symbol.hashCode();
    }

    public String toString() {
        return this.symbol.toString();
    }
}

