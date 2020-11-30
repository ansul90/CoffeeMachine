package models.decorator;

public class GreenMixture extends BeverageDecorator {

    private static volatile GreenMixture obj = null;
    int totalQuantity;

    private GreenMixture() {
    }

    public static GreenMixture getInstance() {
        if (obj == null) {
            synchronized (GreenMixture.class) {
                if (obj == null)
                    obj = new GreenMixture();
            }
        }
        return obj;
    }

    public int getTotalQuantity() {
        return this.totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    @Override
    public void updateQuantity(int val) {
        this.totalQuantity = this.totalQuantity - val;
    }

}
