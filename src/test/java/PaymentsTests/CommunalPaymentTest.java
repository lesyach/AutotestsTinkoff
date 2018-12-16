package PaymentsTests;

import actions.*;
import classes.Logger;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class CommunalPaymentTest {

    private PageActions basicPageActions;
    private Logger logger;

    @Before
    public void before() throws Exception {
        logger = new Logger(this.getClass().getSimpleName());
    }

    @Test
    public void testBody() throws Exception {
        basicPageActions = new PageActions("https://www.tinkoff.ru/", logger);
        basicPageActions.clickBottomFooter("Платежи");
        basicPageActions.clickPaymentCategory("ЖКХ");
        basicPageActions.checkRegion("г. Москва");
        List<String> serviceProvidersMsk = basicPageActions.getServiceProviders();
        basicPageActions.clickServiceProvider(serviceProvidersMsk.get(0));
        basicPageActions.checkPaymentForm();
        basicPageActions.clickBottomFooter("Платежи");
        basicPageActions.clickPaymentCategory("ЖКХ");
        basicPageActions.checkFirstServiceProvider(serviceProvidersMsk.get(0));
        basicPageActions.checkPaymentForm();
        basicPageActions.clickBottomFooter("Платежи");
        basicPageActions.clickPaymentCategory("ЖКХ");
        basicPageActions.checkRegion("г. Санкт-Петербург");
        List<String> serviceProvidersSpb = basicPageActions.getServiceProviders();
        if(serviceProvidersMsk.get(0).equals(serviceProvidersSpb.get(0))){
            logger.logError("Поставщики услуг совпадают!");
        }
        else{
            logger.logInfo("Поставщики услуг НЕ совпадают!");
        }
        basicPageActions.close();
    }
}
