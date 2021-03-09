package globalRecruitment.pageObjects;

import common.CommonUI;
import globalRecruitment.pageUIs.F_01_Login_PageUI;
import globalRecruitment.pageUIs.Home_PageUI;

public class Home_PageObject extends CommonUI {

	public void verifyLoginSucessfuly(String acc) {
		highlightElement(F_01_Login_PageUI.label_ACC);
		element(F_01_Login_PageUI.label_ACC).waitUntilVisible().shouldContainText(acc);
	}

	public void dynamicClickToLeftMenu(String... leftMenuHyperlink) {
		highlightElement(Home_PageUI.lable_dynamic_LEFT_MENU, leftMenuHyperlink);
		element(String.format(Home_PageUI.lable_dynamic_LEFT_MENU, (Object[]) leftMenuHyperlink)).waitUntilVisible().click();
	}

}