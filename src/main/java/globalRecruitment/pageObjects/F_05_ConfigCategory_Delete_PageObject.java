package globalRecruitment.pageObjects;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import common.CommonUI;
import common.CommonJDBC;

public class F_05_ConfigCategory_Delete_PageObject extends CommonUI {
	Connection connection;
	String query;

	public int getRowUpdateCatGroupTable(String codeAddedOrEdited, String nameAddedOrEdited, String nameEngAddedOrEdited, String typeAddedOrEdited, String categoryRelevan1ddedOrEdited, String categoryRelevan2ddedOrEdited) throws Exception {
		connection = CommonJDBC.getConnectionMySqlGolobalRecruitmentDomain();
		Map<String, Object> mapNameValueParameter = new HashMap<String, Object>();

		String code = " " + "AND A.CODE =:code";
		String name = " " + "AND A.NAME =:name";
		String nameEng = " " + "AND A.NAME_EN=:nameEng";
		String type = " " + "AND B.NAME=:type";
		String categoryRelevant1 = " " + "AND (C.NAME =:categoryRelevant1";
		String categoryRelevant2 = " " + "OR C.NAME =:categoryRelevant2)";

		query = "SELECT DISTINCT A.* FROM CAT_GROUP A, CATEGORY_TYPE B, CATEGORY C, CATEGORY_GROUP D WHERE A.GROUP_ID = D.GROUP_ID AND D.CATEGORY_ID=C.CATEGORY_ID AND C.CATEGORY_TYPE_ID = B.CATEGORY_TYPE_ID AND A.IS_ACTIVE = 0 AND 1=1" + code + name + nameEng + type + categoryRelevant1 + categoryRelevant2;

		mapNameValueParameter.put("code", codeAddedOrEdited.trim());
		mapNameValueParameter.put("name", nameAddedOrEdited.trim());
		mapNameValueParameter.put("nameEng", nameEngAddedOrEdited.trim());
		mapNameValueParameter.put("type", typeAddedOrEdited.trim());
		mapNameValueParameter.put("categoryRelevant1", categoryRelevan1ddedOrEdited.trim());
		mapNameValueParameter.put("categoryRelevant2", categoryRelevan2ddedOrEdited.trim());

		return CommonJDBC.getRowCountDB(connection, query, mapNameValueParameter);
	}

	public int getRowUpdateCategoryGroupTable(String codeAddedOrEdited, String nameAddedOrEdited, String nameEngAddedOrEdited, String typeAddedOrEdited, String categoryRelevan1ddedOrEdited, String categoryRelevan2ddedOrEdited) throws Exception {
		connection = CommonJDBC.getConnectionMySqlGolobalRecruitmentDomain();
		Map<String, Object> mapNameValueParameter = new HashMap<String, Object>();
		mapNameValueParameter.put("code", codeAddedOrEdited.trim());

		String code = " " + "AND A.CODE =:code";
		String name = " " + "AND A.NAME =:name";
		String nameEng = " " + "AND A.NAME_EN=:nameEng";
		String type = " " + "AND B.NAME=:type";
		String categoryRelevant1 = " " + "AND (C.NAME =:categoryRelevant1";
		String categoryRelevant2 = " " + "OR C.NAME =:categoryRelevant2)";

		query = "SELECT DISTINCT C.* FROM CAT_GROUP A, CATEGORY_TYPE B, CATEGORY C, CATEGORY_GROUP D WHERE A.GROUP_ID = D.GROUP_ID AND D.CATEGORY_ID=C.CATEGORY_ID AND C.CATEGORY_TYPE_ID = B.CATEGORY_TYPE_ID AND A.IS_ACTIVE = 0 AND 1=1" + code + name + nameEng + type + categoryRelevant1 + categoryRelevant2;

		mapNameValueParameter.put("code", codeAddedOrEdited.trim());
		mapNameValueParameter.put("name", nameAddedOrEdited.trim());
		mapNameValueParameter.put("nameEng", nameEngAddedOrEdited.trim());
		mapNameValueParameter.put("type", typeAddedOrEdited.trim());
		mapNameValueParameter.put("categoryRelevant1", categoryRelevan1ddedOrEdited.trim());
		mapNameValueParameter.put("categoryRelevant2", categoryRelevan2ddedOrEdited.trim());

		return CommonJDBC.getRowCountDB(connection, query, mapNameValueParameter);
	}

}
