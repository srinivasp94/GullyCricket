package sample.ramya.com.exampleapp.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by Home on 30-01-2017.
 */

public class ProductResult {
    @SerializedName("product_id")
    @Expose
    public String productId;
    @SerializedName("product_name")
    @Expose
    public String productName;
    @SerializedName("product_units")
    @Expose
    public String productUnits;
    @SerializedName("mrp")
    @Expose
    public String mrp;
    @SerializedName("selling_price")
    @Expose
    public String sellingPrice;
    @SerializedName("available_stock")
    @Expose
    public String availableStock;
    @SerializedName("product_image")
    @Expose
    public String productImage;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}

