package com.yanchun.common.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Getter
@Setter
public class ResponseBase {
	private Integer rtnCode;
	private String rtnMsg;
	private Object data;
	public ResponseBase() {
	}
	public ResponseBase(Integer rtnCode, String rtnMsg, Object data) {
		super();
		this.rtnCode = rtnCode;
		this.rtnMsg = rtnMsg;
		this.data = data;
	}
	public static void printResult(final int code, final String msg, final Object obj) {
		HttpServletResponse resp = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
		resp.setHeader("Content-Type",
				"application/json; charset=utf-8");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter pw = null;
		try {
			pw = resp.getWriter();
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("rtnCode", code);
			jsonobj.put("rtnMsg", msg);
			if(obj != null){
				jsonobj.put("data", obj);
			}
			String returnJson = JSON.toJSONString(jsonobj);
			pw.write(returnJson);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (pw != null)
				pw.close();
		}
	}
}