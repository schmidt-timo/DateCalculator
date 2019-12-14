public class GregorianDate {

	private int year, month, day;

	//constructors

	public GregorianDate() {

	}

	public GregorianDate(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}

	// Getters

	public int getDay() {
		return day;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}

	// Setters

	public void setDay(int day) {
		boolean has30days = month == 4 || month == 6 || month == 9 || month == 11;

		if (day < 1) {
			this.day = 1;
			System.out.println("You can't use a day smaller than 1. Value was set to 1.");
		} else if (day < 28)
			this.day = day;

		else if (day > 28 && day <= 31) {
			if (month == 0) {
				System.out.println("You need to set month first to ensure this month has that many days.");
			} else if (has30days) {
				this.day = 30;
				System.out.println("This month only has 30 days. Value was set to 30.");
			} else if (month == 2) {
				if (year == 0) {
					System.out.println("You need to set a year first to determine whether it is/was a leap year.");
				} else if (isLeapYear()) {
					this.day = 29;
					System.out.println("This year is/was a leap year. Value was set to 29.");
				} else {
					this.day = 28;
					System.out.println("This month only has 28 days. Value was set to 28.");
				}
			} else
				this.day = day;
		} else if (day > 31) {
			this.day = 1;
			System.out.println("You can't use a day greater than 31. Value was set to 1.");
		} else
			this.day = day;
	}

	public void setMonth(int month) {
		if (month < 1) {
			this.month = 1;
			System.out.println("You can't use a month smaller than 1. Value was set to 1.");
		} else if (month > 12) {
			this.month = 1;
			System.out.println("You can't use a month greater than 12. Value was set to 1.");
		} else
			this.month = month;
	}

	public void setYear(int year) {
		if (year < 1000)
			System.out.println("This year is not valid. Remember to type in your year in format YYYY.");
		else
			this.year = year;
	}
	
	// Methods

	public boolean isLeapYear() {
		return ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0));
	}

	public JulianDate convertToJulianDate() {
		int Y, M, D;

		if (month > 2) {
			Y = year;
			M = month;
		} else {
			Y = year - 1;
			M = month + 12;
		} 	D = day;

		double a = 2 - (Y/100) + (Y/400);
		double b = (365.25 * (Y + 4716));
		double c = (30.6001* (M +1));
		long jd = (long) (a + b + c + D - 1524.5);
		return new JulianDate(jd);
	}

	public String toString() {
		String DAY = (day < 10) ? "0" + day : day + "";
		String MONTH = (month < 10) ? "0" + month : month + "";
		return DAY + "." + MONTH + "." + year;
	}

}
