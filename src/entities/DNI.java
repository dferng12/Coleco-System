package entities;

public class DNI implements Handler{
    private int numbers;
    private char letter;

    public DNI createDNI(int numbers, char letter){
        if (letterIsCorrect(numbers, letter)){
            return new DNI(numbers, letter);
        }else{
            return null;
        }
    }

    private DNI(int numbers, char letter){
        this.numbers = numbers;
        this.letter = letter;
    }


    private boolean letterIsCorrect(int numbers, int letter){
        String LETTERS_ORDER = "TRWAGMYFPDXBNJZSQVHLCKE";

        return LETTERS_ORDER.charAt(numbers % 23 ) == letter;
    }

    @Override
    public String toString() {
        return String.valueOf(numbers) + letter;
    }

    @Override
    public boolean compareTo(Handler handler) {
        return this.toString().equals(handler.toString());
    }
}
