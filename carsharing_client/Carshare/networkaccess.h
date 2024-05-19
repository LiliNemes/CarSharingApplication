#ifndef NETWORKACCESS_H
#define NETWORKACCESS_H

#include <QNetworkAccessManager>
#include "car.h"
#include <QJsonDocument>
#include <QJsonParseError>
#include <QJsonObject>
#include <QJsonValue>
#include <QJsonArray>

class NetworkAccess : public QObject
{
    Q_OBJECT
public:
    NetworkAccess(QObject* pParent = nullptr);
    ~NetworkAccess();
    QList<Car> getCars() const;
    QVariantList getVCars() const {
        return v_cars;
    }
    Q_INVOKABLE void clickedOnReview(int bookingId);
    Q_INVOKABLE void backFromReview();




signals:
    void registrationResponse(bool success, const QString& errorMessage);
    void registrationSuccesful();
    void loginSuccesfulOwner();
    void loginSuccesfulRenter();
    void logoutSuccesful();
    void carsForRentFetched(QString m_model, QString m_location, double m_price, QString m_owner, QString m_licensePlate, int m_releaseYear);
    void carsForOwnerFetched(QString m_model, QString m_location, double m_price, QString m_owner, QString m_licensePlate, int m_releaseYear);
    void carAdded();
    void carDeleted();
    void bookingsFetchedEnded(int bookingId, QString licensePlate, QString start_time, QString end_time, double price_per_day, bool isReviewed);
    void bookingsFetchedGoing(int bookingId, QString licensePlate, QString start_time, double price_per_day);
    void endBookingFinished();
    void endAddReviewFinished();
    void ratingsFetchingFinished(int one, int two, int three, int four, int five);
    void bookingSent();


public slots:
    void createAccount(const QString& email, const QString& password, const bool& type);
    void getAll();
    void login(const QString& email, const QString& password);
    void logout();
    void fetchCarsForRent();
    void fetchCarsForOwner();
    void addNewCar(const QString& licence_plate, const QString& model, int release_year, double price_for_day, const QString address, double gps_x, double gps_y);
    void deleteCar(const QString& licensePlate);
    void makeBooking(const QString licensePlate);
    void fetchBookings();
    void endBooking(int bookingNumber);
    void addNewBooking(const QString& reviewRating, const QString& reviewText);
    void fetchStatistics(const QString& carId);



 private slots:
    void registrationReplyFinished(QNetworkReply *reply);
    void getAllReplyFinished(QNetworkReply *reply);
    void loginReplyFinished(QNetworkReply *reply);
    void logoutReplyFinished(QNetworkReply *reply);
    void fetchCarsForRentReplyFinished(QNetworkReply *reply);
    void fetchCarsForOwnerReplyFinished(QNetworkReply *reply);
    void addCarReplyFinished(QNetworkReply *reply);
    void carDeletedReplyFinished(QNetworkReply *reply);
    void addBookingReplyFinished(QNetworkReply *reply);
    void fetchBookingsFinished(QNetworkReply *reply);
    void endBookingReply(QNetworkReply *reply);
    void addNewReviewReplyFinished(QNetworkReply *reply);
    void fetchStatisticsFinished(QNetworkReply *reply);






private:
    QNetworkAccessManager *network_access_manager;
    QNetworkReply *network_reply = nullptr;
    QString basePath;
    QString registerPath;
    QString loginPath;
    QString logoutPath;
    QString carsPath;
    QList<Car> cars;
    QVariantList v_cars;
    int user_id;
    QString user_email;
    QString user_pwd;
    QString bookingsPath;
    int bookingReviewId;

};

#endif // NETWORKACCESS_H
