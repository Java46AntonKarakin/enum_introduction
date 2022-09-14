package telrun.anotherTime.application;

import java.time.temporal.ChronoUnit;
import java.util.Arrays;

public class Reminder {

	public static void main(String[] args) throws Exception {
		try {
			int[] stepAndDuration = getStepAndDuration(args);
			ring(stepAndDuration[0], stepAndDuration[1]);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static int[] getStepAndDuration(String[] args) throws Exception {
		int[] res = new int[2];
		ChronoUnit unit = getUnit(args[1]);
		res[0] = convertToMillis(Integer.parseInt(args[0]), unit);
		res[1] = args.length > 2 ? convertToMillis(Integer.parseInt(args[2]), unit)
				: convertToMillis(1, ChronoUnit.HOURS);
		return res;
	}

	private static void ring(int step, int duration) throws InterruptedException {
		int counter = duration / step;
		while (counter-- != 0) {
			Thread.sleep(step);
			System.out.println("\007\007\007");
		}
	}

	private static int convertToMillis(int amountOfUnits, ChronoUnit unit) {
		return (int) unit.getDuration().toMillis() * amountOfUnits;
	}

	private static ChronoUnit getUnit(String inputValue) throws Exception {
		var res = inputValue.toUpperCase();
		boolean isInputValueCorrect = Arrays.stream(ChronoUnit.values())
				.anyMatch(unit -> res.equals(unit.toString().toUpperCase()));
		if (!isInputValueCorrect) {
			throw new Exception(String.format(" '%s' is incorrect. Plz enter correct value", inputValue));
		}
		return ChronoUnit.valueOf(res);
	}
}
