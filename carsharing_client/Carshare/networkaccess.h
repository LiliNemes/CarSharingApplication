#ifndef NETWORKACCESS_H
#define NETWORKACCESS_H

#include <QNetworkAccessManager>
//#include <QAuthenticator>

class NetworkAccess : public QObject
{
    Q_OBJECT
public:
    NetworkAccess(QObject* pParent = nullptr);
    ~NetworkAccess();
    //Q_INVOKABLE void createAccount(const QString& firstName, const QString& lastName, const QString& email, const QString& password);
    //Q_INVOKABLE void login(const QString& email, const QString& password);
    //Q_INVOKABLE void logout();
    //Q_INVOKABLE void addNewCar(const QString& licencePlate, const QString&  carModel, const QString& releaseYear);


signals:
    void registrationResponse(bool success, const QString& errorMessage);
    void registrationSuccesful();
    /*
    void communicationStarted();
    void communicationFinished();
    void reportError(QString strError);
    void accountCreated();
    void successfulSignIn(QString role);
    void successfulLogOut();
*/

public slots:
    //void login(const QString& email, const QString& password);
    void createAccount(const QString& email, const QString& password, const bool& type);
    void getAll();




 private slots:
    void registrationReplyFinished(QNetworkReply *reply);
    //void onRequestFinished();
    void getAllReplyFinished(QNetworkReply *reply);




private:
    /*
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
    */

    QNetworkAccessManager *network_access_manager;
    QNetworkReply *network_reply = nullptr;
    QString basePath;
    QString registerPath;
    QString loginPath;
    QString logoutPath;
    //QAuthenticator m_authenticator;
    /*
    int m_userId;
    QString m_baseUrl;
    QString m_authPath;
    QString m_loginPath;
    QString m_logoutPath;
    QList<QPair<QString, QNetworkReply*>> m_listReplys;
*/
};

#endif // NETWORKACCESS_H
