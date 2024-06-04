import QtQuick 6.2
import QtQuick.Controls 6.2
import networkaccess 1.0

ApplicationWindow {
    minimumWidth: 640
    maximumWidth: 640
    minimumHeight: 600
    maximumHeight: 600
    visible: true
    title: "Car Share, it doesn't count anymore!"
    id: mainwindow

    menuBar: MenuBar {
        visible: false
        id: menuBar
        z: 150
        Menu {
            title: "Views"
            MenuItem {
                id: renterCars
                visible: false
                text: "Cars"
                onTriggered: {
                    stack.showRenterCarsView();
                }
            }
            MenuItem {
                id: renterMoney
                visible: false
                text: "Money"
                onTriggered: {
                    stack.showRenterMoneyView();
                }
            }
            MenuItem {
                id: renterBookings
                visible: false
                text: "Bookings"
                onTriggered: {
                    stack.showRenterBookingsView();
                }
            }
            MenuItem {
                id: lenderCars
                visible: false
                text: "Cars"
                onTriggered: {
                    stack.showLenderCarsView();
                }
            }
            MenuItem {
                id: lenderMoney
                visible: false
                text: "Money"
                onTriggered: {
                    stack.showLenderMoneyView();
                }
            }
        }
        Menu {
            title: "Profile"
            MenuItem {
                text: "Log out"
                onTriggered: {
                    communication.logout();
                }
            }
        }
    }

    StackView {
        id: stack
        z: 10
        anchors.fill: parent
        initialItem: "Login.qml"
        replaceEnter: null // no animation
        replaceExit: null
        property int selectedRoomId: -1

        function showLoginView() {
            menuBar.visible = false;
            stack.replace("Login.qml");
        }

        function showRegistrationView() {
            menuBar.visible = false;
            stack.replace("Registration.qml");
        }

        function showRenterCarsView() {
            stack.replace("RenterCars.qml");
        }

        function showRenterMoneyView() {
            stack.replace("RenterMoney.qml");
        }

        function showRenterBookingsView() {
            stack.replace("RenterBookings.qml");
        }

        function showOwnerCarsView() {
            stack.replace("OwnerCars.qml");
        }

        function showLenderMoneyView() {
            stack.replace("LenderMoney.qml");
        }
        function showBookingView() {
            stack.replace("Booking.qml");
        }
        function showAddReview(){
            stack.replace("AddReview.qml");
        }
        function showReviewStatistics(){
            stack.replace("ReviewStatistics.qml");
        }
    }

    NetworkAccess {
        id: communication
    }

    Connections {
        target: communication
        function onRegistrationSuccesful() {
            stack.showLoginView()   // Change to login.qml upon successful registration
        }


        function onLoginSuccesfulOwner(){
            stack.showOwnerCarsView()
            menuBar.visible = true
            renterBookings.visible = false
            renterCars.visible = false
        }

        function onLoginSuccesfulRenter(){
            stack.showRenterCarsView()
            menuBar.visible = true
            renterBookings.visible = true
            renterCars.visible = true
        }

        function onLogoutSuccesful(){
            lenderCars.visible = false;
            lenderMoney.visible = false;
            menuBar.visible = false;
            renterBookings.visible = false;
            renterCars.visible = false;
            renterMoney.visible = false;
            stack.showLoginView();
        }

        function onCarAdded(){
            stack.showOwnerCarsView()
        }
    }
}
