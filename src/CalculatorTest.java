import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    CalcEngine calc;
    UserInterface gui;

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
    }
}