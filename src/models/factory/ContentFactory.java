package models.factory;

import models.decorator.*;

public class ContentFactory {
    public BeverageDecorator getContent(String description) {
        if (description.equals("green_mixture"))
            return GreenMixture.getInstance();
        else if (description.equals("hot_milk"))
            return HotMilk.getInstance();
        else if (description.equals("hot_water"))
            return HotWater.getInstance();
        else if (description.equals("sugar_syrup"))
            return SugarSyrup.getInstance();
        else if (description.equals("tea_leaves_syrup"))
            return TeaLeavesSyrup.getInstance();
        else if (description.equals("ginger_syrup"))
            return GingerSyrup.getInstance();
        return null;
    }
}
