package models.decorator;

public class HotWater extends BeverageDecorator {
    private static volatile HotWater obj = null;
    int totalQuantity;

    private HotWater() {
    }

    public static HotWater getInstance() {
        if (obj == null) {
            synchronized (HotWater.class) {
                if (obj == null)
                    obj = new HotWater();
            }
        }
        return obj;
    }

    @Override
    public int getTotalQuantity() {
        return totalQuantity;
    }

    @Override
    public void setTotalQuantity(int val) {
        this.totalQuantity = val;
    }

    @Override
    public void updateQuantity(int val) {
        this.totalQuantity = this.totalQuantity - val;
    }
}
