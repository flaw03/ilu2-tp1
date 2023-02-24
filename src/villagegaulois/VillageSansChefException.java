package villagegaulois;

public class VillageSansChefException extends Exception {

    public VillageSansChefException(){
        super("Village sans cheff");
    }

    public VillageSansChefException(String message){
        super(message);
    }

    public VillageSansChefException(String message, Throwable cause){
        super(message,cause);
    }

    public VillageSansChefException (Throwable cause){
        super(cause);
    }
}
