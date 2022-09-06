package telrun.anotherTime;

import java.time.temporal.*;

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
		if (daysOff.length == 7) {
			return temporal;
		}
		if (daysOff.length == 0) {
			return temporal.plus(nDays, ChronoUnit.DAYS);
		}

		while (nDays != 0) {
			temporal = temporal.plus(1, ChronoUnit.DAYS);
			if (isWorkingDay(temporal)) {
				nDays--;
			}
		}
		return temporal;
	}

	private boolean isWorkingDay(Temporal temporal) {
		for (int i = 0; i < daysOff.length; i++) {
			if (daysOff[i] == ChronoField.DAY_OF_WEEK.getFrom(temporal)) {
				return false;
			}
		}
		return true;
	}
}
