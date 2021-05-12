package ua.edu.sumdu.j2se.Resendiz.CarRentalSystem.constants;

public enum Constants {
    ADMIN("ADMIN"),
    USER("USER"),
    PATH("/"),
    EDIT("/edit/**"),
    ADD("/add/**"),
    DELETE("/delete"),
    LOGIN("/login"),
    ERRORS("/errors/403"),
    INDEX("index"),
    HOME("/home"),
    REGISTER("register"),
    RESERVATION("reservation"),
    RESERVATIONS("reservations"),
    LOGUP("/logup"),
    CARS("cars"),
    CAR("car"),
    DATEFORMAT("d/MM/yyyy");

    private String value;

    Constants(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
