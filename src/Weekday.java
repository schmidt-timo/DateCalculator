/**
 * This class is for outputting a weekday
 */
public enum Weekday {
	
	MONDAY(0, "Monday"),
    TUESDAY(1, "Tuesday"),
    WEDNESDAY(2, "Wednesday"),
    THURSDAY(3, "Thursday"),
    FRIDAY(4, "Friday"),
    SATURDAY(5, "Saturday"),
    SUNDAY(6, "Sunday");
	
	private int day;
	private String name;
	
	private Weekday(final int day, final String name) {
		this.day = day;
		this.name = name;
	}	
	
	public int getDay() {
		return day;
	}
	
	public String getName() {
		return name;
	}
}
