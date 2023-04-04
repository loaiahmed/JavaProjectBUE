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
    private Airport Arrival_airport;
    private Airport Departure_airport;
    private double Distance;
    static int count = 0;

    public Route(Airport Arrival_airport, Airport Departure_airport, double Distance) {
        this.RouteNumber = count;
        this.Arrival_airport = Arrival_airport;
        this.Departure_airport = Departure_airport;
        this.Distance = Distance;
        count++;
    }

    public Route() {
        this.RouteNumber = count;
        count++;
    }

    public Airport getArrival_airport() {
        return Arrival_airport;
    }

    public Airport getDeparture_airport() {
        return Departure_airport;
    }

    public double getDistance() {
        return Distance;
    }

    public int getRouteNumber() {
        return RouteNumber;
    }

    public void setArrival_airport(Airport Arrival_airport) {
        this.Arrival_airport = Arrival_airport;
    }

    public void setDeparture_airport(Airport Departure_airport) {
        this.Departure_airport = Departure_airport;
    }

    public void setRouteNumber(int RouteNumber) {
        this.RouteNumber = RouteNumber;
    }

    public void setDistance(double Distance) {
        this.Distance = Distance;
    }

    @Override
    public String toString() {
        return super.toString(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return RouteNumber == route.RouteNumber && Double.compare(route.Distance, Distance) == 0 && Objects.equals(Arrival_airport, route.Arrival_airport) && Objects.equals(Departure_airport, route.Departure_airport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(RouteNumber, Arrival_airport, Departure_airport, Distance);
    }
}
