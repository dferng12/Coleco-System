package Model;

public class DNI implements Handler{
    private String numbers;
    private char letter;

    public static DNI createDNI(String dni){
        if(dni.length() != 9){
            return null;
        }

        try{
            Integer.parseInt(dni.substring(0,8));
        }catch (NumberFormatException | NullPointerException e) {
            return null;
        }

        if (letterIsCorrect(Integer.parseInt(dni.substring(0,8)), dni.charAt(dni.length()-1))){
            return new DNI((dni.substring(0,8)), dni.charAt(dni.length()-1));
        }else{
            return null;
        }
    }

    private DNI(String numbers, char letter){
        this.numbers = numbers;
        this.letter = letter;
    }


    private static boolean letterIsCorrect(int numbers, char letter){
        String LETTERS_ORDER = "TRWAGMYFPDXBNJZSQVHLCKE";

        return LETTERS_ORDER.charAt(numbers % 23 ) == letter;
    }

    @Override
    public String toString() {
        return numbers + letter;
    }

    @Override
    public boolean compareTo(Handler handler) {
        return this.toString().equals(handler.toString());
    }
}
