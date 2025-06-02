package View;

import javax.swing.*;
import java.awt.*;
import Model.Contact;

public class AddContactDialog extends JDialog {
    public JTextField nameField = new JTextField(15);
    public JTextField surnameField = new JTextField(15);
    public JTextField emailField = new JTextField(15);
    public JButton addButton = new JButton("Dodaj");
    public JButton cancelButton = new JButton("Anuluj");

    public AddContactDialog(JFrame parent) {
        super(parent, "Utwórz nowy kontakt", true);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0; add(new JLabel("Imię:"), gbc);
        gbc.gridx = 1; add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; add(new JLabel("Nazwisko:"), gbc);
        gbc.gridx = 1; add(surnameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; add(new JLabel("Email:"), gbc);
        gbc.gridx = 1; add(emailField, gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton); buttonPanel.add(cancelButton);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        add(buttonPanel, gbc);

        pack();
        setLocationRelativeTo(parent);
    }

    public Contact getContactFromFields() {
        return new Contact(
                nameField.getText().trim(),
                surnameField.getText().trim(),
                emailField.getText().trim()
        );
    }
}
