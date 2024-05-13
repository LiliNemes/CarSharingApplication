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
    title: "Add maintenance record to an existing car..."

    Rectangle {
        id: rectangle
        width: Constants.width
        height: Constants.height
        anchors.fill: parent

        color: Constants.backgroundColor

        Text {
            id: addMaintenanceRecordToCar_title
            x: 114
            y: 21
            width: 423
            height: 53
            text: qsTr("Add maintenance record to car!")
            font.pixelSize: 30
            verticalAlignment: Text.AlignVCenter
        }

        Rectangle {
            id: rectangle1
            x: 228
            y: 104
            width: 265
            height: 28
            color: "#ffffff"
            radius: 12

            TextInput {
                id: licencePlate_input
                x: 8
                y: 0
                width: 249
                height: 28
                font.pixelSize: 16
                verticalAlignment: Text.AlignVCenter
            }
        }

        Rectangle {
            id: rectangle3
            x: 228
            y: 227
            width: 265
            height: 28
            color: "#ffffff"
            radius: 12

            TextInput {
                id: maintenancePrice__input
                x: 8
                y: 0
                width: 249
                height: 28
                font.pixelSize: 16
            }
        }

        Label {
            id: label
            x: 101
            y: 107
            width: 111
            height: 22
            text: qsTr("Licence Plate:")
        }

        Label {
            id: label2
            x: 101
            y: 233
            width: 111
            height: 17
            text: qsTr("Maintenance price:")
        }

        Label {
            id: label1
            x: 101
            y: 171
            width: 111
            height: 21
            text: qsTr("Maintenance type:")
        }

        Button {
            id: button1
            x: 278
            y: 359
            text: qsTr("Submit")
            onClicked: {
                close()
                //TODO if sikerult felvenni adatbazisba dobjon egy successful buborekot
            }
        }

        Rectangle {
            id: rectangle2
            x: 228
            y: 104
            width: 265
            height: 28
            color: "#ffffff"
            radius: 12
            TextInput {
                id: licencePlate__input
                x: 8
                y: 0
                width: 249
                height: 28
                font.pixelSize: 16
                verticalAlignment: Text.AlignVCenter
            }
        }

        Rectangle {
            id: rectangle4
            x: 228
            y: 167
            width: 265
            height: 28
            color: "#ffffff"
            radius: 12
            TextInput {
                id: maintenanceType__input
                x: 8
                y: 0
                width: 249
                height: 28
                font.pixelSize: 16
                verticalAlignment: Text.AlignVCenter
            }
        }

        Button {
            id: button
            x: 17
            y: 21
            text: qsTr("Back")
            onClicked: close()
        }

        Rectangle {
            id: rectangle5
            x: 228
            y: 295
            width: 265
            height: 28
            color: "#ffffff"
            radius: 12
            TextInput {
                id: maintenance_date__input
                x: 8
                y: 0
                width: 249
                height: 28
                font.pixelSize: 16
            }
        }

        Label {
            id: label3
            x: 101
            y: 301
            width: 111
            height: 17
            text: qsTr("Maintenance date:")
        }

        states: [
            State {
                name: "clicked"
            }
        ]
    }


}

