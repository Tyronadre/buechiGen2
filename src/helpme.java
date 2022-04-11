import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class helpme {
    private JLabel jLabel_1;
    private JTextField textField1;
    private JButton parseFormularButton;

    public helpme() {
        parseFormularButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);

            }
        });
    }
}
