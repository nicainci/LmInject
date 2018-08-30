package com.lm.annotation_process.inject;

import com.lm.annotation_process.utils.BaseProcessor;

import java.util.Set;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;

/**
 * @Author WWC
 * @Create 2018/8/30
 * @Description 处理@Inject注解
 */
public class InjectProcessor extends BaseProcessor {

    @Override
    protected void onProcess(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

    }

}
