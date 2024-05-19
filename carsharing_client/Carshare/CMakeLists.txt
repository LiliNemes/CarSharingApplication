cmake_minimum_required(VERSION 3.16)

project(Carshare VERSION 0.1 LANGUAGES CXX)

set(CMAKE_CXX_STANDARD_REQUIRED ON)

find_package(Qt6 6.4 REQUIRED COMPONENTS Charts Core Gui Qml Quick)

qt_standard_project_setup()


qt_add_executable(appCarshare
    main.cpp
    networkaccess.cpp
    networkaccess.h
    car.cpp
    car.h
)

qt_add_qml_module(appCarshare
    URI Carshare
    VERSION 1.0
    QML_FILES
        Main.qml
        Login.qml
        Registration.qml
        AddNewCar.qml
        RenterBookings.qml
        OwnerCars.qml
        RenterMoney.qml
        RenterCars.qml
        Booking.qml
        AddReview.qml
        ReviewStatistics.qml
)

# Qt for iOS sets MACOSX_BUNDLE_GUI_IDENTIFIER automatically since Qt 6.1.
# If you are developing for iOS or macOS you should consider setting an
# explicit, fixed bundle identifier manually though.
set_target_properties(appCarshare PROPERTIES
#    MACOSX_BUNDLE_GUI_IDENTIFIER com.example.appCarshare
    MACOSX_BUNDLE_BUNDLE_VERSION ${PROJECT_VERSION}
    MACOSX_BUNDLE_SHORT_VERSION_STRING ${PROJECT_VERSION_MAJOR}.${PROJECT_VERSION_MINOR}
    MACOSX_BUNDLE TRUE
    WIN32_EXECUTABLE TRUE
)

target_link_libraries(appCarshare PRIVATE
    Qt6::Quick
    Qt::Charts
    Qt::Core
    Qt::Gui
    Qt::Qml
)

include(GNUInstallDirs)
install(TARGETS appCarshare
    BUNDLE DESTINATION .
    LIBRARY DESTINATION ${CMAKE_INSTALL_LIBDIR}
    RUNTIME DESTINATION ${CMAKE_INSTALL_BINDIR}
)
