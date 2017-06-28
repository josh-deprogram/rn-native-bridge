# React Native Native Components
Prototyping with the linking and bridging of Native components in the world of React Native

## SwiftBridge
A simple iOS Native Swift component. Communication between JS and Swift. 
A sample native call to trigger the device Flash light/Screen brightness called from the JS app.

Issues compiling - 
It was required to set Legacy Swift Version Compiling (Located in Build Setting), due to Xcode8 now defaulting to Swift 3.

Setup:
```
npm install 
react-native start
```

- build project with xcode
- in ios simulator, use remote debug and inspect with chrome, or compile to device


## AndroidBridge
A sample Android module calling Native features. In this case toggling the Flashlight and a Toast notification.
Our custom Modules sit in android/app/src/main/java/com/androidbridge

Setup:
```
npm install 
react-native start
react-native run-android
```

run-android will likely run into compile issues so then open up Android Studio, Clean & Build project, resolving Gradle issues as promopted

This also will be required each time the device is attached via USB.
Run the following command inside the Android SDK Platform tools. 
The location of the SDK can be found by opening Android Studio then > preferences > Android SDK. 
Then cd into the platform-tools directory...

```sh
./adb reverse tcp:8081 tcp:8081
```