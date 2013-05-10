package cn.edu.shiep.business;

import javax.servlet.http.HttpServletRequest;

import cn.edu.shiep.model.RequestModel;

public abstract class AbsBusiness {

	protected HttpServletRequest request;

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
protected String pathSufix;
	
	public void setPathSufix(String pathSufix) {
		this.pathSufix = pathSufix;
	}


	/**
	 * 业务处理
	 * 
	 * @return
	 */
	public abstract String doBusiness(RequestModel reqModel);
}
