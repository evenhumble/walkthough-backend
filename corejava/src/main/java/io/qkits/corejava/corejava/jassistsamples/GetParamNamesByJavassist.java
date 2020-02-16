package io.qkits.corejava.corejava.jassistsamples;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

public class GetParamNamesByJavassist {
    public static void main(String[] args) throws NotFoundException {
		ReadMethodParameter demo = new ReadMethodParameter();
		Method[] methods = demo.getClass().getDeclaredMethods();
		ClassPool pool = ClassPool.getDefault();
		pool.insertClassPath(new ClassClassPath(demo.getClass()));
		CtClass cc= pool.get(demo.getClass().getName());
		for (Method method : methods) {
			CtMethod cm = cc.getDeclaredMethod(method.getName());
			MethodInfo methodInfo = cm.getMethodInfo();
			CodeAttribute codeAttr = methodInfo.getCodeAttribute();
			LocalVariableAttribute lAttr = (LocalVariableAttribute) codeAttr.getAttribute(LocalVariableAttribute.tag);
			if (lAttr == null) {
				System.out.println("params is null");
			}
			String[] paramNames = new String[cm.getParameterTypes().length];
			int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
			for (int i = 0; i < paramNames.length; i++)
				paramNames[i] = lAttr.variableName(i + pos);
			for (int i = 0; i < paramNames.length; i++) {
				System.out.println(paramNames[i]);
			}
		}

		}

}