package org.zilch.com.web.pages.pageEvents;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.zilch.com.utilities.DriverExtensions;
import org.zilch.com.utilities.enums.Locator;
import org.zilch.com.web.drivers.BrowserDriver;
import org.zilch.com.web.pages.BasePage;
import org.zilch.com.web.pages.pageObjects.BoardPageElements;

public class BoardPageEvents extends BasePage {
	public void Play(int[][] scores, String goldenTime)
	{
	    EnterScores(scores);
	    PredictGoldenGoalAndSubmit(goldenTime);
	}

	public void ResetScores()
	{
	    List<WebElement> increases = ele.getWebElements(Locator.XPATH, "//button[@data-test-id=\"match-team-prediction-home-increase\"]");

	    List<WebElement> homeDecreases = ele.getWebElements(Locator.XPATH,"//button[@data-test-id=\"match-team-prediction-home-decrease\"]");
	    List<WebElement> awayDecreases = ele.getWebElements(Locator.XPATH,"//button[@data-test-id=\"match-team-prediction-away-decrease\"]");

	    List<WebElement> homeScores = ele.getWebElements(Locator.XPATH,"//p[@data-test-id=\"match-team-prediction-home-score\"]");
	    List<WebElement> awayScores = ele.getWebElements(Locator.XPATH,"//p[@data-test-id=\"match-team-prediction-away-score\"]");
	    for (int i = 0; i < increases.size(); i++)
	    {
	        while (!homeScores.get(i).getText().equals("0")){
	            DriverExtensions.JavascriptClick(BrowserDriver.getDriver(), homeDecreases.get(i));
	        }
	        while (!awayScores.get(i).getText().equals("0"))
	        {
	            DriverExtensions.JavascriptClick(BrowserDriver.getDriver(), awayDecreases.get(i));
	        }
	    }
	}

	public void EnterScores(int[][] scores) {
		List<WebElement> homeIncreases = ele.getWebElements(Locator.XPATH,"//button[@data-test-id=\"match-team-prediction-home-increase\"]");
		List<WebElement> awayIncreases = ele.getWebElements(Locator.XPATH,"//button[@data-test-id=\"match-team-prediction-away-increase\"]");
	    for (int i = 0; i < scores.length; i++)
	    {
	         for(int j = 0; j < scores[i].length; j++)
	         {
	            int value = scores[i][j];
	            if (j == 0)
	            {
	                for(int k = 0; k < value; k++)
	                {
	    	            DriverExtensions.JavascriptClick(BrowserDriver.getDriver(), homeIncreases.get(i));

	                }
	            }

	            if (j == 1)
	            {
	                for (int k = 0; k < value; k++)
	                {
	    	            DriverExtensions.JavascriptClick(BrowserDriver.getDriver(), awayIncreases.get(i));

	                }
	            }
	         }
	    }
	}

	public void PredictGoldenGoalAndSubmit(String goldenTime)
	{
		ele.getWebElement(Locator.XPATH, BoardPageElements.predictionInput).clear();
		ele.getWebElement(Locator.XPATH, BoardPageElements.predictionInput).sendKeys(goldenTime);

		ele.getWebElement(Locator.ID, BoardPageElements.submitBtn).click();
	}

}
