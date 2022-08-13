
package fr.mdalle.tenisu.model;

public record Player(Country country, Data data, String firstname, int id, String lastname, String picture, String sex, String shortname) {
    public Double bmi() {
        return data.bmi();
    }

    public int wins() {
        return data.wins();
    }
}
