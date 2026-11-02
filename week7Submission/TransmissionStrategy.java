/**
 * Defines the behaviour for a disease transmission strategy.
 * 
 * Different implementations can use different rules to determine whether
 * a disease is transmitted from one human to another.
 * 
 * This interface supports the Strategy design pattern, by allowing the
 * transmission algorithm to be changed without modifying the Disease class.
 * 
 * The @FunctionalInterface annotation also allows strategies to be 
 * implemented using lambda expressions.
 */
@FunctionalInterface
public interface TransmissionStrategy {

    /**
     * Indicates wether this strategy uses airborne trails.
     * 
     * Direct transmission does not require airborne trail tracking,
     * so the default implementation returns false.
     * 
     * AirborneTransmission overrides this method to return true.
     * 
     * @return true if the strategy uses airborne trails; otherwise, false
     */
    default boolean usesAirborneTrail() {
        return false;
    }

    /**
     * Attempts to transmit a disease from an uninfected human to a target human.
     *
     * @param source the human who may transmit the disease
     * @param target the human who may receive the disease
     * @param disease the disease being transmitted
     * @return true if transmission succeeds, false otherwise
     * @throws IllegalArgumentException if any argument is null
     */
    boolean transmit(Human source, Human target, Disease disease);
}