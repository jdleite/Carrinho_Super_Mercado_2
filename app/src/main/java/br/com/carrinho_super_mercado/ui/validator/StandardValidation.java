package br.com.carrinho_super_mercado.ui.validator;

import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class StandardValidation implements ValidationProduct {

    private static final String CAMPO_OBRIGATORIO = "campo obrigatório";
    private final TextInputLayout textInputLayout;
    private final EditText editText;


    public StandardValidation(TextInputLayout textInputLayout) {
        this.textInputLayout = textInputLayout;
        this.editText = this.textInputLayout.getEditText();
    }

    private boolean validateFields() {
        String edt = editText.getText().toString();
        if (edt.isEmpty()) {
            textInputLayout.setError(CAMPO_OBRIGATORIO);
            return false;
        }
        return true;
    }

    @Override
    public boolean isValid() {
        if (!validateFields()) return false;
        removeError();
        return true;
    }

    private void removeError() {
        textInputLayout.setError(null);
        textInputLayout.setErrorEnabled(false);
    }
}
