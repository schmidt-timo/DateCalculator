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

	public GregorianDate getGregorian() {
		int day, month, year;
		double y = 4716, j = 1401, m = 2, n = 12,
				r = 4, p = 1461, v = 3, u = 5,
				s = 153, w = 2, B = 274277, C=-38;

		double f = jd + j + ((((4* jd +B)/146097)*3)/4) + C;
		double e = r*f+v;
		double g = (e%p)/r;
		double h = u*g+w;

		day = (int) ((h%s)/u);
		month = (int) (((h/s+m)%n)+1);
		year = (int) ((e/p)-y+(n+m-month)/n);

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
