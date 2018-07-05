package org.blimpit.external.verger.inventory.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RowMaterials {
    private int id;
    private String rmId;
    private int quantity;
    private String origin;
    private String grade;
    private String packing;
    private int storagePortion;
    private String buyingVoucherPicture;
    private String productID;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRmId() {
        return rmId;
    }

    public void setRmId(String rmId) {
        this.rmId = rmId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getPacking() {
        return packing;
    }

    public void setPacking(String packing) {
        this.packing = packing;
    }

    public int getStoragePortion() {
        return storagePortion;
    }

    public void setStoragePortion(int storagePortion) {
        this.storagePortion = storagePortion;
    }

    public String getBuyingVoucherPicture() {
        return buyingVoucherPicture;
    }

    public void setBuyingVoucherPicture(String buyingVoucherPicture) {
        this.buyingVoucherPicture = buyingVoucherPicture;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }
}
