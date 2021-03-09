package globalRecruitment.StepDefinitions;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import globalRecruitment.pageObjects.F_01_Login_PageObject;
import io.cucumber.datatable.DataTable;
import net.thucydides.core.annotations.Steps;

@SuppressWarnings({ "deprecation" })
public class F_01_Login_PageDefinitions {
	Log log = LogFactory.getLog(getClass());
	String _maNVLabel = "Mã nhân viên/Email";
	String _maKhau = "Mật khẩu";

	@Steps
	F_01_Login_PageObject loginPage;

	@Given("^DangNhap: mở trang Đăng nhập$")
	public void dangnhap_m_trang_ng_nhp() {
		log.info("=========== DangNhap: mở trang Đăng nhập ===========");
		loginPage.openLoginPage();
	}

	@Then("^DangNhap: kiểm tra trang Đăng nhập mở thành công với button Login được hiển thị$")
	public void dangnhap_kim_tra_trang_ng_nhp_m_thnh_cng_vi_button_login_c_hin_th() {
		log.info("=========== DangNhap: kiểm tra trang Đăng nhập mở thành công với button Login được hiển thị ===========");
		loginPage.verifyLoginPageDisplayed();
	}

	@And("^DangNhap: nhập dữ liệu vào các trường với dữ liệu$")
	public void dangnhap_nhp_d_liu_vo_cc_trng_vi_d_liu(DataTable table) {
		log.info("=========== DangNhap: nhập dữ liệu vào các trường với dữ liệu ===========");

		String valueEmailColumn = loginPage.getValueColumnDataTableBDD(table, "Code/Email");
		String valuePasswordColumn = loginPage.getValueColumnDataTableBDD(table, "Password");

		log.info("=========== DangNhap: nhập trường Email với dữ liệu " + valueEmailColumn + " ===========");
		loginPage.dynamicInnputToTextbox(valueEmailColumn, _maNVLabel);

		log.info("=========== DangNhap: nhập trường Password với dữ liệu " + valuePasswordColumn + " ===========");
		loginPage.dynamicInnputToTextbox(valuePasswordColumn, _maKhau);

	}

	@And("^DangNhap: nhấn button Đăng nhập$")
	public void dangnhap_nhn_button_ng_nhp() {
		log.info("=========== DangNhap: nhấn button Đăng nhập ===========");
		loginPage.clickToLoginButton();
	}

	@Then("^DangNhap: kiểm tra hiển thị các message lỗi$")
	public void dangnhap_kim_tra_hin_th_cc_message_li(DataTable table) {
		String messageError = loginPage.getValueColumnDataTableBDD(table, "MessageError");
		log.info("=========== DangNhap: kiểm tra hiển thị các message lỗi " + messageError + " ===========");
		loginPage.verifyMessageErrorDisplayed(messageError);
	}
}
