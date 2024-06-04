import QtQuick 6.2
import QtQuick.Controls 6.2
Page {

    visible: true
    title: "Add a review for this booking"

    Rectangle {
        id: rectangle
        anchors.fill: parent

        Label {
            id: addNewCar__Title
            x: 200
            y: 21
            width: 257
            height: 53
            text: qsTr("Add a review for this booking")
            font.pixelSize: 30
            horizontalAlignment: Text.AlignHCenter
            verticalAlignment: Text.AlignVCenter
            clip: false
        }



        Label {
            id: label
            x: 132
            y: 107
            width: 80
            height: 22
            text: qsTr("Add a score for the booking between 1-5!")
        }


        Label {
            id: label1
            x: 132
            y: 235
            width: 80
            height: 21
            text: qsTr("Please describe your experiences regarding this booking!")
        }



        Rectangle {
            id: rectangle2
            x: 132
            y: 171
            width: 265
            height: 28
            color: "#ffffff"
            border.color: "black"
            TextInput {
                id: reviewRating__input
                anchors.fill: parent
                anchors.margins: 2
                horizontalAlignment: Text.AlignLeft
                verticalAlignment: Text.AlignVCenter
                activeFocusOnPress: true
                cursorVisible: true
                font.pixelSize: 16
            }
        }

        Rectangle {
            id: rectangle4
            x: 132
            y: 299
            width: 265
            height: 200
            color: "#ffffff"
            border.color: "black"
            TextInput {
                id: reviewText__input
                anchors.fill: parent
                anchors.margins: 2
                horizontalAlignment: Text.AlignLeft
                verticalAlignment: Text.AlignTop
                activeFocusOnPress: true
                cursorVisible: true
                font.pixelSize: 16
            }
        }



        Button {
            id: button
            x: 17
            y: 21
            text: qsTr("Back")
            onClicked: {
                backFromReview();
                stack.showRenterBookingsView();
            }
        }
        Button {
            id: submit__button
            x: 245
            y: 503
            text: qsTr("Submit")
            onClicked: {
                communication.addNewBooking(reviewRating__input.text, reviewText__input.text)
            }
        }

        states: [
            State {
                name: "clicked"
            }
        ]
        Connections {
            target: communication

            function onEndAddReviewFinished()
            {
                communication.backFromReview();
                stack.showRenterBookingsView();
            }
        }
    }
}
