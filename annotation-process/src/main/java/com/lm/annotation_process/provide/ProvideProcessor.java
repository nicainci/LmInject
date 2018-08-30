package com.lm.annotation_process.provide;

import com.google.auto.service.AutoService;
import com.lm.annotation_process.utils.BaseProcessor;

import java.util.Set;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

/**
 * @Author WWC
 * @Create 2018/8/30
 * @Description 处理@Provide注解
 */
@SupportedAnnotationTypes("com.lm.annotation.provide.Provide")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(ProvideProcessor.class)
public class ProvideProcessor extends BaseProcessor {

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);

    }

    @Override
    protected void onProcess(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

    }
}
