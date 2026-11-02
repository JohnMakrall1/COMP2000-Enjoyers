import java.util.Random;
import java.util.function.ToDoubleBiFunction;

/**
 * Implements direct-contact disease transmission.
 *
 * Direct transmission occurs when:
 * 1. The source human is infected.
 * 2. The target is a different human who is not already infected.
 * 3. Both humans occupy cells within the contact radius.
 * 4. The random transmission probability check succeeds.
 *
 * This class implements TransmissionStrategy, allowing it to be
 * selected interchangeably with other transmission methods,
 * such as AirborneTransmission.
 */
public class DirectTransmission implements TransmissionStrategy {

    /**
     * Maximum Manhattan distance between humans for direct contact.
     * A radius of 1 allows horizontally or vertically adjacent cells.
     */
    private static final int CONTACT_RADIUS = 1;

    /** Random number generator for transmission probability checks. */
    private final Random random = new Random();

    /**
     * Calculates the transmission probability using the disease's
     * infection rate and the target human's health.
     *
     * ToDoubleBiFunction accepts two arguments and returns a double.
     * Using a lambda keeps the probability calculation separate from
     * the main transmission procedure.
     */
    private final ToDoubleBiFunction<Disease, Human> probabilityCalculator;

    /**
     * Creates a direct transmission strategy using the default
     * probability calculation.
     *
     * This constructor maintains compatibility with existing code,
     * including the transmission selection menu in Main.
     */
    public DirectTransmission() {
        this((disease, target) ->
                disease.getInfectionRate() * target.health());
    }

    /**
     * Creates a direct transmission strategy using a supplied
     * probability calculation.
     *
     * This allows alternative probability rules to be introduced
     * using lambda expressions without changing the transmission
     * algorithm itself.
     *
     * @param probabilityCalculator function used to calculate
     *                              transmission probability
     * @throws IllegalArgumentException if the calculator is null
     */
    public DirectTransmission(
            ToDoubleBiFunction<Disease, Human> probabilityCalculator) {

        if (probabilityCalculator == null) {
            throw new IllegalArgumentException(
                    "Probability calculator cannot be null.");
        }

        this.probabilityCalculator = probabilityCalculator;
    }

    /**
     * Attempts to transmit a disease directly from one human
     * to another.
     *
     * The method validates its arguments, checks infection status
     * and contact distance, then performs a random probability check.
     *
     * @param source the human attempting to transmit the disease
     * @param target the human who may receive the disease
     * @param disease the disease being transmitted
     * @return true if transmission succeeds; otherwise, false
     * @throws IllegalArgumentException if any argument is null
     */
    @Override
    public boolean transmit(Human source, Human target, Disease disease) {

        // Validate arguments before attempting transmission.
        if (source == null || target == null || disease == null) {
            throw new IllegalArgumentException(
                    "Source, target, and disease cannot be null.");
        }

        // A human cannot transmit the disease to themselves.
        if (source == target) {
            return false;
        }

        // The source must already be infected.
        if (!source.isInfected()) {
            return false;
        }

        // Do not attempt to infect an already infected human.
        if (target.isInfected()) {
            return false;
        }

        // Direct transmission requires the humans to be nearby.
        if (!withinContactRange(source, target)) {
            return false;
        }

        // Calculate the probability using the configured function.
        double probability =
                calculateTransmissionProbability(disease, target);

        // Compare a random value against the transmission probability.
        if (random.nextDouble() < probability) {

            // Delegate infection state changes to the Human class.
            target.infect(disease);

            return true;
        }

        return false;
    }

    /**
     * Determines whether two humans are close enough for
     * direct-contact transmission.
     *
     * Manhattan distance is calculated by adding the horizontal
     * and vertical distances between their grid cells.
     *
     * Diagonally adjacent cells are not considered within a
     * contact radius of one.
     *
     * @param source the human attempting to transmit the disease
     * @param target the potential recipient
     * @return true if both humans have locations within contact range
     */
    private boolean withinContactRange(Human source, Human target) {

        Cell sourceCell = source.loc;
        Cell targetCell = target.loc;

        // Humans without valid locations cannot make direct contact.
        if (sourceCell == null || targetCell == null) {
            return false;
        }

        // Convert pixel-coordinate differences into grid-cell distances.
        int columnDistance =
                Math.abs(sourceCell.x - targetCell.x) / Cell.size;

        int rowDistance =
                Math.abs(sourceCell.y - targetCell.y) / Cell.size;

        // Manhattan distance = horizontal distance + vertical distance.
        int distance = columnDistance + rowDistance;

        return distance <= CONTACT_RADIUS;
    }

    /**
     * Calculates and validates the transmission probability.
     *
     * The probability calculation is delegated to the configured
     * lambda function. The result is restricted to the range 0.0
     * to 1.0 to ensure a valid probability value.
     *
     * @param disease the disease being transmitted
     * @param target the potential recipient
     * @return the calculated transmission probability
     */
    private double calculateTransmissionProbability(
            Disease disease, Human target) {

        double probability =
                probabilityCalculator.applyAsDouble(disease, target);

        // Keep the probability within its valid range.
        return Math.max(0.0, Math.min(1.0, probability));
    }
}