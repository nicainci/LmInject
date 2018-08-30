package com.lm.annotation_process.utils;

import com.squareup.javapoet.TypeName;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

/**
 * @Author WWC
 * @Create 2018/8/30
 * @Description BaseProcessor
 */
public abstract class BaseProcessor extends AbstractProcessor {

    private Filer filer;
    private Types typeUtils;
    private Elements elementUtils;
    private Messager messager;

    private boolean hasProcess;

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
}
