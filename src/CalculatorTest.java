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
    public void testAddDays() {
        calc.buttonPressed("1.1.2000");
        calc.applyOperator('+');
        calc.buttonPressed("9");
        calc.equals();
        assertEquals("10.01.2000", calc.getDisplayString());

        calc.clear();

        calc.buttonPressed("14.12.2019");
        calc.applyOperator('+');
        System.out.println(calc.gd.convertToJulianDate());
        calc.buttonPressed("17");
        calc.equals();
        System.out.println(calc.gd.convertToJulianDate());
        System.out.println(calc.gd.convertToJulianDate().getGregorian());
        assertEquals("31.12.2019", calc.getDisplayString());
    }
}