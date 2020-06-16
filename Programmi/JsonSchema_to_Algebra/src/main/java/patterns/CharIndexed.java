package patterns;

/**
 Adapted from gnu.regexp.
*/


public interface CharIndexed {
    /**
     * Defines a constant (0xFFFF was somewhat arbitrarily chosen)
     * that can be returned by the charAt() function indicating that
     * the specified index is out of range.
     */
    char OUT_OF_BOUNDS = '\uFFFF';

    /**
     * Returns the character at the given offset past the current cursor
     * position in the input.  The index of the current position is zero.
     * It is possible for this method to be called with a negative index.
     * This happens when using the '^' operator in multiline matching mode
     * or the '\b' or '\<' word boundary operators.  In any case, the lower
     * bound is currently fixed at -2 (for '^' with a two-character newline).
     *
     * @param index the offset position in the character field to examine
     * @return the character at the specified index, or the OUT_OF_BOUNDS
     *   character defined by this interface.
     */
    char charAt(int index);

    /**
     * Shifts the input buffer by a given number of positions.  Returns
     * true if the new cursor position is valid.
     */
    boolean move(int index);

    /**
     * Returns true if the most recent move() operation placed the cursor
     * position at a valid position in the input.
     */
    boolean isValid();
}
