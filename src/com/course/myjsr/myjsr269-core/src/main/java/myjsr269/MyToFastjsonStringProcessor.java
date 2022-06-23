package myjsr269;

import com.squareup.javapoet.*;
import lombok.SneakyThrows;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.Writer;
import java.util.List;
import java.util.Set;

/**
 * Annotation Processor for @ToFastjsonString (Using for Bean)
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-06-21 11:29:36
 */
@SupportedAnnotationTypes(value = {"myjsr269.ToFastjsonString"})
@SupportedSourceVersion(value = SourceVersion.RELEASE_8)
public class MyToFastjsonStringProcessor extends AbstractProcessor {

    private static final String ANNOTATION_TARGET = "ToFastjsonString";

    @SneakyThrows
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        boolean ans = false;
        super.processingEnv.getMessager().printMessage(Diagnostic.Kind.WARNING, "roundEnv started...");
        for (Element typeElement : roundEnv.getElementsAnnotatedWith(ToFastjsonString.class)) {
            if (!typeElement.getKind().isClass()) {
                super.processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE,
                        "ToFastjsonString not the annotation class target, typeElement = " + typeElement.getSimpleName() +
                                ", ANNOTATION_TARGET = " + ANNOTATION_TARGET + ", typeElement.getKind() = " + typeElement.getKind());
                continue;
            }
            ans = true;
            super.processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE,
                    "ToFastjsonString comes..., typeElement = " + typeElement.getSimpleName() +
                            ", ANNOTATION_TARGET = " + ANNOTATION_TARGET + ", typeElement.getKind() = " + typeElement.getKind());

            final String fullClassName = ((TypeElement) typeElement).getQualifiedName().toString();

            TypeSpec.Builder typeSpecBuilder = TypeSpec.classBuilder(typeElement.getSimpleName().toString() + "1")
                    .addModifiers(Modifier.PUBLIC);

            List<? extends Element> subElements = typeElement.getEnclosedElements();
            for (Element e : subElements) {
                if (ElementKind.FIELD == e.getKind()) {
                    FieldSpec.Builder f = FieldSpec.builder(TypeName.get(e.asType()), e.getSimpleName().toString());
                    for (Modifier m : e.getModifiers()) {
                        f.addModifiers(m);
                    }
                    typeSpecBuilder.addField(f.build());
                }
            }

            ClassName jsonObject = ClassName.get("com.alibaba.fastjson", "JSONObject");
            typeSpecBuilder.addMethod(MethodSpec.methodBuilder("toFastjsonString")
                    .addModifiers(Modifier.PUBLIC)
                    .addStatement("return $T.toJSONString(this)", jsonObject)
                    .returns(String.class).build());

            try {
                JavaFileObject fileObject = super.processingEnv.getFiler().createSourceFile(fullClassName + "1", typeElement);
                Writer w = fileObject.openWriter();
                JavaFile.builder(super.processingEnv.getElementUtils().getPackageOf(typeElement).getQualifiedName().toString(), typeSpecBuilder.build())
                        .build().writeTo(w);
                w.flush();
                w.close();
                super.processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "write " + fullClassName + " end...");
            } catch (Exception e) {
                super.processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, e.getMessage(), typeElement);
            }
        }

        return ans;
    }
}
