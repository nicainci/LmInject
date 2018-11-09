package com.lm.annotation_process.provide;

import com.google.auto.service.AutoService;
import com.lm.annotation.provide.Provide;
import com.lm.annotation.provide.Provider;
import com.lm.annotation.provide.Providers;
import com.lm.annotation_process.utils.BaseProcessor;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
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

    private String mPackage;

    private String mClassName = "ProvidersInitClass";

    @Override
    protected void onProcess(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        Map<TypeElement, List<Element>> classMap = sortElementFromRoundEnv(roundEnv, Provide.class);

        TypeSpec.Builder providersInitClassBuilder = TypeSpec.classBuilder(mClassName)
                .addModifiers(Modifier.PUBLIC)
                .addModifiers(Modifier.FINAL);

        MethodSpec.Builder providersInitClassInitMethod = MethodSpec.methodBuilder("init")
                .addModifiers(Modifier.PUBLIC)
                .addModifiers(Modifier.STATIC)
                .addModifiers(Modifier.FINAL);

        for (Map.Entry<TypeElement, List<Element>> entry : classMap.entrySet()) {
            if (mPackage == null) {
                mPackage = getPackageName(entry.getKey());
            }
            TypeName[] typeNames = buildProviderClass(entry);
            providersInitClassInitMethod.addStatement("$T.put($T.class, new $T())", Providers.class, typeNames[0], typeNames[1]);
        }

        providersInitClassBuilder.addMethod(providersInitClassInitMethod.build());
        writeClassFile(mPackage, mClassName, providersInitClassBuilder);

        logNote("START 自动生成ProvidersInitClass " + mPackage + " " + mClassName);
        logNote("\n" + providersInitClassBuilder.build().toString());
        logNote("END 自动生成ProvidersInitClass " + mPackage + " " + mClassName);
    }

    private TypeName[] buildProviderClass(Map.Entry<TypeElement, List<Element>> entry) {
        TypeElement rootClassTypeElement = entry.getKey();
        String className = rootClassTypeElement.getSimpleName() + "Provider";
        String packageName = getPackageName(rootClassTypeElement);

        logNote("START 自动生成Provider " + packageName + " " + className);

        MethodSpec.Builder getObjectMethodBuilder = MethodSpec.methodBuilder("get")
                .addAnnotation(Override.class)
                .addModifiers(Modifier.PUBLIC)
                .addTypeVariable(getTypeVariableName("R"))
                .returns(getTypeVariableName("R"))
                .addParameter(getTypeName(rootClassTypeElement), "providerObject")
                .addParameter(String.class, "provideName");

        MethodSpec.Builder getIntMethodBuilder = MethodSpec.methodBuilder("getInt")
                .addAnnotation(Override.class)
                .addModifiers(Modifier.PUBLIC)
                .returns(int.class)
                .addParameter(getTypeName(rootClassTypeElement), "providerObject")
                .addParameter(String.class, "provideName");

        MethodSpec.Builder getLongMethodBuilder = MethodSpec.methodBuilder("getLong")
                .addAnnotation(Override.class)
                .addModifiers(Modifier.PUBLIC)
                .returns(long.class)
                .addParameter(getTypeName(rootClassTypeElement), "providerObject")
                .addParameter(String.class, "provideName");

        MethodSpec.Builder getCharMethodBuilder = MethodSpec.methodBuilder("getChar")
                .addAnnotation(Override.class)
                .addModifiers(Modifier.PUBLIC)
                .returns(char.class)
                .addParameter(getTypeName(rootClassTypeElement), "providerObject")
                .addParameter(String.class, "provideName");

        MethodSpec.Builder getByteMethodBuilder = MethodSpec.methodBuilder("getByte")
                .addAnnotation(Override.class)
                .addModifiers(Modifier.PUBLIC)
                .returns(byte.class)
                .addParameter(getTypeName(rootClassTypeElement), "providerObject")
                .addParameter(String.class, "provideName");

        MethodSpec.Builder getBooleanMethodBuilder = MethodSpec.methodBuilder("getBoolean")
                .addAnnotation(Override.class)
                .addModifiers(Modifier.PUBLIC)
                .returns(boolean.class)
                .addParameter(getTypeName(rootClassTypeElement), "providerObject")
                .addParameter(String.class, "provideName");

        MethodSpec.Builder getFloatMethodBuilder = MethodSpec.methodBuilder("getFloat")
                .addAnnotation(Override.class)
                .addModifiers(Modifier.PUBLIC)
                .returns(float.class)
                .addParameter(getTypeName(rootClassTypeElement), "providerObject")
                .addParameter(String.class, "provideName");

        MethodSpec.Builder getDoubleMethodBuilder = MethodSpec.methodBuilder("getDouble")
                .addAnnotation(Override.class)
                .addModifiers(Modifier.PUBLIC)
                .returns(double.class)
                .addParameter(getTypeName(rootClassTypeElement), "providerObject")
                .addParameter(String.class, "provideName");

        for (Element provideElement : entry.getValue()) {
            String provideName = getAnnotation(provideElement, Provide.class).provideName();
            String provideElementName = provideElement.toString();
            if (isElementEquals(provideElement, int.class)) {
                getIntMethodBuilder
                        .beginControlFlow("if ($S.equals(provideName))", provideName)
                        .addStatement("return providerObject.$L", provideElementName)
                        .endControlFlow();
            } else if (isElementEquals(provideElement, long.class)) {
                getLongMethodBuilder
                        .beginControlFlow("if ($S.equals(provideName))", provideName)
                        .addStatement("return providerObject.$L", provideElementName)
                        .endControlFlow();
            } else if (isElementEquals(provideElement, char.class)) {
                getCharMethodBuilder
                        .beginControlFlow("if ($S.equals(provideName))", provideName)
                        .addStatement("return providerObject.$L", provideElementName)
                        .endControlFlow();
            } else if (isElementEquals(provideElement, byte.class)) {
                getByteMethodBuilder
                        .beginControlFlow("if ($S.equals(provideName))", provideName)
                        .addStatement("return providerObject.$L", provideElementName)
                        .endControlFlow();
            } else if (isElementEquals(provideElement, boolean.class)) {
                getBooleanMethodBuilder
                        .beginControlFlow("if ($S.equals(provideName))", provideName)
                        .addStatement("return providerObject.$L", provideElementName)
                        .endControlFlow();
            } else if (isElementEquals(provideElement, float.class)) {
                getFloatMethodBuilder
                        .beginControlFlow("if ($S.equals(provideName))", provideName)
                        .addStatement("return providerObject.$L", provideElementName)
                        .endControlFlow();
            } else if (isElementEquals(provideElement, double.class)) {
                getDoubleMethodBuilder
                        .beginControlFlow("if ($S.equals(provideName))", provideName)
                        .addStatement("return providerObject.$L", provideElementName)
                        .endControlFlow();
            } else {
                getObjectMethodBuilder
                        .beginControlFlow("if ($S.equals(provideName))", provideName)
                        .addStatement("return (R) providerObject.$L", provideElementName)
                        .endControlFlow();
            }

        }
        getObjectMethodBuilder.addStatement("return null");
        getIntMethodBuilder.addStatement("return 0");
        getLongMethodBuilder.addStatement("return 0");
        getCharMethodBuilder.addStatement("return 0");
        getByteMethodBuilder.addStatement("return 0");
        getBooleanMethodBuilder.addStatement("return false");
        getFloatMethodBuilder.addStatement("return 0");
        getDoubleMethodBuilder.addStatement("return 0");

        TypeSpec.Builder classBuilder = TypeSpec.classBuilder(className)
                .addModifiers(Modifier.PUBLIC)
                .addMethod(getObjectMethodBuilder.build())
                .addMethod(getIntMethodBuilder.build())
                .addMethod(getLongMethodBuilder.build())
                .addMethod(getCharMethodBuilder.build())
                .addMethod(getByteMethodBuilder.build())
                .addMethod(getBooleanMethodBuilder.build())
                .addMethod(getFloatMethodBuilder.build())
                .addMethod(getDoubleMethodBuilder.build())
                .addSuperinterface(getParameterizedTypeName(Provider.class, rootClassTypeElement));

        writeClassFile(packageName, className, classBuilder);

        logNote("\n" + classBuilder.build().toString());
        logNote("END 自动生成Provider " + packageName + " " + className);

        TypeName[] resultTypeName = new TypeName[2];
        resultTypeName[0] = getTypeName(rootClassTypeElement);
        resultTypeName[1] = getTypeVariableName(className);
        return resultTypeName;
    }

}
