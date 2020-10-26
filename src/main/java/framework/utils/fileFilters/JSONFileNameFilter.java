package framework.utils.fileFilters;

import java.io.File;
import java.io.FilenameFilter;

public class JSONFileNameFilter implements FilenameFilter {
    @Override
    public boolean accept(File dir, String name) {
        return name.toLowerCase().endsWith(".json");
    }
}
