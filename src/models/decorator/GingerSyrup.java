package models.decorator;

public class GingerSyrup extends BeverageDecorator {
    private static volatile GingerSyrup obj = null;
    int totalQuantity;

    private GingerSyrup() {
    }

    public static GingerSyrup getInstance() {
        if (obj == null) {
            synchronized (GingerSyrup.class) {
                if (obj == null)
                    obj = new GingerSyrup();
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
