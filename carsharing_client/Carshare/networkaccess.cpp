#include <QByteArray>
#include <QNetworkReply>
#include <QRegularExpression>
#include <QDirIterator>
#include <QJsonObject>
#include <QJsonDocument>
#include "networkaccess.h"
#include <QUrl>


NetworkAccess::NetworkAccess(QObject* pParent) : QObject(pParent)
{
    basePath = "http://localhost:8889";
    registerPath = "/users/register";
    loginPath = "/login";
    logoutPath = "/logout";

    network_access_manager = new QNetworkAccessManager(pParent);
    network_access_manager->connectToHost("localhost", 8889);
    getAll();
    //bool bConnected = QObject::connect(network_access_manager, &QNetworkAccessManager::finished,
     //       this, &NetworkAccess::slotReplyFinished);

    //Q_ASSERT_X(bConnected, "NetworkCommunication", "can not connect signal QNetworkAccessManager::finished - slot NetworkCommunication::slotReplyFinished");

}

NetworkAccess::~NetworkAccess()
{
    if(network_access_manager)
    {
        delete network_access_manager;
    }
}
/*
void NetworkAccess::login(const QString& email, const QString& password)
{

}
*/

void NetworkAccess::createAccount(const QString& email, const QString& password, const bool& type)
{
    //Making the body of the POST method.
    QString usertype;
    if (type)
    {
        usertype="OWNER";
    }
    else
    {
        usertype = "RENTER";
    }

    QJsonObject body
    {
        {"email", email},
        {"password", password},
        {"userType", usertype}

    };

    QJsonDocument doc(body);
    QByteArray postBody = doc.toJson();

    QNetworkRequest request;
    request.setUrl(QUrl(basePath+registerPath));
    request.setHeader( QNetworkRequest::ContentTypeHeader, "application/json" );
    //QString auth = QString("%1:%2").arg(email, password);
    //QByteArray base64EncodedHeader(auth.toUtf8().toBase64());
    //QByteArray authHeader = "Basic " + base64EncodedHeader;
    //request.setRawHeader("Authorization", authHeader);
    QNetworkReply *reply = network_access_manager->post(request, postBody);
    connect(reply, &QNetworkReply::finished, this, [this, reply]() {
        registrationReplyFinished(reply);
    });
}

void NetworkAccess::registrationReplyFinished(QNetworkReply *reply)
{
    if (reply->error() == QNetworkReply::NoError)
    {
        qDebug() << "Network connection successful" ;
        emit registrationSuccesful();

    }
    else
    {
        qDebug() << "Network error:" << reply->errorString();
    }
    // Clean up the reply object
    reply->deleteLater();
}


void NetworkAccess::getAll()
{
    QNetworkRequest request;
    request.setUrl(QUrl(basePath+"/"));
    QNetworkReply *reply = network_access_manager->get(request);
    connect(reply, &QNetworkReply::finished, this, [this, reply]() {
        getAllReplyFinished(reply);
    });
}

void NetworkAccess::getAllReplyFinished(QNetworkReply *reply)
{
    if (reply->error() == QNetworkReply::NoError)
    {
        qDebug() << "Network connection successful" ;

    }
    else
    {
        qDebug() << "Network error:" << reply->errorString();
    }
    // Clean up the reply object
    reply->deleteLater();
}

/*
void NetworkAccess::slotReplyFinished(QNetworkReply *pReply)
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

}*/
