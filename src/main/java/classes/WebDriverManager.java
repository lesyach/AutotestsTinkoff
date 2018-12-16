package classes;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class WebDriverManager {

    private WebDriver webDriver;
    private Logger logger;
    private Actions actions;

    public WebDriverManager(String url, Logger logger) throws Exception{
        this.logger = logger;
        try {
            System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe");
            webDriver = new ChromeDriver();
            webDriver.manage().window().maximize();
            webDriver.get(url);
            logger.logImportantInfo("Сайт " + url + " запущен");
            actions = new Actions(webDriver);
        }
        catch (Exception e){
            logger.logError("Сайт " + url + " НЕ запущен");
            logger.logError("Текст ошибки: " + e.getMessage());
            throw e;
        }
    }

    public WebElementManager findElementByClassName(String className) throws Exception {
        try{
            Thread.sleep(2000);
            WebElement webElement = webDriver.findElement(By.className(className));
            actions.moveToElement(webElement);
            actions.perform();
            logger.logInfo("Найден элемент по классу: " + className, ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE));
            return new WebElementManager(webElement, logger, webDriver);
        }
        catch (Exception e){
            logger.logError("Невозможно найти элемент по классу: " + className);
            logger.logError("Текст ошибки: " + e.getMessage());
            throw e;
        }
    }

    public String getElementTextByClassName(String className) throws Exception{
        try{
            Thread.sleep(2000);
            WebElement webElement = webDriver.findElement(By.className(className));
            actions.moveToElement(webElement);
            actions.perform();
            logger.logInfo("Получен текст элемента по классу: " + className, ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE));
            return webElement.getText();
        }
        catch (Exception e){
            logger.logError("Невозможно найти элемент по классу: " + className);
            logger.logError("Текст ошибки: " + e.getMessage());
            throw e;
        }
    }

    public String getElementTextByClassName(String className, int position) throws Exception {
        try{
            Thread.sleep(2000);
            WebElement element = getElementsByClassname(className).get(position);
            actions.moveToElement(element);
            actions.perform();
            logger.logInfo("Найден " + position + " элемент по классу: " + className,
                    ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE));
            return element.getText();
        }
        catch (Exception e){
            logger.logError("Невозможно найти элементы по классу: " + className);
            logger.logError("Текст ошибки: " + e.getMessage());
            throw e;
        }
    }

    public void clickElementByText(String text) throws Exception {
        try{
            Thread.sleep(2000);
            WebElement webElement = webDriver.findElement(By.xpath(".//*[text()='"+text+"']"));
            actions.moveToElement(webElement);
            actions.perform();
            logger.logInfo("Клик по элементу с текстом: " + text, ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE));
            webElement.click();
        }
        catch (Exception e){
            logger.logError("Невозможно найти элемент с текстом: " + text);
            logger.logError("Текст ошибки: " + e.getMessage());
            throw e;
        }
    }

    public void clickElementByClass(String className) throws Exception {
        try{
            Thread.sleep(2000);
            WebElement webElement = webDriver.findElement(By.className(className));
            actions.moveToElement(webElement);
            actions.perform();
            logger.logInfo("Клик по элементу класса: " + className, ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE));
            webElement.click();
        }
        catch (Exception e){
            logger.logError("Невозможно найти элемент по классу: " + className);
            logger.logError("Текст ошибки: " + e.getMessage());
            throw e;
        }
    }

    public List<WebElement> getElementsByClassname(String className) throws Exception {
        try{
            Thread.sleep(2000);
            List<WebElement> elements = webDriver.findElements(By.className(className));
            logger.logInfo("Найдены элементы по классу: " + className, ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE));
            return elements;
        }
        catch (Exception e){
            logger.logError("Невозможно найти элементы по классу: " + className);
            logger.logError("Текст ошибки: " + e.getMessage());
            throw e;
        }
    }

    public void setTextInInputByClass(String className, int position, String text) throws Exception {
        try{
            Thread.sleep(2000);
            WebElement element = getElementsByClassname(className).get(position);
            actions.moveToElement(element);
            actions.perform();
            logger.logInfo("Найден " + position + " элемент по классу: " + className);
            element.sendKeys(text);
            logger.logInfo("Вставка теста: " + text,
                    ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE));
        }
        catch (Exception e){
            logger.logError("Невозможно найти элементы по классу: " + className);
            logger.logError("Текст ошибки: " + e.getMessage());
            throw e;
        }
    }

    public void setTextInInputByName(String name, String text) throws Exception {
        try{
            Thread.sleep(2000);
            WebElement element = webDriver.findElement(By.name(name));
            actions.moveToElement(element);
            actions.perform();
            logger.logInfo("Найден элемент по имени: " + name,
                    ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE));
            element.sendKeys(text);
            logger.logInfo("Вставка теста: " + text,
                    ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE));
        }
        catch (Exception e){
            logger.logError("Невозможно найти элементы по имени: " + name);
            logger.logError("Текст ошибки: " + e.getMessage());
            throw e;
        }
    }

    public void close(){
        webDriver.close();
    }
}
