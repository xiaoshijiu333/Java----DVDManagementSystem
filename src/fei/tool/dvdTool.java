package fei.tool;

public class dvdTool {
	//�ж��ַ����Ƿ�������
	public static boolean isNumber(String str){
		for(int i=str.length()-1;i>=0;i--){
			if(!Character.isDigit(str.charAt(i))){
				return false;
			}
		}
		return true;
	}
}
