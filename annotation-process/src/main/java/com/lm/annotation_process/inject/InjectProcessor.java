package com.lm.annotation_process.inject;

import com.google.auto.service.AutoService;
import com.lm.annotation.inject.Inject;
import com.lm.annotation.inject.Injector;
import com.lm.annotation.provide.ProviderHelper;
import com.lm.annotation_process.utils.BaseProcessor;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

/**
 * @Author WWC
 * @Create 2018/8/30
 * @Description 处理@Inject注解
 */
@SupportedAnnotationTypes("com.lm.annotation.inject.Inject")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class InjectProcessor extends BaseProcessor {

    @Override
    protected void onProcess(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        Map<TypeElement, List<Element>> classMap = sortElementFromRoundEnv(roundEnv, Inject.class);

        for (Map.Entry<TypeElement, List<Element>> entry : classMap.entrySet()) {
            buildInjectorClass(entry);
        }
    }

    /**
     * 自动生成Injector
     *
     * @param entry key=MainActivity  value=List
     */
    private void buildInjectorClass(Map.Entry<TypeElement, List<Element>> entry) {

        TypeElement rootClassTypeElement = entry.getKey();
        String packageName = getPackageName(rootClassTypeElement);
        String className = rootClassTypeElement.getSimpleName() + "Injector";

        logNote("START 自动生成Injector " + packageName + " " + className);

        MethodSpec.Builder injectObjectMethodBuilder = MethodSpec.methodBuilder("inject")
                .addAnnotation(Override.class)
                .addModifiers(Modifier.PUBLIC)
                .addParameter(getTypeName(rootClassTypeElement), "injectTarget")
                .addParameter(Object.class, "providerObject");

        for (Element injectElement : entry.getValue()) {
            String provideName = getAnnotation(injectElement, Inject.class).provideName();
            String injectElementName = injectElement.toString();
            if (isElementEquals(injectElement, int.class)) {
                injectObjectMethodBuilder
                        .beginControlFlow("if (0 != $T.getInt(providerObject, $S)) ", ProviderHelper.class, provideName)
                        .addStatement("injectTarget.$L = $T.getInt(providerObject, $S)", injectElementName, ProviderHelper.class, provideName)
                        .endControlFlow();
            } else if (isElementEquals(injectElement, long.class)) {
                injectObjectMethodBuilder
                        .beginControlFlow("if (0 != $T.getLong(providerObject, $S)) ", ProviderHelper.class, provideName)
                        .addStatement("injectTarget.$L = $T.getLong(providerObject, $S)", injectElementName, ProviderHelper.class, provideName)
                        .endControlFlow();
            } else if (isElementEquals(injectElement, char.class)) {
                injectObjectMethodBuilder
                        .beginControlFlow("if (0 != $T.getChar(providerObject, $S)) ", ProviderHelper.class, provideName)
                        .addStatement("injectTarget.$L = $T.getChar(providerObject, $S)", injectElementName, ProviderHelper.class, provideName)
                        .endControlFlow();
            } else if (isElementEquals(injectElement, byte.class)) {
                injectObjectMethodBuilder
                        .beginControlFlow("if (0 != $T.getByte(providerObject, $S)) ", ProviderHelper.class, provideName)
                        .addStatement("injectTarget.$L = $T.getByte(providerObject, $S)", injectElementName, ProviderHelper.class, provideName)
                        .endControlFlow();
            } else if (isElementEquals(injectElement, boolean.class)) {
                injectObjectMethodBuilder
                        .beginControlFlow("if (false != $T.getBoolean(providerObject, $S)) ", ProviderHelper.class, provideName)
                        .addStatement("injectTarget.$L = $T.getBoolean(providerObject, $S)", injectElementName, ProviderHelper.class, provideName)
                        .endControlFlow();
            } else if (isElementEquals(injectElement, float.class)) {
                injectObjectMethodBuilder
                        .beginControlFlow("if (0 != $T.getFloat(providerObject, $S)) ", ProviderHelper.class, provideName)
                        .addStatement("injectTarget.$L = $T.getFloat(providerObject, $S)", injectElementName, ProviderHelper.class, provideName)
                        .endControlFlow();
            } else if (isElementEquals(injectElement, double.class)) {
                injectObjectMethodBuilder
                        .beginControlFlow("if (0 != $T.getDouble(providerObject, $S)) ", ProviderHelper.class, provideName)
                        .addStatement("injectTarget.$L = $T.getDouble(providerObject, $S)", injectElementName, ProviderHelper.class, provideName)
                        .endControlFlow();
            } else {
                injectObjectMethodBuilder
                        .beginControlFlow("if (null != $T.get(providerObject, $S)) ", ProviderHelper.class, provideName)
                        .addStatement("injectTarget.$L = $T.get(providerObject, $S)", injectElementName, ProviderHelper.class, provideName)
                        .endControlFlow();
            }
        }

        TypeSpec.Builder classBuilder = TypeSpec.classBuilder(className)
                .addModifiers(Modifier.PUBLIC)
                .addSuperinterface(getParameterizedTypeName(Injector.class, rootClassTypeElement))
                .addMethod(injectObjectMethodBuilder.build());

        writeClassFile(packageName, className, classBuilder);

        logNote("\n" + classBuilder.build().toString());

        logNote("END 自动生成Injector" + packageName + " " + className);
    }
}
