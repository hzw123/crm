package cn.mauth.crm.util.common;

/**
 * 常量工具类
 * 
 * @author mauth
 */
public final class Constants {

	public final static String ADMIN = "超级管理员";

	private Constants() {
	}

	/**
	 * 常量
	 * 
	 * @author mauth
	 */
	public interface Session {
		public final static String USER_TYPE = "accounting_user_type";
		public final static String USER_ID = "accounting_user_info_id";
		public final static String USER = "accounting_user";
		public final static String MENU = "accounting_menu";
		public final static String PERMISSIONS = "accounting_Permissions";
		public final static String ROLE = "accounting_role";
		public final static String ACCOUNT = "accounting_account";
		public final static String SESSION_INDEX = "accounting_sessionIndex";
	}

	public interface Redis {
		/**
		 * redis-key-前缀-shiro:access_token:
		 */
		public final static String PREFIX_SHIRO_ACCESS_TOKEN = "shiro:access_token:";
		public final static String PREFIX_SHIRO_CURRENT_TIME_MILLIS="shiro:currentTimeMillis:";
		/**
		 * PASSWORD_MAX_LEN
		 */
		public static final Integer PASSWORD_MAX_LEN = 8;
	}


}
