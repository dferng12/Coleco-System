package entities;

import java.util.List;

public abstract class User {
    private String name;
    private String subname;
    private DNI dni;
    private int age;
    private String email;
    private Address address;
    private int phoneNumber;
    private AuthInfo authInfo;

    public List<Subject> getSubjects() {
        return null;
    }
}
