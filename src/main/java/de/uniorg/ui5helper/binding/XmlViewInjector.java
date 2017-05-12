package de.uniorg.ui5helper.binding;

import com.intellij.lang.injection.MultiHostInjector;
import com.intellij.lang.injection.MultiHostRegistrar;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLanguageInjectionHost;
import com.intellij.psi.xml.XmlAttribute;
import com.intellij.psi.xml.XmlAttributeValue;
import de.uniorg.ui5helper.binding.lang.BindingLanguage;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class XmlViewInjector implements MultiHostInjector {
    @Override
    public void getLanguagesToInject(@NotNull MultiHostRegistrar multiHostRegistrar, @NotNull PsiElement psiElement) {
        final XmlAttribute attribute = (XmlAttribute) psiElement;
        XmlAttributeValue value = attribute.getValueElement();
        if (value == null) {
            return;
        }

        TextRange range = attribute.getValueTextRange();

        if (value.getValue().contains("{") && value.getValue().contains("}")) {
            multiHostRegistrar.startInjecting(BindingLanguage.INSTANCE);
            multiHostRegistrar.addPlace("", "", (PsiLanguageInjectionHost) value, range);
            multiHostRegistrar.doneInjecting();
        }

    }

    @NotNull
    @Override
    public List<? extends Class<? extends PsiElement>> elementsToInjectIn() {
        return Arrays.asList(XmlAttribute.class);
    }
}