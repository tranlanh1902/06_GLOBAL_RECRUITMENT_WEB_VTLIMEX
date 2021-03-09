package globalRecruitment.pageObjects;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.CommonJDBC;
import common.CommonUI;
import globalRecruitment.pageUIs.F_02_ConfigCategory_Search_PageUI;
import net.serenitybdd.core.pages.WebElementFacade;

public class F_02_ConfigCategory_Search_PageObject extends CommonUI {
	Connection connection;
	String query;

	public void verifySeachPageOpen(String titleTextString) {
		highlightElement(F_02_ConfigCategory_Search_PageUI.label_PAGE_TITLE);
		element(F_02_ConfigCategory_Search_PageUI.label_PAGE_TITLE).shouldContainText(titleTextString);
	}

	public void clickToButtonAdd() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		highlightElement(F_02_ConfigCategory_Search_PageUI.btn_ADD);
		element(F_02_ConfigCategory_Search_PageUI.btn_ADD).waitUntilVisible().click();
	}

	public String getToastMessage() {
		return element(F_02_ConfigCategory_Search_PageUI.lable_toast_message_ADD_OR_EDIT_OR_DELETE_SUCCESS).waitUntilVisible().getText();
	}

	public void inputToNameTextbox(String nameSearch) {
		highlightElement(F_02_ConfigCategory_Search_PageUI.txt_NAME);
		element(F_02_ConfigCategory_Search_PageUI.txt_NAME).waitUntilVisible().typeAndEnter(nameSearch);
	}

	public void clickToEditIcon() {
		highlightElement(F_02_ConfigCategory_Search_PageUI.icon_EDIT);
		element(F_02_ConfigCategory_Search_PageUI.icon_EDIT).click();
	}

	public void clickToDeleteIcon() {
		highlightElement(F_02_ConfigCategory_Search_PageUI.icon_DELETE);
		element(F_02_ConfigCategory_Search_PageUI.icon_DELETE).click();
	}

	public void clickToSearchButton() {
		highlightElement(F_02_ConfigCategory_Search_PageUI.btn_SEARCH);
		element(F_02_ConfigCategory_Search_PageUI.btn_SEARCH).waitUntilVisible().click();
	}

	public void clickToConfirmDialogButton(String... nameAddedOrEdited) {
		if (isAlertPresent() == true) {
			acceptAlert();
		} else {
			clickToDeleteIcon();
			acceptAlert();
		}
	}

	public void inputToTypeCombox(String typeSeach) {
		highlightElement(F_02_ConfigCategory_Search_PageUI.cbo_TYPE_CATEGORY_PARENT);
		element(F_02_ConfigCategory_Search_PageUI.cbo_TYPE_CATEGORY_PARENT).waitUntilVisible().click();
		element(F_02_ConfigCategory_Search_PageUI.cbo_TYPE_CATEGORY_SEARCH).waitUntilVisible().typeAndEnter(typeSeach);
	}

	public void inputToCategoryCombox(String categoryRelevantSearch) {
		highlightElement(F_02_ConfigCategory_Search_PageUI.cbo_TYPE_CATEGORY_PARENT);
		element(F_02_ConfigCategory_Search_PageUI.cbo_RELEVANT_CATEGORY_PARENT).waitUntilVisible().click();
		element(F_02_ConfigCategory_Search_PageUI.cbo_RELEVANT_CATEGORY_SEARCH).waitUntilVisible().typeAndEnter(categoryRelevantSearch);
	}

	public int getRowResulSearchUI() {
		return getRowResulSearchUIS(F_02_ConfigCategory_Search_PageUI.label_SEARCH_RESULT_RECORD_THAN_ONE, F_02_ConfigCategory_Search_PageUI.label_SEARCH_RESULT_RECORD_ONE);
	}

	public int getRowResulSearchDB(String nameSearch, String typeSearch, String categoryRelevantSearch) throws Exception {
		connection = CommonJDBC.getConnectionMySqlGolobalRecruitmentDomain();
		Map<String, Object> mapNameValueParameter = new HashMap<String, Object>();

		query = "SELECT DISTINCT A.NAME AS NAME,A.NAME_EN AS NAME_ENG ,B.NAME AS TYPE FROM CAT_GROUP A, CATEGORY_TYPE B, CATEGORY C, CATEGORY_GROUP D WHERE A.GROUP_ID = D.GROUP_ID AND D.CATEGORY_ID=C.CATEGORY_ID AND C.CATEGORY_TYPE_ID = B.CATEGORY_TYPE_ID AND A.IS_ACTIVE =1 AND 1=1";
		String order = " " + "ORDER BY A.CREATED_DATE DESC";

		String name = " " + "AND A.NAME LIKE :name";
		String type = " " + "AND B.NAME=:type";
		String categoryRelevant = " " + "AND C.NAME =:categoryRelavant";

		if (nameSearch.trim().length() > 0) {
			query = query + name;
			mapNameValueParameter.put("name", "%" + nameSearch.toLowerCase().trim() + "%");
		}

		if (typeSearch.trim().length() > 0) {
			query = query + type;
			mapNameValueParameter.put("type", typeSearch.trim());
		}

		if (categoryRelevantSearch.trim().length() > 0) {
			query = query + categoryRelevant;
			mapNameValueParameter.put("categoryRelavant", categoryRelevantSearch.trim());
		}

		int rowCountDB = CommonJDBC.getRowCountDB(connection, query + order, mapNameValueParameter);

		return rowCountDB;
	}

	public List<String> getListDataColumnNameDB(String nameSearch, String typeSearch, String categoryRelevantSearch) throws Exception {
		connection = CommonJDBC.getConnectionMySqlGolobalRecruitmentDomain();
		Map<String, Object> mapNameValueParameter = new HashMap<String, Object>();

		query = "SELECT DISTINCT A.NAME AS NAME,A.NAME_EN AS NAME_ENG ,B.NAME AS TYPE FROM CAT_GROUP A, CATEGORY_TYPE B, CATEGORY C, CATEGORY_GROUP D WHERE A.GROUP_ID = D.GROUP_ID AND D.CATEGORY_ID=C.CATEGORY_ID AND C.CATEGORY_TYPE_ID = B.CATEGORY_TYPE_ID AND A.IS_ACTIVE =1 AND 1=1";
		String order = " " + "ORDER BY A.CREATED_DATE DESC";
		String limit = " " + "LIMIT 10";

		String name = " " + "AND A.NAME LIKE :name";
		String type = " " + "AND B.NAME=:type";
		String categoryRelevant = " " + "AND C.NAME =:categoryRelavant";

		if (nameSearch.trim().length() > 0) {
			query = query + name;
			mapNameValueParameter.put("name", "%" + nameSearch.toLowerCase().trim() + "%");
		}

		if (typeSearch.trim().length() > 0) {
			query = query + type;
			mapNameValueParameter.put("type", typeSearch.trim());
		}

		if (categoryRelevantSearch.trim().length() > 0) {
			query = query + categoryRelevant;
			mapNameValueParameter.put("categoryRelavant", categoryRelevantSearch.trim());
		}

		List<String> lstValueColumNameDB = CommonJDBC.listColumnValueDB(connection, query + order + limit, "NAME", mapNameValueParameter);
		return lstValueColumNameDB;
	}

	public List<String> getListDataColumnNameUI() {
		List<String> lstValueName = new ArrayList<String>();

		List<WebElementFacade> lstValueColumnNameUI = findAll(F_02_ConfigCategory_Search_PageUI.label_lst_NAME_VALUE_COLUMN);

		for (WebElementFacade b : lstValueColumnNameUI) {
			lstValueName.add(b.getText());
		}

		return lstValueName;
	}

	public void inputToTypeAutoComplete(String autocomplete) throws InterruptedException {
		highlightElement(F_02_ConfigCategory_Search_PageUI.cbo_TYPE_CATEGORY_PARENT);
		element(F_02_ConfigCategory_Search_PageUI.cbo_TYPE_CATEGORY_PARENT).waitUntilVisible().click();
		element(F_02_ConfigCategory_Search_PageUI.cbo_TYPE_CATEGORY_SEARCH).waitUntilVisible().sendKeys(autocomplete);
		Thread.sleep(5000);
	}

	public void inputToCategoryRelevantAutoComplete(String autocomplete) throws InterruptedException {
		highlightElement(F_02_ConfigCategory_Search_PageUI.cbo_RELEVANT_CATEGORY_PARENT);
		element(F_02_ConfigCategory_Search_PageUI.cbo_RELEVANT_CATEGORY_PARENT).waitUntilVisible().click();
		element(F_02_ConfigCategory_Search_PageUI.cbo_RELEVANT_CATEGORY_SEARCH).waitUntilVisible().sendKeys(autocomplete);
		Thread.sleep(5000);
	}

	public List<String> getListDataCategoryTypeAutoCompleteUI() {
		String defaultValue = "---------Tất cả---------";
		List<String> lstValueAutoCompleteUI = new ArrayList<String>();
		List<WebElementFacade> lstValueAutoCompleteVsDefaultUIesultUI = findAll(F_02_ConfigCategory_Search_PageUI.label_lst_TYPE_VALUE_AUTOCOMPLETE_RESULT_SEARCH);

		if (lstValueAutoCompleteVsDefaultUIesultUI.size() > 0) {
			for (WebElementFacade a : lstValueAutoCompleteVsDefaultUIesultUI) {
				if (!a.getTextValue().equals(defaultValue)) {
					lstValueAutoCompleteUI.add(a.getTextValue());
				}
			}
		}
		return lstValueAutoCompleteUI;
	}

	public List<String> getListDataCategoryRelevantAutoCompleteUI() {
		String defaultValue = "---------Tất cả---------";
		List<String> lstValueAutoCompleteUI = new ArrayList<String>();
		List<WebElementFacade> lstValueAutoCompleteVsDefaultUI = findAll(F_02_ConfigCategory_Search_PageUI.label_lst_RELEVANT_CATEGORY_VALUE_AUTOCOMPLETE_RESULT_SEARCH);

		if (lstValueAutoCompleteVsDefaultUI.size() > 0) {
			for (WebElementFacade a : lstValueAutoCompleteVsDefaultUI) {
				if (!a.getTextValue().equals(defaultValue)) {
					lstValueAutoCompleteUI.add(a.getTextValue());
				}
			}

		}
		return lstValueAutoCompleteUI;
	}

	public List<String> getListDataTypeAutoCompleteDB(String autocompleteSearch) throws Exception {
		connection = CommonJDBC.getConnectionMySqlGolobalRecruitmentDomain();
		Map<String, Object> mapNameValueParameter = new HashMap<String, Object>();

		query = "SELECT B.NAME name FROM CATEGORY_TYPE B WHERE 1=1";
		String order = " " + "ORDER BY B.NAME";

		String name = " " + "AND B.NAME LIKE :name";

		if (autocompleteSearch.trim().length() > 0) {
			query = query + name;
			mapNameValueParameter.put("name", "%" + autocompleteSearch.toLowerCase().trim() + "%");
		}

		List<String> lstValueColumNameDB = CommonJDBC.listColumnValueDB(connection, query + order, "name", mapNameValueParameter);
		return lstValueColumNameDB;
	}

	public List<String> getListDataCategoryRelevanteAutoCompleteDB(String typeSearch, String autocompleteSearch) throws Exception {
		connection = CommonJDBC.getConnectionMySqlGolobalRecruitmentDomain();
		Map<String, Object> mapNameValueParameter = new HashMap<String, Object>();

		// query = "SELECT B.NAME as categoryRe FROM CATEGORY B, CATEGORY_TYPE A WHERE 1=1 AND A.CATEGORY_TYPE_ID = B.CATEGORY_TYPE_ID AND B.NAME NOT IN (SELECT B.NAME FROM CATEGORY_TYPE A,CATEGORY B, CATEGORY_GROUP C WHERE B.CATEGORY_ID = C.CATEGORY_ID AND A.CATEGORY_TYPE_ID = B.CATEGORY_TYPE_ID AND C.IS_ACTIVE=1)";
		query = "SELECT B.NAME as categoryRe FROM CATEGORY B, CATEGORY_TYPE A WHERE 1=1 AND A.CATEGORY_TYPE_ID = B.CATEGORY_TYPE_ID ";
		String order = " " + "ORDER BY B.NAME";

		String type = " " + "AND A.NAME = :type";
		String autocomplete = " " + "AND B.NAME LIKE :categoryRelevant";

		if (type.trim().length() > 0) {
			query = query + type;
			mapNameValueParameter.put("type", typeSearch.toLowerCase().trim());
		}

		if (autocompleteSearch.trim().length() > 0) {
			query = query + autocomplete;
			mapNameValueParameter.put("categoryRelevant", "%" + autocompleteSearch.toLowerCase().trim() + "%");
		}

		List<String> lstValueColumNameDB = CommonJDBC.listColumnValueDB(connection, query + order, "categoryRe", mapNameValueParameter);

		return lstValueColumNameDB;
	}
}
