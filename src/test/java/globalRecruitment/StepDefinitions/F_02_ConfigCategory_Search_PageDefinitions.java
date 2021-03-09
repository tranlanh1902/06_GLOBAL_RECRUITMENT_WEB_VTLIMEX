package globalRecruitment.StepDefinitions;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import globalRecruitment.pageObjects.F_02_ConfigCategory_Search_PageObject;
import io.cucumber.datatable.DataTable;
import net.thucydides.core.annotations.Steps;

@SuppressWarnings({ "deprecation" })
public class F_02_ConfigCategory_Search_PageDefinitions {
	Log log = LogFactory.getLog(getClass());
	String _nameLabel = "Tên cấu hình", _typeLable = "Loại danh mục", _categoryRelevantLabel = "Tên danh mục";

	@Steps
	F_02_ConfigCategory_Search_PageObject ConfigCategorySearchPage;

	/* ============ Chung ============= */
	@Then("^QLCauHinhDanhMuc-TimKiem: kiểm tra mở trang Tìm kiếm cấu hình thành công với title \"([^\"]*)\"$")
	public void qlcauhinhdanhmuctimkiem_kim_tra_m_trang_tm_kim_cu_hnh_thnh_cng_vi_title_something(String titleText) {
		log.info("=========== QLCauHinhDanhMuc-TimKiem: kiểm tra mở trang Tìm kiếm cấu hình thành công với title " + titleText + " ==========");
		ConfigCategorySearchPage.verifySeachPageOpen(titleText);
	}

	@And("^QLCauHinhDanhMuc-TimKiem: nhấn button Tìm kiếm$")
	public void qlcauhinhdanhmuctimkiem_nhn_button_tm_kim() {
		log.info("=========== QLCauHinhDanhMuc-TimKiem: nhấn button Tìm kiếm ==========");
		ConfigCategorySearchPage.clickToSearchButton();
	}

	@Then("^QLCauHinhDanhMuc-TimKiem: kiểm tra số bản ghi trả về thành công là (.*)$")
	public void qlcauhinhdanhmuctimkiem_kim_tra_s_bn_ghi_tr_v_thnh_cng_l_1(int rowEx) {
		log.info("=========== QLCauHinhDanhMuc-TimKiem: kiểm tra số bản ghi trả về thành công là " + rowEx + " ==========");
		int row = ConfigCategorySearchPage.getRowResulSearchUI();
		Assert.assertTrue(row == rowEx);
	}

	/* ============ Thêm ============= */
	@When("^QLCauHinhDanhMuc-TìmKiem: nhấn button Thêm$")
	public void qlcauhinhdanhmuctmkiem_nhn_button_thm() {
		log.info("=========== QLCauHinhDanhMuc-TìmKiem: nhấn button Thêm ==========");
		ConfigCategorySearchPage.clickToButtonAdd();
	}

	@Then("^QLCauHinhDanhMuc-TimKiem: kiểm tra thêm thành công với toast message hiển thị với nội dung \"([^\"]*)\"$")
	public void qlcauhinhdanhmuctimkiem_kim_tra_thm_thnh_cng_vi_toast_message_hin_th_vi_ni_dung_something(String toastMessage) {
		log.info("=========== QLCauHinhDanhMuc-TimKiem: kiểm tra thêm thành công với toast message hiển thị với nội dung " + toastMessage + " ==========");
		Assert.assertEquals(toastMessage, ConfigCategorySearchPage.getToastMessage());

		log.info("=========== Lưu thông tin các trường đã thêm thành công ==========");
		ShareContextData.ConfigCategory.NAME_Added_Or_Edited = ShareContextData.ConfigCategory.NAME_Add_Or_Edit.trim();
		ShareContextData.ConfigCategory.NAME_ENG_Added_Or_Edited = ShareContextData.ConfigCategory.NAME_ENG_Add_Or_Edit.trim();
		ShareContextData.ConfigCategory.TYPE_Added_Or_Edited = ShareContextData.ConfigCategory.TYPE_Add_Or_Edit;
		ShareContextData.ConfigCategory.CATEGORY_RELEVANT_1_Added_Or_Edited = ShareContextData.ConfigCategory.CATEGORY_RELEVANT_1_Add_Or_Edit;
		ShareContextData.ConfigCategory.CATEGORY_RELEVANT_2_Added_Or_Edited = ShareContextData.ConfigCategory.CATEGORY_RELEVANT_2_Add_Or_Edit;
	}

	/* ============ Sửa ============= */
	@When("^QLCauHinhDanhMuc-TimKiem: nhập tiêu chí tìm kiếm Tên cấu hình cần sửa vừa thêm hoặc sửa thành công$")
	public void qlcauhinhdanhmuctimkiem_nhp_tiu_ch_tm_kim_tn_cu_hnh_cn_sa_trong_type_bng_0_va_thm_hoc_sa_thnh_cng_1_tn_ti_trc_() {
		log.info("=========== QLCauHinhDanhMuc-TimKiem: nhập tiêu chí tìm kiếm Tên cấu hình cần sửa với dữ liệu vừa thêm hoặc sửa thành công " + ShareContextData.ConfigCategory.NAME_Added_Or_Edited + " ==========");
		ConfigCategorySearchPage.inputToNameTextbox(ShareContextData.ConfigCategory.NAME_Added_Or_Edited);
	}

	@And("^QLCauHinhDanhMuc-TimKiem: nhấn icon Sửa$")
	public void qlcauhinhdanhmuctimkiem_nhn_icon_sa() {
		log.info("=========== QLCauHinhDanhMuc-TimKiem: nhấn icon Sửa==========");
		ConfigCategorySearchPage.clickToEditIcon();
	}

	@Then("^QLCauHinhDanhMuc-TimKiem: kiểm tra sửa thành công với toast message hiển thị với nội dung \"([^\"]*)\"$")
	public void qlcauhinhdanhmuctimkiem_kim_tra_sua_thnh_cng_vi_toast_message_hin_th_vi_ni_dung_something(String toastMessage) {
		log.info("=========== QLCauHinhDanhMuc-TimKiem: kiểm tra sửa thành công với toast message hiển thị với nội dung " + toastMessage + " ==========");
		Assert.assertEquals(toastMessage, ConfigCategorySearchPage.getToastMessage());

		log.info("=========== Lưu thông tin các trường đã thêm thành công ==========");
		ShareContextData.ConfigCategory.NAME_Added_Or_Edited = ShareContextData.ConfigCategory.NAME_Add_Or_Edit.trim();
		ShareContextData.ConfigCategory.NAME_ENG_Added_Or_Edited = ShareContextData.ConfigCategory.NAME_ENG_Add_Or_Edit.trim();
		ShareContextData.ConfigCategory.TYPE_Added_Or_Edited = ShareContextData.ConfigCategory.TYPE_Add_Or_Edit;
		ShareContextData.ConfigCategory.CATEGORY_RELEVANT_1_Added_Or_Edited = ShareContextData.ConfigCategory.CATEGORY_RELEVANT_1_Add_Or_Edit;
		ShareContextData.ConfigCategory.CATEGORY_RELEVANT_2_Added_Or_Edited = ShareContextData.ConfigCategory.CATEGORY_RELEVANT_2_Add_Or_Edit;
	}

	/* ============ Xoá ============= */
	@When("^QLCauHinhDanhMuc-TimKiem: nhập tiêu chí tìm kiếm Tên cấu hình cần xoá vừa thêm hoặc sửa thành công$")
	public void qlcauhinhdanhmuctimkiem_nhp_tiu_ch_tm_kim_tn_cu_hnh_cn_xo_trong_type_bng_0_va_thm_hoc_sa_thnh_cng_1_tn_ti_trc_() {
		log.info("=========== QLCauHinhDanhMuc-TimKiem: nhập tiêu chí tìm kiếm Tên cấu hình cần sửa với dữ liệu vừa thêm hoặc sửa thành công " + ShareContextData.ConfigCategory.NAME_Added_Or_Edited + " ==========");
		ConfigCategorySearchPage.inputToNameTextbox(ShareContextData.ConfigCategory.NAME_Added_Or_Edited);
	}

	@And("^QLCauHinhDanhMuc-TimKiem: nhấn icon Xoá$")
	public void qlcauhinhdanhmuctimkiem_nhn_icon_xo() {
		log.info("=========== QLCauHinhDanhMuc-TimKiem: nhấn icon Xoá ==========");
		ConfigCategorySearchPage.clickToDeleteIcon();
	}

	@Then("^QLCauHinhDanhMuc-TimKiem: kiểm tra xoá thành công với toast message hiển thị với nội dung \"([^\"]*)\"$")
	public void qlcauhinhdanhmuctimkiem_kim_tra_xo_thnh_cng_vi_toast_message_hin_th_vi_ni_dung_something(String toastMessage) {
		log.info("=========== QLCauHinhDanhMuc-TimKiem: kiểm tra xoá thành công với toast message hiển thị với nội dung " + toastMessage + " ==========");
		Assert.assertEquals(toastMessage, ConfigCategorySearchPage.getToastMessage());
	}

	@And("^QLCauHinhDanhMuc-TimKiem: nhấn button OK xác nhận xoá$")
	public void qlcauhinhdanhmuctimkiem_nhn_button_ok_xc_nhn_xo() {
		log.info("=========== QLCauHinhDanhMuc-TimKiem: nhấn button OK xác nhận xoá ===========");
		ConfigCategorySearchPage.clickToConfirmDialogButton(ShareContextData.ConfigCategory.NAME_Added_Or_Edited);
	}

	/* ============ 01_Tìm kiếm thành công ============= */

	@Given("^QLCauHinhDanhMuc-TimKiem: nhập các tiêu chí tìm kiếm$")
	public void qlcauhinhdanhmuctimkiem_nhp_cc_tiu_ch_tm_kim(DataTable table) {
		String group = ConfigCategorySearchPage.getValueColumnDataTableBDD(table, "Group");
		String field = ConfigCategorySearchPage.getValueColumnDataTableBDD(table, "Field");
		String case1 = ConfigCategorySearchPage.getValueColumnDataTableBDD(table, "Case");
		String name = ConfigCategorySearchPage.getValueColumnDataTableBDD(table, "Name");
		String type = ConfigCategorySearchPage.getValueColumnDataTableBDD(table, "Type");
		String categoryRelevant = ConfigCategorySearchPage.getValueColumnDataTableBDD(table, "CategoryRelevant");

		log.info("=========== QLCauHinhDanhMuc-TimKiem: tìm kiếm với tiêu chí_" + group + "_" + field + "_" + case1 + " ===========");

		log.info("=========== QLCauHinhDanhMuc-TimKiem: nhập tiêu chí tìm kiếm Tên cấu hình với dữ liệu " + name + " ===========");
		ConfigCategorySearchPage.inputToNameTextbox(name);

		if (type.trim().length() > 0) {
			log.info("=========== QLCauHinhDanhMuc-TimKiem: nhập tiêu chí tìm kiếm Loại danh mục với dữ liệu " + type + " ===========");
			ConfigCategorySearchPage.inputToTypeCombox(type);
		}

		if (categoryRelevant.trim().length() > 0) {
			log.info("=========== QLCauHinhDanhMuc-TimKiem: nhập tiêu chí tìm kiêm Danh mục liên quan với dữ liệu " + categoryRelevant + " ===========");
			ConfigCategorySearchPage.inputToCategoryCombox(categoryRelevant);
		}
	}

	@And("^QLCauHinhDanhMuc-TimKiem: lấy số bản ghi tìm kiếm UI trả về$")
	public void qlcauhinhdanhmuctimkiem_s_bn_ghi_tm_kim_ui_tr_v() {
		log.info("=========== QLCauHinhDanhMuc-TimKiem: lấy số bản ghi tìm kiếm UI trả về ===========");
		int rowUI = ConfigCategorySearchPage.getRowResulSearchUI();
		ShareContextData.ConfigCategory.ROW_UI_Search = rowUI;
		System.out.println("dong=" + rowUI);
	}

	@And("^QLCauHinhDanhMuc-TimKiem: lấy số bản ghi tìm kiếm DB trả về$")
	public void qlcauhinhdanhmuctimkiem_s_bn_ghi_tm_kim_db_tr_v(DataTable table) throws Exception {
		log.info("=========== QLCauHinhDanhMuc-TimKiem: lấy số bản ghi tìm kiếm DB trả về ===========");
		String name = ConfigCategorySearchPage.getValueColumnDataTableBDD(table, "Name");
		String type = ConfigCategorySearchPage.getValueColumnDataTableBDD(table, "Type");
		String categoryRelevant = ConfigCategorySearchPage.getValueColumnDataTableBDD(table, "CategoryRelevant");

		int rowDB = ConfigCategorySearchPage.getRowResulSearchDB(name, type, categoryRelevant);
		ShareContextData.ConfigCategory.ROW_DB_Search = rowDB;
	}

	@And("^QLCauHinhDanhMuc-TimKiem: số bản ghi tìm kiếm UI trả về bằng số bản ghi tìm kiếm DB trả về$")
	public void qlcauhinhdanhmuctimkiem_s_bn_ghi_tm_kim_ui_tr_v_bng_s_bn_ghi_tm_kim_db_tr_v() {
		log.info("=========== QLCauHinhDanhMuc-TimKiem: verify get number row UI return equals get number row DB return ===========");
		Assert.assertTrue(ShareContextData.ConfigCategory.ROW_UI_Search == ShareContextData.ConfigCategory.ROW_DB_Search);
	}

	@And("^QLCauHinhDanhMuc-TimKiem: lấy danh sách kết quả tìm kiếm cột Tên cấu hình UI trả về$")
	public void configcategorysearchpage_ly_danh_sch_kt_qu_tm_kim_ct_tn_cu_hnh_ui_tr_v() {
		log.info("=========== QLCauHinhDanhMuc-TimKiem: lấy danh sách kết quả tìm kiếm cột Tên cấu hình UI trả về ===========");
		ShareContextData.ConfigCategory.LST_VALUE_COLUMN_NAME_UI_Search = ConfigCategorySearchPage.getListDataColumnNameUI();
	}

	@And("^QLCauHinhDanhMuc-TimKiem: lấy danh sách kết quả tìm kiếm cột Tên cấu hình DB trả về$")
	public void configcategorysearchpage_ly_danh_sch_kt_qu_tm_kim_ct_tn_cu_hnh_db_tr_v(DataTable table) throws Exception {
		log.info("=========== QLCauHinhDanhMuc-TimKiem: lấy danh sách kết quả tìm kiếm cột Tên cấu hình DB trả về ===========");
		String name = ConfigCategorySearchPage.getValueColumnDataTableBDD(table, "Name");
		String type = ConfigCategorySearchPage.getValueColumnDataTableBDD(table, "Type");
		String categoryRelevant = ConfigCategorySearchPage.getValueColumnDataTableBDD(table, "CategoryRelevant");
		ShareContextData.ConfigCategory.LST_VALUE_COLUMN_NAME_DB_Search = ConfigCategorySearchPage.getListDataColumnNameDB(name, type, categoryRelevant);
	}

	@And("^QLCauHinhDanhMuc-TimKiem: kiểm tra danh sách kết quả tìm kiếm cột Tên cấu hình UI trả về bằng danh sách kết quả tìm kiếm cột Tên cấu hình DB trả về$")
	public void qlcauhinhdanhmuctimkiem_kim_tra_danh_sch_kt_qu_tm_kim_ct_tn_cu_hnh_ui_tr_v_bng_danh_sch_kt_qu_tm_kim_ct_tn_cu_hnh_db_tr_v() {

		System.out.println(ShareContextData.ConfigCategory.LST_VALUE_COLUMN_NAME_UI_Search);
		System.out.println("==============");
		System.out.println(ShareContextData.ConfigCategory.LST_VALUE_COLUMN_NAME_DB_Search);
		log.info("=========== QLCauHinhDanhMuc-TimKiem: verify list data column Name UI return equals get list data column Name DB return ===========");
		Assert.assertTrue(ShareContextData.ConfigCategory.LST_VALUE_COLUMN_NAME_DB_Search.equals(ShareContextData.ConfigCategory.LST_VALUE_COLUMN_NAME_UI_Search));
	}

	/* ============ 02_Validate autocomplete ============= */
	@Given("^QLCauHinhDanhMuc-TimKiem: nhập dữ liệu trường Loại danh mục là tiền điều kiện nếu field là Danh mục liên quan$")
	public void qlcauhinhdanhmuctimkiem_nhp_d_liu_trng_loi_danh_mc_l_tin_iu_kin_nu_field_l_danh_mc_lin_quan(DataTable table) {
		String type = ConfigCategorySearchPage.getValueColumnDataTableBDD(table, "Type");
		String field = ConfigCategorySearchPage.getValueColumnDataTableBDD(table, "Field");

		if (field.equals(_categoryRelevantLabel)) {
			log.info("=========== QLCauHinhDanhMuc-TimKiem: nhập dữ liệu trường Loại danh mục với dữ liệu " + type + " ===========");
			ConfigCategorySearchPage.inputToTypeCombox(type);
		}
	}

	@And("^QLCauHinhDanhMuc-TimKiem: nhập autocomplete với dữ liệu$")
	public void qlcauhinhdanhmuctimkiem_nhp_autocomplete_vi_d_liu(DataTable table) throws InterruptedException {
		String field = ConfigCategorySearchPage.getValueColumnDataTableBDD(table, "Field");
		String case1 = ConfigCategorySearchPage.getValueColumnDataTableBDD(table, "Case");
		String autocomplete = ConfigCategorySearchPage.getValueColumnDataTableBDD(table, "Autocomplete");

		log.info("=========== QLCauHinhDanhMuc-TimKiem: nhập " + field + "_" + case1 + "_autocomplete với dữ liệu " + autocomplete + " ===========");
		if (field.equals(_typeLable)) {
			ConfigCategorySearchPage.inputToTypeAutoComplete(autocomplete);
		}

		if (field.equals(_categoryRelevantLabel)) {
			ConfigCategorySearchPage.inputToCategoryRelevantAutoComplete(autocomplete);
		}
	}

	@And("^QLCauHinhDanhMuc-TimKiem: lấy danh sách kết quả tìm kiếm UI trả về$")
	public void qlcauhinhdanhmuctimkiem_ly_danh_sch_kt_qu_tm_kim_ui_tr_v(DataTable table) {
		String field = ConfigCategorySearchPage.getValueColumnDataTableBDD(table, "Field");

		log.info("=========== QLCauHinhDanhMuc-TimKiem: lấy danh sách kết quả tìm kiếm UI trả về " + field + " ===========");
		if (field.equals(_typeLable)) {
			ShareContextData.ConfigCategory.LST_VALUE_AUTO_UI_Add_Or_Edit_Or_Search = ConfigCategorySearchPage.getListDataCategoryTypeAutoCompleteUI();
		}

		if (field.equals(_categoryRelevantLabel)) {
			ShareContextData.ConfigCategory.LST_VALUE_AUTO_UI_Add_Or_Edit_Or_Search = ConfigCategorySearchPage.getListDataCategoryRelevantAutoCompleteUI();
		}
	}

	@And("^QLCauHinhDanhMuc-TimKiem: lấy danh sách kết quả tìm kiếm DB trả về$")
	public void qlcauhinhdanhmuctimkiem_ly_danh_sch_kt_qu_tm_kim_db_tr_v(DataTable table) throws Exception {
		String field = ConfigCategorySearchPage.getValueColumnDataTableBDD(table, "Field");
		String autocomplete = ConfigCategorySearchPage.getValueColumnDataTableBDD(table, "Autocomplete");
		String type = ConfigCategorySearchPage.getValueColumnDataTableBDD(table, "Type");

		log.info("=========== QLCauHinhDanhMuc-TimKiem: lấy danh sách kết quả tìm kiếm DB trả về " + field + " ===========");
		if (field.equals(_typeLable)) {
			ShareContextData.ConfigCategory.LST_VALUE_AUTO_DB_Add_Or_Edit_Or_Search = ConfigCategorySearchPage.getListDataTypeAutoCompleteDB(autocomplete);
		}

		if (field.equals(_categoryRelevantLabel)) {
			ShareContextData.ConfigCategory.LST_VALUE_AUTO_DB_Add_Or_Edit_Or_Search = ConfigCategorySearchPage.getListDataCategoryRelevanteAutoCompleteDB(type, autocomplete);
		}
	}

	@Then("^QLCauHinhDanhMuc-TimKiem: kiểm tra danh sách kết quả tìm kiếm trên UI trả về bằng danh sách kết quả tìm kiếm DB trả về$")
	public void qlcauhinhdanhmuctimkiem_kim_tra_danh_sch_kt_qu_tm_kim_trn_ui_tr_v_bng_danh_sch_kt_qu_tm_kim_db_tr_v() {
		log.info("=========== LCauHinhDanhMuc-TimKiem: kiểm tra danh sách kết quả tìm kiếm trên UI trả về bằng danh sách kết quả tìm kiếm DB trả về ===========");
		System.out.println(ShareContextData.ConfigCategory.LST_VALUE_AUTO_UI_Add_Or_Edit_Or_Search);
		System.out.println("================");
		System.out.println(ShareContextData.ConfigCategory.LST_VALUE_AUTO_DB_Add_Or_Edit_Or_Search);

		Assert.assertTrue(ShareContextData.ConfigCategory.LST_VALUE_AUTO_UI_Add_Or_Edit_Or_Search.equals(ShareContextData.ConfigCategory.LST_VALUE_AUTO_DB_Add_Or_Edit_Or_Search));
	}

}
