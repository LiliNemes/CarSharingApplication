import QtQuick 6.2
import QtQuick.Controls 6.2
import networkaccess 1.0

ApplicationWindow {
    minimumWidth: 640
    maximumWidth: 640
    minimumHeight: 480
    maximumHeight: 480
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
                    communication.logOut();
                }
            }
        }
    }

    Rectangle {
        id: networkCommunicationProgress
        width: mainwindow.width
        height: mainwindow.height
        color: "white"
        visible: false
        z: 11
        AnimatedImage {
            source: "load.gif"
            height: 100
            width: 100
            anchors.centerIn: parent
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

        function showLenderCarsView() {
            stack.replace("LenderCars.qml");
        }

        function showLenderMoneyView() {
            stack.replace("LenderMoney.qml");
        }
    }

    Dialog {
        id: dialogNetworkError
        anchors.centerIn: parent
        modal: true
        padding: 20
        contentItem: Label {
            id: dialogNetworkErrorLabel
            color: "red"
        }
    }

    NetworkAccess {
        id: communication
    }

    Connections {
        target: communication
        function onRegistrationSuccesful() {
                    // Change to login.qml upon successful registration
            stack.showLoginView()
        }

        /*
        function onCommunicationStarted() {
            networkCommunicationProgress.visible = true;
        }

        function onCommunicationFinished() {
            networkCommunicationProgress.visible = false;
        }

        function onReportError(strError) {
            dialogNetworkErrorLabel.text = strError;
            dialogNetworkError.open();
        }

        function onAccountCreated() {
            stack.showLoginView();
        }

        function onSuccessfulSignIn(role) {
            if (role === "lender") {
                lenderCars.visible = true;
                lenderMoney.visible = true;
                menuBar.visible = true;
                stack.showLenderMoneyView();
            } else if (role === "renter") {
                renterBookings.visible = true;
                renterCars.visible = true;
                renterMoney.visible = true;
                menuBar.visible = true;
                stack.showRenterMoneyView();
            }
        }

        function onSuccessfulLogOut() {
            lenderCars.visible = false;
            lenderMoney.visible = false;
            menuBar.visible = false;
            renterBookings.visible = false;
            renterCars.visible = false;
            renterMoney.visible = false;
            stack.showLoginView();
        }
        */
    }
}
