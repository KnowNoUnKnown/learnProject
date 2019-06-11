package com.oracle.xing.load.compile;

import javax.tools.*;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class JCompiler {

    /**
     * 编译 .java 内容为 .class内容
     *
     * @param javaName
     * @param javaSrc
     * @return
     * @throws Exception
     */
    public static Map<String, byte[]> compiler(String javaName, String javaSrc, String content) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
        StandardJavaFileManager stdManager = compiler.getStandardFileManager(diagnostics, null, null);
        try (MemoryJavaFileManager manager = new MemoryJavaFileManager(stdManager)) {
            JavaFileObject javaFileObject = MemoryJavaFileManager.makeStringSource(javaName, javaSrc);
            JavaCompiler.CompilationTask task = compiler.getTask(null, manager, null, null, null, Arrays.asList(javaFileObject));
            if (task.call()) {
                return manager.getClassBytes();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String, byte[]> compiler(String javaName, String javaSrc) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        JavaFileObject file = new JavaSource(javaName, javaSrc);

        Iterable<? extends JavaFileObject> compilationUnits = Arrays.asList(file);
        JavaCompiler.CompilationTask task = compiler.getTask(null, null, diagnostics, null, null, compilationUnits);

        boolean result = task.call();
        for (Diagnostic diagnostic : diagnostics.getDiagnostics()) {
            System.out.println(diagnostic.getCode());
            System.out.println(diagnostic.getKind());
            System.out.println(diagnostic.getPosition());
            System.out.println(diagnostic.getStartPosition());
            System.out.println(diagnostic.getEndPosition());
            System.out.println(diagnostic.getSource());
            System.out.println(diagnostic.getMessage(null));
        }
        if (result) {
            try {
                ByteArrayOutputStream outputStream = (ByteArrayOutputStream) file.openOutputStream();
                Map<String, byte[]> resultMap = new HashMap<>();
                resultMap.put(javaName, outputStream.toByteArray());
                return resultMap;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new HashMap<>();
    }
}
