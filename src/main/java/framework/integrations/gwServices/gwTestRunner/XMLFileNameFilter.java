package framework.integrations.gwServices.gwTestRunner;

import java.io.File;
import java.io.FilenameFilter;

public class XMLFileNameFilter implements FilenameFilter {
    @Override
    public boolean accept(File dir, String name) {
        return name.toLowerCase().endsWith(".xml");
    }
}
