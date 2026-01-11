package practice;

import java.util.Arrays;
import java.util.function.Predicate;
import model.Candidate;

public class CandidateValidator implements Predicate<Candidate> {
    private static final int MIN_AGE = 35;
    private static final int MIN_PERIOD_IN_UKRAINE = 10;
    private static final String NATIONALITY = "Ukrainian";

    @Override
    public boolean test(Candidate candidate) {
        if (candidate == null) {
            return false;
        }
        if (candidate.getAge() < MIN_AGE
                || !candidate.getNationality().equals(NATIONALITY)
                || !candidate.isAllowedToVote()) {
            return false;
        }
        String periodsInUkr = candidate.getPeriodsInUkr();
        if (periodsInUkr != null && !periodsInUkr.isEmpty()) {
            int totalYears = 0;
            String[] partsOfPeriod = periodsInUkr.trim().split("-");
            int[] years = Arrays.stream(partsOfPeriod)
                    .mapToInt(Integer::parseInt)
                    .toArray();
            totalYears = years[1] - years[0];
            return totalYears >= MIN_PERIOD_IN_UKRAINE;
        }
        return false;
    }
}
