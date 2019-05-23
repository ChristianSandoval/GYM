
package com.openbravo.pos.ticket;

import java.awt.image.BufferedImage;
import com.openbravo.data.loader.DataRead;
import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.ImageUtils;
import com.openbravo.data.loader.SerializerRead;
import com.openbravo.format.Formats;
import java.util.Properties;

/**
 *
 * @author adrianromero
 *
 */
public class ProductInfoExt {

    private static final long serialVersionUID = 7587696873036L;

    protected String m_ID;
    protected String m_sRef;
    protected String m_sCode;
    protected String m_sName;
    protected boolean m_bCom;
    protected boolean m_bScale;
    protected String categoryid;
    protected String taxcategoryid;
    protected String attributesetid;
    protected double m_dPriceBuy;
    protected double m_dPriceSell;
    protected BufferedImage m_Image;
    protected Properties attributes;
    protected String stock;
    protected int days;
    /** Creates new ProductInfo */
    public ProductInfoExt() {
        m_ID = null;
        m_sRef = "0000";
        m_sCode = "0000";
        m_sName = null;
        m_bCom = false;
        m_bScale = false;
        categoryid = null;
        taxcategoryid = null;
        attributesetid = null;
        m_dPriceBuy = 0.0;
        m_dPriceSell = 0.0;
        m_Image = null;
        attributes = new Properties();
        stock=null;
        days = 0;
    }

    public final String getID() {
        return m_ID;
    }

    public final void setID(String id) {
        m_ID = id;
    }
    
    public final int getDays() {
        return days;
    }

    public final void setDays(int days) {
        this.days = days;
    }

    public final String getReference() {
        return m_sRef;
    }

    public final void setReference(String sRef) {
        m_sRef = sRef;
    }

    public final String getCode() {
        return m_sCode;
    }
    public final String getStock() {
        return stock;
    }

    public final void setCode(String sCode) {
        m_sCode = sCode;
    }

    public final String getName() {
        return m_sName;
    }

    public final void setName(String sName) {
        m_sName = sName;
    }

    public final boolean isCom() {
        return m_bCom;
    }

    public final void setCom(boolean bValue) {
        m_bCom = bValue;
    }

    public final boolean isScale() {
        return m_bScale;
    }

    public final void setScale(boolean bValue) {
        m_bScale = bValue;
    }

    public final String getCategoryID() {
        return categoryid;
    }

    public final void setCategoryID(String sCategoryID) {
        categoryid = sCategoryID;
    }

    public final String getTaxCategoryID() {
        return taxcategoryid;
    }

    public final void setTaxCategoryID(String value) {
        taxcategoryid = value;
    }

    public final String getAttributeSetID() {
        return attributesetid;
    }
    public final void setAttributeSetID(String value) {
        attributesetid = value;
    }

    public final double getPriceBuy() {
        return m_dPriceBuy;
    }

    public final void setPriceBuy(double dPrice) {
        m_dPriceBuy = dPrice;
    }

    public final double getPriceSell() {
        return m_dPriceSell;
    }

    public final void setPriceSell(double dPrice) {
        m_dPriceSell = dPrice;
    }

    public final double getPriceSellTax(TaxInfo tax) {
        return m_dPriceSell * (1.0 + tax.getRate());
    }

    public String printPriceSell() {
        return Formats.CURRENCY.formatValue(new Double(getPriceSell()));
    }

    public String printPriceSellTax(TaxInfo tax) {
        return Formats.CURRENCY.formatValue(new Double(getPriceSellTax(tax)));
    }
    
    public BufferedImage getImage() {
        return m_Image;
    }
    public void setImage(BufferedImage img) {
        m_Image = img;
    }
    
    public String getProperty(String key) {
        return attributes.getProperty(key);
    }
    public String getProperty(String key, String defaultvalue) {
        return attributes.getProperty(key, defaultvalue);
    }
    public void setProperty(String key, String value) {
        attributes.setProperty(key, value);
    }
    public Properties getProperties() {
        return attributes;
    }

    public static SerializerRead getSerializerRead() {
        return new SerializerRead() { public Object readValues(DataRead dr) throws BasicException {
            ProductInfoExt product = new ProductInfoExt();
            product.m_ID = dr.getString(1);
            product.m_sRef = dr.getString(2);
            product.m_sCode = dr.getString(3);
            product.m_sName = dr.getString(4);
            product.m_bCom = dr.getBoolean(5).booleanValue();
            product.m_bScale = dr.getBoolean(6).booleanValue();
            product.m_dPriceBuy = dr.getDouble(7).doubleValue();
            product.m_dPriceSell = dr.getDouble(8).doubleValue();
            product.taxcategoryid = dr.getString(9);
            product.categoryid = dr.getString(10);
            product.attributesetid = dr.getString(11);
            product.m_Image = ImageUtils.readImage(dr.getBytes(12));
            product.attributes = ImageUtils.readProperties(dr.getBytes(13));
            product.stock = dr.getString(14);
            product.days = dr.getInt(15);
            return product;
        }};
    }

    @Override
    public final String toString() {
        return m_sRef + " - " + m_sName;
    }
}
