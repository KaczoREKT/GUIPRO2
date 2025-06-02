package Controller;

import Model.Contact;
import Model.ContactModel;

import javax.swing.*;

public class ContactController {
    private ContactModel contactModel;

    public ContactController(ContactModel model) {
        this.contactModel = model;
    }

    public boolean addContact(String firstName, String lastName, String email) {
        if (firstName == null || firstName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Imię nie może być puste.");
            return false;
        }
        if (lastName == null || lastName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nazwisko nie może być puste.");
            return false;
        }
        if (email == null || email.trim().isEmpty() || !isValidEmail(email)) {
            JOptionPane.showMessageDialog(null, "Nieprawidłowy adres email.");
            return false;
        }
        if (contactModel.isEmailUsed(email)) {
            JOptionPane.showMessageDialog(null, "Kontakt z tym adresem email już istnieje.");
            return false;
        }
        Contact contact = new Contact(firstName, lastName, email);
        return contactModel.addContact(contact);
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[\\w\\.-]+@[\\w\\.-]+\\.[a-zA-Z]{2,}$");
    }
}
