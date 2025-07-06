package com.redbus;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RedBusAutomationAssignmentFinalSolution {

	public static void main(String[] args) throws InterruptedException {

		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--start-maximized");
		WebDriver wd = new ChromeDriver(chromeOptions);
		WebDriverWait wait = new WebDriverWait(wd,Duration.ofSeconds(30));

		wd.get("https://www.redbus.in/");

		By sourceButtonLocator = By.xpath("//div[contains(@class,'srcDestWrapper') and (@role=\"button\")]");
		WebElement sourceButton=wait.until(ExpectedConditions.visibilityOfElementLocated(sourceButtonLocator));
		sourceButton.click();
		
		By searchSuggestionSectionLocator=By.xpath("//*[contains(@class,'searchSuggestionWrapper')]");
		wait.until(ExpectedConditions.visibilityOfElementLocated(searchSuggestionSectionLocator));
		selectLocation(wd, wait,"Mumbai");
		selectLocation(wd, wait,"Pune");
		
		
		By searchButtonLocator=By.xpath("//button[contains(@class,'searchButtonWrapper')]");
		WebElement searchButton=wait.until(ExpectedConditions.elementToBeClickable(searchButtonLocator));
		searchButton.click();
		
		By primoButtonLocator=By.xpath("//div[contains(text(),'Primo')]");
		WebElement primoButton=wait.until(ExpectedConditions.elementToBeClickable(primoButtonLocator));
		primoButton.click();
		
		By acButtonLocator=By.xpath("//div[contains(text(),'AC')]");
		WebElement acButton=wait.until(ExpectedConditions.elementToBeClickable(acButtonLocator));
		acButton.click();
		
		By subtitleButtonLocator=By.xpath("//span[contains(@class,'subtitle')]");
		WebElement subtitleButton=null;
		if(wait.until(ExpectedConditions.textToBePresentInElementLocated(subtitleButtonLocator, "buses"))) {
			subtitleButton=wait.until(ExpectedConditions.visibilityOfElementLocated(subtitleButtonLocator));
		}
		System.out.println(subtitleButton.getText());
		
		By rowListLocator=By.xpath("//li[contains(@class,'tupleWrapper')]");
			
		JavascriptExecutor js=(JavascriptExecutor)wd;

		while(true) {	
			List<WebElement> rowList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(rowListLocator));
			List<WebElement> endOfList=wd.findElements(By.xpath("//span[contains(text(),'End of list')]"));
			
			if(!endOfList.isEmpty())
				break;
			js.executeScript("arguments[0].scrollIntoView({behavior:'smooth'})", rowList.get(rowList.size()-2));		
		}
		
		List<WebElement> rowList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(rowListLocator));

		By busesNameLocator=By.xpath(".//div[contains(@class,'travelsName')]");
		for(WebElement row:rowList) {
		System.out.println(row.findElement(busesNameLocator).getText());
		}
		
		System.out.println("Total number of buses loaded with Primo and Ac are: "+rowList.size());	
	}

	private static void selectLocation(WebDriver wd, WebDriverWait wait, String locationName) {
		WebElement searchTextBoxElement=wd.switchTo().activeElement();
		searchTextBoxElement.sendKeys(locationName);
		
		By searchCategoryLocator=By.xpath("//*[contains(@class,'searchCategory')]");
		List<WebElement> searchList= wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(searchCategoryLocator,2));
		
		System.out.println(searchList.size());
		
		
		WebElement locationSearchResult=searchList.get(0);
		List<WebElement> locationList=locationSearchResult.findElements(By.xpath(".//div[contains(@class,'listHeader')]"));
		System.out.println(locationList.size());
		for(WebElement location:locationList) {
			String lName=location.getText();
			if(lName.equalsIgnoreCase(locationName)) {
				location.click();
				break;
			}
		}
	}

}
