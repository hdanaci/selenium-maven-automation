package com.dice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author bravo 1)Create arraylist of keywords. add 20 different keyworks
 *         list.add("java"); pass each item to search box and print accordingly.
 *         modify your arraylist
 * 
 *         java-1234
 * 
 *         2) Store all keywords into a text file read the text file and repeat
 *         above steps.
 * 
 *         store keyword and results count into an arraylist. ----
 * 
 *         after closing browser. print contents of arraylist that was updated
 *         each time we looped.
 * 
 *         commit > push > share your github link
 */
public class DiceComTask1 {
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();

		String[] list = { "Junior Developer", "Java Developer", "Senior Business Analyst", "Senior Software Engineer",
				"Software Engineer", "System Administrator", "Selenium Automation Engineer",
				"Selenium Mobile Web service Tester", "Selenium WebDriver", "Ruby Rails Developer", "RF Engineer",
				"Test Engineer", "Computer Operator", "Designer", "Linux Administrator", "Lead Business Analyst",
				"Marketing Manager", "Network Administrator", "Network Engineer", "Business Analyst Project Manager" };

		List<String> keywords = new ArrayList<>();
		for (int i = 0; i < list.length; i++) {
			keywords.add(list[i]);
		}

		String[] zips = { "90001", "80001", "32004", "60001", "48001", "10001", "43001", "73301", "20040", "98001",
				"15001", "37010", "27006", "30303", "55001", "85001", "66002", "46001", "96701", "35004" };

		List<String> zipCode = new ArrayList<>();
		for (int i = 0; i < list.length; i++) {
			zipCode.add(zips[i]);
		}

		List<String> jobCount = new ArrayList<>();

		for (int i = 0; i < zipCode.size(); i++) {
			WebDriver driver = new ChromeDriver();
			checkKeyword(driver, keywords.get(i), zipCode.get(i));
			jobCount.add(checkCount(driver, keywords.get(i), zipCode.get(i)));
			driver.close();
		}
		System.out.println(jobCount);

	}

	public static void checkKeyword(WebDriver driver2, String key, String zip) {

		driver2.manage().window().fullscreen();
		driver2.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		String url = "https://dice.com";
		driver2.get(url);

		String actualTitle = driver2.getTitle();
		String expectedTitle = "Job Search for Technology Professionals | Dice.com";

		if (actualTitle.equals(expectedTitle)) {
			System.out.println("Step PASS. Dice homepage successfully loaded");
		} else {
			System.out.println("Step FAIL. Dice homepage did not load successfully");
			throw new RuntimeException("Step FAIL. Dice homepage did not load successfully");
		}

		String keyword = key;
		driver2.findElement(By.id("search-field-keyword")).clear();
		driver2.findElement(By.id("search-field-keyword")).sendKeys(keyword);

		String location = zip;
		driver2.findElement(By.id("search-field-location")).clear();
		driver2.findElement(By.id("search-field-location")).sendKeys(location);

		driver2.findElement(By.id("findTechJobs")).click();

	}

	public static String checkCount(WebDriver driver2, String key, String zip) {
		String count = driver2.findElement(By.id("posiCountId")).getText();
		System.out.println(count);

		int countResult = Integer.parseInt(count.replaceAll(",", ""));

		if (countResult > 0) {
			return "Keyword : " + key + " search returned : " + countResult + " results in " + zip;
		} else {
			return "Step FAIL : Keyword : " + key + " search returned : " + countResult + " results in " + zip;
		}

	}

}
