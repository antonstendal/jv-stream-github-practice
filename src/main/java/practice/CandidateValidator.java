package practice;

import java.util.function.Predicate;
import model.Candidate;

public class CandidateValidator implements Predicate<Candidate> {
    private static final int MIN_AGE = 35;
    private static final int MIN_PERIOD_IN_UKRAINE = 10;

    @Override
    public boolean test(Candidate candidate) {
        if (candidate == null) {
            return false;
        }
        if (candidate.getAge() < MIN_AGE
                || !candidate.getNationality().equals("Ukrainian")
                || !candidate.isAllowedToVote()) {
            return false;
        }
        String periodsInUkr = candidate.getPeriodsInUkr();
        if (candidate.getPeriodsInUkr() != null && !candidate.getPeriodsInUkr().isEmpty()) {
            int totalYears = 0;
            for (String period : periodsInUkr.trim().split(",")) {
                String[] split = period.trim().split("-");
                if (!period.trim().isEmpty() && split.length == 2) {
                    try {
                        int startOfPeriod = Integer.parseInt(split[0].trim());
                        int endOfPeriod = Integer.parseInt(split[1].trim());
                        totalYears += endOfPeriod - startOfPeriod;
                    } catch (NumberFormatException ignored) {
                        throw new RuntimeException();
                    }
                }
            }

            return totalYears >= MIN_PERIOD_IN_UKRAINE;
        }
        return false;
    }
}
