package com.dice;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DiceComReadFileTask2 {
	public static List<String> keywordList = new ArrayList<>();
	public static List<String> zipCodeList = new ArrayList<>();
	public static List<String> jobCount = new ArrayList<>();

	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();

		try (FileReader fr = new FileReader("Keywords.txt"); BufferedReader br = new BufferedReader(fr);) {

			String value = null;
			while ((value = br.readLine()) != null) {
				keywordList.add(value);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try (FileReader fr = new FileReader("ZipList.txt"); BufferedReader br = new BufferedReader(fr);) {

			String value = null;
			while ((value = br.readLine()) != null) {
				zipCodeList.add(value);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < keywordList.size(); i++) {
			WebDriver driver = new ChromeDriver();
			DiceComTask1.checkKeyword(driver, keywordList.get(i), zipCodeList.get(i));
			jobCount.add(DiceComTask1.checkCount(driver, keywordList.get(i), zipCodeList.get(i)));
			driver.quit();
		}

		for (String each : jobCount) {
			System.out.println(each);
		}

	}

}
