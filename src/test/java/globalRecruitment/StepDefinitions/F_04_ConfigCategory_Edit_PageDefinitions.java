package globalRecruitment.StepDefinitions;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import globalRecruitment.pageObjects.F_04_ConfigCategory_Edit_PageObject;
import io.cucumber.datatable.DataTable;
import net.thucydides.core.annotations.Steps;

@SuppressWarnings({ "deprecation" })
public class F_04_ConfigCategory_Edit_PageDefinitions {
	Log log = LogFactory.getLog(getClass());
	String _nameLabel = "Tên cấu hình", _nameEngLabel = "Tên cấu hình (Tiếng Anh)", _typeLable = "Loại danh mục", _categoryRelevantLabel = "Danh mục liên quan";
	final String _uniqueTypeError = "unique", _requiredTypeError = "required", _specialTypeError = "special";

	@Steps
	F_04_ConfigCategory_Edit_PageObject configCategoryEditPage;

	/* ============ Chung ============= */
	@Then("^QLCauHinhDanhMuc-Sua: kiểm tra mở trang Sửa cấu hình thành công với title \"([^\"]*)\"$")
	public void qlcauhinhdanhmucsua_kim_tra_m_trang_sa_cu_hnh_thnh_cng_vi_title_something(String titleText) {
		log.info("=========== QLCauHinhDanhMuc-Sua: kiểm tra mở trang Sửa cấu hình thành công với title " + titleText + " ===========");
		configCategoryEditPage.verifyConfigCategoryEditOpen(titleText);
	}

	/* ============ 01-Kiểm tra Sửa cấu hình thành công ============= */
	@When("^QLCauHinhDanhMuc-Sua: nhập dữ liệu màn hình Sửa cấu hình với các dữ liệu sau$")
	public void qlcauhinhdanhmucthem_nhp_d_liu_mn_hnh_thm_cu_hnh_vi_cc_d_liu_sau(DataTable table) throws InterruptedException {
		String name = configCategoryEditPage.getValueColumnDataTableBDD(table, "Name");
		String nameEnglish = configCategoryEditPage.getValueColumnDataTableBDD(table, "NameEnglish");
		String type = configCategoryEditPage.getValueColumnDataTableBDD(table, "Type");
		String categoryRelevant1 = configCategoryEditPage.getValueColumnDataTableBDD(table, "CategoryRelevant1");
		String categoryRelevant2 = configCategoryEditPage.getValueColumnDataTableBDD(table, "CategoryRelevant2");

		log.info("=========== QLCauHinhDanhMuc-Sua: nhập trường Tên cấu hình with data " + name + " ===========");
		configCategoryEditPage.dynamicInputToTextbox(name, "Tên cấu hình");

		log.info("=========== QLCauHinhDanhMuc-Sua: nhập trường Tên cấu hình (Tiếng Anh) với dữ liệu " + nameEnglish + " ===========");
		configCategoryEditPage.dynamicInputToTextbox(nameEnglish, "Tên cấu hình (Tiếng Anh)");

		log.info("=========== QLCauHinhDanhMuc-Sua: nhập trường Loại danh mục với dữ liệu " + type + ":" + ShareContextData.ConfigCategory.TYPE_Added_Or_Edited + " ===========");
		configCategoryEditPage.dynamicInputToTypeCombox(type, ShareContextData.ConfigCategory.TYPE_Added_Or_Edited);

		log.info("=========== QLCauHinhDanhMuc-Sua: nhập trường Danh mục liên quan với dữ liệu " + categoryRelevant1 + " ===========");
		configCategoryEditPage.dynamicInputToCategoryCombox1(categoryRelevant1);

		log.info("=========== QLCauHinhDanhMuc-Sua: nhập trường Danh mục liên quan với dữ liệu " + categoryRelevant2 + " ===========");
		configCategoryEditPage.dynamicInputToCategoryCombox2(categoryRelevant2);

		log.info("=========== QLCauHinhDanhMuc-Sua: Lưu thông tin các trường trên màn hình Sửa cấu hình danh mục đã nhập ===========");
		ShareContextData.ConfigCategory.NAME_Add_Or_Edit = name;
		ShareContextData.ConfigCategory.NAME_ENG_Add_Or_Edit = nameEnglish;
		ShareContextData.ConfigCategory.TYPE_Add_Or_Edit = type;
		ShareContextData.ConfigCategory.CATEGORY_RELEVANT_1_Add_Or_Edit = categoryRelevant1;
		ShareContextData.ConfigCategory.CATEGORY_RELEVANT_2_Add_Or_Edit = categoryRelevant2;
	}

	@When("^QLCauHinhDanhMuc-Sua: nhấn button Lưu$")
	public void qlcauhinhdanhmucthem_nhn_button_lu() {
		log.info("=========== QLCauHinhDanhMuc-Sua: nhấn button Lưu ===========");
		configCategoryEditPage.clickToSaveButton();
	}

	@And("^QLCauHinhDanhMuc-Sua: nhấn button OK xác nhận lưu$")
	public void qlcauhinhdanhmucthem_nhn_button_ok_xc_nhn_lu() {
		log.info("=========== QLCauHinhDanhMuc-Sua: nhấn button OK xác nhận lưu ===========");
		configCategoryEditPage.clickToConfirmDialogButton();
	}

	@And("^QLCauHinhDanhMuc-Sua: kiểm tra update thành công DB bảng CAT_GROUP (.*) row với dữ liệu đã sửa$")
	public void qlcauhinhdanhmucsua_kim_tra_insert_thnh_cng_db_bng_catgroup_1_row_vi_d_liu_sa(int rowEx) throws Exception {
		log.info("=========== QLCauHinhDanhMuc-Sua: kiểm tra insert thành công DB bảng CAT_GROUP " + rowEx + " row với dữ liệu đã sửa ===========");
		int rowDB = configCategoryEditPage.getRowUpdateCatGroupTable(ShareContextData.ConfigCategory.NAME_Added_Or_Edited, ShareContextData.ConfigCategory.NAME_ENG_Added_Or_Edited, ShareContextData.ConfigCategory.TYPE_Added_Or_Edited, ShareContextData.ConfigCategory.CATEGORY_RELEVANT_1_Added_Or_Edited, ShareContextData.ConfigCategory.CATEGORY_RELEVANT_2_Added_Or_Edited);
		Assert.assertTrue(rowDB == rowEx);
	}

	@And("^QLCauHinhDanhMuc-Sua: kiểm tra update thành công DB bảng CATEGORY_GROUP (.*) row với dữ liệu đã sửa$")
	public void qlcauhinhdanhmucsua_kim_tra_insert_thnh_cng_db_bng_categorygroup_2_row_vi_d_liu_sa(int rowEx) throws Throwable {
		log.info("=========== QLCauHinhDanhMuc-Sua: kiểm tra insert thành công DB bảng CATEGORY_GROUP " + rowEx + " row với dữ liệu đã sửa===========");
		int rowDB = configCategoryEditPage.getRowUpdateCategoryGroupTable(ShareContextData.ConfigCategory.NAME_Added_Or_Edited, ShareContextData.ConfigCategory.NAME_ENG_Added_Or_Edited, ShareContextData.ConfigCategory.TYPE_Added_Or_Edited, ShareContextData.ConfigCategory.CATEGORY_RELEVANT_1_Added_Or_Edited, ShareContextData.ConfigCategory.CATEGORY_RELEVANT_2_Added_Or_Edited);
		Assert.assertTrue(rowDB == rowEx);
	}

	@When("^QLCauHinhDanhMuc-Sua: lấy dữ liệu column code của Cấu hình danh mục vừa sửa trong bảng CAT_GROUP$")
	public void qlcauhinhdanhmucthem_ly_d_liu_column_code_ca_cu_hnh_danh_mc_va_sa_trong_bng_catgroup() throws Throwable {
		log.info("=========== QLCauHinhDanhMuc-Them: lấy dữ liệu column code của Cấu hình danh mục vừa thêm trong bảng CAT_GROU P===========");
		ShareContextData.ConfigCategory.CODE_Added_Or_Edited = configCategoryEditPage.getCodeColumnValueInCatGroupTable(ShareContextData.ConfigCategory.NAME_Added_Or_Edited, ShareContextData.ConfigCategory.NAME_ENG_Added_Or_Edited, ShareContextData.ConfigCategory.TYPE_Added_Or_Edited, ShareContextData.ConfigCategory.CATEGORY_RELEVANT_1_Added_Or_Edited,
				ShareContextData.ConfigCategory.CATEGORY_RELEVANT_2_Added_Or_Edited);
	}

	/* ============ 02-Validate các trường ============= */
	@Given("^QLCauHinhDanhMuc-Sua: nhập dữ liệu màn hình Thêm cấu hình với các dữ liệu lỗi sau$")
	public void qlcauhinhdanhmucsua_nhp_d_liu_mn_hnh_thm_cu_hnh_vi_cc_d_liu_li_sau(DataTable table) {
		String field = configCategoryEditPage.getValueColumnDataTableBDD(table, "Field");
		String case1 = configCategoryEditPage.getValueColumnDataTableBDD(table, "Case");
		String name = configCategoryEditPage.getValueColumnDataTableBDD(table, "Name");
		String nameEnglish = configCategoryEditPage.getValueColumnDataTableBDD(table, "NameEnglish");
		String type = configCategoryEditPage.getValueColumnDataTableBDD(table, "Type");
		String categoryRelevant1 = configCategoryEditPage.getValueColumnDataTableBDD(table, "CategoryRelevant1");
		String categoryRelevant2 = configCategoryEditPage.getValueColumnDataTableBDD(table, "CategoryRelevant2");
		String typeError = configCategoryEditPage.getValueColumnDataTableBDD(table, "TypeError");

		log.info("=========== QLCauHinhDanhMuc-Sua: nhập dữ liệu màn hình Thêm cấu hình với các dữ liệu lỗi " + field + "_" + case1 + " ===========");

		if (typeError.equals(_specialTypeError)) {
			if (name.trim().length() > 0) {
				log.info("=========== QLCauHinhDanhMuc-Sua: nhập dữ liệu trường Tên cấu hình với dữ liệu " + name + " ===========");
				configCategoryEditPage.dynamicInputToTextbox(name, "Tên cấu hình");
			}

			if (nameEnglish.trim().length() > 0) {
				log.info("=========== QLCauHinhDanhMuc-Sua: nhập dữ liệu trường Tên cấu hình (Tiếng Anh) với dữ liệu " + nameEnglish + " ===========");
				configCategoryEditPage.dynamicInputToTextbox(nameEnglish, "Tên cấu hình (Tiếng Anh)");
			}

			if (type.trim().length() > 0) {
				log.info("=========== QLCauHinhDanhMuc-Sua: nhập dữ liệu trường Loại danh mục với dữ liệu " + ShareContextData.ConfigCategory.TYPE_Added_Or_Edited + "===========");
				configCategoryEditPage.dynamicInputToTypeCombox(type, ShareContextData.ConfigCategory.TYPE_Added_Or_Edited);
			}

			if (categoryRelevant1.trim().length() > 0) {
				log.info("=========== QLCauHinhDanhMuc-Sua: nhập dữ liệu trường Danh mục liên quan với dữ liệu " + categoryRelevant1 + " ===========");
				configCategoryEditPage.dynamicInputToCategoryCombox1(categoryRelevant1);
			}

			if (categoryRelevant2.trim().length() > 0) {
				log.info("=========== QLCauHinhDanhMuc-Sua: nhập dữ liệu trường Danh mục liên quan với dữ liệu " + categoryRelevant2 + " ===========");
				configCategoryEditPage.dynamicInputToCategoryCombox2(categoryRelevant2);
			}
		} else {
			log.info("=========== QLCauHinhDanhMuc-Sua: nhập dữ liệu trường Tên cấu hình với dữ liệu " + name + " ===========");
			configCategoryEditPage.dynamicInputToTextbox(name, _nameLabel);

			log.info("=========== QLCauHinhDanhMuc-Sua: nhập dữ liệu trường Tên cấu hình (Tiếng Anh) với dữ liệu " + nameEnglish + " ===========");
			configCategoryEditPage.dynamicInputToTextbox(nameEnglish, _nameEngLabel);

			log.info("=========== QLCauHinhDanhMuc-Sua: nhập dữ liệu trường Loại danh mục với dữ liệu " + ShareContextData.ConfigCategory.TYPE_Added_Or_Edited + "===========");
			configCategoryEditPage.dynamicInputToTypeCombox(type, ShareContextData.ConfigCategory.TYPE_Added_Or_Edited);

			log.info("=========== QLCauHinhDanhMuc-Sua: nhập dữ liệu trương Danh mục liên quan with data " + categoryRelevant1 + " ===========");
			configCategoryEditPage.dynamicInputToCategoryCombox1(categoryRelevant1);

			log.info("=========== QLCauHinhDanhMuc-Sua: nhập dữ liệu trường Danh mục liên quan với dữ liệu " + categoryRelevant2 + " ===========");
			configCategoryEditPage.dynamicInputToCategoryCombox2(categoryRelevant2);
		}
	}

	@When("^QLCauHinhDanhMuc-Sua: nhấn button Lưu nếu typeError là special thì bỏ qua$")
	public void qlcauhinhdanhmucsua_nhn_button_lu_nu_typeerror_l_special_th_b_qua(DataTable table) {
		log.info("=========== QLCauHinhDanhMuc-Sua: nhấn button Lưu nếu typeError là special thì bỏ qua ===========");

		String typeError = configCategoryEditPage.getValueColumnDataTableBDD(table, "TypeError");
		if (!typeError.equals(_specialTypeError)) {
			configCategoryEditPage.clickToSaveButton();
		}
	}

	@And("^QLCauHinhDanhMuc-Sua: nhấn button OK xác nhận lưu nếu typeError là unique$")
	public void qlcauhinhdanhmucsua_nhn_button_ok_xc_nhn_lu_nu_typeerror_l_unique(DataTable table) {
		log.info("=========== QLCauHinhDanhMuc-Sua: nhấn button OK xác nhận lưu nếu typeError là unique ===========");
		String typeError = configCategoryEditPage.getValueColumnDataTableBDD(table, "TypeError");
		if (typeError.equals(_uniqueTypeError)) {
			configCategoryEditPage.clickToConfirmDialogButton();
		}
	}

	@Then("^QLCauHinhDanhMuc-Sua: kiểm tra message error hiển thị$")
	public void qlcauhinhdanhmucsua_kim_tra_message_error_hin_th(DataTable table) throws Throwable {
		String messageError = configCategoryEditPage.getValueColumnDataTableBDD(table, "MessageError");
		String typeError = configCategoryEditPage.getValueColumnDataTableBDD(table, "TypeError");
		String field = configCategoryEditPage.getValueColumnDataTableBDD(table, "Field");

		log.info("=========== QLCauHinhDanhMuc-Sua: kiểm tra message error hiển thị_" + field + "_" + typeError + "_" + messageError + " ===========");

		if (field.equals(_categoryRelevantLabel)) {
			configCategoryEditPage.verifyMessageErrorCategoryRelevant(messageError);
		} else {
			switch (typeError) {
			case _requiredTypeError:
				configCategoryEditPage.dynamicVerifyMessageErrorRequired(messageError, field);
				break;
			case _uniqueTypeError:
				configCategoryEditPage.dynamicVerifyMessageErrorUnique(messageError);
				break;
			case _specialTypeError:
				configCategoryEditPage.dynamicVerifyMessageErrorSpecial(messageError, field);
				break;
			default:
				System.out.println("not found type message error");
				break;
			}
		}
	}

	/* ============ 03-Kiểm tra autocomplete thành công ============= */
	@Given("^QLCauHinhDanhMuc-Sua: nhập dữ liệu trường Loại danh mục là tiền điều kiện nếu field là Danh mục liên quan$")
	public void qlcauhinhdanhmucsua_nhp_d_liu_trng_loi_danh_mc_l_tin_iu_kin_nu_field_l_danh_mc_lin_quan(DataTable table) {
		String type = configCategoryEditPage.getValueColumnDataTableBDD(table, "Type");
		String field = configCategoryEditPage.getValueColumnDataTableBDD(table, "Field");

		if (field.equals(_categoryRelevantLabel)) {
			log.info("=========== QLCauHinhDanhMuc-TimKiem: nhập dữ liệu trường Loại danh mục với dữ liệu " + type + " ===========");
			configCategoryEditPage.dynamicInputToTypeCombox(type, ShareContextData.ConfigCategory.TYPE_Added_Or_Edited);
		}
	}

	@And("^QLCauHinhDanhMuc-Sua: nhập autocomplete với dữ liệu$")
	public void qlcauhinhdanhmucsua_nhp_autocomplete_vi_d_liu(DataTable table) throws InterruptedException {
		String field = configCategoryEditPage.getValueColumnDataTableBDD(table, "Field");
		String case1 = configCategoryEditPage.getValueColumnDataTableBDD(table, "Case");
		String autocomplete = configCategoryEditPage.getValueColumnDataTableBDD(table, "Autocomplete");

		log.info("=========== QLCauHinhDanhMuc-Sua: nhập " + field + "_" + case1 + "_autocomplete với dữ liệu " + autocomplete + " ===========");
		if (field.equals(_typeLable)) {
			log.info("=========== QLCauHinhDanhMuc-Sua: nhập trường Loại danh mục mới dữ liệu " + autocomplete + ":" + ShareContextData.ConfigCategory.TYPE_Added_Or_Edited + " ===========");
			configCategoryEditPage.inputToTypeAutoComplete(autocomplete, ShareContextData.ConfigCategory.TYPE_Added_Or_Edited);
		}

		if (field.equals(_categoryRelevantLabel)) {
			log.info("=========== QLCauHinhDanhMuc-Sua: nhập trường Loại danh mục mới dữ liệu " + autocomplete + ":" + autocomplete + " ===========");
			configCategoryEditPage.inputToCategoryRelevantAutoComplete(autocomplete);
		}
	}

	@And("^QLCauHinhDanhMuc-Sua: lấy danh sách kết quả tìm kiếm UI trả về$")
	public void qlcauhinhdanhmucsua_ly_danh_sch_kt_qu_tm_kim_ui_tr_v(DataTable table) {
		String field = configCategoryEditPage.getValueColumnDataTableBDD(table, "Field");

		log.info("=========== QLCauHinhDanhMuc-Sua: lấy danh sách kết quả tìm kiếm UI trả về " + field + " ===========");
		if (field.equals(_typeLable)) {
			ShareContextData.ConfigCategory.LST_VALUE_AUTO_UI_Add_Or_Edit_Or_Search = configCategoryEditPage.getListDataCategoryTypeAutoCompleteUI();
		}

		if (field.equals(_categoryRelevantLabel)) {
			ShareContextData.ConfigCategory.LST_VALUE_AUTO_UI_Add_Or_Edit_Or_Search = configCategoryEditPage.getListDataCategoryRelevantAutoCompleteUI();
		}
	}

	@And("^QLCauHinhDanhMuc-Sua: lấy danh sách kết quả tìm kiếm DB trả về$")
	public void qlcauhinhdanhmucsua_ly_danh_sch_kt_qu_tm_kim_db_tr_v(DataTable table) throws Exception {
		String field = configCategoryEditPage.getValueColumnDataTableBDD(table, "Field");
		String autocomplete = configCategoryEditPage.getValueColumnDataTableBDD(table, "Autocomplete");
		String type = configCategoryEditPage.getValueColumnDataTableBDD(table, "Type");

		log.info("=========== QLCauHinhDanhMuc-Sua: lấy danh sách kết quả tìm kiếm DB trả về " + field + "===========");
		if (field.equals(_typeLable)) {
			ShareContextData.ConfigCategory.LST_VALUE_AUTO_DB_Add_Or_Edit_Or_Search = configCategoryEditPage.getListDataTypeAutoCompleteDB(autocomplete);
		}

		if (field.equals(_categoryRelevantLabel)) {
			ShareContextData.ConfigCategory.LST_VALUE_AUTO_DB_Add_Or_Edit_Or_Search = configCategoryEditPage.getListDataCategoryRelevanteAutoCompleteDB(type, autocomplete);
		}
	}

	@Then("^QLCauHinhDanhMuc-Sua: kiểm tra danh sách kết quả tìm kiếm trên UI trả về bằng danh sách kết quả tìm kiếm DB trả về$")
	public void qlcauhinhdanhmucsua_danh_sch_kt_qu_tm_kim_trn_ui_tr_v_bng_danh_sch_kt_qu_tm_kim_db_tr_v() {
		log.info("=========== QLCauHinhDanhMuc-Sua: kiểm tra danh sách kết quả tìm kiếm trên UI trả về bằng danh sách kết quả tìm kiếm DB trả về ===========");
		Assert.assertTrue(ShareContextData.ConfigCategory.LST_VALUE_AUTO_UI_Add_Or_Edit_Or_Search.equals(ShareContextData.ConfigCategory.LST_VALUE_AUTO_DB_Add_Or_Edit_Or_Search));
	}

}
