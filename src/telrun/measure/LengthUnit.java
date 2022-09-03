package telrun.measure;

public enum LengthUnit {
	MM(1f), CM(10f), IN(25.4f), FT(304.8f), M(1000f), KM(1000000f);

	float value;

	private LengthUnit(float value) {
		this.value = value;
	}

	public float getValue() {
		return value;
	}

	public Length between(Length l1, Length l2) {
		float res = l1.convertToMM() - l2.convertToMM();
		return new Length((res < 0 ? -res : res) / this.value, this);
	}
}
