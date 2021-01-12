package framework.integrations.gwServices.adminImporter;

import framework.applications.gw.GuidewireCenter;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.File;

import static framework.integrations.http.HTTPOperations.makeMultiPartPOSTRequest;

public class AdminDataImporter <T extends GuidewireCenter> {
    private final T guidewireCenter;

    public AdminDataImporter(T guidewireCenter){
        this.guidewireCenter = guidewireCenter;
    }

    public void importData(String adminDataResourcePathReference){
        File adminDataFile = new File(adminDataResourcePathReference);
        MultipartEntityBuilder postRequestBody = MultipartEntityBuilder.create();
        postRequestBody.addBinaryBody("file", adminDataFile, ContentType.APPLICATION_OCTET_STREAM, "adminData.xml");
        makeMultiPartPOSTRequest(guidewireCenter.getEnvironment().getEnvironmentUrl()+"/service/adminimport?adminFileType=admin", postRequestBody);
    }
}
