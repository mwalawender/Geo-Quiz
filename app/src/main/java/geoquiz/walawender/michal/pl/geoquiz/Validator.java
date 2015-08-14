package geoquiz.walawender.michal.pl.geoquiz;

public class Validator {

    public boolean validateData(String loadedLogin, String inputLogin, String loadedPassword, String inputPassword){
        boolean validationResult = false;
        if(loadedLogin.equals(inputLogin)){
            if(loadedPassword.equals(inputPassword)){
                validationResult = true;
            } else {
                validationResult = false;
            }
        }
        return validationResult;
    }
}
