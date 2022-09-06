package telrun.anotherTime.tests;

import static org.junit.Assert.*;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;

import org.junit.jupiter.api.Test;

import telrun.anotherTime.BarMizvaAdjuster;
import telrun.anotherTime.NextFriday13;
import telrun.anotherTime.WorkingDaysAdjuster;

public class DateTimeOperationsTests {

	@Test
	void dateOperationTest1() {
		LocalDate birthAS = LocalDate.of(1799, 6, 6);
		LocalDate barMizvaAS = birthAS.plusYears(13);

		assertEquals(LocalDate.of(1812, 6, 6), barMizvaAS);
	}
	

	
	@Test
	void getPersonAge () {
		String format = "birsday is %s, today is %s, age is %d\n";
		
		LocalDateTime currentSec = LocalDateTime.now();
		System.out.printf(format, LocalDateTime.of(2000, 1, 1,0,0,0), currentSec, 
				ChronoUnit.SECONDS.between(LocalDateTime.of(2000, 1, 1,0,0,0), currentSec));
		
		LocalDate current = LocalDate.now();
		System.out.printf(format, LocalDate.of(0001, 1, 1), current, 
				ChronoUnit.YEARS.between(LocalDate.of(6666, 1, 1), current));
	}	
	
	@Test
	void nextFriday13Test () {
		TemporalAdjuster nextFriday13 = new NextFriday13();
		assertEquals(LocalDate.of(2023, 1, 13), LocalDate.of(2022, 9, 5).with(nextFriday13));
		assertEquals(LocalDate.of(2023, 10, 13), LocalDate.of(2023, 1, 13).with(nextFriday13));
	}
	
	@Test
	void adjusterTest () {
		LocalDate birthAS = LocalDate.of(1799, 6, 6);
		LocalDateTime lt = LocalDateTime.now();
		assertEquals(LocalDate.of(1812, 6, 6), birthAS.with(new BarMizvaAdjuster()));
		System.out.println(lt.with(new BarMizvaAdjuster()));
	}
	
	@Test
	void workingDaysTest () {
		WorkingDaysAdjuster workingDays = new WorkingDaysAdjuster();
		workingDays.setDaysOff(new int [] {1,2,3,4,5,6,7});
		workingDays.setnDays(1000);
		assertEquals(LocalDate.now(), LocalDate.now().with(workingDays));
		
		workingDays.setDaysOff(new int [] {});
		assertEquals(LocalDate.now().plusDays(1000), LocalDate.now().with(workingDays));
		
		workingDays.setDaysOff(new int [] {5, 6});
		workingDays.setnDays(7);
		assertEquals(LocalDate.of(2022, 9, 14), LocalDate.of(2022, 9, 5).with(workingDays));
	}
}
