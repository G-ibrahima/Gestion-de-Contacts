package Gestionnaire.Contacts.service;

import Gestionnaire.Contacts.DTO.ContactDTO;
import Gestionnaire.Contacts.exeption.ContactException;
import Gestionnaire.Contacts.model.ContactModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ContactServiceImpl implements ContactService {

   private List<ContactDTO.Response> contacts = new ArrayList<>();


    @Override
   public List<ContactDTO.Response> getAllContacts(){
        return contacts;
    }


    @Override
   public ContactDTO.Response addContact(ContactDTO.Request contact){
        ContactModel model = ContactDTO.toModel(contact);
        model.setId((long) contacts.size() + 1);
        ContactDTO.Response responseDTO = ContactDTO.toResponse(model);
        contacts.add(responseDTO);
        return responseDTO;
    }

    @Override
   public ContactDTO.Response updateContact(Long id,ContactDTO.Request contact){

        for (ContactDTO.Response c : contacts){
            if (c.getId().equals(id)) {
                c.setFirstName(contact.getFirstName());
                c.setLastName(contact.getLastName());
                c.setEmail(contact.getEmail());
                c.setPhone(contact.getPhone());
                return c;
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
