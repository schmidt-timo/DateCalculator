import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    CalcEngine calc;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        calc = new CalcEngine();
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @Test
    public void testWeekday() {

        // Test Timo's birthday (Friday)
        calc.buttonPressed("22.4.1994");
        assertEquals("Friday", calc.getWeekday());

        calc.clear();

        // Test Sophia's birthday (Monday)
        calc.buttonPressed("22.1.1996");
        assertEquals("Monday", calc.getWeekday());

        calc.clear();

        // Test today's date (Sunday)
        calc.buttonPressed("15.12.2019");
        assertEquals("Sunday", calc.getWeekday());

    }

    @Test
    public void testAddDays() {
        calc.buttonPressed("1.1.2000");
        calc.applyOperator('+');
        calc.buttonPressed("9");
        calc.equals();
        assertEquals("10.01.2000", calc.getDisplayString());

        calc.clear();

        calc.buttonPressed("14.12.2019");
        calc.applyOperator('+');
        calc.buttonPressed("17");
        calc.equals();
        assertEquals("31.12.2019", calc.getDisplayString());

        calc.clear();

        calc.buttonPressed("31.12.2019");
        calc.applyOperator('+');
        calc.buttonPressed("1");
        calc.equals();
        assertEquals("01.01.2020", calc.getDisplayString());
    }

    @Test
    public void testSubtractDays() {
        calc.buttonPressed("1.1.2000");
        calc.applyOperator('-');
        calc.buttonPressed("1");
        calc.equals();
        assertEquals("31.12.1999", calc.getDisplayString());

        calc.clear();

        calc.buttonPressed("14.12.2019");
        calc.applyOperator('-');
        calc.buttonPressed("13");
        calc.equals();
        assertEquals("01.12.2019", calc.getDisplayString());

        calc.clear();

        calc.buttonPressed("01.01.2020");
        calc.applyOperator('-');
        calc.buttonPressed("1");
        calc.equals();
        assertEquals("31.12.2019", calc.getDisplayString());
    }

    @Test
    public void testDaysBetween() {
        calc.buttonPressed("31.1.2020");
        calc.applyOperator('-');
        calc.buttonPressed("1.1.2020");
        calc.equals();
        assertEquals("30", calc.getDisplayString());

        calc.clear();

        calc.buttonPressed("31.12.2019");
        calc.applyOperator('-');
        calc.buttonPressed("31.12.2018");
        calc.equals();
        assertEquals("365", calc.getDisplayString());

        calc.clear();

        calc.buttonPressed("1.1.2020");
        calc.applyOperator('-');
        calc.buttonPressed("1.1");
        assertThrows(IllegalArgumentException.class, () -> calc.equals());
    }
}