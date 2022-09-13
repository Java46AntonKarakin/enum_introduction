package telrun.anotherTime.application;

import java.time.temporal.ChronoUnit;
import java.util.Arrays;

public class Reminder {

	public static void main(String[] args) throws Exception {
		try {
			int[] inputValues = adjustInpurValues(args);
			ring(inputValues[0], inputValues[1]);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static int[] adjustInpurValues(String[] args) throws Exception {
		int[] res = new int[2];
		ChronoUnit unit = getUnit(args[1]);
		res[0] = convertToMillis(Integer.parseInt(args[0]), unit);
		res[1] = args.length == 3 ? convertToMillis(Integer.parseInt(args[2]), unit)
				: convertToMillis(1, ChronoUnit.HOURS);
		return res;
	}

	private static void ring(int stepInMillis, int duration) throws InterruptedException {
		int counter = duration / stepInMillis;
		while (counter != 0) {
			Thread.sleep(stepInMillis);
			System.out.println("\007");
			counter--;
		}
	}

	private static int convertToMillis(int amountOfUnits, ChronoUnit unit) {
		return (int) unit.getDuration().toMillis() * amountOfUnits;
	}

	private static ChronoUnit getUnit(String inputValue) throws Exception {
		var res = inputValue.toUpperCase();
		boolean flIsMatch = Arrays.stream(ChronoUnit.values())
				.anyMatch(unit -> res.equals(unit.toString().toUpperCase()));
		if (!flIsMatch) {
			throw new Exception(String.format(" '%s' is incorrect. Plz enter correct value", inputValue));
		}
		return ChronoUnit.valueOf(res);
	}
}
