package framework.integrations.ftp.layoutReader;

import framework.integrations.ftp.layoutReader.layout.LayoutFile;
import framework.logger.RegressionLogger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;

public abstract class LayoutReader {

    protected LayoutFile layoutFile;
    protected RegressionLogger logger;

    public LayoutReader(File layoutFile, RegressionLogger logger) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(framework.integrations.ftp.layoutReader.layout.LayoutFile.class);
        this.layoutFile = (LayoutFile) context.createUnmarshaller().unmarshal(layoutFile);
        this.logger = logger;
    }

    protected abstract boolean verifyLine(String line);
}
