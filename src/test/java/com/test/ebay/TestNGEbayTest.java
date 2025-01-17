package com.test.ebay;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.base.Base;

public class TestNGEbayTest extends Base {
	
	/*
	 * Simple demonstration 
	 * on collecting web elements into list
	 * and manipulating list contents 
	 * by looping through the list
	 */
	
	@BeforeClass
    //@BeforeMethod
    public void setUp() {
		initialize("https://www.ebay.com/");		
	}
	
	@Test
	public void searchTest() {
		WebElement searchField = driver.findElement(By.id("gh-ac"));
	
		searchField.clear();
		searchField.sendKeys("Movies");
		searchField.sendKeys(Keys.ENTER);
		
		int counter = 0;
		 List<WebElement> movies = driver.findElements(By.xpath("//h3[@class='s-item__title']"));
		 for(WebElement movie : movies) {
			String title = movie.getText();
			counter++;
			 System.out.println(counter+" Movie is: " + title);
		 }
		 /*
		  * 		 for(int i=0; i<=movies.size(); i++) {
			String title = driver.findElement(By.xpath("//h3[@class='s-item__title']")).getText();
			
				 System.out.println(i+" Movie is: " + title);
		 }
		  */
	}
	
	@AfterMethod
	public void tearDown() {
		killBrowser();
	}
	
}
