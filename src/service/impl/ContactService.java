package service.impl;

import entity.Contact;
import repository.ContactRepository;
import service.IService;

import java.util.List;

public class ContactService implements IService<Contact> {
    private static final ContactRepository contactRepository = new ContactRepository();

    @Override
    public List<Contact> getAll() {
        return contactRepository.getAll();
    }

    @Override
    public void save(Contact contact) {
        contactRepository.save(contact);
    }

    @Override
    public void update(Contact oldContact, Contact newContact) {
        contactRepository.update(oldContact, newContact);
    }

    @Override
    public boolean remove(String name) {
        return contactRepository.deleteByName(name);
    }

    @Override
    public List<Contact> findByName(String name) {
        return contactRepository.findByName(name);
    }

    @Override
    public void readFromFile() {
        contactRepository.readFromFile();
    }

    @Override
    public void writeToFile() {
        contactRepository.writeToFile();
    }
}