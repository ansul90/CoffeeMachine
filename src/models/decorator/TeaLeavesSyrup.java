package models.decorator;

public class TeaLeavesSyrup extends BeverageDecorator {
    private static volatile TeaLeavesSyrup obj = null;
    int totalQuantity;

    private TeaLeavesSyrup() {
    }

    public static TeaLeavesSyrup getInstance() {
        if (obj == null) {
            synchronized (TeaLeavesSyrup.class) {
                if (obj == null)
                    obj = new TeaLeavesSyrup();
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
