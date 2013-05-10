package cn.edu.shiep.server;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import cn.edu.shiep.business.AbsBusiness;
import cn.edu.shiep.business.CommonBusiness;
import cn.edu.shiep.model.RequestModel;
import cn.edu.shiep.servlet.InitServlet;


public class TotalService  {
	private static Logger log = Logger.getLogger(TotalService.class);
	/**
	 * �����������
	 * @param reqMsg
	 * @return
	 */
	public String businessServe(HttpServletRequest request,RequestModel requestModel) {
		
        //����PAD�ͻ��˽��
        String result = null;
		try {
			String head = requestModel.getHead();
			String pathSufix = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ request.getContextPath()+"/";
			String method = CommonBusiness.JSONheadStr2Obj(head).getMethod();
			if(method==null){
				//���ܱ�����򷵻ؿ�,��ӦҲΪ��
				return null;
			}else {
			String className =InitServlet.commonConfig.getString(method) ;
			AbsBusiness busi= (AbsBusiness)ObjectFactory.createFactory(className);
			busi.setRequest(request);
			busi.setPathSufix(pathSufix);
			result = busi.doBusiness(requestModel);
		  }
		} catch (Exception ex) {
			log.error("businessServe �쳣 = ", ex);
		}
		return result;
	}
}
