import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

/**
 * A graphical user interface for the calculator. No calculation is being
 * done here. This class is responsible just for putting up the display on 
 * screen. It then refers to the "CalcEngine" to do all the real work.
 *
 * @author David J. Barnes and Michael Kolling
 *         extended by Timo Schmidt, Pavel Tsvyatkov
 *         changed by Timo Schmidt, Sophia Stölzle
 * @version 2019.12.08
 */
public class UserInterface
    implements ActionListener
{
    protected CalcEngine calc;
    protected boolean showingAuthor;

    protected JFrame frame;
    protected JTextField display;
    private JLabel status;

    private JLabel currDate;

    /**
     * Create a user interface.
     * @param engine The calculator engine.
     */
    public UserInterface(CalcEngine engine)
    {
        calc = engine;
        showingAuthor = true;
        makeFrame();
        frame.setVisible(true);
    }

    /**
     * Set the visibility of the interface.
     * @param visible true if the interface is to be made visible, false otherwise.
     */
    public void setVisible(boolean visible)
    {
        frame.setVisible(visible);
    }

    /**
     * Make the frame for the user interface.
     */
    protected void makeFrame()
    {
        frame = new JFrame(calc.getTitle());
        
        JPanel contentPane = (JPanel)frame.getContentPane();
        contentPane.setLayout(new BorderLayout(8, 8));
        contentPane.setBorder(new EmptyBorder( 10, 10, 10, 10));

        display = new JTextField();
        contentPane.add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 4));
        
            addButton(buttonPanel, "7", null);
            addButton(buttonPanel, "8", null);
            addButton(buttonPanel, "9", null);
            addButton(buttonPanel, "clear", "Clear the display");

            addButton(buttonPanel, "4", null);
            addButton(buttonPanel, "5", null);
            addButton(buttonPanel, "6", null);
            addButton(buttonPanel, "+", "Add a number of days to the date");
            
            addButton(buttonPanel, "1", null);
            addButton(buttonPanel, "2", null);
            addButton(buttonPanel, "3", null);
            addButton(buttonPanel, "-", "Either subtract a number of days from the date or " +
                                                              "subtract two dates with each other and get the number of days in between");

            buttonPanel.add(new JLabel());
            addButton(buttonPanel, "0", null);
            addButton(buttonPanel, ".", null);
            addButton(buttonPanel, "weekday", "Get the weekday of this date");

            addButton(buttonPanel, "?", "Get version number");
            buttonPanel.add(new JLabel("Current date:  ", SwingConstants.RIGHT));
            buttonPanel.add(currDate = new JLabel("0.0.0"));
            addButton(buttonPanel, "=", "Calculate result");

        contentPane.add(buttonPanel, BorderLayout.CENTER);

        status = new JLabel(calc.getAuthor());
        contentPane.add(status, BorderLayout.SOUTH);

        // Welcome message
        display.setText("Start by putting in a date in format DD.MM.YYYY or D.M.YYYY");

        frame.pack();
    }

    /**
     * Add a button to the button panel.
     * @param panel The panel to receive the button.
     * @param buttonText The text for the button.
     */
    protected void addButton(Container panel, String buttonText, String tooltipText)
    {
        JButton button = new JButton(buttonText);
        button.addActionListener(this);
        if (tooltipText != null)
            button.setToolTipText(tooltipText);
        panel.add(button);
    }

    private String getDate() {
        return calc.getDate();
    }

    /**
     * An interface action has been performed.
     * Find out what it was and handle it.
     * @param event The event that has occured.
     */
    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
        
        switch(command) {
            case "clear":
        	    calc.clear();
                break;
            case "?":
        	    showInfo();
                break;
            case "=":
                calc.equals();
                break;
            case "+":
                calc.applyOperator('+');
                break;
            case "-":
                calc.applyOperator('-');
                break;
            case "weekday":
                calc.getWeekday();
                break;
    	    default:
        	    calc.buttonPressed(command);
        }
       
        // else unknown command
        redisplay();
    }

    /**
     * Update the interface display to show the current value of the 
     * calculator.
     */
    protected void redisplay(){
        display.setText(calc.getDisplayString());
        currDate.setText(calc.getDate());
    }

    /**
     * Toggle the info display in the calculator's status area between the
     * author and version information.
     */
    private void showInfo()
    {
        if(showingAuthor)
            status.setText(calc.getVersion());
        else
            status.setText(calc.getAuthor());

        showingAuthor = !showingAuthor;
    }
}
