package com.gft.dataservice.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
public class CalculationService {    
	
@Autowired
private com.gft.dataservice.services.MFXService mfxservice;



	        @RequestMapping(value = "/calculate/{formula}", method = RequestMethod.GET)
	        public String getCalculatedResult(@PathVariable String formula) {
	            return mfxservice.calculate(formula);
	        }

	        @RequestMapping(value = "/calculate", method = RequestMethod.POST)
	        @ResponseBody
	        public String getCalculatedPostResult(@RequestBody String formula) {
	        	String result = "";
	        	try{
	        		result = mfxservice.calculate(formula);
	        	}catch(Exception e){
	        	}
	            return result;
	        }
	        
	        @RequestMapping(value = "/result", method = RequestMethod.GET)
	        public String getResult() {
	        	String formula = "Result=(2+2)";
	        	String result = "NULL";
	        	try{
	        		result = mfxservice.calculate(formula);
	        	}catch(Exception e){
	        		result = "Error in calculation";
	        	}
	            return result;
	        }
}

