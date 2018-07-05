package org.blimpit.external.verger.inventory.service;


import javafx.scene.paint.Material;
import org.blimpit.external.verger.inventory.controller.ProductionControl;
import org.blimpit.external.verger.inventory.model.Products;
import org.blimpit.external.verger.inventory.model.RowMaterials;
import org.blimpit.external.verger.inventory.model.Stock;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("production")
public class ProductionService {

    ProductionControl productionControl = new ProductionControl();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getMessage() {
        return "Production Services";
    }

    @GET
    @Path("/stockdata")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Stock> getStock() {

        return productionControl.getStock();

    }

    @POST
    @Path("stksave")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Stock saveStocks(Stock stk) {

        return productionControl.saveStocks(stk);
    }

    @POST
    @Path("/rmsave")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public RowMaterials saveRawMaterials(RowMaterials materials) {

        System.out.println(materials);
        return productionControl.saveRawMaterials(materials);

    }

    @GET
    @Path("/rmdata")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<RowMaterials> getRowMaterials() {

        return productionControl.getRowMaterials();
    }

    @POST
    @Path("/addPro")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Products addProducts(Products product) {

        return productionControl.addProducts(product);
    }

    @GET
    @Path("/getPro")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Products> getProduct() {

        return productionControl.getProduct();
    }

    @DELETE
    @Path("/deletePro/{product_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteProduct(@PathParam("product_id") String pro_id) {
        System.out.print("Delete ID ekaa...  = " + pro_id);
        return productionControl.deleteProduct(pro_id);
    }

    @DELETE
    @Path("/deleteStk/{batch_no}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteStock(@PathParam("batch_no") String stk_id) {
        System.out.print("Delete STK ID ekaa...  = " + stk_id);
        return productionControl.deleteStock(stk_id);
    }

    @DELETE
    @Path("/deleteMat/{rm_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteMaterials(@PathParam("rm_id") String mrl_id) {
        System.out.print("Delete STK ID ekaa...  = " + mrl_id);
        return productionControl.deleteMaterials(mrl_id);
    }

    @POST
    @Path("/updatePro")
    public org.blimpit.utils.usermanagement.model.ResponseStatus updateProduct(Products products){

        System.out.print("upadateProduct....");
        return productionControl.updateProduct(products);


    }

    @POST
    @Path("/updateStk")
    public org.blimpit.utils.usermanagement.model.ResponseStatus updateStock(Stock stock){

        System.out.print("upadateProduct....");
        return productionControl.updateStock(stock);


    }

    @POST
    @Path("/updateMat")
    public org.blimpit.utils.usermanagement.model.ResponseStatus updateMaterial(RowMaterials rowMat){

        System.out.print("updateMaterial....");
        System.out.println(rowMat.getRmId());
        System.out.println(rowMat.getProductID());
        System.out.println(rowMat.getBuyingVoucherPicture());
        System.out.println(rowMat.getGrade());
        System.out.println(rowMat.getOrigin());
        System.out.println(rowMat.getPacking());
        System.out.println(rowMat.getQuantity());
        System.out.println(rowMat.getStoragePortion());

        return productionControl.updateMaterial(rowMat);


    }


}