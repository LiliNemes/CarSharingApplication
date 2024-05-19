#include "car.h"

Car::Car(const QString &name, const QString &model, double price,
         const QString &location, const QString &owner, const QString &licensePlate, int releaseYear)
    : m_name(name), m_model(model), m_price(price),
    m_location(location), m_owner(owner), m_licensePlate(licensePlate), m_releaseYear(releaseYear) {}

QString Car::name() const {
    return m_name;
}

void Car::setName(const QString &name) {
    m_name = name;
}

QString Car::model() const {
    return m_model;
}

void Car::setModel(const QString &model) {
    m_model = model;
}

double Car::pricePerDay() const {
    return m_price;
}

void Car::setPricePerDay(double pricePerDay) {
    m_price = pricePerDay;
}

QString Car::location() const {
    return m_location;
}

void Car::setLocation(const QString &location) {
    m_location = location;
}

QString Car::owner() const {
    return m_owner;
}

void Car::setOwner(const QString &owner) {
    m_owner = owner;
}

QString Car::licensePlate() const {
    return m_licensePlate;
}

void Car::setLicensePlate(const QString &licensePlate) {
    m_licensePlate = licensePlate;
}

int Car::releaseYear() const {
    return m_releaseYear;
}

void Car::setReleaseYear(int releaseYear) {
    m_releaseYear = releaseYear;
}
