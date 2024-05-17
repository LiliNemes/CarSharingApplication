#include <QGuiApplication>
#include <QQmlApplicationEngine>

#include "networkaccess.h"

int main(int argc, char *argv[])
{
    QGuiApplication app(argc, argv);

    QQmlApplicationEngine engine;
    qmlRegisterType<NetworkAccess>("networkaccess", 1, 0, "NetworkAccess");


    const QUrl url(QStringLiteral("qrc:/Carshare/Main.qml"));
    QObject::connect(
        &engine,
        &QQmlApplicationEngine::objectCreationFailed,
        &app,
        []() { QCoreApplication::exit(-1); },
        Qt::QueuedConnection);
    engine.load(url);

    return app.exec();    
}
