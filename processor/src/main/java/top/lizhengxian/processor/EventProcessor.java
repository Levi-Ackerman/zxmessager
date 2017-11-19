package top.lizhengxian.processor;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

import top.lizhengxian.event_lib.DescriptionInfo;
import top.lizhengxian.event_lib.IContacts;
import top.lizhengxian.event_lib.anno.Subscribe;

import static top.lizhengxian.processor.EventProcessor.SUBSCRIBE;

@AutoService(Processor.class)
@SupportedAnnotationTypes(SUBSCRIBE)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class EventProcessor extends AbstractProcessor {
    static final String SUBSCRIBE = "top.lizhengxian.event_lib.anno.Subscribe";
    private static final String INDEX_PACKAGE = "IndexPackage";
    public static final String FIELD_MAP = "mMap";
    public static final String GENERATE_CLASS_NAME = "Contacts";

    private String mPackageName;
    private Set<DescriptionInfo> mDescs = new HashSet<>();

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        mPackageName = processingEnv.getOptions().getOrDefault(INDEX_PACKAGE, null);
        if (mPackageName == null || "".equals(mPackageName.trim())) {
            throw new RuntimeException("IndexPackage can't be null");
        }
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        annotations.parallelStream()
                .filter(anno -> SUBSCRIBE.equals(anno.toString()))
                .flatMap(subscribeAnno -> roundEnv.getElementsAnnotatedWith(subscribeAnno).parallelStream())  //find all annotation methods
                .map(method -> (ExecutableElement) method)
                .forEach(this::initDesc);

        FieldSpec map = FieldSpec.builder(ParameterizedTypeName.get(HashMap.class, Integer.class, DescriptionInfo.class), FIELD_MAP)
                .addModifiers(Modifier.PRIVATE)
                .build();
        MethodSpec.Builder constructorBuilder = MethodSpec.constructorBuilder().addModifiers(Modifier.PUBLIC);
        constructorBuilder.addCode(FIELD_MAP + " = new HashMap<>();\n");
        mDescs.forEach(desc ->
                constructorBuilder.addStatement(String.format(Locale.US, "%s.put(%d,new %s(%d,%d,\"%s\",\"%s\",\"%s\"))",
                        FIELD_MAP, desc.id, DescriptionInfo.class.getCanonicalName(), desc.id, desc.threadType, desc.className, desc.methodName, desc.paramName)));


        MethodSpec getContactMapMethod = MethodSpec.methodBuilder("getContactsMap")
                .addAnnotation(Override.class)
                .addModifiers(Modifier.PUBLIC)
                .returns(ParameterizedTypeName.get(Map.class, Integer.class, DescriptionInfo.class))
                .addCode("return " + FIELD_MAP + ";\n").build();
        TypeSpec indexClass = TypeSpec.classBuilder(GENERATE_CLASS_NAME)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addField(map)
                .addSuperinterface(IContacts.class)
                .addMethod(constructorBuilder.build())
                .addMethod(getContactMapMethod)
                .build();
        JavaFile file = JavaFile.builder(mPackageName, indexClass).build();
        try {
            file.writeTo(processingEnv.getFiler());
        } catch (IOException e) {
//            e.printStackTrace();
        }

        return true;
    }

    /**
     * fill description object with method information
     *
     * @param method
     */
    private void initDesc(ExecutableElement method) {
        List<? extends VariableElement> params = method.getParameters();
        String paramName;
        if (params.size() == 0) {
            paramName = "";
        } else if (params.size() == 1) {
            paramName = params.get(0).asType().toString();
        } else {
            throw new RuntimeException("@Subscribe can only use for method with none or only 1 parameter");
        }
        Subscribe subscribe = method.getAnnotation(Subscribe.class);
        DescriptionInfo desc = new DescriptionInfo();
        desc.paramName = paramName;
        desc.id = subscribe.id();
        desc.threadType = subscribe.thread();
        desc.className = method.getEnclosingElement().asType().toString();
        desc.methodName = method.getSimpleName().toString();
        mDescs.add(desc);
    }
}
