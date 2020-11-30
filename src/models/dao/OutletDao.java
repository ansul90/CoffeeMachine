package models.dao;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OutletDao {
    @JsonProperty("count_n")
    int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
