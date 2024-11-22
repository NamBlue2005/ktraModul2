package controller;

import entity.Contact;
import service.impl.ContactService;
import service.IService;

import java.util.List;

public class ContactController {
    private static final IService<Contact> contactService = new ContactService();

    public List<Contact> getAll() {
        return contactService.getAll();
    }

    public void save(Contact contact) {
        contactService.save(contact);
    }

    public void update(Contact oldContact, Contact newContact) {
        contactService.update(oldContact, newContact);
    }

    public void deleteByName(String name) {
        contactService.remove(name);
    }

    public List<Contact> searchByName(String name) {
        return contactService.findByName(name);
    }

    public void readFromFile() {
        contactService.readFromFile();
    }

    public void writeToFile() {
        contactService.writeToFile();
    }
}