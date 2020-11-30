package models.decorator;

public class SugarSyrup extends BeverageDecorator {
    private static volatile SugarSyrup obj = null;
    int totalQuantity;

    private SugarSyrup() {
    }

    public static SugarSyrup getInstance() {
        if (obj == null) {
            synchronized (SugarSyrup.class) {
                if (obj == null)
                    obj = new SugarSyrup();
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
