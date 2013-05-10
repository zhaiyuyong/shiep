package cn.edu.shiep.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.edu.shiep.business.CommonBusiness;
import cn.edu.shiep.model.RequestModel;
import cn.edu.shiep.server.TotalService;

public class MainServlet extends HttpServlet {
	private static Logger log = Logger.getLogger(MainServlet.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doService(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doService(request, response);
			
	}

	public void doService(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/xml; charset=UTF-8");
		
//		Map<String,Object> map = request.getParameterMap();
//		for(Map.Entry<String, Object> entry : map.entrySet()){
//			System.out.println("key="+entry.getKey());
//			Object value = entry.getValue();
//			System.out.println("value="+value.toString());
//			if(value instanceof String[]){
//				String[] i = (String[])value;
//				System.out.println("length="+i.length);
//				System.out.println(i[0]);
//				for(String s:i){
//					System.out.println("s=="+s);
//				}
//			}
//		}
		//System.out.println(request.getParameter("method"));
		String rquestStr = CommonBusiness.getParamterStr(request);
		System.out.println("PAD�ͻ�������Σ�"+rquestStr);
		RequestModel requestModel = CommonBusiness.parseJsonString(rquestStr);
		System.out.println(requestModel);
		// �����̨����
		TotalService totalService = new TotalService();
		// ������Ӧ����
		String jsonStr = totalService.businessServe(request,requestModel);
		
		log.debug("������ӦPAD�ͻ��ˣ�"+rquestStr);
		Writer rWriter = response.getWriter();
		rWriter.write(jsonStr);
		rWriter.flush();
		rWriter.close();
	}

}
