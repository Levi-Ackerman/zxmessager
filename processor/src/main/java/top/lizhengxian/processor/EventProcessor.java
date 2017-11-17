package top.lizhengxian.processor;

import com.google.auto.service.AutoService;

import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import top.lizhengxian.event_lib.DescriptionInfo;
import top.lizhengxian.event_lib.Subscribe;

import static top.lizhengxian.processor.EventProcessor.SUBSCRIBE;

@AutoService(Processor.class)
@SupportedAnnotationTypes(SUBSCRIBE)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class EventProcessor extends AbstractProcessor {
    static final String SUBSCRIBE = "top.lizhengxian.event_lib.Subscribe";
    private Elements mElemUtil;
    private Types mTypeUtil;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        mElemUtil = processingEnv.getElementUtils();
        mTypeUtil = processingEnv.getTypeUtils();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        annotations.parallelStream()
                .filter(anno -> SUBSCRIBE.equals(anno.toString()))
                .flatMap(subscribeAnno -> roundEnv.getElementsAnnotatedWith(subscribeAnno).parallelStream())  //find all annotation methods
                .map(method -> (ExecutableElement) method)
                .forEach(method -> {
                    List<? extends VariableElement> params = method.getParameters();
                    if (params.size()!=1){
                        throw new RuntimeException("@Subscribe can only use for method with only 1 parameter");
                    }
                    DescriptionInfo desc = new DescriptionInfo();
                    desc.paramName = params.get(0).asType().toString();
                    desc.id = method.getAnnotation(Subscribe.class).value();
                    desc.className = method.getEnclosingElement().asType().toString();
                    desc.methodName = method.getSimpleName().toString();
                    System.out.println(desc);
                });
        return true;
    }
}
