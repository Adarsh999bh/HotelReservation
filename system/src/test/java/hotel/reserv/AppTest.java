package hotel.reserv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import java.util.stream.IntStream;

/**
 * Unit test for simple App.
 */
class AppTest {

    List<Hotel> hotels;
    @BeforeEach
    public void init(){
        hotels = new ArrayList<>();
        hotels.add(new Hotel("Ridgewood",5,220, 150, 100,40));
        hotels.add(new Hotel("Bridgewood",4,160, 60, 110,50));
        hotels.add(new Hotel("Lakewood",3,110, 90, 80,80));
    }
    /**
     * Rigorous Test.
     */
    @Test
    public void testLenHotels(){

        assertEquals(3, hotels.size());

    }
    public String findCheapHotel(String customerType,String checkinDate,String checkoutDate) throws ParseException{
        String[] days={"mon","tue","wed","thu","fri","sat","sun"};
        SimpleDateFormat sdf=new SimpleDateFormat("dd MMM yyyy EEE");
        Date firstDate,secondDate;
        firstDate=sdf.parse(checkinDate);
        secondDate=sdf.parse(checkoutDate);
        long diffInMillis=Math.abs(secondDate.getTime() - firstDate.getTime());
        long diff=TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
        String ss=checkinDate.substring(12,15);
        System.out.println(ss);
        int currentDayIndex=IntStream.range(0, 7).filter(i -> checkinDate.substring(12,15).equals(days[i])).findFirst().orElse(-1);
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
    @Test
    public void testCheapestHotelforRegularCustomer(){
        try{
            assertEquals("Lakewood 200", findCheapHotel("Regular", "01 oct 2021 fri", "03 oct 2021 sun"));
        }
        catch(ParseException ex){
            System.out.println(ex.getMessage()); 
        }
    }
    @Test
    public void testCheapestHotelforRewardsCustomer(){
        try{
            assertEquals("Ridgewood 140", findCheapHotel("Rewards", "01 oct 2021 fri", "03 oct 2021 sun"));
        }
        catch(ParseException ex){
            System.out.println(ex.getMessage()); 
        }
    }
}
