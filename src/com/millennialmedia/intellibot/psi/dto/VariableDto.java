package com.millennialmedia.intellibot.psi.dto;

import com.intellij.psi.PsiElement;
import com.millennialmedia.intellibot.psi.element.DefinedVariable;
import com.millennialmedia.intellibot.psi.util.PatternUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.regex.Pattern;

/**
 * @author mrubino
 * @since 2014-06-18
 */
public class VariableDto implements DefinedVariable {

    private PsiElement reference;
    private String name;
    private Pattern pattern;

    public VariableDto(@NotNull PsiElement reference, @NotNull String name) {
        this.reference = reference;
        this.name = name.trim();
    }

    @Override
    public boolean matches(String text) {
        if (text == null) {
            return false;
        }
        Pattern pattern = this.pattern;
        if (pattern == null) {
            pattern = Pattern.compile(PatternUtil.getVariablePattern(this.name), Pattern.CASE_INSENSITIVE);
            this.pattern = pattern;
        }
        return pattern.matcher(text).matches();
    }

    @Nullable
    @Override
    public PsiElement reference() {
        return this.reference;
    }
}
