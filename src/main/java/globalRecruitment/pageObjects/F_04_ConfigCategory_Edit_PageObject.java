package globalRecruitment.pageObjects;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.CommonUI;
import common.CommonJDBC;
import globalRecruitment.pageUIs.F_03_ConfigCategory_Add_PageUI;
import globalRecruitment.pageUIs.F_04_ConfigCategory_Edit_PageUI;
import net.serenitybdd.core.pages.WebElementFacade;

public class F_04_ConfigCategory_Edit_PageObject extends CommonUI {
	static Connection connection;
	String query;

	public void verifyConfigCategoryEditOpen(String titleText) {
		highlightElement(F_04_ConfigCategory_Edit_PageUI.label_EDIT_PAGE_TITLE);
		element(F_04_ConfigCategory_Edit_PageUI.label_EDIT_PAGE_TITLE).shouldContainText(titleText);
	}

	public void dynamicInputToTextbox(String valueInput, String... lableTextbox) {
		highlightElement(String.format(F_04_ConfigCategory_Edit_PageUI.txt_dynamic, (Object[]) lableTextbox));
		element(String.format(F_04_ConfigCategory_Edit_PageUI.txt_dynamic, (Object[]) lableTextbox)).waitUntilVisible().clear();
		element(String.format(F_04_ConfigCategory_Edit_PageUI.txt_dynamic, (Object[]) lableTextbox)).waitUntilVisible().sendKeys(valueInput);
	}

	public void dynamicInputToTypeCombox(String valueSelect, String... typeAdded) {
		highlightElement(String.format(F_04_ConfigCategory_Edit_PageUI.cbo_TYPE_CATEGORY_PARENT, (Object[]) typeAdded));
		element(String.format(F_04_ConfigCategory_Edit_PageUI.cbo_TYPE_CATEGORY_PARENT, (Object[]) typeAdded)).waitUntilVisible().click();
		element(F_04_ConfigCategory_Edit_PageUI.cbo_TYPE_CATEGORY_SEARCH).waitUntilVisible().typeAndEnter(valueSelect);
	}

	public void dynamicInputToCategoryCombox1(String valueSelect) {
		List<WebElementFacade> lstRemoveIcon = findAll(F_04_ConfigCategory_Edit_PageUI.icon_REMOVE_CATEGORY_RELEVANT);
		int size = lstRemoveIcon.size();

		if (size > 0) {
			int temp = 0;
			while (temp < size) {
				element(F_04_ConfigCategory_Edit_PageUI.icon_REMOVE_CATEGORY_RELEVANT).click();
				temp++;
			}

			highlightElement(F_04_ConfigCategory_Edit_PageUI.cbo_RELEVANT_CATEGORY_PARENT_AND_SEARCH);
			element(F_04_ConfigCategory_Edit_PageUI.cbo_RELEVANT_CATEGORY_PARENT_AND_SEARCH).waitUntilVisible().click();
			element(F_04_ConfigCategory_Edit_PageUI.cbo_RELEVANT_CATEGORY_PARENT_AND_SEARCH).waitUntilVisible().typeAndEnter(valueSelect);
		} else {
			highlightElement(F_04_ConfigCategory_Edit_PageUI.cbo_RELEVANT_CATEGORY_PARENT_AND_SEARCH);
			element(F_04_ConfigCategory_Edit_PageUI.cbo_RELEVANT_CATEGORY_PARENT_AND_SEARCH).waitUntilVisible().click();
			element(F_04_ConfigCategory_Edit_PageUI.cbo_RELEVANT_CATEGORY_PARENT_AND_SEARCH).waitUntilVisible().typeAndEnter(valueSelect);
		}
	}

	public void dynamicInputToCategoryCombox2(String valueSelect) {
		highlightElement(F_04_ConfigCategory_Edit_PageUI.cbo_RELEVANT_CATEGORY_PARENT_AND_SEARCH);
		element(F_04_ConfigCategory_Edit_PageUI.cbo_RELEVANT_CATEGORY_PARENT_AND_SEARCH).waitUntilVisible().click();
		element(F_04_ConfigCategory_Edit_PageUI.cbo_RELEVANT_CATEGORY_PARENT_AND_SEARCH).waitUntilVisible().typeAndEnter(valueSelect);
	}

	public void clickToSaveButton() {
		highlightElement(F_03_ConfigCategory_Add_PageUI.btn_SAVE);
		element(F_04_ConfigCategory_Edit_PageUI.btn_SAVE).click();
	}

	public void clickToConfirmDialogButton() {

		if (isAlertPresent() == true) {
			acceptAlert();
		} else {
			clickToSaveButton();
			acceptAlert();
		}
	}

	public int getRowUpdateCatGroupTable(String nameAddedOrEdited, String nameEngAddedOrEdited, String typeAddedOrEdited, String categoryRelevan1ddedOrEdited, String categoryRelevan2ddedOrEdited) throws Exception {
		connection = CommonJDBC.getConnectionMySqlGolobalRecruitmentDomain();
		Map<String, Object> mapNameValueParameter = new HashMap<String, Object>();

		String name = " " + "AND A.NAME =:name";
		String nameEng = " " + "AND A.NAME_EN=:nameEng";
		String type = " " + "AND B.NAME=:type";
		String categoryRelevant1 = " " + "AND (C.NAME =:categoryRelevant1";
		String categoryRelevant2 = " " + "OR C.NAME =:categoryRelevant2)";

		query = "SELECT DISTINCT A.* FROM CAT_GROUP A, CATEGORY_TYPE B, CATEGORY C, CATEGORY_GROUP D WHERE A.GROUP_ID = D.GROUP_ID AND D.CATEGORY_ID=C.CATEGORY_ID AND C.CATEGORY_TYPE_ID = B.CATEGORY_TYPE_ID AND A.IS_ACTIVE =1 AND 1=1" + name + nameEng + type + categoryRelevant1 + categoryRelevant2;

		mapNameValueParameter.put("name", nameAddedOrEdited.trim());
		mapNameValueParameter.put("nameEng", nameEngAddedOrEdited.trim());
		mapNameValueParameter.put("type", typeAddedOrEdited.trim());
		mapNameValueParameter.put("categoryRelevant1", categoryRelevan1ddedOrEdited.trim());
		mapNameValueParameter.put("categoryRelevant2", categoryRelevan2ddedOrEdited.trim());

		return CommonJDBC.getRowCountDB(connection, query, mapNameValueParameter);
	}

	public int getRowUpdateCategoryGroupTable(String nameAddedOrEdited, String nameEngAddedOrEdited, String typeAddedOrEdited, String categoryRelevan1ddedOrEdited, String categoryRelevan2ddedOrEdited) throws Exception {
		connection = CommonJDBC.getConnectionMySqlGolobalRecruitmentDomain();
		Map<String, Object> mapNameValueParameter = new HashMap<String, Object>();

		String name = " " + "AND A.NAME =:name";
		String nameEng = " " + "AND A.NAME_EN=:nameEng";
		String type = " " + "AND B.NAME=:type";
		String categoryRelevant1 = " " + "AND (C.NAME =:categoryRelevant1";
		String categoryRelevant2 = " " + "OR C.NAME =:categoryRelevant2)";

		query = "SELECT DISTINCT C.* FROM CAT_GROUP A, CATEGORY_TYPE B, CATEGORY C, CATEGORY_GROUP D WHERE A.GROUP_ID = D.GROUP_ID AND D.CATEGORY_ID=C.CATEGORY_ID AND C.CATEGORY_TYPE_ID = B.CATEGORY_TYPE_ID AND A.IS_ACTIVE =1 AND 1=1" + name + nameEng + type + categoryRelevant1 + categoryRelevant2;

		mapNameValueParameter.put("name", nameAddedOrEdited.trim());
		mapNameValueParameter.put("nameEng", nameEngAddedOrEdited.trim());
		mapNameValueParameter.put("type", typeAddedOrEdited.trim());
		mapNameValueParameter.put("categoryRelevant1", categoryRelevan1ddedOrEdited.trim());
		mapNameValueParameter.put("categoryRelevant2", categoryRelevan2ddedOrEdited.trim());

		return CommonJDBC.getRowCountDB(connection, query, mapNameValueParameter);
	}

	public String getCodeColumnValueInCatGroupTable(String nameAddedOrEdited, String nameEngAddedOrEdited, String typeAddedOrEdited, String categoryRelevan1ddedOrEdited, String categoryRelevan2ddedOrEdited) throws Exception {
		connection = CommonJDBC.getConnectionMySqlGolobalRecruitmentDomain();
		Map<String, Object> mapNameValueParameter = new HashMap<String, Object>();

		String name = " " + "AND A.NAME =:name";
		String nameEng = " " + "AND A.NAME_EN=:nameEng";
		String type = " " + "AND B.NAME=:type";
		String categoryRelevant1 = " " + "AND (C.NAME =:categoryRelevant1";
		String categoryRelevant2 = " " + "OR C.NAME =:categoryRelevant2)";

		query = "SELECT DISTINCT A.* FROM CAT_GROUP A, CATEGORY_TYPE B, CATEGORY C, CATEGORY_GROUP D WHERE A.GROUP_ID = D.GROUP_ID AND D.CATEGORY_ID=C.CATEGORY_ID AND C.CATEGORY_TYPE_ID = B.CATEGORY_TYPE_ID AND A.IS_ACTIVE =1 AND 1=1" + name + nameEng + type + categoryRelevant1 + categoryRelevant2;

		mapNameValueParameter.put("name", nameAddedOrEdited.trim());
		mapNameValueParameter.put("nameEng", nameEngAddedOrEdited.trim());
		mapNameValueParameter.put("type", typeAddedOrEdited.trim());
		mapNameValueParameter.put("categoryRelevant1", categoryRelevan1ddedOrEdited.trim());
		mapNameValueParameter.put("categoryRelevant2", categoryRelevan2ddedOrEdited.trim());

		String valueColumn = CommonJDBC.getColumnValueDB(connection, query, "code", mapNameValueParameter);
		System.out.println(valueColumn);
		return valueColumn;

	}

	public void verifyMessageErrorCategoryRelevant(String messageError) {
		highlightElement(F_04_ConfigCategory_Edit_PageUI.label_RELEVANT_MESSAGE_ERROR);
		element(F_04_ConfigCategory_Edit_PageUI.label_RELEVANT_MESSAGE_ERROR).waitUntilVisible().shouldContainText(messageError);
	}

	public void dynamicVerifyMessageErrorUnique(String messageError) {
		element(F_04_ConfigCategory_Edit_PageUI.label_UNIQUE_TOAST_MESSAGE_ERROR).shouldContainText(messageError);
	}

	public void dynamicVerifyMessageErrorRequired(String messageError, String... field) {
		highlightElement(String.format(F_04_ConfigCategory_Edit_PageUI.label_dynamic_REQUIRED_LABEL_MESSAGE_ERROR, (Object[]) field));
		element(String.format(F_04_ConfigCategory_Edit_PageUI.label_dynamic_REQUIRED_LABEL_MESSAGE_ERROR, (Object[]) field)).waitUntilVisible().shouldContainText(messageError);
	}

	public void dynamicVerifyMessageErrorSpecial(String messageError, String... field) {
		highlightElement(String.format(F_04_ConfigCategory_Edit_PageUI.label_dynamic_SPECIAL_CHARACTERS_LABEL_MESSAGE_ERROR, (Object[]) field));
		element(String.format(F_04_ConfigCategory_Edit_PageUI.label_dynamic_SPECIAL_CHARACTERS_LABEL_MESSAGE_ERROR, (Object[]) field)).waitUntilVisible().shouldContainText(messageError);
	}

	public void inputToTypeAutoComplete(String autocomplete, String... typeAddOrEdit) throws InterruptedException {
		highlightElement(String.format(F_04_ConfigCategory_Edit_PageUI.cbo_TYPE_CATEGORY_PARENT, (Object[]) typeAddOrEdit));
		element(String.format(F_04_ConfigCategory_Edit_PageUI.cbo_TYPE_CATEGORY_PARENT, (Object[]) typeAddOrEdit)).waitUntilVisible().click();
		element(String.format(F_04_ConfigCategory_Edit_PageUI.cbo_TYPE_CATEGORY_SEARCH, (Object[]) typeAddOrEdit)).waitUntilVisible().sendKeys(autocomplete);

		Thread.sleep(5000);
	}

	public void inputToCategoryRelevantAutoComplete(String autocomplete) throws InterruptedException {
		List<WebElementFacade> lstRemoveIcon = findAll(F_04_ConfigCategory_Edit_PageUI.icon_REMOVE_CATEGORY_RELEVANT);
		int size = lstRemoveIcon.size();

		if (size > 0) {
			int temp = 0;
			while (temp < size) {
				element(F_04_ConfigCategory_Edit_PageUI.icon_REMOVE_CATEGORY_RELEVANT).click();
				temp++;
			}

			highlightElement(F_04_ConfigCategory_Edit_PageUI.cbo_RELEVANT_CATEGORY_PARENT_AND_SEARCH);
			element(F_04_ConfigCategory_Edit_PageUI.cbo_RELEVANT_CATEGORY_PARENT_AND_SEARCH).waitUntilVisible().click();
			element(F_04_ConfigCategory_Edit_PageUI.cbo_RELEVANT_CATEGORY_PARENT_AND_SEARCH).waitUntilVisible().sendKeys(autocomplete);
		} else {
			highlightElement(F_04_ConfigCategory_Edit_PageUI.cbo_RELEVANT_CATEGORY_PARENT_AND_SEARCH);
			element(F_04_ConfigCategory_Edit_PageUI.cbo_RELEVANT_CATEGORY_PARENT_AND_SEARCH).waitUntilVisible().click();
			element(F_04_ConfigCategory_Edit_PageUI.cbo_RELEVANT_CATEGORY_PARENT_AND_SEARCH).waitUntilVisible().sendKeys(autocomplete);
		}

		Thread.sleep(5000);
	}

	public List<String> getListDataCategoryTypeAutoCompleteUI() {
		List<String> lstValueAutoCompleteUI = new ArrayList<String>();
		List<WebElementFacade> lstTypeAutoCompleteResultUI = findAll(F_04_ConfigCategory_Edit_PageUI.label_lst_TYPE_VALUE_AUTOCOMPLETE_RESULT_SEARCH);

		if (lstTypeAutoCompleteResultUI.size() > 0) {
			highlightElement(F_04_ConfigCategory_Edit_PageUI.label_lst_TYPE_VALUE_AUTOCOMPLETE_RESULT_SEARCH);

			for (WebElementFacade a : lstTypeAutoCompleteResultUI) {
				lstValueAutoCompleteUI.add(a.getTextValue());
			}

		}

		return lstValueAutoCompleteUI;
	}

	public List<String> getListDataCategoryRelevantAutoCompleteUI() {
		List<String> lstValueAutoCompleteUI = new ArrayList<String>();

		List<WebElementFacade> lstTypeAutoCompleteResult = findAll(F_04_ConfigCategory_Edit_PageUI.lable_lst_RELEVANT_CATEGORY_VALUE_AUTOCOMPLETE_RESULT_SEARCH);

		if (lstTypeAutoCompleteResult.size() > 0) {

			for (WebElementFacade a : lstTypeAutoCompleteResult) {
				lstValueAutoCompleteUI.add(a.getTextValue());
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

		query = "SELECT B.NAME as categoryRe FROM CATEGORY B, CATEGORY_TYPE A WHERE 1=1 AND A.CATEGORY_TYPE_ID = B.CATEGORY_TYPE_ID AND B.NAME NOT IN (SELECT B.NAME FROM CATEGORY_TYPE A,CATEGORY B, CATEGORY_GROUP C WHERE B.CATEGORY_ID = C.CATEGORY_ID AND A.CATEGORY_TYPE_ID = B.CATEGORY_TYPE_ID AND C.IS_ACTIVE=1)";
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