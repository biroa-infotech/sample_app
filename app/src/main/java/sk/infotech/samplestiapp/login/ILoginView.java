package sk.infotech.samplestiapp.login;

/**
 * Created by MacMini on 25/01/16.
 */
public interface ILoginView {
    void wrongUsername(int errorType);
    void wrongPassword(int errorType);
    void goToMainScreen();

}
