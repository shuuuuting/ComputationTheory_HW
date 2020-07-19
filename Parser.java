import java.util.*;
import java.io.IOException;
import java.io.Reader;
import static java.util.Objects.requireNonNull;
/* Rule:
<EXPR> -> <EXPR> + <TERM> | <TERM>
<TERM> -> <TERM> * <FACTOR> | <FACTOR>
<FACTOR> -> ( <EXPR> ) | a | b | c
*/
public class Parser{
	public static int idx = 0;
	public static String inputstring;
	public static Dictionary<String, Double> dict = new Hashtable<String, Double>();
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("input[ For Example:(a+b)*c ]:");
		String calculate = input.next();
		System.out.print("a=");
		Double a = Double.parseDouble(input.next());
		dict.put("a",a);
		System.out.print("b=");
		Double b = Double.parseDouble(input.next());
		dict.put("b",b);
		System.out.print("c=");
		Double c = Double.parseDouble(input.next());
		dict.put("c",c);
		inputstring = calculate;
		double cal = calculate();
		System.out.println("result:"+String.valueOf(cal));
	}
	/*private static parse(String calculate){
		Stack operation = new Stack();
		Stack num = new Stack();
		int idx = 0;
		String now = calculate.charAt(idx);
		return now;
		for(int i=0 ; i<calculate.length() ; i++){
			if(!Character.isDigit(calculate.charAt(i))){
				operation.push(calculate.charAt(i));
			}
			else{
				num.push(calculate.charAt(i));	
			}		
		}
		System.out.println(Arrays.toString(operation.toArray()));
		System.out.println(Arrays.toString(num.toArray())); 
	}*/
	public static String now(){
		return Character.toString(inputstring.charAt(idx));
	}
	public static double calculate(){
		return EXPR();
	}
	private static double EXPR(){
		double value = TERM();
		String now = now();
		while(now.equals("+")){
			idx+=1;
			value = value + TERM();
			now = now();
		} 
		return value;
	}
	private static double TERM(){
		double value = FACTOR();
		String now = now();
		while(now.equals("*")){
			idx+=1;
			value = value * FACTOR();
			now = now();
		}
		return value;
	}
	private static double FACTOR(){
		String now = now();
		double value = 0;
		if(now.equals("(")){
			idx+=1;
			value = EXPR();
			now = now();
			if(now.equals(")")){
				if(idx<inputstring.length()-1){
					idx+=1;
				}
			}
		}else{
			try{
				value = dict.get(now);
				if(idx<inputstring.length()-1){
					idx+=1;}
			}catch(NumberFormatException e){
				System.out.println("數值轉換錯誤");
			}
		}
		return value;
	}
}
