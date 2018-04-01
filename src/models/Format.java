package models;

public class Format {
	public static final String SEPARATOR = "-";

	
	private ProcessType formatType;
	private int length;
	private String value;
	
	public ProcessType getFormatType() {
		return formatType;
	}
	public void setFormatType(ProcessType formatType) {
		this.formatType = formatType;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "Format [formatType=" + formatType + ", length=" + length + ", value=" + value + "]";
	}
	
	public static Format createObjectFromString(String input) {
		try {
			String[] splitVals = input.split(SEPARATOR);
			Format format = new Format();
			try {
				format.setFormatType(ProcessType.valueOf(splitVals[0]));
			} catch (IllegalArgumentException iae) {
			}
			format.setLength(Integer.valueOf(splitVals[1]));
			format.setValue(splitVals[2]);
			return format;
		}
		catch (Exception e) {
			return emptyObject();
		}
	}
	
	public static Format emptyObject() {
		return new Format();
	}

}
