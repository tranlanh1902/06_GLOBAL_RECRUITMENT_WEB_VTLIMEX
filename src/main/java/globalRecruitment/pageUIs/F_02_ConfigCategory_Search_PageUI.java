package globalRecruitment.pageUIs;

public class F_02_ConfigCategory_Search_PageUI {
	public static final String label_PAGE_TITLE = "//div[@id='searchResult']//header//strong//span";
	public static final String btn_ADD = "//button[@class='btn btn-primary btn-sm ladda-button' and @type='button']";
	public static final String btn_SEARCH = "//button[@class='btn btn-primary btn-sm ladda-button' and @type='submit']";
	public static final String lable_toast_message_ADD_OR_EDIT_OR_DELETE_SUCCESS = "//div[@class='toast-message']";
	public static final String icon_DELETE = "//table[@id='groupTable']//preceding-sibling::td//a[@class='btn-table delete']";
	public static final String icon_EDIT = "//table[@id='groupTable']//preceding-sibling::td//a[@class='btn-table edit']";

	public static final String txt_NAME = "//input[@id='groupName']";

	public static final String cbo_TYPE_CATEGORY_PARENT = "//form[@id='groupFormSearch']//div[@class='form-group row']//div[@id='categoryType_chosen']//a";
	public static final String cbo_TYPE_CATEGORY_SEARCH = "//form[@id='groupFormSearch']//div[@class='form-group row']//div[@id='categoryType_chosen']//input";

	public static final String cbo_RELEVANT_CATEGORY_PARENT = "//span[@id='select2-category-container']";
	public static final String cbo_RELEVANT_CATEGORY_SEARCH = "//span[@class='select2-search select2-search--dropdown']//input";

	public static final String label_SEARCH_RESULT_RECORD_THAN_ONE = "//p[@id='pagebannerId']";
	public static final String label_SEARCH_RESULT_RECORD_ONE = "//div[@class='callout callout-info pagepanner']//p[contains(text(),'một')]";

	public static final String label_lst_NAME_VALUE_COLUMN = "//tbody//tr//td[4]";

	// trừ đi khi có valueText = '---------Tất cả---------'
	public static final String label_lst_TYPE_VALUE_AUTOCOMPLETE_RESULT_SEARCH = "//ul[@class='chosen-results']//li[contains(@class,'active-result')]";
	public static final String label_lst_TYPE_VALUE_AUTOCOMPLETE_NO_RESULT_SEARCH = "//ul[@class='chosen-results']//li[@class='no-results']";

	public static final String label_lst_RELEVANT_CATEGORY_VALUE_AUTOCOMPLETE_RESULT_SEARCH = "//ul[@id='select2-category-results']//li[contains(@class,'select2-results__option') and (@aria-selected='true' or @aria-selected ='false')]";
	public static final String label_lst_RELEVANT_CATEGORY_VALUE_AUTOCOMPLETE_NO_RESULT_SEARCH = "//ul[@id='select2-category-results']//li[contains(@class,'message')]";
}
