// Copyright (C) 2021 The Qt Company Ltd.
// SPDX-License-Identifier: LicenseRef-Qt-Commercial OR GPL-3.0-only

import QtQuick 6.2
import Carshare
import QtQuick.Controls 6.2

Window {
    width: Constants.width
    height: Constants.height
    minimumWidth: Constants.width
    minimumHeight: Constants.height
    maximumWidth: Constants.width

    visible: false
    title: "Carshare"

    StackView{
        id: stack
        z: 10
        anchors.fill: parent
        initialItem: "Main.qml"
    }

    Component{
        id: addNewCarComponent
        AddNewCar{}
    }

    Component{
        id: deleteCarComponent
        DeleteCar{}
    }

    Component{
        id: addMaintenanceRecordToCarComponent
        AddMaintenanceToCar{}
    }

    Component{
        id: deleteMaintenanceFromCarComponent
        DeleteMaintenanceFromCar{}
    }

    Component{
        id: modifyCarComponent
        ModifyCar{}
    }

    Component{
        id: viewPaymentsComponent
        ViewPayments{}
    }

    Component{
        id: viewCarsComponent
        ViewCars{}
    }

    Component{
        id: viewBookingsComponent
        ViewBookings{}
    }

}


