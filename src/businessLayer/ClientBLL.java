package businessLayer;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import businessLayer.validators.AgeValidator;
import businessLayer.validators.EmailValidator;
import businessLayer.validators.TelephoneValidator;
import businessLayer.validators.Validator;
import dataAccessLayer.ClientDAO;
import model.Client;



public class ClientBLL {
    private List<Validator<Client>> validators;
    private ClientDAO clientDAO;

    public ClientBLL() {
        validators = new ArrayList<Validator<Client>>();
        validators.add(new EmailValidator());
        validators.add(new TelephoneValidator());
        validators.add(new AgeValidator());

        clientDAO = new ClientDAO();
    }

    public Client findClientById(int id) {
        Client client = clientDAO.findById(id);
        if (client == null) {
            throw new NoSuchElementException("The client with id =" + id + " was not found!");
        }
        return client;
    }

    public List<Client> findAll(){
        List<Client> clients = clientDAO.findAll();
        if(clients == null && clients.size()==0) {
            throw new NoSuchElementException("Cannot display the clients!");
        }
        return clients;
    }

    public Client insert(Client client){
        for(Validator validator:validators)
            validator.validate(client);
      return  clientDAO.insert(client);
    }

   public Client update(Client client,int id){
       for(Validator validator:validators)
           validator.validate(client);
       return  clientDAO.update(client,id);
    }

    public void delete(int id){
        clientDAO.delete(id);
    }

}
