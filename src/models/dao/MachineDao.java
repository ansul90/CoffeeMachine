package models.dao;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class MachineDao {
    OutletDao outlets;

    @JsonProperty("total_items_quantity")
    Map<String, Integer> totalItemsQuantity;
    Map<String, Map<String, Integer>> beverages;

    public Map<String, Integer> getTotalItemsQuantity() {
        return totalItemsQuantity;
    }

    public void setTotalItemsQuantity(Map<String, Integer> totalItemsQuantity) {
        this.totalItemsQuantity = totalItemsQuantity;
    }

    public Map<String, Map<String, Integer>> getBeverages() {
        return beverages;
    }

    public void setBeverages(Map<String, Map<String, Integer>> beverages) {
        this.beverages = beverages;
    }

    public OutletDao getOutlets() {
        return outlets;
    }

    public void setOutlets(OutletDao outlets) {
        this.outlets = outlets;
    }


}
