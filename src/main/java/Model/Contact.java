package Model;

import java.io.Serializable;

public class Contact implements Serializable {
    private String firstName;
    private String lastName;
    private String email;

    public Contact(String firstName, String lastName, String email) {
        this.firstName = firstName.trim();
        this.lastName = lastName.trim();
        this.email = email.trim().toLowerCase();
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return firstName + " " + lastName + " <" + email + ">";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;
        Contact c = (Contact) o;
        return email.equalsIgnoreCase(c.email);
    }

    @Override
    public int hashCode() {
        return email.toLowerCase().hashCode();
    }
}
