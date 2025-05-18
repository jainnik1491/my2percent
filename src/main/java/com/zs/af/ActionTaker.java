package com.zs.af;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class ActionTaker {


    public  void takeAction(List<Steps> steps){

        WebDriver driver = new ChromeDriver();

        steps.forEach((s)->{

            switch (s.action.toLowerCase()){
                case "sendkeys":driver.findElement(By.xpath(s.xpath)).sendKeys(s.key);
                    break;
                case "click": driver.findElement(By.xpath(s.xpath)).click();
                    break;
                case "navigate":driver.get(s.xpath);
                    break;
                default: System.out.println("please enter valid action");


            }
        });
    }
}
