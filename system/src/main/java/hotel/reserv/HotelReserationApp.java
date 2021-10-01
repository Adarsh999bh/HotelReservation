package hotel.reserv;

import java.util.ArrayList;
import java.util.List;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Hello world!
 */
public final class HotelReserationApp {
    /**
     * Says welcome.
     * @param args The arguments of the program.
     */

    static List<Hotel> hotels=new ArrayList<>();


    static public String findCheapHotel(String customerType,String checkinDate,String checkoutDate) throws ParseException{
        String[] days={"mon","tue","wed","thu","fri","sat","sun"};
        SimpleDateFormat sdf=new SimpleDateFormat("dd MMM yyyy EEE");
        Date firstDate,secondDate;
        firstDate=sdf.parse(checkinDate);
        secondDate=sdf.parse(checkoutDate);
        long diffInMillis=Math.abs(secondDate.getTime() - firstDate.getTime());
        long diff=TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
        int currentDayIndex=IntStream.range(0, 7).filter(i -> checkinDate.substring(12,15).equals(days[i])).findFirst().orElse(-1);
        if(currentDayIndex==-1){
            return "Invalid Date format";
        }
        int sum,minSum=Integer.MAX_VALUE;
        String hotelName="";
        if(customerType.equals("Regular")){
            for(Hotel hotel:hotels){
                sum=0;
                int copyofCurrentDayIndex=currentDayIndex;
                for(int i=0;i<diff;i++,copyofCurrentDayIndex++){
                    if(days[copyofCurrentDayIndex].equals("sat") || days[copyofCurrentDayIndex].equals("sun")){
                        sum+=hotel.weekendCostForRegularCustomer;
                    }
                    else{
                        sum+=hotel.weekDayCostForRegularCustomer;
                    }
                    if(copyofCurrentDayIndex==6){
                        copyofCurrentDayIndex=0;
                    }
                }
               // HotelRates.put(hotel.hotelName, sum);
               if(sum<minSum){
                   minSum=sum;
                   hotelName=hotel.hotelName;
               }
            }
        }
        else{
            for(Hotel hotel:hotels){
                sum=0;
                int copyofCurrentDayIndex=currentDayIndex;
                for(int i=0;i<diff;i++,copyofCurrentDayIndex++){
                    if(days[copyofCurrentDayIndex].equals("sat") || days[copyofCurrentDayIndex].equals("sun")){
                        sum+=hotel.weekendCostForRewardssCustomer;
                    }
                    else{
                        sum+=hotel.weekDayCostForRewardsCustomer;
                    }
                    if(copyofCurrentDayIndex==6){
                        copyofCurrentDayIndex=0;
                    }
                }
               // HotelRates.put(hotel.hotelName, sum);
                if(sum<minSum){
                    minSum=sum;
                    hotelName=hotel.hotelName;
                }
            }
        }
        return hotelName+" "+minSum;
    }
    public static void main(String[] args) {
        System.out.println("Welcome.!");
        hotels.add(new Hotel("Lakewood ",3,110, 90, 80,80));
        hotels.add(new Hotel("Bridgewood ",4,160, 60, 110,50));
        hotels.add(new Hotel("Ridgewood ",5,220, 150, 100,40));
        System.out.println("date format : dd MMM yyyy EEE");
        try{
            System.out.println(findCheapHotel("Regular", "01 oct 2021 fri", "03 oct 2021 sun"));
            System.out.println(findCheapHotel("Rewards", "01 oct 2021 fri", "03 oct 2021 sun"));
        }
        catch(ParseException ex){
            System.out.println(ex.getMessage());
        }

    }
}
