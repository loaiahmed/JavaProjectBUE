/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.Objects;

/**
 *
 * @author youssef
 */
public class Airport {
    private int airportCode;
    private String name;
    private String city;
    private String country;

    public Airport() {}

    public Airport(int airportCode, String name, String city, String country) {
        this.airportCode = airportCode;
        this.name = name;
        this.city = city;
        this.country = country;
    }

    public int getAirportCode() {
        return airportCode;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getName() {
        return name;
    }

    public void setAirportCode(int airportCode) {
        this.airportCode = airportCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return super.toString(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airport airport = (Airport) o;
        return airportCode == airport.airportCode && Objects.equals(name, airport.name) && Objects.equals(city, airport.city) && Objects.equals(country, airport.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(airportCode, name, city, country);
    }
}
