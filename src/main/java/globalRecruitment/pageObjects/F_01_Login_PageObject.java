package globalRecruitment.pageObjects;

import common.CommonUI;
import globalRecruitment.pageUIs.F_01_Login_PageUI;

public class F_01_Login_PageObject extends CommonUI {

	public void openLoginPage() {
		open();
	}

	public void verifyLoginPageDisplayed() {
		highlightElement(F_01_Login_PageUI.btn_LOGIN);
		element(F_01_Login_PageUI.btn_LOGIN).shouldBeVisible();
	}

	public void dynamicInnputToTextbox(String valueTextbox, String... placeHolder) {
		highlightElement(String.format(F_01_Login_PageUI.txt_dynamic, (Object[]) placeHolder));
		element(String.format(F_01_Login_PageUI.txt_dynamic, (Object[]) placeHolder)).waitUntilVisible().sendKeys(valueTextbox);
	}

	public void clickToLoginButton() {
		highlightElement(F_01_Login_PageUI.btn_LOGIN);
		element(F_01_Login_PageUI.btn_LOGIN).waitUntilVisible().click();
	}

	public void verifyMessageErrorDisplayed(String messageError) {
		highlightElement(F_01_Login_PageUI.label_MESSAGE_ERROR);
		element(F_01_Login_PageUI.label_MESSAGE_ERROR).waitUntilVisible().shouldContainText(messageError);
	}

}