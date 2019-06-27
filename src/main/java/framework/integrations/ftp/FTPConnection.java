package framework.integrations.ftp;

import framework.applications.Application;
import framework.logger.RegressionLogger;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.testng.Assert;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

public class FTPConnection {

    // application variables
    private Application application;

    // connection parameters
    private String serverName;
    private String username;
    private String password;

    // utility variables
    private FTPClient _ftpClient;
    private RegressionLogger logger;

    public FTPConnection(Application application, String serverName, String username, String password) {
        this.serverName = serverName;
        this.username = username;
        this.password = password;
        this.application = application;
        this.logger = application.getLogger();
    }


    // operation methods
    public FTPConnection openConnection() {
        this._ftpClient = new FTPClient();
        try {
            this._ftpClient.connect(this.serverName);
            this._ftpClient.login(username, new String(Base64.decodeBase64(password)));
            logger.info("Successfully established FTP Connection to: " + serverName + " as user: " + username);
        } catch (IOException e) {
            this.logger.error(e.getLocalizedMessage());
            Assert.fail("Could not Connect to FTP Client: Bad Host, Username or password", e);
        }

        return this;
    }

    public FTPConnection chagneWorkingDirectory(String relativeDestinationPath) {
        try {
            String currentLocation = _ftpClient.printWorkingDirectory();
            this._ftpClient.changeWorkingDirectory(relativeDestinationPath);
            logger.info("Successfully changed current working directory to: " + relativeDestinationPath + " from: " + currentLocation);
        } catch (IOException e) {
            Assert.fail("Could not change the directory path to" + relativeDestinationPath + ". Current user does not have permission or the path does not exist", e);
        }

        return this;
    }

    public boolean hasFileWithName(String fileNameToSearch) {
        try {
            return Arrays.stream(this._ftpClient.listFiles()).anyMatch(ftpFile -> ftpFile.getName().equalsIgnoreCase(fileNameToSearch));
        } catch (IOException e) {
            logger.error("Could not search for the file with name: " + fileNameToSearch + " encountered error: " + e.getLocalizedMessage(), e);
            Assert.fail("Cannot try searching for file", e);
        }

        return false;
    }

    public Optional<FTPFile> openFile(String fileName) {
        try {
            return Arrays.stream(this._ftpClient.listFiles()).filter(ftpFile -> ftpFile.getName().equalsIgnoreCase(fileName)).findFirst();
        } catch (IOException e) {
            logger.error("Could not open file with name: " + fileName + " encountered error: " + e.getLocalizedMessage(), e);
            Assert.fail("Failed to open file", e);
        }

        return Optional.empty();
    }


    public void closeConnection() {
        try {
            this._ftpClient.logout();
            this._ftpClient.disconnect();
        } catch (IOException e) {
            Assert.fail("Could not logout of the FTP Server: " + serverName + " with user: " + username);
            logger.warn("Could not logout of the FTP Server. Please consider closing the connection manually");
        }
    }

    public FTPClient get_ftpClient(){
        return this._ftpClient;
    }


    // Getters and Setters
    public String getPassword() {
        return password;
    }

    public String getServerName() {
        return serverName;
    }

    public String getUsername() {
        return username;
    }


}
