package View;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class NewMailDialog extends JDialog {
    private JTextField toField;
    private JTextField subjectField;
    private JTextArea messageArea;

    public NewMailDialog(JFrame parent) {
        super(parent, "Utwórz nowy mail", true);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // DO + WYBIERZ
        JLabel toLabel = new JLabel("DO:");
        toField = new JTextField(18);
        JButton pickButton = new JButton("WYBIERZ");
        JPanel toPanel = new JPanel(new BorderLayout());
        toPanel.add(toField, BorderLayout.CENTER);
        toPanel.add(pickButton, BorderLayout.EAST);

        // TEMAT
        JLabel subjectLabel = new JLabel("TEMAT:");
        subjectField = new JTextField(25);

        // WIADOMOŚĆ
        messageArea = new JTextArea(12, 30);
        JScrollPane messageScroll = new JScrollPane(messageArea);

        // Przyciski na dole
        JButton sendButton = new JButton("WYŚLIJ");
        JButton cancelButton = new JButton("ODRZUĆ");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(sendButton);
        buttonPanel.add(cancelButton);

        // Układ
        gbc.gridx = 0; gbc.gridy = 0;
        add(toLabel, gbc);
        gbc.gridx = 1;
        add(toPanel, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(subjectLabel, gbc);
        gbc.gridx = 1;
        add(subjectField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        add(messageScroll, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(buttonPanel, gbc);

        pack();
        setLocationRelativeTo(parent);

        // Przycisk WYBIERZ – pokaż listę kontaktów do wyboru
        pickButton.addActionListener(e -> chooseContact());

        // Wyślij (tutaj możesz dodać własną logikę wysyłania/zapisywania)
        sendButton.addActionListener(e -> {
            String to = toField.getText().trim();
            String subject = subjectField.getText().trim();
            String message = messageArea.getText().trim();

            if (to.isEmpty() || subject.isEmpty() || message.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Wszystkie pola są wymagane!");
            } else {
                // WYSYŁKA MAILA
                try {
                    String fromEmail = "haszysznatan@interia.pl";
                    String smtpHost = "poczta.interia.pl";
                    int smtpPort = 587;
                    String smtpUser = "haszysznatan@interia.pl";
                    String smtpPass = "{haszysz420*}";

                    Email email = EmailBuilder
                            .startingBlank()
                            .from(fromEmail)
                            .to(to)
                            .withSubject(subject)
                            .withPlainText(message)
                            .buildEmail();

                    try (Mailer mailer = MailerBuilder
                            .withSMTPServer(smtpHost, smtpPort, smtpUser, smtpPass)
                            .buildMailer()) {
                        mailer.sendMail(email);
                    }

                    JOptionPane.showMessageDialog(this, "Mail został wysłany!");
                    dispose();

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Błąd podczas wysyłania maila:\n" + ex.getMessage());
                }
            }
        });


        cancelButton.addActionListener(e -> dispose());
    }

    // Wybierz kontakt z pliku contacts.csv
    private void chooseContact() {
        ArrayList<String> emails = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("contacts.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length >= 3) {
                    emails.add(tokens[2].trim());
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Nie znaleziono pliku kontaktów!");
            return;
        }
        if (emails.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Brak kontaktów do wyboru!");
            return;
        }
        String selected = (String) JOptionPane.showInputDialog(
                this,
                "Wybierz adres e-mail:",
                "Kontakty",
                JOptionPane.PLAIN_MESSAGE,
                null,
                emails.toArray(),
                emails.get(0)
        );
        if (selected != null) {
            toField.setText(selected);
        }
    }
}
