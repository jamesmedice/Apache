package com.gft.dataservice.services;

import java.util.Hashtable;

 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MFXService {
	
	private final Logger log = LoggerFactory.getLogger(MFXService.class);
		
    public String calculate(String input){
//    	MFX mfx = new MFX(null, "127.0.0.1:3306", "samp2", "root", "root", "MFX");
    	String[] formulas = input.split("\n");
    	String lastFormula = formulas != null ?formulas[formulas.length-1]:null ;
    	String[] lastFormulaSplitted = lastFormula != null ? lastFormula.split("="):null;
    	String resultKey= lastFormulaSplitted != null && lastFormulaSplitted.length>1? lastFormulaSplitted[0]:null;
		if (resultKey == null) {
			resultKey = "result";
			input = resultKey + "=" + input;
		}
		log.debug("stand result: " + resultKey); 
		
		Hashtable<String, String> result = null;
		if (result == null) {
			return "Input was null";
		}
		return result.get(resultKey);
	}
}
