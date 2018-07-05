package org.blimpit.external.verger.inventory.utils;

public interface Constant {

    String PATH_CONFFILE = "config.properties";

    /**
<<<<<<< HEAD
     * DB TABLES
=======
     * File Store Path
     */
    String LOCAL_FILE_PATH = "save.file.path.local";
    String SERVER_FILE_PATH = "server.file.path";

    String FILE_PATH_FINANCES_ARCHIVE = "archive.filePath.finances";
    String FILE_PATH_SHIPMENTS_ARCHIVE = "archive.filePath.shipments";
    String FILE_PATH_ORDERS_ARCHIVE = "archive.filePath.orders";

    /**
     *DB TABLES
>>>>>>> f7346a3dd065469761ed3179571b700ff757f8d4
     */

    String DB_TABLE_USER = "db.verger.users";
    String DB_TABLE_FEATURES = "db.verger.features";
    String DB_TABLE_FEATURESMAPPER = "db.verger.featuresmapper";
    String DB_TABLE_DOCUMENT = "db.document.table";


    String DB_TABLE_STOCK = "db.database.table.stocks";
    String DB_TABLE_RAWMATERIAL = "db.database.table.rawmaterial";
    String DB_TABLE_PRODUCTS = "db.database.table.products";


    /**
     * DB TABLES ATTRIBUTES
     */

    String STOCK_BATCHNO = "db.column.batchNo";
    String STOCK_QUANTITY = "db.column.quantity";
    String STOCK_RMTRACKING = "db.column.rmtracking";
    String STOCK_DATE = "db.column.date";
    String STOCK_BECOMING = "db.column.becoming";
    String STOCK_DOCUMENT = "db.column.document";

    String RM_ID = "db.column.rmid";
    String RM_QUANTITY = "db.column.rmquantity";
    String ORGIN = "db.column.orgin";
    String GRADE = "db.column.grade";
    String PACKING = "db.column.packing";
    String STORAGE_PORTION = "db.column.storageportion";
    String BUYING_VOUCHER_PICTURE = "db.column.buyingvoucherpicture";

    String PRODUCT_ID = "db.column.proid";
    String PRODUCT_NAME = "db.column.proname";


    String USER_USERNAME = "db.column.username";
    String USER_NAME = "db.column.name";
    String USER_PASSWORD = "db.column.password";
    String USER_DESIGNATION = "db.column.designation";
    String USER_STATUS = "db.column.status";
    String USER_DEPARTMENT = "db.column.department";
    String FEATURES_ID = "db.column.fid";
    String FEATURES_USER_ID = "db.column.uid";

    String TABLE_DOCUMENTID = "db.column.documentId";
    String TABLE_DOCUMENTNAME = "db.column.documentName";
    String TABLE_DOCUMENTPATH = "db.column.documentPath";
    String TABLE_SECTION = "db.column.documentSection";
    String TABLE_DATE_DOC = "db.column.createDate";

}
