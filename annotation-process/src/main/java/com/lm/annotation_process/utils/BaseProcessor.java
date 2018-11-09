package com.lm.annotation_process.utils;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeVariableName;

import java.io.IOException;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;

/**
 * @Author WWC
 * @Create 2018/8/30
 * @Description BaseProcessor
 */
public abstract class BaseProcessor extends AbstractProcessor {

    protected Filer filer;
    protected Types typeUtils;
    protected Elements elementUtils;
    protected Messager messager;

    private boolean hasProcess;

    private boolean isLog = true;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        filer = processingEnv.getFiler();
        typeUtils = processingEnv.getTypeUtils();
        elementUtils = processingEnv.getElementUtils();
        messager = processingEnv.getMessager();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (hasProcess) return false;
        onProcess(annotations, roundEnv);
        hasProcess = true;
        return false;
    }

    protected abstract void onProcess(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv);

    //======================================================================================
    // 一些辅助方法
    //======================================================================================

    /**
     * 对要处理的注解根据类进行排列
     * 例如 MainActivity OtherActivity中都有字段注解了@Provide
     * 那么排列的结果就是
     * Map
     * |- key=MainActivity  value=List
     * |- key=OtherActivity value=list
     *
     * @param roundEnv   RoundEnvironment
     * @param annotation 要处理的注解
     * @return 根据类排列的结果
     */
    public Map<TypeElement, List<Element>> sortElementFromRoundEnv(RoundEnvironment roundEnv, Class<? extends Annotation> annotation) {
        Map<TypeElement, List<Element>> sortResultMap = new HashMap<>();
        for (Element element : roundEnv.getElementsAnnotatedWith(annotation)) {
            Element rootElement = element.getEnclosingElement();
            if (rootElement == null || rootElement.getKind() != ElementKind.CLASS) {
                continue;
            }
            List<Element> childElementList = sortResultMap.get(rootElement);
            if (childElementList == null) {
                childElementList = new ArrayList<>();
                sortResultMap.put((TypeElement) rootElement, childElementList);
            }
            childElementList.add(element);
        }
        return sortResultMap;
    }

    /**
     * 例如获取泛型T
     * 那么代码可以这么写
     * TypeVariableName T = getTypeVariableName("T");
     *
     * @param name
     * @return
     */
    public TypeVariableName getTypeVariableName(String name) {
        return TypeVariableName.get(name);
    }

    public ClassName getClassName(Class<?> clazz) {
        return ClassName.get(clazz);
    }

    /**
     * 例如获取Set<String>这种Type
     * 那么代码可以这么写
     * getParameterizedTypeName(Set.class,String.class)
     *
     * @param rawType
     * @param typeArguments
     * @return
     */
    public ParameterizedTypeName getParameterizedTypeName(Class<?> rawType, Type... typeArguments) {
        return ParameterizedTypeName.get(rawType, typeArguments);
    }

    public ParameterizedTypeName getParameterizedTypeName(Class<?> clazz, TypeName... typeNames) {
        return ParameterizedTypeName.get(getClassName(clazz), typeNames);
    }

    public ParameterizedTypeName getParameterizedTypeName(ClassName className, TypeName... typeNames) {
        return ParameterizedTypeName.get(className, typeNames);
    }

    public ParameterizedTypeName getParameterizedTypeName(Class<?> clazz, Element... elements) {
        TypeName[] typeNames = new TypeName[elements.length];
        for (int i = 0; i < typeNames.length; i++) {
            typeNames[i] = getTypeName(elements[i]);
        }
        return ParameterizedTypeName.get(getClassName(clazz), typeNames);
    }

    /**
     * 获取注解元素的类型
     * 例如String str;这段代码被注解了
     * 那么获取的结果就是
     * String
     *
     * @param element
     * @return
     */
    public TypeName getTypeName(Element element) {
        return TypeName.get(typeUtils.erasure(element.asType()));
    }

    public boolean isElementEquals(Element element, Class<?> clazz) {
        return getTypeName(element).toString().equals(clazz.getName());
    }

    /**
     * 获取注解元素的外围元素类型
     * 例如MainActivity中有个字段String str;被注解了
     * 那么获取的结果就是
     * MainActivity
     *
     * @param element
     * @return
     */
    public TypeName getEnclosingTypeName(Element element) {
        return getTypeName(element.getEnclosingElement());
    }

    /**
     * 获取包名
     *
     * @param element
     * @return
     */
    public String getPackageName(Element element) {
        return elementUtils.getPackageOf(element).toString();
    }

    /**
     * 获取注解对象
     */
    public <A extends Annotation> A getAnnotation(Element element, Class<A> annotation) {
        return element.getAnnotation(annotation);
    }

    /**
     * 写入.class文件
     *
     * @param packageName  包名
     * @param className    类名
     * @param classBuilder
     */
    public void writeClassFile(String packageName, String className, TypeSpec.Builder classBuilder) {
        try {
            Writer writer = filer.createSourceFile(packageName + "." + className).openWriter();
            JavaFile.builder(packageName, classBuilder.build()).build().writeTo(writer);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //======================================================================
    // 日志相关
    //======================================================================
    public void logNote(CharSequence msg) {
        log(Diagnostic.Kind.NOTE, msg);
    }

    public void logWarning(CharSequence msg) {
        log(Diagnostic.Kind.WARNING, msg);
    }

    public void logError(CharSequence msg) {
        log(Diagnostic.Kind.ERROR, msg);
    }

    private void log(Diagnostic.Kind kind, CharSequence msg) {
        if (isLog) messager.printMessage(kind, msg);
    }
}
