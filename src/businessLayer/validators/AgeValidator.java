package businessLayer.validators;


import model.Client;

public class AgeValidator implements Validator<Client>{
    private static final int MIN_AGE= 18;
    public void validate(Client client){
        if(client.getAge()< MIN_AGE){
            throw new IllegalArgumentException("The age should be grater than 18!");
        }
    }
}
