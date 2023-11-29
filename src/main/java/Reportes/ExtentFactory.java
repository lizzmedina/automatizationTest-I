package Reportes;
import com.aventstack.extentreports.ExtentReports;
public class ExtentFactory {
        public static ExtentReports getInstance() {
            ExtentReports extent = new ExtentReports();
            extent.setSystemInfo("Environment", "PROD");
            extent.setSystemInfo("OS", "Windows");
            extent.setSystemInfo("Browser", "Chrome");
            return extent;
        }
}
