package actions;

import classes.Logger;
import classes.WebDriverManager;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class PageActions {

    private WebDriverManager webDriverManager;
    private Logger logger;

    public PageActions(String url, Logger logger) throws Exception {
        this.logger = logger;
        webDriverManager = new WebDriverManager(url, logger);
    }

    /**
     * Description: Клик по пункту нижнего футера
     * @param linkText - имя пункта
     */
    public void clickBottomFooter(String linkText) throws Exception {
        logger.logImportantInfo("Клик по пункту нижнего футера: " + linkText);
        webDriverManager.
                findElementByClassName("footer__Footer__navFull_2x1Og").
                clickToElementByText(linkText);
    }

    /**
     * Description: Выбор категории платежа
     * @param categoryText - нужная категория
     */
    public void clickPaymentCategory(String categoryText) throws Exception {
        logger.logImportantInfo("Выбор категории платежа: "+categoryText);
        WebElement rightClider = webDriverManager.
                findElementByClassName("PaymentsCategories__slider_Isgo4").
                getElementByClass("SliderControls__control_3mN2y", 1);
        boolean categoryClicked = false;
        while(!categoryClicked){
            try{
                webDriverManager.
                        findElementByClassName("PaymentsCategories__slider_Isgo4").
                        clickToElementByText(categoryText);
                categoryClicked = true;
                break;
            }
            catch (Exception e){
                if(rightClider.getAttribute("class").contains("SliderControls__control_enabled_29DWl"))
                    rightClider.click();
            }
        }
        if(!categoryClicked){
            logger.logError("Нет пункта с именем \""+categoryText+"\"");
            throw new Exception("Нет пункта с именем \""+categoryText+"\"");
        }
    }

    /**
     * Description: Проверка региона, если не совпадает - поменять
     * @param region - имя региона
     */
    public void checkRegion(String region) throws Exception {
        logger.logImportantInfo("Проверка региона: " + region);
        if(!webDriverManager.getElementTextByClassName("PaymentsCatalogHeader__regionSelect_wxV0X").
                equals(region)){
            logger.logImportantInfo("Регион не совпадает, смена региона");
            webDriverManager.clickElementByClass("PaymentsCatalogHeader__regionSelect_wxV0X");
            webDriverManager.clickElementByText(region);
        }
    }

    /**
     * Description: Вернуть список поставщиков услуг
     */
    public List<String> getServiceProviders() throws Exception {
        List<String> serviceProviders = new ArrayList<String>();
        for(WebElement webElement : webDriverManager.getElementsByClassname("UIMenu__link_enabledFade_bOPvf")){
            serviceProviders.add(webElement.getText());
        }
        logger.logImportantInfo("Получен список поставщиков услуг");
        return serviceProviders;
    }

    /**
     * Description: Клик по поставщику услуг
     * @param serviceProvider - имя поставщика
     */
    public void clickServiceProvider(String serviceProvider) throws Exception {
        logger.logImportantInfo("Клик по поставщику услуг: " + serviceProvider);
        webDriverManager.clickElementByText(serviceProvider);
    }

    /**
     * Description: Проверка полей во складке "Оплатить ЖКУ в Москве"
     */
    public void checkPaymentForm() throws Exception {
        logger.logImportantInfo("Проверка полей во складке \"Оплатить ЖКУ в Москве\"");
        webDriverManager.clickElementByText("Оплатить ЖКУ в Москве");

        //клик по кнопке оплаты без ввода данных
        webDriverManager.clickElementByClass("ui-button__text");
        if(webDriverManager.getElementTextByClassName("ui-form-field-error-message",0).equals("Поле обязательное")
                && webDriverManager.getElementTextByClassName("ui-form-field-error-message",1).equals("Поле обязательное")
                && webDriverManager.getElementTextByClassName("ui-form-field-error-message",2).equals("Поле обязательное")){
            logger.logImportantInfo("Проверка оплаты без ввода данных пройдена!");
        }
        else {
            logger.logError("Проверка оплаты без ввода данных !НЕ! пройдена!");
        }

        //ввод левых данных и клик по кнопке
        webDriverManager.setTextInInputByName("provider-payerCode","1020");
        webDriverManager.setTextInInputByName("provider-period","16699");
        webDriverManager.setTextInInputByClass("Input__valueContent_3N2Qn",0,"-460789");
        webDriverManager.setTextInInputByClass("Input__valueContent_3N2Qn",1,"-781029");
        webDriverManager.clickElementByClass("ui-button__text");
        if(webDriverManager.getElementTextByClassName("ui-form-field-error-message",0).equals("Поле неправильно заполнено")
                && webDriverManager.getElementTextByClassName("ui-form-field-error-message",1).equals("Поле заполнено некорректно")
                && webDriverManager.getElementTextByClassName("ui-form-field-error-message",2).equals("Поле заполнено неверно")
                && webDriverManager.getElementTextByClassName("ui-form-field-error-message",3).equals("Поле заполнено неверно")){
            logger.logImportantInfo("Проверка оплаты свводом некорректных данных пройдена!");
        }
        else {
            logger.logError("Проверка оплаты без ввода данных !НЕ! пройдена!");
        }
    }

    /**
     * Description: Проверить, что первый поставщик первый в поиске)
     * @param serviceProvider - имя поставщика
     */
    public void checkFirstServiceProvider(String serviceProvider) throws Exception {
        webDriverManager.setTextInInputByClass("Input__valueContent_3N2Qn",0,serviceProvider);
        if (webDriverManager.getElementTextByClassName("Text__text_size_17_28jfD").
                equals(serviceProvider)){
            webDriverManager.clickElementByClass("Text__text_size_17_28jfD");
        }
    }

    /**
     * Description: Закрыть браузер
     */
    public void close() {
        webDriverManager.close();
    }
}
