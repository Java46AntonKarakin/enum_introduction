package telrun.time.test;

import static org.junit.Assert.*;
import org.junit.Test;
import telrun.time.DayOfWeek;

public class DayOfWeekTests {
private static final String SUNDAY = "Sunday";
private static final String SATURDAY = "Saturday";
private static final String OTHER_DAY = "other day of weed";

public String getFullNameOfDayOfWeek(DayOfWeek weekDay) {
	switch (weekDay) {
	case SUN: return SUNDAY;
	case SAT: return SATURDAY;
	default: return OTHER_DAY;
	}
}

@Test
public void hebrewDayTest () {
	assertEquals(SUNDAY, getFullNameOfDayOfWeek(DayOfWeek.SUN));
	assertEquals(SATURDAY, getFullNameOfDayOfWeek(DayOfWeek.SAT));
}

@Test
public void toStringTest () {
	assertEquals("FRI", DayOfWeek.FRI.toString());
}

// TODO *unknown fail from CW*
//@Test
public void valueOfTest () {
	assertEquals(DayOfWeek.SAT, DayOfWeek.valueOf("Saturday"));
	assertEquals(DayOfWeek.SAT, DayOfWeek.valueOf("SATURDAY"));
}

@Test
public void compareTo () {
	assertTrue(DayOfWeek.SAT.compareTo(DayOfWeek.SUN) > 0);
}

@Test
public void plusDaysTest () {
	assertEquals(DayOfWeek.SAT, DayOfWeek.THU.plusDays(2));
	assertEquals(DayOfWeek.THU, DayOfWeek.THU.plusDays(7));
	assertEquals(DayOfWeek.SAT, DayOfWeek.THU.plusDays(1003));
	
}

@Test
public void betweenTest () {
	assertEquals(2, DayOfWeek.between(DayOfWeek.THU, DayOfWeek.SAT));
	assertEquals(6, DayOfWeek.between(DayOfWeek.THU, DayOfWeek.WED));
	assertEquals(7, DayOfWeek.between(DayOfWeek.SAT, DayOfWeek.SAT));
}



}
