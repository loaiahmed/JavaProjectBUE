/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.Objects;

/**
 *
 * @author youssef
 */
public class Route {
    private int RouteNumber;
    private Airport originAirport;
    private Airport destinationAirport;
    static int count = 0;

    public Route(Airport originAirport, Airport destinationAirport) {
        this.RouteNumber = count;
        this.originAirport = originAirport;
        this.destinationAirport = destinationAirport;
        count++;
    }

    public Route() {
        this.RouteNumber = count;
        count++;
    }

    public Airport getOriginAirport() {
        return originAirport;
    }

    public Airport getDestinationAirport() {
        return destinationAirport;
    }

    public int getRouteNumber() {
        return RouteNumber;
    }

    public void setOriginAirport(Airport Arrival_airport) {
        this.originAirport = Arrival_airport;
    }

    public void setDestinationAirport(Airport Departure_airport) {
        this.destinationAirport = Departure_airport;
    }

    public void setRouteNumber(int RouteNumber) {
        this.RouteNumber = RouteNumber;
    }

    @Override
    public String toString() {
        return "Route{" +
                "RouteNumber=" + RouteNumber +
                ", Arrival_airport=" + originAirport.getName() +
                ", Departure_airport=" + destinationAirport.getName() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return RouteNumber == route.RouteNumber && Objects.equals(originAirport, route.originAirport) && Objects.equals(destinationAirport, route.destinationAirport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(RouteNumber, originAirport, destinationAirport);
    }
}
