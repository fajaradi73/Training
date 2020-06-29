package id.fajarproject.training.feature.login;

/**
 * Create by Fajar Adi Prasetyo on 24/06/2020.
 */
public interface LoginView {
    void showSuccessLogin();
    void showErrorLogin();
    void showErrorUserName();
    void showErrorPassword();
    void setAction();
    void showLoading(boolean isShow);
    void setEnableUI(boolean isEnable);
    void showDialog(String message);
}
