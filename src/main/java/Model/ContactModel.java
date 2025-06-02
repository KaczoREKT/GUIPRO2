package Model;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class ContactModel extends AbstractListModel<Contact> {
    private List<Contact> contacts = new ArrayList<>();
    private static final String CONTACTS_FILE = "contacts.ser";

    public ContactModel() {
        loadContacts();
    }

    @Override
    public int getSize() { return contacts.size(); }

    @Override
    public Contact getElementAt(int index) { return contacts.get(index); }

    public boolean addContact(Contact contact) {
        if (isEmailUsed(contact.getEmail())) {
            return false;
        }
        contacts.add(contact);
        fireIntervalAdded(this, contacts.size()-1, contacts.size()-1);
        saveContacts();
        return true;
    }

    public boolean isEmailUsed(String email) {
        for (Contact c : contacts) {
            if (c.getEmail().equalsIgnoreCase(email)) return true;
        }
        return false;
    }

    private void saveContacts() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(CONTACTS_FILE))) {
            out.writeObject(contacts);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Błąd zapisu kontaktów: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void loadContacts() {
        File file = new File(CONTACTS_FILE);
        if (!file.exists()) return;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(CONTACTS_FILE))) {
            contacts = (List<Contact>) in.readObject();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Błąd odczytu kontaktów: " + e.getMessage());
        }
    }
}
