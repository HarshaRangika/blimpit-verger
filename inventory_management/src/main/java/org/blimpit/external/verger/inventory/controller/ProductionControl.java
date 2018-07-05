package org.blimpit.external.verger.inventory.controller;


import javafx.scene.paint.Material;
import org.blimpit.external.verger.inventory.config.ApplicationProperties;
import org.blimpit.external.verger.inventory.model.Products;
import org.blimpit.external.verger.inventory.model.RowMaterials;
import org.blimpit.external.verger.inventory.model.Stock;
import org.blimpit.external.verger.inventory.utils.Constant;
import org.blimpit.utils.connectors.Connector;
import org.blimpit.utils.connectors.ConnectorException;
import org.blimpit.utils.connectors.Constants;
import org.blimpit.utils.connectors.mysql.MySQLConnector;
import org.blimpit.utils.connectors.mysql.Record;

import javax.ws.rs.core.MediaType;
import java.util.*;

public class ProductionControl {

    private ApplicationProperties applicationProperties = ApplicationProperties.getInstance(Constant.PATH_CONFFILE);

    // private String primaryID = applicationProperties.getValue(Constant.PRIMARY_ID);

    private String tableStock = applicationProperties.getValue(Constant.DB_TABLE_STOCK);
    private String tableRawMaterials = applicationProperties.getValue(Constant.DB_TABLE_RAWMATERIAL);
    private String tableProducts = applicationProperties.getValue(Constant.DB_TABLE_PRODUCTS);

    private String batchNo = applicationProperties.getValue(Constant.STOCK_BATCHNO);
    private String quantity = applicationProperties.getValue(Constant.STOCK_QUANTITY);
    private String rmTracking = applicationProperties.getValue(Constant.STOCK_RMTRACKING);
    private String proDate = applicationProperties.getValue(Constant.STOCK_DATE);
    private String becoming = applicationProperties.getValue(Constant.STOCK_BECOMING);
    private String document = applicationProperties.getValue(Constant.STOCK_DOCUMENT);

    private String rmId = applicationProperties.getValue(Constant.RM_ID);
    private String rmQuantity = applicationProperties.getValue(Constant.RM_QUANTITY);
    private String orgin = applicationProperties.getValue(Constant.ORGIN);
    private String grage = applicationProperties.getValue(Constant.GRADE);
    private String packing = applicationProperties.getValue(Constant.PACKING);
    private String storagePortion = applicationProperties.getValue(Constant.STORAGE_PORTION);
    private String buyingVoucherPic = applicationProperties.getValue(Constant.BUYING_VOUCHER_PICTURE);

    private String proID = applicationProperties.getValue(Constant.PRODUCT_ID);
    private String productName = applicationProperties.getValue(Constant.PRODUCT_NAME);


    private Connector connector;

    public ProductionControl() {
        try {

            connector = MySQLConnector.getInstance(applicationProperties.getValue(Constants.DB_HOST),
                    applicationProperties.getValue(Constants.DB_PORT),
                    applicationProperties.getValue(Constants.DB_NAME),
                    applicationProperties.getValue(Constants.DB_USERNAME),
                    applicationProperties.getValue(Constants.DB_PASSWORD));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<Stock> getStock() {
        List<Stock> stockData = new ArrayList<Stock>();
        try {
            Record[] data = connector.read(applicationProperties.getValue(Constant.DB_TABLE_STOCK));

            for (int i = 0; i < data.length; i++) {
                Stock stk = new Stock();
                //  stk.setId(Integer.parseInt(data[i].getRecordAttributes().get(primaryID)));
                stk.setBatchNo(data[i].getRecordAttributes().get(batchNo));
                stk.setQuantuty(Integer.parseInt(data[i].getRecordAttributes().get(quantity)));
                stk.setRmTracking(data[i].getRecordAttributes().get(rmTracking));
                stk.setProductionDate(data[i].getRecordAttributes().get(proDate));
                stk.setBecoming(data[i].getRecordAttributes().get(becoming));
                stk.setProductID(data[i].getRecordAttributes().get(proID));
                stk.setDocument(data[i].getRecordAttributes().get(document));

                stockData.add(stk);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return stockData;
    }

    public Stock saveStocks(Stock stk) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put(batchNo, stk.getBatchNo());
            map.put(quantity, String.valueOf(stk.getQuantuty()));
            map.put(rmTracking, stk.getRmTracking());
            map.put(proDate, stk.getProductionDate());
            map.put(becoming, stk.getBecoming());
            map.put(proID, stk.getProductID());
            map.put(document, stk.getDocument());

            boolean add = connector.insert(tableStock, map);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return stk;
    }

    public RowMaterials saveRawMaterials(RowMaterials materials) {
        System.out.println("RM ID Ekaaaaa...... : " + materials.getRmId());

        try {
            Map<String, String> map = new HashMap<String, String>();
            // map.put(primaryID, "1");
            map.put(rmId, materials.getRmId());
            map.put(rmQuantity, String.valueOf(materials.getQuantity()));
            map.put(orgin, materials.getOrigin());
            map.put(grage, materials.getGrade());
            map.put(packing, materials.getPacking());
            map.put(storagePortion, String.valueOf(materials.getStoragePortion()));
            map.put(buyingVoucherPic, materials.getBuyingVoucherPicture());
            map.put(proID, materials.getProductID());
            boolean ddd = connector.insert(tableRawMaterials, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return materials;
    }

    public List<RowMaterials> getRowMaterials() {

        List<RowMaterials> materialsList = new ArrayList<RowMaterials>();
        try {

            Record[] data = connector.read(applicationProperties.getValue(Constant.DB_TABLE_RAWMATERIAL));

            for (int i = 0; i < data.length; i++) {

                RowMaterials rmrls = new RowMaterials();

                rmrls.setRmId(data[i].getRecordAttributes().get(rmId));
                rmrls.setQuantity(Integer.parseInt(data[i].getRecordAttributes().get(rmQuantity)));
                rmrls.setOrigin(data[i].getRecordAttributes().get(orgin));
                rmrls.setGrade(data[i].getRecordAttributes().get(grage));
                rmrls.setPacking(data[i].getRecordAttributes().get(packing));
                rmrls.setStoragePortion(Integer.parseInt(data[i].getRecordAttributes().get(storagePortion)));
                rmrls.setBuyingVoucherPicture(data[i].getRecordAttributes().get(buyingVoucherPic));
                rmrls.setProductID(data[i].getRecordAttributes().get(proID));

                materialsList.add(rmrls);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return materialsList;
    }

    public Products addProducts(Products product) {
        try {

            Map<String, String> map = new HashMap<String, String>();
            map.put(proID, product.getProductID());
            map.put(productName, product.getProductName());

            boolean add = connector.insert(tableProducts, map);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

    public List<Products> getProduct() {

        List<Products> proList = new ArrayList<Products>();

        try {

            Record[] data = connector.read(applicationProperties.getValue(Constant.DB_TABLE_PRODUCTS));

            for (int i = 0; i < data.length; i++) {
                Products products = new Products();

                products.setProductID(data[i].getRecordAttributes().get(proID));
                products.setProductName(data[i].getRecordAttributes().get(productName));

                proList.add(products);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return proList;
    }

    public javax.ws.rs.core.Response deleteProduct(String pro_id) {
        try {

            boolean delete = false;
            delete = connector.delete(tableProducts, "product_id", pro_id);

            if (delete) {
                return javax.ws.rs.core.Response.ok("{\"msg\":\"Entry deleted\"}", MediaType.APPLICATION_JSON).build();
            } else {
                return javax.ws.rs.core.Response.status(javax.ws.rs.core.Response.Status.BAD_REQUEST).entity("Not Deleted").build();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public javax.ws.rs.core.Response deleteStock(String stk_id) {
        try {

            boolean delete = false;
            delete = connector.delete(tableStock, "batch_no", stk_id);

            if (delete) {
                return javax.ws.rs.core.Response.ok("{\"msg\":\"Entry deleted\"}", MediaType.APPLICATION_JSON).build();
            } else {
                return javax.ws.rs.core.Response.status(javax.ws.rs.core.Response.Status.BAD_REQUEST).entity("Not Deleted").build();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public javax.ws.rs.core.Response deleteMaterials(String mrl_id) {
        try {

            boolean delete = false;
            delete = connector.delete(tableRawMaterials, "rm_id", mrl_id);

            if (delete) {
                return javax.ws.rs.core.Response.ok("{\"msg\":\"Entry deleted\"}", MediaType.APPLICATION_JSON).build();
            } else {
                return javax.ws.rs.core.Response.status(javax.ws.rs.core.Response.Status.BAD_REQUEST).entity("Not Deleted").build();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public org.blimpit.utils.usermanagement.model.ResponseStatus updateProduct(Products products) {

        org.blimpit.utils.usermanagement.model.ResponseStatus responseStatus = new org.blimpit.utils.usermanagement.model.ResponseStatus();
        Map<String, String> map = new HashMap();

        System.out.print("upadateProduct backEnd....");

        map.put(proID, products.getProductID());
        map.put(productName, products.getProductName());

        // System.out.println("Map = "+map);
        try {
            System.out.print(map.values());
            boolean add = connector.update(tableProducts, proID, products.getProductID(), map);
            if (add) {
                responseStatus.setSuccess(true);
                responseStatus.setMessage("Product Successfully Updated");
            } else {
                responseStatus.setSuccess(false);
                responseStatus.setMessage("Cannot Update");
            }
        } catch (ConnectorException var6) {
            //responseStatus.setSuccess(true);
            responseStatus.setMessage("Cannot Update Products , check mysql server is up and running, " + var6.getMessage());
        }
        return responseStatus;

    }

    public org.blimpit.utils.usermanagement.model.ResponseStatus updateStock(Stock stock) {

        org.blimpit.utils.usermanagement.model.ResponseStatus responseStatus = new org.blimpit.utils.usermanagement.model.ResponseStatus();
        Map<String, String> map = new HashMap();

        System.out.print("upadateStock backEnd.... " + stock.getProductID());



        map.put(batchNo, stock.getBatchNo());
        map.put(quantity, String.valueOf(stock.getQuantuty()));
        map.put(rmTracking, stock.getRmTracking());
        map.put(proDate, stock.getProductionDate());
        map.put(becoming, stock.getBecoming());
        map.put(proID, stock.getProductID());
        map.put(document, stock.getDocument());

        // System.out.println("Map = "+map);
        try {
            System.out.print(map.values());
            boolean add = connector.update(tableStock, batchNo, stock.getBatchNo(), map);
            if (add) {
                responseStatus.setSuccess(true);
                responseStatus.setMessage("Stock Successfully Updated");
            } else {
                responseStatus.setSuccess(false);
                responseStatus.setMessage("Cannot Update");
            }
        } catch (ConnectorException var6) {
            //responseStatus.setSuccess(true);
            responseStatus.setMessage("Cannot Update Stock , check mysql server is up and running, " + var6.getMessage());
        }
        return responseStatus;

    }

    public org.blimpit.utils.usermanagement.model.ResponseStatus updateMaterial(RowMaterials rowMat) {

        org.blimpit.utils.usermanagement.model.ResponseStatus responseStatus = new org.blimpit.utils.usermanagement.model.ResponseStatus();
        Map<String, String> map = new HashMap();

        System.out.print("updateMaterial backEnd.... " + rowMat.getProductID());

        System.out.println("Product ID = "+rowMat.getProductID());

        map.put(rmId, rowMat.getRmId());
        map.put(rmQuantity, String.valueOf(rowMat.getQuantity()));
        map.put(orgin, rowMat.getOrigin());
        map.put(grage, rowMat.getGrade());
        map.put(packing, rowMat.getPacking());
        map.put(storagePortion, String.valueOf(rowMat.getStoragePortion()));
        map.put(buyingVoucherPic, rowMat.getBuyingVoucherPicture());
        map.put(proID, rowMat.getProductID());


        try {
            System.out.print(""+map.values());
            boolean add = connector.update(tableRawMaterials, rmId, rowMat.getRmId(), map);
            if (add) {
                responseStatus.setSuccess(true);
                responseStatus.setMessage("Material Successfully Updated");
                System.out.println("#Material Successfully Updated#");
            } else {
                responseStatus.setSuccess(false);
                responseStatus.setMessage("Cannot Update");
            }
        } catch (ConnectorException var6) {
            //responseStatus.setSuccess(true);
            responseStatus.setMessage("Cannot Update Stock , check mysql server is up and running, " + var6.getMessage());
            var6.printStackTrace();
        }
        return responseStatus;

    }

}
