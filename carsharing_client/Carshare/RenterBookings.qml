import QtQuick 6.2
import QtQuick.Controls 6.2

Page {
    id: renterBookingsView

    visible: true
    title: "Bookings"

    ListView {
        id: renterBookingsList
        width: parent.width
        height: parent.height

        delegate: Item {
            width: parent.width
            height: 150

            Rectangle {
                width: parent.width
                height: parent.height
                color: "transparent"

                Column {
                    anchors.fill: parent
                    anchors.margins: 10
                    spacing: 5

                    Row {
                        spacing: 10
                        Text { text: qsTr("Booking ID:") }
                        Text { text: bookingId }
                    }

                    Row {
                        spacing: 10
                        Text { text: qsTr("License plate:") }
                        Text { text: licensePlate }
                    }

                    Row {
                        spacing: 10
                        Text { text: qsTr("Price per day:") }
                        Text { text: pricePerDay }
                    }

                    Row {
                        spacing: 10
                        Text { text: qsTr("Start time:") }
                        Text { text: startTime }
                    }

                    Row {
                        spacing: 10
                        Text { text: qsTr("End time:") }
                        Text { text: endTime }
                    }
                }

                Button {
                    id: buttonEndBooking
                    anchors.right: parent.right
                    anchors.bottom: parent.bottom
                    width: 170
                    height: 25
                    text: "End Booking"
                    visible: visibleStop
                    onClicked: {
                        communication.endBooking(bookingId)
                    }
                }

                Button {
                    id: buttonReviewBooking
                    anchors.right: parent.right
                    anchors.bottom: parent.bottom
                    width: 170
                    height: 25
                    text: "Review"
                    visible: visibleReview
                    onClicked: {
                        communication.clickedOnReview(bookingId);

                        stack.showAddReview()
                    }
                }
            }
        }

        model: ListModel {
            id: bookingsListViewModel
        }

        Component.onCompleted: {
            refresh();
        }

        function refresh() {
            bookingsListViewModel.clear();
            communication.fetchBookings();
        }

        Connections {
            target: communication

            function onBookingsFetchedEnded(bookingId, licensePlate, start_time, end_time, price_per_day, isReviewed) {
                bookingsListViewModel.append({
                    "bookingId": bookingId,
                    "licensePlate": licensePlate,
                    "startTime": start_time,
                    "endTime": end_time,
                    "pricePerDay": price_per_day,
                    "visibleStop": false,
                    "visibleReview": isReviewed
                });
            }

            function onBookingsFetchedGoing(bookingId, licensePlate, start_time, price_per_day) {
                bookingsListViewModel.append({
                    "bookingId": bookingId,
                    "licensePlate": licensePlate,
                    "startTime": start_time,
                    "endTime": "-",
                    "pricePerDay": price_per_day,
                    "visibleStop": true,
                    "visibleReview": false
                });
            }

            function onEndBookingFinished(){
                bookingsListViewModel.clear();
                communication.fetchBookings();
            }
        }
    }
}
