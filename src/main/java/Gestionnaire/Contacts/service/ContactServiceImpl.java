package Gestionnaire.Contacts.service;

import Gestionnaire.Contacts.exeption.ContactException;
import Gestionnaire.Contacts.model.ContactModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ContactServiceImpl implements ContactService {

   private List<ContactModel> contacts = new ArrayList<>();

    @Override
   public List<ContactModel> getAllContacts(){
        return contacts;
    }


    @Override
   public ContactModel addContact(ContactModel contact){
        contact.setId((long) contacts.size() + 1);
        contacts.add(contact);
        return contact;
    }


    @Override
   public ContactModel updateContact(Long id,ContactModel contact){

        for (ContactModel c : contacts){
            if (c.getId().equals(id)) {
                c.setFirstName(contact.getFirstName());
                c.setLastName(contact.getLastName());
                c.setEmail(contact.getEmail());
                c.setPhone(contact.getPhone());
                return contact;
            }
        }
       throw new ContactException("Contact non trouve !");
    }

    @Override
   public void deleteContact(Long id){
        boolean removed = contacts.removeIf(c -> c.getId().equals(id));
        if (!removed) {
            throw new ContactException("Contact non trouve !");
        }
    }
}
