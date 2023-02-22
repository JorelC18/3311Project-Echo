package NHPIVisualizer;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ComboBoxExample extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel mainPanel;
    private JLabel label;
    private JComboBox<String> comboBox;
    private JPanel subPanel;
    private CardLayout cardLayout;
    private JPanel provincePanel;
    private JComboBox<String> provinceComboBox1;
    private JComboBox<String> provinceComboBox2;
    private JPanel townPanel;
    private JComboBox<String> townComboBox1;
    private JComboBox<String> townComboBox2;

    public ComboBoxExample() {
        super("Main Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        // Create the main panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Create the left-side panel with a label and a combo box
        JPanel leftPanel = new JPanel(new BorderLayout());
        label = new JLabel("Select an option:", SwingConstants.LEFT);
        comboBox = new JComboBox<String>(new String[] {"Province", "Town"});
        leftPanel.add(label, BorderLayout.WEST);
        leftPanel.add(comboBox, BorderLayout.CENTER);
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(leftPanel);

        // Create the right-side panel with a CardLayout
        subPanel = new JPanel();
        cardLayout = new CardLayout();
        subPanel.setLayout(cardLayout);

        // Create the province panel with two combo boxes
        provincePanel = new JPanel();
        provinceComboBox1 = new JComboBox<String>(new String[] {"Province 1", "Province 2"});
        provinceComboBox2 = new JComboBox<String>(new String[] {"City 1", "City 2"});
        provincePanel.add(provinceComboBox1);
        provincePanel.add(provinceComboBox2);
        subPanel.add(provincePanel, "province");

        // Create the town panel with two combo boxes
        townPanel = new JPanel();
        townComboBox1 = new JComboBox<String>(new String[] {"Town 1", "Town 2"});
        townComboBox2 = new JComboBox<String>(new String[] {"Village 1", "Village 2"});
        townPanel.add(townComboBox1);
        townPanel.add(townComboBox2);
        subPanel.add(townPanel, "town");

        mainPanel.add(subPanel);

        // Add more stuff below the main panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(800, 300));
        bottomPanel.setBorder(BorderFactory.createTitledBorder("Other stuff"));
        mainPanel.add(bottomPanel);

        add(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ComboBoxExample();
    }

}
