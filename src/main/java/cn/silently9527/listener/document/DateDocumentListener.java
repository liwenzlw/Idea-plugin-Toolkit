package cn.silently9527.listener.document;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.URLUtil;
import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.event.DocumentListener;
import com.intellij.ui.EditorTextField;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

public class DateDocumentListener implements DocumentListener {
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    private EditorTextField textField;
    private EditorTextField localTimeTextField;
    private EditorTextField localDateTextField;
    private EditorTextField secondTextField;
    private EditorTextField millisTextField;
    private EditorTextField utcTimeTextField;

    public DateDocumentListener(EditorTextField textField,
                                EditorTextField localTimeTextField,
                                EditorTextField localDateTextField,
                                EditorTextField secondTextField,
                                EditorTextField millisTextField,
                                EditorTextField utcTimeTextField) {
        this.textField = textField;
        this.localTimeTextField = localTimeTextField;
        this.localDateTextField = localDateTextField;
        this.secondTextField = secondTextField;
        this.millisTextField = millisTextField;
        this.utcTimeTextField = utcTimeTextField;
    }

    @Override
    public void documentChanged(@NotNull DocumentEvent event) {
        String text = this.textField.getText();
        if (StringUtils.isBlank(text)) {
            return;
        }
        try {
            DateTime dateTime = DateUtil.parse(text, DATE_TIME_FORMAT);

            this.localTimeTextField.setText(dateTime.toString(DATE_TIME_FORMAT));
            this.localDateTextField.setText(dateTime.toString(DATE_FORMAT));
            this.secondTextField.setText(String.valueOf(dateTime.getTime() / 1000));
            this.millisTextField.setText(String.valueOf(dateTime.getTime()));
            this.utcTimeTextField.setText(DateUtil.offsetHour(dateTime, -8).toString(DATE_TIME_FORMAT));
        } catch (Exception ignored) {
            this.localTimeTextField.setText("");
            this.localDateTextField.setText("");
            this.secondTextField.setText("");
            this.millisTextField.setText("");
            this.utcTimeTextField.setText("");
        }

    }

}
