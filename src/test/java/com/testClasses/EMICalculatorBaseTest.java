package com.testClasses;

import org.testng.annotations.Test;

import java.util.Hashtable;
import java.util.Properties;

import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.Status;

import baseClasses.baseTestClass;
import pageClasses.LandingPage;
import pageClasses.ProductPage;
import utilities.ReadPropertiesFile;
import utilities.TestDataProvider;

public class EMICalculatorBaseTest extends baseTestClass {
	LandingPage landingPage;
	ProductPage productPage;
	
	@Test(dataProvider="EMICalculatorTestData",description="executes the complete test")
	public void emicalculator(Hashtable<String, String> testData) {
		
		Properties prop = ReadPropertiesFile.readConfiguration();
		
		//creating test for logger to record
		logger = report.createTest("EMI_Calculator Test");
		
		//initializing the browser
		invokeBrowser(prop.getProperty("browserName"));
		
		landingPage = openApplication(testData.get("WebPageURL"));
		landingPage.clickCarLoanButton();
		landingPage.enterLoanAmount(testData.get("CarLoanAmount"));
		landingPage.enterLoanInterestRate(testData.get("InterestRate"));
		
		productPage = landingPage.enterLoanTerm(testData.get("LoanTenure"));
		productPage.clickEMIInArrears();
		productPage.clickReadMore();
		productPage.fetchTestResult();
		productPage.writeData();
		
		logger.log(Status.PASS, "EMI Calculator Base Test Pass");
	}
	
	@DataProvider
	public Object[][] EMICalculatorTestData(){
		return TestDataProvider.getTestData("EMICalculatorTestData.xlsx", "LoanDetails", "emiCalculator");
	}
}