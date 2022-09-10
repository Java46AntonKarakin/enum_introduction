package telrun.anotherTime.application;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Locale;

public class PrintCalendar {

	public static void main(String[] args) throws Exception {
		try {
			int[] monthYear = getMonthYears(args);
			printCalendar(monthYear[0], monthYear[1], monthYear[2]);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void printCalendar(int month, int year, int weekBeginsWith) {
		printTitle(month, year, weekBeginsWith);
		printWeekDays(weekBeginsWith);
		printDates(month, year, weekBeginsWith);
	}

	private static void printDates(int month, int year, int weekBeginsWith) {
		int column = getFirstColumn(month, year, weekBeginsWith);
		printOffset(column);
		int nDays = getMonthDays(month, year);
		int nWeedDays = DayOfWeek.values().length;
		for (int day = 1; day <= nDays; day++) {
			System.out.printf("%4d", day);
			column++;
			if (column >= nWeedDays) {
				column = 0;
				System.out.println();
			} else {
			}
		}
	}

	private static int getMonthDays(int month, int year) {
		YearMonth ym = YearMonth.of(year, month);
		return ym.lengthOfMonth();
	}

	private static void printOffset(int column) {
		System.out.printf("%s", " ".repeat(column * 4));
	}

	private static int getFirstColumn(int month, int year, int weekBeginsWith) {
		LocalDate firstMonthDate = LocalDate.of(year, month, 1);
		int monthBeginsWithDOW = firstMonthDate.getDayOfWeek().getValue();

		int res = monthBeginsWithDOW - weekBeginsWith;
		if (res < 0) {
			res += DayOfWeek.values().length;
		}
		return res;
	}

	private static void printWeekDays(int weekBeginsWith) {

		DayOfWeek[] actualOrder = DayOfWeek.values();
		if (weekBeginsWith != 0) {
			actualOrder = changeDaysOrder(actualOrder, weekBeginsWith);
		}
		System.out.print(" ");
		for (DayOfWeek weekDay : actualOrder) {
			System.out.printf("%s ", weekDay.getDisplayName(TextStyle.SHORT, Locale.getDefault()));
		}
		System.out.println();
	}

	private static DayOfWeek[] changeDaysOrder(DayOfWeek[] actualOrder, int weekBeginsWith) {
		DayOfWeek[] res = new DayOfWeek[7];
		for (int num = 0; num <= actualOrder.length - 1; num++) {
			res[num] = actualOrder[weekBeginsWith - 1];
			weekBeginsWith = weekBeginsWith >= actualOrder.length ? 1 : weekBeginsWith + 1;
		}
		return res;
	}

	private static void printTitle(int month, int year, int weekBeginsWith) {
		Month monthEn = Month.of(month);
		System.out.printf(" " + "%s, %d, week begins with: %s\n",
				monthEn.getDisplayName(TextStyle.SHORT, Locale.getDefault()), year,
				DayOfWeek.of(weekBeginsWith).toString());
	}

	private static int[] getMonthYears(String[] args) throws Exception {
		LocalDate curr = LocalDate.now();
		int[] res = { curr.getMonthValue(), curr.getYear(), DayOfWeek.values().length };
		if (args.length > 0) {
			res[0] = getMonth(args[0]);
			if (args.length > 1) {
				res[1] = getYear(args[1]);
				if (args.length > 2) {
					res[2] = getFirstDayOfWeek(args[2]);
				}
			}
		}
		return res;
	}

	private static int getFirstDayOfWeek(String inputValue) throws Exception {
		try {
			inputValue = inputValue.toUpperCase();
			boolean flIsMatch = false;
			if (inputValue.matches("([A-Z])*")) {
				for (var day : DayOfWeek.values()) {
					if (inputValue.equals(day.toString())) {
						flIsMatch = true;
						break;
					}
				}
				if (!flIsMatch) {
					throw new Exception(String.format(" <<%s>> is not a day of the week", inputValue));
				}
			} else { 
				throw new Exception(String.format(" <<%s>> is not a day of the week", inputValue));
			}

			return DayOfWeek.valueOf(inputValue).getValue();
		} catch (NumberFormatException e) {
			throw new Exception("plz enter the name of the day of the week on which the week should start");
		}

	}

	private static int getYear(String yearStr) throws Exception {
		try {
			int res = Integer.parseInt(yearStr);
			if (res <= 0) {
				throw new Exception("year value should be as positive as quokka.");
			}
			return res;
		} catch (NumberFormatException e) {
			throw new Exception("plz enter year value in format <YYYY>");
		}
	}

	private static int getMonth(String monthStr) throws Exception {
		try {
			var month = Month.values().length;
			int res = Integer.parseInt(monthStr);
			if (res < 1 || res > month) {
				throw new Exception(String.format("month %d is wrong value, should be [1, %d]", res, month));
			}
			return res;
		} catch (NumberFormatException e) {
			throw new Exception("plz enter month value in format <MM>");
		}
	}
}
