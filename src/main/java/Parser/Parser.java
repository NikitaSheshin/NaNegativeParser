package Parser;

import Model.Review;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    WebDriver browser;
    String nameOfChapter;

    private Parser(String nameOfChapter){
        System.setProperty
                ("webdriver.chrome.driver", "A:\\5семестр\\JavaLabs\\SeleniumParser\\driver\\chromedriver.exe");
        browser = new ChromeDriver();
        this.nameOfChapter = nameOfChapter;
    }

    public static Parser initParser(String nameOfChapter){
        return new Parser(nameOfChapter);
    }

    public ArrayList<Review> parse() throws InterruptedException {
        openBrowser();
        var allReviews = getInfoFromChapter();
        closeBrowser();

        return allReviews;
    }

    private ArrayList<Review> getInfoFromChapter(){
        ArrayList<Review> allReviews = new ArrayList<>();

        int countOfPages = getCountOfPages();

        for(int i = 1; i < countOfPages; ++i){

            JavascriptExecutor js = (JavascriptExecutor) browser;
            WebElement nextPage = browser.findElement(By.xpath("//*[@id=\"main\"]/div[1]/div[3]/ul/li[" +
                    i +
                    "]"));
            js.executeScript("arguments[0].scrollIntoView();", nextPage);

            nextPage.click();
        }

        List<WebElement> c = browser.findElements(By.xpath("//*[@id=\"main\"]/div[1]/div[2]/div/div"));

        return allReviews;
    }

    private int getCountOfPages(){
        return browser.findElements(By.xpath("//*[@id=\"main\"]/div[1]/div[3]/ul/li")).size();
    }

    private void openBrowser() { browser.get(nameOfChapter); }
    private void closeBrowser(){
        browser.quit();
    }
}
