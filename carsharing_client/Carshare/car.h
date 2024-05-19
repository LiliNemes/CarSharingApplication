#ifndef CAR_H
#define CAR_H

#include <QString>
class Car
{
public:
    Car(const QString &name, const QString &model, double price,
        const QString &location, const QString &owner, const QString &licensePlate, int releaseYear);

    QString name() const;
    void setName(const QString &name);

    QString model() const;
    void setModel(const QString &model);

    double pricePerDay() const;
    void setPricePerDay(double pricePerDay);

    QString location() const;
    void setLocation(const QString &location);

    QString owner() const;
    void setOwner(const QString &owner);

    QString licensePlate() const;
    void setLicensePlate(const QString &licensePlate);

    int releaseYear() const;
    void setReleaseYear(int releaseYear);

private:
    QString m_name;
    QString m_model;
    QString m_location;
    double m_price;
    QString m_owner;
    QString m_licensePlate;
    int m_releaseYear;

};

#endif // CAR_H
