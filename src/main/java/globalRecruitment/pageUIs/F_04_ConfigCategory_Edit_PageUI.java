package globalRecruitment.pageUIs;

public class F_04_ConfigCategory_Edit_PageUI {
	public static final String label_EDIT_PAGE_TITLE = "//span[@class='modal-title']";
	public static final String txt_dynamic = "//label[@class='col-xl-6 tagName' and contains(text(),'%s')]//following-sibling::div//input";

	public static final String cbo_TYPE_CATEGORY_PARENT = "//form[@id='catGroupEditForm']//div[@class='form-group row']//div[@id='categoryType_chosen']//span[text()='%s']";
	public static final String cbo_TYPE_CATEGORY_SEARCH = "//form[@id='catGroupEditForm']//div[@class='form-group row']//div[@id='categoryType_chosen']//input";

	public static final String cbo_RELEVANT_CATEGORY_PARENT_AND_SEARCH = "//input[@type='search']";
	public static final String icon_REMOVE_CATEGORY_RELEVANT = "//span[@class='select2-selection__choice__remove']";

	public static final String btn_SAVE = "//button[@class='btn btn-primary' and @type='submit']";

	public static final String label_RELEVANT_MESSAGE_ERROR = "//label[@id='checkCategoryEdit']";
	public static final String label_dynamic_REQUIRED_LABEL_MESSAGE_ERROR = "//label[@class='col-xl-6 tagName' and contains(text(),'%s')]//following-sibling::div//div[@class='formErrorContent']";
	public static final String label_dynamic_SPECIAL_CHARACTERS_LABEL_MESSAGE_ERROR = "//label[@class='col-xl-6 tagName' and contains(text(),'%s')]//following-sibling::label";
	public static final String label_UNIQUE_TOAST_MESSAGE_ERROR = "//div[@class='toast-message']";

	public static final String label_lst_TYPE_VALUE_AUTOCOMPLETE_RESULT_SEARCH = "//form[@id='catGroupEditForm']//div[@class='form-group row']//div[@id='categoryType_chosen']//ul[@class='chosen-results']//li[contains(@class,'active-result')]";

	public static final String lable_lst_RELEVANT_CATEGORY_VALUE_AUTOCOMPLETE_RESULT_SEARCH = "//ul[@id='select2-categoryEditId-results']//li[@aria-selected='false']";

}
