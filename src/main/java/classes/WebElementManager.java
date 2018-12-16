package classes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebElementManager {
    private WebElement webElement;
    private WebDriver webDriver;
    private Logger logger;
    private Actions actions;

    public WebElementManager(WebElement webElement, Logger logger, WebDriver webDriver){
        this.webElement = webElement;
        this.logger = logger;
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
    }

    public void clickToElementByText(String text) throws Exception {
        try{
            Thread.sleep(2000);
            WebElement element = webElement.findElement(By.xpath(".//*[text()='"+text+"']"));
            logger.logInfo("Клик по элементу  текстом: " + text);
            element.click();
        }
        catch (Exception e){
            logger.logError("Невозможно найти элементы с текстом: " + text);
            logger.logError("Текст ошибки: " + e.getMessage());
            throw e;
        }
    }

    public WebElement getElementByClass(String className, int position) throws Exception {
        try{
            Thread.sleep(2000);
            WebElement element = webElement.findElements(By.className(className)).get(position);
            logger.logInfo("Найден " + position + " элемент по классу: " + className);
            return element;
        }
        catch (Exception e){
            logger.logError("Невозможно найти элементы по класу: " + className);
            logger.logError("Текст ошибки: " + e.getMessage());
            throw e;
        }
    }
}
