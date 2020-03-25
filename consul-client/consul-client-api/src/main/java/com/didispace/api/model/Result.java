package com.didispace.api.model;

import com.didispace.api.constant.ResultEnum;
import lombok.Data;

import java.io.Serializable;

/**
 *  结果封装
 * @author fangzheng
 * @param <T>
 */
@Data
public class Result<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	public Result(){};
    public Result(int code, String info) {
        this.code = code;
        this.info = info;
    }
    public Result(int code, String info, T data) {
        this.code = code;
        this.info = info;
        this.data = data;
    }

    public Result(ResultEnum resultEnum, T data) {
        this.code = resultEnum.getCode();
        this.info = resultEnum.getInfo();
        this.data = data;
    }

	/**
	 * 状态code
	 */
	private Integer code = 0;
	/**
	 * 描述信息
	 */
    private String info;
    /**
     * 数据model
     */
	private T data;

    public void ok() {
        ok(ResultEnum.SUCCESS.getInfo());
    }

    public void ok(String info) {
        this.code = ResultEnum.SUCCESS.getCode();
        this.info = info;
    }

    public void ok(T data) {
        this.data = data;
        ok();
    }

    public void error() {
        error(ResultEnum.ERROR.getInfo());
    }

    public void error(String info) {
        this.code = ResultEnum.ERROR.getCode();
        this.info = info;
    }

}
