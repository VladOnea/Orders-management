package businessLayer.validators;

import model.Client;

import java.util.regex.Pattern;

public class TelephoneValidator implements Validator<Client>{
    private static final String TELEPHONE_PATTERN = "^(\\+4|)?(07[0-8]{1}[0-9]{1}|02[0-9]{2}|03[0-9]{2}){1}?(\\s|\\.|\\-)?([0-9]{3}(\\s|\\.|\\-|)){2}$";// for the telephone numbers of romanians

    public void validate(Client client) {
        Pattern pattern = Pattern.compile(TELEPHONE_PATTERN);
        if (!pattern.matcher(client.getTelephoneNumber()).matches()) {
            throw new IllegalArgumentException("The telephone number you introduced is not a valid one for Romania!");
        }
    }

}
