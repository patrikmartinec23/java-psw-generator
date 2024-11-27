import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class GUI implements ActionListener, ChangeListener {

    private JLabel pswLabel;
    private JLabel lengthLabel;
    private JFrame frame;
    private JPanel panel;
    private JSlider slider;
    private JCheckBox numberCheckBox;
    private JCheckBox symbolCheckBox;

    private final char[] letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
    private final char[] numbers = "0123456789".toCharArray();
    private final char[] specialChars = "!@#$%&*()_+-=[];:',.<>?/".toCharArray();

    public GUI() {

        frame = new JFrame();

        numberCheckBox = new JCheckBox("123");
        symbolCheckBox = new JCheckBox("#$&");

        numberCheckBox.addActionListener(e -> generatePassword());
        symbolCheckBox.addActionListener(e -> generatePassword());

        slider = new JSlider(JSlider.HORIZONTAL, 6, 20, 12);
        slider.setMajorTickSpacing(2);

        slider.addChangeListener(this);

        lengthLabel = new JLabel("Password Length: " + slider.getValue());

        JButton button = new JButton("Generate Password");
        button.addActionListener(this);

        pswLabel = new JLabel("");

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(50,100,60,100));
        panel.setLayout(new GridLayout(0,1));
        panel.add(numberCheckBox);
        panel.add(symbolCheckBox);
        panel.add(slider);
        panel.add(lengthLabel);
        panel.add(button);
        panel.add(pswLabel);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Password Generator");
        frame.pack();
        generatePassword();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new GUI();
    }

    public void generatePassword() {
        int length = slider.getValue();

        ArrayList<Character> characterPool = new ArrayList<>();

        for (char c : letters) {
            characterPool.add(c);
        }

        if (numberCheckBox.isSelected()) {
            for (char c : numbers) {
                characterPool.add(c);
            }
        }

        if (symbolCheckBox.isSelected()) {
            for (char c : specialChars) {
                characterPool.add(c);
            }
        }

        Random random = new Random();
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char randomChar = characterPool.get(random.nextInt(characterPool.size()));
            password.append(randomChar);
        }

        pswLabel.setText(String.valueOf(password));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        generatePassword();
    }

    public void stateChanged(ChangeEvent e) {
        lengthLabel.setText("Password Length: " + slider.getValue());
        generatePassword();
    }
}
