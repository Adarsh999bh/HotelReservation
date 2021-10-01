package hotel.reserv;

public class Hotel {
    public String hotelName;
    public int weekDayCostForRegularCustomer,weekendCostForRegularCustomer;
    public int weekDayCostForRewardsCustomer,weekendCostForRewardssCustomer;
    public int rating;
    public Hotel(String hotelName,int rating,int weekDayCostForRegularCustomer,int weekendCostForRegularCustomer,int weekDayCostForRewardsCustomer,int weekendCostForRewardssCustomer){
        this.hotelName=hotelName;
        this.rating=rating;
        this.weekDayCostForRegularCustomer=weekDayCostForRegularCustomer;
        this.weekDayCostForRewardsCustomer=weekDayCostForRewardsCustomer;
        this.weekendCostForRegularCustomer=weekendCostForRegularCustomer;
        this.weekendCostForRewardssCustomer=weekendCostForRewardssCustomer;
    }
    
    
}
