package globalRecruitment.StepDefinitions;

import java.util.List;

public class ShareContextData {

	public static class ConfigCategory {

		// function add or edit
		public static String NAME_Add_Or_Edit, NAME_ENG_Add_Or_Edit, TYPE_Add_Or_Edit, CATEGORY_RELEVANT_1_Add_Or_Edit, CATEGORY_RELEVANT_2_Add_Or_Edit;
		// public static String CODE_Added_Or_Edited, NAME_Added_Or_Edited, NAME_ENG_Added_Or_Edited, TYPE_Added_Or_Edited, CATEGORY_RELEVANT_1_Added_Or_Edited, CATEGORY_RELEVANT_2_Added_Or_Edited;
		public static String CODE_Added_Or_Edited = "111229398", NAME_Added_Or_Edited = "Ngôn ngữ1", NAME_ENG_Added_Or_Edited = "Ngôn ngữ english2", TYPE_Added_Or_Edited = "Ngôn ngữ", CATEGORY_RELEVANT_1_Added_Or_Edited = "Chinese", CATEGORY_RELEVANT_2_Added_Or_Edited = "Thái Lan";
		// public static String CODE_Added_Or_Edited, NAME_Added_Or_Edited = "Thị trường1", NAME_ENG_Added_Or_Edited = "Thị trường english2", TYPE_Added_Or_Edited = "Danh sách thị trường", CATEGORY_RELEVANT_1_Added_Or_Edited = "Lào", CATEGORY_RELEVANT_2_Added_Or_Edited = "Đông Timor";

		public static Integer ROW_UI_Search, ROW_DB_Search;
		public static List<String> LST_VALUE_COLUMN_NAME_DB_Search, LST_VALUE_COLUMN_NAME_UI_Search;
		public static String TYPE_SELECTED_Search;

		public static List<String> LST_VALUE_AUTO_DB_Add_Or_Edit_Or_Search, LST_VALUE_AUTO_UI_Add_Or_Edit_Or_Search;

	}
}
