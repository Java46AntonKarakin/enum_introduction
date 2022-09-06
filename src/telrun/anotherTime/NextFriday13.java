package telrun.anotherTime;

import java.time.*;
import java.time.temporal.*;

public class NextFriday13 implements TemporalAdjuster {

	// returns temporal matching Friday 13
	// Friday is ChronoField.DAY_OF_WEEK == DayOfWeek.FRIDAY.ordinal()+1
	// 13 day of month ChronoField.DAY_OF_MONTH == 13

	@Override
	public Temporal adjustInto(Temporal temporal) {
		var res = temporal.plus(1, ChronoUnit.DAYS);
		while (ChronoField.DAY_OF_WEEK.getFrom(res) != DayOfWeek.FRIDAY.ordinal()+1) {
			res = res.plus(1, ChronoUnit.DAYS);
		}
		while  (ChronoField.DAY_OF_MONTH.getFrom(res) != 13) {
			res = res.plus(1, ChronoUnit.WEEKS);
		}
		return res;
	}

}