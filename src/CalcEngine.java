/**
 * The main part of the calculator doing the calculations.
 *
 * @author David J. Barnes and Michael Kolling
 *         extended by Timo Schmidt, Sophia Stölzle
 * @version 2019.12.14
 */
public class CalcEngine
{
    // The current value (to be) shown in the display.
    protected String displayString;

    // The current (Gregorian) date which is set.
    protected GregorianDate gd = new GregorianDate();

    // This field saves the last used operator
    private char lastOperator;

    // Constructor
    public CalcEngine() {
        clear();
    }
    
    /**
     * @return The value that should currently be displayed
     * on the calculator display.
     */
    public String getDisplayString() { 
    	return displayString; 
    }
    
    /**
     * A number or operator button was pressed.
     * Either start a new operand, or incorporate this number as
     * the least significant digit of an existing one.
     * @param c The number or operator pressed on the calculator.
     */
    public void buttonPressed(String c) { //number and operator
        displayString += c;
    }

    /**
     * The '=' button was pressed.
     * case '+': A number of days gets added to a date --> new Date
     * case '-': Either a number of days get substracted from a date --> new Date
     * or two dates get subtracted with each other --> number of days between
     */
    public void equals() {
        JulianDate jd = gd.convertToJulianDate();

        switch (lastOperator) {
            case '+':
                if (displayString.matches("\\d+")) {
                    jd.add(Integer.parseInt(displayString));
                    gd = jd.getGregorian();
                    displayString = gd.toString();
                } else
                    throw new IllegalArgumentException("Your input is not a number.");
                break;
            case '-':
                // If displayString is only a number (subtract days)
                if (displayString.matches("\\d+")) {
                    jd.add(-1 * Integer.parseInt(displayString));
                    gd = jd.getGregorian();
                    displayString = gd.toString();
                }
                // If displayString is a date (get days between)
                else if (displayString.matches("^(3[0-1]|[0-2]?[0-9])\\.(1[0-2]|0?[0-9])\\.[0-9]{4}$")) {
                    setDate();
                    JulianDate jd2 = gd.convertToJulianDate();
                    displayString = jd.difference(jd2) + "";
                }
                // If input is not correct
                else
                    throw new IllegalArgumentException("Your input is neither a number nor a date.");
                break;
        }
    }

    /**
     * Method to return the String of a Gregorian Date
     * @return a String of the Gregorian Date in format MM.DD.YYYY
     */
    public String getDate() {
        if (gd.getYear() == 0 && gd.getMonth() == 0 && gd.getDay() == 0)
            return "0.0.0";
        else
            return gd.toString();
    }

    /**
     * Method to determine which weekday the date is/was
     */
    public String getWeekday() {
        setDate();
        JulianDate jd = gd.convertToJulianDate();
        return jd.weekday();
    }

    /**
     * Sets the GregorianDate gd to the inputted date
     * Date gets split after each dot in the three single values for year, month and day.
     * Display will be emptied afterwards
     */
    public void setDate() {
        String[] dmy = displayString.split("\\.");

        gd.setYear(Integer.parseInt(dmy[2]));
        gd.setMonth(Integer.parseInt(dmy[1]));
        gd.setDay(Integer.parseInt(dmy[0]));
    }

    /**
     * Method to save the last used operator in the corresponding field
     * @param operator you want to apply
     */
    public void applyOperator(char operator) {
        lastOperator = operator;
        setDate();
        displayString = "";
    }

    /**
     * The 'AC' (clear) button was pressed.
     * Reset everything to a starting state.
     */
    public void clear(){
        displayString = "";
        gd = new GregorianDate(0,0,0);
    }

    /**
     * @return The title of this calculation engine.
     */
    public String getTitle()
    {
        return "Java Calculator";
    }

    /**
     * @return The author of this engine.
     */
    public String getAuthor()
    {
        return "David J. Barnes & Michael Kolling // ext. by Timo Schmidt & Sophia Stölzle";
    }

    /**
     * @return The version number of this engine.
     */
    public String getVersion()
    {
       return "Version 3.0";
    }

}
