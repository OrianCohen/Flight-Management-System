package model.objects;

import java.util.Objects;

public class Passenger extends Person {

    static int passengersCount;
    private String passport;
    private long id;

    public Passenger() {
        super();
    }

    public Passenger(
            String firstName,
            String lastName,
            String email,
            String birthDateStr,
            String passport,
            long id
        ) {
        super(firstName, lastName, email, id, birthDateStr);
        this.passport = passport;
    }

    // Setters (which will be used by the builder)
    public void setPassport(String passport) {
        this.passport = passport;
    }

    // Getters
    public String getPassport() {
        return this.passport;
    }

    @Override
    public String toString() {
        return super.toString() + "\n"+
                " Passport: " + passport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Passenger passenger = (Passenger) o;
        return Objects.equals(passport, passenger.passport);
    }

}
