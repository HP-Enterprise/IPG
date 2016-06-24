package com.cmos.ipg.dubbo;

import com.cmos.core.bean.InputObject;
import com.cmos.core.bean.OutputObject;

/**
 * 
 * 
 */
public interface IControlIPGService {
	/**
	 * Call WebService Unified Method
	 * 
	 * @param inputObject
	 * @return OutputObject
	 */
	OutputObject execute(InputObject inputObject);
}
