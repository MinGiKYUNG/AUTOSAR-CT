package Architecture.gen_java.TestCase_standardTypes;

import java.util.*;

public class Std_ReturnType extends TypeWrapper<Integer> {
private String literalText = null;
private static Map<String, Std_ReturnType> literalTextMap = new HashMap<String, Std_ReturnType>();
public final static Std_ReturnType E_OK = new Std_ReturnType(
	SymbolDefinitions.E_OK, SymbolDefinitions.E_OK.getLiteralText());
public final static Std_ReturnType E_NOT_OK = new Std_ReturnType(
SymbolDefinitions.E_NOT_OK, SymbolDefinitions.E_NOT_OK
	.getLiteralText());
public Std_ReturnType(SymbolDefinitions value, String literalText) {
	super(value.getValue());
	this.literalText = literalText;
	literalTextMap.put(literalText, this);
}
public Std_ReturnType(int value, String literalText) {
super(new Integer(value));
this.literalText = literalText;
literalTextMap.put(literalText, this);
}
public Std_ReturnType(int value) {
super(new Integer(value));
}
public String getLiteralText() {
return this.literalText;
}
public static Std_ReturnType getByLiteralText(String literalText) {
return literalTextMap.get(literalText);
	}
}


