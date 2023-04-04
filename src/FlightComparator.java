import java.util.Comparator;

public class FlightComparator implements Comparator<Flight> {

    // Overriding compare()method of Comparator
    // for descending order of cgpa
    public int compare(Flight f1, Flight f2) {
        if (f1.getDepartureTime().isBefore( f2.getDepartureTime()))
            return 1;
        else if (f1.getDepartureTime().isAfter( f2.getDepartureTime()))
            return -1;
        return 0;
    }
}
