package com.cmos.ipg.dubbo;

import com.cmos.core.bean.InputObject;
import com.cmos.core.bean.OutputObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * 
 */
@Service
public class ControlServiceImpl implements IControlIPGService {
	@Autowired
	private com.cmos.core.service.IControlService coreControlService;

	public OutputObject execute(InputObject inputObject) {
		OutputObject outputObject = null;
		try {
			// 转换成统一出参
			outputObject = coreControlService.execute(inputObject);
		} catch (Exception e) {
			outputObject = new OutputObject();
//			outputObject.setReturnCode(RETURN_CODE.SYSTEM_ERROR);
			outputObject.setReturnMessage(e.getMessage() == null ? e.getCause().getMessage() : e.getMessage());
		}
		return outputObject;
	}

}
