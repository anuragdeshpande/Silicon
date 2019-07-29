
package framework.integrations.ftp.layoutReader.layout;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the framework.integrations.ftp.layoutReader.layout package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: framework.integrations.ftp.layoutReader.layout
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link LayoutFile }
     * 
     */
    public LayoutFile createLayoutFile() {
        return new LayoutFile();
    }

    /**
     * Create an instance of {@link LayoutFile.DelimitedLayout }
     * 
     */
    public LayoutFile.DelimitedLayout createLayoutFileDelimitedLayout() {
        return new LayoutFile.DelimitedLayout();
    }

    /**
     * Create an instance of {@link LayoutFile.FixedWidthLayout }
     * 
     */
    public LayoutFile.FixedWidthLayout createLayoutFileFixedWidthLayout() {
        return new LayoutFile.FixedWidthLayout();
    }

    /**
     * Create an instance of {@link LayoutFile.FixedWidthLayout.MasterDetail }
     * 
     */
    public LayoutFile.FixedWidthLayout.MasterDetail createLayoutFileFixedWidthLayoutMasterDetail() {
        return new LayoutFile.FixedWidthLayout.MasterDetail();
    }

    /**
     * Create an instance of {@link FixedWidthField }
     * 
     */
    public FixedWidthField createFixedWidthField() {
        return new FixedWidthField();
    }

    /**
     * Create an instance of {@link DelimitedField }
     * 
     */
    public DelimitedField createDelimitedField() {
        return new DelimitedField();
    }

    /**
     * Create an instance of {@link LayoutFile.DelimitedLayout.DelimitedLine }
     * 
     */
    public LayoutFile.DelimitedLayout.DelimitedLine createLayoutFileDelimitedLayoutDelimitedLine() {
        return new LayoutFile.DelimitedLayout.DelimitedLine();
    }

    /**
     * Create an instance of {@link LayoutFile.FixedWidthLayout.Line }
     * 
     */
    public LayoutFile.FixedWidthLayout.Line createLayoutFileFixedWidthLayoutLine() {
        return new LayoutFile.FixedWidthLayout.Line();
    }

    /**
     * Create an instance of {@link LayoutFile.FixedWidthLayout.MasterDetail.Master }
     * 
     */
    public LayoutFile.FixedWidthLayout.MasterDetail.Master createLayoutFileFixedWidthLayoutMasterDetailMaster() {
        return new LayoutFile.FixedWidthLayout.MasterDetail.Master();
    }

    /**
     * Create an instance of {@link LayoutFile.FixedWidthLayout.MasterDetail.Detail }
     * 
     */
    public LayoutFile.FixedWidthLayout.MasterDetail.Detail createLayoutFileFixedWidthLayoutMasterDetailDetail() {
        return new LayoutFile.FixedWidthLayout.MasterDetail.Detail();
    }

}
