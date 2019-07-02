package framework.integrations.ftp.layoutReader;

import framework.integrations.ftp.layoutReader.layout.LayoutFile;
import framework.logger.RegressionLogger;

import javax.xml.bind.JAXBException;
import java.io.File;

public class DelimitedLayoutReader extends LayoutReader {

    private LayoutFile.DelimitedLayout.DelimitedLine delimitedLinesLayout;

    public DelimitedLayoutReader(File fileToRead, RegressionLogger logger) throws JAXBException {
        super(fileToRead, logger);
        this.delimitedLinesLayout = layoutFile.getDelimitedLayout().getDelimitedLine();
    }

    @Override
    public boolean verifyLine(String line) {
        return false;
    }
}
