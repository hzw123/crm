package cn.mauth.crm.util.common;

/**
 * 常量工具类
 * 
 * @author mauth
 */
public final class Constants {

	public final static String ADMIN = "admin";
	public final static String MANAGEER= "manager";
	public final static String USER = "user";
	public final static String SESSION_ID = "sessionId";

	private Constants() {
	}

	/**
	 * 常量
	 *
	 * @author mauth
	 */
	public interface Session {
		public final static String USER_TYPE = "crm_mauth_cn_user_type";
		public final static String USER_ID = "crm_mauth_cn_user_info_id";
		public final static String USER = "crm_mauth_cn_user";
		public final static String ADMIN = "crm_mauth_cn_admin";
		public final static String MENU = "crm_mauth_cn_menu";
		public final static String PERMISSIONS = "crm_mauth_cn_Permissions";
		public final static String ROLE = "crm_mauth_cn_role";
		public final static String LOGIN_TYPE = "crm_mauth_cn_login_type";
		public final static String SESSION_KEY = "crm_mauth_cn_session_key";

	}

	public interface Jwt {
		public final static String USER_ID = "crm_mauth_cn_user_info_id";
	}

}
