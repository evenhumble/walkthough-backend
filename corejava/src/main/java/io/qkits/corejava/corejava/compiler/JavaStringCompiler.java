package io.qkits.corejava.corejava.compiler;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Map;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

/**
 * @author: patrick on 2019-07-07
 * @Description:
 */
public class JavaStringCompiler {

  JavaCompiler compiler;
  StandardJavaFileManager stdManager;


  public JavaStringCompiler() {
    this.compiler = ToolProvider.getSystemJavaCompiler();
    this.stdManager = compiler.
        getStandardFileManager(null, null, Charset.defaultCharset());
  }

  public Map<String, byte[]> compile(String fileName, String source) throws IOException {
    try (MemoryJavaFileManager manager = new MemoryJavaFileManager(stdManager)) {
      JavaFileObject javaFileObject = manager.makeStringSource(fileName, source);
      JavaCompiler.CompilationTask
          task = compiler.getTask(null, manager, null, null, null, Arrays.asList(javaFileObject));
      Boolean result = task.call();
      if (result == null || !result) {
        throw new RuntimeException("Compilation failed.");
      }
      return manager.getClassBytes();
    }
  }
  public Class<?> loadClass(String name, Map<String, byte[]> classBytes) throws ClassNotFoundException, IOException {
    try (MemoryClassLoader classLoader = new MemoryClassLoader(classBytes)) {
      return classLoader.loadClass(name);
    }
  }
}
