#ifndef NETWORKCOMMS_H
#define NETWORKCOMMS_H
#include <QNetworkAccessManager>
#include <QAuthenticator>

class NetworkComms : public QObject
{
    Q_OBJECT
public:
    NetworkComms(QObject* pParent = nullptr);
    ~NetworkComms();
    Q_INVOKABLE void createAccount(const QString& firstName, const QString& lastName, const QString& email, const QString& password);
    Q_INVOKABLE void login(const QString& email, const QString& password);
    Q_INVOKABLE void logout();
    Q_INVOKABLE void addNewCar(const QString& licencePlate, const QString&  carModel, const QString& releaseYear);


signals:
    void communicationStarted();
    void communicationFinished();
    void reportError(QString strError);
    void accountCreated();
    void successfulSignIn(QString role);
    void successfulLogOut();


private slots:
    void slotReplyFinished(QNetworkReply *pReply);

private:
    void reportErrorToUser(QNetworkReply *pReply);
    void addAuthHeader(QNetworkRequest& request);
    void sendRequest(const QString& strPath, bool bAuth = false, bool bNotifyStart = true);
    void sendDelete(const QString& strPath, bool bAuth = false, bool bNotifyStart = true);
    void sendPostOrPut(const QString& strPath, const QByteArray& body, bool bPost, bool bAuth = false, bool bNotifyStart = true);
    void handleCreateAccountResponse(QNetworkReply *pReply);
    void handleLoginResponse(QNetworkReply *pReply);
    void handleAboutMeResponse(QNetworkReply *pReply);
    void handleLogoutResponse(QNetworkReply *pReply);

    //Note: can not handle array as value
    QString getJsonValue(const QString& sampleText, const QString& key, int idx, const QString& afterKey = "");
    QString cutBeginAndEndQuotes(const QString& sampleText);

    QNetworkAccessManager* m_pNetManager;
    QAuthenticator m_authenticator;
    int m_userId;
    QString m_baseUrl;
    QString m_authPath;
    QString m_loginPath;
    QString m_logoutPath;
    QList<QPair<QString, QNetworkReply*>> m_listReplys;
};

#endif // NETWORKCOMMS_H
