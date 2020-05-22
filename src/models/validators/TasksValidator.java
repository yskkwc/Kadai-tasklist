package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Tasks;

public class TasksValidator {

    public static List<String> validate(Tasks tl) {
        List<String> errors = new ArrayList<String>();

        String content_error = _validateContents(tl.getContents());
        if (!content_error.equals("")) {
            errors.add(content_error);
        }

        return errors;
    }

    // "contents"の必須入力チェック
    private static String _validateContents(String content) {
        if (content == null || content.equals("")) {
            return "・内容を入力してください。";
        }

        return "";
    }
}