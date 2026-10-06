package portfolio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import portfolio.TrainingSession.Status;
import portfolio.TrainingSession.WorkoutSet;

/** Dependency-free executable tests. Every failure exits with a non-zero status. */
public final class TrainingSessionTest {
    private static int checks;

    public static void main(String[] args) {
        var first = new WorkoutSet("  Squat  ", 10, new BigDecimal("12.25"));
        check(first.exercise().equals("Squat"), "normalises exercise name");
        check(first.volumeKg().compareTo(new BigDecimal("122.50")) == 0, "exact decimal volume");
        var empty = TrainingSession.start();
        check(empty.status() == Status.ACTIVE && empty.sets().isEmpty(), "starts active and empty");
        fails(IllegalStateException.class, empty::complete, "empty session cannot complete");
        var active = empty.recordSet(first).recordSet(new WorkoutSet("Row", 8, new BigDecimal("7.5")));
        check(empty.sets().isEmpty(), "recording does not mutate previous state");
        check(active.totalVolumeKg().compareTo(new BigDecimal("182.50")) == 0, "sums distinct sets");
        var completed = active.complete();
        check(completed.status() == Status.COMPLETED && completed.sets().size() == 2, "completion retains sets");
        fails(IllegalStateException.class, () -> completed.recordSet(first), "completed session rejects changes");
        fails(IllegalStateException.class, completed::complete, "duplicate completion rejected");
        fails(IllegalArgumentException.class, () -> empty.recordSet(null), "null set rejected");
        for (String name : new String[]{null, "", "  "}) {
            fails(IllegalArgumentException.class, () -> new WorkoutSet(name, 1, BigDecimal.ZERO), "invalid exercise");
        }
        for (int repetitions : new int[]{0, -1}) {
            fails(IllegalArgumentException.class, () -> new WorkoutSet("Squat", repetitions, BigDecimal.ZERO), "invalid repetitions");
        }
        fails(IllegalArgumentException.class, () -> new WorkoutSet("Squat", 1, new BigDecimal("-0.1")), "negative load");
        fails(IllegalArgumentException.class, () -> new WorkoutSet("Squat", 1, null), "missing load");
        check(new WorkoutSet("Push-up", 5, BigDecimal.ZERO).volumeKg().signum() == 0, "zero external load allowed");
        var source = new ArrayList<>(List.of(first));
        var snapshot = new TrainingSession(Status.ACTIVE, source);
        source.clear();
        check(snapshot.sets().size() == 1, "copies mutable input");
        fails(UnsupportedOperationException.class, () -> snapshot.sets().clear(), "output list is immutable");
        fails(IllegalArgumentException.class, () -> new TrainingSession(Status.COMPLETED, List.of()), "constructor preserves completion invariant");
        fails(IllegalArgumentException.class, () -> new TrainingSession(null, List.of()), "missing status");
        fails(IllegalArgumentException.class, () -> new TrainingSession(Status.ACTIVE, null), "missing sets");
        System.out.println("Passed " + checks + " checks.");
    }

    private static void check(boolean condition, String description) {
        if (!condition) throw new AssertionError(description);
        checks++;
    }

    private static void fails(Class<? extends Throwable> type, Runnable action, String description) {
        try { action.run(); }
        catch (Throwable error) {
            if (!type.isInstance(error)) throw new AssertionError(description, error);
            checks++;
            return;
        }
        throw new AssertionError(description + ": expected " + type.getSimpleName());
    }
}
