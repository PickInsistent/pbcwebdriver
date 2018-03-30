package com.example.pbcwebdriver.downloader;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author LiZhanPing
 */
public class PbccrcDownloader {
    public static void download() {
//        System.setProperty("webdriver.gecko.driver", "D:\\Program Files\\firefox\\geckodriver.exe");
        WebDriver webDriver = new FirefoxDriver(new FirefoxProfile(new File("C:\\Users\\Administrator\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\m7p6xnv2.default")));
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.get("https://ipcrs.pbccrc.org.cn/");
        webDriver.switchTo().frame("conFrame");
        System.out.println("webDriver = " + webDriver.getPageSource());
        for (String s : webDriver.getWindowHandles()) {
            System.out.println("s = " + s);
        }
        WebElement start = webDriver.findElement(By.xpath("//*[@class='banner_bot']//a"));
        start.click();
        WebElement loginname = webDriver.findElement(By.id("loginname"));
        loginname.sendKeys("SJ9301127");
        WebElement pass = webDriver.findElement(By.id("pass"));
        pass.click();
        String aClass = pass.getAttribute("class");
        System.out.println("aClass = " + aClass);
        pass.sendKeys("ssdfsdfsdff");
        (new Actions(webDriver)).sendKeys(pass, "ssdfsdfsdff");
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].value=\"aaa\"", pass);
        System.out.println("请输入验证码:");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        WebElement captche = webDriver.findElement(By.id("_@IMGRC@_"));
        captche.sendKeys(s);
        WebElement submit = webDriver.findElement(By.className("inputBtn btn2"));
        submit.submit();
        try {
            //让线程等待3秒钟
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        webDriver.quit();  //退出driver
    }

    public static void main(String[] args) {
        download();
    }
}
