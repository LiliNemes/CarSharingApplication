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

    visible: true
    title: "Car Share, it doesn't count anymore!"

    Rectangle {
        id: rectangle
        width: Constants.width
        height: Constants.height
        anchors.fill: parent

        color: Constants.backgroundColor

        Text {
            id: text1
            x: 163
            y: 21
            width: 309
            height: 53
            text: qsTr("Welcome to car share!")
            font.pixelSize: 30
            verticalAlignment: Text.AlignVCenter
        }

        Button {
            id: addNewCar__button
            x: 147
            y: 89
            width: 325
            height: 52
            text: qsTr("Add new car")
            onClicked: stack.push(addNewCarComponent)
        }

        Button {
            id: deleteCar__button
            x: 147
            y: 205
            width: 325
            height: 52
            text: qsTr("Delete car")
            onClicked: stack.push(deleteCarComponent)
        }

        Button {
            id: addMaintenanceRecordToCar__button
            x: 147
            y: 263
            width: 325
            height: 52
            text: qsTr("Add maintenance record to car")
            onClicked: stack.push(addMaintenanceRecordToCarComponent)
        }

        Button {
            id: deleteMaintenanceRecordFromCar__button
            x: 147
            y: 321
            width: 325
            height: 52
            text: qsTr("Delete maintenance record from car")
            onClicked: stack.push(deleteMaintenanceFromCarComponent)
        }

        Button {
            id: viewCars__button
            x: 147
            y: 379
            width: 325
            height: 52
            text: qsTr("View cars")
            onClicked: stack.push(viewCarsComponent)
        }

        Button {
            id: viewPayments__button
            x: 147
            y: 437
            width: 325
            height: 52
            text: qsTr("View payments")
            onClicked: stack.push(viewPaymentsComponent)
        }

        Button {
            id: modifyCar__button
            x: 147
            y: 147
            width: 325
            height: 52
            text: qsTr("Modify car")
            onClicked: stack.push(modifyCarComponent)
        }

        Button {
            id: viewBookings__button
            x: 147
            y: 495
            width: 325
            height: 52
            text: qsTr("View bookings")
            onClicked: stack.push(viewBookingsComponent)
        }

        states: [
            State {
                name: "clicked"
            }
        ]
    }
}
