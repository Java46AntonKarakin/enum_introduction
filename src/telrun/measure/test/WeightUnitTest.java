package telrun.measure.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import telrun.measure.WeightUnit;

class WeightUnitTest {

	@Test
	void convertValueTest() {
		assertEquals(10000f, WeightUnit.KG.convertValue(10, WeightUnit.GR));
	}
}
