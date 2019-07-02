package framework.integrations.ftp;

import framework.integrations.ftp.layoutReader.DelimitedLayoutReader;
import framework.integrations.ftp.layoutReader.FixedWidthLineLayoutReader;
import framework.integrations.ftp.layoutReader.MasterDetailLayoutReader;
import framework.logger.RegressionLogger;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class RemoteFTPFile extends FTPFile {

    private String _fileName;
    private FTPClient _ftpConnection;
    private RegressionLogger logger;

    public RemoteFTPFile(String fileName, FTPClient connection, RegressionLogger logger){
        this._ftpConnection = connection;
        this._fileName = fileName;
        this.logger = logger;
    }

    /**
     * Reads the XML layout file that is passed. Compares the layout in the file and returns a boolean value if the layout
     * is a match or not.
     * @param layoutFilePath file Layout Path
     * @return boolean value if the file is in the pattern of the XML file passed.
     */
    public boolean VerifyFileMatchsDelimitedLayout(String layoutFilePath) throws IOException, JAXBException {
        DelimitedLayoutReader reader = new DelimitedLayoutReader(new File(layoutFilePath), logger);
        Scanner inputFile = new Scanner(this._ftpConnection.retrieveFileStream(_fileName));

        // for each line in the input file
        while(inputFile.hasNextLine()){
            if (!reader.verifyLine(inputFile.nextLine())) {
                return false;
            }
        }


        return true;
    }

    /**
     * Reads the XML layout file that is passed. Compares the layout in the file and returns a boolean value if the layout
     * is a match or not.
     * @param layoutFilePath file Layout Path
     * @return boolean value if the file is in the pattern of the XML file passed.
     */
    public boolean VerifyFileMatchsFixedWidthLineLayout(String layoutFilePath) throws IOException, JAXBException {
        FixedWidthLineLayoutReader reader = new FixedWidthLineLayoutReader(new File(layoutFilePath), logger);
        Scanner inputFile = new Scanner(this._ftpConnection.retrieveFileStream(_fileName));

        // for each line in the input file
        while(inputFile.hasNextLine()){
            if (!reader.verifyLine(inputFile.nextLine())) {
                return false;
            }
        }


        return true;
    }

    /**
     * Reads the XML layout file that is passed. Compares the layout in the file and returns a boolean value if the layout
     * is a match or not.
     * @param layoutFilePath file Layout Path
     * @return boolean value if the file is in the pattern of the XML file passed.
     */
    public boolean VerifyFileMatchsFixedWidthMasterDetailLayout(String layoutFilePath) throws IOException, JAXBException {
        MasterDetailLayoutReader reader = new MasterDetailLayoutReader(new File(layoutFilePath), logger);
        Scanner inputFile = new Scanner(this._ftpConnection.retrieveFileStream(_fileName));

        // for each line in the input file
        while(inputFile.hasNextLine()){
            if (!reader.verifyLine(inputFile.nextLine())) {
                return false;
            }
        }


        return true;
    }
}
