package common;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.datatable.DataTable;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.webdriver.WebDriverFacade;

public class CommonUI extends PageObject {
	WebElement element;
	JavascriptExecutor javascriptExecutor;
	WebDriverWait waitExplicit;

	public WebDriver getWebDriver() {
		return (WebDriver) ((WebDriverFacade) getDriver()).getProxiedDriver();
	}

	public Object ScrollToBottomPage() {
		javascriptExecutor = ((JavascriptExecutor) getWebDriver());
		return javascriptExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public Object scollToElementJS(String locator, String... values) {
		locator = String.format(locator, (Object[]) values);
		javascriptExecutor = (JavascriptExecutor) getWebDriver();
		element = getDriver().findElement(By.xpath(locator));
		return javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void highlightElement(String locator, String... values) {
		locator = String.format(locator, (Object[]) values);
		javascriptExecutor = (JavascriptExecutor) getWebDriver();
		element = element(locator);
		String originalStyle = element.getAttribute("style");
		javascriptExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 5px solid red; border-style: dashed;");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		javascriptExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public int randomInteger() {
		Random random = new Random();
		return random.nextInt(99999);

	}

	public String getValueColumnDataTableBDD(DataTable tableName, String columnName) {
		String valueColumn = null;
		String valueColumnRe = null;
		List<Map<String, String>> asMaps = tableName.asMaps(String.class, String.class);
		for (Map<String, String> featureMap : asMaps) {
			valueColumn = featureMap.get(columnName);
			valueColumnRe = valueColumn.replaceAll("\"", "");
		}
		return valueColumnRe;
	}

	public void acceptAlert() {
		getDriver().switchTo().alert().accept();
	}

	public String getTextInAlert() {
		return getDriver().switchTo().alert().getText();
	}

	public boolean isAlertPresent() {
		try {
			getDriver().switchTo().alert();
			return true;
		} catch (NoAlertPresentException Ex) {
			return false;
		}
	}

	public int getRowResulSearchUIS(String xpathThanOneRecord, String xpathOneRecord) {
		int recordNumberInt = 0;

		ScrollToBottomPage();

		List<WebElementFacade> lstResultThanOneRecord = findAll(xpathThanOneRecord);
		List<WebElementFacade> lstResultOneRecord = findAll(xpathOneRecord);

		if (lstResultThanOneRecord.size() > 0) {
			highlightElement(xpathThanOneRecord);
			String recordResultString = element(xpathThanOneRecord).getText();

			String[] recordResultStringArray = recordResultString.split("\\s");

			String recordNumberString = recordResultStringArray[2];

			String recordNumberStringRep = recordNumberString.replaceAll(",", "");
			recordNumberInt = Integer.parseInt(recordNumberStringRep);
		}

		if (lstResultOneRecord.size() > 0) {
			highlightElement(xpathOneRecord);
			recordNumberInt = 1;
		}

		return recordNumberInt;
	}
}
