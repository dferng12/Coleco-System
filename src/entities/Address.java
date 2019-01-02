package entities;

public class Address {
    private String city;
    private String street;
    private int number;
    private int floor;
    private char letter;

    public Address(String city, String street, int number, int floor, char letter) {
        this.city = city;
        this.street = street;
        this.number = number;
        this.floor = floor;
        this.letter = letter;
    }

    @Override
    public String toString() {
        return street + " nº" + number + " " + floor + "º" + letter + " " + city ;
    }
}
