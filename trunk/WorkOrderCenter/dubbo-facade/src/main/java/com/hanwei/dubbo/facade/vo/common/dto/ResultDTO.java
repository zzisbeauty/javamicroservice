package com.hanwei.dubbo.facade.vo.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.xnio.Result;

import java.io.Serializable;

/**
 *   @Description:接口返回数据格式
 */
@Data
@Schema(title="接口返回对象", description="接口返回对象")
public class ResultDTO<T> implements Serializable {

	/**
	 * 成功标志
	 */
	@Schema(description = "成功标志")
	private boolean success = true;

	/**
	 * 返回处理消息
	 */
	@Schema(description = "返回处理消息")
	private String message = "";

	/**
	 * 返回代码
	 */
	@Schema(description = "返回代码")
	private Integer code = 0;

	/**
	 * 返回数据对象 data
	 */
	@Schema(description = "返回数据对象")
	private T result;

	/**
	 * 时间戳
	 */
	@Schema(description = "时间戳")
	private long timestamp = System.currentTimeMillis();

	public ResultDTO() {
	}

    /**
     * 兼容VUE3版token失效不跳转登录页面
     * @param code
     * @param message
     */
	public ResultDTO(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public ResultDTO<T> success(String message) {
		this.message = message;
		this.code = 200;
		this.success = true;
		return this;
	}

	public static<T> ResultDTO<T> ok() {
		ResultDTO<T> r = new ResultDTO<T>();
		r.setSuccess(true);
		r.setCode(200);
		return r;
	}

	public static<T> ResultDTO<T> ok(String msg) {
		ResultDTO<T> r = new ResultDTO<T>();
		r.setSuccess(true);
		r.setCode(200);
		r.setResult((T) msg);
		r.setMessage(msg);
		return r;
	}

	public static<T> ResultDTO<T> ok(T data) {
		ResultDTO<T> r = new ResultDTO<T>();
		r.setSuccess(true);
		r.setCode(200);
		r.setResult(data);
		return r;
	}

	public static<T> ResultDTO<T> OK() {
		ResultDTO<T> r = new ResultDTO<T>();
		r.setSuccess(true);
		r.setCode(200);
		return r;
	}

	/**
	 * 此方法是为了兼容升级所创建
	 *
	 * @param msg
	 * @param <T>
	 * @return
	 */
	public static<T> ResultDTO<T> OK(String msg) {
		ResultDTO<T> r = new ResultDTO<T>();
		r.setSuccess(true);
		r.setCode(200);
		r.setMessage(msg);
		r.setResult((T) msg);
		return r;
	}

	public static<T> ResultDTO<T> OK(T data) {
		ResultDTO<T> r = new ResultDTO<T>();
		r.setSuccess(true);
		r.setCode(200);
		r.setResult(data);
		return r;
	}

	public static<T> ResultDTO<T> OK(String msg, T data) {
		ResultDTO<T> r = new ResultDTO<T>();
		r.setSuccess(true);
		r.setCode(200);
		r.setMessage(msg);
		r.setResult(data);
		return r;
	}

	public static<T> ResultDTO<T> error(String msg, T data) {
		ResultDTO<T> r = new ResultDTO<T>();
		r.setSuccess(false);
		r.setCode(200);
		r.setMessage(msg);
		r.setResult(data);
		return r;
	}

	public static<T> ResultDTO<T> error(String msg) {
		return error(500, msg);
	}

	public static<T> ResultDTO<T> error(int code, String msg) {
		ResultDTO<T> r = new ResultDTO<T>();
		r.setCode(code);
		r.setMessage(msg);
		r.setSuccess(false);
		return r;
	}

	public ResultDTO<T> error500(String message) {
		this.message = message;
		this.code = 500;
		this.success = false;
		return this;
	}

	/**
	 * 无权限访问返回结果
	 */
	public static<T> ResultDTO<T> noauth(String msg) {
		return error(510, msg);
	}

	@JsonIgnore
	private String onlTable;

}
