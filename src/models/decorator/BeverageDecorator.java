package models.decorator;


public abstract class BeverageDecorator {
    public abstract int getTotalQuantity();

    public abstract void setTotalQuantity(int val);

    public abstract void updateQuantity(int val);
}
