package framework.integrations.ftp.layoutReader;

import framework.integrations.ftp.layoutReader.enums.LineTypes;
import framework.integrations.ftp.layoutReader.layout.LayoutFile;
import framework.integrations.ftp.layoutReader.models.MasterDetail;
import framework.logger.RegressionLogger;
import org.junit.Assert;

import javax.xml.bind.JAXBException;
import java.io.File;

public class MasterDetailLayoutReader extends LayoutReader {

    private LayoutFile.FixedWidthLayout.MasterDetail.Master masterLayout;
    private LayoutFile.FixedWidthLayout.MasterDetail.Detail detailLayout;
    private MasterDetail masterDetail;

    public MasterDetailLayoutReader(File fileToRead, RegressionLogger logger) throws JAXBException {
        super(fileToRead, logger);
        LayoutFile.FixedWidthLayout.MasterDetail masterDetail = layoutFile.getFixedWidthLayout().getMasterDetail();
        this.masterLayout = masterDetail.getMaster();
        this.detailLayout = masterDetail.getDetail();
        this.masterDetail = new MasterDetail(logger, masterLayout, detailLayout);
    }

    @Override
    public boolean verifyLine(String line) {
        LineTypes lineType = isMasterOrDetail(line);

        // found a new Detail line - add to the existing object
        if (lineType == LineTypes.DETAIL) {

            // found detail line without a master line
            if(!masterDetail.hasMasterLine()){
                logger.fatal("Cannot process detail line without a master line. Invalid File Format.");
                throw new InvalidLineFormatException("Detail Line found without a master line.");
            }

            masterDetail.addDetailLine(line);
            return masterDetail.isDetailValid();
        }


        // found master line
        if (lineType == LineTypes.MASTER) {

            if(!masterDetail.hasMasterLine() || (masterDetail.hasMasterLine() && masterDetail.hasDetailLines())){
                // new master detail object started in the file - create new master detail object.
                masterDetail = new MasterDetail(logger, masterLayout, detailLayout);
                masterDetail.addMasterLine(line);
                return masterDetail.isMasterValid();
            }

            // no detail lines got another master line
            if (!masterDetail.hasDetailLines()) {
                logger.fatal("Multiple Master Lines without detail lines. Invalid File Format.");
                throw new InvalidLineFormatException("Multiple Master Lines without Duplicate Lines");
            }
        }


        // catch all statement - always return false
        return false;
    }


    private LineTypes isMasterOrDetail(String line) {
        if (line.startsWith(masterLayout.getMustStartWith()))
            return LineTypes.MASTER;

        if (line.startsWith(detailLayout.getMustStartWith()))
            return LineTypes.DETAIL;

        String errorMessage = "Unknown Start Character on line: " + line.substring(0, 10) + "...";
        Assert.fail(errorMessage);
        logger.fatal(errorMessage);

        throw new InvalidLineFormatException(errorMessage);
    }
}
