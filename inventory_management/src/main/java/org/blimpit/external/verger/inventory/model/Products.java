package org.blimpit.external.verger.inventory.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Products {

    private String productID;
    private String productName;

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
