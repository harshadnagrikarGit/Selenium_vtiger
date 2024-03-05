	package commonutils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenerImplementation implements ITestListener {
	
	ExtentReports report ;
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
//		System.out.println("TestScript execution is started");
		String methodName = result.getMethod().getMethodName();
//		Reporter.log(methodName+" TestScript execution is started",true);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		String methodName = result.getMethod().getMethodName();
//		Reporter.log(methodName+" TestScript execution is passed",true);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		String message = result.getThrowable().toString();
		System.out.println(message);
		String methodName = result.getMethod().getMethodName();
//		Reporter.log(methodName+" TestScript execution is failed "+message,true);
		
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		String methodName = result.getMethod().getMethodName();
//		Reporter.log(methodName+" TestScript execution is skipped",true);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		String methodName = result.getMethod().getMethodName();
//		Reporter.log(methodName+" TestScript execution is failed within success percentage",true);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		String methodName = result.getMethod().getMethodName();
//		Reporter.log(methodName+" TestScript execution is failed with timeout",true);
	}

	@Override
	public void onStart(ITestContext result) {
		// TODO Auto-generated method stub
//		String methodName = result.getMethod().getMethodName();
//		Reporter.log("Execution is started",true);
		
		JavaUtil jutil = new JavaUtil();
		
		
		// use ExtentSparkReporter class just  to configure extenReport
		ExtentSparkReporter reporter = new ExtentSparkReporter("./ExtentReport/Report1 "+jutil.getRandomNumber()+".html");
		reporter.config().setDocumentTitle("vtigerCRM");
		reporter.config().setTheme(Theme.DARK);
		reporter.config().setReportName("FirstReport");
		
		// use ExtentSparkReporter to generate ExtentReport
		report  = new ExtentReports();
		report.attachReporter(reporter);
		report.setSystemInfo("Browser", "Chrome");
		report.setSystemInfo("ChromeVersion","122.0.6261.95" );
		report.setSystemInfo("Author", "HarshadNagrikar");
		
		
	}

	@Override
	public void onFinish(ITestContext result) {
		// TODO Auto-generated method stub
//		String methodName = result.getMethod().getMethodName();
//		Reporter.log("Execution is finished",true);
		report.flush();
	}
	
	
	
	

}
