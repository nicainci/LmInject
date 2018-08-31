package com.lm.annotation_process.inject;

import com.google.auto.service.AutoService;
import com.lm.annotation.inject.Inject;
import com.lm.annotation.inject.Injector;
import com.lm.annotation.provide.ProviderHelper;
import com.lm.annotation_process.utils.BaseProcessor;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
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
            TypeElement rootClassTypeElement = entry.getKey();
            String className = rootClassTypeElement.getSimpleName() + "Injector";
            String packageName = getPackageName(rootClassTypeElement);

            logNote("START 自动生成Injector" + packageName + " " + className);

            MethodSpec.Builder injectMethodBuilder = MethodSpec.methodBuilder("inject")
                    .addAnnotation(Override.class)
                    .addModifiers(Modifier.PUBLIC)
                    .addParameter(getTypeName(rootClassTypeElement), "injectTarget")
                    .addParameter(Object.class, "providerObject");

            for (Element injectElement : entry.getValue()) {
                String provideName = getAnnotation(injectElement, Inject.class).provideName();
                injectMethodBuilder.addStatement("injectTarget.$L = $T.get(providerObject, $S)", provideName, ProviderHelper.class, provideName);
            }

            TypeSpec.Builder classBuilder = TypeSpec.classBuilder(className)
                    .addModifiers(Modifier.PUBLIC)
                    .addSuperinterface(getParameterizedTypeName(Injector.class, rootClassTypeElement))
                    .addMethod(injectMethodBuilder.build());


            writeClassFile(packageName, className, classBuilder);

            logNote("\n" + classBuilder.build().toString());

            logNote("END 自动生成Injector" + packageName + " " + className);
        }

    }

}
