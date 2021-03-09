package globalRecruitment.StepDefinitions;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import globalRecruitment.pageObjects.Home_PageObject;
import net.thucydides.core.annotations.Steps;

@SuppressWarnings({ "deprecation" })
public class Home_PageDefinitions {
	Log log = LogFactory.getLog(getClass());

	@Steps
	Home_PageObject home;

	@Then("^TrangChu: kiểm tra login thành công với acc \"([^\"]*)\" được hiển thị$")
	public void trangchu_kim_tra_login_thnh_cng_vi_acc_something_c_hin_th(String acc) {
		log.info("=========== TrangChu: kiểm tra login thành công với acc " + acc + " được hiển thị ===========");
		home.verifyLoginSucessfuly(acc);
	}

	@When("^TrangChu: nhấn menu icon \"([^\"]*)\"$")
	public void trangchu_nhn_menu_icon_something(String titleMenu) {
		log.info("=========== TrangChu: nhấn menu icon " + titleMenu + " ===========");
		home.dynamicClickToLeftMenu(titleMenu);
	}
}
