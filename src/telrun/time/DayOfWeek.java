package telrun.time;

public enum DayOfWeek {
	SUN, MON, TUE, WED, THU, FRI, SAT;

	public DayOfWeek plusDays(int days) {
		int weekDayNum = ordinal();			// get serial number of this 
		int resultNum = (weekDayNum + days) % 7;
		return values()[resultNum];			// values() --->  get array of all DayOfWeek
	}

	public static int between (DayOfWeek day1, DayOfWeek day2) {
		int day1Num = day1.ordinal();
		int day2Num = day2.ordinal();
		if (day2Num <= day1Num) {
			day2Num+=7;
		}
		return day2Num - day1Num;
		}
	}
