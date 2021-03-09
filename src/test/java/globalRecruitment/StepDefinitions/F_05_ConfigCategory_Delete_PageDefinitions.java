package globalRecruitment.StepDefinitions;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;

import cucumber.api.java.en.And;
import globalRecruitment.pageObjects.F_05_ConfigCategory_Delete_PageObject;
import net.thucydides.core.annotations.Steps;

@SuppressWarnings({ "deprecation" })
public class F_05_ConfigCategory_Delete_PageDefinitions {
	Log log = LogFactory.getLog(getClass());

	@Steps
	F_05_ConfigCategory_Delete_PageObject ConfigCategoryDeletePage;

	@And("^QLCauHinhDanhMuc-Xoa: kiểm tra update thành công DB bảng CAT_GROUP (.*) row với dữ liệu đã xoá$")
	public void qlcauhinhdanhmucxoa_kim_tra_update_thnh_cng_db_bng_catgroup_1_row_vi_d_liu_xo(int rowEx) throws Exception {
		log.info("=========== ConfigCategoryDelete: verify update CAT_GROUP table (.*) row suceess with data edited ===========");
		int rowDB = ConfigCategoryDeletePage.getRowUpdateCatGroupTable(ShareContextData.ConfigCategory.CODE_Added_Or_Edited, ShareContextData.ConfigCategory.NAME_Added_Or_Edited, ShareContextData.ConfigCategory.NAME_ENG_Added_Or_Edited, ShareContextData.ConfigCategory.TYPE_Added_Or_Edited, ShareContextData.ConfigCategory.CATEGORY_RELEVANT_1_Added_Or_Edited,
				ShareContextData.ConfigCategory.CATEGORY_RELEVANT_2_Added_Or_Edited);
		Assert.assertTrue(rowDB == rowEx);
		System.out.println(rowDB);
	}

	@And("^QLCauHinhDanhMuc-Xoa: kiểm tra update thành công DB bảng CATEGORY_GROUP (.*) row với dữ liệu đã xoá$")
	public void qlcauhinhdanhmucxoa_kim_tra_update_thnh_cng_db_bng_categorygroup_2_row_vi_d_liu_xo(int rowEx) throws Exception {
		log.info("=========== ConfigCategoryDelete: verify update CATEGORY_GROUP table (.*) row suceess with data edited ===========");
		int rowDB = ConfigCategoryDeletePage.getRowUpdateCategoryGroupTable(ShareContextData.ConfigCategory.CODE_Added_Or_Edited, ShareContextData.ConfigCategory.NAME_Added_Or_Edited, ShareContextData.ConfigCategory.NAME_ENG_Added_Or_Edited, ShareContextData.ConfigCategory.TYPE_Added_Or_Edited, ShareContextData.ConfigCategory.CATEGORY_RELEVANT_1_Added_Or_Edited,
				ShareContextData.ConfigCategory.CATEGORY_RELEVANT_2_Added_Or_Edited);
		Assert.assertTrue(rowDB == rowEx);
		System.out.println(rowDB);
	}
}
