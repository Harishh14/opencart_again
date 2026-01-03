package Utilities;

import java.awt.Desktop;
import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.*;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Baseclass.BaseClass;

public class ExtentReport implements ITestListener, ISuiteListener {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static String reportPath;

    /* ======================= EXTENT SETUP ======================= */

    private static ExtentReports getReportObject() {

        if (extent == null) {
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            reportPath = System.getProperty("user.dir")
                    + "/Reports/ExtentReport_" + timeStamp + ".html";

            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            spark.config().setDocumentTitle("Automation Test Report");
            spark.config().setReportName("OpenCart Automation Execution");

            extent = new ExtentReports();
            extent.attachReporter(spark);

            extent.setSystemInfo("Project", "OpenCart Automation");
            extent.setSystemInfo("Tester", "Himanshu");
            extent.setSystemInfo("Environment", "QA");
        }
        return extent;
    }

    /* ======================= TEST EVENTS ======================= */

    @Override
    public void onTestStart(ITestResult result) {

        ExtentReports extentReports = getReportObject();

        Method method = result.getMethod().getConstructorOrMethod().getMethod();
        ExtentTest extentTest = extentReports.createTest(method.getName());

        // Capture groups
        String[] groups = result.getMethod().getGroups();
        if (groups.length > 0) {
            extentTest.assignCategory(groups);
        }

        // Capture XML parameters
        ITestContext context = result.getTestContext();
        extentTest.info("Browser : " + context.getCurrentXmlTest().getParameter("browser"));
        extentTest.info("OS : " + context.getCurrentXmlTest().getParameter("os"));

        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        test.get().fail(result.getThrowable());

        WebDriver driver = ((BaseClass) result.getInstance()).getDriver();
        String screenshotPath = captureScreenshot(driver, result.getMethod().getMethodName());

        try {
            test.get().addScreenCaptureFromPath(screenshotPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().skip("Test Skipped");
    }

    /* ======================= SUITE FINISH ======================= */

    @Override
    public void onFinish(ISuite suite) {
        extent.flush();
        openReport();
//        sendReportEmail();
    }

    /* ======================= SCREENSHOT ======================= */

    private static String captureScreenshot(WebDriver driver, String testName) {

        String path = System.getProperty("user.dir")
                + "/Screenshots/" + testName + ".png";

        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);
            File dest = new File(path);
            FileUtils.copyFile(src, dest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }

    /* ======================= AUTO OPEN REPORT ======================= */

    private static void openReport() {
        try {
            Desktop.getDesktop().browse(new File(reportPath).toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* ======================= EMAIL REPORT ======================= */

//    private static void sendReportEmail() {
//
//        final String fromEmail = "yourmail@gmail.com";
//        final String password = "your_app_password";
//        final String toEmail = "receiver@gmail.com";
//
//        Properties props = new Properties();
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.port", "587");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//
//        Session session = Session.getInstance(props,
//                new Authenticator() {
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(fromEmail, password);
//                    }
//                });
//
//        try {
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(fromEmail));
//            message.setRecipients(Message.RecipientType.TO,
//                    InternetAddress.parse(toEmail));
//            message.setSubject("Automation Execution Report");
//
//            MimeBodyPart textPart = new MimeBodyPart();
//            textPart.setText("Please find attached automation execution report.");
//
//            MimeBodyPart attachment = new MimeBodyPart();
//            attachment.attachFile(reportPath);
//
//            Multipart multipart = new MimeMultipart();
//            multipart.addBodyPart(textPart);
//            multipart.addBodyPart(attachment);
//
//            message.setContent(multipart);
//
//            Transport.send(message);
//            System.out.println("Extent Report Email Sent Successfully");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
