package telrun.measure;

public class Length implements Comparable<Length> {
	private final float amount;
	private final LengthUnit unit;

	public Length(float amount, LengthUnit unit) {
		this.amount = amount;
		this.unit = unit;
	}

	@Override
	public boolean equals(Object obj) {
		var inputValue = (Length) obj;
		return this.convertToMM() == inputValue.convertToMM();
	}

	@Override
	public int compareTo(Length o) {
		double res = this.convertToMM() - o.convertToMM();
		return res == 0 ? 0 : res > 0 ? 1 : -1;
	}

	public Length convert(LengthUnit unit) {
		return new Length((this.convertToMM() / unit.value), unit);
	}

	public String toString() {
		return String.format("%d%s", (int) this.getAmount(), this.unit);
	}

	float convertToMM() {
		return this.getUnit().value * getAmount();
	}

	public float getAmount() {
		return amount;
	}

	public LengthUnit getUnit() {
		return unit;
	}

}
