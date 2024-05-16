#include <QGuiApplication>
#include <QQmlApplicationEngine>

#include "networkcomms.h"

int main(int argc, char *argv[])
{
    QGuiApplication app(argc, argv);

    QQmlApplicationEngine engine;
    qmlRegisterType<NetworkComms>("networkcomms", 1, 0, "NetworkComms");

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
