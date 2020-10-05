package framework.suiteManager;

import framework.database.ConnectionManager;
import framework.database.models.DBConnectionDTO;
import framework.database.models.TestRuntimeDTO;
import framework.suiteManager.dtos.PackagedSuite;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SuiteOrganizer {

    public SuiteOrganizer() {

    }

    public static void main(String[] args) throws SQLException, IOException {
        String applicationName = System.getProperty("ApplicationName");
        int threadCount = Integer.parseInt(System.getProperty("ThreadCount", "7"));
        ArrayList<PackagedSuite> packages = PackagedSuite.initializePackages(threadCount, System.getProperty("DistributeAgainst", "TR01,TR02,TR03,TR04,TR05,TR06,TR07,TR08,TR09,TR10,QA1,QA2,QA3,QA4,QA5,QA6,QA7,QA8,QA10"));
        int currentIterationCount = 0;
        long currentMaxRuntime = 0;
        QueryRunner regressionDB = ConnectionManager.getDBConnectionTo(DBConnectionDTO.TEST_NG_REPORTING_SERVER);
        List<TestRuntimeDTO> dtos = regressionDB.query("select * from TestRuntimeCatalog where projectSource='"+applicationName+"' order by totalRunTime DESC", new BeanListHandler<>(TestRuntimeDTO.class));
        System.out.println("Total Tests: " + dtos.size());
        // processing all tests
        Iterator<TestRuntimeDTO> iterator = dtos.iterator();
        Iterator<PackagedSuite> packageIterator = packages.iterator();
        while (iterator.hasNext()) {
            TestRuntimeDTO dto = iterator.next();
            // sort package list
            sortPackages(packages);
            packageIterator = packages.iterator();
            PackagedSuite aPackage = packageIterator.next();
            // add to the list if the packagedSuite is in current round or still in previous round
            if ((aPackage.getTotalRuntime() <= currentMaxRuntime) && (aPackage.getIterationCount() <= currentIterationCount)) {
                long updatedPackageRunTime = aPackage.addTest(dto.getFullClassName(), dto.getTotalRuntime());
                if (updatedPackageRunTime > currentMaxRuntime) {
                    currentMaxRuntime = updatedPackageRunTime;
                }
                if (currentIterationCount < aPackage.getIterationCount()) {
                    currentIterationCount = aPackage.getIterationCount();
                }
            }


        }


        // outputting distributed package numbers
        writeFileToResources(packages);

    }

    private static void sortPackages(ArrayList<PackagedSuite> packages) {
        packages.sort((o1, o2) -> {
            Long O1 = o1.getTotalRuntime();
            Long O2 = o2.getTotalRuntime();
            return O1.compareTo(O2);
        });
    }

    private static void writeFileToResources(ArrayList<PackagedSuite> packages) throws IOException {
        String targetDirectoryPath = System.getProperty("ReportDirectoryFullLocation", "C:\\tmp")+"\\";
        File file = new File(targetDirectoryPath);

        if (!file.exists()) {
            new File(targetDirectoryPath).mkdirs();
        }

        for (PackagedSuite aPackage : packages) {
            System.out.println(aPackage.toString());
            FileWriter writer = new FileWriter(targetDirectoryPath+aPackage.getPackageIdentifier()+".txt");
            for (String testName : aPackage.getTestNames()) {
                writer.write(testName);
                writer.write("\n");
            }
            writer.close();

        }
    }
}
