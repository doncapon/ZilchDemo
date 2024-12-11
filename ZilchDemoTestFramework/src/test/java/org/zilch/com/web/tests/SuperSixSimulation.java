package org.zilch.com.web.tests;

import java.util.Random;
import org.testng.annotations.Test;
import org.zilch.com.base.WebBaseTest;
import org.zilch.com.web.pages.pageEvents.*;

public class SuperSixSimulation extends WebBaseTest {
	private String doncapon = configManager.getConfigProperty("user1");
	private String doncPin = configManager.getConfigProperty("password1");

	private String opeolu =  configManager.getConfigProperty("user2");;
	private String opePin = configManager.getConfigProperty("password2");

	private String yankosmgt = configManager.getConfigProperty("user3");
	private String yinkaPin = configManager.getConfigProperty("password3");

	HomePageEvents homeEvents;
	LoginPageEvents loginEvents;
	PlayLandPageEvents playLandEvents;
	BoardPageEvents boardEvents;

	private String goldenGoal = "";
	private int[][] scores = new int[][] {
	    new int[] { 0, 0 },
	    new int[] { 0, 0 },
	    new int[] { 0, 0 },
	    new int[] { 0, 0 },
	    new int[]{ 0, 0 },
	    new int[]{ 0, 0 } };

	public void GenerateData()
	{
	    int[] arr = new int[] {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1
	                            ,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
	                             2,2,2,2,2,2,2,2,2,2,2,2,2,3,3,3,3,3,3,3,3,3,
	                             4,4,4,4,4,5,5,5,5,6,6,7,0,1,1,1,1,1,1,0,0,};
	    
	            Random random = new Random();

	    for (int i = 0; i < 6; i++) {
	        for (int j = 0; j < 2; j++) {
	            int choice = random.nextInt(0, arr.length);
	            scores[i][j] = arr[choice];
	        }
	    }

	    goldenGoal =random.nextInt(1, 3) + "";
	}

  @Test(priority = 1)
  public void segunPlay() {
	  player(doncapon, doncPin);
  }
  
  @Test(priority = 2)
  public void opeoluwaPlay() {
		player(opeolu, opePin);
  }
  
  @Test(priority = 3)
  public void damiPlay() {
	  player(yankosmgt, yinkaPin);
  }

  public void player(String username, String pin){
	  GenerateData();

	  homeEvents = new HomePageEvents();
	  logger.info("Clicking login button");
	  homeEvents.clickLogin();
	  logger.info("Entering login credentials");
	  loginEvents = new LoginPageEvents();
	  loginEvents.enterCredentials(yankosmgt,yinkaPin);
	  playLandEvents = new PlayLandPageEvents();
	  playLandEvents.ClickPlayButton();
	  boardEvents = new BoardPageEvents();
	  boardEvents.ResetScores();
	  boardEvents.Play(scores, goldenGoal);
  }
}
