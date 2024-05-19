#include <QByteArray>
#include <QNetworkReply>
#include <QRegularExpression>
#include <QDirIterator>
#include <QJsonObject>
#include <QJsonDocument>
#include "networkaccess.h"
#include <QUrl>
#include <QDateTime>
#include <QGuiApplication>
#include <QQmlApplicationEngine>


NetworkAccess::NetworkAccess(QObject* pParent) : QObject(pParent)
{
    basePath = "http://localhost:8889";
    registerPath = "/users/register";
    loginPath = "/users/login";
    logoutPath = "/logout";
    carsPath = "/cars";
    bookingsPath = "/bookings";


    network_access_manager = new QNetworkAccessManager(pParent);
    network_access_manager->connectToHost("localhost", 8889);
}

NetworkAccess::~NetworkAccess()
{
    if(network_access_manager)
    {
        delete network_access_manager;
    }
}

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

void NetworkAccess::login(const QString& email, const QString& password)
{
    QJsonObject body {
        {"email", email},
        {"password", password}

    };
    QJsonDocument doc(body);
    QByteArray postBody = doc.toJson();
    user_pwd = password;
    user_email = email;

    QNetworkRequest request;
    request.setUrl(QUrl(basePath+loginPath));
    request.setHeader( QNetworkRequest::ContentTypeHeader, "application/json" );
    QString auth = QString("%1:%2").arg(email, password);
    QByteArray base64EncodedHeader(auth.toUtf8().toBase64());
    QByteArray authHeader = "Basic " + base64EncodedHeader;
    request.setRawHeader("Authorization", authHeader);
    QNetworkReply *reply = network_access_manager->post(request, postBody);
    connect(reply, &QNetworkReply::finished, this, [this, reply]() {
        loginReplyFinished(reply);
    });
}

void NetworkAccess::loginReplyFinished(QNetworkReply *reply)
{
    if (reply->error() == QNetworkReply::NoError) {
        qDebug() << "Network connection successful" ;

        QJsonDocument responseDoc = QJsonDocument::fromJson(reply->readAll());
        QJsonObject responseObject = responseDoc.object();
        QString profile = responseObject["role"].toString();
        user_id = responseObject["user_id"].toInt();

        if(profile == "OWNER"){
            emit loginSuccesfulOwner();
        }
        else {
            emit loginSuccesfulRenter();
        }

    } else {
        qDebug() << "Network error:" << reply->errorString();
        user_email = "";
        user_pwd = "";

    }

    reply->deleteLater();
}

void NetworkAccess::logout()
{
    QNetworkRequest request;
    request.setUrl(QUrl(basePath+logoutPath));

    QNetworkReply *reply = network_access_manager->get(request);
    connect(reply, &QNetworkReply::finished, this, [this, reply]() {
        logoutReplyFinished(reply);
    });
}

void NetworkAccess::logoutReplyFinished(QNetworkReply *reply)
{
    if (reply->error() == QNetworkReply::NoError) {
        qDebug() << "Logout successful";

        // Emit the signal to indicate successful logout
        user_id = 0;
        user_email = "";
        user_pwd="";
        cars.clear();
        emit logoutSuccesful();

    } else {
        qDebug() << "Logout error:" << reply->errorString();

    }

    reply->deleteLater();
}

void NetworkAccess::fetchCarsForRent() {
    QUrl url(basePath + carsPath +"/available");
    QNetworkRequest request(url);
    QString auth = QString("%1:%2").arg(user_email, user_pwd);
    QByteArray base64EncodedHeader(auth.toUtf8().toBase64());
    QByteArray authHeader = "Basic " + base64EncodedHeader;
    request.setRawHeader("Authorization", authHeader);
    QNetworkReply *reply= network_access_manager->get(request);
    connect(reply, &QNetworkReply::finished, this, [this, reply]() {
        fetchCarsForRentReplyFinished(reply);
    });
}


void NetworkAccess::fetchCarsForRentReplyFinished(QNetworkReply *reply)
{
    if (reply->error() == QNetworkReply::NoError) {
        QByteArray data = reply->readAll();
        QJsonDocument doc = QJsonDocument::fromJson(data);
        if(doc.isArray()){
            QJsonArray jsonArray = doc.array();
            for (const QJsonValue &value : jsonArray) {
                QJsonObject obj = value.toObject();
                emit carsForRentFetched(obj["model"].toString(), obj["location"].toString(), obj["price"].toDouble(),
                        obj["owner"].toString(), obj["licensePlate"].toString(), obj["releaseYear"].toInt());
            }
        }
        else{
            qDebug() << "Problem with QJsonDocument not containing an array";
        }

    } else {
        qDebug() << "Network error:" << reply->errorString();
    }

    reply->deleteLater();
}

void NetworkAccess::fetchCarsForOwner() {
    QUrl url(basePath + carsPath+ "/user/" +(QString::number(user_id)));
    QNetworkRequest request(url);
    QString auth = QString("%1:%2").arg(user_email, user_pwd);
    QByteArray base64EncodedHeader(auth.toUtf8().toBase64());
    QByteArray authHeader = "Basic " + base64EncodedHeader;
    request.setRawHeader("Authorization", authHeader);
    QNetworkReply *reply= network_access_manager->get(request);
    connect(reply, &QNetworkReply::finished, this, [this, reply]() {
        fetchCarsForOwnerReplyFinished(reply);
    });
}

void NetworkAccess::fetchCarsForOwnerReplyFinished(QNetworkReply *reply){
    if (reply->error() == QNetworkReply::NoError) {
        QByteArray data = reply->readAll();
        QJsonDocument doc = QJsonDocument::fromJson(data);
        if(doc.isArray()){
            QJsonArray jsonArray = doc.array();
            for (const QJsonValue &value : jsonArray) {
                QJsonObject obj = value.toObject();
                emit carsForOwnerFetched(obj["model"].toString(), obj["location"].toString(), obj["price"].toDouble(),
                                        obj["owner"].toString(), obj["licensePlate"].toString(), obj["releaseYear"].toInt());
            }
        }
        else{
            qDebug() << "Problem with QJsonDocument not containing an array";
        }

    } else {
        qDebug() << "Network error:" << reply->errorString();
    }

    reply->deleteLater();
}

void NetworkAccess::addNewCar(const QString& licence_plate, const QString& model, int release_year, double price_for_day, const QString address, double gps_x, double gps_y)
{
    QJsonObject body {
        {"licensePlate", licence_plate},
        {"model", model},
        {"releaseYear", release_year},
        {"price", price_for_day},
        {"location", QJsonObject{
                         {"address", address},
                         {"gps_height", gps_x},
                         {"gps_width", gps_y}
                     }},
        {"owner", QJsonObject{
                     {"user_id", user_id}
                 }}
    };

    QJsonDocument doc(body);
    QByteArray postBody = doc.toJson();

    QNetworkRequest request;
    request.setUrl(QUrl(basePath+carsPath));
    request.setHeader( QNetworkRequest::ContentTypeHeader, "application/json" );
    QString auth = QString("%1:%2").arg(user_email, user_pwd);
    QByteArray base64EncodedHeader(auth.toUtf8().toBase64());
    QByteArray authHeader = "Basic " + base64EncodedHeader;
    request.setRawHeader("Authorization", authHeader);
    QNetworkReply *reply = network_access_manager->post(request, postBody);
    connect(reply, &QNetworkReply::finished, this, [this, reply]() {
        addCarReplyFinished(reply);
    });
}

void NetworkAccess::addCarReplyFinished(QNetworkReply *reply)
{
    if (reply->error() == QNetworkReply::NoError)
    {
        qDebug() << "Network connection successful" ;
        emit carAdded();

    }
    else
    {
        qDebug() << "Network error:" << reply->errorString();
    }
    // Clean up the reply object
    reply->deleteLater();
}

void NetworkAccess::deleteCar(const QString& licensePlate)
{
    QNetworkRequest request;
    request.setUrl(QUrl(basePath+carsPath+ "/car/"+licensePlate));
    request.setHeader( QNetworkRequest::ContentTypeHeader, "application/json" );
    QString auth = QString("%1:%2").arg(user_email, user_pwd);
    QByteArray base64EncodedHeader(auth.toUtf8().toBase64());
    QByteArray authHeader = "Basic " + base64EncodedHeader;
    request.setRawHeader("Authorization", authHeader);
    QNetworkReply *reply = network_access_manager->deleteResource(request);
    connect(reply, &QNetworkReply::finished, this, [this, reply]() {
        carDeletedReplyFinished(reply);
    });
}

void NetworkAccess::carDeletedReplyFinished(QNetworkReply *reply)
{
    if (reply->error() == QNetworkReply::NoError)
    {
        qDebug() << "Network connection successful" ;
        emit carDeleted();

    }
    else
    {
        qDebug() << "Network error:" << reply->errorString();
    }
    // Clean up the reply object
    reply->deleteLater();
}

void NetworkAccess::makeBooking(const QString licensePlate)
{
    QJsonObject body {
        {"car", QJsonObject{{"licensePlate", licensePlate}}},
        {"user", QJsonObject{{"user_id", user_id}}}
    };

    QJsonDocument doc(body);
    QByteArray postBody = doc.toJson();

    QNetworkRequest request;
    request.setUrl(QUrl(basePath+bookingsPath));
    request.setHeader( QNetworkRequest::ContentTypeHeader, "application/json" );
    QString auth = QString("%1:%2").arg(user_email, user_pwd);
    QByteArray base64EncodedHeader(auth.toUtf8().toBase64());
    QByteArray authHeader = "Basic " + base64EncodedHeader;
    request.setRawHeader("Authorization", authHeader);
    QNetworkReply *reply = network_access_manager->post(request, postBody);
    connect(reply, &QNetworkReply::finished, this, [this, reply]() {
        addBookingReplyFinished(reply);
    });
}

void NetworkAccess::addBookingReplyFinished(QNetworkReply *reply)
{
    if (reply->error() == QNetworkReply::NoError)
    {
        qDebug() << "Network connection successful" ;
        emit bookingSent();

    }
    else
    {
        qDebug() << "Network error:" << reply->errorString();
    }
    // Clean up the reply object
    reply->deleteLater();
}

void NetworkAccess::fetchBookings()
{
    QUrl url(basePath + bookingsPath+ "/users/" +(QString::number(user_id)));
    QNetworkRequest request(url);
    QString auth = QString("%1:%2").arg(user_email, user_pwd);
    QByteArray base64EncodedHeader(auth.toUtf8().toBase64());
    QByteArray authHeader = "Basic " + base64EncodedHeader;
    request.setRawHeader("Authorization", authHeader);
    QNetworkReply *reply= network_access_manager->get(request);
    connect(reply, &QNetworkReply::finished, this, [this, reply]() {
        fetchBookingsFinished(reply);
    });
}

void NetworkAccess::fetchBookingsFinished(QNetworkReply *reply)
{
    if (reply->error() == QNetworkReply::NoError) {
        QByteArray data = reply->readAll();
        QJsonDocument doc = QJsonDocument::fromJson(data);
        if(doc.isArray()){
            QJsonArray jsonArray = doc.array();
            for (const QJsonValue &value : jsonArray) {
                QJsonObject obj = value.toObject();

                double price = obj["car"].toObject()["price"].toDouble();
                if(obj["end_time"].isNull()){
                    emit bookingsFetchedGoing(obj["bookingId"].toInt(), obj["car"].toObject()["licensePlate"].toString(), obj["start_time"].toString(), price);
                }
                else{
                    if(obj["review"].isNull())
                        emit bookingsFetchedEnded(obj["bookingId"].toInt(), obj["car"].toObject()["licensePlate"].toString(), obj["start_time"].toString(), obj["end_time"].toString(), price, true);
                    else{
                        emit bookingsFetchedEnded(obj["bookingId"].toInt(), obj["car"].toObject()["licensePlate"].toString(), obj["start_time"].toString(), obj["end_time"].toString(), price, false);
                    }

                }
            }
        }
        else{
            qDebug() << "Problem with QJsonDocument not containing an array";
        }

    } else {
        qDebug() << "Network error:" << reply->errorString();
    }

    reply->deleteLater();
}

void NetworkAccess::endBooking(int bookingNumber)
{
    QJsonObject body {
        {"bookingId", bookingNumber},
        {"user", QJsonObject{{"user_id", user_id}}}
    };

    QJsonDocument doc(body);
    QByteArray postBody = doc.toJson();
    QNetworkRequest request;
    request.setUrl(QUrl(basePath+bookingsPath + "/stop"));
    request.setHeader( QNetworkRequest::ContentTypeHeader, "application/json" );
    QString auth = QString("%1:%2").arg(user_email, user_pwd);
    QByteArray base64EncodedHeader(auth.toUtf8().toBase64());
    QByteArray authHeader = "Basic " + base64EncodedHeader;
    request.setRawHeader("Authorization", authHeader);
    QNetworkReply *reply = network_access_manager->put(request, postBody);
    connect(reply, &QNetworkReply::finished, this, [this, reply]() {
        endBookingReply(reply);
    });
}

void NetworkAccess::endBookingReply(QNetworkReply *reply)
{
    if (reply->error() == QNetworkReply::NoError)
    {
        qDebug() << "Network connection successful" ;
        emit endBookingFinished();

    }
    else
    {
        qDebug() << "Network error:" << reply->errorString();
    }
    // Clean up the reply object
    reply->deleteLater();
}

void NetworkAccess::clickedOnReview(int bookingId)
{
    bookingReviewId = bookingId;
}



void NetworkAccess::addNewBooking(const QString& reviewRating, const QString& reviewText)
{
    int rating = reviewRating.toInt();
    QJsonObject body {
        {"rating", rating},
        {"comment", reviewText},
        {"author", QJsonObject{{"user_id", user_id}}},
        {"booking", QJsonObject{{"bookingId", bookingReviewId}}}
    };

    QJsonDocument doc(body);
    QByteArray postBody = doc.toJson();

    QNetworkRequest request;
    request.setUrl(QUrl(basePath+"/reviewratings"));
    request.setHeader( QNetworkRequest::ContentTypeHeader, "application/json" );
    QString auth = QString("%1:%2").arg(user_email, user_pwd);
    QByteArray base64EncodedHeader(auth.toUtf8().toBase64());
    QByteArray authHeader = "Basic " + base64EncodedHeader;
    request.setRawHeader("Authorization", authHeader);
    QNetworkReply *reply = network_access_manager->post(request, postBody);
    connect(reply, &QNetworkReply::finished, this, [this, reply]() {
        addNewReviewReplyFinished(reply);
    });
}

void NetworkAccess::addNewReviewReplyFinished(QNetworkReply *reply)
{
    if (reply->error() == QNetworkReply::NoError)
    {
        qDebug() << "Network connection successful" ;
        emit endAddReviewFinished();

    }
    else
    {
        qDebug() << "Network error:" << reply->errorString();
    }
    // Clean up the reply object
    reply->deleteLater();
}

void NetworkAccess::backFromReview()
{
    bookingReviewId = -1;
}

void NetworkAccess::fetchStatistics(const QString& carId)
{
    QUrl url(basePath + "/reviewratings/car/" +carId);
    QNetworkRequest request(url);
    QString auth = QString("%1:%2").arg(user_email, user_pwd);
    QByteArray base64EncodedHeader(auth.toUtf8().toBase64());
    QByteArray authHeader = "Basic " + base64EncodedHeader;
    request.setRawHeader("Authorization", authHeader);
    QNetworkReply *reply= network_access_manager->get(request);
    connect(reply, &QNetworkReply::finished, this, [this, reply]() {
        fetchStatisticsFinished(reply);
    });
}

void NetworkAccess::fetchStatisticsFinished(QNetworkReply *reply)
{
    int ratings_1 =0;
    int ratings_2 =0;
    int ratings_3 =0;
    int ratings_4 =0;
    int ratings_5 =0;

    if (reply->error() == QNetworkReply::NoError) {
        QByteArray data = reply->readAll();
        QJsonDocument doc = QJsonDocument::fromJson(data);
        if(doc.isArray()){
            QJsonArray jsonArray = doc.array();
            for (const QJsonValue &value : jsonArray) {
                QJsonObject obj = value.toObject();
                if(obj["rating"]==1){
                    ratings_1++;
                }
                else if(obj["rating"]==2){
                    ratings_2++;
                }
                else if(obj["rating"]==3){
                    ratings_3++;
                }
                else if(obj["rating"]==4){
                    ratings_4++;
                }
                else if(obj["rating"]==5){
                    ratings_5++;
                }
            }
            emit ratingsFetchingFinished(ratings_1, ratings_2, ratings_3, ratings_4, ratings_5);
        }
        else{
            qDebug() << "Problem with QJsonDocument not containing an array";
        }

    } else {
        qDebug() << "Network error:" << reply->errorString();
    }

    reply->deleteLater();

}


QList<Car> NetworkAccess::getCars() const {
    return cars;
}
