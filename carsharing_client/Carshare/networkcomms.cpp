#include <QByteArray>
#include <QNetworkReply>
#include <QRegularExpression>
#include <QDirIterator>
#include <QJsonObject>
#include <QJsonDocument>
#include "networkcomms.h"

NetworkComms::NetworkComms(QObject* pParent) : QObject(pParent), m_userId(-1)
{
    QString host = "localhost";
    m_baseUrl = "http://" + host + ":8081/api/v1/smarthome";
    m_authPath = "/auth/signup";
    m_loginPath = "/auth/login";
    m_logoutPath = "/auth/logout";

    m_pNetManager = new QNetworkAccessManager(pParent);
    m_pNetManager->connectToHost(host, 8081);
    bool bConnected = QObject::connect(m_pNetManager, &QNetworkAccessManager::finished,
            this, &NetworkComms::slotReplyFinished);

    Q_ASSERT_X(bConnected, "NetworkCommunication", "can not connect signal QNetworkAccessManager::finished - slot NetworkCommunication::slotReplyFinished");

}

NetworkComms::~NetworkComms()
{
    if(m_pNetManager)
    {
        delete m_pNetManager;
    }
}


void NetworkComms::slotReplyFinished(QNetworkReply *pReply)
{
    if(pReply)
    {
        for(qsizetype  i=0; i<m_listReplys.size(); i++)
        {
            if(m_listReplys.at(i).second == pReply)
            {
                if(pReply->request().url().path().endsWith(m_authPath))
                {
                    emit communicationFinished();
                    handleCreateAccountResponse(pReply);
                }
                else if(pReply->request().url().path().endsWith(m_loginPath))
                {
                    emit communicationFinished();
                    handleLoginResponse(pReply);
                }
                else if(pReply->request().url().path().endsWith(m_logoutPath))
                {
                    emit communicationFinished();
                    handleLogoutResponse(pReply);
                }
                m_listReplys.removeAt(i);
            }
        }

    }
}

void NetworkComms::reportErrorToUser(QNetworkReply *pReply)
{
    if(pReply)
    {
        QByteArray byteArrayResponse(pReply->readAll());
        QString strResponse(QString::fromUtf8(byteArrayResponse));
        emit reportError("Operation unsuccessful" + (strResponse.isEmpty() ? "" : (": " + strResponse)));
    }
}

void NetworkComms::addAuthHeader(QNetworkRequest& request)
{
    QString credentialsString = QString("%1:%2").arg(m_authenticator.user(), m_authenticator.password());
    QByteArray base64Encoded(credentialsString.toUtf8().toBase64());
    QByteArray headerData = "Basic " + base64Encoded;
    request.setRawHeader("Authorization", headerData);
}

void NetworkComms::sendRequest(const QString& strPath, bool bAuth, bool bNotifyStart)
{
    if(bNotifyStart)
    {
        emit communicationStarted();
    }
    QNetworkRequest request;
    request.setUrl(QUrl(m_baseUrl+strPath));
    request.setTransferTimeout();
    if(bAuth)
    {
        addAuthHeader(request);
    }
    m_listReplys.append(qMakePair("GET", m_pNetManager->get(request)));
}

void NetworkComms::sendDelete(const QString& strPath, bool bAuth, bool bNotifyStart)
{
    if(bNotifyStart)
    {
        emit communicationStarted();
    }
    QNetworkRequest request;
    request.setUrl(QUrl(m_baseUrl+strPath));
    request.setTransferTimeout();
    if(bAuth)
    {
        addAuthHeader(request);
    }
    m_listReplys.append(qMakePair("DEL", m_pNetManager->deleteResource(request)));
}

void NetworkComms::sendPostOrPut(const QString& strPath, const QByteArray& body, bool bPost, bool bAuth, bool bNotifyStart)
{
    if(bNotifyStart)
    {
        emit communicationStarted();
    }
    QNetworkRequest request;
    request.setUrl(QUrl(m_baseUrl+strPath));
    request.setHeader(QNetworkRequest::ContentTypeHeader, "application/json");
    request.setTransferTimeout();
    if(bAuth)
    {
        addAuthHeader(request);
    }

    if(bPost)
    {
        m_listReplys.append(qMakePair("POST", m_pNetManager->post(request, body)));
    }
    else
    {
        m_listReplys.append(qMakePair("PUT", m_pNetManager->put(request, body)));
    }
}

void NetworkComms::createAccount(const QString& firstName, const QString& lastName, const QString& email, const QString& password)
{
    QByteArray body("{\"firstName\":\"" + firstName.toUtf8() + "\",\"lastName\":\"" + lastName.toUtf8() + "\",\"email\":\"" +
                             email.toUtf8() + "\",\"password\":\"" + password.toUtf8() + "\"}");
    sendPostOrPut(m_authPath, body, true);
}

void NetworkComms::handleCreateAccountResponse(QNetworkReply *pReply)
{
    if(pReply)
    {
        QNetworkReply::NetworkError err = pReply->error();
        if(err == QNetworkReply::NoError)
        {
            emit accountCreated();
        }
        else
        {
            reportErrorToUser(pReply);
        }
    }
}

void NetworkComms::login(const QString& email, const QString& password)
{
    QByteArray body("{\"email\":\"" + email.toUtf8() + "\",\"password\":\"" + password.toUtf8() + "\"}");
    m_authenticator.setUser(email);
    m_authenticator.setPassword(password);
    sendPostOrPut(m_loginPath, body, true);
}

void NetworkComms::handleLoginResponse(QNetworkReply *pReply)
{
    if(pReply)
    {
        QNetworkReply::NetworkError err = pReply->error();
        if(err == QNetworkReply::NoError)
        {
            // TODO gather role from pReply and change the param accordingly
            emit successfulSignIn("renter");
        }
        else
        {
            reportErrorToUser(pReply);
        }
    }
}

void NetworkComms::logout()
{
    sendRequest(m_logoutPath);
}

void NetworkComms::handleLogoutResponse(QNetworkReply *pReply)
{
    if(pReply)
    {
        QNetworkReply::NetworkError err = pReply->error();
        if(err == QNetworkReply::NoError)
        {
            emit successfulLogOut();
        }
        else
        {
            reportErrorToUser(pReply);
        }
    }
}

void NetworkComms::addNewCar(const QString& licencePlate, const QString&  carModel, const QString& releaseYear)
{

}
