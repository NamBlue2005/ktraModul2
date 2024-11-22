package repository;

import entity.Contact;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ContactRepository {
    private static final String SRC_CONTACT = "contacts.csv";

    public List<Contact> getAll() {
        List<Contact> contacts = new ArrayList<>();
        File file = new File(SRC_CONTACT);

        if (file.exists()) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
                contacts = (List<Contact>) objectInputStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }
        }
        return contacts;
    }

    public void save(Contact contact) {
        List<Contact> contacts = getAll();
        contacts.add(contact);
        writeFile(contacts);
    }

    public void update(Contact oldContact, Contact newContact) {
        List<Contact> contacts = getAll();
        int index = contacts.indexOf(oldContact);
        if (index != -1) {
            contacts.set(index, newContact);
            writeFile(contacts);
        }
    }

    public boolean deleteByName(String name) {
        List<Contact> contacts = getAll();
        boolean removed = contacts.removeIf(contact -> contact.getName().equals(name));
        if (removed) {
            writeFile(contacts);
        }
        return removed;
    }

    public List<Contact> findByName(String name) {
        List<Contact> contacts = getAll();
        return contacts.stream()
                .filter(contact -> contact.getName().contains(name))
                .toList();
    }

    public void readFromFile() {
    }

    public void writeToFile() {
        List<Contact> contacts = getAll();
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(SRC_CONTACT))) {
            objectOutputStream.writeObject(contacts);
        } catch (IOException e) {
            System.out.println("Lỗi ghi tập tin: " + e.getMessage());
        }
    }

    private void writeFile(List<Contact> contacts) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(SRC_CONTACT))) {
            objectOutputStream.writeObject(contacts);
        } catch (IOException e) {
            System.out.println("Lỗi ghi tập tin: " + e.getMessage());
        }
    }
}