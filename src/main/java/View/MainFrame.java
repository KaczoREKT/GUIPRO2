package View;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class MainFrame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::createAndShowGUI);
    }

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("PJATK MAIL");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLayout(new BorderLayout());

        // GÓRNY PANEL
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10)); // odstęp między przyciskami
        topPanel.setBackground(new Color(238, 238, 238)); // jasny szary
        topPanel.setBorder(new MatteBorder(0, 0, 2, 0, new Color(200, 200, 200))); // dolna szara linia

        // Ikony (opcjonalnie podmień ścieżki do ikon)
        // JButton createMailButton = new JButton("Utwórz mail", new ImageIcon("mail_icon.png"));
        // JButton createContactButton = new JButton("Utwórz kontakt", new ImageIcon("contact_icon.png"));
        JButton createMailButton = new JButton("Utwórz mail");
        JButton createContactButton = new JButton("Utwórz kontakt");
        createContactButton.addActionListener(e -> {
            AddContactDialog dialog = new AddContactDialog(frame);
            dialog.setVisible(true);
        });
        createMailButton.addActionListener(e -> {
            NewMailDialog dialog = new NewMailDialog(frame);
            dialog.setVisible(true);
        });



        // Styl przycisków jak na obrazku
        Dimension btnSize = new Dimension(140, 50);
        createMailButton.setPreferredSize(btnSize);
        createContactButton.setPreferredSize(btnSize);

        topPanel.add(createMailButton);
        topPanel.add(createContactButton);

        // LEWY PANEL
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        JLabel sentLabel = new JLabel("Wysłane wiadomości:");
        DefaultListModel<String> sentModel = new DefaultListModel<>();
        JList<String> sentList = new JList<>(sentModel);
        leftPanel.add(sentLabel, BorderLayout.NORTH);
        leftPanel.add(new JScrollPane(sentList), BorderLayout.CENTER);
        leftPanel.setPreferredSize(new Dimension(200, 0));

        // ŚRODKOWY PANEL
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 5, 8, 5);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel toLabel = new JLabel("DO:");
        JTextField toField = new JTextField(30);
        JLabel subjectLabel = new JLabel("TEMAT:");
        JTextField subjectField = new JTextField(30);

        gbc.gridx = 0; gbc.gridy = 0;
        centerPanel.add(toLabel, gbc);
        gbc.gridx = 1;
        centerPanel.add(toField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        centerPanel.add(subjectLabel, gbc);
        gbc.gridx = 1;
        centerPanel.add(subjectField, gbc);

        // Dodanie paneli do głównego okna
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(centerPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
