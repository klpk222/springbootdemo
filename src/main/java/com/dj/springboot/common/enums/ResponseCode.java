package com.dj.springboot.common.enums;

public enum ResponseCode {
	/**
	 * @Description:正确
	 */
	OK(200),

	/**
	 * @Description:错误
	 */
	BAD_REQUEST(400),

	/**
	 * @Description:未经授权
	 */
	LIMIT_ERROR_CODE(401),

	/**
	 * @Description:Token过期
	 */
	TOKEN_TIMEOUT_CODE(402),

	/**
	 * @Description:禁止访问
	 */
	NO_AUTH_CODE(403),

	/**
	 * @Description:资源未找到
	 */
	NOT_FOUND(404),

	/**
	 * @Description:服务器错误
	 */
	INTERNAL_SERVER_ERROR(500);

	private int code;
	public void setCode(int code) {
		this.code = code;
	}
	public int getCode() {
		return code;
	}
	private ResponseCode(int code) {
		this.code = code;
	}
}
