package globalRecruitment.StepDefinitions;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import globalRecruitment.pageObjects.F_03_ConfigCategory_Add_PageObject;
import io.cucumber.datatable.DataTable;
import net.thucydides.core.annotations.Steps;

@SuppressWarnings({ "deprecation" })
public class F_03_ConfigCategory_Add_PageDefinitions {
	Log log = LogFactory.getLog(getClass());
	String _nameLabel = "Tên cấu hình", _nameEngLabel = "Tên cấu hình (Tiếng Anh)", _typeLable = "Loại danh mục", _categoryRelevantLabel = "Danh mục liên quan";
	final String _uniqueTypeError = "unique", _requiredTypeError = "required", _specialTypeError = "special";
	@Steps
	F_03_ConfigCategory_Add_PageObject ConfigCategoryAddPage;

	/* ============ Chung ============= */
	@Given("^QLCauHinhDanhMuc-Them: kiểm tra mở trang Thêm cấu hình thành công với title \"([^\"]*)\"$")
	public void qlcauhinhdanhmucthem_kim_tra_m_trang_thm_cu_hnh_thnh_cng_something(String titleText) {
		log.info("=========== QLCauHinhDanhMuc-Them: kiểm tra mở trang Thêm cấu hình thành công với title " + titleText + " ===========");
		ConfigCategoryAddPage.verifyConfigCategoryAddOpen(titleText);
	}

	/* ============ 01-Kiểm tra Thêm cấu hình thành công ============= */
	@When("^QLCauHinhDanhMuc-Them: nhập dữ liệu màn hình Thêm cấu hình với các dữ liệu sau$")
	public void qlcauhinhdanhmucthem_nhp_d_liu_mn_hnh_thm_cu_hnh_vi_cc_d_liu_sau(DataTable table) throws InterruptedException {
		String name = ConfigCategoryAddPage.getValueColumnDataTableBDD(table, "Name");
		String nameEnglish = ConfigCategoryAddPage.getValueColumnDataTableBDD(table, "NameEnglish");
		String type = ConfigCategoryAddPage.getValueColumnDataTableBDD(table, "Type");
		String categoryRelevant1 = ConfigCategoryAddPage.getValueColumnDataTableBDD(table, "CategoryRelevant1");
		String categoryRelevant2 = ConfigCategoryAddPage.getValueColumnDataTableBDD(table, "CategoryRelevant2");

		log.info("=========== QLCauHinhDanhMuc-Them: nhập trường Tên cấu hình with data " + name + " ===========");
		ConfigCategoryAddPage.dynamicInputToTextbox(name, _nameLabel);

		log.info("=========== QLCauHinhDanhMuc-Them: nhập trường Tên cấu hình (Tiếng Anh) với dữ liệu " + nameEnglish + " ===========");
		ConfigCategoryAddPage.dynamicInputToTextbox(nameEnglish, _nameEngLabel);

		log.info("=========== QLCauHinhDanhMuc-Them: nhập trường Loại danh mục với dữ liệu " + type + "===========");
		ConfigCategoryAddPage.inputToTypeCombox(type);

		log.info("=========== QLCauHinhDanhMuc-Them: nhập trường Danh mục liên quan với dữ liệu " + categoryRelevant1 + " ===========");
		ConfigCategoryAddPage.inputToCategoryCombox(categoryRelevant1);

		log.info("=========== QLCauHinhDanhMuc-Them: nhập trường Danh mục liên quan với dữ liệu " + categoryRelevant2 + " ===========");
		ConfigCategoryAddPage.inputToCategoryCombox(categoryRelevant2);

		log.info("=========== QLCauHinhDanhMuc-Them: Lưu thông tin các trường trên màn hình Thêm cấu hình danh mục đã nhập ===========");
		ShareContextData.ConfigCategory.NAME_Add_Or_Edit = name;
		ShareContextData.ConfigCategory.NAME_ENG_Add_Or_Edit = nameEnglish;
		ShareContextData.ConfigCategory.TYPE_Add_Or_Edit = type;
		ShareContextData.ConfigCategory.CATEGORY_RELEVANT_1_Add_Or_Edit = categoryRelevant1;
		ShareContextData.ConfigCategory.CATEGORY_RELEVANT_2_Add_Or_Edit = categoryRelevant2;
	}

	@When("^QLCauHinhDanhMuc-Them: nhấn button Lưu$")
	public void qlcauhinhdanhmucthem_nhn_button_lu() {
		log.info("=========== QLCauHinhDanhMuc-Them: nhấn button Lưu ===========");
		ConfigCategoryAddPage.clickToSaveButton();
	}

	@And("^QLCauHinhDanhMuc-Them: nhấn button OK xác nhận lưu$")
	public void qlcauhinhdanhmucthem_nhn_button_ok_xc_nhn_lu() {
		log.info("=========== QLCauHinhDanhMuc-Them: nhấn button OK xác nhận lưu ===========");
		ConfigCategoryAddPage.clickToConfirmDialogButton();
	}

	@And("^QLCauHinhDanhMuc-Them: kiểm tra insert thành công DB bảng CAT_GROUP (.*) row với dữ liệu đã thêm$")
	public void qlcauhinhdanhmucthem_kim_tra_insert_thnh_cng_db_bng_catgroup_1_row_vi_d_liu_thm(int rowEx) throws Exception {
		log.info("=========== QLCauHinhDanhMuc-Them: kiểm tra insert thành công DB bảng CAT_GROUP " + rowEx + " row với dữ liệu đã thêm ===========");
		int rowDB = ConfigCategoryAddPage.getRowInsertCatGroupTable(ShareContextData.ConfigCategory.NAME_Added_Or_Edited, ShareContextData.ConfigCategory.NAME_ENG_Added_Or_Edited, ShareContextData.ConfigCategory.TYPE_Added_Or_Edited, ShareContextData.ConfigCategory.CATEGORY_RELEVANT_1_Added_Or_Edited, ShareContextData.ConfigCategory.CATEGORY_RELEVANT_2_Added_Or_Edited);
		Assert.assertTrue(rowDB == rowEx);
	}

	@And("^QLCauHinhDanhMuc-Them: kiểm tra insert thành công DB bảng CATEGORY_GROUP (.*) row với dữ liệu đã thêm$")
	public void qlcauhinhdanhmucthem_kim_tra_insert_thnh_cng_db_bng_categorygroup_2_row_vi_d_liu_thm(int rowEx) throws Exception {
		log.info("=========== QLCauHinhDanhMuc-Them: kiểm tra insert thành công DB bảng CATEGORY_GROUP " + rowEx + " row với dữ liệu đã thêm ===========");
		int rowDB = ConfigCategoryAddPage.getRowInsertCategoryGroupTable(ShareContextData.ConfigCategory.NAME_Added_Or_Edited, ShareContextData.ConfigCategory.NAME_ENG_Added_Or_Edited, ShareContextData.ConfigCategory.TYPE_Added_Or_Edited, ShareContextData.ConfigCategory.CATEGORY_RELEVANT_1_Added_Or_Edited, ShareContextData.ConfigCategory.CATEGORY_RELEVANT_2_Added_Or_Edited);
		Assert.assertTrue(rowDB == rowEx);
	}

	@When("^QLCauHinhDanhMuc-Them: lấy dữ liệu column code của Cấu hình danh mục vừa thêm trong bảng CAT_GROUP$")
	public void qlcauhinhdanhmucthem_ly_d_liu_column_code_ca_cu_hnh_danh_mc_va_thm_trong_bng_catgroup() throws Throwable {
		log.info("=========== QLCauHinhDanhMuc-Them: lấy dữ liệu column code của Cấu hình danh mục vừa thêm trong bảng CAT_GROU P===========");
		ShareContextData.ConfigCategory.CODE_Added_Or_Edited = ConfigCategoryAddPage.getCodeColumnValueInCatGroupTable(ShareContextData.ConfigCategory.NAME_Added_Or_Edited, ShareContextData.ConfigCategory.NAME_ENG_Added_Or_Edited, ShareContextData.ConfigCategory.TYPE_Added_Or_Edited, ShareContextData.ConfigCategory.CATEGORY_RELEVANT_1_Added_Or_Edited,
				ShareContextData.ConfigCategory.CATEGORY_RELEVANT_2_Added_Or_Edited);
	}

	/* ============ 02-Validate các trường ============= */
	@Given("^QLCauHinhDanhMuc-Them: nhập dữ liệu màn hình Thêm cấu hình với các dữ liệu lỗi sau$")
	public void qlcauhinhdanhmucthem_nhp_d_liu_mn_hnh_thm_cu_hnh_vi_cc_d_liu_li_sau(DataTable table) throws InterruptedException {
		String field = ConfigCategoryAddPage.getValueColumnDataTableBDD(table, "Field");
		String case1 = ConfigCategoryAddPage.getValueColumnDataTableBDD(table, "Case");
		String name = ConfigCategoryAddPage.getValueColumnDataTableBDD(table, "Name");
		String nameEnglish = ConfigCategoryAddPage.getValueColumnDataTableBDD(table, "NameEnglish");
		String type = ConfigCategoryAddPage.getValueColumnDataTableBDD(table, "Type");
		String categoryRelevant1 = ConfigCategoryAddPage.getValueColumnDataTableBDD(table, "CategoryRelevant1");
		String categoryRelevant2 = ConfigCategoryAddPage.getValueColumnDataTableBDD(table, "CategoryRelevant2");

		log.info("=========== QLCauHinhDanhMuc-Them: nhập dữ liệu màn hình Thêm cấu hình với các dữ liệu lỗi " + field + "_" + case1 + " ===========");
		if (name.trim().length() > 0) {
			log.info("=========== QLCauHinhDanhMuc-Them: nhập dữ liệu trường Tên cấu hình với dữ liệu " + name + " ===========");
			ConfigCategoryAddPage.dynamicInputToTextbox(name, _nameLabel);
		}

		if (nameEnglish.trim().length() > 0) {
			log.info("=========== QLCauHinhDanhMuc-Them: nhập dữ liệu trường Tên cấu hình (Tiếng Anh) với dữ liệu " + nameEnglish + " ===========");
			ConfigCategoryAddPage.dynamicInputToTextbox(nameEnglish, _nameEngLabel);
		}

		if (type.trim().length() > 0) {
			log.info("=========== QLCauHinhDanhMuc-Them: nhập dữ liệu trường Loại danh mục với dữ liệu " + type + "===========");
			ConfigCategoryAddPage.inputToTypeCombox(type);
		}

		if (categoryRelevant1.trim().length() > 0) {
			log.info("=========== QLCauHinhDanhMuc-Them: nhập dữ liệu trường Danh mục liên quan với dữ liệu " + categoryRelevant1 + " ===========");
			ConfigCategoryAddPage.inputToCategoryCombox(categoryRelevant1);
		}

		if (categoryRelevant2.trim().length() > 0) {
			log.info("=========== QLCauHinhDanhMuc-Them: nhập dữ liệu trường Danh mục liên quan với dữ liệu " + categoryRelevant2 + " ===========");
			ConfigCategoryAddPage.inputToCategoryCombox(categoryRelevant2);
		}
	}

	@When("^QLCauHinhDanhMuc-Them: nhấn button Lưu nếu typeError là special thì bỏ qua$")
	public void qlcauhinhdanhmucthem_nhn_button_lu_nu_typeerror_l_special_th_b_qua(DataTable table) {
		log.info("=========== QLCauHinhDanhMuc-Them: nhấn button Lưu nếu typeError là special thì bỏ qua ===========");
		String typeError = ConfigCategoryAddPage.getValueColumnDataTableBDD(table, "TypeError");
		if (!typeError.equals(_specialTypeError)) {
			ConfigCategoryAddPage.clickToSaveButton();
		}
	}

	@And("^QLCauHinhDanhMuc-Them: nhấn button OK xác nhận lưu nếu typeError là unique$")
	public void qlcauhinhdanhmucthem_nhn_button_ok_xc_nhn_lu_nu_typeerror_l_unique(DataTable table) {
		log.info("=========== QLCauHinhDanhMuc-Them: nhấn button OK xác nhận lưu nếu typeError là unique ===========");
		String typeError = ConfigCategoryAddPage.getValueColumnDataTableBDD(table, "TypeError");
		if (typeError.equals(_uniqueTypeError)) {
			ConfigCategoryAddPage.clickToConfirmDialogButton();
		}
	}

	@Then("^QLCauHinhDanhMuc-Them: kiểm tra message error hiển thị$")
	public void qlcauhinhdanhmucthem_kim_tra_message_error_hin_th(DataTable table) {
		String messageError = ConfigCategoryAddPage.getValueColumnDataTableBDD(table, "MessageError");
		String typeError = ConfigCategoryAddPage.getValueColumnDataTableBDD(table, "TypeError");
		String field = ConfigCategoryAddPage.getValueColumnDataTableBDD(table, "Field");

		log.info("=========== QLCauHinhDanhMuc-Them: kiểm tra message error hiển thị_" + field + "_" + messageError + " ===========");

		if (field.equals(_categoryRelevantLabel)) {
			ConfigCategoryAddPage.verifyMessageErrorCategoryRelevant(messageError);
		} else {
			switch (typeError) {
			case _requiredTypeError:
				ConfigCategoryAddPage.dynamicVerifyMessageErrorRequired(messageError, field);
				break;
			case _uniqueTypeError:
				ConfigCategoryAddPage.verifyMessageErrorUnique(messageError);
				break;
			case _specialTypeError:
				ConfigCategoryAddPage.dynamicVerifyMessageErrorSpecial(messageError, field);
				break;
			default:
				System.out.println("not found type message error");
				break;
			}
		}
	}

	/* ============ 03-Kiểm tra autocomplete thành công ============= */
	@Given("^QLCauHinhDanhMuc-Them: nhập dữ liệu trường Loại danh mục là tiền điều kiện nếu field là Danh mục liên quan$")
	public void qlcauhinhdanhmucthem_nhp_d_liu_trng_loi_danh_mc_l_tin_iu_kin_nu_field_l_danh_mc_lin_quan(DataTable table) throws InterruptedException {
		String type = ConfigCategoryAddPage.getValueColumnDataTableBDD(table, "Type");
		String field = ConfigCategoryAddPage.getValueColumnDataTableBDD(table, "Field");

		if (field.equals(_categoryRelevantLabel)) {
			log.info("=========== QLCauHinhDanhMuc-TimKiem: nhập dữ liệu trường Loại danh mục với dữ liệu " + type + " ===========");
			ConfigCategoryAddPage.inputToTypeCombox(type);
		}
	}

	@And("^QLCauHinhDanhMuc-Them: nhập autocomplete với dữ liệu$")
	public void qlcauhinhdanhmucthem_nhp_autocomplete_vi_d_liu(DataTable table) throws InterruptedException {
		String field = ConfigCategoryAddPage.getValueColumnDataTableBDD(table, "Field");
		String case1 = ConfigCategoryAddPage.getValueColumnDataTableBDD(table, "Case");
		String autocomplete = ConfigCategoryAddPage.getValueColumnDataTableBDD(table, "Autocomplete");

		log.info("=========== QLCauHinhDanhMuc-Them: nhập " + field + "_" + case1 + "_autocomplete với dữ liệu " + autocomplete + " ===========");
		if (field.equals(_typeLable)) {
			ConfigCategoryAddPage.inputToTypeAutoComplete(autocomplete);
		}

		if (field.equals(_categoryRelevantLabel)) {
			ConfigCategoryAddPage.inputToCategoryRelevantAutoComplete(autocomplete);
		}
	}

	@And("^QLCauHinhDanhMuc-Them: lấy danh sách kết quả tìm kiếm trên UI trả về$")
	public void qlcauhinhdanhmucthem_ly_danh_sch_kt_qu_tm_kim_trn_ui_tr_v(DataTable table) {
		String field = ConfigCategoryAddPage.getValueColumnDataTableBDD(table, "Field");

		log.info("=========== QLCauHinhDanhMuc-Them: lấy danh sách kết quả tìm kiếm trên UI trả về " + field + "===========");
		if (field.equals(_typeLable)) {
			ShareContextData.ConfigCategory.LST_VALUE_AUTO_UI_Add_Or_Edit_Or_Search = ConfigCategoryAddPage.getListDataCategoryTypeAutoCompleteUI();
		}

		if (field.equals(_categoryRelevantLabel)) {
			ShareContextData.ConfigCategory.LST_VALUE_AUTO_UI_Add_Or_Edit_Or_Search = ConfigCategoryAddPage.getListDataCategoryRelevantAutoCompleteUI();
		}
	}

	@And("^QLCauHinhDanhMuc-Them: lấy danh sách kết quả tìm kiếm DB trả về$")
	public void qlcauhinhdanhmucthem_ly_danh_sch_kt_qu_tm_kim_db_tr_v(DataTable table) throws Exception {
		String field = ConfigCategoryAddPage.getValueColumnDataTableBDD(table, "Field");
		String autocomplete = ConfigCategoryAddPage.getValueColumnDataTableBDD(table, "Autocomplete");
		String type = ConfigCategoryAddPage.getValueColumnDataTableBDD(table, "Type");

		log.info("=========== QLCauHinhDanhMuc-Them: lấy danh sách kết quả tìm kiếm DB trả về " + field + " ===========");
		if (field.equals(_typeLable)) {
			ShareContextData.ConfigCategory.LST_VALUE_AUTO_DB_Add_Or_Edit_Or_Search = ConfigCategoryAddPage.getListDataTypeAutoCompleteDB(autocomplete);
		}

		if (field.equals(_categoryRelevantLabel)) {
			ShareContextData.ConfigCategory.LST_VALUE_AUTO_DB_Add_Or_Edit_Or_Search = ConfigCategoryAddPage.getListDataCategoryRelevanteAutoCompleteDB(type, autocomplete);
		}
	}

	@Then("^QLCauHinhDanhMuc-Them: kiểm tra danh sách kết quả tìm kiếm trên UI trả về bằng danh sách kết quả tìm kiếm DB trả về$")
	public void qlcauhinhdanhmucthem_danh_sch_kt_qu_tm_kim_trn_ui_tr_v_bng_danh_sch_kt_qu_tm_kim_db_tr_v() {
		log.info("=========== QLCauHinhDanhMuc-Them: kiểm tra danh sách kết quả tìm kiếm trên UI trả về bằng danh sách kết quả tìm kiếm DB trả về ===========");
		Assert.assertTrue(ShareContextData.ConfigCategory.LST_VALUE_AUTO_UI_Add_Or_Edit_Or_Search.equals(ShareContextData.ConfigCategory.LST_VALUE_AUTO_DB_Add_Or_Edit_Or_Search));
	}

}
