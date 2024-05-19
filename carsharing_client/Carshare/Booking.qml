import QtQuick 6.2
import QtQuick.Controls 6.2
import QtQuick.Layouts 1.12
import networkaccess 1.0



Page {
    id: bookingPage
    title: "Make a Booking"
    property var locale: Qt.locale()
    property date currentDate: new Date()
    property string dateString

    ColumnLayout {
        anchors.fill: parent
        anchors.margins: 20
        spacing: 20

        Text {
            text: qsTr("Make a Booking")
            font.pixelSize: 24
            Layout.alignment: Qt.AlignHCenter
        }

        ColumnLayout {
            Layout.alignment: Qt.AlignHCenter
            spacing: 10

            Text {
                text: qsTr("Start Date:" + dateString)
                font.pixelSize: 18
            }
            Text {
                text: qsTr("Price:" + dateString)
                font.pixelSize: 18
            }

        }



        // Booking Button
        Button {
            text: qsTr("Book Now")
            Layout.alignment: Qt.AlignHCenter
            onClicked: {
                makeBooking();
            }
        }
    }
    Component.onCompleted: {
            dateString = currentDate.toLocaleDateString();
    }


    // NetworkAccess Component
    NetworkAccess {
        id: network
    }

    function makeBooking() {
        var startDate = startDatePicker.date;

        // Assuming the backend expects a specific date format
        var formattedDate = Qt.formatDate(startDate, "yyyy-MM-dd");

        network.bookCar({
            "startDate": formattedDate
        });
    }

    Connections {
        target: network


    }
}
