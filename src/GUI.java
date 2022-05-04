import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    private JPanel panel_Main;
    private JTextField textField1;
    private JButton button1;

    public GUI() {
        button1.addActionListener(e -> {
            if (textField1.getText().charAt(0) != '(')
                textField1.setText("("+textField1.getText()+")");
            BuechiState initState = new BuechiState(new LTL().parse(textField1.getText()));
            Thread thread = new Thread(new Run(initState));
            EventQueue.invokeLater(thread);
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("GUI");
        frame.setContentPane(new GUI().panel_Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
