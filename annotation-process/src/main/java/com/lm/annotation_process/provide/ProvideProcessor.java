package com.lm.annotation_process.provide;

import com.google.auto.service.AutoService;
import com.lm.annotation.provide.Provide;
import com.lm.annotation.provide.Provider;
import com.lm.annotation_process.utils.BaseProcessor;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.lang.annotation.Annotation;
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
import javax.tools.Diagnostic;

/**
 * @Author WWC
 * @Create 2018/8/30
 * @Description 处理@Provide注解
 */
@SupportedAnnotationTypes("com.lm.annotation.provide.Provide")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class ProvideProcessor extends BaseProcessor {

    @Override
    protected void onProcess(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        Map<TypeElement, List<Element>> classMap = sortElementFromRoundEnv(roundEnv, Provide.class);
        for (Map.Entry<TypeElement, List<Element>> entry : classMap.entrySet()) {

            TypeElement rootClassTypeElement = entry.getKey();
            String className = rootClassTypeElement.getSimpleName() + "Provider";
            String packageName = getPackageName(rootClassTypeElement);

            logNote("START 自动生成Provider" + packageName + " " + className);

            MethodSpec.Builder getMethodBuilder = MethodSpec.methodBuilder("get")
                    .addAnnotation(Override.class)
                    .addModifiers(Modifier.PUBLIC)
                    .addTypeVariable(getTypeVariableName("R"))
                    .returns(getTypeVariableName("R"))
                    .addParameter(getTypeName(rootClassTypeElement), "providerObject")
                    .addParameter(String.class, "provideName");
            for (Element provideElement : entry.getValue()) {
                getMethodBuilder
                        .beginControlFlow("if ($S.equals(provideName))", getAnnotation(provideElement, Provide.class).provideName())
                        .addStatement("return (R) providerObject.$L", getAnnotation(provideElement, Provide.class).provideName())
                        .endControlFlow();
            }
            getMethodBuilder.addStatement("return null");


            TypeSpec.Builder classBuilder = TypeSpec.classBuilder(className)
                    .addModifiers(Modifier.PUBLIC)
                    .addMethod(getMethodBuilder.build())
                    .addSuperinterface(getParameterizedTypeName(Provider.class, rootClassTypeElement));

            writeClassFile(packageName, className, classBuilder);

            logNote("\n" + classBuilder.build().toString());
            logNote("END 自动生成Provider" + packageName + " " + className);
        }
    }


}
