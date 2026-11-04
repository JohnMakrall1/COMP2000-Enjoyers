import java.util.Random;
import java.util.function.ToDoubleBiFunction;

/**
 * Implements direct-contact disease transmission.
 *
 * Transmission occurs when:
 * 1. The source human is infected and contagious.
 * 2. The target is a different, uninfected human.
 * 3. Both humans are within the contact radius.
 * 4. The random transmission probability check succeeds.
 *
 * This class implements TransmissionStrategy, allowing it to be
 * used interchangeably with other transmission algorithms.
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
     * Calculates transmission probability using the disease's
     * infection rate and the target human's health.
     *
     * ToDoubleBiFunction accepts two arguments and returns a double.
     * This separates probability calculation from the transmission
     * algorithm and allows alternative calculations using lambdas.
     */
    private final ToDoubleBiFunction<Disease, Human> probabilityCalculator;

    /**
     * Creates a direct transmission strategy using the default
     * probability calculation.
     *
     * Maintains compatibility with existing code, including Ringworm.
     */
    public DirectTransmission() {
        this((disease, target) ->
                disease.getInfectionRate() * target.health());
    }

    /**
     * Creates a direct transmission strategy with a custom
     * probability calculation.
     *
     * Allows alternative probability rules without modifying
     * the main transmission algorithm.
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
     * Attempts to transmit a disease directly between two humans.
     *
     * Checks the arguments, infection status, contagious status
     * and contact distance before calculating transmission probability.
     *
     * @param source the infected human attempting transmission
     * @param target the potential recipient
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

        // A human cannot transmit disease to themselves.
        if (source == target) {
            return false;
        }

        // The source must be infected and past the incubation period.
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

        // Calculate probability using the configured function.
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
     * Diagonally adjacent cells are outside a radius of one.
     *
     * @param source the transmitting human
     * @param target the potential recipient
     * @return true if both humans are within contact range
     */
    private boolean withinContactRange(Human source, Human target) {

        Cell sourceCell = source.loc;
        Cell targetCell = target.loc;

        // Humans without valid locations cannot make direct contact.
        if (sourceCell == null || targetCell == null) {
            return false;
        }

        // Calculate horizontal and vertical grid distances.
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
     * The configured lambda performs the calculation. The result
     * is clamped between 0.0 and 1.0 to ensure a valid probability.
     *
     * @param disease the disease being transmitted
     * @param target the potential recipient
     * @return the calculated transmission probability
     */
    private double calculateTransmissionProbability(
            Disease disease, Human target) {

        double probability =
                probabilityCalculator.applyAsDouble(disease, target);

        return Math.max(0.0, Math.min(1.0, probability));
    }
}