package com.ExcelRPorject3.Amazon;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerTest implements ITestListener {
	 String RESET = "\u001B[0m";
     String RED = "\u001B[31m";
     String GREEN = "\u001B[32m";
     String YELLOW = "\u001B[33m";
	@Override		
    public void onFinish(ITestContext Result) {					
		System.out.println(Result.getName()+" test case finished");				
        		
	}
	@Override		
    public void onStart(ITestContext Result) {					
		System.out.println(GREEN+Result.getName()+RESET+GREEN+" test case started"+RESET);
        		
    }
	@Override		
    public void onTestFailedButWithinSuccessPercentage(ITestResult Result) {
		        		
    }		

    @Override		
    public void onTestFailure(ITestResult Result) {					
        System.out.println(RED+"The name of the test case failed is: "+RESET+RED+Result.getName()+RESET);        		
    }		

    @Override		
    public void onTestSkipped(ITestResult Result) {
    	System.out.println(YELLOW+"The name of the test case failed is: "+RESET+YELLOW+Result.getName()+RESET);
    }		

    @Override		
    public void onTestStart(ITestResult Result) {					
    	System.out.println(GREEN+Result.getName()+RESET+GREEN+" test case started"+RESET);        		
    }

    @Override		
    public void onTestSuccess(ITestResult Result) {	
    	System.out.println(GREEN+Result.getName()+RESET+GREEN+" test case sucess"+RESET);        		
    }
	
}
