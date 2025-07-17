package com.weavus.weavus_bankweb.controller.error;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String errorCode = "";
        String errorMessage = "";

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                errorCode = "404 Not Found";
                errorMessage = "お探しのページが見つかりませんでした。URLを確認してください。";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                errorCode = "500 Internal Server Error";
                errorMessage = "サーバー内部でエラーが発生しました。しばらくしてから再度お試しください。";
            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                errorCode = "403 Forbidden";
                errorMessage = "このページにアクセスする権限がありません。";
            } else if (statusCode == HttpStatus.UNAUTHORIZED.value()) {
                errorCode = "401 Unauthorized";
                errorMessage = "認証が必要です。ログインページに移動してください。";
            } else {
                errorCode = statusCode + " Error";
                errorMessage = "予期せぬエラーが発生しました。";
            }
        } else {
            errorCode = "エラー";
            errorMessage = "エラーが発生しました。ホームページに戻ってください。";
        }

        model.addAttribute("errorCode", errorCode);
        model.addAttribute("errorMessage", errorMessage);

        return "/error/error";
    }
}
