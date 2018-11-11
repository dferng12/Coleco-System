package entities;

public class DNI {
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

    public boolean isEqual(DNI otherDni){
        return this.numbers == otherDni.numbers;
    }

    public boolean letterIsCorrect(int numbers, int letter){
        String LETTERS_ORDER = "TRWAGMYFPDXBNJZSQVHLCKE";

        return LETTERS_ORDER.charAt(numbers % 23 ) == letter;
    }

}
