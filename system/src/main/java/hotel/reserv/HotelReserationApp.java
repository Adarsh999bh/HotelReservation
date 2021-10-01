package hotel.reserv;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public final class HotelReserationApp {
    /**
     * Says welcome.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        System.out.println("Welcome.!");
        List<Hotel> hotels=new ArrayList<>();
        hotels.add(new Hotel("Lakewood ",3,110, 90, 80,80));
        hotels.add(new Hotel("Bridgewood ",4,160, 60, 110,50));
        hotels.add(new Hotel("Ridgewood ",5,220, 150, 100,40));

    }
}
