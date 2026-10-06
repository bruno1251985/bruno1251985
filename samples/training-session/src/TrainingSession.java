package portfolio;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

/** A small, immutable domain example; independent of the private Hercul code. */
public record TrainingSession(Status status, List<WorkoutSet> sets) {
    public enum Status { ACTIVE, COMPLETED }

    public record WorkoutSet(String exercise, int repetitions, BigDecimal loadKg) {
        public WorkoutSet {
            if (exercise == null || exercise.isBlank()) {
                throw new IllegalArgumentException("Exercise is required");
            }
            exercise = exercise.strip();
            if (repetitions <= 0) {
                throw new IllegalArgumentException("Repetitions must be positive");
            }
            if (loadKg == null || loadKg.signum() < 0) {
                throw new IllegalArgumentException("Load must be non-negative");
            }
        }

        public BigDecimal volumeKg() {
            return loadKg.multiply(BigDecimal.valueOf(repetitions));
        }
    }

    public TrainingSession {
        if (status == null || sets == null) {
            throw new IllegalArgumentException("Status and sets are required");
        }
        sets = List.copyOf(sets);
        if (status == Status.COMPLETED && sets.isEmpty()) {
            throw new IllegalArgumentException("An empty session cannot be completed");
        }
    }

    public static TrainingSession start() {
        return new TrainingSession(Status.ACTIVE, List.of());
    }

    public TrainingSession recordSet(WorkoutSet set) {
        requireActive();
        if (set == null) throw new IllegalArgumentException("Set is required");
        return new TrainingSession(status, Stream.concat(sets.stream(), Stream.of(set)).toList());
    }

    public TrainingSession complete() {
        requireActive();
        if (sets.isEmpty()) throw new IllegalStateException("Record a set before completing");
        return new TrainingSession(Status.COMPLETED, sets);
    }

    public BigDecimal totalVolumeKg() {
        return sets.stream().map(WorkoutSet::volumeKg).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private void requireActive() {
        if (status != Status.ACTIVE) throw new IllegalStateException("Session is already completed");
    }
}
