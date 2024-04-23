package com.pcwk.ehr.cmn;

import java.util.UUID;

public class StringUtil {

	/**
	 *  32bit UUID 생성
	 * @return String
	 */
	public static String getUUID() {
		String resultUUID = "";
		UUID uuidTemp = UUID.randomUUID();
		
		return uuidTemp.toString().replaceAll("-", "");
	}
	
}
