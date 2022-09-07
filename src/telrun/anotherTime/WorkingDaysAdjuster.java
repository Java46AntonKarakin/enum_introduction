package telrun.anotherTime;

import java.time.LocalDate;
import java.time.temporal.*;
import java.util.Arrays;

public class WorkingDaysAdjuster implements TemporalAdjuster {

	int[] daysOff;
	int nDays;

	public final int[] getDaysOff() {
		return daysOff;
	}

	public final void setDaysOff(int[] daysOf) {
		this.daysOff = daysOf;
	}

	public final int getnDays() {
		return nDays;
	}

	public final void setnDays(int nDays) {
		this.nDays = nDays;
	}

	@Override
	public Temporal adjustInto(Temporal temporal) {
		LocalDate res = LocalDate.from(temporal);
		if (Arrays.stream(daysOff).filter(x-> x>=1 && x<= 7).distinct().count()==7) {
			return temporal;
		}
		if (daysOff.length == 0) {
			return temporal.with(res.plus(nDays, ChronoUnit.DAYS));
		}
		
		while (nDays != 0) {
			res = res.plus(1, ChronoUnit.DAYS);
			if (!isWorkingDay(res)) {
				nDays--;
			}
		}
		return temporal.with(res);
	}

	private boolean isWorkingDay(Temporal temporal) {
		return Arrays.stream(daysOff).anyMatch(n->n==ChronoField.DAY_OF_WEEK.getFrom(temporal));
	}
}
