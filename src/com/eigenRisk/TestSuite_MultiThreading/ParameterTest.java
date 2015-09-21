package com.eigenRisk.TestSuite_MultiThreading;
 
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
 
public class ParameterTest
{
    @Parameters({ "userName" })
    @Test
    public void optionTest(@Optional("optional value") String value) {
        System.out.println("This is: " + value);
        System.out.println("Test Case Name :- insideTest1\n");
    }
    
	@Parameters({ "userName" })
	@Test(priority = 5, enabled=true)
	public void loginActivity(@Optional("loginID") String loginID) throws Exception{
		System.out.println("Test Case Name :- loadExposure\n");
		System.out.println("Login ID is > " + loginID);
		System.out.println("Test Case Name :- insideTest2\n");
	}
	

    
    @Parameters({ "userName" })
	@Test(priority = 5, enabled=true)
	public void insideTest3(@Optional("loginID") String loginID) throws Exception{
		System.out.println("Test Case Name :- insideTest3\n");
		System.out.println("Login ID is > " + loginID);
    }
    
}

