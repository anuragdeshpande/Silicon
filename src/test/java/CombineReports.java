import framework.ReportManager;

import java.io.IOException;
import java.util.Arrays;

public class CombineReports {
    public static void main(String[] args) throws IOException {
        ReportManager.generateCombinedReports(args[0], Arrays.copyOfRange(args, 1, args.length));
    }
}
