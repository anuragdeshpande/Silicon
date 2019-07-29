package framework.integrations.ftp.layoutReader;

import framework.integrations.ftp.layoutReader.layout.LayoutFile;
import framework.integrations.ftp.layoutReader.models.tokenizer.FixedWidthTokenizer;
import framework.logger.RegressionLogger;

import javax.xml.bind.JAXBException;
import java.io.File;

public class FixedWidthLineLayoutReader extends LayoutReader {
    private LayoutFile.FixedWidthLayout.Line lineLayout;

    public FixedWidthLineLayoutReader(File fileToRead, RegressionLogger logger) throws JAXBException {
        super(fileToRead, logger);
        this.lineLayout = layoutFile.getFixedWidthLayout().getLine();
    }

    @Override
    public boolean verifyLine(String line) {
        FixedWidthTokenizer tokenizer = new FixedWidthTokenizer(lineLayout, logger);
        return tokenizer.validateTokens(line);
    }
}
