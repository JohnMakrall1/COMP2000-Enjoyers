import java.util.Random;

/**
 * Transmits disease when a human enters one of an infected human's
 * six most recently walked-over cells.
 */
public class AirborneTransmission implements TransmissionStrategy {

    private final Random random = new Random();

    @Override
    public boolean usesAirborneTrail() {
        return true;
    }

    @Override
    public boolean transmit(Human source, Human target, Disease disease) {
        if (source == null || target == null || disease == null) {
            throw new IllegalArgumentException(
                    "Source, target, and disease cannot be null."
            );
        }

        if (source == target || !source.isInfected() || target.isInfected()) {
            return false;
        }

        Cell targetCell = target.loc;
        if (targetCell == null || !source.hasAirborneTrailAt(targetCell)) {
            return false;
        }

        double probability = Math.max(
                0.0,
                Math.min(1.0, disease.getInfectionRate() * target.health())
        );
        if (random.nextDouble() < probability) {
            target.infect(disease);
            return true;
        }

        return false;
    }
}
