package Architecture.gen_java.TestCase_general;

import java.util.*;

public class TypeWrapper<T> {
private T value;
public TypeWrapper() {
}
public TypeWrapper(T value) {
	this.value = value;
}
public T getValue() {
return this.value;
	}
	public void setValue(T value) {
this.value = value;
	}
	}


