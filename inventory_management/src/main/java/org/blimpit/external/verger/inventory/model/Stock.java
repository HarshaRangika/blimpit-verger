package org.blimpit.external.verger.inventory.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
public class Stock {
    private int id;
    private String batchNo;
    private int quantuty;
    private String rmTracking;
    private String productionDate;
    private String becoming;
    private String productID;
    private String document;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public int getQuantuty() {
        return quantuty;
    }

    public void setQuantuty(int quantuty) {
        this.quantuty = quantuty;
    }

    public String getRmTracking() {
        return rmTracking;
    }

    public void setRmTracking(String rmTracking) {
        this.rmTracking = rmTracking;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    public String getBecoming() {
        return becoming;
    }

    public void setBecoming(String becoming) {
        this.becoming = becoming;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }
}
