package com.redbus;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RedBusAutomationAssignment {

	public static void main(String[] args) throws InterruptedException {

		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--start-maximized");
		WebDriver wd = new ChromeDriver(chromeOptions);
		WebDriverWait wait = new WebDriverWait(wd,Duration.ofSeconds(30));

		wd.get("https://www.redbus.in/");
//		wd.manage().window().maximize();

		//Locating the element
		By sourceButtonLocator = By.xpath("//div[contains(@class,'srcDestWrapper') and (@role=\"button\")]");
		WebElement sourceButton=wait.until(ExpectedConditions.visibilityOfElementLocated(sourceButtonLocator));
//		//Finding the element
//		WebElement sourceButton = wd.findElement(sourceButtonLocator);
		sourceButton.click();
		
		By searchSuggestionSectionLocator=By.xpath("//*[contains(@class,'searchSuggestionWrapper')]");
		wait.until(ExpectedConditions.visibilityOfElementLocated(searchSuggestionSectionLocator));
		WebElement searchTextBoxElement=wd.switchTo().activeElement();
		searchTextBoxElement.sendKeys("Mumbai");
		
		By searchCategoryLocator=By.xpath("//*[contains(@class,'searchCategory')]");
		List<WebElement> searchList= wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(searchCategoryLocator,2));
		
		System.out.println(searchList.size());
		
		
		WebElement locationSearchResult=searchList.get(0);
		List<WebElement> locationList=locationSearchResult.findElements(By.xpath(".//div[contains(@class,'listHeader')]"));
		System.out.println(locationList.size());
		for(WebElement location:locationList) {
			String lName=location.getText();
			if(lName.equalsIgnoreCase("Mumbai")) {
				location.click();
				break;
			}
		}
		
		
		
		//Focus on the To Section
		
		WebElement toTextBox=wd.switchTo().activeElement();
		
		toTextBox.sendKeys("Pune");
		
		By toSearchCategoryLocator=By.xpath("//*[contains(@class,'searchCategory')]");
		List<WebElement> toSearchList= wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(toSearchCategoryLocator,2));
		
		System.out.println(toSearchList.size());
		
		
		WebElement toLocationCalegory=searchList.get(0);
		List<WebElement> toLocationList=toLocationCalegory.findElements(By.xpath(".//div[contains(@class,'listHeader')]"));
		System.out.println(toLocationList.size());
		for(WebElement location:toLocationList) {
			String lName=location.getText();
			if(lName.equalsIgnoreCase("Mumbai")) {
				location.click();
				break;
			}
		}

	}

}
