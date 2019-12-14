public class JulianDate {

	long jd;

	//constructor
	public JulianDate() {
		
	}

	public JulianDate(long julianDate) {
		jd = julianDate;
	}
	
	public long getJD() {
		return jd;
	}

	public void add(int day) {
		jd += day;
	}

	public int difference(JulianDate subtrahend) {
		return (int) Math.abs(jd - subtrahend.getJD());
	}

	/**
	 * Method to calculate the Gregorian Date out of a Julian Date
	 * Method fixed, Source: https://coderanch.com/t/410264/java/Julian-Gregorian-date-conversion
	 */
	public GregorianDate getGregorian() {
		int JGREG = 15 + 31*(10+12*1582);
		double HALFSECOND = 0.5;

		int jalpha,jb,jc,JD,je,year,month,day;
		double julian = jd + HALFSECOND / 86400.0;
		int ja = (int) jd;
		if (ja>= JGREG) {
			jalpha = (int) (((ja - 1867216) - 0.25) / 36524.25);
			ja = ja + 1 + jalpha - jalpha / 4;
		}

		jb = ja + 1524;
		jc = (int) (6680.0 + ((jb - 2439870) - 122.1) / 365.25);
		JD = 365 * jc + jc / 4;
		je = (int) ((jb - JD) / 30.6001);
		day = jb - JD - (int) (30.6001 * je);
		month = je - 1;
		if (month > 12) month = month - 12;
		year = jc - 4715;
		if (month > 2) year--;
		if (year <= 0) year--;

		return new GregorianDate(year, month, day);
	}

	public String weekday() {
		for(Weekday s : Weekday.values()) {
			if(jd % 7 == s.getDay()) {
				return s.getName();
			}
		}   
		return null;
	}

	public String toString() {
		return jd + "";
	}

}
