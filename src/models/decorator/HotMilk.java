package models.decorator;

public class HotMilk extends BeverageDecorator {
    private static volatile HotMilk obj = null;
    int totalQuantity;

    private HotMilk() {
    }

    public static HotMilk getInstance() {
        if (obj == null) {
            synchronized (HotMilk.class) {
                if (obj == null)
                    obj = new HotMilk();
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
