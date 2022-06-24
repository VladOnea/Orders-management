package presentation;

import dataAccessLayer.ClientDAO;
import model.Client;

import java.util.List;

public class MainClass {
    public static void main(String[] args) {
       /* ClientDAO clientDAO=new ClientDAO();

        List<Client> clients=clientDAO.findAll();
        for(Client client:clients)
            System.out.println(client.getFirstName()+" "+client.getLastName());
        Client client=clientDAO.findById(30);
        client.setAge(33);
        clientDAO.delete(31);
        //clientDAO.insert(new Client("Andrei","Avram",20,"Bd-ul Unirii","aavram@sigmamail.com","0753649117","Baia Mare"));
        //System.out.printf(client.getFirstName());
        clientDAO.update(client,30);*/
        new Controller();
    }
}
