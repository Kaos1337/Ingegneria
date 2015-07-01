package it.univr.is.database;

public class ParametroSQL {
	
	private final String sValue;
	private final int iValue;
	private final boolean isString;

	
	ParametroSQL(String s){
		sValue = s;
		isString = true;
		iValue = 0;
	}
	
	ParametroSQL(int i){
		iValue = i;
		isString = false;
		sValue = null;
	}
	
	public String getStringValue() throws Exception {
		if(isString) return sValue;
		else throw new Exception();
	}
	
	public int getIntValue() throws Exception {
		if(! isString) return iValue;
		else throw new Exception();
	}
	
	public boolean isString(){
		return isString;
	}
	
}
