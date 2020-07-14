package patterns;

/**
 * Dedicated to tests handling word boundary problems, caused by the fact that
 * Bricks automata cannot match '^' and '$'.
 *
 */
public class StringBoundaryTest {

	// TODO
	// \\b
	// =>
	// "(^\\w|\\w$|\\W\\w|\\w\\W)"

	// p_15620.json: "pattern": "^(?!$|^[A-Fa-f0-9]{64})[ -~]{1,255}$"
}
